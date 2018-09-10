import { Injectable } from '@angular/core';
import { Symptom } from '../model/symptom';
import { Observable } from 'rxjs/Observable';
import { MedicalRecord } from '../model/medicalRecord';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Medicine } from '../model/medicine';
import { PossibleDisease } from '../model/possibleDisease';

@Injectable()
export class DiagnoseService {

  constructor(private http: HttpClient
  ) { }

  
  diagnose(id: number, obj: Symptom[]): Observable<MedicalRecord>{

    let params = JSON.stringify(obj);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<MedicalRecord>("http://localhost:8080/api/patient/diagnose/" + id, params,
      {
        headers: headers
      }).map(res => res);

  }

  setDiagnose(md: MedicalRecord, id: number): Observable<Medicine[]>{
    let params = JSON.stringify(md);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<Medicine[]>("http://localhost:8080/api/medicalRecord/" + id, params,
      {
        headers: headers
      });

  }

  getForSymptoms(obj: Symptom[]): Observable<PossibleDisease[]>{
    let params = JSON.stringify(obj);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<PossibleDisease[]>("http://localhost:8080/api/disease/forSymptoms", params,
      {
        headers: headers
      });

  }

  getSortedSymptoms(id: number): Observable<Symptom[]>{
    
    return this.http.get<Symptom[]>("http://localhost:8080/api/disease/"+ id + "/sorted");

  }
}
