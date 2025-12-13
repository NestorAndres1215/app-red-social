import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GruposAdminComponent } from './grupos-admin.component';

describe('GruposAdminComponent', () => {
  let component: GruposAdminComponent;
  let fixture: ComponentFixture<GruposAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GruposAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GruposAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
