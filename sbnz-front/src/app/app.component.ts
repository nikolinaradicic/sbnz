import { Component } from '@angular/core';
import { LoggedUtils } from './utils/logged.utils';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  isLoggedIn(){
    return !LoggedUtils.isEmpty();
  }

  getRole(){
    return LoggedUtils.getRole();
  }

  logout(){
    console.log("izlogovo");
    localStorage.removeItem("loggedUser");
  }
}
