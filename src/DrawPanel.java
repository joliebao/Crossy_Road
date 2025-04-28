import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {
    private boolean placed;

    public DrawPanel() {
        Player p = new Player();
        Grid grid = new Grid();
        this.addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = 0;

        Graphics2D g2 = (Graphics2D) g;

        // Drawing squares 30 x 40
        for (int rows = 0; rows < 30; rows++) {
            int x = 0;
            for (int cols = 0; cols < 40; cols++) {
                g.drawRect(x, y, 30, 30);
                x += 30;
            }
            y += 30;
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