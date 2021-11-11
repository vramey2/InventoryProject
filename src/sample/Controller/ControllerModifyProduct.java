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

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import sample.Controller.ControllerAddProductScreen;

import static sample.Model.Inventory.*;
import sample.Model.Product;
import sample.Controller.ControllerMainScreen;

public class ControllerModifyProduct implements Initializable {
    public TextField idPrModTextField;
    public TextField namePrModTextField;
    public TextField invPrModTextField;
    public TextField pricePrModTextField;
    public TextField maxPrModTextField;
    public TextField minPrTextFieldMod;
    public Product selectedProduct;
    public TextField querySearchPartMod;
    public TableView<Part> partsTableViewModTwo;
    public TableColumn<Part, Integer> partIdColumnMod;
    public TableColumn<Part, String> partNameColumnMod;
    public TableColumn<Part, Integer> partStockColumnMod;
    public TableColumn<Part, Double> partPriceColumnMod;
    public Button addModPrButton;
    public TableView<Part> associatedPartsModTable;
    public TableColumn<Part, Integer> assocPartIdColumnMod;
    public TableColumn<Part, String> assocNameColumnMod;
    public TableColumn<Part, Integer> assocStockColumnMod;
    public TableColumn<Part, Double> assocPriceColumnMod;
    public Button removeAssocPartButton;
    public Button cancelButtonMod;
    public Button savePrButtonMod;
    public Part selectedPart;
    public ObservableList<Part> associatedParts = FXCollections.observableArrayList();


    public Button addButton;
    Stage stage;
    Parent scene;


    public void cancelAssocPartButtonPushed(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to go back to main screen?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }

    }

    public void initDataProduct(Product product) {


        //   selectedProduct = product;

        selectedProduct = ControllerMainScreen.getProductToModify();

        associatedParts = selectedProduct.getAllAssociatedParts();

        idPrModTextField.setText(String.valueOf(selectedProduct.getId()));
        namePrModTextField.setText(selectedProduct.getName());
        invPrModTextField.setText(String.valueOf(selectedProduct.getStock()));
        pricePrModTextField.setText(String.valueOf(selectedProduct.getPrice()));
        maxPrModTextField.setText(String.valueOf(selectedProduct.getMax()));
        minPrTextFieldMod.setText(String.valueOf(selectedProduct.getMin()));


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
        idPrModTextField.setEditable(false);
        selectedProduct = ControllerMainScreen.getProductToModify();
        associatedParts = selectedProduct.getAllAssociatedParts();


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

        //  Product selectedProduct = productTable.getSelectionModel().getSelectedItem();

        associatedPartsModTable.setItems(associatedParts);

        idPrModTextField.setText(String.valueOf(selectedProduct.getId()));
        namePrModTextField.setText(selectedProduct.getName());
        invPrModTextField.setText(String.valueOf(selectedProduct.getStock()));
        pricePrModTextField.setText(String.valueOf(selectedProduct.getPrice()));
        maxPrModTextField.setText(String.valueOf(selectedProduct.getMax()));
        minPrTextFieldMod.setText(String.valueOf(selectedProduct.getMin()));


    }


// search for part

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
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeaderText("Part is not found!");
                alert.showAndWait();}
        }
    }



    public void addButtonPushed(ActionEvent event) {

        Part selectedItem = partsTableViewModTwo.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            System.out.println("Select a part");

        } else {
            selectedProduct.addAssociatedPart(selectedItem);
            associatedPartsModTable.setItems(associatedParts);
        }
    }

    public void removeAssocPartPushed(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this part?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) { //this gives us rows that were selected

            try {
                ObservableList<Part> selectedRows, allSelectedParts;
                allSelectedParts = associatedPartsModTable.getItems();

                //this gives us rows that were selected
                selectedRows = associatedPartsModTable.getSelectionModel().getSelectedItems();
                for (Part part : selectedRows) {
                    allSelectedParts.remove(part);
                }
            } catch (NoSuchElementException e) {

                //ignore
            }


        }
    }

    public boolean updated(int id, Product product) {
        int index = -1;
        for (Product productS : Inventory.getAllProducts()) {
            index += 1;
            if (productS.getId() == id) {
                Inventory.getAllProducts().set(index, product);
                return true;
            }

        }
        return false;
    }

    public void saveAssocPartButtonPushed(ActionEvent event) {
        try {


            int id = Integer.parseInt(idPrModTextField.getText());
            String name = namePrModTextField.getText();
            double price = Double.parseDouble(pricePrModTextField.getText());
            int stock = Integer.parseInt(invPrModTextField.getText());
            int max = Integer.parseInt(maxPrModTextField.getText());
            int min = Integer.parseInt(minPrTextFieldMod.getText());

            if (max < min || stock < min || stock > max || name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter valid value!");
                alert.showAndWait();
                System.out.println(" inside if");
            } else {

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

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error dialog");
                alert.setContentText("Please enter valid value for each field!");
                alert.showAndWait();
            }
        }

    }

