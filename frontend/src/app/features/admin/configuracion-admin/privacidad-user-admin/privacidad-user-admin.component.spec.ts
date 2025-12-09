import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrivacidadUserAdminComponent } from './privacidad-user-admin.component';

describe('PrivacidadUserAdminComponent', () => {
  let component: PrivacidadUserAdminComponent;
  let fixture: ComponentFixture<PrivacidadUserAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrivacidadUserAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrivacidadUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
