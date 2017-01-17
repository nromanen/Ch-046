/**
 * Created by rmochetc on 15.01.2017.
 */
System.register(["@angular/router", "./alliance/alliance.component", "./users/users.component", "./vailage/village.componrnt"], function (exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var router_1, alliance_component_1, users_component_1, village_componrnt_1, routes, RoutesModule;
    return {
        setters: [
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (alliance_component_1_1) {
                alliance_component_1 = alliance_component_1_1;
            },
            function (users_component_1_1) {
                users_component_1 = users_component_1_1;
            },
            function (village_componrnt_1_1) {
                village_componrnt_1 = village_componrnt_1_1;
            }
        ],
        execute: function () {/**
             * Created by rmochetc on 15.01.2017.
             */
            exports_1("routes", routes = [
                { path: '', component: alliance_component_1.AllianceComponent },
                { path: 'users', component: users_component_1.Users, },
                { path: 'villages', component: village_componrnt_1.Village, },
            ]);
            exports_1("RoutesModule", RoutesModule = router_1.RouterModule.forRoot(routes));
        }
    };
});
//# sourceMappingURL=app.routers.js.map