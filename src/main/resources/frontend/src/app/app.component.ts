import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'flyem';
  textFromResponse: string = "init Text";

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get<string>("/api/healthcheck", { responseType: 'text' as 'json' } ).subscribe((response) => {
      this.textFromResponse = response;
    });
  }

}
