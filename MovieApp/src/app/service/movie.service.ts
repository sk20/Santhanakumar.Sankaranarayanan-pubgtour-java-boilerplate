import { Injectable } from '@angular/core';
import { Movies } from '../movies';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  movies: Array<Movies>;
  // booksSubject: BehaviorSubject<Array<Movies>>;
  token: any;
  userId: String;
  constructor(private httpClient: HttpClient,
    private authenticationService: AuthenticationService) {
    this.movies = [];
    this.userId = this.authenticationService.getUserId();
  }

  getAllMovies(): Observable<Movies[]>{
    return this.httpClient.get<Movies[]>('http://localhost:8082/movies/');
  }

  getMovies(): Observable<Movies[]> {
    // let userData =JSON.parse(localStorage.getItem('userId'));
    // var userLike = null;
    // if(userData!= null){
    //    userLike = userData.userLikes;
    // }
    return this.httpClient.get<Movies[]>('http://localhost:8081/catalog/nowplayingmovies/'+this.userId);  
  }

  getSearchMovies(text): Observable<Movies[]> {
    return this.httpClient.get<Movies[]>('http://localhost:8085/api/v1/search/'+text);
  }

  getMovieById(movId): Movies {
    const requiredMovie = this.movies.find(mov => mov.movieId === movId);
    return Object.assign({}, requiredMovie);
  }
}
