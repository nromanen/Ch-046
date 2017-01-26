/**
 * Created by rmochetc on 15.01.2017.
 */
System.register(["@angular/router", "./alliance/alliance.component", "./player/player.component", "./help/ask-help.component", "./help/all-helps.component"], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var router_1, alliance_component_1, player_component_1, ask_help_component_1, all_helps_component_1, routes, RoutesModule;
    return {
        setters: [
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (alliance_component_1_1) {
                alliance_component_1 = alliance_component_1_1;
            },
            function (player_component_1_1) {
                player_component_1 = player_component_1_1;
            },
            function (ask_help_component_1_1) {
                ask_help_component_1 = ask_help_component_1_1;
            },
            function (all_helps_component_1_1) {
                all_helps_component_1 = all_helps_component_1_1;
            }
        ],
        execute: function () {/**
             * Created by rmochetc on 15.01.2017.
             */
            exports_1("routes", routes = ([
                {
                    path: 'admin', component: alliance_component_1.AllianceComponent
                },
                { path: 'admin/',
                    redirectTo: 'admin',
                    pathMatch: 'full'
                },
                {
                    path: 'player/:id',
                    component: player_component_1.PlayerComponent
                },
                {
                    path: 'user/:id',
                    redirectTo: 'player/:id',
                    pathMatch: 'full',
                },
                {
                    path: 'help',
                    component: ask_help_component_1.HelpComponent
                },
                {
                    path: 'allHelps',
                    component: all_helps_component_1.AllHelps
                }
            ]));
            exports_1("RoutesModule", RoutesModule = router_1.RouterModule.forRoot(routes));
        }
    };
});
//# sourceMappingURL=app.routers.js.map