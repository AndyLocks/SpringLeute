import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AccountService } from '../account.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [HeaderComponent, FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {
  constructor (private accountService: AccountService, private router: Router) {} 

  form = new FormGroup({
    nickname: new FormControl<string>(""),
    realName: new FormControl<string>(""),
    description: new FormControl<string>(""),
    email: new FormControl<string>(""),
    password: new FormControl<string>(""),
  });

  public onSubmit() {
    const account = {
      nickname: this.form.value["nickname"],
      realName: this.form.value["realName"],
      description: this.form.value["description"],
      email: this.form.value["email"],
      password: this.form.value["password"],
    }
    this.accountService.createAccount(account).subscribe();

    this.router.navigate(['home']);
  }
}
