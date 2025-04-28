import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {
    private int[][] grid;

    private boolean placed;

    public DrawPanel() {
        grid = new int[30][40];
        this.addMouseListener(this);
        randomizing();
    }

    public void randomizing(){
        for (int row = 0; row < 30; row++){
            for (int col = 0; col < 40; col++){
                int randomizer = (int) (Math.random() * 8 + 1);
                grid[row][col] = randomizer;
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 5;

        Graphics2D g2 = (Graphics2D) g;

        // Drawing squares 30 x 40
        for (int rows = 0; rows < 30; rows++) {
            int x = 5;
            for (int cols = 0; cols < 40; cols++) {
                g.drawRect(x, y, 20, 20);

                g2.setColor(Color.lightGray);
                g2.fillRect(x, y, 20, 20);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}