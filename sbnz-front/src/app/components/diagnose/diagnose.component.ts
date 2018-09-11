import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Symptom } from '../../model/symptom';
import { AdminService } from '../../services/admin.service';
import { UserService } from '../../services/user.service';
import { MedicalRecord } from '../../model/medicalRecord';
import { DiagnoseService } from '../../services/diagnose.service';
import { PossibleDisease } from '../../model/possibleDisease';
import { Disease } from '../../model/disease';

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
  order = "name";
  therapy = false;
  mRecord: MedicalRecord;
  possibleDiseases: PossibleDisease[] = null;
  diseases: Disease[] = []
  showSymptomsD: Disease = null;

  constructor(
    private route: ActivatedRoute,
    private adminService: AdminService,
    private diagnoseService: DiagnoseService
  ) { }

  ngOnInit() {
    this.patientId = +this.route.snapshot.paramMap.get('id');
    this.adminService.getSymptoms().subscribe(data => this.symptoms = data);
    this.adminService.getDiseases().subscribe(data => this.diseases = data);
  }

  addSymptom(symptom: Symptom){
    this.foundSymptoms.push(symptom);
    for (let i = 0; i < this.symptoms.length; i++){
      if (this.symptoms[i].id == symptom.id){
        this.symptoms.splice(i,1);
        break;
      }
    }
  }

  deleteSymptom(index: number){
    this.symptoms.push(this.foundSymptoms[index]);
    this.foundSymptoms.splice(index, 1);
  }

  diagnose(){
    this.diagnoseService.diagnose(this.patientId, this.foundSymptoms).subscribe(
        data => {
          if(data.disease.length > 0){
              this.mRecord = data;
              this.therapy = true;
              this.possibleDiseases = null;
          }
          else{
            alert("Rezoner ne moze da zakljuci. Nedovoljan broj simptoma");
          }
        });
  }

  onBack(agreed: boolean) {
    this.therapy = false;
  }

  getPossible(){
    this.diagnoseService.getForSymptoms(this.foundSymptoms).subscribe(
      data => {
        console.log(data);
        if(data){
            this.possibleDiseases = data;
        }
      });
  }

  diseaseChosen(pd: PossibleDisease){
      this.mRecord = {
        symptoms : this.foundSymptoms,
        disease : [pd.disease]
      }
      this.therapy = true;
      this.possibleDiseases = null;
  }

  showSymptoms(d: Disease){
    this.diagnoseService.getSortedSymptoms(d.id).subscribe(
      data => {
        d.symptoms = data;
        this.showSymptomsD = d;
      }
    )
  }

  hideSymptoms(){
    this.showSymptomsD = null;
  }

  selfDiagnose(d: Disease){
    if(this.foundSymptoms.length == 0){
      alert("Na desnoj strani izaberite simptome koje pacijent ima.")
    }
    else{
      
    this.mRecord = {
      symptoms: this.foundSymptoms,
      disease: [d]
    };
    this.therapy = true;
    }
  }
}
