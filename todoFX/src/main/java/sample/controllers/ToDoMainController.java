package sample.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Task;
import sample.utils.NoConnectionException;
import sample.utils.RestApi;

import java.time.LocalDate;

public class ToDoMainController {
    @FXML
    private TableView<Task> tableToDo;
    @FXML
    private TableColumn<Task, String> taskColumn;
    @FXML
    private TableColumn<Task, LocalDate> deadlineColumn;

    private RestApi myApiSession = new RestApi();
    private Main main;

    private ObservableList<Task> taskData = FXCollections.observableArrayList();
    public ToDoMainController(){}


    @FXML
    private void initialize(){
        taskColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        deadlineColumn.setCellValueFactory(cellData -> cellData.getValue().deadlineProperty());
    }


    public void addTaskData(){
        main.updateTaskTable();
        tableToDo.setItems(main.getTaskData());
    }

    @FXML
    private void handleAcceptTask() {
        int selectedIndex = tableToDo.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task currentTask = tableToDo.getItems().get(selectedIndex);
            try {
                if (myApiSession.deleteTask(currentTask)) {
                    tableToDo.getItems().remove(selectedIndex);
                }
                if (!myApiSession.deleteTask(currentTask)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(main.getPrimaryStage());
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Нет соединения");

                    alert.showAndWait();
                }
            } catch (NoConnectionException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Ошибка");
                alert.setHeaderText("Нет соединения");

                alert.showAndWait();
            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбрано клиента для удаления");
            alert.setContentText("Выберите клиента, которого хотите удалить");

            alert.showAndWait();
        }
    }

    public void loadData() {
        try {
            taskData = myApiSession.getTask();
        } catch (NoConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет соединения");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteTask(){
        int selectedIndex = tableToDo.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task currentTask = tableToDo.getItems().get(selectedIndex);
            try {
                if (myApiSession.deleteTask(currentTask)) {
                    tableToDo.getItems().remove(selectedIndex);
                }
                if (!myApiSession.deleteTask(currentTask)){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(main.getPrimaryStage());
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Нет соединения");

                    alert.showAndWait();
                }
            } catch (NoConnectionException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Ошибка");
                alert.setHeaderText("Нет соединения");

                alert.showAndWait();
            }
        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбрано клиента для удаления");
            alert.setContentText("Выберите клиента, которого хотите удалить");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewTask(){
        Task newTask = new Task( -1L, null, null, null, null);
        boolean okClicked = main.showTask(newTask);
        if (okClicked) {
            try {
                myApiSession.createTask(newTask);
            } catch (NoConnectionException e){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(main.getPrimaryStage());
                alert.setTitle("Ошибка");
                alert.setHeaderText("Нет соединения");

                alert.showAndWait();
            }
            loadData();
        }
    }

    @FXML
    private void handleEditTask() {
        Task selectedTask = tableToDo.getSelectionModel().getSelectedItem();
        if (selectedTask != null) {
            boolean okClicked = main.showTask(selectedTask);
            if (okClicked) {
                try {
                    myApiSession.updateTask(selectedTask);
                } catch (NoConnectionException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(main.getPrimaryStage());
                    alert.setTitle("Ошибка");
                    alert.setHeaderText("Нет соединения");

                    alert.showAndWait();
                }
                loadData();
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(main.getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Не выбран груз");
            alert.setContentText("Выберите груз для исправления");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleCancel() {
        System.out.println("категория");
        main.showCategory();
    }

    public void setMain(Main main){
        this.main = main;
        tableToDo.setItems(main.getTaskData());
    }




}
