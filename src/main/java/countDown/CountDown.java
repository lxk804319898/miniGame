package countDown;

import javax.swing.*;
import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 倒计时
 * @author masgak
 */
public class CountDown {

    private JLabel jl0;

    private ScheduledThreadPoolExecutor scheduled;

    public void initTime(){
        LocalDate today = LocalDate.now();
        LocalTime offWorkTime = LocalTime.of(18, 10, 0);
        LocalTime midBreakStart = LocalTime.of(11, 56, 0);
        LocalTime midBreakEnd = LocalTime.of(14, 0, 0);

        timer(
            LocalDateTime.of(today, offWorkTime),
            LocalDateTime.of(today, midBreakStart),
            LocalDateTime.of(today, midBreakEnd)
        );
    }

    private void timer(LocalDateTime offWorkTime, LocalDateTime midBreakStartTime, LocalDateTime midBreakEndTime) {
        String midEndWarn = "午休时间已过！";
        scheduled.scheduleAtFixedRate(() -> {
            LocalDateTime now = LocalDateTime.now();
            StringBuilder showText = new StringBuilder("<html><br>");

            if (now.isBefore(midBreakStartTime)) {
                Duration remaining = Duration.ofSeconds(now.until(midBreakStartTime, ChronoUnit.SECONDS));
                showText.append("距离午休开始（").append(midBreakStartTime).append("）还有<br>")
                    .append(formatRemaining(remaining))
                    .append("<br><br>");
            } else if (now.isBefore(midBreakEndTime)) {
                Duration remaining = Duration.ofSeconds(now.until(midBreakEndTime, ChronoUnit.SECONDS));
                showText.append("距离午休结束（").append(midBreakEndTime).append("）还有<br>")
                    .append(formatRemaining(remaining))
                    .append("<br><br>");
            } else if (now.isBefore(offWorkTime)) {
                showText.append(midEndWarn).append("<br><br>");
                Duration remaining = Duration.ofSeconds(now.until(offWorkTime, ChronoUnit.SECONDS));
                showText.append("距离下班（").append(offWorkTime).append("）还有<br>")
                    .append(formatRemaining(remaining))
                    .append("<br><br>");
            } else {
                stopTimer(true);
            }
            System.out.println(showText.toString());
            jl0.setText(showText.toString());
        }, 0, 1, TimeUnit.SECONDS);
    }

    private String formatRemaining(Duration remaining) {
        long remainingTime = remaining.getSeconds();
        long remainingHours = remainingTime / 3600;
        long remainingMinutes = (remainingTime % 3600) / 60;
        long remainingSeconds = (remainingTime % 3600) % 60;
        return String.format("%d时%d分%d秒", remainingHours, remainingMinutes, remainingSeconds);
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
