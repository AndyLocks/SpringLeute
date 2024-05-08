import { Routes } from '@angular/router';
import { SearchComponent } from './search/search.component';
import { ResultComponent } from './result/result.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { DeleterComponent } from './deleter/deleter.component';

export const routes: Routes = [
    {path: "", redirectTo: '/home', pathMatch: 'full'},
    {path: "home", component: HomeComponent},
    {path: "search", component: SearchComponent},
    {path: "user/:nickname", component: ResultComponent},
    {path: "register", component: RegisterComponent},
    {path: "delete_account", component: DeleterComponent}
];

export class AppRoatingModule { }