public class Vehicle {
    private int numAssociation;
    private int size;
    private int x;
    private int y;
    private int speed;

    public Vehicle (int num, int length, int x, int y){
        numAssociation = num;
        size = length;
        this.x = x;
        this.y = y;

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

    public int start(){
        return x;
    }

    public int end(){
        return x+size;
    }

    public int place(){
        return y;
    }

    public int getNumAssociation(){
        return numAssociation;
    }

    public int getSpeed(){
        return speed;
    }
}
