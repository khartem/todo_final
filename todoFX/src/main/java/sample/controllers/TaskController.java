package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Category;
import sample.models.Task;
import sample.models.User;
import sample.utils.NoConnectionException;
import sample.utils.RestApi;

public class TaskController {
    @FXML
    private TextField nameTask;
    @FXML
    private TextArea taskDesc;
    @FXML
    private DatePicker deadline;
    @FXML
    private ChoiceBox<String> taskCategory;

    private Stage dialogStage;
    private Task task;
    private Category category;
    private boolean okClicked = false;
    private Main main = new Main();
    private RestApi myApiSession = new RestApi();
    private ObservableList<Category> categoryData = FXCollections.observableArrayList();

    @FXML
    private void initialize(){}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setMain(Main main) {
        this.main = main;
    }



    public void setTask(Task task){
        this.task = task;
        nameTask.setText(task.getName());
        taskDesc.setText(task.getName());
        deadline.setValue(task.getDeadline());
        ObservableList<String> viewCategory = FXCollections.observableArrayList();
        try {
            categoryData = myApiSession.getCategory();
            System.out.println(categoryData.toString());
        } catch (NoConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет соединения");

            alert.showAndWait();
        }
        for (Category i: categoryData){
            viewCategory.add(i.getName());
        }
        taskCategory.setItems(viewCategory);
//        for (Category i: categoryData){
//            if (i.getName().equals(category.getName())){
//                taskCategory.setValue(i.getName());
//                break;
//            }
//        }
    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            task.setName(nameTask.getText());
            task.setDescription(taskDesc.getText());
            task.setDeadline(deadline.getValue());
            ObservableList<User> userData = FXCollections.observableArrayList();
            try {
                userData = myApiSession.getUser();
            } catch (NoConnectionException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Ошибка");
                alert.setHeaderText("Нет соединения");

                alert.showAndWait();
            }
            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (nameTask.getText() == null){
            errorMessage+=" Введите наименование задания ";
        }
        if (deadline.getValue().equals(null)){
            errorMessage+=" Введите дедлайн ";
        }
        if (taskCategory.getValue().equals(null)){
            errorMessage+=" Введите категорию ";
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
