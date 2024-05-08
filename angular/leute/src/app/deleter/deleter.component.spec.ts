import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleterComponent } from './deleter.component';

describe('DeleterComponent', () => {
  let component: DeleterComponent;
  let fixture: ComponentFixture<DeleterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeleterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeleterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
