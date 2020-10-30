package pushBox.main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Stack;

import javax.swing.*;

@SuppressWarnings("serial")
public class Game extends JPanel {
	private LoadMap lm;
	private int level;
	private int manx, many;

	private int[][] ditu;
	private int lastimg = 2;

	private Stack<backupInfo> myStack = new Stack<>();
	private Image[] imgs = {
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/0.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/1.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/2.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/3.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/4.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/5.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/6.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/7.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/8.gif"))),
			Toolkit.getDefaultToolkit().getImage(String.valueOf(new ImageIcon("src/main/java/pushBox/imgs/9.gif"))),
	};

	public Game(int level) {
		this.setBounds(0, 0, 600, 600);
		this.level = level;
		this.lm = new LoadMap(this.level);
		this.ditu = this.lm.getMap();
		this.manx = this.lm.getManx();
		this.many = this.lm.getMany();

	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				g.drawImage(imgs[ditu[i][j]], i * 30, j * 30, this);
			}
		}
		g.setColor(Color.RED);
		g.setFont(new Font("微软雅黑", Font.BOLD, 26));
		g.drawString("第"+this.level+"关", 50, 50);
	}

	public void add(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				MoveUp();
				if (isPass()) {
					this.lm = new LoadMap(++this.level);
					this.ditu = this.lm.getMap();
					this.manx = this.lm.getManx();
					this.many = this.lm.getMany();
					this.myStack.clear();

				}
				break;
			case KeyEvent.VK_DOWN:
				MoveDown();
				if (isPass()) {
					this.lm = new LoadMap(++this.level);
					this.ditu = this.lm.getMap();
					this.manx = this.lm.getManx();
					this.many = this.lm.getMany();
					this.myStack.clear();
				}
				break;
			case KeyEvent.VK_LEFT:
				MoveLeft();
				if (isPass()) {
					this.lm = new LoadMap(++this.level);
					this.ditu = this.lm.getMap();
					this.manx = this.lm.getManx();
					this.many = this.lm.getMany();
					this.myStack.clear();
				}
				break;
			case KeyEvent.VK_RIGHT:
				MoveRight();
				if (isPass()) {
					this.lm = new LoadMap(++this.level);
					this.ditu = this.lm.getMap();
					this.manx = this.lm.getManx();
					this.many = this.lm.getMany();
					this.myStack.clear();
				}
				break;
			default:
				break;
		}
	}

	private boolean isPass() {
		boolean isPass = true;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (ditu[i][j] == 3) {
					isPass = false;
				}
			}
		}
		return isPass;
	}

	private void MoveRight() {
		if (this.ditu[manx + 1][many] == 2 || this.ditu[manx + 1][many] == 4) {
			Back1();
			this.ditu[manx][many] = this.lastimg;
			this.lastimg = this.ditu[manx + 1][many];
			this.ditu[manx + 1][many] = 7;
			this.manx++;

		} else if (this.ditu[manx + 1][many] == 3 || this.ditu[manx + 1][many] == 9) {
			Back1();
			if (this.ditu[manx + 2][many] == 2) {
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx + 1][many] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx + 2][many] = 3;
				this.ditu[manx + 1][many] = 7;
				this.manx++;

			} else if (this.ditu[manx + 2][many] == 4) {
				Back1();
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx + 1][many] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx + 2][many] = 9;
				this.ditu[manx + 1][many] = 7;
				this.manx++;

			}
		}
		this.repaint();
	}


	private void MoveLeft() {
		if (this.ditu[manx - 1][many] == 2 || this.ditu[manx - 1][many] == 4) {
			Back1();
			this.ditu[manx][many] = this.lastimg;
			this.lastimg = this.ditu[manx - 1][many];
			this.ditu[manx - 1][many] = 6;
			this.manx--;

		} else if (this.ditu[manx - 1][many] == 3 || this.ditu[manx - 1][many] == 9) {
			Back1();
			if (this.ditu[manx - 2][many] == 2) {
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx - 1][many] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx - 2][many] = 3;
				this.ditu[manx - 1][many] = 6;
				this.manx--;

			} else if (this.ditu[manx - 2][many] == 4) {
				Back1();
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx - 1][many] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx - 2][many] = 9;
				this.ditu[manx - 1][many] = 6;
				this.manx--;

			}
		}
		this.repaint();
	}

	private void MoveDown() {
		if (this.ditu[manx][many + 1] == 2 || this.ditu[manx][many + 1] == 4) {
			Back1();
			this.ditu[manx][many] = this.lastimg;
			this.lastimg = this.ditu[manx][many + 1];
			this.ditu[manx][many + 1] = 5;
			this.many++;

		} else if (this.ditu[manx][many + 1] == 3 || this.ditu[manx][many + 1] == 9) {
			Back1();
			if (this.ditu[manx][many + 2] == 2) {
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx][many + 1] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx][many + 2] = 3;
				this.ditu[manx][many + 1] = 5;
				this.many++;

			} else if (this.ditu[manx][many + 2] == 4) {
				Back1();
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx][many + 1] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx][many + 2] = 9;
				this.ditu[manx][many + 1] = 5;
				this.many++;

			}
		}
		this.repaint();
	}

	private void MoveUp() {
		if (this.ditu[manx][many - 1] == 2 || this.ditu[manx][many - 1] == 4) {
			Back1();
			this.ditu[manx][many] = this.lastimg;
			this.lastimg = this.ditu[manx][many - 1];
			this.ditu[manx][many - 1] = 8;
			this.many--;

		} else if (this.ditu[manx][many - 1] == 3 || this.ditu[manx][many - 1] == 9) {
			Back1();
			if (this.ditu[manx][many - 2] == 2) {
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx][many - 1] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx][many - 2] = 3;
				this.ditu[manx][many - 1] = 8;
				this.many--;

			} else if (this.ditu[manx][many - 2] == 4) {
				Back1();
				this.ditu[manx][many] = this.lastimg;
				if (this.ditu[manx][many - 1] == 3)
					this.lastimg = 2;
				else
					this.lastimg = 4;
				this.ditu[manx][many - 2] = 9;
				this.ditu[manx][many - 1] = 8;
				this.many--;

			}
		}
		this.repaint();
	}

	// 下一关
	public void goNext(int level) {
		// TODO Auto-generated method stub
		this.level = level;
		this.lm = new LoadMap(level);
		this.ditu = this.lm.getMap();
		this.manx = this.lm.getManx();
		this.many = this.lm.getMany();
		this.repaint();
		this.requestFocus();

	}

	// 上一关
	public void goBack(int level) {
		// TODO Auto-generated method stub
		this.level = level;
		this.lm = new LoadMap(level);
		this.ditu = this.lm.getMap();
		this.manx = this.lm.getManx();
		this.many = this.lm.getMany();
		this.repaint();
		this.requestFocus();

	}

	private void Back1() {
		int[][] temp = new int[20][20];
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				temp[i][j] = this.ditu[i][j];
			}
		}
		backupInfo bi = new backupInfo(temp, manx, many, lastimg);
		this.repaint();
		this.requestFocus();
		this.myStack.push(bi);
	}
	public void Back() {
		// TODO Auto-generated method stub
		if(!this.myStack.isEmpty()){
			backupInfo temp = this.myStack.pop();
			this.ditu = temp.getBackupmap();
			this.manx = temp.getManx();
			this.many = temp.getMany();
			this.lastimg = temp.getLastimg();
			this.repaint();
		}
		this.requestFocus();
	}

	public void restart() {
		// TODO Auto-generated method stub
		this.lm = new LoadMap(this.level);
		this.ditu = this.lm.getMap();
		this.manx = this.lm.getManx();
		this.many = this.lm.getMany();
		this.lastimg = 2;
		this.repaint();
		this.requestFocus();
		this.myStack.clear();
	}

	public void choose(int level) {
		// TODO Auto-generated method stub
		this.level = level;
		this.lm = new LoadMap(level);
		this.ditu = this.lm.getMap();
		this.manx = this.lm.getManx();
		this.many = this.lm.getMany();
		this.lastimg = 2;
		this.repaint();
		this.requestFocus();
		this.myStack.clear();
	}

}
