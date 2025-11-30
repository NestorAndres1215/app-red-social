import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccionAdminComponent } from './accion-admin.component';

describe('AccionAdminComponent', () => {
  let component: AccionAdminComponent;
  let fixture: ComponentFixture<AccionAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccionAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccionAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
