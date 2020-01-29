import {Component, OnDestroy, OnInit} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {User, UserService} from "../service/user.service";

class Message {
  constructor(public author: string, public text: string) {
  }
}

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.scss']
})
export class MessageComponent implements OnInit, OnDestroy {
  private stompClient;
  private chatToken: string;

  public messages: Message[] = [];
  public currentMessage: string = null;
  public profileData: User = new User();

  constructor(private activateRouter : ActivatedRoute,
              private userService : UserService) {
  }

  ngOnInit() {
    let ws = new SockJS("/ws");
    this.stompClient = Stomp.over(ws);

    this.activateRouter.params.subscribe(params => {
       this.chatToken = params['token'];
       this.subscribeToTopic();
    })

    this.userService.getCurrentUser().subscribe(response => {
      this.profileData = response;
    })
  }

  ngOnDestroy(): void {
    this.stompClient.disconnect();
  }

  public sendMessage(): void {
    let message = new Message(this.profileData.username, this.currentMessage);

    this.stompClient.send("/app/message/" + this.chatToken, {}, JSON.stringify(message));
    this.currentMessage = null;
  }

  private subscribeToTopic(): void {
    let that = this;

    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/topic/messages/" + that.chatToken, (message) => {
        that.messages.push(JSON.parse(message.body));
      })
    })
  }
}
