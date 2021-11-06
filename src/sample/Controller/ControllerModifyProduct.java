package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sample.Model.Outsourced;
import sample.Model.Part;
import sample.Model.inHouse;
import sample.Model.Product;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerModifyProduct implements Initializable{
    public TextField idPrModTextField;
    public TextField namePrModTextField;
    public TextField invPrModTextField;
    public TextField pricePrModTextField;
    public TextField maxPrModTextField;
    public TextField minPrTextFieldMod;
    public Product selectedProduct;
    public TextField querySearchPartMod;
    public TableView partsTableViewModTwo;
    public TableColumn partIdColumnMod;
    public TableColumn partNameColumnMod;
    public TableColumn partStockColumnMod;
    public TableColumn partPriceColumnMod;
    public Button addModPrButton;
    public TableView associatedPartsModTable;
    public TableColumn assocPartIdColumnMod;
    public TableColumn assocNameColumnMod;
    public TableColumn assocStockColumnMod;
    public TableColumn assocPriceColumnMod;
    public Button removeAssocPartButton;
    public Button cancelButtonMod;
    public Button savePrButtonMod;

    public void initDataProduct(Product product) {

        selectedProduct = product;

        idPrModTextField.setText (String.valueOf(selectedProduct.getId()));
        namePrModTextField.setText(selectedProduct.getName());
        invPrModTextField.setText(String.valueOf(selectedProduct.getStock()));
        pricePrModTextField.setText(String.valueOf(selectedProduct.getPrice()));
        maxPrModTextField.setText(String.valueOf(selectedProduct.getMax()));
        minPrTextFieldMod.setText(String.valueOf(selectedProduct.getMin()));

        selectedProduct.getAllAssociatedParts();


        /**idNum.setText(String.valueOf(selectedProduct.getId()));
         nameTextField.setText(selectedPart.getName());
         invTextField.setText(String.valueOf(selectedPart.getStock()));
         priceTextField.setText(String.valueOf(selectedPart.getPrice()));
         maxTextField.setText(String.valueOf(selectedPart.getMax()));
         minTextField.setText(String.valueOf(selectedPart.getMin()));
         if (part instanceof Outsourced) {

         Outsourced outsourced = (Outsourced) part;
         changeLabel.setText("Company Name");
         outsourcedRadioButton.setSelected(true);

         machineIdTextField.setText(outsourced.getCompanyName());
         } else if  (part instanceof inHouse) {
         inHouse inhouseP = (inHouse) part;
         changeLabel.setText("Machine ID");
         inHouseRadioButton.setSelected(true);
         machineIdTextField.setText(String.valueOf(inhouseP.getMachineID()));

         }*/
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");}

    public void getSearchedPartMod(ActionEvent event) {
    }

    public void addButtonPushed(ActionEvent event) {
    }

    public void removeAssocPartPushedMod(ActionEvent event) {
    }

    public void cancelButtonPushedMod(ActionEvent event) {
    }

    public void savePrButtonPushedMod(ActionEvent event) {
    }
}
