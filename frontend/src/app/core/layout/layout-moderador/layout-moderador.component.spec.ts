
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutModeradorComponent } from './layout-moderador.component';

describe('LayoutUserComponent', () => {
  let component: LayoutModeradorComponent;
  let fixture: ComponentFixture<LayoutModeradorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LayoutModeradorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LayoutModeradorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});