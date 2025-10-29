import 'zone.js';
import { bootstrapApplication } from '@angular/platform-browser';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { provideBrowserGlobalErrorListeners, importProvidersFrom } from '@angular/core';

import { App } from './app/app';
import { routes } from './app/app.routes';
import { authInterceptor } from './app/core/interceptors/auth.interceptor';
import { OAuthModule } from 'angular-oauth2-oidc';

bootstrapApplication(App, {
  providers: [
    provideHttpClient(withInterceptors([authInterceptor])), // ✅ Interceptor activo aquí
    provideRouter(routes),
    provideBrowserGlobalErrorListeners(),
    importProvidersFrom(OAuthModule.forRoot()) // ✅ Mantén tu OAuth
  ]
});