package RememberCard;

import javax.swing.*;

public class StartRCCardGame {

    public static void start() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                RememberCard remCard = new RememberCard();
                remCard.setSize(500, 600);
                remCard.setLocationRelativeTo(null);
                remCard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                remCard.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        StartRCCardGame.start();
    }
}
