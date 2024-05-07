import { Routes } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { ResultComponent } from './result/result.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';

export const routes: Routes = [
    {path: "", redirectTo: '/home', pathMatch: 'full'},
    {path: "home", component: HomeComponent},
    {path: "search", component: SearchComponent},
    {path: "user/:nickname", component: ResultComponent}
];

export class AppRoatingModule { }