import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialUserAdminComponent } from './historial-user-admin.component';

describe('HistorialUserAdminComponent', () => {
  let component: HistorialUserAdminComponent;
  let fixture: ComponentFixture<HistorialUserAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistorialUserAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
