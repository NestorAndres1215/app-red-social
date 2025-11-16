import { Routes } from '@angular/router';
import { PrincipalComponent } from './features/home/principal/principal.component';

export const routes: Routes = [

      { path: '', component: PrincipalComponent, pathMatch: 'full' },
];
