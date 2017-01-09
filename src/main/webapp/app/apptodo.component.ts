import {Component} from 'angular2/core';
import {TodoListComponent} from './todo-list/todo-list.component'
import {HeaderComponent} from "./header/header.component";


@Component({
    selector: 'my-app-todo',
    template: `
        <my-header></my-header>
        <my-todo-list></my-todo-list> 
    `,
    directives: [TodoListComponent, HeaderComponent]
})

export class AppTodoComponent {

}