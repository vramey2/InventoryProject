package sample.Controller;


import javafx.beans.property.Property;
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
import java.util.Optional;
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
    public TableView partsTableViewTwo;
    public TableColumn partIdColumn;
    public TableColumn partNameColumn;
    public TableColumn partStockColumn;
    public TableColumn partPriceColumn;
    public TableView associatedPartsTable;
    public TableColumn assocPartIdColumn;
    public TableColumn assocNameColumn;
    public TableColumn assocStockColumn;
    public TableColumn assocPriceColumn;
    public TextField querySearchPart;
    public Button removeAssocPartButton;
    Stage stage;
    Parent scene;
    private Part selectedPart;



    public void cancelButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to go back to main screen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
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

            addProduct(new Product(productId, name, price, stock, max, min));

            if (associatedPartsTable.getItems().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error dialog");
                alert.setContentText("Please add associated parts!");
                alert.showAndWait();}
            else {

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();}
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setContentText("Please enter valid value for each field!");
            alert.showAndWait();
        }
    }
    public void removeAssocPartPushed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) { //this gives us rows that were selected

            /** associatedParts.remove(selectedPart);
             deletePart(associatedPartsTable.getSelectionModel().getSelectedItem(), associatedPartsTable.getItems());
             associatedPartsTable.setItems(associatedPartsTable.getItems());*/
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

        Part selectedItem = (Part) partsTableViewTwo.getSelectionModel().getSelectedItem();
        associatedPartsTable.getItems().add(selectedItem);

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
