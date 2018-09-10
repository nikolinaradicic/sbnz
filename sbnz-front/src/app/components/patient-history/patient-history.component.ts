import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Patient } from '../../model/patient';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-patient-history',
  templateUrl: './patient-history.component.html',
  styleUrls: ['./patient-history.component.css']
})
export class PatientHistoryComponent implements OnInit {

  patientId: number;
  patient: Patient = null;
  order = "recordedDate";
  reverse = true;

  constructor(private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    this.patientId = +this.route.snapshot.paramMap.get('id');
    this.userService.getPatient(this.patientId).subscribe(
      data => {this.patient = data;}
    )
    
  }

}
