import { Component } from '@angular/core';
import { LoggedUtils } from './utils/logged.utils';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  private serverUrl = 'http://localhost:8080/socket'
  private stompClient;
  isLoggedIn(){
    return !LoggedUtils.isEmpty();
  }

  getRole(){
    return LoggedUtils.getRole();
  }

  logout(){
    localStorage.removeItem("loggedUser");
  }


  ngOnInit(){}

}
