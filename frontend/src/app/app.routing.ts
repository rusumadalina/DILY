import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {LeftMenuComponent} from './left-menu/left-menu.component';
import {ModuleWithProviders} from '@angular/core';
import {ParalaxComponent} from './paralax/paralax.component';
import {HeaderComponent} from "./header/header.component";
import {SettingsComponent} from "./settings/settings.component";
import {DashboardComponent} from "./dashboard/dashboard.component";

export const appRoutes: Routes = [
  {path: '', component: ParalaxComponent},
  {path: 'menu', component: LeftMenuComponent},
  {path: 'header', component: HeaderComponent},
  {path: 'dashboard/settings', component: SettingsComponent},
  {path: 'dashboard', component: DashboardComponent}
];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
