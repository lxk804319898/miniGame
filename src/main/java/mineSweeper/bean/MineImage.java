package mineSweeper.bean;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author duke
 * @Date 2017年6月11日 上午12:45:28
 * @Description
 */
public class MineImage {

	public static ImageIcon getImageIcon(int width, int height, int mineStatus) {
		String imagePath = null;
		if (mineStatus == MineType.MINE_STATUS_OPEN_0) {
			imagePath = "src/main/java/mineSweeper/images/m0.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_1) {
			imagePath = "src/main/java/mineSweeper/images/m1.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_2) {
			imagePath = "src/main/java/mineSweeper/images/m2.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_3) {
			imagePath = "src/main/java/mineSweeper/images/m3.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_4) {
			imagePath = "src/main/java/mineSweeper/images/m4.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_5) {
			imagePath = "src/main/java/mineSweeper/images/m5.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_6) {
			imagePath = "src/main/java/mineSweeper/images/m6.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_7) {
			imagePath = "src/main/java/mineSweeper/images/m7.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_8) {
			imagePath = "src/main/java/mineSweeper/images/m8.png";
		} else if (mineStatus == MineType.MINE_STATUS_OPEN_9) {
			imagePath = "src/main/java/mineSweeper/images/m9.png";
		} else if (mineStatus == MineType.MINE_STATUS_BLANK) {
			imagePath = "src/main/java/mineSweeper/images/blank.png";
		} else if (mineStatus == MineType.MINE_STATUS_FLAG) {
			imagePath = "src/main/java/mineSweeper/images/flag.png";
		} else if (mineStatus == MineType.MINE_STATUS_UNKNOW) {
			imagePath = "src/main/java/mineSweeper/images/unknow.png";
		} else if (mineStatus == MineType.MINE_STATUS_MINE_CLICK) {
			imagePath = "src/main/java/mineSweeper/images/mine_click.png";
		} else if (mineStatus == MineType.MINE_STATUS_DEAD) {
			imagePath = "src/main/java/mineSweeper/images/facedead.png";
		} else if (mineStatus == MineType.MINE_STATUS_MILE) {
			imagePath = "src/main/java/mineSweeper/images/facesmile.png";
		} else if (mineStatus == MineType.MINE_STATUS_WAIT) {
			imagePath = "src/main/java/mineSweeper/images/facewait.png";
		} else if (mineStatus == MineType.MINE_STATUS_WIN) {
			imagePath = "src/main/java/mineSweeper/images/facewin.png";
		} else if (mineStatus == MineType.MINE_STATUS_LOGO) {
			imagePath = "src/main/java/mineSweeper/images/logo.png";
		}

		if (imagePath == null || "".equals(imagePath)) {
			return null;
		}
		ImageIcon icon = new ImageIcon(imagePath);
		// 图像缩放为适合Frame大小
		Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_FAST);
		if (img == null) {
			return null;
		}
		return new ImageIcon(img);
	}
}
