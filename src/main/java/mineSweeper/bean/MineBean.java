package mineSweeper.bean;

/**
 * @author duke
 * @Date 2017年6月4日 下午8:42:50
 * @Description 每个格子携带数据的对象
 */
public class MineBean {
	public static int MINE_VALUE = 9;// 是雷子的格子值

	private boolean isClickOpen = false;// 是否左键点开了格子
	private int mineCount = 0;// 周围雷的个数标记值(如果是9，则为雷)
	private int imageStatus = MineType.MINE_STATUS_BLANK;// 未点开图片状态

	public MineBean() {
	}

	public MineBean(boolean isClickOpen, int mineCount, int imageStatus) {
		super();
		this.isClickOpen = isClickOpen;
		this.mineCount = mineCount;
		this.imageStatus = imageStatus;
	}

	public boolean isClickOpen() {
		return isClickOpen;
	}

	public void setClickOpen(boolean isClickOpen) {
		this.isClickOpen = isClickOpen;
	}

	public int getMineCount() {
		return mineCount;
	}

	public void setMineCount(int mineCount) {
		this.mineCount = mineCount;
	}

	public int getImageStatus() {
		return imageStatus;
	}

	public void setImageStatus(int imageStatus) {
		this.imageStatus = imageStatus;
	}

	// 判断当前位置是否是雷子
	public boolean isMineNow() {
		return mineCount == MINE_VALUE;
	}

	public void setMineNow() {
		mineCount = MINE_VALUE;
	}

	public void reset() {
		isClickOpen = false;// 是否左键点开了格子
		mineCount = 0;// 周围雷的个数标记值(如果是9，则为雷)
		imageStatus = MineType.MINE_STATUS_BLANK;// 图片状态
	}
}
