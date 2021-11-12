package sample.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Controller.*;

import java.util.Optional;

public class Utility {



    public static boolean inputValidation (int min, int max, int stock, String name){
        if  (max < min || stock < min || stock > max || name.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Please enter valid value!");
            alert.showAndWait();
            System.out.println(" inside if");
            return false;
        }
        else
            return true;
    }

    public static void displayErrorAlert (){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error dialog");
        alert.setContentText("Please enter valid value for each field!");
        alert.showAndWait();
    }

    public static void displayWarningAlert (int alertNumber) {

         Alert alert = new Alert ( Alert.AlertType.WARNING);
        switch (alertNumber) {
            case 1:
                alert.setHeaderText("Please select a part first!");
                break;

            case 2:
                alert.setHeaderText("Please select a product first!");
                break;

            case 3:
                alert.setHeaderText("Part is not found!");
                break;

            case 4:
                alert.setHeaderText("Part was not deleted!");
                break;

            case 5:
                alert.setHeaderText("Product is not found!");
                break;

            case 6:
                alert.setHeaderText("Product was not deleted!");
                break;

            case 7:
                alert.setHeaderText("Product has associated parts!");
                break;

        }
        alert.showAndWait();
    }

    public static boolean displayAlert (int alertNumber) {

        Alert alert = new Alert (Alert.AlertType.CONFIRMATION);
        switch (alertNumber)
        {
            case 1:
                alert.setContentText("Do you want to go back to main screen?");
                break;

            case 2:
                alert.setContentText("Do you want to remove this part?");
                break;

            case 3:
                alert.setContentText("Do you want to delete this poduct?");
                break;

            case 4:
                alert.setContentText("Do you want to exit application?");
        }
/**
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, " ");
        switch (alertNumber) {
            case 1:
              new Alert(Alert.AlertType.CONFIRMATION, "Do you want to go back to main screen?");

            break;

            case 2:
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to remove this part?");

                break;

        }
 */
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
        } }





