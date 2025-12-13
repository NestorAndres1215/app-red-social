import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudesIngresosAdminComponent } from './solicitudes-ingresos-admin.component';

describe('SolicitudesIngresosAdminComponent', () => {
  let component: SolicitudesIngresosAdminComponent;
  let fixture: ComponentFixture<SolicitudesIngresosAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudesIngresosAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolicitudesIngresosAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
