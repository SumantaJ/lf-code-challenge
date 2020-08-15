import { Injectable } from '@angular/core';
import { Greeting } from '../models/greeting';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GreetingService {
  constructor(private http: HttpClient) {}

  getGreeting(id: string){
    return this.http.get(`${environment.baseUrl}/${id}`)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  addGreeting(greeting: Greeting):any{
    return this.http.post(environment.baseUrl, greeting)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  updateGreeting(greeting: Greeting): any{
    return this.http.put(environment.baseUrl, greeting)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  getGreetingsFromData(): any{
    return this.http.get(environment.baseUrl)
    .pipe(
      retry(1),
      catchError(this.handleError)
    );
  }

  handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      alert("client");
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      if(error.status == '0'){
        errorMessage = "Looks like app server is not running !!"
      }else if(error.status == '422' || '400'){
        errorMessage = error.error.validationErrors[0].field +" "+ error.error.validationErrors[0].message;  
      }else{
        errorMessage = error.error.message;
      }  
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
  }
}
