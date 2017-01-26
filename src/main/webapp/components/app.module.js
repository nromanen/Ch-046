System.register(['@angular/core', '@angular/platform-browser', '@angular/http', '@angular/forms', "./app.routers", './app.component', "./alliance/alliance.component", "./header/header.component", "./header/leader-header.component", "./alliance/edit-alliance.component", "./modal_window/modal", "./player/player.component", "./player/playerList.component", "./player/playerRow.component", "./header/player-header.component", "./player/player-list.component", "./village/village-info.component", "./army/army-cell-info.component", "./timer/timer.component", "./leader/leader-manager.component", "./leader/leader-main.component", "./alliance/add-alliance.component", "./user/add-member.component", "./user/edit-member.component", "./army/armyCellComponent", "./village/villageRow.component", "./village/addVillageForm", "./army/addArmyForm.component", "./help/ask-help.component", "./help/all-helps.component", "./services/alliance/alliance-service", "./services/user.service", "./services/player.service", "./services/newVillageArmiesService", "./services/villageService", "./services/helpNotification/help.service", "./services/helpNotification/stomp.service"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, platform_browser_1, http_1, forms_1, app_routers_1, app_component_1, alliance_component_1, header_component_1, leader_header_component_1, edit_alliance_component_1, modal_1, player_component_1, playerList_component_1, playerRow_component_1, player_header_component_1, player_list_component_1, village_info_component_1, army_cell_info_component_1, timer_component_1, leader_manager_component_1, leader_main_component_1, add_alliance_component_1, add_member_component_1, edit_member_component_1, armyCellComponent_1, villageRow_component_1, addVillageForm_1, addArmyForm_component_1, ask_help_component_1, all_helps_component_1, alliance_service_1, user_service_1, player_service_1, newVillageArmiesService_1, villageService_1, help_service_1, stomp_service_1;
    var AppModule;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (platform_browser_1_1) {
                platform_browser_1 = platform_browser_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (forms_1_1) {
                forms_1 = forms_1_1;
            },
            function (app_routers_1_1) {
                app_routers_1 = app_routers_1_1;
            },
            function (app_component_1_1) {
                app_component_1 = app_component_1_1;
            },
            function (alliance_component_1_1) {
                alliance_component_1 = alliance_component_1_1;
            },
            function (header_component_1_1) {
                header_component_1 = header_component_1_1;
            },
            function (leader_header_component_1_1) {
                leader_header_component_1 = leader_header_component_1_1;
            },
            function (edit_alliance_component_1_1) {
                edit_alliance_component_1 = edit_alliance_component_1_1;
            },
            function (modal_1_1) {
                modal_1 = modal_1_1;
            },
            function (player_component_1_1) {
                player_component_1 = player_component_1_1;
            },
            function (playerList_component_1_1) {
                playerList_component_1 = playerList_component_1_1;
            },
            function (playerRow_component_1_1) {
                playerRow_component_1 = playerRow_component_1_1;
            },
            function (player_header_component_1_1) {
                player_header_component_1 = player_header_component_1_1;
            },
            function (player_list_component_1_1) {
                player_list_component_1 = player_list_component_1_1;
            },
            function (village_info_component_1_1) {
                village_info_component_1 = village_info_component_1_1;
            },
            function (army_cell_info_component_1_1) {
                army_cell_info_component_1 = army_cell_info_component_1_1;
            },
            function (timer_component_1_1) {
                timer_component_1 = timer_component_1_1;
            },
            function (leader_manager_component_1_1) {
                leader_manager_component_1 = leader_manager_component_1_1;
            },
            function (leader_main_component_1_1) {
                leader_main_component_1 = leader_main_component_1_1;
            },
            function (add_alliance_component_1_1) {
                add_alliance_component_1 = add_alliance_component_1_1;
            },
            function (add_member_component_1_1) {
                add_member_component_1 = add_member_component_1_1;
            },
            function (edit_member_component_1_1) {
                edit_member_component_1 = edit_member_component_1_1;
            },
            function (armyCellComponent_1_1) {
                armyCellComponent_1 = armyCellComponent_1_1;
            },
            function (villageRow_component_1_1) {
                villageRow_component_1 = villageRow_component_1_1;
            },
            function (addVillageForm_1_1) {
                addVillageForm_1 = addVillageForm_1_1;
            },
            function (addArmyForm_component_1_1) {
                addArmyForm_component_1 = addArmyForm_component_1_1;
            },
            function (ask_help_component_1_1) {
                ask_help_component_1 = ask_help_component_1_1;
            },
            function (all_helps_component_1_1) {
                all_helps_component_1 = all_helps_component_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            },
            function (user_service_1_1) {
                user_service_1 = user_service_1_1;
            },
            function (player_service_1_1) {
                player_service_1 = player_service_1_1;
            },
            function (newVillageArmiesService_1_1) {
                newVillageArmiesService_1 = newVillageArmiesService_1_1;
            },
            function (villageService_1_1) {
                villageService_1 = villageService_1_1;
            },
            function (help_service_1_1) {
                help_service_1 = help_service_1_1;
            },
            function (stomp_service_1_1) {
                stomp_service_1 = stomp_service_1_1;
            }],
        execute: function() {
            AppModule = (function () {
                function AppModule() {
                }
                AppModule = __decorate([
                    core_1.NgModule({
                        imports: [
                            platform_browser_1.BrowserModule,
                            http_1.HttpModule,
                            forms_1.FormsModule,
                            forms_1.ReactiveFormsModule,
                            app_routers_1.RoutesModule
                        ],
                        declarations: [
                            app_component_1.AppComponent,
                            alliance_component_1.AllianceComponent,
                            header_component_1.HeaderComponent,
                            add_alliance_component_1.AllianceForm,
                            edit_alliance_component_1.EditAllianceComponent,
                            modal_1.ConfirmComponent,
                            player_component_1.PlayerComponent,
                            playerList_component_1.PlayerList,
                            playerRow_component_1.PlayerRow,
                            player_header_component_1.PlayerHeader,
                            timer_component_1.TimerComponent,
                            leader_manager_component_1.LeaderManagerComponent,
                            leader_main_component_1.LeaderMainComponent,
                            add_member_component_1.MemberForm,
                            edit_member_component_1.EditMemberComponent,
                            armyCellComponent_1.ArmyCellComponent,
                            villageRow_component_1.VillageRow,
                            addVillageForm_1.AddVillageForm,
                            village_info_component_1.VillageInfoComponent,
                            player_list_component_1.PlayerListComponent,
                            army_cell_info_component_1.ArmyCellInfoComponent,
                            leader_header_component_1.LeaderHeaderComponent,
                            addArmyForm_component_1.AddArmyForm,
                            ask_help_component_1.HelpComponent,
                            all_helps_component_1.AllHelps
                        ],
                        providers: [
                            alliance_service_1.AllianceService,
                            user_service_1.UserService,
                            player_service_1.PlayerService,
                            newVillageArmiesService_1.CurrVillageArmiesService,
                            villageService_1.VillageService,
                            help_service_1.HelpService,
                            stomp_service_1.StompService
                        ],
                        bootstrap: [app_component_1.AppComponent]
                    }), 
                    __metadata('design:paramtypes', [])
                ], AppModule);
                return AppModule;
            }());
            exports_1("AppModule", AppModule);
        }
    }
});
//# sourceMappingURL=app.module.js.map