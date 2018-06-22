import { Component, OnInit } from '@angular/core';
import { Medicine } from '../../model/medicine';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-medicine',
  templateUrl: './medicine.component.html',
  styleUrls: ['./medicine.component.css']
})
export class MedicineComponent implements OnInit {

  newMedicine: Medicine ={
    name: "",
    components: [],
    medicineType: "ANTIBIOTIC"
  };

  components: Medicine[] = [];

  medicine: Medicine[] = [];

  searchText = "";

  constructor(
    private adminService: AdminService
  ){ }

  ngOnInit() {
    this.adminService.getMedicineComponents().subscribe(data => this.components = data);
    this.adminService.getMedicine().subscribe(data => this.medicine = data);
  }

  deleteMedicine(index: number){
    this.adminService.deleteMedicine(this.medicine[index].id).subscribe( 
      data => {
        if(data){
          this.medicine.splice(index,1);
        }
      }
    )
  }

  deleteComponent(index: number){
    this.newMedicine.components.splice(index, 1);
  }

  addComponent(comp: Medicine){
    this.newMedicine.components.push(comp);
  }

  addMedicine(){
    this.adminService.addMedicine(this.newMedicine).subscribe(
      data =>{
        if(data){
          this.medicine.push(data);
          this.newMedicine = {
            name: "",
            components: [],
            medicineType: "ANTIBIOTIC"
          }
        }
      })
  }

}
