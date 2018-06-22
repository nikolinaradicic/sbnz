import { Injectable } from '@angular/core';
import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Medicine } from '../model/medicine';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) { }

  addMedicine(medicine: Medicine): Observable<Medicine>{
    console.log(medicine);
    let params = JSON.stringify(medicine);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<Medicine>("http://localhost:8080/api/medicine", params,
      {
        headers: headers
      })
  }

  getMedicine(): Observable<Medicine[]>{
    return this.http.get<Medicine[]>("http://localhost:8080/api/medicine");
  }

  deleteMedicine(id: number): Observable<boolean>{
    return this.http.delete<boolean>("http://localhost:8080/api/medicine/" + id);
  }

  addComponent(component: Medicine): Observable<Medicine>{
    let params = JSON.stringify(component);
    let headers = new HttpHeaders().set('Content-Type', 'application/json; charset=utf-8');
    return this.http.post<Medicine>("http://localhost:8080/api/component", params,
      {
        headers: headers
      })
  }

  getMedicineComponents(): Observable<Medicine[]>{
    return this.http.get<Medicine[]>("http://localhost:8080/api/component")
  }

  deleteComponent(id: number): Observable<boolean>{
    return this.http.delete<boolean>("http://localhost:8080/api/component/" + id);
  }

}
