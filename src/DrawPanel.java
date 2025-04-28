import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements KeyListener {
    private boolean placed;
    private Player p;
    private Grid grid;
    private String key;

    public DrawPanel() {
        p = new Player();
        grid = new Grid();
        this.addKeyListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.cyan);
        g.fillRect(p.getX() * 20 ,p.getY() * 20,30,30);
        g.setColor(Color.black);

        Graphics2D g2 = (Graphics2D) g;

        int y = 0;
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

    public void movePlayer(){
        if (key.equals("a")){
            p.setX(-1);
        } else if (key.equals("w")){
            p.setY(1);
        } else if (key.equals("d")){
            p.setX(1);
        } else if (key.equals("s")){
            p.setY(-1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("pressed");
        System.out.println(e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // NOTE: THIS DOESN'T INTERACT WITH KEYS FOR SOME REASON
        // NOTHING IS PRINTING
        System.out.println(String.valueOf(e.getKeyChar()));
        key = String.valueOf(e.getKeyChar());
    }

}