package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**  This class is for Product object of the inventory management system.
 * @author Veronika Ramey
        */
public class Product {
   /**List of parts associated with the Product*/
    private final ObservableList <Part> associatedParts = FXCollections.observableArrayList();

/**ID of the product*/
    private int id;

    /** Name of the product*/
    private String name;

    /**Price of the product*/
    private double price;

    /**Quantity of the product*/
    private int stock;

    /**Minimum number of the product that is allowed in the stock*/
    private int min;

    /**Maximum number of the product that the inventory can take*/
    private int max;

/**Method creates Product instance. This method is a constructor for product with appropriate fields.
 * @param id Id number for the product
 * @param name  Name of the product
 * @param price Price of the product
 * @param stock  Quantity of the product
 * @param min   Minimum number of product to be in stock
 * @param max Maximum quantity of the product that could be in the stock*/
    public Product (int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


    /**Setter method for ID. Method is a setter for ID number of the product
     * @param id Id of product*/
    public void setId(int id) {
        this.id = id;
    }

    /**Setter method for Name. Method is a setter for name of the product
     * @param name NName of product*/
    public void setName(String name) {
        this.name = name;
    }


    /**Setter method for price. Method is a setter for price of the product
     * @param price Price of product*/

    public void setPrice(double price) {
        this.price = price;
    }

    /**Setter method for stock. Method is a setter for quantity of the product
     * @param stock Quantity of product*/

    public void setStock(int stock) {
        this.stock = stock;
    }

    /**Setter method for minimum quantity of the product. Method is a setter for minimum quantity that must be available in the stock
     * @param min Minimum quantity of product*/
    public void setMin(int min) {
        this.min = min;
    }

    /**Setter method for maximum quantity of the product. Method is a setter for maximum quantity that can be stocked
     * @param max Max quantity of product*/
    public void setMax(int max) {
        this.max = max;
    }


    /**Getter method for ID. Method is a getter for Id number of the product
     * @return Id number of the product*/
    public int getId() {
        return id;
    }

    /**Getter for product name. Method is a getter for product name
     * @return Product name */
    public String getName() {
        return name;
    }

    /**Getter for price. Method is a getter for product price
     * @return Product price */
    public double getPrice() {
        return price;
    }

/**Getter for stock. Method is a getter for available quantity of product
 * @return Stock of product*/
    public int getStock() {
        return stock;
    }

/**Getter for minimum stock. Method is a getter for minimum required number of product stock
 * @return Minimum quantity*/
    public int getMin() {
        return min;
    }

/**Getter for maximum stock. Method is a getter for maximum product quantity that can be in stock
 * @return Maximum quantity*/
    public int getMax() {
        return max;
    }


    /**Method adds associated parts to the product. Method adds items to the end of the array list of product
     * @param part Part to be added to the list*/
    public void addAssociatedPart(Part part){

        associatedParts.add(part);

    }


    /**Method gets all parts associated to the product. Method is used to get a list of associated parts
     * @return Observable list of associated parts*/

    public ObservableList <Part> getAllAssociatedParts(){

        return associatedParts;

    }



}
