import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MiembrosGrupoAdminComponent } from './miembros-grupo-admin.component';

describe('MiembrosGrupoAdminComponent', () => {
  let component: MiembrosGrupoAdminComponent;
  let fixture: ComponentFixture<MiembrosGrupoAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MiembrosGrupoAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MiembrosGrupoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
