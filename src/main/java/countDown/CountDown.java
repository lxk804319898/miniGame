package countDown;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时
 * @author masgak
 * @todo 中午倒计时需求
 */
public class CountDown {

    private JLabel jl0;
    private JLabel jm0;

    private ScheduledThreadPoolExecutor scheduled;
    
    private Date stringToDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            jl0.setText("时间格式传入错误,如yyyy-MM-dd HH:mm:ss，" + dateStr);
            throw new IllegalArgumentException("时间格式传入错误,如yyyy-MM-dd HH:mm:ss，" + dateStr);
        }
    }

    public void initTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        timer(simpleDateFormat.format(date)+" "+"15:38:00",simpleDateFormat.format(date)+" "+"11:56:00" , simpleDateFormat.format(date)+" "+"14:00:00");
    }

    private void timer(String dateStr ,String dateMid ,String dateMidEnd) {
        Date end = stringToDate(dateStr);
        Date mid = stringToDate(dateMid);
        Date midEnd = stringToDate(dateMidEnd);
        String endWarn = "已到达下班时间！";
        String midWarn = "已到达午休时间！";
        String midEndWarn = "午休时间已过！";
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                StringBuffer showText = new StringBuffer("<html><br>");
                // 判断午休
                long midTime = (mid.getTime() - 1 - System.currentTimeMillis()) / 1000;
                long midEndTime = (midEnd.getTime() - 1 - System.currentTimeMillis()) / 1000;
                if(midTime <= 0 && midEndTime <= 0) {
                    showText.append(midEndWarn + "<br><br>");
                }
                if(midTime <= 0 && midEndTime > 0){
                    long midEndHour = midEndTime / 3600;
                    long midEndMinute = (midEndTime - midEndHour * 3600) / 60;
                    long midEndSeconds = midEndTime - midEndHour * 3600 - midEndMinute * 60;
                    showText.append("距离 午休结束"+ dateMidEnd + "还有<br>" +
                            midEndHour + "时 " + midEndMinute + "分 " + midEndSeconds + "秒 " +
                            "<br><br>");
                }
                if(midTime > 0){
                    long midHour = midTime / 3600;
                    long midMinute = (midTime - midHour * 3600) / 60;
                    long midSeconds = midTime - midHour * 3600 - midMinute * 60;
                    showText.append("距离 午休开始"+ dateMid + "还有<br>" +
                            midHour + "时 " + midMinute + "分 " + midSeconds + "秒 " +
                            "<br><br>");
                }

                // 判断下班
                long time = (end.getTime() - 1 - System.currentTimeMillis()) / 1000;
                if (time <= 0) {
                    stopTimer(true);
                    //showText.append(endWarn + "<br>");
                    //showText.append("<img src=\"src/resources/CountDownResources/apple.png\" />");
                }else{
                    long hour = time / 3600;
                    long minute = (time - hour * 3600) / 60;
                    long seconds = time - hour * 3600 - minute * 60;
                    showText.append("距离 下班"+ dateStr + "还有<br>" +
                            hour + "时 " + minute + "分 " + seconds + "秒 " +
                            "</html>");
                }


//                String stringBuilder = "<html><br>距离" + dateStr + "还有<br><br>" +
//                        hour + "时 " + minute + "分 " + seconds + "秒 " +
//                        "</html>";

                jl0.setText(showText.toString());
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    /**
     * 停止定时器
     */
    private void stopTimer(boolean isFinished) {
        if(isFinished) {
            ImageIcon icon = new ImageIcon("src/resources/CountDownResources/paolu.png");
            icon.setImage(icon.getImage().getScaledInstance(180, 150, Image.SCALE_DEFAULT));
            jl0.setIcon(icon);
            if (scheduled != null) {
                scheduled.shutdownNow();
                scheduled = null;
            }
        }
    }
    
    public CountDown() {
        scheduled = new ScheduledThreadPoolExecutor(1);
        init();
    }

    private void init() {
        JFrame jFrame = new JFrame("倒计时");
        jl0 = new JLabel();

        JPanel jp = new JPanel();
        jp.add(jl0);

        jFrame.add(jp);

        jFrame.setVisible(true);
        jFrame.setLocation(300, 400);
        jFrame.setSize(330, 200);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
