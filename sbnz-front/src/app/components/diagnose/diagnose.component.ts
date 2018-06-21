import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-diagnose',
  templateUrl: './diagnose.component.html',
  styleUrls: ['./diagnose.component.css']
})
export class DiagnoseComponent implements OnInit {
  patientId: number;

  constructor(
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.patientId = +this.route.snapshot.paramMap.get('id');
  }

}
