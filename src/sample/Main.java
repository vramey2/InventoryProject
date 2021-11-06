package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.*;
import sample.Controller.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/mainScreen.fxml"));
        primaryStage.setTitle(" ");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
//Load test data

        Product product1 = new Product(1, "Wheel", 15.25, 3, 1, 200);
        Product product2 = new Product(2, "Chair", 1.25, 4, 1, 200);
        Product product3 = new Product(3, "Something", 100.25, 3, 1, 200);
        Product product4 = new Product(4, "Top", 35.05, 3, 1, 200);
        Product product5 = new Product(5, "Engine", 250.25, 3, 1, 200);


        Inventory.addProduct (product1);
        Inventory.addProduct (product2);
        Inventory.addProduct (product3);
        Inventory.addProduct (product4);
        Inventory.addProduct (product5);

        Part part1 = new Outsourced(1, "spoon", 24.5, 1, 1, 500, "Wendy");
        Part part2 = new Outsourced(2, "knife", 30.1, 5, 1, 500, "Wendy");
        Part part3 = new Outsourced(3, "fork", 25.5, 21, 1, 500, "Wendy");
        Part part4 = new inHouse(3, "plate", 25.5, 21, 1, 500, 224);
        Part part5 = new inHouse(3, "pot", 25.5, 21, 1, 500, 300);



        Inventory.addPart(part1);
        Inventory.addPart(part2);
        Inventory.addPart(part3);
        Inventory.addPart(part4);
        Inventory.addPart(part5);


        launch(args);
    }
}