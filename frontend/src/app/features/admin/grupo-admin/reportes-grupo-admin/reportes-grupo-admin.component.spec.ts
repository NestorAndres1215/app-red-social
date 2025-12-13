import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReportesGrupoAdminComponent } from './reportes-grupo-admin.component';

describe('ReportesGrupoAdminComponent', () => {
  let component: ReportesGrupoAdminComponent;
  let fixture: ComponentFixture<ReportesGrupoAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReportesGrupoAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ReportesGrupoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
