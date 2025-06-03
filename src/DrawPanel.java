import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private boolean startGame;
    private Player p = new Player();;
    private Grid grid;
    private boolean lost;
    private int score;
    private static String key;
    private long time;
    private boolean once = true;

    public DrawPanel() {
        grid = new Grid();
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.setFocusable(true);
        score = 0;
    }

    protected void paintComponent(Graphics g) {
        if (!lost) {
//            lost = !(grid.isSafe(p.getX(), p.getY(), key, startGame));
            super.paintComponent(g);

            if (startGame && once){ // need to make this happen once
                time = System.currentTimeMillis();
                once = false;
            }

            if (System.currentTimeMillis() - time == 1000) {
                time = System.currentTimeMillis();
                grid.updateGrid();
            }

            for (int r = 0; r < grid.getRLength(); r++){
                for (int c = 0; c < grid.getCLength(); c++){
                    if (grid.getNumAssociation(r,c) == 0){
                        g.setColor(Color.green);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 1) {
                        g.setColor(Color.gray);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 2) {
                        g.setColor(Color.blue);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 3) {
                        g.setColor(Color.lightGray);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 4) {
                        g.setColor(Color.getHSBColor(12, 100, 28));
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 5) {
                        g.setColor(Color.red);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 6) {
                        g.setColor(Color.magenta);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 7) {
                        g.setColor(Color.white);
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 8) {
                        g.setColor(Color.getHSBColor(8, 88, 18));
                        g.fillRect(c * 30, r * 30, 30, 30);
                    } else if (grid.getNumAssociation(r,c) == 9) {
                        g.setColor(Color.cyan);
                        g.fillRect(p.getX() * 30, p.getY() * 30, 30, 30);
                    }
                }
            }
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
        startGame = true;
        key = String.valueOf(e.getKeyChar());
        movePlayer(key);

        if (key.equals("w")){
            score++;
        } else if (key.equals("s")){
            score--;
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