package sample.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Main;
import sample.models.Category;
import sample.models.Task;
import sample.models.User;

import java.time.LocalDate;

public class RestApi {
    private Main main;
    private static final String ServerURL = "http://localhost:8484";


    public void createUser(User user) throws NoConnectionException {
        HttpClass.PostRequest(ServerURL + "/user/addUser", user.toJson());
    }

    public ObservableList<User> getUser() throws NoConnectionException {
        ObservableList<User> result = FXCollections.observableArrayList();
        String buffer = HttpClass.GetRequest(ServerURL + "/user/getUserAll");

        if (buffer != null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentClient = jsonResult.getJSONObject(i);

                String login = currentClient.getString("login");
                String surname = null;
                String name = null;
                String father_name = null;
                LocalDate birthday = null;
                try{
                    surname = currentClient.getString("surname");
                }catch (JSONException ignored){

                }
                try{
                    name = currentClient.getString("name");
                }catch (JSONException ignored){

                }
                try{
                    father_name = currentClient.getString("father_name");
                }catch (JSONException ignored){

                }
                String password = currentClient.getString("password");
                try {
                    birthday = LocalDate.parse(currentClient.getString("birthday"));
                }catch (JSONException ignored){

                }
                Long id = currentClient.getLong("id");

                User newUser = new User(id, login, surname, name, father_name, password, birthday);
                result.add(newUser);
            }
        } else {
            throw new NoConnectionException("нет соединения");
        }
        return result;
    }

    public void updateUser(User user) throws NoConnectionException {
        Long id = user.getId();
        HttpClass.PutRequest(ServerURL + "/user/updateUser/" + id, user.toJson());
    }

    public boolean deleteUser(User user) throws NoConnectionException {
        Long id = user.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/user/deleteUser/" + id);
    }

    public void createTask(Task task) throws NoConnectionException {
        HttpClass.PostRequest(ServerURL + "/task/addTask", task.toJson());
    }

    public ObservableList<Task> getTask() throws NoConnectionException {
        ObservableList<Task> result = FXCollections.observableArrayList();
        String buffer = HttpClass.GetRequest(ServerURL + "/task/getTaskAll");

        if (buffer != null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentTask = jsonResult.getJSONObject(i);

                String name = currentTask.getString("name");
                String description = String.valueOf(currentTask.get("description"));
                LocalDate deadline = LocalDate.parse(currentTask.getString("deadline"));
                JSONObject user = currentTask.getJSONObject("user_id");
                User newTaskUser = null;
                try {
                    newTaskUser = new User(user.getLong("id"),
                            user.getString("login"),
                            user.getString("surname"),
                            user.getString("name"),
                            user.getString("father_name"),
                            user.getString("password"),
                            LocalDate.parse(user.getString("birthday")));
                } catch (JSONException e) {
                    newTaskUser = new User(user.getLong("id"),
                            user.getString("login"),
                            null,
                            null,
                            null,
                            user.getString("password"),
                            null);
                }
                Long id = currentTask.getLong("id");
                Task newTask = new Task(id, name, description, deadline, newTaskUser);
                result.add(newTask);
            }
        } else {
            throw new NoConnectionException("нет соединения");
        }
        return result;
    }

    public void updateTask(Task task) throws NoConnectionException {
        Long id = task.getId();
        HttpClass.PutRequest(ServerURL + "/task/updateTask/" + id, task.toJson());
    }

    public boolean deleteTask(Task task) throws NoConnectionException {
        Long id = task.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/task/deleteTask/" + id);
    }

    public void createCategory(Category category) throws NoConnectionException {
        HttpClass.PostRequest(ServerURL + "/category/addCategory", category.toJson());
    }

    public ObservableList<Category> getCategory() throws NoConnectionException {
        ObservableList<Category> result = FXCollections.observableArrayList();
        String buffer = HttpClass.GetRequest(ServerURL + "/category/getCategoryAll");

        if (buffer != null) {
            JSONArray jsonResult = new JSONArray(buffer);

            for (int i = 0; i < jsonResult.length(); i++) {
                JSONObject currentCategory = jsonResult.getJSONObject(i);

                String name = currentCategory.getString("name");
                Long id = currentCategory.getLong("id");

                Category newCategory = new Category(id, name);
                result.add(newCategory);
            }
        } else {
            throw new NoConnectionException("нет соединения");
        }
        return result;
    }

    public void updateCategory(Category category) throws NoConnectionException {
        Long id = category.getId();
        HttpClass.PutRequest(ServerURL + "/category/updateCategory/" + id, category.toJson());
    }

    public boolean deleteCategory(Category category) throws NoConnectionException {
        Long id = category.getId();
        if (id == null)
            return false;
        return HttpClass.DeleteRequest(ServerURL + "/category/deleteCategory/" + id);
    }
}
