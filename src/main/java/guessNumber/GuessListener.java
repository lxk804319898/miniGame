package guessNumber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuessListener implements ActionListener {

    JTextField jtf;
    JLabel jLabel;
    GuessNumber guessNumber;
    JFrame jFrame;

    public void setJtf(JTextField jtf, GuessNumber guessNumber, JFrame jFrame){
        this.jtf = jtf;
        this.guessNumber = guessNumber;
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String guess = jtf.getText();
        String answer = guessNumber.guess(guess);
        jLabel = new JLabel();
        Dimension dm1=new Dimension(280,20);
        jLabel.setPreferredSize(dm1);
        jFrame.add(jLabel);
        jLabel.setText(answer+"   "+guess);
    }
}
