import { Routes } from '@angular/router';
import { PrincipalComponent } from './features/home/principal/principal.component';
import { Oauth2RedirectComponent } from './features/auth/oauth2-redirect/oauth2-redirect.component';
import { ErrorComponent } from './shared/components/error/error.component';

export const routes: Routes = [

      { path: '', component: PrincipalComponent, pathMatch: 'full' },
      { path: 'oauth2/redirect', component: Oauth2RedirectComponent },
      { path: 'error', component: ErrorComponent },
];
