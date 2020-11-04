package login;

import commonUtils.Jdbc;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import commonUtils.User;
import gameCenter.GameMenu;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    private JTextField nameText = new JTextField();
    private JPasswordField passwordText = new JPasswordField();

    private JButton login = new JButton("登录");
    private JButton register = new JButton("注册");

    private Login() {
        Font font = new Font("宋体", Font.BOLD, 12);
        super.setTitle("闽侯渔业中心登录");
        JPanel panel = new JPanel();
        panel.setLayout(null);
        JLabel nameLab = new JLabel("用户名");
        nameLab.setBounds(20, 20, 60, 30);
        nameText.setBounds(90, 20, 140, 30);
        JLabel passLab = new JLabel("密    码");
        passLab.setBounds(20, 60, 60, 30);
        passwordText.setBounds(90, 60, 140, 30);
        login.setBounds(30, 120, 90, 20);
        register.setBounds(140, 120, 90, 20);

        panel.add(nameLab);
        panel.add(nameText);
        panel.add(passLab);
        panel.add(passwordText);
        panel.add(login);
        panel.add(register);

        passwordText.setFont(font);
        register.setFont(font);

        login.addActionListener(this);
        register.addActionListener(this);

        super.add(panel);
        super.setSize(300, 250);
        super.setLocation(600,300);
        super.setVisible(true);
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == login) {
            login();
        } else if (arg0.getSource() == register) {
            register();
        }
    }

    /**
     *   登录按钮的事件处理函数
     */
    private void login() {
        Jdbc d = new Jdbc();
        String username = nameText.getText();
        String password = passwordText.getText();
        if (d.compare(username, password)) {
            User.name = username;
            super.setVisible(false);
            GameMenu gameMenu = new GameMenu();
        }
    }

    /**
     * 注册按钮触发后的事件处理函数
     */
    private void register() {
        Jdbc d = new Jdbc();
        String username = nameText.getText();
        String password = passwordText.getText();
        d.insert(username, password);
    }

}

