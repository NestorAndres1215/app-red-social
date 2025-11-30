import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActividadModeradorAdminComponent } from './actividad-moderador-admin.component';

describe('ActividadModeradorAdminComponent', () => {
  let component: ActividadModeradorAdminComponent;
  let fixture: ComponentFixture<ActividadModeradorAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActividadModeradorAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActividadModeradorAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
