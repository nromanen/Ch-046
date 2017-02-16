import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule, Http} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RoutesModule} from "./app.routers";

import {AppComponent}  from './app.component';
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";


import {ConfirmComponent} from "./modal_window/modal";

import {PlayerComponent} from "./player/player.component";
import {PlayerList} from "./player/villages-list.component";
import {PlayerHeader} from "./header/player-header.component";
import {VillageInfoComponent} from "./village/village-info.component";
import {ArmyCellInfoComponent} from "./army/army-cell-info.component";

import {TimerComponent} from "./timer/timer.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {LeaderMainComponent} from "./leader/leader-main.component";

import {AllianceForm} from "./alliance/add-alliance.component";
import {MemberForm} from "./user/add-member.component";
import {EditMemberComponent} from "./user/edit-member.component";
import {ArmyCellComponent} from "./army/armyCellComponent";
import {VillageRow} from "./village/villageRow.component";
import {AddVillageForm} from "./village/addVillageForm";
import {AddArmyForm} from "./army/addArmyForm.component";
import {AllianceService} from "./services/alliance/alliance-service";
import {UserService} from "./services/user.service";
import {PlayerService} from "./services/player.service";
import {CurrVillageArmiesService} from "./services/newVillageArmiesService";
import {VillageService} from "./services/villageService";
import {HelpService} from "./services/helpNotification/help.service";
import {StompService} from "./services/helpNotification/stomp.service";

import {EditAllianceComponent} from "./alliance/edit-alliance.component";
import {CalendarModule} from 'primeng/primeng';
import {AllHelps} from "./help/all-helps.component";
import {HelpComponent} from "./help/ask-help.component";
import {TranslateModule, TranslateLoader, TranslateStaticLoader} from "ng2-translate";
import {CookieService} from "angular2-cookie/services/cookies.service";
import {AttackArchiveComponent} from "./help/attack-archive.component";
import {AttackArchiveService} from "./services/helpNotification/attack-archive.service";

import {ConfirmParsingComponent} from "./modal_parsing_window/modal";
import {ParserService} from "./services/parser.service";

export function HttpLoaderFactory(http: Http) {
    return new TranslateStaticLoader(http, 'assets/i18', '.json');
}

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RoutesModule,
        TranslateModule.forRoot({
            provide: TranslateLoader,
            useFactory: HttpLoaderFactory,
            deps: [Http]
        }),
        CalendarModule
    ],
    declarations: [
        AppComponent,
        AllianceComponent,
        HeaderComponent,
        AllianceForm,
        EditAllianceComponent,
        ConfirmComponent,
        PlayerComponent,
        PlayerList,
        PlayerHeader,
        TimerComponent,
        LeaderManagerComponent,
        LeaderMainComponent,
        MemberForm,
        EditMemberComponent,
        ArmyCellComponent,
        VillageRow,
        AddVillageForm,
        VillageInfoComponent,
        ArmyCellInfoComponent,
        AddArmyForm,
        HelpComponent,
        AllHelps,
        AttackArchiveComponent,
        ConfirmParsingComponent

    ],
    providers: [
        AllianceService,
        UserService,
        PlayerService,
        CurrVillageArmiesService,
        VillageService,
        AttackArchiveService,
        CookieService,
        HelpService,
        StompService,
        ParserService
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}