package snake;

import javax.swing.JFrame;

/**
 * @author masgak
 */
public class Snake extends JFrame {
    
    public void showUI() {

        JFrame jf = new JFrame();
        jf.add(new Board());

        jf.setResizable(false);
        jf.pack();

        jf.setTitle("Snake");

        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

}
