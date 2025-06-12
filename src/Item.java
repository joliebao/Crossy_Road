import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Item {
    protected int numAssociation;
    protected int start;
    protected int y;
    protected int x;
    protected BufferedImage tileImg;
    protected BufferedImage resizedImg;

    public Item (int num, int x, int y) {
        this.x = x;
        start = x;
        this.y = y;
        numAssociation = num;

        if (numAssociation == 0) {
            tileImg = loadImage("Sprites/Grass.png");
        } else if (numAssociation == 1) {
            tileImg = loadImage("Sprites/Road.png");
        } else if (numAssociation == 2) {
            tileImg = loadImage("Sprites/Water.png");
        } else if (numAssociation == 3) {
            tileImg = loadImage("Sprites/Railroad.png");
        } else if (numAssociation == 4) {
            tileImg = loadImage("Sprites/Tree.png");
        } else if (numAssociation == 5) {
            tileImg = loadImage("Sprites/CarFront.png");
            // do back too
        } else if (numAssociation == 6) {
            tileImg = loadImage("Sprites/TruckFront.png");
            // do middle and back
        } else if (numAssociation == 7) {
            tileImg = loadImage("Sprites/Train.png");
        } else if (numAssociation == 8) {
            tileImg = loadImage("Sprites/Plank.png");
        } else if (numAssociation == 9) {
            tileImg = loadImage("Sprites/Player.png");
        }

        BufferedImage resizedImg = scaleImage(tileImg, 30, 30);
//         Find way to resize this
//        tileImg = (BufferedImage) tileImg.getScaledInstance(30, 30, BufferedImage.SCALE_REPLICATE);
    }

    public BufferedImage getTileImg() {
        return tileImg;
    }

    public int getStart(){
        return start;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }

    public int getNumAssociation(){
        return numAssociation;
    }

    public void setNumAssociation(int num){
        numAssociation = num;
    }

    public static BufferedImage loadImage(String fileName) {
        try {
            BufferedImage image;
            image = ImageIO.read(new File(fileName));
            return image;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return numAssociation + "";
    }
}