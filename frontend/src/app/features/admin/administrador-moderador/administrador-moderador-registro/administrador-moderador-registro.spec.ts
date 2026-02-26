import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministradorModeradorRegistro } from './administrador-moderador-registro';

describe('AdministradorModeradorRegistro', () => {
  let component: AdministradorModeradorRegistro;
  let fixture: ComponentFixture<AdministradorModeradorRegistro>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministradorModeradorRegistro]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdministradorModeradorRegistro);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
