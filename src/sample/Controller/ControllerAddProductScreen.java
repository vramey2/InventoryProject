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
import javafx.stage.Stage;


import sample.Model.*;
import sample.Model.Product;


import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import static sample.Model.Inventory.*;

public class ControllerAddProductScreen implements Initializable {
    public TextField idPrTextField;
    public TextField namePrTextField;
    public TextField invPrTextField;
    public TextField pricePrTextField;
    public TextField maxPrTextField;
    public TextField minPrTextField;
    public Button cancelButton;
    public Button savePrButton;
    public TableView <Part> partsTableViewTwo;
    public TableColumn <Part, Integer> partIdColumn;
    public TableColumn <Part, String> partNameColumn;
    public TableColumn <Part, Integer> partStockColumn;
    public TableColumn <Part, Double> partPriceColumn;
    public TableView <Part> associatedPartsTable;
    public TableColumn <Part, Integer> assocPartIdColumn;
    public TableColumn <Part, String> assocNameColumn;
    public TableColumn <Part, Integer> assocStockColumn;
    public TableColumn <Part, Double> assocPriceColumn;
    public TextField querySearchPart;
    public Button removeAssocPartButton;
    Stage stage;
    Parent scene;


    //may be need to delete later
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();



    public void cancelButtonPushed(ActionEvent event) throws IOException {

        if (Utility.displayAlert(1)) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idPrTextField.setEditable(false);

        //Available Parts table columns
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        partsTableViewTwo.setItems(Inventory.getAllParts() );

        //Associated Parts table columns
        assocPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        assocStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));



    }

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
    public void removeAssocPartPushed(ActionEvent event) {
        if (Utility.displayAlert(2)){
            try {
                ObservableList<Part> selectedRows, allSelectedParts;
                allSelectedParts = associatedPartsTable.getItems();

                //this gives us rows that were selected
                selectedRows = associatedPartsTable.getSelectionModel().getSelectedItems();
                for (Part part : selectedRows) {
                    allSelectedParts.remove(part);
                }
            }
            catch (NoSuchElementException e){

                //ignore
            }


        }}
    public void addButtonPushed(ActionEvent event) {

        Part selectedItem;
        selectedItem = partsTableViewTwo.getSelectionModel().getSelectedItem();
        //  associatedPartsTable.getItems().add(selectedItem);
        //maybe to delete
        if (selectedItem != null){
            associatedParts.add(selectedItem);
            associatedPartsTable.setItems(associatedParts);
        }

    }

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
    }


}
