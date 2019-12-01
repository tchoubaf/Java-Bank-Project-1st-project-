import { Component, OnInit } from '@angular/core';

import {Router} from "@angular/router";
import { User } from 'src/app/model/user/user.component';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  user: User = new User();
  errorMessage:string;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit() {
  }

  register() {
    this.authService.register(this.user).subscribe(data =>{
      this.router.navigate(['/login']);
    },err => {
      this.errorMessage = "Username already exist";
    });
  }

}