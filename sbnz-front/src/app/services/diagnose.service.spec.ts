import { TestBed, inject } from '@angular/core/testing';

import { DiagnoseService } from './diagnose.service';

describe('DiagnoseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DiagnoseService]
    });
  });

  it('should be created', inject([DiagnoseService], (service: DiagnoseService) => {
    expect(service).toBeTruthy();
  }));
});
