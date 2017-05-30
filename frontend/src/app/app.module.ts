import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FormComponent } from './form/form.component';
import { RegisterComponent } from './register/register.component';
import { LeftMenuComponent } from './left-menu/left-menu.component';
import {routing} from './app.routing';
import { HeaderComponent } from './header/header.component';
import { SettingsComponent } from './settings/settings.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import {AuthGuard} from './guards/auth.guard';
import { AuthenticationComponent } from './authentication/authentication.component';
import { MemoryComponent } from './memory/memory.component';
import {FriendComponent} from './friend/friend.component';
import { SearchComponent } from './search/search.component';

@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    RegisterComponent,
    LeftMenuComponent,
    HeaderComponent,
    SettingsComponent,
    DashboardComponent,
    AuthenticationComponent,
    MemoryComponent,
    FriendComponent,
    SearchComponent,
  ],
  imports: [
    BrowserModule,
    [FormsModule],
    HttpModule,
    routing,
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
