package mineSweeper.tools;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class SizeTools {
	private static final Toolkit kit = Toolkit.getDefaultToolkit();

	//获取屏幕宽高
	public static Dimension getScreenSize() {
		return kit.getScreenSize();
	}

	//获取任务栏高度
	public static int getInsetsSize(JFrame jfFrame) {
		// 获取屏幕边界
		Insets screenInsets = kit.getScreenInsets(jfFrame.getGraphicsConfiguration());
		// 取得底部边界高度，即任务栏高度
		return screenInsets.bottom;
	}
	
	public static Image getLogo(){
		return Toolkit.getDefaultToolkit().getImage("images/logo.png"); 
	}
}
