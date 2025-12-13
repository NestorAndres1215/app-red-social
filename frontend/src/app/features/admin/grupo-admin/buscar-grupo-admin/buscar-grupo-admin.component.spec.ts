import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarGrupoAdminComponent } from './buscar-grupo-admin.component';

describe('BuscarGrupoAdminComponent', () => {
  let component: BuscarGrupoAdminComponent;
  let fixture: ComponentFixture<BuscarGrupoAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuscarGrupoAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuscarGrupoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
