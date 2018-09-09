import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { UserService } from './services/user.service';
import { AdminService } from './services/admin.service';

import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { TokenInterceptorService } from './services/token-interceptor.service';
import { AddPatientComponent } from './components/add-patient/add-patient.component';
import { SearchComponent } from './components/search/search.component';
import { FilterPipe } from './pipes/filter.pipe';
import { DiagnoseComponent } from './components/diagnose/diagnose.component';
import { PatientsComponent } from './components/patients/patients.component';
import { SymptomsComponent } from './components/symptoms/symptoms.component';
import { MedicineComponent } from './components/medicine/medicine.component';
import { DiseasesComponent } from './components/diseases/diseases.component';
import { MedCompComponent } from './components/med-comp/med-comp.component';
import { AddDoctorComponent } from './components/add-doctor/add-doctor.component';
import { TherapyComponent } from './components/therapy/therapy.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent,
    AddPatientComponent,
    SearchComponent,
    FilterPipe,
    DiagnoseComponent,
    PatientsComponent,
    SymptomsComponent,
    MedicineComponent,
    DiseasesComponent,
    MedCompComponent,
    AddDoctorComponent,
    TherapyComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(
      [
        {
          path: 'login', component: LoginComponent 
        },
        {
          path: 'components', component: MedCompComponent
        },
        {
          path: 'medicine', component: MedicineComponent
        },
        {
          path: 'symptoms', component: SymptomsComponent
        },
        {
          path: 'diseases', component: DiseasesComponent
        },
        { 
          path: 'newPatient', component: AddPatientComponent 
        },
        { 
          path: 'patients', component: PatientsComponent
        },
        {
          path:'not-found', component: PageNotFoundComponent
        },
        { path: 'diagnose/:id', component: DiagnoseComponent },
        {
          path:'**', redirectTo:'not-found'
        }
      ]
    )
  ],
  providers: [
    UserService,
    AdminService,
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
