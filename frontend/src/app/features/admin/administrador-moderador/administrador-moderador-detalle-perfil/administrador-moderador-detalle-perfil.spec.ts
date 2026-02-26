import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministradorModeradorDetallePerfil } from './administrador-moderador-detalle-perfil';

describe('AdministradorModeradorDetallePerfil', () => {
  let component: AdministradorModeradorDetallePerfil;
  let fixture: ComponentFixture<AdministradorModeradorDetallePerfil>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministradorModeradorDetallePerfil]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdministradorModeradorDetallePerfil);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
