package mineSweeper.bean;

/**
 * @author duke
 * @Date 2017年6月11日 下午3:58:08
 * @Description 格子图片状态
 */
public class MineType {
	public static final int MINE_STATUS_OPEN_0 = 0;// 周围8个格子中没有雷
	public static final int MINE_STATUS_OPEN_1 = 1;// 周围8个格子中有1个雷
	public static final int MINE_STATUS_OPEN_2 = 2;// 周围8个格子中有2个雷
	public static final int MINE_STATUS_OPEN_3 = 3;// 周围8个格子中有3个雷
	public static final int MINE_STATUS_OPEN_4 = 4;// 周围8个格子中有4个雷
	public static final int MINE_STATUS_OPEN_5 = 5;// 周围8个格子中有5个雷
	public static final int MINE_STATUS_OPEN_6 = 6;// 周围8个格子中有6个雷
	public static final int MINE_STATUS_OPEN_7 = 7;// 周围8个格子中有7个雷
	public static final int MINE_STATUS_OPEN_8 = 8;// 周围8个格子中有8个雷
	public static final int MINE_STATUS_OPEN_9 = 9;// 周围8个格子中有9个雷
	public static final int MINE_STATUS_BLANK = 10;// 默认格子图片
	public static final int MINE_STATUS_FLAG = 11;// 格子标记为旗子
	public static final int MINE_STATUS_UNKNOW = 12;// 格子标记为问号
	public static final int MINE_STATUS_MINE_CLICK = 13;// 点击了雷子时的图片
	public static final int MINE_STATUS_DEAD = 14;// 失败时，顶部按钮图片
	public static final int MINE_STATUS_MILE = 15;// 开始时，顶部按钮图片
	public static final int MINE_STATUS_WAIT = 16;// 等待时，顶部按钮图片
	public static final int MINE_STATUS_WIN = 17;// 胜利时，顶部按钮图片
	public static final int MINE_STATUS_LOGO = 18;// logo标记
}
