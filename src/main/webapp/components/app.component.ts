import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";
import {Component} from "@angular/core";
import {CurrVillageArmiesService} from "./services/newVillageArmiesService";

@Component({
    selector: 'my-app',
    template: `
<h1 i18n>Hello!</h1>
         <router-outlet></router-outlet>
    `,
})

export class AppComponent {
constructor(currPlayerService:CurrVillageArmiesService){

}
}