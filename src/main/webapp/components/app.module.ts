import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {RoutesModule} from "./app.routers";

import { AppComponent }  from './app.component';
import {AllianceComponent} from "./alliance/alliance.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {HeaderComponent} from "./header/header.component";
import {AllianceForm} from "./alliance/addalliance.component";
import {EditAllianceComponent} from "./alliance/editalliance.component";
import {ConfirmComponent} from "./modal_window/modal";
import {MemberForm} from './user/add-member.component';
import {EditMemberComponent} from './user/edit-member.component';

import {AllianceService} from "./services/alliance-service";
import {UserService} from "./services/user.service";

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
        LeaderManagerComponent,
        MemberForm,
        EditMemberComponent,
    ],
    providers: [
        AllianceService,
        ConfirmComponent,
        UserService

    ],
    bootstrap: [AppComponent]
})

export class AppModule { }