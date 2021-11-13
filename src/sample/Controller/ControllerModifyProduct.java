package sample.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import sample.Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import static sample.Model.Inventory.*;
import sample.Model.Product;


/**This is controller class that initiates functionality of modifyProductScreen.fxml
 *
 * @author Veronika Ramey
 * */

public class ControllerModifyProduct implements Initializable {

    /**This is text field for prodcut ID*/
    public TextField idPrModTextField;

    /**Text field for product name*/
    public TextField namePrModTextField;

    /**Text field for product inventory*/
    public TextField invPrModTextField;

    /**Text field for product price*/
    public TextField pricePrModTextField;

    /**Text field for maximum quantity of product*/
    public TextField maxPrModTextField;

    /**Text field for minimum quantity of product*/
    public TextField minPrTextFieldMod;

    /**Selected product*/
    public Product selectedProduct;

    /**Text field for searching part*/
    public TextField querySearchPartMod;

    /**Table view for all available parts*/
    public TableView<Part> partsTableViewModTwo;

    /**Id column for all parts table*/
    public TableColumn<Part, Integer> partIdColumnMod;

    /**Name column for all parts table*/
    public TableColumn<Part, String> partNameColumnMod;

    /**Stock column for all parts table*/
    public TableColumn<Part, Integer> partStockColumnMod;

    /**Price column for all parts table*/
    public TableColumn<Part, Double> partPriceColumnMod;

    /**Table view for associated parts*/
    public TableView<Part> associatedPartsModTable;

    /**Id column for associated parts table*/
    public TableColumn<Part, Integer> assocPartIdColumnMod;

    /**Name column for associated parts table*/
    public TableColumn<Part, String> assocNameColumnMod;

    /**Stock column for associated parts table*/
    public TableColumn<Part, Integer> assocStockColumnMod;

    /**Price column for associated parts table*/
    public TableColumn<Part, Double> assocPriceColumnMod;

    /**Button to remove associated part*/
    public Button removeAssocPartButton;

    /**Button to go back to main screen*/
    public Button cancelButtonMod;

    /**Button to save updated product*/
    public Button savePrButtonMod;

    /**Observable list for associated parts*/
    public ObservableList<Part> associatedParts;

    /**Button to add part to associated list*/
    public Button addButton;

    /**Stage to be displayed*/
    Stage stage;

    /**Scene to be displayed*/
    Parent scene;


   /**Method takes back to the main screen. Method first asks for confirmation, then takes back to main screen.
    * @param event Acton on cancel button pushed*/
    public void cancelAssocPartButtonPushed(ActionEvent event) throws IOException {

        if (Utility.displayAlert(1)) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }


 /**Searches for part. Method searching for part first by name, than by id, displays alert if part is not found.
  * @param event event on search text field*/
    public void getSearchedPartMod(ActionEvent event) {

        String c = querySearchPartMod.getText();

        ObservableList<Part> parts;
        parts = lookupPart(c);

        if (parts.size() == 0) {
            try {
                int partID = Integer.parseInt(c);
                Part pt = lookupPart(partID);


                if (pt != null)
                    parts.add(pt);

            } catch (NumberFormatException e) {
                //ignore
            }
        }
        partsTableViewModTwo.setItems(parts);


        if (parts.isEmpty()){
            {
                Utility.displayWarningAlert(3);}
        }
    }

    /** Method resets parts table to all parts. This method resets parts table to list all available parts if search text field is empty.
     * @param keyEvent Key is pressed in the search part text field*/
    public void clearSearch(KeyEvent keyEvent) {
        String checkText = querySearchPartMod.getText();
        if (checkText.equals(""))
        {
            partsTableViewModTwo.setItems(getAllParts());
        }
    }

   /**Method adds Part to associated part list. Method adds selected part to associated parts, if there is no selection made, alert is displayed.]
    * @param event Action on add button pushed*/
    public void addButtonPushed(ActionEvent event) {

        Part selectedItem = partsTableViewModTwo.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            Utility.displayWarningAlert(1);

        } else {
            selectedProduct.addAssociatedPart(selectedItem);
            associatedPartsModTable.setItems(associatedParts);
        }
    }

    /**Remove associated part. Method is used to delete selected part from associated parts list, if no part is selected an alert is displayed.
     * @param event Action on remove button pushed*/

    public void removeAssocPartPushed(ActionEvent event) {

        if (associatedPartsModTable.getSelectionModel().getSelectedItem() == null ){
            Utility.displayWarningAlert(4);
        }

        else {


            if (Utility.displayAlert(2)) { //this gives us rows that were selected

                try {
                    ObservableList<Part> selectedRows, allSelectedParts;
                    allSelectedParts = associatedPartsModTable.getItems();

                    //this gives us rows that were selected
                    selectedRows = associatedPartsModTable.getSelectionModel().getSelectedItems();
                    for (Part part : selectedRows) {
                        allSelectedParts.remove(part);
                    }
                } catch (NoSuchElementException e) {

                    Utility.displayWarningAlert(3);
                }
            }


        }
    }

    /**This method is to update the product. Method uses product id to set new product data.
     * @param product selected product
     * @param id ID of product*/
    public void updated(int id, Product product) {
        int index = -1;
        for (Product productS : Inventory.getAllProducts()) {
            index += 1;
            if (productS.getId() == id) {
                Inventory.getAllProducts().set(index, product);

            }
        }

    }

    /**This method saves updated Product. This method saves updated product after input validation,  if associated parts were not added an alert is displayed.
     * Redirects back to main screen
     * @param event Action on save updated product button */
    public void saveUpdatedProductButtonPushed(ActionEvent event) {
        try {


            int id = Integer.parseInt(idPrModTextField.getText());
            String name = namePrModTextField.getText();
            double price = Double.parseDouble(pricePrModTextField.getText());
            int stock = Integer.parseInt(invPrModTextField.getText());
            int max = Integer.parseInt(maxPrModTextField.getText());
            int min = Integer.parseInt(minPrTextFieldMod.getText());

            if (Utility.inputValidation (min, max, stock, name))
            {

                Product newProduct = new Product(id, name, price, stock, min, max);


                for (Part part : associatedParts) {

                    newProduct.addAssociatedPart(part);
                }

                selectedProduct.setId(id);
                selectedProduct.setName(name);
                selectedProduct.setPrice(price);
                selectedProduct.setStock(stock);
                selectedProduct.setMax(max);
                selectedProduct.setMin(min);
                updated(id, selectedProduct);


                if (associatedPartsModTable.getItems().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error dialog");
                    alert.setContentText("Please add associated parts!");
                    alert.showAndWait();
                } else {

                    stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                }
            }
        } catch(NumberFormatException | IOException e){

            Utility.displayErrorAlert();
        }
    }


    /**Initializes controller. Method is to initialize controller for the add product scene, sets parts and associated parts table views and sets product id field not editable.
     * @param url  Specifies how to resolve the root object's relative paths
     * @param resourceBundle Localization resources for the root object, null if  there no localization of the root object.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        idPrModTextField.setEditable(false);
        selectedProduct = ControllerMainScreen.productToUpdate();
        associatedParts = selectedProduct.getAllAssociatedParts();


        //Available Parts table columns
        partIdColumnMod.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumnMod.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumnMod.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumnMod.setCellValueFactory(new PropertyValueFactory<>("price"));


        partsTableViewModTwo.setItems(Inventory.getAllParts());


        assocPartIdColumnMod.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocNameColumnMod.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPriceColumnMod.setCellValueFactory(new PropertyValueFactory<>("price"));
        assocStockColumnMod.setCellValueFactory(new PropertyValueFactory<>("stock"));



        associatedPartsModTable.setItems(associatedParts);

        idPrModTextField.setText(String.valueOf(selectedProduct.getId()));
        namePrModTextField.setText(selectedProduct.getName());
        invPrModTextField.setText(String.valueOf(selectedProduct.getStock()));
        pricePrModTextField.setText(String.valueOf(selectedProduct.getPrice()));
        maxPrModTextField.setText(String.valueOf(selectedProduct.getMax()));
        minPrTextFieldMod.setText(String.valueOf(selectedProduct.getMin()));


    }


    }

