
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
            translate.use(cookieService.get('locale-cookie'));

        }else{
            translate.use(window.navigator.language);
            console.log(window.navigator.language);
        }

    }
}