import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';
import { ProductComponent } from './product/product.component';
import { HeaderComponent } from './header/header.component';
import { IAccount } from './models/account';
import { AccountComponent } from './account/account.component';

import { HttpClientModule } from '@angular/common/http';
import { AccountService } from './account.service';
import { aboba } from './data/aboba';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, ProductComponent, HeaderComponent, AccountComponent, HttpClientModule, RouterLink, RouterLinkActive],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'leute';
  account: IAccount = aboba;
  constructor (private accountService: AccountService) {}


  ngOnInit(): void {
    this.accountService.getAccount("illia").subscribe(account => {
      this.account = account
    })
  }
}
