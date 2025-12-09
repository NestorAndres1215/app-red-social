import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuentasBloqueadasAdminComponent } from './cuentas-bloqueadas-admin.component';

describe('CuentasBloqueadasAdminComponent', () => {
  let component: CuentasBloqueadasAdminComponent;
  let fixture: ComponentFixture<CuentasBloqueadasAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CuentasBloqueadasAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CuentasBloqueadasAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
