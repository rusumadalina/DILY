import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {LeftMenuComponent} from './left-menu/left-menu.component';
import {ModuleWithProviders} from '@angular/core';
import {ParalaxComponent} from './paralax/paralax.component';
import {HeaderComponent} from "./header/header.component";
import {SettingsComponent} from "./settings/settings.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AuthGuard} from "./guards/auth.guard";
import {AuthenticationComponent} from "./authentication/authentication.component";

export const appRoutes: Routes = [
  {path: '', component: ParalaxComponent},
  {path: 'auth' , component:  AuthenticationComponent},
  {path: 'dashboard/settings', component: SettingsComponent, canActivate: [AuthGuard]},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]}
];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
