import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';



import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { UserService } from './services/user.service';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(
      [
        { path: 'login', component: LoginComponent },
        {
          path:'not-found', component: PageNotFoundComponent
        },
        {
          path:'**', redirectTo:'not-found'
        }
      ]
    )
  ],
  providers: [
    UserService,
    HttpClient
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
