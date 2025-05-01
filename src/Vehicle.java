public class Vehicle extends Item{
    private int size;
    private int speed;

    public Vehicle (int num, int length, int x, int y){
        super(num, x, y);
        size = length;

        if (numAssociation == 5 || numAssociation == 6){
            speed = 4;
        } else if (numAssociation == 7){
            speed = 15;
        } else if (numAssociation == 8){
            speed = 2;
        }
    }

    public int getSize(){
        return size;
    }

    public int end(){
        return x+size;
    }

    public int getSpeed(){
        return speed;
    }
}
