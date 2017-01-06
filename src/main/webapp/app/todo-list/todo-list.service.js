System.register(['angular2/core', 'angular2/http', 'rxjs/Rx'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1;
    var TaskService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            TaskService = (function () {
                function TaskService(_http) {
                    this._http = _http;
                    this.tasks = [];
                    this.activeTasks = [];
                    this.completedTasks = [];
                    this.allTasks = [];
                    this.activeTasksCount = 0;
                    this.getTasks('all');
                }
                TaskService.prototype.getCookie = function (name) {
                    var value = "; " + document.cookie;
                    var parts = value.split("; " + name + "=");
                    if (parts.length == 2)
                        return parts.pop().split(";").shift();
                };
                TaskService.prototype.getTasks = function (status) {
                    var _this = this;
                    console.log("it works");
                    this._http.get('/tasks?type=all12')
                        .map(function (res) { return res.json(); })
                        .subscribe(function (response) {
                        console.log(response);
                        _this.allTasks = response;
                        _this.setTasks(status);
                    }, function (error) { return console.log(error); });
                };
                TaskService.prototype.createPost = function (post, status) {
                    var body = JSON.stringify({ title: post.title, completed: post.completed });
                    console.log(post);
                    console.log(body);
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('X-CSRFToken', this.getCookie('csrftoken'));
                    this._http.post('/tasks', body, {
                        headers: headers
                    }).map(function (res) { return res.json(); })
                        .subscribe(function (response) { return console.log('Task created successful'); }, function (error) { return console.log(error); });
                    this.getTasks(status);
                    this.getTasks(status);
                };
                TaskService.prototype.deleteTask = function (task, status) {
                    this.allTasks.splice(this.allTasks.indexOf(task), 1);
                    this.setTasks(status);
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('X-CSRFToken', this.getCookie('csrftoken'));
                    var url = '/tasks/' + task.id;
                    this._http.delete(url, {
                        headers: headers
                    }).subscribe(function (response) { return console.log('Task deleted, id = ' + task.id); }, function (error) { return console.log(error); });
                };
                TaskService.prototype.updateTask = function (task, newTask, status) {
                    this.allTasks[this.allTasks.indexOf(task)] = newTask;
                    this.setTasks(status);
                    var body = JSON.stringify({ title: newTask.title, completed: newTask.completed });
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('X-CSRFToken', this.getCookie('csrftoken'));
                    var url = '/tasks/' + task.id + '/?format=json';
                    console.log(url);
                    this._http.put(url, body, {
                        headers: headers
                    }).subscribe(function (response) { return console.log('Task updated, id = ' + task.id); }, function (error) { return console.log(error); });
                };
                TaskService.prototype.getWithCompleted = function (completed) {
                    return this.allTasks.filter(function (task) { return task.completed === completed; });
                };
                TaskService.prototype.getActive = function () {
                    this.completedTasks = this.getWithCompleted(true);
                    this.activeTasks = this.getWithCompleted(false);
                    this.activeTasksCount = this.activeTasks.length;
                };
                TaskService.prototype.setTasks = function (status) {
                    this.getActive();
                    if (status === "completed") {
                        this.tasks = this.completedTasks;
                    }
                    else if (status === "active") {
                        this.tasks = this.activeTasks;
                    }
                    else {
                        this.tasks = this.allTasks;
                    }
                };
                TaskService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], TaskService);
                return TaskService;
            }());
            exports_1("TaskService", TaskService);
        }
    }
});
//# sourceMappingURL=todo-list.service.js.map