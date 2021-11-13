package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class has methods for manipulating part and product data. This class has methods for functionality of the inventory app.
 * @author Veronika Ramey
 * */

public class Inventory {

    /**List of all parts in the Inventory app.*/

    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** This is a list for all products in the Inventory app.*/

    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /** Id for a part.*/
    public static int partID ;

    /**ID for a product.*/
    private static int productID;



    /**This is method to add a new part. Method adds new Part to the end of array list for inventory.
     * @param newPart This is newly added Part.*/
     public static void addPart(Part newPart) {
        allParts.add(newPart);
     }

    /**This method is to add new product. Method adds a new Product to the end of array list for inventory.
     * @param product This is newly added Product.*/
     public static void addProduct(Product product) {

         allProducts.add(product);
    }

    /**Method is to search for Part by ID. Method is searching for part using its id and returns either found part or null if not found.
     * @param partID ID number of the part to look up
     * @return  Part if found or null if part is not found*/
    public static Part lookupPart(int partID) {

        ObservableList<Part> allParts = getAllParts();
        for (Part pt : allParts) {
            if (pt.getId() == partID)
                return pt;
        }
        return null;
    }

/**Method is to search for Product by its ID. Method is searching for product using its id and returns either found product or null if not found.
 * @param productID Id of product to be searched.
 * @return Product is returned if it has a matching id or null if nothing is found.
 */
        public static Product lookupProduct(int productID) {

        ObservableList<Product> allProducts = getAllProducts();
        for (Product pd : allProducts) {
            if (pd.getId() == productID)
                return pd;
        }
        return null;
    }


    /**This method is to search for part. Method  searches by name or part of name.
     * @param name Name of part the user is looking for.
     * @return Returns a list of parts that were found.
     *
     * */
    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> searchedPart = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();
        for (Part pt : allParts) {
            if (pt.getName().contains(name))

                searchedPart.add(pt);
        }
        return searchedPart;
    }

    /**This method is to search for product. Method searches by product name or part of the name.
     * @param name Name of the product to be searched.
     * @return Returns a list of products that were found. */
    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> searchedProduct = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts();
        for (Product pd : allProducts) {
            if (pd.getName().contains(name))
                searchedProduct.add(pd);
        }
        return searchedProduct;
    }

    /**This is method to update existing Part. Method modifies data in the Part based on user's input.
     *
     * @param index Index of the part in the array
     * @param selectedPart Part the user selected
     */
    public static void updatePart(int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }


    /**This method is to update existing Product. Method modifies data for the Product and modifies associated part list.
     * @param index Index of the product to be searched.
     * @param selectedProduct Product selected by the user for searching */
    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }

    /**Method is for deleting Part. Method deletes part selected by the user.
     * @param selectedPart Part selected by the user for deletion.
     * @return boolean returns true or false depending if the part was found.*/
    public static boolean deletePart(Part selectedPart) {

        if (selectedPart != null ){ allParts.remove (selectedPart);

            return true;}
        else
            return false;

    }

    /**Method is for deleting Product. Method deletes product selected by the user.
     * @param selectedProduct Product selected by the user for deletion.
     * @return boolean returns true or false depending if the product was found.*/
    public static boolean deleteProduct (Product selectedProduct)  {

        allProducts.remove(selectedProduct);

        return true;
    }

    /**Method returns all parts. Method is used to return a list of parts in the inventory.
     * @return List of all parts. */

    public static ObservableList<Part> getAllParts() {

        return allParts;
    }


/**Method returns all products. Method is used to return a list of products in the inventory.
 * @return List of all products. */
    //Return observable list of parts
    public static ObservableList<Product> getAllProducts() {

        return allProducts;

    }

/**Method generates unique part Ids. Method is used to generate unique IDs for parts.
 * @return   generated ID for new part*/
    public static int generateID() {
        if (!Inventory.allParts.isEmpty()) {
            partID = Inventory.allParts.size();
        }
        partID += 1;
        return partID;

    }

    /**Method generates unique product Ids. Method is used to generate unique IDs for products.
     * @return   generated ID for new product*/
    public static int generateProductId(){
        if (!allProducts.isEmpty()){
            productID = allProducts.size();
        }
        productID +=1;
        return productID;
    }
}
