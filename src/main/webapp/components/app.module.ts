﻿import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RoutesModule} from "./app.routers";

import {AppComponent}  from './app.component';
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";

import {EditAllianceComponent} from "./alliance/editalliance.component";
import {ConfirmComponent} from "./modal_window/modal";

import {PlayerComponent} from "./player/player.component";
import {PlayerList} from "./player/playerList.component";
import {PlayerRow} from "./player/playerRow.component";
import {PlayerHeader} from "./player/player-header.component";
import {ArmyCellComponent} from "./army/armyCellComponent";
import {VillageRow} from "./village/villageRow.component";
import {AddVillageForm} from "./village/addVillageForm";
import {AddArmyForm} from "./army/addArmyForm.component";

import {TimerComponent} from "./timer/timer.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {EditMemberComponent} from "./user/edit-member.component";

import {AllianceForm} from "./alliance/addalliance.component";
import {MemberForm} from "./user/add-member.component";
import {PagerService} from "./services/pager.service";
import {AllianceService} from "./services/alliance/alliance-service";
import {UserService} from "./services/user.service";
import {VillageService} from "./services/villageService";
import {CurrVillageArmiesService} from "./services/newVillageArmiesService";
import {PlayerService} from "./services/player.service";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RoutesModule
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
        PlayerRow,
        PlayerHeader,
        TimerComponent,
        LeaderManagerComponent,
        MemberForm,
        EditMemberComponent,
        ArmyCellComponent,
        VillageRow,
        AddVillageForm,
        AddArmyForm
    ],
    providers: [
        AllianceService,
        ConfirmComponent,
        PagerService,
        UserService,
        ConfirmComponent,
        PlayerService,
        CurrVillageArmiesService,
        VillageService

    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}