import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Connect 4");
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton human = new JButton("1 vs 1");
        human.setSize(80, 50);
        human.setLocation(300,250);
        human.addActionListener(e -> {
            frame.setVisible(false);
            JFrame frame1 = new JFrame("Connect 4");
            frame1.setSize(800, 600);
            frame1.setLayout(new BorderLayout());
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Board board = new Board();
            JPanel panel = new BoardPanel(board);
            frame1.add(panel);
            frame1.setLocationRelativeTo(null);
            frame1.setVisible(true);
        });
        JButton humanAi = new JButton("1 vs Ai");
        humanAi.setSize(80, 50);
        humanAi.setLocation(400,250);
        humanAi.addActionListener(e -> {
            frame.setVisible(false);
            JFrame frame2 = new JFrame("Connect 4");
            frame2.setSize(800, 600);
            frame2.setLayout(new BorderLayout());
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Board board = new Board();
            JPanel panel = new BoardPanelAi(board);
            frame2.add(panel);
            frame2.setLocationRelativeTo(null);
            frame2.setVisible(true);
        });
        JPanel jPanel = new JPanel();



        jPanel.setLayout(null);
        ImageIcon icon = new ImageIcon("/imgs/start.jpg");
        JLabel jLabel = new JLabel();
        jLabel.setIcon(icon);
        jPanel.add(jLabel);
        jPanel.add(human);
        jPanel.add(humanAi);
        frame.add(jPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}


