import { Component, OnInit } from '@angular/core';
import { Medicine } from '../../model/medicine';
import { AdminService } from '../../services/admin.service';

@Component({
  selector: 'app-med-comp',
  templateUrl: './med-comp.component.html',
  styleUrls: ['./med-comp.component.css']
})
export class MedCompComponent implements OnInit {

  newComponent: Medicine = {
    name: ""
  };

  components: Medicine[] = [];
  constructor(
    private adminService: AdminService
  ) { }

  ngOnInit() {
    this.adminService.getMedicineComponents().subscribe(data => this.components = data);
  }

  createComponent(){
    this.adminService.addComponent(this.newComponent).subscribe(
      data => {
        if (data){
          this.components.push(data);
          this.newComponent = {
            name: ""
          };
        }
      }
    )
  }

  deleteMedicine(index: number){
    this.adminService.deleteComponent(this.components[index].id).subscribe(
      data => {
        if(data){
          this.components.splice(index,1);
        }
      }
    )
  }

}
