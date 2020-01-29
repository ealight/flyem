import {Injectable} from "@angular/core";
import {Observable, observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

export class User {
  constructor(public username?: string,
              public email?: string) { }
}

@Injectable({providedIn: 'root'})
export class UserService{
  constructor(private http : HttpClient) {}

  getCurrentUser() : Observable<User> {
    return this.http.get("/api/user/current")
  }
}
