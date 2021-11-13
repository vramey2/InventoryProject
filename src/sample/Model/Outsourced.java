package sample.Model;


/** This class extends Part class. Class for outsourced parts in the inventory, which extends a Part class.
        * @author Veronika Ramey
        **/
public class Outsourced extends Part {

/** Name of company-manufacturer of the outsourced part*/
    private String companyName;

 /**Method creates outsourced part instance. This method is a constructor for outsourced part with appropriate fields.
     * @param id Id number for the outsourced part
     * @param name Name of the outsourced part
     * @param price Price of the outsourced part
     * @param stock Quantity of available outsourced parts
     * @param min Minimum number of outsourced parts for the inventory stock
     * @param max Maximum number of the outsourced parts that can be in the inventory stock
    * @param companyName Name of company-manufacturer of the outsourced part. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**Getter method for company name. This method is a getter for the company name.
     * @return Name of company-manufacturer of the outsourced part*/
    public String getCompanyName() {
        return companyName;
    }

    /**Setter method for the company name. This method is a setter for the company name.
     * @param companyName Name of the company-manufacturer for the outsourced part*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
