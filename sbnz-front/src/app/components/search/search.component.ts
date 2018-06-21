import { Component, OnInit } from '@angular/core';
import { Medicine } from '../../model/medicine';
import { Output } from '@angular/core';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor() { }
  searchText = "";

  @Output()
  addMedicine = new EventEmitter<Medicine>();

  @Output()
  addComponent = new EventEmitter<Medicine>();

  medicine: Medicine[] = [
    {name: 'Penicilin', id: 1},
    {name: 'Garamicin', id: 2}
  ]
  components: Medicine[] = [

    { name: 'garamnesto', id: 3}
  ]


  searchList: Medicine[] = [];

  ngOnInit() {
    for (let i of this.medicine){
      this.searchList.push(i);
    }

    for (let i of this.components){
      this.searchList.push(i);
    }
  }

  add(medicine: Medicine){
    console.log(medicine);
    for (let obj of this.medicine){
      if (obj == medicine){
        console.log("emitujem ga")
        this.addMedicine.emit(medicine);
        return;
      }
    }
    console.log("emitujem");
    this.addComponent.emit(medicine);

  }

}
