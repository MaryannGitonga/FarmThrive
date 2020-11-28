/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farmthrive;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringJoiner;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
/**
 *
 * @author USER
 */
public class FarmThrive extends Application {
    
    @Override
    public void start(Stage primaryStage) {
//      Connection
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmthrive","root","");
            System.out.println("Suceess");
            Statement stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
//      End of Connection

        
//      Welcome
        BorderPane welcomeBorder = new BorderPane();
   
        HBox welcomeHbox = new HBox(40);
        welcomeHbox.setPadding(new Insets(50,50,50,80));

        Button farmerButton = new Button("Farmer");
        farmerButton.setPrefSize(150, 60);
        farmerButton.setStyle("-fx-background-color:#556B2F;-fx-font: 30px;-fx-border-width: 20px; -fx-text-fill:#fff;");
        
        Button consumerButton = new Button("Consumer");
        consumerButton.setPrefSize(150, 60);
        consumerButton.setStyle("-fx-background-color:#556B2F;-fx-font: 30px;-fx-border-width: 20px;-fx-text-fill:#fff;");

        welcomeHbox.getChildren().addAll(farmerButton,consumerButton);
        
        Label welcomeLabel = new Label("Welcome to FarmThrive");
        welcomeLabel.setTextFill(Color.DARKOLIVEGREEN);
        welcomeLabel.setFont(Font.font(40));
        welcomeLabel.setPadding(new Insets(50,50,50,50));
        welcomeBorder.setTop(welcomeLabel);
        welcomeBorder.setCenter(welcomeHbox);
//      End of Welcome
           
        
//      Consumer Dashboard
        String dbConsumerID = "", dbConsumerfName = "", dbConsumerlName = "", dbConsumerContact = "", dbConsumerAddress = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmthrive","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, firstName, lastName, contact, address FROM consumer WHERE ID = '1234'");
            System.out.println("Suceess");
            
            while(rs.next()){
                dbConsumerID = rs.getString("ID");
                dbConsumerfName = rs.getString("firstName");
                dbConsumerlName = rs.getString("lastName");
                dbConsumerContact = rs.getString("contact");
                dbConsumerAddress = rs.getString("address");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
        
        BorderPane consumerDashboard = new BorderPane();
        HBox dashboard = new HBox(360);
        
        Label consumerLabel = new Label("Consumer Dashboard");
        Button buyButton = new Button("BUY");
        buyButton.setFont(Font.font(16));
        buyButton.setStyle("-fx-text-fill:#fff; -fx-background-color:#556B2F;");
        
        dashboard.getChildren().addAll(consumerLabel,buyButton);
        
        consumerLabel.setFont(Font.font(30));
        consumerLabel.setTextFill(Color.DARKOLIVEGREEN);
        consumerDashboard.setTop(dashboard);
        
        GridPane updateConsumerForm = new GridPane();
        Consumer dbConsumer = new Consumer(dbConsumerID,dbConsumerfName,dbConsumerlName,dbConsumerContact,dbConsumerAddress);
        System.out.println(dbConsumer.toString());
        
        Label textConsumerfName = new Label("First Name:");
        TextField consumerfName = new TextField(dbConsumer.getFirstName());
        
        Label textConsumerlName = new Label("Last Name:");
        TextField consumerlName = new TextField(dbConsumer.getLastName());
        
        Label textConsumerContact = new Label("Contact:");
        TextField consumerContact = new TextField(dbConsumer.getContact());
        
        Label textConsumerAddress = new Label("Address");
        TextField consumerAddress = new TextField(dbConsumer.getAddress());
        
        Button updateConsumerBtn = new Button("Update Details");
        updateConsumerBtn.setStyle("-fx-background-color:#556B2F; -fx-text-fill:#fff;");
        
        updateConsumerForm.setPadding(new Insets(50,50,50,50));
        updateConsumerForm.setVgap(10);
        updateConsumerForm.setAlignment(Pos.CENTER_LEFT);
        
        updateConsumerForm.add(textConsumerfName,0,0);
        updateConsumerForm.add(consumerfName,0,1);
        
        updateConsumerForm.add(textConsumerlName,0,2);
        updateConsumerForm.add(consumerlName,0,3);
        
        updateConsumerForm.add(textConsumerContact,0,4);
        updateConsumerForm.add(consumerContact,0,5);
        
        updateConsumerForm.add(textConsumerAddress,0,6);
        updateConsumerForm.add(consumerAddress,0,7);
        updateConsumerForm.add(updateConsumerBtn,0,9);
        
        consumerDashboard.setLeft(updateConsumerForm);
        
        TableView produceTable = new TableView();
        TableColumn IDColumn = new TableColumn("ID");
        IDColumn.setCellValueFactory(new PropertyValueFactory("ID"));
        
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory("name"));
        
        TableColumn typeColumn = new TableColumn("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory("type"));
        
        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory("price"));
        
        TableColumn quantityColumn = new TableColumn("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory("quantity"));
        
        TableColumn farmerColumn = new TableColumn("Farmer ID");
        farmerColumn.setCellValueFactory(new PropertyValueFactory("farmer"));
        
        produceTable.getColumns().setAll(IDColumn, nameColumn, typeColumn, priceColumn, quantityColumn, farmerColumn);
        
        String produceID2, produceName2, produceType2, producePrice2, produceQuantity2, produceFarmer2;
        ArrayList<Produce> produces = new ArrayList<Produce>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmthrive","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, name, type, price, quantity, farmer FROM produce");
//            ResultSet count = stmt.executeQuery("SELECT COUNT(*) AS total FROM produce");
//            count.next();
            System.out.println("Suceess");
            
            while(rs.next()){
                produceID2 = String.valueOf(rs.getInt("ID"));
                produceName2 = rs.getString("name");
                produceType2 = rs.getString("type");
                producePrice2 = rs.getString("price");
                produceQuantity2 = rs.getString("quantity");
                produceFarmer2 = rs.getString("farmer");
//                System.out.println(produceID2);

                produces.add(new Produce(produceID2, produceName2,produceType2,producePrice2,produceQuantity2,produceFarmer2));
            }  
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
        
        for(Produce obj: produces){
            System.out.println(obj);
        }
        
        produceTable.setItems(FXCollections.observableArrayList(produces));
        produceTable.setStyle("-fx-selection-bar:#556B2F;");
        
        consumerDashboard.setRight(produceTable);
//      End of Consumer Dashboard

//      Farmer Dashboard
        String dbID = "", dbfName = "", dblName = "", dbContact = "", dbAddress = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmthrive","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ID, firstName, lastName, contact, address FROM farmer WHERE ID = '1237'");
            System.out.println("Suceess");
            
            while(rs.next()){
                dbID = rs.getString("ID");
                dbfName = rs.getString("firstName");
                dblName = rs.getString("lastName");
                dbContact = rs.getString("contact");
                dbAddress = rs.getString("address");
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
        
        BorderPane farmerDashboard = new BorderPane();
                               
        Label farmerLabel = new Label("Farmer Dashboard");
        farmerLabel.setFont(Font.font(30));
        farmerLabel.setTextFill(Color.DARKOLIVEGREEN);
        farmerDashboard.setTop(farmerLabel);
        
        GridPane updateFarmerForm = new GridPane();
        Farmer dbFarmer = new Farmer(dbID,dbfName,dblName,dbContact,dbAddress);
        System.out.println(dbFarmer.toString());
        
        Label textFarmerfName = new Label("First Name:");
        TextField farmerfName = new TextField(dbFarmer.getFirstName());
        
        Label textFarmerlName = new Label("Last Name:");
        TextField farmerlName = new TextField(dbFarmer.getLastName());
        
        Label textFarmerContact = new Label("Contact:");
        TextField farmerContact = new TextField(dbFarmer.getContact());
        
        Label textFarmerAddress = new Label("Address");
        TextField farmerAddress = new TextField(dbFarmer.getAddress());
        
        Button updateFarmerBtn = new Button("Update Details");
        updateFarmerBtn.setStyle("-fx-background-color:#556B2F; -fx-text-fill:#fff;");
        
        updateFarmerForm.setPadding(new Insets(50,50,50,50));
        updateFarmerForm.setVgap(10);
        updateFarmerForm.setAlignment(Pos.CENTER_LEFT);
        
        updateFarmerForm.add(textFarmerfName,0,0);
        updateFarmerForm.add(farmerfName,0,1);
        
        updateFarmerForm.add(textFarmerlName,0,2);
        updateFarmerForm.add(farmerlName,0,3);
        
        updateFarmerForm.add(textFarmerContact,0,4);
        updateFarmerForm.add(farmerContact,0,5);
        
        updateFarmerForm.add(textFarmerAddress,0,6);
        updateFarmerForm.add(farmerAddress,0,7);
        updateFarmerForm.add(updateFarmerBtn,0,9);
        
        farmerDashboard.setLeft(updateFarmerForm);
        
        GridPane addProduceForm = new GridPane();
        
        Label textProduceName = new Label("Produce Name:");
        TextField produceName = new TextField();
        
        Label textProduceType = new Label("Produce Type:");
        TextField produceType = new TextField();
        
        Label textProducePrice = new Label("Produce Price:");
        TextField producePrice = new TextField();
        
        Label textProduceQuantity = new Label("Produce Quantity:");
        TextField produceQuantity = new TextField();
        
        Button addProduceBtn = new Button("Post Produce");
        addProduceBtn.setStyle("-fx-background-color:#556B2F; -fx-text-fill:#fff;");
        
        addProduceForm.setPadding(new Insets(50,50,50,50));
        addProduceForm.setVgap(10);
        addProduceForm.setAlignment(Pos.CENTER_RIGHT);
        String produceID = "";
        addProduceForm.add(textProduceName,0,0);
        addProduceForm.add(produceName,0,1);
        
        addProduceForm.add(textProduceType,0,2);
        addProduceForm.add(produceType,0,3);
        
        addProduceForm.add(textProducePrice,0,4);
        addProduceForm.add(producePrice,0,5);
        
        addProduceForm.add(textProduceQuantity,0,6);
        addProduceForm.add(produceQuantity,0,7);
        addProduceForm.add(addProduceBtn,0,9);
        
        farmerDashboard.setRight(addProduceForm);
               
//      End of Farmer Dashboard

        
//      Scenes
        Scene welcomeScene = new Scene(welcomeBorder);
        Scene consumerDashboardScene = new Scene(consumerDashboard);
        Scene farmerDashboardScene = new Scene(farmerDashboard);
//      End of Scenes        
        
//      Events
        farmerButton.setOnAction(e -> primaryStage.setScene(farmerDashboardScene));
        
        consumerButton.setOnAction(e -> primaryStage.setScene(consumerDashboardScene));
        
        updateFarmerBtn.setOnAction(e -> {
            
            dbFarmer.setFirstName(farmerfName.getText());
            dbFarmer.setLastName(farmerlName.getText());
            dbFarmer.setContact(farmerContact.getText());
            dbFarmer.setAddress(farmerAddress.getText());
            System.out.println(dbFarmer.toString());
                
            try{    
                ConnectionClass connectionClass = new ConnectionClass();
                Connection con = connectionClass.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE farmer SET firstName = '" + dbFarmer.getFirstName() + "', lastName = '" + dbFarmer.getLastName() + "', contact = '" + dbFarmer.getContact() + "', address = '" + dbFarmer.getAddress() + "' WHERE ID = '" + dbFarmer.getID() + "'");
                System.out.println("Suceess");
            } catch (SQLException ex) {
                System.out.println("Failed");
            }
        });
        
        updateConsumerBtn.setOnAction(e -> {
            
            dbConsumer.setFirstName(consumerfName.getText());
            dbConsumer.setLastName(consumerlName.getText());
            dbConsumer.setContact(consumerContact.getText());
            dbConsumer.setAddress(consumerAddress.getText());
            System.out.println(dbConsumer.toString());
                
            try{    
                ConnectionClass connectionClass = new ConnectionClass();
                Connection con = connectionClass.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE consumer SET firstName = '" + dbConsumer.getFirstName() + "', lastName = '" + dbConsumer.getLastName() + "', contact = '" + dbConsumer.getContact() + "', address = '" + dbConsumer.getAddress() + "' WHERE ID = '" + dbConsumer.getID() + "'");
                System.out.println("Suceess");
            } catch (SQLException ex) {
                System.out.println("Failed");
            }
        });
        
        addProduceBtn.setOnAction(e -> {
            
            Produce dbProduce = new Produce(produceID, produceName.getText(),produceType.getText(),producePrice.getText(),produceQuantity.getText(),dbFarmer.getID());
            System.out.println(dbProduce.toString());
            
            produceName.setText("");
            produceType.setText("");
            producePrice.setText("");
            produceQuantity.setText("");
            
            try{    
                ConnectionClass connectionClass = new ConnectionClass();
                Connection con = connectionClass.getConnection();
                Statement stmt = con.createStatement();
                stmt.executeUpdate("INSERT INTO produce (name, type, price, quantity, farmer) VALUES('" + dbProduce.getName() + "', '" + dbProduce.getType() + "', '" + dbProduce.getPrice() + "', '" + dbProduce.getQuantity() + "', '" + dbProduce.getFarmer() + "')");
                System.out.println("Success");
            } catch (SQLException ex) {
                System.out.println("Failed");
            }
            
        });
        
        ArrayList<String> selectedProduces = new ArrayList<String>();
        ArrayList<Integer> selectedPrices = new ArrayList<Integer>();
        ArrayList<String> selectedFarmers = new ArrayList<String>();
        
        buyButton.setOnAction(e -> {
           StringJoiner prod =  new StringJoiner(System.lineSeparator());
            int totalPrice = 0;
        
            //      Buy Popup
            Popup buyPopup = new Popup();
            GridPane buyPane = new GridPane();
            buyPane.setStyle("-fx-background-color: white;");
            buyPane.setMinHeight(200);
            buyPane.setMinWidth(200);
            buyPane.setPadding(new Insets(50,50,50,50));
            Label buyLabel = new Label("Things you want to buy:");
            buyPane.add(buyLabel, 0, 0);

            for(String produce:selectedProduces){
                prod.add(produce);
            }

            Label buyProduce = new Label();
            buyProduce.setText(prod.toString());
            buyPane.add(buyProduce, 0, 1);

            Label priceLabel = new Label("Total Price:");
            buyPane.add(priceLabel, 0, 3);

            for(Integer price:selectedPrices){
                totalPrice += price;
            }

            Label buyPrice = new Label("Ksh: " + String.valueOf(totalPrice));
            buyPane.add(buyPrice, 0, 4); 
            
            Label farmerLabel2 = new Label("Farmer Contact Info:");
            buyPane.add(farmerLabel2, 0, 6);
            
            Label buyFarmer = new Label(selectedFarmers.get(0));
            buyPane.add(buyFarmer, 0, 7);
            
            Button buyFinal = new Button("Buy");
            buyFinal.setFont(Font.font(14));
            buyFinal.setStyle("-fx-text-fill:#fff; -fx-background-color:#556B2F;");
            buyPane.add(buyFinal, 0, 9);
            
            buyFinal.setOnAction(a ->{
                buyPopup.hide();
                selectedProduces.clear();
                selectedPrices.clear();
                selectedFarmers.clear();
            });
            
            buyPopup.getContent().add(buyPane);
            
            buyPopup.show(primaryStage);
        });
        
        
        
        produceTable.setOnMousePressed(e ->{
            ObservableList <Produce> selectedProduce = produceTable.getSelectionModel().getSelectedItems();
            int selectedID = Integer.valueOf(selectedProduce.get(0).getID());
            String selectedFarmer = selectedProduce.get(0).getFarmer();
            int dbPrice;
            String dbProduceName;
            String dbFarmerContact;
            try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/farmthrive","root","");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT produce.name, produce.price,farmer.contact FROM produce,farmer WHERE produce.ID = " + selectedID + " AND produce.farmer = farmer.ID");
            System.out.println("Suceess");
            
            while(rs.next()){
                dbProduceName = rs.getString("produce.name");
                dbPrice = Integer.valueOf(rs.getString("produce.price"));
                dbFarmerContact = rs.getString("farmer.contact");
                selectedProduces.add(dbProduceName);
                selectedPrices.add(dbPrice);
                selectedFarmers.add(dbFarmerContact);
            }
            for(String c:selectedFarmers){
                System.out.println(c);
            }
            
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Failed");
        }
            
        });
        
//      Initializing Stage
        primaryStage.setTitle("FarmThrive");
        primaryStage.setScene(welcomeScene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    
    
}
