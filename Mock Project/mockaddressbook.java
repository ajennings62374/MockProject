import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.event.ActionEvent;
import javafx.stage.WindowEvent;
import javafx.scene.control.ListView;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.scene.control.ButtonType;

public class mockaddressbook 
{
    private static TextField textFieldMessage; // must be able to access this in different methods
    private static TextField textFieldMessage2;
    private static TextField textFieldMessage3;
    private static TextField textFieldMessage4;
    private static TextField textFieldMessage5;
    private static TextField textFieldMessage6;
    private static Label label1;
    private static ListView<AddressBook> AddListView;
    private static ArrayList<AddressBook> AddArrList = new ArrayList<AddressBook>();
    private static AddressBook currentlySelectedAddress = null;
    public static void main(String args[])
    {
        launchFX();
    }

    private static void launchFX()
    {
        //Initialises JavaFX
        new JFXPanel();
        //runs initialisation on the javaFX thread
        Platform.runLater(() -> initialiseGUI());
    }

    private static void initialiseGUI()
    {
        Stage stage = new Stage();
        stage.setTitle("My Address Book");
        stage.setResizable(false);
        Pane rootPane = new Pane();
        stage.setScene(new Scene(rootPane));
        stage.setWidth(900);
        stage.setHeight(500);
        stage.show();
        stage.setOnCloseRequest((WindowEvent we) -> displayCloseDialog(we));

        AddListView = new ListView<AddressBook>();
        AddListView.setLayoutX(500);
        AddListView.setLayoutY(50);
        AddListView.setOnMouseClicked((MouseEvent me) -> {
                currentlySelectedAddress = AddListView.getSelectionModel().getSelectedItem();
            });
        rootPane.getChildren().add(AddListView);
        updateListView();

        Label label1 = new Label();
        label1.setLayoutX(50);
        label1.setLayoutY(50);
        label1.setText("Enter details...");
        rootPane.getChildren().add(label1);

        textFieldMessage = new TextField();
        textFieldMessage.setLayoutX(50);
        textFieldMessage.setLayoutY(100);
        textFieldMessage.setPrefWidth(200);
        textFieldMessage.setPromptText("Enter the person's name");
        rootPane.getChildren().add(textFieldMessage);

        textFieldMessage2 = new TextField();
        textFieldMessage2.setLayoutX(50);
        textFieldMessage2.setLayoutY(150);
        textFieldMessage2.setPrefWidth(200);
        textFieldMessage2.setPromptText("Enter House Number/Name");
        rootPane.getChildren().add(textFieldMessage2);

        textFieldMessage3 = new TextField();
        textFieldMessage3.setLayoutX(50);
        textFieldMessage3.setLayoutY(200);
        textFieldMessage3.setPrefWidth(200);
        textFieldMessage3.setPromptText("Enter Road");
        rootPane.getChildren().add(textFieldMessage3);

        textFieldMessage4 = new TextField();
        textFieldMessage4.setLayoutX(50);
        textFieldMessage4.setLayoutY(250);
        textFieldMessage4.setPrefWidth(200);
        textFieldMessage4.setPromptText("Enter Town");
        rootPane.getChildren().add(textFieldMessage4);

        textFieldMessage5 = new TextField();
        textFieldMessage5.setLayoutX(50);
        textFieldMessage5.setLayoutY(300);
        textFieldMessage5.setPrefWidth(200);
        textFieldMessage5.setPromptText("Enter County");
        rootPane.getChildren().add(textFieldMessage5);

        textFieldMessage6 = new TextField();
        textFieldMessage6.setLayoutX(50);
        textFieldMessage6.setLayoutY(350);
        textFieldMessage6.setPrefWidth(200);
        textFieldMessage6.setPromptText("Enter Postcode");
        rootPane.getChildren().add(textFieldMessage6);

        Button btn = new Button();
        btn.setText("Add new Item");
        btn.setLayoutX(50);
        btn.setLayoutY(400);
        btn.setOnAction((ActionEvent ae) -> addNewItem());
        rootPane.getChildren().add(btn);

        Button btndel = new Button();
        btndel.setText("Delete item");
        btndel.setLayoutX(150);
        btndel.setLayoutY(400);
        btndel.setOnAction((ActionEvent ae) -> deleteItem());
        rootPane.getChildren().add(btndel);

        ReadFilein();
    }

    private static void addNewItem(){        
        String name = textFieldMessage.getText();
        String housenum = textFieldMessage2.getText();
        String houseroad = textFieldMessage3.getText();
        String housetown = textFieldMessage4.getText();
        String housecounty = textFieldMessage5.getText();
        String housepostcode = textFieldMessage6.getText();

        AddArrList.add(new AddressBook(name, housenum, houseroad, housetown, housecounty, housepostcode));
        updateListView();
    }

    private static void updateListView(){
        AddListView.getItems().clear();
        for (AddressBook Add: AddArrList) { // for every object in arraylist
            AddListView.getItems().add(Add);
        }
        //textFieldMessage.setText(null);
    }

    private static  void ReadFilein(){
        try {
            String line = null;
            FileReader fr = new FileReader("AddressFile.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null){
                String[] splitline = line.split(",");
                AddArrList.add(new AddressBook(splitline[0], splitline[1], splitline[2], splitline[3], splitline[4], splitline[5]));
            }
            updateListView();
            br.close(); 
        }
        catch (IOException error) {
            System.out.println("Error");
        }
    }

    private static void deleteItem(){
        AddArrList.remove(currentlySelectedAddress);
        updateListView();
    }

    public static void displayCloseDialog(WindowEvent we){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Write to file");
        alert.setHeaderText("Write changes back to file");
        alert.setContentText("Do you want to write changes back to file?");
        int i;
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK)
        {
            try {
                i = 0;
                String content = null;
                FileWriter fw = new FileWriter("AddressFile.txt", false);
                BufferedWriter bw = new BufferedWriter(fw);
                 for (AddressBook AB: AddArrList){
                    content = AB.toString();
                    bw.newLine();
                    bw.write(content);
                    i++;
                }
                bw.close(); 
            }
            catch (IOException error) {
                System.out.println("Error");
            }
        }
        else
        {
            System.exit(0);
        }
    }
}