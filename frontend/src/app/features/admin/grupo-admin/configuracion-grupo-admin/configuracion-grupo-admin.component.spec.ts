import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfiguracionGrupoAdminComponent } from './configuracion-grupo-admin.component';

describe('ConfiguracionGrupoAdminComponent', () => {
  let component: ConfiguracionGrupoAdminComponent;
  let fixture: ComponentFixture<ConfiguracionGrupoAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfiguracionGrupoAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfiguracionGrupoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
