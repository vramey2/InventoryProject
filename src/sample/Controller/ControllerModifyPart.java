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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Inventory.updatePart;


/**This is controller class that initiates functionality of modifyPartScreen.fxml
 *
 * @author Veronika Ramey
 * */

public class ControllerModifyPart implements Initializable {

/**Radio button to select for in-house part*/
    public RadioButton inHouseRadioButton;

    /**Toggle group for radio buttons*/
    public ToggleGroup toggleGr;

    /**Radio button for outsourced part*/
    public RadioButton outsourcedRadioButton;

    /**Label that changes depending on type of part*/
    public Label changeLabel;

    /**Text field for part ID*/
    public TextField idNum;

    /**Text field for part name*/
    public TextField nameTextField;

    /**Text field for quantity of parts - stock*/
    public TextField invTextField;

    /**Text field for price of part*/
    public TextField priceTextField;

    /**Text field for maximum quantity of part*/
    public TextField maxTextField;

    /**Text field for machineID for in-hose part*/
    public TextField machineIdTextField;

    /**Text field for minimum quantity of part*/
    public TextField minTextField;

    /**Button to save updated part*/
    public Button saveButton;

    /**Selected part object*/
    public Part selectedPart;

    /**Button to go back to main screen*/
    public Button backButton;

    /**Stage to display*/
    Stage stage;

    /**Scene to display*/
    Parent scene;


    /**Method to go back to main screen. This method first asks to confirm if user wants to go back, if confirmed the scene is changed to main screen
     * @param event Action on back button pushed*/
    public void backButtonPushed(ActionEvent event) throws IOException {

        if (Utility.displayAlert(1)) {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();}
    }

    /**Method saves updated part. Method saves updated part, first input is validated, than part is saved, error alert displayed if there is a format error
     * @param event Action on save button pushed.*/
    public void saveButtonPushed(ActionEvent event) throws IOException {

        try {
            int id = Integer.parseInt(idNum.getText());
            int index = id -1;

            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(invTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());



            if (Utility.inputValidation (min, max, stock, name))
            {
                if (inHouseRadioButton.isSelected()) {
                    int machineID = Integer.parseInt(machineIdTextField.getText());

                 updatePart(index, new inHouse(id, name, price, stock, min, max, machineID));



                } else if (outsourcedRadioButton.isSelected()) {
                    String companyName = machineIdTextField.getText();
                updatePart(index, new Outsourced(id, name, price, stock, min, max, companyName));
                                  }


                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();
            }
        } catch (NumberFormatException e) {
           Utility.displayErrorAlert();

        }

    }


    /**Method is used to initialize the view. This method is accepting data that initializes the view.
     * @param part Selected part
     */

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


/**Changes label to company name. This method changes label to 'company name' if outsource is selected.
 * @param actionEvent Action on outsourced button*/
    public void outSourcedSelected(ActionEvent actionEvent) {
        changeLabel.setText("Company Name");
        outsourcedRadioButton.setSelected(true);
    }

/**Changes label to machine ID. This method changes label to 'machine id' if in-house is selected.
 * @param actionEvent Action on the in-house radio button*/
    public void inHouseSelected(ActionEvent actionEvent) {
        changeLabel.setText("Machine ID");
        inHouseRadioButton.setSelected(true);
    }

    /**Initializes controller. Method is to initialize controller for the modify part scene.
     * @param url  Specifies how to resolve the root object's relative paths
     * @param resourceBundle Localization resources for the root object, null if  there no localization of the root object.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }

}
