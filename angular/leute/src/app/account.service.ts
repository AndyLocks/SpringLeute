import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { IAccount } from './models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private static baseUrl: string = "http://localhost:8000";

  constructor(private http: HttpClient) { }

  public getAccount(nickname: string): Observable<IAccount> {    
    return this.http.get<IAccount>(AccountService.baseUrl + "/api/v1/user/" + nickname);
  }

  public createAccount(account: any): Observable<any> {
    return this.http.post<any>(AccountService.baseUrl + "/api/v1/user/registration", {
      nickname: account.nickname,
      realName: account.realName,
      description: account.description,
      email: account.email,
      password: account.password
    });
  }

  public deleteAccount(account: any): Observable<any> {
    console.log(AccountService.baseUrl + "/api/v1/user/delete/" + account.nickname + "?password=" + account.password)
    return this.http.delete<any>(AccountService.baseUrl + "/api/v1/user/delete/" + account.nickname + "?password=" + account.password);
  }
}
