package com.example.demo.SERVER.tables;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "tasks")
public class Task {
    public Task(String name, String description,LocalDate deadline){
        this.name=name;
        this.description=description;
        this.deadline=deadline;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "taskid")
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private LocalDate deadline;

    @CreatedDate
    @Column
    private LocalDate created;

    @LastModifiedDate
    @Column
    private LocalDate updated;

    public Task(){}

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "category_like",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> category;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", created=" + created +
                ", updated=" + updated +
                ", user=" + user +
                ", category=" + category +
                '}';
    }
}
