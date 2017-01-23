System.register(['@angular/core', '@angular/platform-browser', '@angular/http', '@angular/forms', './app.component', "./alliance/alliance.component", "./header/header.component", "./alliance/addalliance.component", "./alliance/editalliance.component", "./modal_window/modal", "./app.routers", "./services/pager.service", "./services/alliance/alliance-service", "./timer/timer.component", "./leader/leader-manager.component", "./user/add-member.component", "./services/user.service", "./user/alliance-members.component", "./user/edit-member.component"], function(exports_1, context_1) {
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
    var core_1, platform_browser_1, http_1, forms_1, app_component_1, alliance_component_1, header_component_1, addalliance_component_1, editalliance_component_1, modal_1, app_routers_1, pager_service_1, alliance_service_1, timer_component_1, leader_manager_component_1, add_member_component_1, user_service_1, alliance_members_component_1, edit_member_component_1;
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
            function (leader_manager_component_1_1) {
                leader_manager_component_1 = leader_manager_component_1_1;
            },
            function (add_member_component_1_1) {
                add_member_component_1 = add_member_component_1_1;
            },
            function (user_service_1_1) {
                user_service_1 = user_service_1_1;
            },
            function (alliance_members_component_1_1) {
                alliance_members_component_1 = alliance_members_component_1_1;
            },
            function (edit_member_component_1_1) {
                edit_member_component_1 = edit_member_component_1_1;
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
                            leader_manager_component_1.LeaderManagerComponent,
                            add_member_component_1.MemberForm,
                            alliance_members_component_1.AllianceMembersComponent,
                            edit_member_component_1.EditMemberComponent
                        ],
                        providers: [
                            alliance_service_1.AllianceService,
                            pager_service_1.PagerService,
                            user_service_1.UserService
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