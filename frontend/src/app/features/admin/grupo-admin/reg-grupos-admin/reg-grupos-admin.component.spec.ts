import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegGruposAdminComponent } from './reg-grupos-admin.component';

describe('RegGruposAdminComponent', () => {
  let component: RegGruposAdminComponent;
  let fixture: ComponentFixture<RegGruposAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegGruposAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegGruposAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
