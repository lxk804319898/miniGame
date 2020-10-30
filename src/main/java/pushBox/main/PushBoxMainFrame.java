package pushBox.main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PushBoxMainFrame extends JFrame {
	private JPanel jP;
	private Game game;
	private int level = 1;

	private String[] selectionValues = new String[50];
	public PushBoxMainFrame() {
		this.setTitle("我的推箱子游戏");
		this.setSize(800,600);
		int screenwidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenheight = Toolkit.getDefaultToolkit().getScreenSize().height;
		int windowwidth = this.getSize().width;
		int windowheight = this.getSize().height;
		this.setLocation((screenwidth-windowwidth)/2, (screenheight-windowheight)/2);
		this.jP = new Huaban();
		jP.setLayout(null);
		JButton startBtn = new JButton("开始游戏");
		startBtn.addActionListener(e -> {
			startGame();
		});
		JButton endBtn = new JButton("结束游戏");
		endBtn.addActionListener(e -> {
			dispose();
		});
		JButton helpBtn = new JButton("操作说明");
		helpBtn.addActionListener(e -> {
			JOptionPane.showMessageDialog(PushBoxMainFrame.this, "人物移动:方向键上下左右+\n后退一步:空格键");
		});
		startBtn.setBounds(630, 350, 100, 30);
		endBtn.setBounds(630, 400, 100, 30);
		helpBtn.setBounds(630, 450, 100, 30);
		jP.add(startBtn);
		jP.add(endBtn);
		jP.add(helpBtn);
		this.add(jP);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		for (int i = 0; i <50; i++) {
			selectionValues[i] = "第"+(i+1)+"关";
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PushBoxMainFrame();
	}

	public void startGame(){

		this.remove(jP);
		this.setLayout(null);
		JPanel jp1 = new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon  img = new ImageIcon("src/main/java/pushBox/imgs/toolImg.png");
				g.drawImage(img.getImage(), 0, 0, this);
			}
		};
		jp1.setBounds(600, 0, 200, 600);

		jp1.setLayout(null);
		jp1.setBounds(600, 0, 200, 600);
		this.add(jp1);

		JButton jb2 = new JButton("后退一步");
		jp1.add(jb2);
		jb2.setBounds(50, 200, 100, 30);
		jb2.addActionListener(e -> {
			game.Back();
		});

		JButton jb3 = new JButton("上一关");
		jp1.add(jb3);
		jb3.setBounds(50, 250, 100, 30);
		jb3.addActionListener(e -> {
			if(level>1)
				game.goBack(--level);
		});

		JButton jb4 = new JButton("下一关");
		jp1.add(jb4);
		jb4.setBounds(50, 300, 100, 30);
		jb4.addActionListener(e -> {
			if(level<50)
				game.goNext(++level);
		});

		JButton jb5 = new JButton("选关");
		jp1.add(jb5);
		jb5.setBounds(50, 350, 100, 30);
		jb5.addActionListener(e -> {
			String temp = 	(String) JOptionPane.showInputDialog(PushBoxMainFrame.this, "请选择关卡",	"关卡选择", JOptionPane.WARNING_MESSAGE, null, selectionValues,selectionValues[0]);
			for(int i=0;i<50;i++){
				if(selectionValues[i].equals(temp)){
					level = i+1;
					game.choose(level);
				}
			}
		});
		JButton jb6 = new JButton("重新开始");
		jp1.add(jb6);
		jb6.setBounds(50, 400, 100, 30);
		jb6.addActionListener(e -> {
			game.restart();
		});
		JButton jb7 = new JButton("退出游戏");
		jp1.add(jb7);
		jb7.setBounds(50, 450, 100, 30);

		jb7.addActionListener(e -> {
			dispose();
		});
		this.add(jp1);
		this.game = new Game(this.level);
		this.add(game);
		this.game.addKeyListener(new MykeyListener());
		this.game.requestFocus();
		this.repaint();

	}
	class MykeyListener extends KeyAdapter{
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
			game.add(e);
		}
	}

}
