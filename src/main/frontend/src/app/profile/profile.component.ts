import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {User, UserService} from "../service/user.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})



export class ProfileComponent implements OnInit {
  profileData: User = new User();

  constructor(private userService : UserService,
              private router: Router) { }

  ngOnInit() {
    this.userService.getCurrentUser().subscribe(response => {
      this.profileData = response;
    })
  }

}
