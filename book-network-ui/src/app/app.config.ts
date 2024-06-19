import {APP_INITIALIZER, ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptors} from "@angular/common/http";
import {httpTokenInterceptor} from "./services/interceptor/http-token.interceptor";
import {KeycloakService} from "./services/keycloak/keycloak.service";
export function kcFactory(kc: KeycloakService) {
  return () => kc.init();
}
export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(withInterceptors([httpTokenInterceptor])),
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
  ]
};
