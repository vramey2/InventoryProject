package sample.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import sample.Model.Outsourced;
import sample.Model.inHouse;
import sample.Model.Part;
import sample.Model.Inventory;


import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import static sample.Model.Inventory.*;

public class ControllerAddPart implements Initializable{

    public Button backButton;
    public RadioButton outsourced;
    public RadioButton inHouse;
    public Label changeLabel;
    public TextField idNum;
    public TextField invTextField;
    public TextField priceTextField;
    public TextField maxTextField;
    public TextField machineIdTextField;
    public TextField minTextField;
    public ToggleGroup toggleGr;

    @FXML
    private TextField nameTextField;


    Stage stage;
    Parent scene;
    private String name;
    private int id;
    private int max;
    private int min;
    private int stock;
    private double price;


    //Method to change the scene to add Part
    public void backButtonPushed(ActionEvent event) throws IOException {
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
        System.out.println("Initialized");
    }

    public void outSourcedSelected(ActionEvent actionEvent) {
        changeLabel.setText("Company Name");
    }

    public void inHouseSelected(ActionEvent actionEvent) {
        changeLabel.setText("Machine ID");
    }

    public void saveButtonPushed(ActionEvent actionEvent) throws IOException {

try {
        int id = generateID();


        String name = nameTextField.getText();
        double price = Double.parseDouble(priceTextField.getText());
        int stock = Integer.parseInt(invTextField.getText());
        int max = Integer.parseInt(maxTextField.getText());
        int min = Integer.parseInt(minTextField.getText());

        if (max < min || stock < min || stock > max) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please enter valid value!");
            alert.showAndWait();
            System.out.println(" inside if");
        } else {

            {

                if (inHouse.isSelected()) {
                    int machineID = Integer.parseInt(machineIdTextField.getText());
                    addPart(new inHouse(id, name, price, stock, min, max, machineID));
                } else {
                    String companyName = machineIdTextField.getText();
                   addPart(new Outsourced(id, name, price, stock, min, max, companyName));
                }
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

            } }}
         catch (NumberFormatException e) {

                System.out.println("Inside exception");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error dialog");
                alert.setContentText("Please enter valid value for each field!");
                alert.showAndWait();

            }
        }}




