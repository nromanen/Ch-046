/**
 * Created by rmochetc on 15.01.2017.
 */

import {Routes, RouterModule} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';

import {AllianceComponent} from "./alliance/alliance.component";
import {Users} from "./users/users.component";
import {Village} from "./vailage/village.componrnt";

export const routes:Routes = [
    {path: '', component: AllianceComponent},
    {path: 'users', component: Users, },
    {path: 'villages', component: Village,},
];

export const RoutesModule: ModuleWithProviders = RouterModule.forRoot(routes);