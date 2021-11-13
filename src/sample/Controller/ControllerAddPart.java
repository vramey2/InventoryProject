package sample.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.Outsourced;
import sample.Model.inHouse;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Inventory.*;


/**This is controller class that initiates functionality of addPartScreen.fxml
 *
 * @author Veronika Ramey
 * */
public class ControllerAddPart implements Initializable {

    /**Button to go back to the main scene*/
    public Button backButton;

    /**Radio button to chose if the part is outsourced*/
    public RadioButton outsourced;

    /**Radio button to chose if the part is in-house*/
    public RadioButton inHouse;

    /**Label for text field which will be either machine id or company name, depending on the type of part*/
    public Label changeLabel;

    /**Text field for id number*/
    public TextField idNum;

    /**Text field for part's stock*/
    public TextField invTextField;

    /**Text field for part's price*/
    public TextField priceTextField;

    /**Text field for maximum quantity of part*/
    public TextField maxTextField;

    /**Text field for machine id or company name depending on type of part*/
    public TextField machineIdTextField;

    /**Text field for minimum quantity of part*/
    public TextField minTextField;

    /**Toggle group for in-house and outsourced radio buttons*/
    public ToggleGroup toggleGr;

    /**Text field for part's name*/
    public TextField nameTextField;

    /**Stage to be displayed*/
    Stage stage;

    /**scene to be displayed*/
    Parent scene;


    /**Changes scene back to the main screen.
     * This method is used to go back to the main screen once 'back button' is pushed, once the user confirms action.
     * @param event Action on back button */
    public void backButtonPushed(ActionEvent event) throws IOException {

        if (Utility.displayAlert(1)) {

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/sample/View/mainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**Sets texts to 'company name'. Method is used to set text to 'company name' if outsourced radio button is selected.
     * @param actionEvent Action on outsourced radio button*/
    public void outSourcedSelected(ActionEvent actionEvent) {
        changeLabel.setText("Company Name");
    }

    /**Sets texts to 'Machine ID'. Method is used to set text to 'Machine id ' if inhouse radio button is selected.
     * @param actionEvent Action on ihouse radio button*/
    public void inHouseSelected(ActionEvent actionEvent) {
        changeLabel.setText("Machine ID");
    }


    /**Method creates new instance of part. Method adds new part and takes back to the main screen.
     * Before part is saved input into the fields is validated and corresponding alerts are displayed.
     * @param actionEvent action on save button*/
    public void saveButtonPushed(ActionEvent actionEvent) throws IOException {

        try {
            int id = generateID();


            String name = nameTextField.getText();
            double price = Double.parseDouble(priceTextField.getText());
            int stock = Integer.parseInt(invTextField.getText());
            int max = Integer.parseInt(maxTextField.getText());
            int min = Integer.parseInt(minTextField.getText());

            if (Utility.inputValidation (min, max, stock, name))
            {

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

                }
            }
        } catch (NumberFormatException e) {

            System.out.println("Inside exception");

            Utility.displayErrorAlert();

        }
    }

    /**Intializes controller. Method is to initialize controller for the add part scene.
     * @param url  Specifies how to resolve the root object's relative paths
     * @param resourceBundle Localization resources for the root object, null if  there no localization of the root object.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initialized");
    }


    }





