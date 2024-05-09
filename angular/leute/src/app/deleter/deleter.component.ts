import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { AccountService } from '../account.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-deleter',
  standalone: true,
  imports: [HeaderComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './deleter.component.html',
  styleUrl: './deleter.component.scss'
})
export class DeleterComponent {
  constructor (private accountService: AccountService, private router: Router) {}

  form = new FormGroup({
    nickname: new FormControl<string>(""),
    password: new FormControl<string>(""),
  });

  public onSubmit() {
    const account = {
      nickname: this.form.value["nickname"],
      password: this.form.value["password"],
    }

    this.accountService.deleteAccount(account).subscribe();

    this.router.navigate(['home']);
  }
}
