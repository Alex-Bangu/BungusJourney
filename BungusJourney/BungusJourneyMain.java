package BungusJourney;

import javax.swing.*;

public class BungusJourneyMain{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bungus' Journey");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        GamePanel panel = new GamePanel();
        frame.add(panel);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startGameThread();
    }
}
