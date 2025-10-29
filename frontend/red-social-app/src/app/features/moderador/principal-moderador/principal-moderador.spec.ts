import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrincipalModerador } from './principal-moderador';

describe('PrincipalModerador', () => {
  let component: PrincipalModerador;
  let fixture: ComponentFixture<PrincipalModerador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrincipalModerador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrincipalModerador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
