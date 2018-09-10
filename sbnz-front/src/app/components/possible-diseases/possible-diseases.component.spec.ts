import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PossibleDiseasesComponent } from './possible-diseases.component';

describe('PossibleDiseasesComponent', () => {
  let component: PossibleDiseasesComponent;
  let fixture: ComponentFixture<PossibleDiseasesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PossibleDiseasesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PossibleDiseasesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
