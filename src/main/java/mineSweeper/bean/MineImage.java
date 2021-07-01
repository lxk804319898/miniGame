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
			"src/main/resources/mineSweeper/m0.png",
			"src/main/resources/mineSweeper/m1.png",
			"src/main/resources/mineSweeper/m2.png",
			"src/main/resources/mineSweeper/m3.png",
			"src/main/resources/mineSweeper/m4.png",
			"src/main/resources/mineSweeper/m5.png",
			"src/main/resources/mineSweeper/m6.png",
			"src/main/resources/mineSweeper/m7.png",
			"src/main/resources/mineSweeper/m8.png",
			"src/main/resources/mineSweeper/m9.png",
			"src/main/resources/mineSweeper/blank.png",
			"src/main/resources/mineSweeper/flag.png",
			"src/main/resources/mineSweeper/unknow.png",
			"src/main/resources/mineSweeper/mine_click.png",
			"src/main/resources/mineSweeper/facedead.png",
			"src/main/resources/mineSweeper/facesmile.png",
			"src/main/resources/mineSweeper/facewin.png",
			"src/main/resources/mineSweeper/logo.png"
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
