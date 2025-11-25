import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEliminacionComponent } from './modal-eliminacion.component';

describe('ModalEliminacionComponent', () => {
  let component: ModalEliminacionComponent;
  let fixture: ComponentFixture<ModalEliminacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalEliminacionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalEliminacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
