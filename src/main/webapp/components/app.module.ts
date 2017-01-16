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
import {RouterModule,ActivatedRoute} from "@angular/router";
import {PlayerComponent} from "./player/player.component";
import {PlayerList} from "./player/playerList.component";
import {PlayerRow} from "./player/playerRow.component";
import {PlayerHeader} from "./player/playerHeader.component";
import {PlayerService} from "./services/player.service";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule.forRoot([
            {
                path:'player',
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
                path: 'user',
                redirectTo:'player',
                pathMatch:'full'
            },
            {
                path:'user/:id',
                component:PlayerComponent
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
        ActivatedRoute
    ],
    providers: [
        AllianceService,
        ConfirmComponent,
        PlayerService

    ],
    bootstrap: [AppComponent]
})

export class AppModule { }