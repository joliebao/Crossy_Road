public class Vehicle extends Item{
    private int size;
    private int speed;

    public Vehicle (int num, int start, int y) {
        super(num, start, y);

        if (numAssociation == 5) { // car
            speed = 4;
            size = 2 + (int) (Math.random() * 1);
        } else if (numAssociation == 6){ // truck
            speed = 4;
            size = 3 + (int) (Math.random() * 2);
        } else if (numAssociation == 7){ // train
            speed = 15;
            size = 60;
        } else if (numAssociation == 8){ // log
            speed = 2;
            size = 5 + (int) (Math.random() * 2);
        }
    }

    // To use the start number: All values of the numAssociation will have the same start number.
    // The start number will be used for comparing the entire segment of the vehicle
    // For example:
    // 00022000
    // Start would be 3; compare 4 to 3 and that is one vehicle
    // idk if it will work (!)
    public int getSize(){
        return size;
    }

    public int getSpeed(){
        return speed;
    }
}
