import { Component, OnInit } from '@angular/core';
import { Symptom } from '../../model/symptom';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-symptoms',
  templateUrl: './symptoms.component.html',
  styleUrls: ['./symptoms.component.css']
})
export class SymptomsComponent implements OnInit {

  symptoms: Symptom[] = [];
  newSymptom: Symptom ={
    name: ""
  }

  constructor(private adminService: AdminService) { }

  ngOnInit() {
    this.adminService.getSymptoms().subscribe(data => this.symptoms = data);
  }

  deleteSymptom(index: number){
    this.adminService.deleteSymptom(this.symptoms[index].id).subscribe(
      data => {
        if(data){
          this.symptoms.splice(index, 1);
        }
        else{
          alert("Nije moguce brisanje simptoma koji se sadrzi u nekoj od bolesti");
        }
      }
    )
  }

  createSymptom(){
    this.adminService.addSymptom(this.newSymptom).subscribe(
      data => {
        if(data){
          this.symptoms.push(data);
          this.newSymptom = {
            name: ""
          }
        }
      }
    )
  }

}
