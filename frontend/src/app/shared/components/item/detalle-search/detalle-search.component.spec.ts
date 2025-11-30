import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleSearchComponent } from './detalle-search.component';

describe('DetalleSearchComponent', () => {
  let component: DetalleSearchComponent;
  let fixture: ComponentFixture<DetalleSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetalleSearchComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetalleSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
