import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdministradorModeradorAuditoria } from './administrador-moderador-auditoria';

describe('AdministradorModeradorAuditoria', () => {
  let component: AdministradorModeradorAuditoria;
  let fixture: ComponentFixture<AdministradorModeradorAuditoria>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdministradorModeradorAuditoria]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdministradorModeradorAuditoria);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
