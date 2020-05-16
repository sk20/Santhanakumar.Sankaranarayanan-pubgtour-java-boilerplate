import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
 
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';
import { Movies } from '../movies';

const API_URL='http://localhost:8082/movies/search/';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient, private authenticationService: AuthenticationService) { }

  search(query: string): Observable<Movies[]> {
    const params: string = [
      `q=${query}`,
      `part=snippet`,
      `type=video`,
      `maxResults=10`
    ].join('&');

    const queryUrl = `${API_URL}/${params}`;
 
    return this.http.get(queryUrl).pipe(map(response => {
      return response['items'].map(item => {
        return new Movies({
          movieId: item.movieId.movieId,
          name: item.snippet.name,
          desc: item.snippet.desc,
          poster_path: item.snippet.poster_path.high.url
        });
      });
    }));
  }
}
