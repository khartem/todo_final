package com.example.demo.SERVER.controllers;

import com.example.demo.SERVER.repository.TaskRepository;
import com.example.demo.SERVER.tables.Task;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @PostMapping("/addTask")
    Task createTask(@RequestBody Task task){
        return this.taskRepository.save(task);
    }

    @GetMapping("/getTask={id}")
    Task getTask(@PathVariable Long id){
        return this.taskRepository.findTaskById(id);
    }

    @GetMapping("/getTaskAll")
    List<Task> getTaskAll(){
        return this.taskRepository.findAll();
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id){
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));

    }

    @PutMapping("/updateTask/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskUpdate) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(taskUpdate.getName());
                    task.setDescription(taskUpdate.getDescription());
                    task.setDeadline(taskUpdate.getDeadline());
                    task.setCreated(taskUpdate.getCreated());
                    task.setUpdated(taskUpdate.getUpdated());
                    task.setUser(taskUpdate.getUser());
                    task.setCategory(taskUpdate.getCategory());
                    return taskRepository.save(task);
                }).orElseThrow(()-> new ResourceNotFoundException("not found" + id));
    }
}
