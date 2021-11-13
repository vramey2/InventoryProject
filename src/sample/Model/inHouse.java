package sample.Model;

/** This class extends Part class. Class for in-house parts in the inventory, which extends a Part class.
 * @author Veronika Ramey
 * */
public class inHouse extends Part{

/** an ID for machine number.*/
    private int machineID;

    /**Method creates in-house part instance. This method is a constructor for in-house part with appropriate fields.
     * @param id Id number for the in-house part
     * @param name Name of the in-house part
     * @param price Price of the in-house part
     * @param stock Quantity of available in-house parts
     * @param min Minimum number of in-house parts for the inventory stock
     * @param max Maximum number of the in-house parts that can be in the inventory stock
     * @param machineID Id number of the machine for the in-house part */
    public inHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        super(id, name, price, stock, min, max);
        this.machineID = machineID;
    }

    /**Getter for the machine ID. This is getter for the in-house part machine ID number.
     * @return   Returns ID number of the machine. */
    public int getMachineID() {
        return machineID;
    }

    /**Setter for the machine ID number. This is a setter method for the in-house machine ID number.
     * @param machineID Machine ID number for the in-house part.*/
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }
}
