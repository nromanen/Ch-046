/**
 * Created by rmochetc on 15.01.2017.
 */
System.register(['@angular/router', "./alliance/alliance.component", "./leader/leader-manager.component"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var router_1, alliance_component_1, leader_manager_component_1;
    var routes, RoutesModule;
    return {
        setters:[
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (alliance_component_1_1) {
                alliance_component_1 = alliance_component_1_1;
            },
            function (leader_manager_component_1_1) {
                leader_manager_component_1 = leader_manager_component_1_1;
            }],
        execute: function() {
            exports_1("routes", routes = ([
                {
                    path: 'admin', component: alliance_component_1.AllianceComponent
                },
                { path: 'admin/',
                    redirectTo: 'admin',
                    pathMatch: 'full'
                },
                {
                    path: 'leader',
                    component: leader_manager_component_1.LeaderManagerComponent
                },
                {
                    path: 'leader/',
                    redirectTo: 'leader',
                    pathMatch: 'full'
                }
            ]));
            exports_1("RoutesModule", RoutesModule = router_1.RouterModule.forRoot(routes));
        }
    }
});
//# sourceMappingURL=app.routers.js.map