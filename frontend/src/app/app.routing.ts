import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {LeftMenuComponent} from './left-menu/left-menu.component';
import {ModuleWithProviders} from '@angular/core';
import {HeaderComponent} from './header/header.component';
import {SettingsComponent} from './settings/settings.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {AuthGuard} from './guards/auth.guard';
import {AuthenticationComponent} from './authentication/authentication.component';
import {FriendComponent} from './friend/friend.component';
import {SearchComponent} from "./search/search.component";
import {MemoryComponent} from "./memory/memory.component";
import {TaggedComponent} from "app/tagged/tagged.component";
import {NewMemoryComponent} from "./new-memory/new-memory.component";

export const appRoutes: Routes = [
  {path: '' , component:  AuthenticationComponent},
  {path: 'settings', component: SettingsComponent, canActivate: [AuthGuard]},
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'friends', component: FriendComponent, canActivate: [AuthGuard]},
  {path: 'search', component: SearchComponent, canActivate: [AuthGuard]},
  {path: 'memory', component: MemoryComponent, canActivate: [AuthGuard]},
  {path: 'tagged', component: TaggedComponent, canActivate: [AuthGuard]},
  {path: 'new-memory', component: NewMemoryComponent, canActivate: [AuthGuard]}

];
export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
