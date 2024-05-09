import { Component, Input, OnInit } from '@angular/core';
import { IAccount } from '../models/account';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-account',
  standalone: true,
  imports: [RouterOutlet, HeaderComponent, CommonModule],
  templateUrl: './account.component.html',
  styleUrl: './account.component.scss'
})
export class AccountComponent implements OnInit{
  showDiscordAccount: boolean = false
  @Input() account: IAccount;

  public ngOnInit(): void {
      if(this.account.discord_nickname != null) {
        this.showDiscordAccount = true
      }
  }
}
