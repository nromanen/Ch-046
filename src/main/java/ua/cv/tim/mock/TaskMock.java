package ua.cv.tim.mock;



import ua.cv.tim.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rmochetc on 05.01.2017.
 */
public class TaskMock {

    private static List<Task> tasks = new ArrayList<>();
    private static long counter = 1;

    static {

        tasks.add(new Task(counter++, "Create todo",false));
        tasks.add(new Task(counter++, "Create html parser for travian",false));
        tasks.add(new Task(counter++, "Learn Sprig",false));
        tasks.add(new Task(counter++, "Learn Sprig + Angular",true));
        tasks.add(new Task(counter++, "I use Mock",true));
    }

    public static void addTask(Task task){

        task.setId(counter++);

        System.out.println(task);

        tasks.add(task);

        System.out.println(tasks);
    }

    public static List<Task> getTasks() {
        return tasks;
    }

    public static void updateTask(Task task){

        tasks.set(getIdByTaskId(task.getId()),task);
    }

    public static void delete(long id){
        tasks.remove(getIdByTaskId(id));
    }

    private static int getIdByTaskId(long id){
        for (int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    public static Task getById(long id){
        for (Task task: tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }
}
