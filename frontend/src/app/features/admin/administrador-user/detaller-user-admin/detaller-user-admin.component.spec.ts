import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallerUserAdminComponent } from './detaller-user-admin.component';

describe('DetallerUserAdminComponent', () => {
  let component: DetallerUserAdminComponent;
  let fixture: ComponentFixture<DetallerUserAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallerUserAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetallerUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
