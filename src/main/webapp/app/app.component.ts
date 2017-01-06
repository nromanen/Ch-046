import {Component} from 'angular2/core';
import {TodoListComponent} from './todo-list/todo-list.component'
import {AllianceComponent} from "./alliance/alliance.component";

@Component({
    selector: 'my-app',
    template: `
        <!--<my-todo-list></my-todo-list> -->
        <my-alliance></my-alliance>
    `,
    directives: [AllianceComponent]
})

export class AppComponent {

}