import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeModeradorComponent } from './home-moderador.component';

describe('HomeModeradorComponent', () => {
  let component: HomeModeradorComponent;
  let fixture: ComponentFixture<HomeModeradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeModeradorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeModeradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
