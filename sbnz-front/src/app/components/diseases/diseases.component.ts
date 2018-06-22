import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { Disease } from '../../model/disease';
import { Symptom } from '../../model/symptom';

@Component({
  selector: 'app-diseases',
  templateUrl: './diseases.component.html',
  styleUrls: ['./diseases.component.css']
})
export class DiseasesComponent implements OnInit {

  newDisease: Disease = {
    name: "",
    symptoms: [],
    diseaseType: "FIRST"
  }

  diseases: Disease[] = [];
  symptoms: Symptom[] = [];
  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit() {
    this.adminService.getSymptoms().subscribe( data => this.symptoms = data);
    this.adminService.getDiseases().subscribe( data => this.diseases = data);
  }

  isThere(){
    return true;
  }

  deleteSymptom(index: number){
      this.newDisease.symptoms.splice(index, 1);
  }

  addSymptom(symptom: Symptom){
    this.newDisease.symptoms.push(symptom);
  }

  addDisease(){
    this.adminService.addDisease(this.newDisease).subscribe(
      data => {
        if(data){
          this.diseases.push(data);
          this.newDisease = {
            name: "",
            symptoms: [],
            diseaseType: "FIRST"
          }
        }
      }
    )
  }

  deleteDisease(index: number){
    this.adminService.deleteDisease(this.diseases[index].id).subscribe(
      data => {
        if(data){
          this.diseases.splice(index, 1);
        }
      }
    )
  }

}
