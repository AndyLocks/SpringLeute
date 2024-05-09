import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AccountService } from '../account.service';
import { IAccount } from '../models/account';
import { AccountComponent } from '../account/account.component';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from '../header/header.component';

@Component({
  selector: 'app-result',
  standalone: true,
  imports: [AccountComponent, CommonModule, HeaderComponent],
  templateUrl: './result.component.html',
  styleUrl: './result.component.scss'
})
export class ResultComponent implements OnInit{
  nickname = ''
  account: IAccount;
  showAccount: boolean = false
  constructor (private route: ActivatedRoute, private accountService: AccountService) {}

  public ngOnInit() {
    this.nickname = this.route.snapshot.params["nickname"];

    this.accountService.getAccount(this.nickname).subscribe(account => {
      this.account = account
      if(account.nickname != '') {
        this.showAccount = true
      }
    })
  }
}
