package ui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.Client;
import model.Employee;
import model.Product;
import model.RestaurantManager;

import java.io.IOException;

public class RestaurantAdministratorGUI {

    private RestaurantManager manager;

    public final static String SIGNUP_FXML = "signup.fxml";

    public final static String LOGIN_FXML = "login.fxml";

    public final static String ADDMENU_FXML = "addmenu.fxml";

    public final static String EDITMENU_FXML = "editmenu.fxml";

    public final static String ADDEMPLOYEE_FXML = "addemployee.fxml";

    public final static String ADDCLIENT_FXML = "addclient.fxml";

    public final static String ADDPRODUCT_FXML = "addproduct.fxml";

    public final static String ADDINGREDIENT_FXML = "addingredient.fxml";

    public final static String ADDTYPE_FXML = "addtype.fxml";

    @FXML
    private BorderPane bpPaneMain;

    @FXML
    private BorderPane bpPaneAdd;

    @FXML
    private MenuBar mbMenuMain;

    @FXML
    private VBox vbMainPane;

    @FXML
    private JFXTextField tfUserLogin;

    @FXML
    private JFXPasswordField pfPasswordLogin;

    @FXML
    private JFXTextField tfUserSignup;

    @FXML
    private JFXPasswordField pfPassword1Signup;

    @FXML
    private JFXPasswordField pfPassword2Signup;

    @FXML
    private JFXTextField tfFirstNameSignup;

    @FXML
    private JFXTextField tfLastNameSignup;

    @FXML
    private JFXTextField tfIdentificationSignup;

    @FXML
    private JFXTextField tfCodeOrder;

    @FXML
    private JFXTextField tfDateOrder;

    @FXML
    private JFXComboBox<String> cbEmployeeOrder;

    @FXML
    private JFXComboBox<String> cbClientOrder;

    @FXML
    private JFXComboBox<String> cbProductOrder;

    @FXML
    private JFXComboBox<String> cbSizeOrder;

    @FXML
    private JFXTextField tfAmountOrder;

    @FXML
    private JFXTextField tfUPriceOrder;

    @FXML
    private JFXTextField tfTPriceOrder;

    @FXML
    private TableView<?> tvOrdersOrder;

    @FXML
    private JFXComboBox<String> cbTypeAdd;

    @FXML
    private JFXTextField tfFirstNameAddClient;

    @FXML
    private JFXTextField tfLastNameAddClient;

    @FXML
    private JFXTextField tfIdAddClient;

    @FXML
    private JFXTextField tfAddressAddClient;

    @FXML
    private JFXTextField tfTelAddClient;

    @FXML
    private JFXTextArea taObservationsAddClient;

    @FXML
    private JFXTextField tfFirstNameAddEmployee;

    @FXML
    private JFXTextField tfLastNameAddEmployee;

    @FXML
    private JFXTextField tfIdAddEmployee;

    @FXML
    private JFXTextField tfNameAddIngredient;

    @FXML
    private JFXTextField tfNameAddType;

    public RestaurantAdministratorGUI(){
        manager = new RestaurantManager();
    }
    public void setFirstPane() throws IOException {

    }
    public BorderPane getMainPanePrincipal() {
        return bpPaneMain;
    }

    @FXML
    public void actLoginLogin(ActionEvent event) throws IOException{
        String userName = tfUserLogin.getText();
        String password = pfPasswordLogin.getText();
        if(!userName.equals("") && !password.equals("")){
            if(manager.activeUser(userName, password)){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order-menu.fxml"));
                fxmlLoader.setController(this);
                Parent ordermenu = fxmlLoader.load();
                bpPaneMain.setCenter(ordermenu);
                mbMenuMain.setVisible(true);
                mbMenuMain.setDisable(false);
                setupOrderScreen();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect data");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void actSignupLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SIGNUP_FXML));
        fxmlLoader.setController(this);
        Parent signUp = fxmlLoader.load();

        bpPaneMain.setCenter(signUp);
    }

    @FXML
    public void actBackSignup(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(LOGIN_FXML));
        fxmlLoader.setController(this);
        Parent signIn = fxmlLoader.load();

        bpPaneMain.setCenter(signIn);
    }

    @FXML
    public void actSignupSignup(ActionEvent event) throws IOException {
        String firstName = tfFirstNameSignup.getText();
        String lastName = tfLastNameSignup.getText();
        String id = tfIdentificationSignup.getText();
        String userName = tfUserSignup.getText();
        String password = pfPassword1Signup.getText();
        String passwordVer = pfPassword2Signup.getText();
        boolean passValid = true;
        if(password.equals(passwordVer)){
            passValid = false;
        }
        boolean created = manager.addUser(firstName, lastName, id, userName, password);
        if((!firstName.equals("")&&!lastName.equals("")&&!id.equals("")
        &&!userName.equals("")&&!password.equals(""))&&created &&!passValid){
            manager.saveUsersData();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("User created successfully");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Revisa que los campos hayan sido llenados correctamente y que las contraseñas sean iguales");
            alert.showAndWait();
        }
    }

    public void setupOrderScreen(){
        for(int a = 0; a<manager.getEmployees().size(); a++){
            cbEmployeeOrder.getItems().add(manager.getEmployees().get(a).getFirstName() + " " + manager.getEmployees().get(a).getLastName());
        }
        for(int b = 0; b<manager.getClients().size(); b++){
            cbClientOrder.getItems().add(manager.getClients().get(b).getFirstName() + " " + manager.getClients().get(b).getLastName());
        }
        for(int c = 0; c<manager.getProducts().size(); c++){
            cbProductOrder.getItems().add(manager.getProducts().get(c).getName());
        }
    }


    @FXML
    public void miAboutMain(ActionEvent event) {

    }

    @FXML
    public void miAddItemsMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(ADDMENU_FXML));
        fxmlLoader.setController(this);
        Parent addmenu = fxmlLoader.load();

        bpPaneMain.setCenter(addmenu);
        setupAddItemsScreen();
    }

    public void setupAddItemsScreen(){
        ObservableList<String> options = FXCollections.observableArrayList(
                "Empleado",
                "Cliente",
                "Producto",
                "Ingrediente",
                "Tipo de comida"
        );
        cbTypeAdd.getItems().setAll(options);
    }

    @FXML
    public void miAddOrderMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("order-menu.fxml"));
        fxmlLoader.setController(this);
        Parent ordermenu = fxmlLoader.load();
        bpPaneMain.setCenter(ordermenu);

        setupOrderScreen();
    }

    @FXML
    public void miEditItemsMain(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(EDITMENU_FXML));
        fxmlLoader.setController(this);
        Parent editmenu = fxmlLoader.load();

        bpPaneMain.setCenter(editmenu);
    }

    @FXML
    public void actAddOrderOrder(ActionEvent event) {

    }

    @FXML
    public void actDeleteOrder(ActionEvent event) {

    }

    @FXML
    public void actInfoOrder(ActionEvent event) {

    }

    @FXML
    public void actDisplayAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader;
        switch (cbTypeAdd.getSelectionModel().getSelectedItem().toString()){
            case("Empleado"):
                fxmlLoader = new FXMLLoader(getClass().getResource(ADDEMPLOYEE_FXML));
                fxmlLoader.setController(this);
                Parent addemployee = fxmlLoader.load();

                bpPaneAdd.setCenter(addemployee);
            break;
            case("Cliente"):
                fxmlLoader = new FXMLLoader(getClass().getResource(ADDCLIENT_FXML));
                fxmlLoader.setController(this);
                Parent addclient = fxmlLoader.load();

                bpPaneAdd.setCenter(addclient);
            break;
            case("Producto"):
                fxmlLoader = new FXMLLoader(getClass().getResource(ADDPRODUCT_FXML));
                fxmlLoader.setController(this);
                Parent addproduct = fxmlLoader.load();

                bpPaneAdd.setCenter(addproduct);
            break;
            case("Ingrediente"):
                fxmlLoader = new FXMLLoader(getClass().getResource(ADDINGREDIENT_FXML));
                fxmlLoader.setController(this);
                Parent addingredient = fxmlLoader.load();

                bpPaneAdd.setCenter(addingredient);
            break;
            case("Tipo de comida"):
                fxmlLoader = new FXMLLoader(getClass().getResource(ADDTYPE_FXML));
                fxmlLoader.setController(this);
                Parent addtype = fxmlLoader.load();

                bpPaneAdd.setCenter(addtype);
            break;
        }
    }

    @FXML
    public void actAddClientAddClient(ActionEvent event) {
        String firstName = tfFirstNameAddClient.getText();
        String lastName = tfLastNameAddClient.getText();
        String id = tfIdAddClient.getText();
        String address = tfAddressAddClient.getText();
        if(address.equals(null)){
            address = "";
        }
        String tel = tfTelAddClient.getText();
        if(tel.equals(null)){
            tel = "";
        }
        String observations = taObservationsAddClient.getText();
        if(observations.equals(null)){
            observations = "";
        }
        manager.addClient(firstName, lastName, id, address, tel, observations);
    }

    @FXML
    public void actAddEmployeeAddEmployee(ActionEvent event) {
        String firstName = tfFirstNameAddEmployee.getText();
        String lastName = tfLastNameAddEmployee.getText();
        String id = tfIdAddEmployee.getText();
        manager.addEmployee(firstName, lastName, id);
    }

    @FXML
    public void actAddIngredientAddIngredient(ActionEvent event) {
        String name = tfNameAddIngredient.getText();
        manager.addIngredient(name);
    }

    @FXML
    public void actAddTypeAddType(ActionEvent event) {
        String name = tfNameAddType.getText();
        manager.addFoodType(name);
    }

}
