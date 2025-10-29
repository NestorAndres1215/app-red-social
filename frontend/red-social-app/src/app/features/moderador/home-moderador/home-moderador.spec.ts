import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeModerador } from './home-moderador';

describe('HomeModerador', () => {
  let component: HomeModerador;
  let fixture: ComponentFixture<HomeModerador>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeModerador]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeModerador);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
