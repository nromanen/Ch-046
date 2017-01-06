System.register(['angular2/core', "./todo-list.service", './task'], function(exports_1, context_1) {
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
    var core_1, todo_list_service_1, task_1;
    var TodoListComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (todo_list_service_1_1) {
                todo_list_service_1 = todo_list_service_1_1;
            },
            function (task_1_1) {
                task_1 = task_1_1;
            }],
        execute: function() {
            TodoListComponent = (function () {
                function TodoListComponent(_taskService) {
                    this._taskService = _taskService;
                    this.newTaskTitle = '';
                    this.selectedTask = null;
                    this.deletedTask = null;
                    this.isActive = 'all';
                    console.log('constructor_TodoListComponent62');
                }
                TodoListComponent.prototype.addTask = function () {
                    if (this.newTaskTitle !== '') {
                        this._taskService.createPost({ title: this.newTaskTitle, completed: false }, this.isActive);
                        this.newTaskTitle = '';
                        this._taskService.setTasks(this.isActive);
                    }
                };
                TodoListComponent.prototype.onDelete = function (task) {
                    this._taskService.deleteTask(task, this.isActive);
                };
                TodoListComponent.prototype.toggleCompletion = function (task) {
                    var newTask = new task_1.Task(task.title, !task.completed, task.id);
                    this._taskService.updateTask(task, newTask, this.isActive);
                };
                TodoListComponent.prototype.onMoseOver = function (task) {
                    this.deletedTask = task;
                };
                TodoListComponent.prototype.onMoseLeave = function () {
                    this.deletedTask = null;
                };
                TodoListComponent.prototype.editTask = function (task) {
                    this.selectedTask = task;
                };
                TodoListComponent.prototype.updateTask = function (newTitle) {
                    var newTask = new task_1.Task(newTitle, this.selectedTask.completed, this.selectedTask.id);
                    this._taskService.updateTask(this.selectedTask, newTask, this.isActive);
                    this.selectedTask = null;
                };
                TodoListComponent.prototype.cancelEditingTask = function () {
                    this.selectedTask = null;
                };
                TodoListComponent.prototype.onActive = function (status) {
                    this.isActive = status;
                    if (status === 'completed') {
                        this._taskService.setTasks('completed');
                    }
                    else if (status === 'active') {
                        this._taskService.setTasks('active');
                    }
                    else
                        this._taskService.setTasks('all');
                };
                TodoListComponent.prototype.onDeleteAllCompleted = function () {
                    var forDelete = this._taskService.completedTasks;
                    for (var i = 0; i < forDelete.length; i++) {
                        this._taskService.deleteTask(forDelete[i], this.isActive);
                    }
                    this.onActive(this.isActive);
                };
                TodoListComponent = __decorate([
                    core_1.Component({
                        selector: 'my-todo-list',
                        template: "\n        <div class=\"todo\">\n            <h1>TODOS_USING_JAVA_BACKEND!!!</h1>\n            <input id=\"task-title\" placeholder=\"What needs to be completed?\" [(ngModel)]=\"newTaskTitle\" autofocus (keyup.enter)=\"addTask()\">\n            <div class=\"list\">\n                <ul>\n                    <li  *ngFor=\"#task of _taskService.tasks\" (mouseover)=\"onMoseOver(task)\" (mouseleave)=\"onMoseLeave()\" (dblclick)=\"editTask(task)\">\n                        <input type=\"checkbox\" [checked]=\"task.completed\" (change)=\"toggleCompletion(task)\" *ngIf=\"task !== selectedTask\">\n                        <span [ngClass]=\"{completed: task.completed}\" *ngIf=\"task !== selectedTask\">{{ task.title }}</span>\n                        <input id=\"task-title-edit\" type=\"text\" *ngIf=\"task === selectedTask\" value=\"{{task.title}}\" #updateTitle (keyup.enter)=\"updateTask(updateTitle.value)\" (keyup.escape)=\"cancelEditingTask()\">\n                        <span *ngIf=\"task === deletedTask  && task !== selectedTask\" ><button class=\"delete\" (click)=\"onDelete(task)\">Delete</button></span>\n                    </li>\n                    <li *ngIf=\"_taskService.tasks.length === 0 && isActive !== 'completed'\"><strong>You do not have active tasks now. Let's create a new one task</strong></li>\n                    <li *ngIf=\"_taskService.tasks.length === 0 && isActive === 'completed'\"><strong>You do not have completed tasks now. Let's complete your tasks</strong></li>\n                </ul>\n            </div>\n            <div class=\"footer\">\n                <div class=\"link\" ><strong>{{_taskService.activeTasksCount}}</strong> {{_taskService.activeTasksCount == 1 ? 'item' : 'items'}} left</div>\n                <div class=\"link-center\">\n                    <a [ngClass]=\"{isActive: isActive === 'all'}\" (click)=\"onActive('all')\">All</a>\n                    <a [ngClass]=\"{isActive: isActive === 'active'}\" (click)=\"onActive('active')\">Active</a>\n                    <a [ngClass]=\"{isActive: isActive === 'completed'}\" (click)=\"onActive('completed')\">Completed</a>\n                </div>\n                <div class=\"link-right\" (click)=\"onDeleteAllCompleted()\" *ngIf=\"_taskService.completedTasks.length > 0\"><a>Clear completed</a></div>\n            </div>\n        </div>\n    ",
                        providers: [todo_list_service_1.TaskService],
                    }), 
                    __metadata('design:paramtypes', [todo_list_service_1.TaskService])
                ], TodoListComponent);
                return TodoListComponent;
            }());
            exports_1("TodoListComponent", TodoListComponent);
        }
    }
});
//# sourceMappingURL=todo-list.component.js.map