
import {Component} from "@angular/core";
import {HelpService} from "./services/helpNotification/help.service";
import {Alliance} from "./alliance/alliance";

@Component({
    selector: 'my-app',
    template: `
         <router-outlet></router-outlet>
    `,
})

export class AppComponent {
    public static alliance: Alliance;

constructor(private helpService:HelpService){

}

    public ngOnInit(): void {
               console.log("INIT APP_COMPONENT");
            this.helpService.getAlliance()
                .subscribe(
                    resp => {
                        console.log("APP_COMPONENT_SUBSCRIBE");
                        AppComponent.alliance = resp;
                        console.log(AppComponent.alliance);
                    }
                );
    }
}