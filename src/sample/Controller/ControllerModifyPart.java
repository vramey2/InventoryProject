package sample.Controller;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Model.Outsourced;
import sample.Controller.ControllerMainScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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
    private inHouse selectedInhouse;
    private Part partS;

    private Outsourced selectedOutsourced;


    public void backButtonPushed(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void saveButtonPushed(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(idNum.getText());
            int index = id -1;

            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(invTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());



            if (max < min || stock < min || stock > max || name.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Please enter valid value!");
                alert.showAndWait();
                System.out.println(" inside if");
            } else {



                if (inHouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(machineIdTextField.getText());

                 updatePart(index, new inHouse(id, name, price, stock, min, max, machineID));
                 //   inHouse modifiedInHouse = new inHouse(id, name, price, stock, min, max, machineID);
               //     Inventory.addPart(modifiedInHouse);
                  /**  selectedInhouse.setId(id);
                    selectedInhouse.setName(name);
                    selectedInhouse.setPrice(price);
                    selectedInhouse.setStock(stock);
                    selectedInhouse.setMax(max);
                    selectedInhouse.setMin(min);
                    selectedInhouse.setMachineID(machineID);

                    updated (id, selectedInhouse);*/
                  //  inHouse selectedInhouse = new inHouse(id, name, price, stock, min, max, machineID);
                //    Inventory.getAllParts().set(selectedIndex, selectedInhouse);



                } else if (outsourcedRadioButton.isSelected()) {
                    String companyName = machineIdTextField.getText();
                updatePart(index, new Outsourced(id, name, price, stock, min, max, companyName));
                 //   Outsourced modifiedOutsourced = new Outsourced(id, name, price, stock, min, max, companyName);
                  //  Inventory.addPart(modifiedOutsourced);
                    /**
                    selectedOutsourced.setId(id);
                    selectedOutsourced.setName(name);
                    selectedOutsourced.setPrice(price);
                    selectedOutsourced.setStock(stock);
                    selectedOutsourced.setMax(max);
                    selectedOutsourced.setMin(min);
                    selectedOutsourced.setCompanyName (companyName);

                    updated (id, selectedOutsourced);*/


                }

                //Inventory.deletePart(selectedPart);


           //     updatedPart(id, selectedPart);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error dialog");
            alert.setContentText("Please enter valid value for each field!");
            alert.showAndWait();

        }

    }

    public boolean updated (int id, Part part) {
        int index = -1;
        for (Part partS : Inventory.getAllParts()) {
            index += 1;
            if (partS.getId() == id) {
                Inventory.getAllParts().set(index, part);
                return true;
            }

        }
        return false; }
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
        } else if (part instanceof inHouse) {
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
        outsourcedRadioButton.setSelected(true);
    }


    public void inHouseSelected(ActionEvent actionEvent) {
        changeLabel.setText("Machine ID");
        inHouseRadioButton.setSelected(true);
    }




}
