package chatRoom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.*;
import java.io.*;

/**
 * @author masgak
 */
public class ChatRooms {

    private JFrame frame;
    private JTextField txtF1;
    private JTextField txtF2;
    private JTextArea txtare1;
    private JTextArea txtare2;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new ChatRooms();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ChatRooms() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("IP");
        lblNewLabel.setBounds(10, 10, 54, 15);
        frame.getContentPane().add(lblNewLabel);

        txtF1 = new JTextField();
        txtF1.setText("172.18.150.192");
        txtF1.setBounds(55, 7, 90, 21);
        frame.getContentPane().add(txtF1);
        txtF1.setColumns(10);

        JLabel jlabel = new JLabel("端口");
        jlabel.setBounds(177, 10, 54, 15);
        frame.getContentPane().add(jlabel);

        txtF2 = new JTextField();
        txtF2.setText("8877");
        txtF2.setBounds(215, 7, 66, 21);
        frame.getContentPane().add(txtF2);
        txtF2.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 36, 414, 274);
        frame.getContentPane().add(scrollPane);

        txtare1 = new JTextArea();
        scrollPane.setViewportView(txtare1);

        JScrollPane jscrollpane = new JScrollPane();
        jscrollpane.setBounds(10, 332, 414, 83);
        frame.getContentPane().add(jscrollpane);

        txtare2 = new JTextArea();
        jscrollpane.setViewportView(txtare2);

        JButton btnNewButton = new JButton("关闭");
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton.setBounds(153, 428, 93, 23);
        frame.getContentPane().add(btnNewButton);

        JButton jbutton = new JButton("发送");
        jbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String host=txtF1.getText();
                int port=Integer.parseInt(txtF2.getText());
                try {
                    Socket client=new Socket(host,port);
                    Writer wt=new OutputStreamWriter(client.getOutputStream());
                    wt.write(txtare2.getText());
                    wt.flush();
                    wt.close();
                    client.close();
                    txtare2.setText(null);
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                    System.out.println("接口不可用");
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("IO错误");
                }
            }
        });
        jbutton.setBounds(294, 428, 93, 23);
        frame.getContentPane().add(jbutton);

        JButton jbutton2 = new JButton("启动服务");
        jbutton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbutton2.setEnabled(false);
                jbutton2.setText("服务已启动");
                ServerThread st=new ServerThread(txtare1,txtF2);
                st.start();
            }
        });
        jbutton2.setBounds(331, 6, 93, 23);
        frame.getContentPane().add(jbutton2);
        frame.setVisible(true);
    }
}
