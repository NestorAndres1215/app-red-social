import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegModeradorUserComponent } from './reg-moderador-user.component';

describe('RegModeradorUserComponent', () => {
  let component: RegModeradorUserComponent;
  let fixture: ComponentFixture<RegModeradorUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegModeradorUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegModeradorUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
