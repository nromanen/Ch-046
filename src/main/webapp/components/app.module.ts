import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RoutesModule} from "./app.routers";
import {AppComponent} from "./app.component";
import {AllianceComponent} from "./alliance/alliance.component";
import {HeaderComponent} from "./header/header.component";
import {AllianceForm} from "./alliance/add-alliance.component";
import {EditAllianceComponent} from "./alliance/edit-alliance.component";
import {ConfirmComponent} from "./modal_window/modal";
import {PlayerComponent} from "./player/player.component";
import {PlayerList} from "./player/playerList.component";
import {PlayerRow} from "./player/playerRow.component";
import {PlayerHeader} from "./header/player-header.component";
import {TimerComponent} from "./timer/timer.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {MemberForm} from "./user/add-member.component";
import {EditMemberComponent} from "./user/edit-member.component";
import {ArmyCellComponent} from "./army/armyCellComponent";
import {VillageRow} from "./village/villageRow.component";
import {AddVillageForm} from "./village/addVillageForm";
import {AddArmyForm} from "./army/addArmyForm.component";
import {HelpComponent} from "./help/ask-help.component";
import {AllHelps} from "./help/all-helps.component";
import {AllianceService} from "./services/alliance/alliance-service";
import {UserService} from "./services/user.service";
import {PlayerService} from "./services/player.service";
import {CurrVillageArmiesService} from "./services/newVillageArmiesService";
import {VillageService} from "./services/villageService";
import {HelpService} from "./services/helpNotification/help.service";
import {StompService} from "./services/helpNotification/stomp.service";

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
        AddArmyForm,
        HelpComponent,
        AllHelps
    ],
    providers: [
        AllianceService,
        UserService,
        PlayerService,
        CurrVillageArmiesService,
        VillageService,

        HelpService,
        StompService
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}