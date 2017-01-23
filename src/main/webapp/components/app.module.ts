import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent }  from './app.component';
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";

import {AllianceForm} from "./alliance/addalliance.component";
import {EditAllianceComponent} from "./alliance/editalliance.component";
import {ConfirmComponent} from "./modal_window/modal";
import {RoutesModule} from "./app.routers";
import {PagerService} from "./services/pager.service";
import {AllianceService} from "./services/alliance/alliance-service";
import {TimerComponent} from "./timer/timer.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {MemberForm} from "./user/add-member.component";
import {UserService} from "./services/user.service";
import {AllianceMembersComponent} from "./user/alliance-members.component";
import {EditMemberComponent} from "./user/edit-member.component";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        FormsModule,
        ReactiveFormsModule,
        RoutesModule,
    ],
    declarations: [
        AppComponent,
        AllianceComponent,
        HeaderComponent,
        AllianceForm,
        EditAllianceComponent,
        ConfirmComponent,
        TimerComponent,
        LeaderManagerComponent,
        MemberForm,
        AllianceMembersComponent,
        EditMemberComponent

    ],
    providers: [
        AllianceService,
        PagerService,
        UserService
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }