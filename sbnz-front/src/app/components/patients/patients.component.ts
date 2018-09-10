import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/user.service';
import { Patient } from '../../model/patient';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {

  patients: Patient[] = [];
  searchText = "";

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  this.userService.getPatients().subscribe(data => this.patients = data);
  }
  
  goToDiagnose(p: Patient){
    console.log(p);
    this.router.navigate(['/diagnose/', p.id]);
  }

  goToHistory(p: Patient){
    this.router.navigate(['/history/', p.id])
  }

}
