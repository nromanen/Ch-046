import {AllianceComponent} from "./alliance/alliance.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {HeaderComponent} from "./header/header.component";
import {Component} from "@angular/core";

@Component({
    selector: 'my-app',
    template: `
        <my-header></my-header>
        <leader-manager></leader-manager>
    `,
})

export class AppComponent {

}