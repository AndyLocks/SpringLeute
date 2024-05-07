import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [HeaderComponent, RouterOutlet, ReactiveFormsModule, CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss'
})
export class SearchComponent {

  constructor(private router: Router) {}

  form = new FormGroup({
    request: new FormControl<string>("")
  });

  public onSubmit() {
    this.router.navigate(['/user/' + this.form.value["request"]])
  }
}
