import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent }  from './app.component';
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";
import {AllianceService} from "./services/alliance-service";
import {AllianceForm} from "./alliance/addalliance.component";
import {EditAllianceComponent} from "./alliance/editalliance.component";
import {ConfirmComponent} from "./modal_window/modal";
import {RouterModule} from "@angular/router";
import {PlayerComponent} from "./player/player.component";
import {PlayerList} from "./player/playerList.component";
import {PlayerRow} from "./player/playerRow.component";
import {PlayerHeader} from "./player/playerHeader.component";
import {PlayerService} from "./services/player.service";
import {EditVillageComponent} from "./village/editViallage.component";
import {CurrVillageService} from "./services/currentVillage.service";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forRoot([
            {
                path:'player/:id',
                component: PlayerComponent
            },
            {
                path:'admin',
                component:AllianceComponent
            },
            {
                path: 'admin/',
                redirectTo:'admin',
                pathMatch:'full'
            },
            {
                path: 'user/:id',
                redirectTo:'player/:id',
                pathMatch:'full',

            },
            {
                path:'edit',
                component:EditVillageComponent,
            }
        ])
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
        EditVillageComponent
    ],
    providers: [
        AllianceService,
        ConfirmComponent,
        PlayerService,
        CurrVillageService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }