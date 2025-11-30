import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AuditoriaModeradorAdminComponent } from './auditoria-moderador-admin.component';

describe('AuditoriaModeradorAdminComponent', () => {
  let component: AuditoriaModeradorAdminComponent;
  let fixture: ComponentFixture<AuditoriaModeradorAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AuditoriaModeradorAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AuditoriaModeradorAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
