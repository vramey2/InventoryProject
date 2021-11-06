package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import sample.Model.Outsourced;
import sample.Model.Part;
import sample.Model.inHouse;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Inventory.addPart;
import static sample.Model.Inventory.updatePart;

public class ControllerModifyPart implements Initializable {


    public RadioButton inHouseRadioButton;
    public ToggleGroup toggleGr;
    public RadioButton outsourcedRadioButton;
    public Label changeLabel;
    public TextField idNum;
    public TextField nameTextField;
    public TextField invTextField;
    public TextField priceTextField;
    public TextField maxTextField;
    public TextField machineIdTextField;
    public TextField minTextField;
    public Button saveButton;
    public Part selectedPart;
    public Button backButton;

    Stage stage;
    Parent scene;


    public void backButtonPushed(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void saveButtonPushed(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idNum.getText());

        String name = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        int stock = Integer.parseInt(invTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());

        if (max < min || stock < min || stock > max)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please enter valid value!");
            alert.showAndWait();
            System.out.println(" inside if");
        }
        else {

            try {

                if (inHouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(machineIdTextField.getText());
                    addPart(new inHouse(id, name, price, stock, max, min, machineID));
                }
                else {
                    String companyName = machineIdTextField.getText();
                    addPart(new Outsourced(id, name, price, stock, max, min, companyName));
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
            catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error dialog");
                alert.setContentText("Please enter valid value for each field!");
                alert.showAndWait();

            }
        }
    }
    /**if (inHouseRadioButton.isSelected()) {
     updatePart(index, new inHouse(Integer.parseInt(idNum.getText()), nameTextField.getText(), Double.parseDouble(priceTextField.getText()), Integer.parseInt(invTextField.getText()), Integer.parseInt(maxTextField.getText()), Integer.parseInt(minTextField.getText()), Integer.parseInt(machineIdTextField.getText())));
     } else {
     updatePart(index, new Outsourced(Integer.parseInt(idNum.getText()), nameTextField.getText(), Double.parseDouble(priceTextField.getText()), Integer.parseInt(invTextField.getText()), Integer.parseInt(maxTextField.getText()), Integer.parseInt(minTextField.getText()), machineIdTextField.getText()));
     }

     stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
     scene = FXMLLoader.load(getClass().getResource("/sample/View/view.fxml"));
     stage.setScene(new Scene(scene));
     stage.show();*/



    //machineIdTextField.setText(String.valueOf(selectedPart.getMachineID()));


    //This method accepts person to initialize the view
    public void initData(Part part) {

        selectedPart = part;

        idNum.setText(String.valueOf(selectedPart.getId()));
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

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }


    public void outSourcedSelected(ActionEvent actionEvent) {
        changeLabel.setText("Company Name");
        outsourcedRadioButton.setSelected(true);}


    public void inHouseSelected (ActionEvent actionEvent){
        changeLabel.setText("Machine ID");
        inHouseRadioButton.setSelected(true);
    }

}
