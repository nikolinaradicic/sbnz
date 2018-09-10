import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { ReportService } from '../../services/report.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {

  constructor(
    private reportService: ReportService,
    private router: Router
  ) { }

  patients: Patient[];
  chronic: Patient[];
  addicts: Patient[];
  lowImunity: Patient[];

  ngOnInit() {
    this.reportService.getAddicts().subscribe(data => {this.addicts = data;});
    this.reportService.getChronic().subscribe(data => {
      this.chronic = data;
      this.patients = data;
    });
    this.reportService.getLowImunity().subscribe(data => {this.lowImunity = data});
  }

  getChronic(){
    this.patients = this.chronic;
  }

  getAddicts(){
    this.patients = this.addicts;
  }
  getLowImunity(){
    this.patients = this.lowImunity;
  }

  goToHistory(patient: Patient){
    this.router.navigate(['/history/', patient.id]);
  }
}
