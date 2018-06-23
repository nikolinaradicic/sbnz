import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Symptom } from '../../model/symptom';
import { AdminService } from '../../services/admin.service';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-diagnose',
  templateUrl: './diagnose.component.html',
  styleUrls: ['./diagnose.component.css']
})
export class DiagnoseComponent implements OnInit {
  patientId: number;
  foundSymptoms: Symptom[] = [];
  symptoms: Symptom[] = [];
  searchText = "";

  constructor(
    private route: ActivatedRoute,
    private adminService: AdminService,
    private userService: UserService
  ) { }

  ngOnInit() {
    this.patientId = +this.route.snapshot.paramMap.get('id');
    this.adminService.getSymptoms().subscribe(data => this.symptoms = data);
  }

  addSymptom(symptom: Symptom){
    this.foundSymptoms.push(symptom);
    //for (let i = 0; i < this.symptoms.length; i++){
      //if (this.symptoms[i].id == symptom.id){
        //this.foundSymptoms.splice(i,1);
        //break;
      //}
    //}
  }

  deleteSymptom(index: number){
    //this.symptoms.push(this.foundSymptoms[index]);
    this.foundSymptoms.splice(index, 1);
  }

  diagnose(){
    this.userService.diagnose(this.patientId, this.foundSymptoms).subscribe(data => console.log(data));
  }

}
