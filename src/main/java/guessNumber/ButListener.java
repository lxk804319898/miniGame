package guessNumber;

import snake.Snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//按钮监听器的父类ActionListener里面有函数可以直接检测按钮是否被点击
public class ButListener implements ActionListener {
    //定义JTextFieldb变量jt,用来保存传递过来的文本框对象
    private JTextField jt;

    public void setJt(JTextField jt){
        this.jt = jt;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //新的界面（跟旧的差不多后面有代码）
        Sucess sc = new Sucess();
        sc.showUI(jt.getText().toCharArray()[0]-48);
    }

}