import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})



export class ProfileComponent implements OnInit {
  profileData: User = {};

  constructor(private http: HttpClient,
              private router: Router) { }

  ngOnInit() {
    this.http.get<User>("/api/user").subscribe(response => {
      this.profileData = response;
    },
      error => {
        console.log(error);
        this.router.navigate(['/login']);
      });
  }

}

class User {
  constructor(public username?: string,
              public email?: string) { }
}
