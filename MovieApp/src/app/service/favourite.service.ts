import { Injectable } from '@angular/core';
import { Movies } from '../movies';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {
  movies: Array<Movies>;
  favUrl = 'http://localhost:8086/api/v1/favourite/';
  favbooksList: Array<String> =[] ;
  constructor(private httpClient: HttpClient) { }

  addToCollection(mov: Movies){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    this.favbooksList.push(mov.movieId);
    return this.httpClient.post(this.favUrl + localStorage.getItem('userId') , mov.movieId, httpOptions);
  }

  removeFromCollection(movieListId: Array<String>) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.httpClient.delete(this.favUrl + localStorage.getItem('userId')+'/'+movieListId.pop(), httpOptions);
  }

  getFavouriteMovies(): Observable<Movies[]> {
    return this.httpClient.get<Movies[]>(this.favUrl + localStorage.getItem('userId'));
  }
}
