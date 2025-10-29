import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrincipalUser } from './principal-user';

describe('PrincipalUser', () => {
  let component: PrincipalUser;
  let fixture: ComponentFixture<PrincipalUser>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrincipalUser]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrincipalUser);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
