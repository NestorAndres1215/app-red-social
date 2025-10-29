import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OlvidarContrasena } from './olvidar-contrasena';

describe('OlvidarContrasena', () => {
  let component: OlvidarContrasena;
  let fixture: ComponentFixture<OlvidarContrasena>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OlvidarContrasena]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OlvidarContrasena);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
