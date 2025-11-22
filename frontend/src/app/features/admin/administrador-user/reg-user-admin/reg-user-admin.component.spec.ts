import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegUserAdminComponent } from './reg-user-admin.component';

describe('RegUserAdminComponent', () => {
  let component: RegUserAdminComponent;
  let fixture: ComponentFixture<RegUserAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegUserAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegUserAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
