package sample.models;

import javafx.beans.property.*;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Date;

public class User implements APIModel{
    private final StringProperty login;
    private final StringProperty surname;
    private final StringProperty name;
    private final StringProperty father_name;
    private final ObjectProperty<LocalDate> birthday;
    private final StringProperty password;
    private final LongProperty id;


    public User(Long id, String login, String surname,String name,String father_name,String password, LocalDate birthday) {
        this.id = new SimpleLongProperty(id);
        this.login = new SimpleStringProperty(login);
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.father_name = new SimpleStringProperty(father_name);
        this.password = new SimpleStringProperty(password);
        this.birthday = new SimpleObjectProperty<LocalDate>((LocalDate) birthday);
    }

    @Override
    public JSONObject toJson() {
        JSONObject map = new JSONObject();
        map.put("id", id.get());
        map.put("login", login.get());
        map.put("surname", surname.get());
        map.put("name", name.get());
        map.put("father_name", father_name.get());
        map.put("password", password.get());
        map.put("birthday", birthday.get());

        return map;
    }

    public String getLogin() {
        return login.get();
    }

    public StringProperty loginProperty() {
        return login;
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
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

    public String getFather_name() {
        return father_name.get();
    }

    public StringProperty father_nameProperty() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name.set(father_name);
    }

    public LocalDate getBirthday() {
        return birthday.get();
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
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

    public String getPassword() {
        return password.get();
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                ", surname=" + surname +
                ", name=" + name +
                ", father_name=" + father_name +
                ", birthday=" + birthday +
                ", password=" + password +
                ", id=" + id +
                '}';
    }
}

