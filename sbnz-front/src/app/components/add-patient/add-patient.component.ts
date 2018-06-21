import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { Medicine } from '../../model/medicine';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrls: ['./add-patient.component.css']
})
export class AddPatientComponent implements OnInit {

  patient: Patient={
    name: "",
    lastName: "",
    email: "",
    medicineAlergies: [],
    componentAlergies: []
  };

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  addMedicine(event: Medicine){
    this.patient.medicineAlergies.push(event);
  }

  deleteMedicine(index: number){
    this.patient.medicineAlergies.splice(index, 1);
  }

  addComponent(event: Medicine){
    this.patient.componentAlergies.push(event);
  }

  deleteComponent(index: number){
    this.patient.componentAlergies.splice(index, 1);
  }

  addPatient(){
    this.userService.addPatient(this.patient).subscribe(data => this.router.navigate(['/']))
  }

}
