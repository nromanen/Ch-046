import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";
import {Component} from "@angular/core";
import {CurrVillageArmiesService} from "./services/newVillageArmiesService";

@Component({
    selector: 'my-app',
    template: `
   <router-outlet></router-outlet>
   <!--<router-outlet name="editRouter"></router-outlet>-->
    `,
})

export class AppComponent {
constructor(currPlayerService:CurrVillageArmiesService){

}
}