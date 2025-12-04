import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetallePerfilesModeradorComponent } from './detalle-perfiles-moderador.component';

describe('DetallePerfilesModeradorComponent', () => {
  let component: DetallePerfilesModeradorComponent;
  let fixture: ComponentFixture<DetallePerfilesModeradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetallePerfilesModeradorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetallePerfilesModeradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
