/**
 * Created by rmochetc on 15.01.2017.
 */

import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {AllianceComponent} from "./alliance/alliance.component";
import {LeaderMainComponent} from "./leader/leader-main.component";
import {LeaderManagerComponent} from "./leader/leader-manager.component";
import {PlayerComponent} from "./player/player.component";



export const routes:Routes = ([


    {
        path: 'admin', component: AllianceComponent
    },

    {   path: 'admin/',
        redirectTo:'admin',
        pathMatch:'full'
    },
    {
        path:'leader',
        component:LeaderMainComponent
    },
    {
        path: 'leader/',
        redirectTo:'leader',
        pathMatch:'full'
    },
    {
        path: 'leader/manager',
        component: LeaderManagerComponent
    },
    {
        path:'player/:id',
        component: PlayerComponent
    },
    {
        path: 'user/:id',
        redirectTo:'player/:id',
        pathMatch:'full',

    },
]);

export const RoutesModule: ModuleWithProviders = RouterModule.forRoot(routes);