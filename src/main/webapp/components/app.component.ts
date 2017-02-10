
import {Component} from "@angular/core";
import {TranslateService} from "ng2-translate";
import {CookieService} from "angular2-cookie/services/cookies.service";

@Component({
    selector: 'my-app',
    template: `
         <router-outlet></router-outlet>
    `,
})

export class AppComponent {
constructor(private translate: TranslateService,cookieService:CookieService){
    if (cookieService.get('locale-cookie')!=null) {
        console.log('not null');

        translate.use(cookieService.get('locale-cookie'));

    }else{
        translate.use(window.navigator.language);
    }
    // translate.use(translate.getBrowserLang());
    console.log(translate.getBrowserLang());
    console.log(window.navigator.language);
    let ca: Array<string> = document.cookie.split(';');
    console.log(ca);
    console.log(cookieService.get('locale-cookie'));

}
}