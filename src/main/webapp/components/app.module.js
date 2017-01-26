System.register(["@angular/core", "@angular/platform-browser", "@angular/http", "@angular/forms", "./app.component", "./alliance/alliance.component", "./header/header.component", "./alliance/addalliance.component", "./alliance/editalliance.component", "./modal_window/modal", "./app.routers", "./services/pager.service", "./services/alliance/alliance-service", "./timer/timer.component", "./player/player.component", "./player/player-header.component", "./help/ask-help.component", "./help/help.service", "./help/stomp.service", "./help/all-helps.component"], function (exports_1, context_1) {
    "use strict";
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __moduleName = context_1 && context_1.id;
    var core_1, platform_browser_1, http_1, forms_1, app_component_1, alliance_component_1, header_component_1, addalliance_component_1, editalliance_component_1, modal_1, app_routers_1, pager_service_1, alliance_service_1, timer_component_1, player_component_1, player_header_component_1, ask_help_component_1, help_service_1, stomp_service_1, all_helps_component_1, AppModule;
    return {
        setters: [
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
            function (app_component_1_1) {
                app_component_1 = app_component_1_1;
            },
            function (alliance_component_1_1) {
                alliance_component_1 = alliance_component_1_1;
            },
            function (header_component_1_1) {
                header_component_1 = header_component_1_1;
            },
            function (addalliance_component_1_1) {
                addalliance_component_1 = addalliance_component_1_1;
            },
            function (editalliance_component_1_1) {
                editalliance_component_1 = editalliance_component_1_1;
            },
            function (modal_1_1) {
                modal_1 = modal_1_1;
            },
            function (app_routers_1_1) {
                app_routers_1 = app_routers_1_1;
            },
            function (pager_service_1_1) {
                pager_service_1 = pager_service_1_1;
            },
            function (alliance_service_1_1) {
                alliance_service_1 = alliance_service_1_1;
            },
            function (timer_component_1_1) {
                timer_component_1 = timer_component_1_1;
            },
            function (player_component_1_1) {
                player_component_1 = player_component_1_1;
            },
            function (player_header_component_1_1) {
                player_header_component_1 = player_header_component_1_1;
            },
            function (ask_help_component_1_1) {
                ask_help_component_1 = ask_help_component_1_1;
            },
            function (help_service_1_1) {
                help_service_1 = help_service_1_1;
            },
            function (stomp_service_1_1) {
                stomp_service_1 = stomp_service_1_1;
            },
            function (all_helps_component_1_1) {
                all_helps_component_1 = all_helps_component_1_1;
            }
        ],
        execute: function () {
            AppModule = (function () {
                function AppModule() {
                }
                return AppModule;
            }());
            AppModule = __decorate([
                core_1.NgModule({
                    imports: [
                        platform_browser_1.BrowserModule,
                        http_1.HttpModule,
                        forms_1.FormsModule,
                        forms_1.ReactiveFormsModule,
                        app_routers_1.RoutesModule,
                    ],
                    declarations: [
                        app_component_1.AppComponent,
                        alliance_component_1.AllianceComponent,
                        header_component_1.HeaderComponent,
                        addalliance_component_1.AllianceForm,
                        editalliance_component_1.EditAllianceComponent,
                        modal_1.ConfirmComponent,
                        timer_component_1.TimerComponent,
                        player_component_1.PlayerComponent,
                        player_header_component_1.PlayerHeader,
                        ask_help_component_1.HelpComponent,
                        all_helps_component_1.AllHelps
                    ],
                    providers: [
                        alliance_service_1.AllianceService,
                        pager_service_1.PagerService,
                        help_service_1.HelpService,
                        stomp_service_1.StompService
                    ],
                    bootstrap: [app_component_1.AppComponent]
                })
            ], AppModule);
            exports_1("AppModule", AppModule);
        }
    };
});
//# sourceMappingURL=app.module.js.map