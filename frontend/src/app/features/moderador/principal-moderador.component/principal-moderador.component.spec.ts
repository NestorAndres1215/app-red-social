import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PrincipalModeradorComponent } from './principal-moderador.component';

describe('PrincipalModeradorComponent', () => {
  let component: PrincipalModeradorComponent;
  let fixture: ComponentFixture<PrincipalModeradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PrincipalModeradorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PrincipalModeradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
