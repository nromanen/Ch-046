/**
 * Created by rmochetc on 15.01.2017.
 */

import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {AllianceComponent} from "./alliance/alliance.component";
import {PlayerComponent} from "./player/player.component";
import {HelpComponent} from "./help/ask-help,component";



export const routes:Routes = ([


    {
        path: 'admin', component: AllianceComponent
    },

    {   path: 'admin/',
        redirectTo:'admin',
        pathMatch:'full'
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
    {
        path:'help',
        component: HelpComponent
    }

]);

export const RoutesModule: ModuleWithProviders = RouterModule.forRoot(routes);