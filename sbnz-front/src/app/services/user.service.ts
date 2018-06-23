import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { HttpParams } from '@angular/common/http/src/params';
import { Patient } from '../model/patient';
import { Observable } from 'rxjs/Observable';
import { MedicalRecord } from '../model/medicalRecord';
import { Symptom } from '../model/symptom';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string) {
    let authenticationRequest = { username: username, password: password };
    let params = JSON.stringify(authenticationRequest);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post("http://localhost:8080/auth", params,
      {
        headers: headers
      });
  }

  addPatient(obj: Patient){
    console.log(obj);
    let params = JSON.stringify(obj);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post("http://localhost:8080/api/patient", params,
      {
        headers: headers
      }).map(res => res);
  }

  getPatients(): Observable<Patient[]>{
    return this.http.get<Patient[]>("http://localhost:8080/api/patient");
  }

  diagnose(id: number, obj: Symptom[]): Observable<MedicalRecord>{

    let params = JSON.stringify(obj);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<MedicalRecord>("http://localhost:8080/api/patient/diagnose/" + id, params,
      {
        headers: headers
      }).map(res => res);

  }

}
