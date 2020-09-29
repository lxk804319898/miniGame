package mineSweeper.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author duke
 * @Date 2017年6月4日 下午9:04:29
 * @Description
 */
public class MyMouseListener implements MouseListener {
	public void mouseLeftClicked(MouseEvent paramMouseEvent) {

	}

	public void mouseRightClicked(MouseEvent paramMouseEvent) {

	}

	@Override
	public void mouseClicked(MouseEvent paramMouseEvent) {
		// 区分左右键点击
		int buttonCode = paramMouseEvent.getButton();
		if (buttonCode == MouseEvent.BUTTON1) {
			mouseLeftClicked(paramMouseEvent);
		} else if (buttonCode == MouseEvent.BUTTON3) {
			mouseRightClicked(paramMouseEvent);
		}
	}

	@Override
	public void mousePressed(MouseEvent paramMouseEvent) {

	}

	@Override
	public void mouseReleased(MouseEvent paramMouseEvent) {

	}

	@Override
	public void mouseEntered(MouseEvent paramMouseEvent) {

	}

	@Override
	public void mouseExited(MouseEvent paramMouseEvent) {

	}
}
