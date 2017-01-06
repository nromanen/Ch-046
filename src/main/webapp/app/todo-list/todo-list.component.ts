import {Component} from 'angular2/core'
import {TaskService} from "./todo-list.service";
import {Task} from './task';

@Component({
    selector: 'my-todo-list',
    template: `
        <div class="todo">
            <h1>TODOS_USING_JAVA_BACKEND!!!</h1>
            <input id="task-title" placeholder="What needs to be completed?" [(ngModel)]="newTaskTitle" autofocus (keyup.enter)="addTask()">
            <div class="list">
                <ul>
                    <li  *ngFor="#task of _taskService.tasks" (mouseover)="onMoseOver(task)" (mouseleave)="onMoseLeave()" (dblclick)="editTask(task)">
                        <input type="checkbox" [checked]="task.completed" (change)="toggleCompletion(task)" *ngIf="task !== selectedTask">
                        <span [ngClass]="{completed: task.completed}" *ngIf="task !== selectedTask">{{ task.title }}</span>
                        <input id="task-title-edit" type="text" *ngIf="task === selectedTask" value="{{task.title}}" #updateTitle (keyup.enter)="updateTask(updateTitle.value)" (keyup.escape)="cancelEditingTask()">
                        <span *ngIf="task === deletedTask  && task !== selectedTask" ><button class="delete" (click)="onDelete(task)">Delete</button></span>
                    </li>
                    <li *ngIf="_taskService.tasks.length === 0 && isActive !== 'completed'"><strong>You do not have active tasks now. Let's create a new one task</strong></li>
                    <li *ngIf="_taskService.tasks.length === 0 && isActive === 'completed'"><strong>You do not have completed tasks now. Let's complete your tasks</strong></li>
                </ul>
            </div>
            <div class="footer">
                <div class="link" ><strong>{{_taskService.activeTasksCount}}</strong> {{_taskService.activeTasksCount == 1 ? 'item' : 'items'}} left</div>
                <div class="link-center">
                    <a [ngClass]="{isActive: isActive === 'all'}" (click)="onActive('all')">All</a>
                    <a [ngClass]="{isActive: isActive === 'active'}" (click)="onActive('active')">Active</a>
                    <a [ngClass]="{isActive: isActive === 'completed'}" (click)="onActive('completed')">Completed</a>
                </div>
                <div class="link-right" (click)="onDeleteAllCompleted()" *ngIf="_taskService.completedTasks.length > 0"><a>Clear completed</a></div>
            </div>
        </div>
    `,
    providers: [TaskService],
})

export class TodoListComponent{

    newTaskTitle: string = '';
    selectedTask: Task = null;
    deletedTask: Task = null;
    isActive: string = 'all';

    constructor(private _taskService: TaskService){
        console.log('constructor_TodoListComponent62');
    }

    addTask(){

        if (this.newTaskTitle!==''){
            this._taskService.createPost({title: this.newTaskTitle, completed: false}, this.isActive);
            this.newTaskTitle = '';
            this._taskService.setTasks(this.isActive);
        }
    }

    onDelete(task: Task){
        this._taskService.deleteTask(task, this.isActive);
    }

    toggleCompletion(task: Task){
               
        var newTask = new Task(task.title, !task.completed, task.id);
        this._taskService.updateTask(task, newTask, this.isActive);

    }

    onMoseOver(task: Task){
        this.deletedTask = task;
    }

    onMoseLeave(){
        this.deletedTask = null;
    }

    editTask(task: Task){
        this.selectedTask = task;
    }

    updateTask(newTitle: string){
        var newTask = new Task(newTitle, this.selectedTask.completed, this.selectedTask.id);
        this._taskService.updateTask(this.selectedTask, newTask, this.isActive);

        this.selectedTask = null;

    }

    cancelEditingTask(){
        this.selectedTask = null;
    }

    onActive(status: string){

        this.isActive = status;
        if (status === 'completed'){
            this._taskService.setTasks('completed');
        } else if (status === 'active'){
            this._taskService.setTasks('active');
        } else
            this._taskService.setTasks('all');
    }
    

    onDeleteAllCompleted(){
        var forDelete: Array<Task> = this._taskService.completedTasks;
        for(var i = 0; i < forDelete.length; i++){
            this._taskService.deleteTask(forDelete[i], this.isActive);
        }
        
        this.onActive(this.isActive);
                
    }

}