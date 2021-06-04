package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Category;
import sample.utils.NoConnectionException;
import sample.utils.RestApi;

public class CategoryController {
    @FXML
    private TextField textCategory;

    private Stage dialogStage;
    private boolean okClicked = false;
    private Main main;
    private RestApi myApiSession = new RestApi();

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private void handleCreate() {
        Category newCategory = new Category(-1L,null);
        if (isInputValid()) {
            newCategory.setName(textCategory.getText());
            try {
                myApiSession.createCategory(newCategory);
            } catch (NoConnectionException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Ошибка");
                alert.setHeaderText("Нет соединения");

                alert.showAndWait();
            }
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (textCategory.getText() == null || textCategory.getText().length() == 0) {
            errorMessage += "Введите название города!\n";
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
}
