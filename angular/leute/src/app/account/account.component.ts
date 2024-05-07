import { Component, Input } from '@angular/core';
import { IAccount } from '../models/account';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent {
  @Input() account: IAccount;
}
