import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomerAdmin } from './homer-admin';

describe('HomerAdmin', () => {
  let component: HomerAdmin;
  let fixture: ComponentFixture<HomerAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomerAdmin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomerAdmin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
