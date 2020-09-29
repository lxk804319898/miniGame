package chatRoom;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import javax.swing.*;

import javax.swing.JTextArea;
/**
 * @author masgak
 */
public class ServerThread extends Thread {

    JTextArea txtare;
    JTextField txtF;
    public ServerThread(JTextArea txtare1,JTextField txtFile){
        this.txtare=txtare1;
        this.txtF=txtFile;
    }
    @Override
    public void run() {
        int port = Integer.parseInt(txtF.getText());
        ServerSocket server;
        Socket socket;
        String line;
        BufferedReader br;
        //先留着，大师说升级了再改
        List<Socket> sockets = new ArrayList<Socket>();
        ExecutorService executorService;

        try {
            server = new ServerSocket(port);
            do{
                socket = server.accept();
                br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                line = br.readLine();
                txtare.append("大师指导："+line+"\n");
                txtare.paintImmediately(txtare.getBounds());
                if("exit".equals(line)){
                    server.close();
                    System.out.println("服务已关闭！");
                    break;
                }
            }while(true);
            socket.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
