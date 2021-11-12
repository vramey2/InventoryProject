package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class has methods for manipulating part and product data. This class has methods for functionality of the inventory app.*/

public class Inventory {

    /**List of all parts in the Inventory app.*/

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /** This is a list for all products in the Inventory app.*/

    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

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

    public static void updatePart(int index, Part selectedPart) {

        allParts.set(index, selectedPart);
    }

    public static void updateProduct (int index, Product selectedProduct) {

        allProducts.set(index, selectedProduct);
    }

    public static ObservableList<Part> getAllParts() {

        return allParts;
    }



    //Return observable list of parts
    public static ObservableList<Product> getAllProducts() {

        return allProducts;

    }
//search for parts


    public static ObservableList<Part> lookupPart(String name) {
        ObservableList<Part> searchedPart = FXCollections.observableArrayList();
        ObservableList<Part> allParts = getAllParts();
        for (Part pt : allParts) {
            if (pt.getName().contains(name))

               searchedPart.add(pt);
               }



        return searchedPart;
    }

    //search for products
    public static ObservableList<Product> lookupProduct(String name) {
        ObservableList<Product> searchedProduct = FXCollections.observableArrayList();
        ObservableList<Product> allProducts = getAllProducts();
        for (Product pd : allProducts) {
            if (pd.getName().contains(name))
                searchedProduct.add(pd);
        }
        return searchedProduct;
    }

    public static Part lookupPart(int partID) {

        ObservableList<Part> allParts = getAllParts();
        for (Part pt : allParts) {
            if (pt.getId() == partID)
                return pt;


        }
        return null;
    }

    public static Product lookupProduct(int productID) {

        ObservableList<Product> allProducts = getAllProducts();
        for (Product pd : allProducts) {
            if (pd.getId() == productID)
                return pd;


        }
        return null;
    }




    public static boolean deletePart(Part selectedPart) {

       if (selectedPart != null ){ allParts.remove (selectedPart);

        return true;}
       else
           return false;

    }
    public static boolean deleteProduct (Product selectedProduct)  {

        allProducts.remove(selectedProduct);

        return true;
    }



    public static int generateID() {
        if (!Inventory.allParts.isEmpty()) {
            partID = Inventory.allParts.size();
        }
        partID += 1;
        return partID;

    }

//generate product id

    public static int generateProductId(){
        if (!allProducts.isEmpty()){
            productID = allProducts.size();
        }
        productID +=1;
        return productID;
    }
}
