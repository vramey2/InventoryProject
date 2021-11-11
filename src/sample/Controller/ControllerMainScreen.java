package sample.Controller;

import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Model.Inventory;
import sample.Model.Part;
import sample.Model.Product;
import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Model.Inventory.*;
public class ControllerMainScreen implements Initializable {

    private static Product selectedProduct;
    public TextField queryT;
    public TextField queryProd;
    public Button exitButton;
    public Button addPartButton;
    public Button modifyButton;
    public Button deleteButton;
    public Button addProductButton;
    public Button deleteProductButton;
    public Button modifyProductButton;
    @FXML private TableView <Part> partsTableView;
    @FXML private TableColumn <Part, Integer> partIdColumn;
    @FXML private TableColumn <Part, String> partNameColumn;
    @FXML private TableColumn <Part, Integer> partStockColumn;
    @FXML private TableColumn <Part, Double>  partPriceColumn;
    @FXML private TableView <Product> productsTableView ;
    @FXML private TableColumn <Product, String> productNameColumn;
    @FXML private TableColumn <Product, Integer>  productIdColumn;
    @FXML private TableColumn <Product, Integer> productStockColumn;
    @FXML private TableColumn <Product, Double> productPriceColumn;



    Stage stage;
    Parent scene;


    //Method to change the scene to add Part
    public void addButtonPushed  (ActionEvent event) throws IOException {

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/addPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void addProductButtonPushed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation( getClass().getResource( "/sample/View/addProductScreen.fxml"));

        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);

        ControllerAddProductScreen controller = loader.getController();



       //This line gets the stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    //Method to change the scene to modify Part
    public void modifyPartButtonPushed(ActionEvent event) throws IOException {

        if (partsTableView.getSelectionModel().getSelectedItem() == null ){
            Alert alert = new Alert ( Alert.AlertType.WARNING);
            alert.setHeaderText("Please select a part first!");
            alert.showAndWait();
        }

        else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/View/modifyPartScreen.fxml"));

            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);


            ControllerModifyPart controller = loader.getController();


            controller.initData(partsTableView.getSelectionModel().getSelectedItem());


            //This line gets the stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();
        }
    }

    public void modifyProductButtonPushed(ActionEvent event) throws IOException {

        if (productsTableView.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Please select a product first!");
            alert.showAndWait();
        } else {
            selectedProduct = productsTableView.getSelectionModel().getSelectedItem();


            Parent parent = FXMLLoader.load(getClass().getResource("/sample/View/modifyProductScreen.fxml"));


            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }


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
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Part is not found!");
                    alert.showAndWait();
                }
            }
        }





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
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setHeaderText("Product is not found!");
                    alert.showAndWait();
                }
            }
        }
    }

    //Exit button method to close main screen window
    public void exitButtonPushed (ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to exit application?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
    }


    public void userClickedOnPartsT(MouseEvent mouseEvent) {
        this.modifyButton.setDisable(false);
        this.deleteButton.setDisable(false);
    }

    public void deleteButtonPushed(ActionEvent actionEvent) {

        if (partsTableView.getSelectionModel().getSelectedItem() == null ){
            Alert alert = new Alert ( Alert.AlertType.WARNING);
            alert.setHeaderText("Part was not deleted!");
            alert.showAndWait();
        }
        else {Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this part?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //this gives us rows that were selected
            Inventory.deletePart(partsTableView.getSelectionModel().getSelectedItem());

        }
        else
        { alert = new Alert ( Alert.AlertType.WARNING);
            alert.setHeaderText("Part was not deleted!");
            alert.showAndWait();}
    }}


    public void deleteProductButtonPushed(ActionEvent event) {
        if (productsTableView.getSelectionModel().getSelectedItem() == null ){
            Alert alert = new Alert ( Alert.AlertType.WARNING);
            alert.setHeaderText("Product was not deleted!");
            alert.showAndWait();
        }
        else {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you want to delete this poduct?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            //this gives us rows that were selected
            ObservableList<Part> associatedParts = productsTableView.getSelectionModel().getSelectedItem().getAllAssociatedParts();

            if (associatedParts.size() >= 1) {
                alert = new Alert ( Alert.AlertType.WARNING);
                alert.setHeaderText("Product has associated parts!");
                alert.showAndWait();
            } else
                Inventory.deleteProduct( productsTableView.getSelectionModel().getSelectedItem());

        }
        else {
            alert = new Alert ( Alert.AlertType.WARNING);
            alert.setHeaderText("Product was not deleted!");
            alert.showAndWait();}

    }}


    public void userClickedOnProductsT(MouseEvent mouseEvent) {
        this.deleteProductButton.setDisable(false);
        this.modifyProductButton.setDisable(false);
    }

    public static Product getProductToModify() {
        return selectedProduct;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // modifyButton.isDisable();
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
