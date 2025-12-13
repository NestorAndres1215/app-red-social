import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MisGruposAdminComponent } from './mis-grupos-admin.component';

describe('MisGruposAdminComponent', () => {
  let component: MisGruposAdminComponent;
  let fixture: ComponentFixture<MisGruposAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MisGruposAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MisGruposAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
