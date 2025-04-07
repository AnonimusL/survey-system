import { Component, OnInit } from '@angular/core';
import { SocialAuthService } from "@abacritt/angularx-social-login";
import { UserService } from 'src/app/services/user.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { jwtDecode } from "jwt-decode";
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: any;
  loggedIn: boolean;

  loginForm: FormGroup;

  validationMessages = {
    email: {
      required: 'Email is required.',
      email: 'Please enter a valid email address.'
    },
    password: {
      required: 'Password is required.'
    }
  };

  constructor(private router: Router, private authService: SocialAuthService, private userService: UserService, private formBuilder: FormBuilder) { 
    this.loggedIn = false
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.authService.authState.subscribe((user) => {
      this.user = user;
      this.loggedIn = (user != null);
      if(this.loggedIn){
        this.userService.logIn()
      }else{
        this.userService.logOut()
      }
      console.log(user)
      localStorage.setItem("role","TARGET_ROLE")
      localStorage.setItem("email", user.email)
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const email = this.loginForm.value.email;
      const password = this.loginForm.value.password;

      /** 
      this.userService.authUser(email, password).subscribe((res: any) => {
        console.log(res)
        if (res && res.statusMessage == 'success') {
          console.log('Status 200: Success');
          console.log('User Data:', res.data.jwt);
          localStorage.setItem('token', res.data.jwt) 

          const decodedToken: any = jwtDecode(res.data.jwt);
          localStorage.setItem('role', decodedToken.role)

          this.userService.logIn()
          this.router.navigate(['/survey'])
      }
    });
    */
    localStorage.setItem('token', "mojtoken") 
    this.userService.logIn()
    this.router.navigate(['/survey'])
    }
  }

}


