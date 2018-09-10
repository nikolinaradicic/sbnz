import { Component, OnInit } from '@angular/core';
import { MedicalRecord } from '../../model/medicalRecord';
import { Input, Output, EventEmitter } from '@angular/core';
import { Medicine } from '../../model/medicine';
import { AdminService } from '../../services/admin.service';
import { DiagnoseService } from '../../services/diagnose.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-therapy',
  templateUrl: './therapy.component.html',
  styleUrls: ['./therapy.component.css']
})
export class TherapyComponent implements OnInit {

  constructor(
    private medicineService: AdminService,
    private diagnoseService: DiagnoseService,
    private router: Router
  ) { }

  searchText = "";
  order = "name";
  @Input()
  mr : MedicalRecord;
  @Input()
  patientId: number;
  selected: Medicine[] = [];
  medicine: Medicine[] = [];
  alergies: Medicine[] = [];
  @Output()
  back = new EventEmitter<boolean>();
  ngOnInit() {
    this.medicineService.getMedicine().subscribe( data => {
      if(data){
        this.medicine = data;
      }
    })
  }

  goBack(){
    this.back.emit(true);
  }

  finish(){
    console.log(this.mr);
    this.mr.medicine = this.selected;
    this.diagnoseService.setDiagnose(this.mr, this.patientId).subscribe(data => {
      if (data.length == 0){
        this.router.navigate(['/patients']);
      }
      this.alergies = data;
    });
  }

  addMedication(m: Medicine){
    this.selected.push(m);
    for (let i = 0; i < this.medicine.length; i++){
      if (this.medicine[i].id == m.id){
        this.medicine.splice(i,1);
        break;
      }
    }
  }

  deleteMedication(index: number){
    this.medicine.push(this.selected[index]);
    this.selected.splice(index, 1);
  }
}
