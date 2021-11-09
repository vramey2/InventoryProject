package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


import java.util.Objects;

public class Inventory {  private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    public static int partID;
    private static int productID;

    //Add items to the end of array list
    public static void addPart(Part newPart) {

        allParts.add(newPart);

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

    public static void addProduct(Product product) {

        allProducts.add(product);

    }

    //Return observable list of parts
    public static ObservableList<Product> getAllProducts() {

        return allProducts;

    }
//search for parts

    /**
     * public static Part lookupPart (int partID) {
     * for (Part part : allParts) {
     * }
     * <p>
     * <p>
     * }
     */
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

    public static int generateID() {
        partID += 1;
        return partID;
    }

//generate product id

    public static int generateProductId(){
        productID +=1;
        return productID;
    }
    public static boolean deletePart(Part selectedPart) {

        allParts.remove (selectedPart);

        return true;

    }
    public static boolean deleteProduct (Product selectedProduct)  {

        allProducts.remove(selectedProduct);

        return true;
    }

}
