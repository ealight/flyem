import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {AppRoutingModule} from "./app-routing.module";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import { LoginComponent } from './login/login.component';
import {LoginModule} from "./login/login.module";
import { ProfileComponent } from './profile/profile.component';
import {ProfileModule} from "./profile/profile.module";
import { RegistrationComponent } from './registration/registration.component';
import {RegistrationModule} from "./registration/registration.module";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    RegistrationComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        LoginModule,
        ProfileModule,
        RegistrationModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatButtonModule,
        MatInputModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
