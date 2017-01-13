import {Component} from 'angular2/core';
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";

@Component({
    selector: 'my-app',
    template: `
        <my-header></my-header>
        <my-alliance></my-alliance>
    `,
    directives: [AllianceComponent, HeaderComponent]
})

export class AppComponent {

}