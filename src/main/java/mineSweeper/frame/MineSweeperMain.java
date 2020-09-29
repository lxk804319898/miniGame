package mineSweeper.frame;

import mineSweeper.arithmetic.MineGameUtils;
import mineSweeper.bean.MineBean;
import mineSweeper.bean.MineImage;
import mineSweeper.bean.MineType;
import mineSweeper.listener.MyMouseListener;
import mineSweeper.tools.SizeTools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 * @author duke
 * @Date 2017年6月4日 下午12:35:51
 * @Description
 */
public class MineSweeperMain extends JFrame {
	private static final long serialVersionUID = -125417968666795289L;
	public final int mMineCount = 75;// 雷子个数
	public final int mRowNums = 25;// 格子行数
	public final int mColumnNums = 30;// 格子列数

	public final int mGridSideLength = 25;// 格子的边长

	private JButton[][] buttonArr;
	private JButton resetBtn;

	private boolean isClickComplete = true;

	private int mWidth;
	private int mHeight;

	private MineGameUtils gameUtils;

	private static String GAME_TITLE = "扫雷游戏  - <duke>";

	// 入口
	public static void main(String[] args) {
		new MineSweeperMain();
	}

	public MineSweeperMain() {
		// 初始化UI对应的背景数组状态
		gameUtils = new MineGameUtils(mMineCount, mRowNums, mColumnNums, callback);

		mWidth = mColumnNums * mGridSideLength;
		mHeight = mRowNums * mGridSideLength;

		this.setResizable(false);
		this.setTitle(GAME_TITLE);
		this.setIconImage(SizeTools.getLogo());
		Dimension dimension = SizeTools.getScreenSize();
		// 设置窗口左上角位置
		this.setLocation((dimension.width - mWidth) >> 1,
				((dimension.height - mHeight) >> 1) - SizeTools.getInsetsSize(this));
		// 设置窗口宽高
		this.setSize(mWidth, mHeight);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(topLayout(), BorderLayout.NORTH);
		this.add(centerLayout(), BorderLayout.CENTER);
		// 显示窗口
		this.setVisible(true);
	}

	public JPanel topLayout() {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		resetBtn = new JButton();
		resetBtn.setBorderPainted(false); // 消除边框
		resetBtn.setContentAreaFilled(false); // 消除内容域，即中间的那一块
		setButtonImage(resetBtn, MineType.MINE_STATUS_MILE);
		resetBtn.addMouseListener(new MyMouseListener() {
			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				setButtonImage(resetBtn, MineType.MINE_STATUS_WAIT);
			}

			@Override
			public void mouseReleased(MouseEvent paramMouseEvent) {
				setButtonImage(resetBtn, MineType.MINE_STATUS_MILE);
			}

			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				// 重新开始
				if (gameUtils == null) {
					return;
				}
				gameUtils.reset();
			}
		});
		panel.add(resetBtn);
		return panel;
	}

	public JPanel centerLayout() {
		JPanel panel = new JPanel();
		GridLayout gridLayout = new GridLayout(mRowNums, mColumnNums);
		panel.setLayout(gridLayout);
		if (buttonArr == null) {
			return panel;
		}
		for (int i = 0; i < mRowNums; i++) {
			for (int j = 0; j < mColumnNums; j++) {
				panel.add(buttonArr[i][j]);
				final int ii = i;
				final int jj = j;
				buttonArr[i][j].addMouseListener(new MyMouseListener() {
					@Override
					public void mouseLeftClicked(MouseEvent paramMouseEvent) {
						leftClick(ii, jj);
					}

					@Override
					public void mouseRightClicked(MouseEvent paramMouseEvent) {
						rightClick(ii, jj);
					}
				});
			}
		}
		return panel;
	}

	private void initButtons() {
		isClickComplete = true;
		if (buttonArr == null) {
			buttonArr = new JButton[mRowNums][mColumnNums];
		}
		for (int i = 0; i < mRowNums; i++) {
			for (int j = 0; j < mColumnNums; j++) {
				if (buttonArr[i][j] == null) {
					buttonArr[i][j] = new JButton();
				}
				setButtonImage(buttonArr[i][j], MineType.MINE_STATUS_BLANK);
			}
		}
	}

	public void leftClick(int i, int j) {
		if (gameUtils == null) {
			return;
		}
		// 防止重复点击
		if (!isClickComplete) {
			return;
		}
		isClickComplete = false;
		gameUtils.leftClick(i, j);
		isClickComplete = true;
	}

	public void rightClick(int i, int j) {
		if (gameUtils == null) {
			return;
		}
		gameUtils.rightClick(i, j);
	}

	public void setButtonImage(JButton button, int flag) {
		if (button == null) {
			return;
		}
		ImageIcon icon = MineImage.getImageIcon(mGridSideLength, mGridSideLength, flag);
		button.setIcon(icon);
	}

	private MineGameUtils.CallBack callback = new MineGameUtils.CallBack() {

		@Override
		public void onWin(long time) {
			if (resetBtn != null) {
				setButtonImage(resetBtn, MineType.MINE_STATUS_WIN);
			}
			JOptionPane.showMessageDialog(MineSweeperMain.this, "胜利! 用时: " + time / 1000 + " 秒");
		}

		@Override
		public void onInit() {
			initButtons();
		}

		@Override
		public void onGameOver() {
			if (resetBtn != null) {
				setButtonImage(resetBtn, MineType.MINE_STATUS_DEAD);
			}
			JOptionPane.showMessageDialog(MineSweeperMain.this, "好像是输了?!");
		}

		@Override
		public void onLeftClick(MineBean mineBean, int i, int j) {
			if (buttonArr == null || mineBean == null) {
				return;
			}
			setButtonImage(buttonArr[i][j], mineBean.getMineCount());
		}

		@Override
		public void onRightClick(MineBean mineBean, int i, int j) {
			if (buttonArr == null || mineBean == null) {
				return;
			}
			setButtonImage(buttonArr[i][j], mineBean.getImageStatus());
		}
	};
}
