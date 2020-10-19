package mineSweeper.bean;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * @author duke
 * @Date 2017年6月11日 上午12:45:28
 * @Description
 */
public class MineImage {
	private static final String[] IMAGE_PATHS;

	static {
		IMAGE_PATHS = new String[] {
			"src/main/java/mineSweeper/images/m0.png",
			"src/main/java/mineSweeper/images/m1.png",
			"src/main/java/mineSweeper/images/m2.png",
			"src/main/java/mineSweeper/images/m3.png",
			"src/main/java/mineSweeper/images/m4.png",
			"src/main/java/mineSweeper/images/m5.png",
			"src/main/java/mineSweeper/images/m6.png",
			"src/main/java/mineSweeper/images/m7.png",
			"src/main/java/mineSweeper/images/m8.png",
			"src/main/java/mineSweeper/images/m9.png",
			"src/main/java/mineSweeper/images/blank.png",
			"src/main/java/mineSweeper/images/flag.png",
			"src/main/java/mineSweeper/images/unknow.png",
			"src/main/java/mineSweeper/images/mine_click.png",
			"src/main/java/mineSweeper/images/facedead.png",
			"src/main/java/mineSweeper/images/facesmile.png",
			"src/main/java/mineSweeper/images/facewin.png",
			"src/main/java/mineSweeper/images/logo.png"
		};
	}

	public static ImageIcon getImageIcon(int width, int height, int mineStatus) {
		String imagePath = IMAGE_PATHS[mineStatus];

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
