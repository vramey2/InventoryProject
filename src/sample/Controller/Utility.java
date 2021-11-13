package sample.Controller;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**Class provides additional methods used for controller classes with purpose of code re-usability.
 *
 * @author Veronika RAMEY
*/

public class Utility {

/**Validates user input. Method gives an alert if maximum is less minimum, stock quantity is not between maximum and minimum or name is not entered.
 * @param min minimum value
 * @param max value
 * @param name name of part o rproduct
 * @param stock quantity of part or product
 * @return boolean returns true is validated and false if it is not*/
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

    /**Display alert. Methods displays error alert and asks user to enter missing input.*/
    public static void displayErrorAlert (){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error dialog");
        alert.setContentText("Please enter valid value for each field!");
        alert.showAndWait();
    }


    /**Displays warning alert. Method displays warning alert and uses switch to display different warning text.
     * @param alertNumber number of the warning message*/
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

    /**Displays confirmation alert. Method is to ask for confirmation, displays confirmation type alert and uses switch to include different content text.
     * @param alertNumber number of alert for switching text*/
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

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
        }

}





