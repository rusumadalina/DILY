import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { FormComponent } from './form/form.component';
import { RegisterComponent } from './register/register.component';
import { LeftMenuComponent } from './left-menu/left-menu.component';
import {routing} from './app.routing';
import { ParalaxComponent } from './paralax/paralax.component';
import { HeaderComponent } from './header/header.component';
import { SettingsComponent } from './settings/settings.component';
import { DashboardComponent } from './dashboard/dashboard.component';
@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    RegisterComponent,
    LeftMenuComponent,
    ParalaxComponent,
    HeaderComponent,
    SettingsComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
