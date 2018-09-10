import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from '../model/patient';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReportService {

  constructor(private http: HttpClient) { }

  getChronic(): Observable<Patient[]>{
    return this.http.get<Patient[]>("http://localhost:8080/api/report/chronic");
  }

  getAddicts(): Observable<Patient[]>{
    return this.http.get<Patient[]>("http://localhost:8080/api/report/addicts");
  }

  getLowImunity(): Observable<Patient[]>{
    return this.http.get<Patient[]>("http://localhost:8080/api/report/lowImunity");
  }

}
