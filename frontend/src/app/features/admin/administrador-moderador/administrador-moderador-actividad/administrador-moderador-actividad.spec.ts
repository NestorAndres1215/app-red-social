import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministradorModeradorActividad } from './administrador-moderador-actividad';

describe('AdministradorModeradorActividad', () => {
  let component: AdministradorModeradorActividad;
  let fixture: ComponentFixture<AdministradorModeradorActividad>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministradorModeradorActividad]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdministradorModeradorActividad);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
