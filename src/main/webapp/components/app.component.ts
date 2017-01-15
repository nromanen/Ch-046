import {Component} from 'angular2/core';
import {LeaderManageComponent} from "./leader/leader.manage.component";
import {HeaderComponent} from "./header/header.component";

@Component({
    selector: 'my-app',
    template: `
        <my-header></my-header>
        <leader-manage></leader-manage>
    `,
    directives: [LeaderManageComponent, HeaderComponent]
})

export class AppComponent {

}