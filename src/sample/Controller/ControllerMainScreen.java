package sample.Controller;

import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.Inventory;
import sample.Model.Part;
import sample.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static sample.Model.Inventory.*;



/**This is controller class that initiates functionality of mainScreen.fxml.
 * A runtime exception occurs when no product is selected and modify product button is pushed. The runtime error is fixed
 * by checking if selection is null and this solution included in 'modifypartbuttononpushed' method.
 *
 * @author Veronika Ramey
 * */
public class ControllerMainScreen implements Initializable {

    /** Object of product that was selected by user*/
    private static Product selectedProduct;

    /** This is text field to search part by id or name*/
    public TextField queryT;

    /**This is text field to search product for id or name*/
    public TextField queryProd;

    /** Button used to exit application*/
    public Button exitButton;

    /** Button used to add a new part to inventory*/
    public Button addPartButton;

    /** Button used to modify an existing part*/
    public Button modifyButton;

    /**Button used to delete an existing part*/
    public Button deleteButton;

    /**Button used to add a new Product*/
    public Button addProductButton;

    /**Button used to delete existing product*/
    public Button deleteProductButton;

    /**Button used to modify existing product*/
    public Button modifyProductButton;

    /** This is a table view used for parts*/
    @FXML private TableView <Part> partsTableView;

    /**Column in the parts table for part ID*/
    @FXML private TableColumn <Part, Integer> partIdColumn;

    /**Column in the parts table for name of part*/
    @FXML private TableColumn <Part, String> partNameColumn;

    /**Column in the part table for stock of parts */
    @FXML private TableColumn <Part, Integer> partStockColumn;

    /**Column in the parts table for price of part*/
    @FXML private TableColumn <Part, Double>  partPriceColumn;

    /** This is a table view used for products*/
    @FXML private TableView <Product> productsTableView ;

    /** Column of the products table for product name */
    @FXML private TableColumn <Product, String> productNameColumn;

    /**Column of the products table for product id*/
    @FXML private TableColumn <Product, Integer>  productIdColumn;

    /**Column of the product table for stock of product.*/
    @FXML private TableColumn <Product, Integer> productStockColumn;

    /**Column of the product table for price of product*/
    @FXML private TableColumn <Product, Double> productPriceColumn;


    /**Method changes a scene when add button is pushed. This method changes main scene to add part scene when add Part button is pushed.
     * @param event Action on add button*/
       public void addButtonPushed  (ActionEvent event) throws IOException {
        Stage stage;
        Parent scene;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/addPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

/**Method changes a scene when add product button is pushed. This method changes main scene to add product scene when add Product button is pushed.
 * @param event Action on add product button*/
    public void addProductButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "/sample/View/addProductScreen.fxml"));

        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);

        ControllerAddProductScreen controller = loader.getController();

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

  /** Method to change the scene to modify Part scene. This method changes main scene to modify part scene when appropriate button is pushed.
   * If no part was selected the method displays alert instead of changing the scene and loading modify part controller.
   * @param event Action on modify part button*/
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {

        if (partsTableView.getSelectionModel().getSelectedItem() == null ){
           Utility.displayWarningAlert(1);
        }

        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/modifyPartScreen.fxml"));

            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);


            ControllerModifyPart controller = loader.getController();


            controller.initData(partsTableView.getSelectionModel().getSelectedItem());


            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
    }

    /** Method to change the scene to modify Product scene. This method changes main scene to modify product scene when appropriate button is pushed.
     * If part has not been selected, the method shows alert instead of lading modify product controller.
     * @param event Action on modify part button*/
    public void modifyProductButtonPushed(ActionEvent event) throws IOException {

        if (productsTableView.getSelectionModel().getSelectedItem() == null) {
            Utility.displayWarningAlert(2);
        } else {
            selectedProduct = productsTableView.getSelectionModel().getSelectedItem();


            Parent parent = FXMLLoader.load(getClass().getResource("/sample/View/modifyProductScreen.fxml"));


            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


    /**Method is for searching part. Method is used to search part by name or part of a name, and if the part not found by id. If part or parts are found they are added to filtered result,
     * if part is not found an alert is displayed.
     * @param actionEvent action event*/
    public void getSearchResultsPart(ActionEvent actionEvent) {


            String a = queryT.getText();

            ObservableList<Part> parts = lookupPart(a);


            if (parts.size() == 0) {
                try {
                    int partID = Integer.parseInt(a);
                    Part pt = lookupPart(partID);


                    if (pt != null)
                        parts.add(pt);

                } catch (NumberFormatException e) {
                    //ignore

                }
            }
            partsTableView.setItems(parts);
            if (parts.isEmpty()) {

                {
                   Utility.displayWarningAlert(3);
                }
            }
        }


    /** Method resets parts table to all parts. This method resets parts table to list all available parts if search text field is empty.
     * @param keyEvent Key is pressed in the search part text field*/
    public void clearSearch(KeyEvent keyEvent) {
        String checkText = queryT.getText();
        if (checkText.equals(""))
        {
            partsTableView.setItems(getAllParts());
        }
    }

    /**Method is for searching product. Method is used to search product by name or part of a name, and if the product not found by id. If product(s) are found they are added to filtered result,
     * if product is not found an alert is displayed.
     * @param actionEvent action event*/
    public void getSearchResultsProduct(ActionEvent actionEvent) {


        String b = queryProd.getText();

        ObservableList<Product> products = lookupProduct(b);

        if (products.size() == 0) {
            try {
                int productID = Integer.parseInt(b);
                Product pd = lookupProduct(productID);
                if (pd != null)
                    products.add(pd);
            } catch (NumberFormatException e) {
                //ignore
            }
        }
        productsTableView.setItems(products);
        if (products.isEmpty()) {
            if (products.isEmpty()) {
                {
                    Utility.displayWarningAlert(5);
                }
            }
        }
    }


    /** Method resets products table to all products. This method resets prodcuts table to list all available parts if search text field is empty.
     * @param keyEvent Key is pressed in the search part textfield*/
    public void clearSearchPr (KeyEvent keyEvent) {
        String checkText = queryProd.getText();
        if (checkText.equals(""))
        {
            productsTableView.setItems(getAllProducts());
        }
    }
    /**Closes main screen window. This method closes application, but asks for confirmation of exit first.
     * @param event Action on exit button*/
    public void exitButtonPushed (ActionEvent event) {

        if (Utility.displayAlert(4)) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
        }


/**Enables modify and delete parts buttons. The method enables buttons to modify or delete part once the use clicks on the parts table.
 *@param mouseEvent Mouse click event */
    public void userClickedOnPartsT(MouseEvent mouseEvent) {
        this.modifyButton.setDisable(false);
        this.deleteButton.setDisable(false);
    }


    /**Enables modify and delete product buttons. The method enables buttons to modify or delete product once the use clicks on the parts table.
     *@param mouseEvent Mouse click event */
    public void userClickedOnProductsT(MouseEvent mouseEvent) {
        this.deleteProductButton.setDisable(false);
        this.modifyProductButton.setDisable(false);
    }

    /**Methods gets product object to be modified. Method is to get selected product to be modified.
     * @return Product object*/
    public static Product productToUpdate()
    {
        return selectedProduct;
    }

    /** Method to delete a part. Method is used to delete part once delete button is pushed.
     * If part has not been selected, the method shows alert instead of deleting a part.
     * @param actionEvent Action on delete part button*/
    public void deleteButtonPushed(ActionEvent actionEvent) {
 if (partsTableView.getSelectionModel().getSelectedItem() == null ){
            Utility.displayWarningAlert(4);
        }

        else if   (Utility.displayAlert(2)){

            Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem()); }

      else
        {  Utility.displayWarningAlert(4);}
    }


    /** Method to delete a product. Method is used to delete product once delete button is pushed.
     * If product has not been selected, the method shows alert instead of deleting a product.
     * @param event Action on delete product button*/
    public void deleteProductButtonPushed(ActionEvent event) {
        if (productsTableView.getSelectionModel().getSelectedItem() == null) {
            Utility.displayWarningAlert(6);
        } else {

            if (Utility.displayAlert(3)) {
                {
                    //this gives us rows that were selected
                    ObservableList<Part> associatedParts = productsTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts();

                    if (associatedParts.size() >= 1) {
                       Utility.displayWarningAlert(7);
                    } else
                        Inventory.deleteProduct(productsTableView.getSelectionModel().getSelectedItem());

                }
            }
        else{
                   Utility.displayWarningAlert(6);
                }

            }
        }

        /** Method is to initialize controller. Method initializes controller and populates parts and products table views, also disables modify and delete buttons.
         * @param url Specifies location to resolve the root object's relative paths.
         * @param resourceBundle  Localization resources for the root object, null if  there no localization of the root object.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        partsTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory (new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory (new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory (new PropertyValueFactory<>("price"));


        productsTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory (new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productStockColumn.setCellValueFactory (new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory (new PropertyValueFactory<>("price"));

        this.modifyButton.setDisable (true);
        this.deleteButton.setDisable(true);
        this.deleteProductButton.setDisable(true);
        this.modifyProductButton.setDisable(true);

    }


}
