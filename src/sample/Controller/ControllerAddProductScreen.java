package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import static sample.Model.Inventory.*;

/**This is controller class that initiates functionality of addProductScreen.fxml
 *
 * @author Veronika Ramey
 * */
public class ControllerAddProductScreen implements Initializable {

    /**Text field for product id*/
    public TextField idPrTextField;

    /**Text field for product name*/
    public TextField namePrTextField;

    /**Text field for product stock*/
    public TextField invPrTextField;

    /**Text field for product price*/
    public TextField pricePrTextField;

    /**Text field for maximum quantity of product*/
    public TextField maxPrTextField;

    /**Text field for minimum quantity of product*/
    public TextField minPrTextField;

    /**Button to cancel action and go back to main scene*/
    public Button cancelButton;

    /**Button to save product*/
    public Button savePrButton;

    /**Table view for all parts*/
    public TableView <Part> partsTableViewTwo;

    /**Column for part id for all parts table*/
    public TableColumn <Part, Integer> partIdColumn;

    /**Column for part name for all parts table*/
    public TableColumn <Part, String> partNameColumn;

    /**Column for part stock for all parts table*/
    public TableColumn <Part, Integer> partStockColumn;

    /**Column for part price for all parts table*/
    public TableColumn <Part, Double> partPriceColumn;

    /**Table view for associated parts*/
    public TableView <Part> associatedPartsTable;

    /**Column for part id for associated parts*/
    public TableColumn <Part, Integer> assocPartIdColumn;

    /**Column for part name for associated parts*/
    public TableColumn <Part, String> assocNameColumn;

    /**Column for part stock for associated parts*/
    public TableColumn <Part, Integer> assocStockColumn;

    /**Column for part price for associated parts*/
    public TableColumn <Part, Double> assocPriceColumn;

    /**Text field for searching part by name or id*/
    public TextField querySearchPart;

    /**Button to remove associated part for the table*/
    public Button removeAssocPartButton;

    /**stage for display*/
    Stage stage;

    /**scene for display*/
    Parent scene;

   /**Observable list of associated parts*/
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**Adds new product. This method is used to save new product, input values are validated and product is not saved unless associated part is added.
     * @param event Action on save product button
     * @throws IOException */
    public void savePrButtonPushed(ActionEvent event) throws IOException {

        try {
            int productId = generateProductId();

            String name = namePrTextField.getText();
            double price = Double.parseDouble(pricePrTextField.getText());
            int stock = Integer.parseInt(invPrTextField.getText());
            int max = Integer.parseInt(maxPrTextField.getText());
            int min = Integer.parseInt(minPrTextField.getText());


            if (Utility.inputValidation (min, max, stock, name))
            {
                Product productAdded = new Product(productId, name, price, stock, min, max);

                for (Part part : associatedParts) {
                    productAdded.addAssociatedPart(part);
                    System.out.println("inside for");
                }

                addProduct(productAdded);

                if (associatedPartsTable.getItems().isEmpty()) {
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
        }
        catch (NumberFormatException e) {

            Utility.displayErrorAlert();
        }
    }

    /**Method removes associated part. Method is used to remove associated part
     * @param event  Action on remove associated part button pushed*/
    public void removeAssocPartPushed(ActionEvent event) {
        if (associatedPartsTable.getSelectionModel().getSelectedItem() == null ){
            Utility.displayWarningAlert(4);
        }

        else {
        if (Utility.displayAlert(2)) {
            try {
                ObservableList<Part> selectedRows, allSelectedParts;
                allSelectedParts = associatedPartsTable.getItems();

                selectedRows = associatedPartsTable.getSelectionModel().getSelectedItems();
                for (Part part : selectedRows) {
                    allSelectedParts.remove(part);
                }

        }
            catch (NoSuchElementException e){

                Utility.displayWarningAlert(3);
            }}

        }}

    /**Method adds part to associated parts list. This method adds selected part to associated part list, if selection is not made a warning is displayed.
     * @param event  Action on add button pushed*/
    public void addButtonPushed(ActionEvent event) {

        Part selectedItem;
        selectedItem = partsTableViewTwo.getSelectionModel().getSelectedItem();

        if (selectedItem != null){
            associatedParts.add(selectedItem);
            associatedPartsTable.setItems(associatedParts);
        }
        else
            Utility.displayWarningAlert(1);

    }

    /**Searches for Part using name or id. Method searches for Part using partial name or name, then by id.
     * If part is not found a message is displayed.
     * @param event Action on search test field*/
    public void getSearchedPart(ActionEvent event) {


        String a =  querySearchPart.getText();

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
        partsTableViewTwo.setItems(parts);
        if (parts.isEmpty()) {

            {
                Utility.displayWarningAlert(3);
            }

        }

         }

         /** Method resets parts table to all parts. This method resets parts table to list all available parts if search text field is empty.
          * @param keyEvent Key is pressed in the search part textfield*/
    public void clearSearch(KeyEvent keyEvent) {
        String checkText = querySearchPart.getText();
        if (checkText.equals(""))
        {
            partsTableViewTwo.setItems(getAllParts());
        }
    }


    /**Method takes back to main scene. This method takes back to main screen, first confirmation is asked from the user.
     * @param event Action on cancel button pushed*/

    public void cancelButtonPushed(ActionEvent event) throws IOException {

        if (Utility.displayAlert(1)) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


    /**Initializes controller. Method is to initialize controller for the add product scene, sets parts and associated parts table views and sets product id field not editable.
     * @param url  Specifies how to resolve the root object's relative paths
     * @param resourceBundle Localization resources for the root object, null if  there no localization of the root object.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPrTextField.setEditable(false);

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTableViewTwo.setItems(Inventory.getAllParts() );

        assocPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        assocStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

    }



}
