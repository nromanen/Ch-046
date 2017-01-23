import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {AllianceComponent} from "./alliance/alliance.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";



export const routes:Routes = ([

    {
        path: 'admin', component: AllianceComponent
    },
    {
        path: 'leader', component: LeaderManagerComponent
    }
]);

export const RoutesModule: ModuleWithProviders = RouterModule.forRoot(routes);