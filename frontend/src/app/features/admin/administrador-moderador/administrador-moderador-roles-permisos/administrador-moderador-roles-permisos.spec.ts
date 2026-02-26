import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministradorModeradorRolesPermisos } from './administrador-moderador-roles-permisos';

describe('AdministradorModeradorRolesPermisos', () => {
  let component: AdministradorModeradorRolesPermisos;
  let fixture: ComponentFixture<AdministradorModeradorRolesPermisos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministradorModeradorRolesPermisos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdministradorModeradorRolesPermisos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
