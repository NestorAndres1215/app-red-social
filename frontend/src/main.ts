import { bootstrapApplication } from '@angular/platform-browser';

import { importProvidersFrom } from '@angular/core';
import { NgxPaginationModule } from 'ngx-pagination';
import { App } from './app/app';

bootstrapApplication(App, {
  providers: [
    importProvidersFrom(NgxPaginationModule)
  ]
});
