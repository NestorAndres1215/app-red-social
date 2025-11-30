import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialItemComponent } from './historial-item.component';

describe('HistorialItemComponent', () => {
  let component: HistorialItemComponent;
  let fixture: ComponentFixture<HistorialItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistorialItemComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistorialItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
