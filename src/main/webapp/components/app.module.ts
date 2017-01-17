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
import {RoutesModule} from "./app.routers";
import {Users} from "./users/users.component";
import {Village} from "./vailage/village.componrnt";
import {PagerService} from "./services/pager.service";

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
        Users,
        Village

    ],
    providers: [
        AllianceService,
        PagerService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }