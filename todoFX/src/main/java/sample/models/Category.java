package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;


public class Category implements APIModel{
    private final StringProperty name;
    private final LongProperty id;

    public Category(Long id, String name){
        this.id = new SimpleLongProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    @Override
    public JSONObject toJson() {
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("name", name.get());
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

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "name=" + name +
                ", id=" + id +
                '}';
    }
}
