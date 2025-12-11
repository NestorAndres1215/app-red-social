import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CodigoVerificacionAuthComponent } from './codigo-verificacion-auth.component';

describe('CodigoVerificacionAuthComponent', () => {
  let component: CodigoVerificacionAuthComponent;
  let fixture: ComponentFixture<CodigoVerificacionAuthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CodigoVerificacionAuthComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CodigoVerificacionAuthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
