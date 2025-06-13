import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.sql.SQLOutput;

public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private boolean startGame;
    private Player p = new Player();;
    private Grid grid;
    private boolean lost;
    private int score = 0;
    private static String key;
    private long time;
    private boolean once = true;
    private boolean pressed;

    public DrawPanel() {
        grid = new Grid();
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    protected void paintComponent(Graphics g) {
        if (!lost) {
            lost = grid.isLost(pressed, startGame);
            pressed = false;
            super.paintComponent(g);

            if (startGame && once){
                time = System.currentTimeMillis();
                once = false;
            }

            if (System.currentTimeMillis() - time == 300) {
                time = System.currentTimeMillis();
                grid.updateGrid();
            }

            Graphics2D g2 = (Graphics2D) g;

            for (int r = 0; r < grid.getRLength(); r++){
                for (int c = 0; c < grid.getCLength(); c++){
                    Item itm = grid.getItem(r, c);
                    g.drawImage(itm.getTileImg(), c * 30, r * 30, 51, 51, null);
                }
            }
            Font font = new Font("Trebuchet MS", Font.PLAIN, 30);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("Score: " + score, 20, 40);
        } else {
            Font font = new Font("Georgia", Font.BOLD, 100);
            g.setFont(font);
            g.setColor(Color.black);
            g.drawString("YOU LOST!", 315, 450);
        }
    }

    public void movePlayer(String key){
        if (!lost) {
            if (key.equals("a")) {
                grid.changePlayerLoc(true, -1, p.getX(), p.getY());
            } else if (key.equals("w")) {
                grid.changePlayerLoc(false, -1, p.getX(), p.getY());
            } else if (key.equals("d")) {
                grid.changePlayerLoc(true, 1, p.getX(), p.getY());
            } else if (key.equals("s")) {
                grid.changePlayerLoc(false, 1, p.getX(), p.getY());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        pressed = true;

        startGame = true;
        key = String.valueOf(e.getKeyChar());
        movePlayer(key);

        if (key.equals("w")){
            score++;
        }
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