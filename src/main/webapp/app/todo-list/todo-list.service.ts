import {Injectable, provide} from 'angular2/core';
import {Http, Headers} from 'angular2/http';
import 'rxjs/Rx';
import {Task} from './task';


@Injectable()

export class TaskService{
    
    tasks: Array<Task> = [];
    activeTasks: Array<Task> = [];
    completedTasks: Array<Task> = [];
    allTasks: Array<Task> = [];
    activeTasksCount = 0;


    constructor(private _http: Http){

        this.getTasks('all');

    }

    private getCookie(name) {
    let value = "; " + document.cookie;
    let parts = value.split("; " + name + "=");
    if (parts.length == 2)
      return parts.pop().split(";").shift();
    }

    getTasks(status: string){
    console.log("it works");
        this._http.get('/tasks?type=all12')
                    .map(res => res.json())
                    .subscribe(
                        response => {
                        console.log(response);
                            this.allTasks = response;
                            this.setTasks(status);
                        },
                        error => console.log(error)
                    );
    }

    createPost(post: {title: string, completed: boolean}, status: string){

        const body = JSON.stringify({title: post.title, completed: post.completed});
        console.log(post);
        console.log(body);
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-CSRFToken', this.getCookie('csrftoken'));
        this._http.post('/tasks', body, {
            headers: headers
            }).map(res => res.json())
              .subscribe(
                  response => console.log('Task created successful'),
                  error => console.log(error)
        );

        this.getTasks(status);
        this.getTasks(status);

    }


    deleteTask(task: Task, status: string){

        this.allTasks.splice(this.allTasks.indexOf(task), 1);

        this.setTasks(status);

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-CSRFToken', this.getCookie('csrftoken'));
        const url = '/tasks/'+ task.id;
        this._http.delete(url, {
            headers: headers
        }).subscribe(
                response => console.log('Task deleted, id = ' + task.id),
                error => console.log(error)
        );

    }

    updateTask(task: Task, newTask: Task, status: string){

        this.allTasks[this.allTasks.indexOf(task)] = newTask;
        this.setTasks(status);

        const body = JSON.stringify({title: newTask.title, completed: newTask.completed});
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('X-CSRFToken', this.getCookie('csrftoken'));
        const url = '/tasks/'+ task.id + '/?format=json';
        console.log(url);
        this._http.put(url, body, {
            headers: headers
        }).subscribe(
                response => console.log('Task updated, id = ' + task.id),
                error => console.log(error)
        );
    }

    private getWithCompleted(completed: Boolean) {
		return this.allTasks.filter((task: Task) => task.completed === completed);
}

    private getActive(){

        this.completedTasks = this.getWithCompleted(true);
        this.activeTasks = this.getWithCompleted(false);
        this.activeTasksCount = this.activeTasks.length;

    }

    setTasks(status: string){
        this.getActive();
        if (status === "completed") {
            this.tasks = this.completedTasks;
        } else if (status === "active") {
            this.tasks = this.activeTasks;
        } else {
            this.tasks = this.allTasks;
        }
    }
}