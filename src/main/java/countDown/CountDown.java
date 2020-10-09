package countDown;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
            if (date.getTime() <= System.currentTimeMillis()) {
                jl0.setText("时间不能早于现在" + dateStr);
                throw new IllegalArgumentException("时间不能早于现在" + dateStr);
            }
            return date;
        } catch (ParseException e) {
            jl0.setText("时间格式传入错误,如yyyy-MM-dd HH:mm:ss，" + dateStr);
            throw new IllegalArgumentException("时间格式传入错误,如yyyy-MM-dd HH:mm:ss，" + dateStr);
        }
    }

    public void initTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        timer(simpleDateFormat.format(date)+" "+"18:10:00",simpleDateFormat.format(date)+" "+"11:56:00");
    }

    private void timer(String dateStr, String dateMid) {
        Date end = stringToDate(dateStr);
        Date mid = stringToDate(dateMid);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long time = (end.getTime() - 1 - System.currentTimeMillis()) / 1000;
                if (time <= 0) {
                    stopTimer();
                    jl0.setText("到达指定时间点" + dateStr);
                    return;
                }
                long hour = time / 3600;
                long minute = (time - hour * 3600) / 60;
                long seconds = time - hour * 3600 - minute * 60;

                String stringBuilder = "<html><br>距离" + dateStr + "还有<br><br>" +
                        hour + "时 " + minute + "分 " + seconds + "秒 " +
                        "</html>";
                jl0.setText(stringBuilder);
            }
        }, 0, 1, TimeUnit.SECONDS);

    }

    /**
     * 停止定时器
     */
    private void stopTimer() {
        if (scheduled != null) {
            scheduled.shutdownNow();
            scheduled = null;
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
