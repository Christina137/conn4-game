import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.Dimension;


public class BoardPanel extends JPanel {
    private static final int CELL_SIZE = 80;
    private static final int BORDER_SIZE = 10;

    private Board board;

    private JLabel jLabel;

    public BoardPanel(Board board) {
        this.board = board;
        addMouseListener(new MouseHandler());

        this.setLayout(null);


        jLabel = new JLabel(showplayer() + " Turn!");
        Dimension size = jLabel.getPreferredSize();
        jLabel.setBounds(600, 100, size.width * 2, size.height);
        this.add(jLabel);

        JButton resetButton = new JButton("Reset");
        resetButton.setSize(80, 50);
        resetButton.setLocation(600, 280);
        resetButton.addActionListener(e -> reset());
        this.add(resetButton);


    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int rows = board.getRows();
        int columns = board.getColumns();
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                int x = BORDER_SIZE + column * CELL_SIZE;
                int y = BORDER_SIZE + row * CELL_SIZE;
                drawCell(g, x, y, board.getCell(row, column));
            }
        }
    }
    private String showplayer(){
        if (board.getCurrentPlayer() == 1){
            return "Red";
        }
        return "Yellow";
    }
    private void drawCell(Graphics g, int x, int y, int value) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

        if (value == 1) {
            g.setColor(Color.RED);
            g.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
        } else if (value == 2) {
            g.setColor(Color.YELLOW);
            g.fillOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
        }
    }
    private class MouseHandler extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            Point p = e.getPoint();
            int column = p.x / CELL_SIZE;
            int row = board.getNextOpenRow(column);
            if (row >= 0) {
                board.setCell(row, column);
                board.PrintBoard();
                System.out.println(board.getCurrentPlayer());
                repaint();
                if (board.containsWin()){
                    option1();
                }
                if (board.isTie()){
                    option2();
                }
                jLabel.setText(showplayer() + " Turn!");
            }
        }
    }

    private void option1(){
        int var = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Player " + showplayer() + " Win!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(var == 0){
            this.reset();
        }else {
            System.exit(0);
        }
    }

    private void option2(){
        int var = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Opps! this is a tie game!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(var == 0){
            this.reset();
        }else {
            System.exit(0);
        }
    }

    private void reset() {
        board.reset();
        jLabel.setText("red Turn!");
        repaint();
    }
}

