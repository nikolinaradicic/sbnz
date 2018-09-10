import { Component, OnInit } from '@angular/core';
import { Input, Output } from '@angular/core';
import { PossibleDisease } from '../../model/possibleDisease';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-possible-diseases',
  templateUrl: './possible-diseases.component.html',
  styleUrls: ['./possible-diseases.component.css']
})
export class PossibleDiseasesComponent implements OnInit {

  @Input()
  possible : PossibleDisease[];

  @Output()
  chosen = new EventEmitter<PossibleDisease>();
  constructor() { }

  ngOnInit() {
  }

  choose(pd: PossibleDisease){
    this.chosen.emit(pd);
  }

}
