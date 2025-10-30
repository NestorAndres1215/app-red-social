import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarModerador } from './navbar-moderador';

describe('NavbarModerador', () => {
  let component: NavbarModerador;
  let fixture: ComponentFixture<NavbarModerador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarModerador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NavbarModerador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
