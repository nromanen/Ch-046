package ua.cv.tim.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.cv.tim.model.Task;
import ua.cv.tim.service.TaskService;

import java.util.List;


/**
 * Created by rmochetc on 30.12.2016.
 */

@RestController
public class TestController {

    @Autowired
    private TaskService service;

    @RequestMapping(value = "/tasks", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Task>> listAllTasks() {

        System.out.println("GET TASKS");

        List<Task> tasks = service.getAll();
        if(tasks.isEmpty()){
            return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }

        System.out.println("!!!!!!!!!!!____________________!!!!!!!!!!!! TASKS=" + tasks);
        return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> createTask(@RequestBody Task task) {

        System.out.println("Creating Task " + task);

        service.addTask(task);

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> deleteTask(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting TAsk with id " + id);

        Task task = service.getById(id);
        if (task == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        service.deleteTask(id);
        return new ResponseEntity<Task>(HttpStatus.NO_CONTENT);

    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Task> updateUser(@PathVariable("id") long id, @RequestBody Task task) {
        System.out.println("Updating User " + id);

        Task currentTask = service.getById(id);

        if (currentTask==null) {
            System.out.println("Task with id " + id + " not found");
            return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
        }

        currentTask.setTitle(task.getTitle());
        currentTask.setCompleted(task.isCompleted());

        service.updateTask(currentTask);
        return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
    }



}