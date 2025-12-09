import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialLogueoAdminComponent } from './historial-logueo-admin.component';

describe('HistorialLogueoAdminComponent', () => {
  let component: HistorialLogueoAdminComponent;
  let fixture: ComponentFixture<HistorialLogueoAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistorialLogueoAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialLogueoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
