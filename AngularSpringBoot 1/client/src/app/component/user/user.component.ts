import { Component, OnInit } from '@angular/core';

import {Router, ActivatedRoute} from "@angular/router";
import { User } from 'src/app/model/user/user.component';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  userId: string;
  currentUser: User;

  constructor(private router: Router, private route: ActivatedRoute) {
      this.currentUser = JSON.parse(localStorage.getItem("detailUser"));
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      if(params.has('id')){
        this.userId = params.get('id');
      }
    });
  }

}