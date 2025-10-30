import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaUser } from './busqueda-user';

describe('BusquedaUser', () => {
  let component: BusquedaUser;
  let fixture: ComponentFixture<BusquedaUser>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusquedaUser]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BusquedaUser);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
