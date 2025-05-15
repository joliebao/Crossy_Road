import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.io.File;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private boolean placed;
    private Player p;
    private Grid grid;
    private boolean lost;

    public DrawPanel() {
        lost = false;
        p = new Player();
        grid = new Grid();
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    protected void paintComponent(Graphics g) {
        if (!lost) {
            super.paintComponent(g);

            g.setColor(Color.cyan);
            g.fillRect(p.getX() * 30, p.getY() * 30, 30, 30);
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
            lost = lose();
        } else {
            Font font = new Font("Georgia", Font.BOLD, 100);
            g.setFont(font);
            g.drawString("YOU LOST!", 315, 450);
        }
    }

    public boolean lose(){
        if (p.getY() > 29){
            return true;
        } else if (p.getX() > 39){
            return true;
        } else if (p.getX() < 0){
            return true;
        }
        return false;
    }

    public void movePlayer(String key){
        if (key.equals("a")){
            p.setX(-1);
        } else if (key.equals("w")){
            p.setY(-1);
        } else if (key.equals("d")){
            p.setX(1);
        } else if (key.equals("s")){
            p.setY(1);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String key = String.valueOf(e.getKeyChar());
        movePlayer(key);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
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