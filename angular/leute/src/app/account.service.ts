import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { IAccount } from './models/account';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private static baseUrl: string = "http://localhost:8080"

  constructor(private http: HttpClient) { }

  getAccount(nickname: string): Observable<IAccount> {    
    return this.http.get<IAccount>(AccountService.baseUrl + "/api/v1/user/" + nickname)
  }
}
