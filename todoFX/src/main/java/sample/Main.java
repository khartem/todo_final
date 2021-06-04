package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controllers.CategoryController;
import sample.controllers.RegistrationController;
import sample.controllers.TaskController;
import sample.controllers.ToDoMainController;
import sample.models.Task;
import sample.utils.NoConnectionException;
import sample.utils.RestApi;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private RestApi myApiSession = new RestApi();
    private ObservableList<Task> taskData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("ToDo Java");
        showRegistration();


    }

    public void showRegistration() throws IOException{
        FXMLLoader loader = new FXMLLoader(new File("C:/Users/Artyom/Desktop/Финансовый университет/Второй курс/СТП/todoFX/src/main/java/sample/views/Enter.fxml").toURI().toURL());
        AnchorPane registration = loader.load();
        Scene scene = new Scene(registration);
        primaryStage.setScene(scene);
        RegistrationController controller = loader.getController();
        controller.setMain(this);
        primaryStage.show();

    }

    public void showToDoMain() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/Artyom/Desktop/Финансовый университет/Второй курс/СТП/todoFX/src" +
                    "/main/java/sample/views/ToDoMain.fxml").toURI().toURL());
            AnchorPane todomain = (AnchorPane) loader.load();
            Scene scene = new Scene(todomain);
            primaryStage.setScene(scene);

            ToDoMainController controller = loader.getController();
            controller.setMain(this);
            controller.addTaskData();
            primaryStage.show();

        } catch (IOException e) {
        }
    }

    public void updateTaskTable() {
        taskData.clear();
        try {
            taskData.addAll(myApiSession.getTask());
        } catch (NoConnectionException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(getPrimaryStage());
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет соединения");

            alert.showAndWait();
        }
    }

    public void showCategory() {
        try {
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/Artyom/Desktop/Финансовый университет/Второй курс/СТП/todoFX/src" +
                    "/main/java/sample/views/Category.fxml").toURI().toURL());
            AnchorPane category = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Category");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(category);
            dialogStage.setScene(scene);

            CategoryController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setMain(this);

            dialogStage.showAndWait();

        } catch (IOException e) {
        }
    }

    public boolean showTask(Task task) {
        try {
            FXMLLoader loader = new FXMLLoader(new File("C:/Users/Artyom/Desktop/Финансовый университет/Второй курс/СТП/todoFX/src" +
                    "/main/java/sample/views/Task.fxml").toURI().toURL());
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TaskController controllerEditDialog = loader.getController();
            controllerEditDialog.setDialogStage(dialogStage);
            controllerEditDialog.setTask(task);
            controllerEditDialog.setMain(this);

            dialogStage.showAndWait();

            return controllerEditDialog.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Task> getTaskData() {
        return taskData;
    }
}
