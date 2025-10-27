import { Routes } from '@angular/router';
import { Principal } from './features/home/principal/principal';

export const routes: Routes = [

    { path: '', component: Principal, pathMatch: 'full' },
];
