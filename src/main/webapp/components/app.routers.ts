/**
 * Created by rmochetc on 15.01.2017.
 */

import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {AllianceComponent} from "./alliance/alliance.component";



export const routes:Routes = ([


    {
        path: 'admin', component: AllianceComponent
    },

    {   path: 'admin/',
        redirectTo:'admin',
        pathMatch:'full'
    }

]);

export const RoutesModule: ModuleWithProviders = RouterModule.forRoot(routes);