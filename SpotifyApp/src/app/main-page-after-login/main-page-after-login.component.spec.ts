import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainPageAfterLoginComponent } from './main-page-after-login.component';

describe('MainPageAfterLoginComponent', () => {
  let component: MainPageAfterLoginComponent;
  let fixture: ComponentFixture<MainPageAfterLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MainPageAfterLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MainPageAfterLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
