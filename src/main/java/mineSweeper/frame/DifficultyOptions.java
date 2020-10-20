package mineSweeper.frame;

import commonUtils.consts.MenuParaConsts;
import mineSweeper.bean.MineLevel;

import javax.swing.*;
import java.awt.*;

public class DifficultyOptions extends JFrame {
    private int UIWidth = 200;
    private int UIHeight = 200;

    private CallBack callBack;

    private JTextField widthText = new JTextField(3);
    private JTextField heightText = new JTextField(3);
    private JTextField numText = new JTextField(3);

    public DifficultyOptions(CallBack callback) throws HeadlessException {
        this.callBack = callback;
        init();
    }

    private void init(){
        this.setTitle("扫雷自定义难度");
        this.setResizable(false);
        this.setBounds((MenuParaConsts.SCREEN_WIDTH - UIWidth) / 2,(MenuParaConsts.SCREEN_HEIGHT - UIHeight) / 2, UIWidth, UIHeight);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel optionPanel = new JPanel();
        JLabel widthLabel = new JLabel("宽度:");
        JLabel heightLabel = new JLabel("高度:");
        JLabel numLabel = new JLabel("雷数:");

        GridBagLayout gridBagLayout=new GridBagLayout();
        optionPanel.setLayout(gridBagLayout);
        GridBagConstraints gridBagConstraints=new GridBagConstraints();
        gridBagConstraints.fill=GridBagConstraints.NONE;

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(widthLabel, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(widthText, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(heightLabel, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(heightText, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(numLabel, gridBagConstraints);

        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=2;
        gridBagConstraints.gridwidth=1;
        gridBagConstraints.gridheight=1;
        gridBagLayout.setConstraints(numText, gridBagConstraints);

        optionPanel.add(widthLabel);
        optionPanel.add(widthText);
        optionPanel.add(heightLabel);
        optionPanel.add(heightText);
        optionPanel.add(numLabel);
        optionPanel.add(numText);

        JPanel btnPanel = new JPanel();
        JButton confirmBtn = new JButton("确定");
        confirmBtn.addActionListener(e -> {
            if(paramVaild()){
                String mineInfo =widthText.getText()+"_"+heightText.getText()+"_"+numText.getText();
                MineLevel.CUSTOMIZE.setMineInfo(mineInfo);
                callBack.confirmCreate(MineLevel.CUSTOMIZE);
                dispose();
            }
        });
        JButton cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(e -> {
            dispose();
        });
        btnPanel.add(confirmBtn);
        btnPanel.add(cancelBtn);

        getContentPane().add("Center",optionPanel);
        getContentPane().add("South",btnPanel);
        this.setVisible(true);
    }


    private boolean paramVaild(){
        String width = widthText.getText();
        String height = heightText.getText();
        String num = numText.getText();
        if(width.length()==0){
            JOptionPane.showMessageDialog(null, "宽度不能为空！");
            return false;
        }
        if(height.length()==0){
            JOptionPane.showMessageDialog(null, "高度不能为空！");
            return false;
        }
        if(num.length()==0){
            JOptionPane.showMessageDialog(null, "雷数不能为空！");
            return false;
        }
        if(Integer.parseInt(num)>=Integer.parseInt(width)*Integer.parseInt(height)){
            JOptionPane.showMessageDialog(null, "雷数不能超过格子数！");
            return false;
        }
        return true;
    }


    public interface CallBack {
        void confirmCreate(MineLevel mineLevel);
    }

    public CallBack getCallBack() {
        return callBack;
    }

    public void setCallBack(CallBack callback) {
        this.callBack = callback;
    }
}
