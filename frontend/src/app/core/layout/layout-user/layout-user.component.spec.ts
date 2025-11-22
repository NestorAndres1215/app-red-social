/* tslint:disable:no-unused-variable */
import { waitForAsync, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { LayoutUserComponent } from './layout-user.component';



describe('LayoutUserComponent', () => {
  let component: LayoutUserComponent;
  let fixture: ComponentFixture<LayoutUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LayoutUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LayoutUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});