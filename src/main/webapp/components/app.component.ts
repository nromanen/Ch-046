import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";
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
    //
    // // the lang to use, if the lang isn't available, it will use the current loader to get them
    // translate.use('uk');
    console.log(translate.getBrowserLang());
    console.log(translate.getDefaultLang());
    let ca: Array<string> = document.cookie.split(';');
    console.log(ca);
    // translate.get('HELLO').subscribe((res: string) => {
    //     console.log(res);
    //     //=> 'hello world'
    // });
    // translate.get('Hello').subscribe(
    //     value => {
    //         // value is our translated string
    //         console.log(value);
    //     }
    // )
}
}