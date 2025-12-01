import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallePerfilesAdministradoresComponent } from './detalle-perfiles-administradores.component';

describe('DetallePerfilesAdministradoresComponent', () => {
  let component: DetallePerfilesAdministradoresComponent;
  let fixture: ComponentFixture<DetallePerfilesAdministradoresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallePerfilesAdministradoresComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetallePerfilesAdministradoresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
