import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSuspendidosComponent } from './admin-suspendidos.component';

describe('AdminSuspendidosComponent', () => {
  let component: AdminSuspendidosComponent;
  let fixture: ComponentFixture<AdminSuspendidosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminSuspendidosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminSuspendidosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
