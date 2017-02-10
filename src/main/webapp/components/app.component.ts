
import {Component} from "@angular/core";
import {TranslateService} from "ng2-translate";

@Component({
    selector: 'my-app',
    template: `
         <router-outlet></router-outlet>
    `,
})

export class AppComponent {
constructor(private translate: TranslateService){
    translate.setDefaultLang('en');
    // translate.use('uk');
    console.log(translate.getBrowserLang());
    console.log(translate.getDefaultLang());
    let ca: Array<string> = document.cookie.split(';');
    console.log(ca);

}
}