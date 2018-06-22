import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MedCompComponent } from './med-comp.component';

describe('MedCompComponent', () => {
  let component: MedCompComponent;
  let fixture: ComponentFixture<MedCompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MedCompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MedCompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
