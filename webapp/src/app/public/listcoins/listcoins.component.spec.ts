import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListcoinsComponent } from './listcoins.component';

describe('ListcoinsComponent', () => {
  let component: ListcoinsComponent;
  let fixture: ComponentFixture<ListcoinsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListcoinsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListcoinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
