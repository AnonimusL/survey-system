import { Injectable } from '@angular/core';
import { OrganizationDetailed } from '../models/organization_model';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  private apiUrl = environment.userApiUrl;

  constructor(private http: HttpClient) {
   
  }

  getOrganization(organizationId: number): Observable<OrganizationDetailed>{
    return this.http.get<OrganizationDetailed>(`${this.apiUrl}/organization/${organizationId}`)
  }
}
