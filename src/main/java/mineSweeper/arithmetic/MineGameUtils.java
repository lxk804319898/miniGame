package mineSweeper.arithmetic;

import mineSweeper.bean.MineBean;
import mineSweeper.bean.MineType;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author duke
 * @Date 2017年6月4日 下午12:36:27
 * @Description 扫雷游戏核心算法
 */
public class MineGameUtils {
	private int mMineCount;// 雷子个数
	public int mRowNums;// 格子行数
	public int mColumnNums;// 格子列数

	private MineBean[][] beanArr;// 雷子状态对应的数组

	private int unOpenMines;// 剩余未开启格子数量
	private int flagMines;// 标记为雷子的个数

	private long gameStartTime;// 游戏开始时间
	private boolean isGameOver = false;// 游戏是否结束

	private Random random = new Random();

	private CallBack callBack;

	public CallBack getCallBack() {
		return callBack;
	}

	public void setCallBack(CallBack callBack) {
		this.callBack = callBack;
	}

	/**
	 * 构造函数
	 * 
	 * @param mineCount
	 *            总共雷子个数
	 * @param rowNums
	 *            格子行数
	 * @param columnNums
	 *            格子烈数
	 * @param callBack
	 *            回调
	 */
	public MineGameUtils(int mineCount, int rowNums, int columnNums, CallBack callBack) {
		if (mineCount <= 1 || rowNums <= 1 || columnNums <= 1) {
			return;
		}
		this.mMineCount = mineCount;
		this.mRowNums = rowNums;
		this.mColumnNums = columnNums;
		this.callBack = callBack;

		// 初始化
		reset(false);
	}

	public void reset(boolean isReset) {
		resetOrCreateGrids(mRowNums, mColumnNums);
		makeRandomMines();
		initGridAroundStatus();
		unOpenMines = beanArr.length * beanArr[0].length;
		flagMines = 0;
		gameStartTime = 0;
		isGameOver = false;
		if (this.callBack != null) {
			this.callBack.onInit(isReset);
		}
	}

	// 初始化所有格子
	private void resetOrCreateGrids(int rowNums, int columnNums) {
	    if(beanArr == null) {
            beanArr = new MineBean[rowNums][columnNums];
        }
		for (int i = 0; i < beanArr.length; i++) {
			for (int j = 0; j < beanArr[i].length; j++) {
				if (beanArr[i][j] == null) {
					beanArr[i][j] = new MineBean();
				}
				beanArr[i][j].reset();
			}
		}
	}

	// 初始化随机雷子
	private void makeRandomMines() {
		if (beanArr == null) {
			return;
		}
		int nowMines = 0;
		while (nowMines < mMineCount) {
			int i = random.nextInt(beanArr.length);
			int j = random.nextInt(beanArr[0].length);
			MineBean mineBean = beanArr[i][j];
			if (!mineBean.isMineNow()) {
				mineBean.setMineNow();
				nowMines++;
			}
		}
	}

	// 计算格子周围雷子状态
	private void initGridAroundStatus() {
		if (beanArr == null) {
			return;
		}
		for (int i = 0; i < beanArr.length; i++) {
			for (int j = 0; j < beanArr[i].length; j++) {
				MineBean mineBean = beanArr[i][j];
				if (mineBean.isMineNow()) {
					// 当前格子是雷
					continue;
				}
				// 取当前格子周围有效的格子集合
				ArrayList<Point> list = getAroundGrids(i, j);
				int mineCount = 0;
				Point tempPoint = null;
				MineBean tempBean = null;
				// 统计这些点是否是雷子
				for (int k = 0; k < list.size(); k++) {
					tempPoint = list.get(k);
					if (tempPoint == null) {
						continue;
					}
					tempBean = getMineBean(tempPoint.x, tempPoint.y);
					if (tempBean == null) {
						continue;
					}
					if (tempBean.isMineNow()) {
						mineCount++;
					}
				}
				mineBean.setMineCount(mineCount);
			}
		}
	}

	private ArrayList<Point> getAroundGrids(int i, int j) {
		if (beanArr == null) {
			return null;
		}
		// 取当前格子周围的8个点
		Point point1 = new Point((i - 1), (j - 1));
		Point point2 = new Point((i - 1), (j));
		Point point3 = new Point((i - 1), (j + 1));
		Point point4 = new Point((i), (j - 1));
		Point point5 = new Point((i), (j + 1));
		Point point6 = new Point((i + 1), (j - 1));
		Point point7 = new Point((i + 1), (j));
		Point point8 = new Point((i + 1), (j + 1));
		ArrayList<Point> aroundList = new ArrayList<>();
		aroundList.add(point1);
		aroundList.add(point2);
		aroundList.add(point3);
		aroundList.add(point4);
		aroundList.add(point5);
		aroundList.add(point6);
		aroundList.add(point7);
		aroundList.add(point8);
		for (int k = 0; k < aroundList.size(); k++) {
			Point pointTemp = aroundList.get(k);
			if (pointTemp.x < 0 || pointTemp.x >= beanArr.length || pointTemp.y < 0
					|| pointTemp.y >= beanArr[0].length) {
				// 越界
				aroundList.remove(k);
				k--;
			}
		}
		return aroundList;
	}

	public void leftClick(int i, int j) {
		if (beanArr == null || this.callBack == null || this.isGameOver) {
			return;
		}
		MineBean mineBean = getMineBean(i, j);
		if (mineBean == null) {
			return;
		}
		if (mineBean.isClickOpen()) {
			return;
		}
		mineBean.setClickOpen(true);
		if (unOpenMines < 0) {
			unOpenMines = 0;
		}
		unOpenMines--;
		if (mineBean.isMineNow()) {
			isGameOver = true;
			gameOver(i, j);
			return;
		}
		if (gameStartTime <= 0) {
			gameStartTime = System.currentTimeMillis();
		}
		if (this.callBack != null) {
			this.callBack.onLeftClick(mineBean, i, j);
		}
		checkWin();
		if (mineBean.getMineCount() == MineType.MINE_STATUS_OPEN_0) {
			// 递归摊开一片
			recursionAround(i, j);
		}
	}

	private void recursionAround(int i, int j) {
		ArrayList<Point> list = getAroundGrids(i, j);
		for (int k = 0; k < list.size(); k++) {
			Point tempPoint = list.get(k);
			if (tempPoint == null) {
				continue;
			}
			MineBean mineBean = getMineBean(tempPoint.x, tempPoint.y);
			if (mineBean == null) {
				continue;
			}
			if (mineBean.isMineNow()) {
				continue;
			}
			if (mineBean.isClickOpen()) {
				continue;
			}
			leftClick(tempPoint.x, tempPoint.y);
		}
	}

	public void rightClick(int i, int j) {
		if (beanArr == null || this.callBack == null || this.isGameOver) {
			return;
		}
		MineBean mineBean = getMineBean(i, j);
		if (mineBean == null) {
			return;
		}
		if (mineBean.isClickOpen()) {
			return;
		}
		if (gameStartTime <= 0) {
			gameStartTime = System.currentTimeMillis();
		}
		if (flagMines < 0) {
			flagMines = 0;
		}
		if (mineBean.getImageStatus() == MineType.MINE_STATUS_BLANK) {
			mineBean.setImageStatus(MineType.MINE_STATUS_FLAG);
			flagMines++;
		} else if (mineBean.getImageStatus() == MineType.MINE_STATUS_FLAG) {
			mineBean.setImageStatus(MineType.MINE_STATUS_UNKNOW);
			flagMines--;
		} else if (mineBean.getImageStatus() == MineType.MINE_STATUS_UNKNOW) {
			mineBean.setImageStatus(MineType.MINE_STATUS_BLANK);
		}
		if (this.callBack != null) {
			this.callBack.onRightClick(mineBean, i, j);
		}
		//checkWin();
	}

	private void checkWin() {
		if(mMineCount != unOpenMines) {
			return;
		}
//		if (flagMines != unOpenMines) {
//			return;
//		}
		isGameOver = true;
		if (this.callBack != null) {
			this.callBack.onWin(System.currentTimeMillis() - gameStartTime);
		}
	}

	private void gameOver(int i, int j) {
		if (beanArr == null || this.callBack == null) {
			return;
		}
		MineBean mineBean = getMineBean(i, j);
		if (mineBean != null) {
			mineBean.setImageStatus(MineType.MINE_STATUS_MINE_CLICK);
			if (this.callBack != null) {
				this.callBack.onRightClick(mineBean, i, j);
			}
		}
		for (int k1 = 0; k1 < beanArr.length; k1++) {
			for (int k2 = 0; k2 < beanArr[k1].length; k2++) {
				mineBean = getMineBean(k1, k2);
				if (!mineBean.isClickOpen()) {
					if (mineBean.isMineNow()) {
						mineBean.setImageStatus(MineType.MINE_STATUS_OPEN_9);
					} else {
						mineBean.setImageStatus(mineBean.getMineCount());
					}
					if (this.callBack != null) {
						this.callBack.onRightClick(mineBean, k1, k2);
					}
				}
			}
		}
		if (this.callBack != null) {
			this.callBack.onGameOver();
		}
	}

	public MineBean getMineBean(int i, int j) {
		if (beanArr == null) {
			return null;
		}
		try {
			return beanArr[i][j];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public interface CallBack {
		void onInit(boolean isReset);

		void onWin(long time);// 胜利

		void onGameOver();// 失败

		void onLeftClick(MineBean mineBean, int i, int j);

		void onRightClick(MineBean mineBean, int i, int j);
	}

	public void printArrayInt() {
		if (beanArr == null) {
			return;
		}
		for (int i = 0; i < beanArr.length; i++) {
			for (int j = 0; j < beanArr[i].length; j++) {
				System.out.print(beanArr[i][j].getMineCount());
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}
