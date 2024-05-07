import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { IAccount } from '../models/account';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [RouterOutlet, CommonModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent {
  title = "aboba";
}
