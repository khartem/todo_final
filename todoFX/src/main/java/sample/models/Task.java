package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

import java.time.LocalDate;

public class Task implements APIModel{
    private final StringProperty name;
    private final StringProperty description;
    private final ObjectProperty<LocalDate> deadline;
    private final LongProperty id;
    private User user_id;
    private Category category;

    public Task(Long id, String name, String description, Object deadline, User user_id) {
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.deadline = new SimpleObjectProperty<LocalDate>((LocalDate) deadline);
        this.user_id = user_id;
    }

    @Override
    public JSONObject toJson() {
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("name", name.get());
        map.put("description", description.get());
        map.put("deadline", deadline.get());
        map.put("user_id", user_id.toJson());

        return map;
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public LocalDate getDeadline() {
        return deadline.get();
    }

    public ObjectProperty<LocalDate> deadlineProperty() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline.set(deadline);
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name=" + name +
                ", description=" + description +
                ", deadline=" + deadline +
                ", id=" + id +
                ", user_id=" + user_id +
                '}';
    }
}
