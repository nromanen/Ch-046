import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";
import {Component} from "@angular/core";

@Component({
    selector: 'my-app',
    template: `
        <!--<my-header></my-header>-->
        <!--<my-alliance></my-alliance>-->
        <!--<a routerLink="/player">Player</a>-->
   <router-outlet></router-outlet>
   <!--<a routerLink="/admin">Alliance</a>-->
   <router-outlet></router-outlet>
    `,
})

export class AppComponent {

}