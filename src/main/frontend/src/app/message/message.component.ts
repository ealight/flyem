import {Component, OnDestroy, OnInit} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {HttpClient} from "@angular/common/http";

class Message {
  constructor(public author: string, public text: string) { }
}

class User {
  constructor(public username?: string,
              public email?: string) { }
}

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnInit, OnDestroy {
  private stompClient;
  public messages : Message[] = [];
  public currentMessage : string = null;
  private profileData : User;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    let ws = new SockJS("/ws");
    this.stompClient = Stomp.over(ws);
    let that = this;

    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/topic/messages", (message) => {
          that.messages.push(JSON.parse(message.body));
      })
    })

    this.http.get<User>("/api/user").subscribe(response => {
      this.profileData = response;
    });
  }

  ngOnDestroy() : void {
    this.stompClient.disconnect();
  }

  public sendMessage() : void {
    let message = new Message(this.profileData.username, this.currentMessage);
    this.stompClient.send("/app/message", {}, JSON.stringify(message));
    this.currentMessage = null;
  }
}
