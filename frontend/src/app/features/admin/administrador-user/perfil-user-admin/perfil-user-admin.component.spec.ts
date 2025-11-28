import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PerfilUserAdminComponent } from './perfil-user-admin.component';

describe('PerfilUserAdminComponent', () => {
  let component: PerfilUserAdminComponent;
  let fixture: ComponentFixture<PerfilUserAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PerfilUserAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PerfilUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
