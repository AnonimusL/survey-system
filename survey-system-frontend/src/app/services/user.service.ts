import { Injectable } from '@angular/core';
import { LoginRequest, TargetUser } from '../models/organization_model';
import { Observable, Subject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { SurveyInstance, UserSurvey } from '../models/survey_model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private loggedIn = false
  private loggedInSubject = new Subject<boolean>(); // Subject for emitting login status changes
  
  private apiUsersUrl = environment.userApiUrl

  users: TargetUser[] = []
  targetUsers: TargetUser[] = []

  constructor(private http: HttpClient) {
    if(localStorage.getItem('token') != null)
      this.loggedIn = true
  }

  getUsers(type: string): Observable<TargetUser[]>{
    if(type == 'target')
      return this.http.get<TargetUser[]>(`${this.apiUsersUrl}/target-user`)
    else
      return this.http.get<TargetUser[]>(`${this.apiUsersUrl}/user/all`)
  }

  uploadTargetUsers(files:any): Observable<TargetUser[]> {
    const formData: FormData = new FormData();

    for (let i = 0; i < files.length; i++) {
      formData.append('files', files[i], files[i].name);
    }

    const token = localStorage.getItem('token')

    console.log(token)
  
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    
    return this.http.post<TargetUser[]>(`${this.apiUsersUrl}/target-user/upload/users`, formData, {headers})
  }

  getUserSurveys(email: string): Observable<UserSurvey[]>{
    return this.http.get<UserSurvey[]>(`${this.apiUsersUrl}/target-user/surveys/${email}`);
  }

  isLoggedIn(): boolean {
    return this.loggedIn;
  }

  logIn(): void {
    this.loggedIn = true;
    this.loggedInSubject.next(true); // Emit true when user logs in
    localStorage.setItem('logged', "true")

  }

  logOut(): void {
    this.loggedIn = false;
    this.loggedInSubject.next(false); // Emit false when user logs out
    localStorage.clear()

  }

  getLoggedInStatus(): Observable<boolean> {
    return this.loggedInSubject.asObservable(); // Expose the Subject as an Observable
  }

  authUser(email: string, password: string): Observable<boolean>{

    const loginRequest: LoginRequest = {
      email: email,
      password: password
    }
    return this.http.post<boolean>(`http://localhost:8080/api/auth/login`, loginRequest)
  }
}
