package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.User;
import sample.utils.NoConnectionException;
import sample.utils.RestApi;

public class RegistrationController {
    private Stage dialogStage;

    @FXML
    private TextField textLoginSign;

    @FXML
    private PasswordField passwordSign;

    @FXML
    private TextField textLoginReg;

    @FXML
    private PasswordField textPasswordReg;

    @FXML
    private PasswordField textAgainPasswordReg;

    private RestApi myApiSession = new RestApi();
    private Main main;
    private ObservableList<User> userData = FXCollections.observableArrayList();

    @FXML
    private void initialize(){};

    @FXML
    public void handleReg(){
        User newUser = new User(-1L, null, null, null, null, null, null);
        if (textPasswordReg.getText().equals(textAgainPasswordReg.getText())){
            newUser.setLogin(textLoginReg.getText());
            newUser.setPassword(textPasswordReg.getText());
        }
        try {
            System.out.println(newUser.toJson());
            myApiSession.createUser(newUser);
        } catch (NoConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет соединения");

            alert.showAndWait();
        }
    };

    @FXML
    public void handleEnter(){
        if(isInputValid()){
            try {
                userData = myApiSession.getUser();
            } catch (NoConnectionException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Ошибка");
                alert.setHeaderText("Нет соединения");

                alert.showAndWait();
            }
            for (User i: userData){
                if (i.getPassword().equals(passwordSign.getText())&i.getLogin().equals(textLoginSign.getText())){
                    main.showToDoMain();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(main.getPrimaryStage());
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Неверный пароль или логин");

                    alert.showAndWait();
                }
            }
        }
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (textLoginSign.getText() == null){
            errorMessage+=" Введите логин ";
        }
        if (passwordSign.getText() == null){
            errorMessage+=" Введите пароль ";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Показываем сообщение об ошибке.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Неверно введены некоторые поля");
            alert.setHeaderText("Введите поля корректно");
            alert.setContentText(errorMessage);

            alert.showAndWait();
            return false;
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getLogin(){
        return textLoginSign.getText();
    }
}
