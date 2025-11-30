import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscarModeradorAdminComponent } from './buscar-moderador-admin.component';

describe('BuscarModeradorAdminComponent', () => {
  let component: BuscarModeradorAdminComponent;
  let fixture: ComponentFixture<BuscarModeradorAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BuscarModeradorAdminComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BuscarModeradorAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
