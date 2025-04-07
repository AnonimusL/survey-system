import { Component, OnInit } from '@angular/core';
import { UserService } from './services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'survey-system-front';

  isLoggedIn: boolean;
  roleTarget: boolean = false
  constructor(private router: Router, private userService: UserService) { 
    this.isLoggedIn = localStorage.getItem('logged') != null
   
    if(localStorage.getItem('role') != null){
      if(localStorage.getItem('role') == 'TARGET_ROLE'){
        this.roleTarget = true
      }
    } 
  }

  ngOnInit(): void {
    this.isLoggedIn = localStorage.getItem('logged') != null
   
    if(localStorage.getItem('role') != null){
      if(localStorage.getItem('role') == 'TARGET_ROLE'){
        this.roleTarget = true
      }
    } 
    // Subscribe to changes in the login status
    this.userService.getLoggedInStatus().subscribe((status: boolean) => {
      this.isLoggedIn = status;
    });
  }

  logout(): void{
    this.userService.logOut()
  }


}
