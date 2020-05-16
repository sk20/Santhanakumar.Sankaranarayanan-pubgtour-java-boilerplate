import { Component, OnInit, Output, EventEmitter, ElementRef } from '@angular/core';
import { fromEvent } from 'rxjs';
import { map, filter, debounceTime, tap, switchAll } from 'rxjs/operators';
import { Movies } from 'src/app/movies';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { Router } from '@angular/router';
import { MovieService } from 'src/app/service/movie.service';
import { SearchService } from 'src/app/service/search.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  @Output() loading = new EventEmitter<boolean>();
  @Output() results = new EventEmitter<Movies[]>();

  title: string = "";
  changeCount: number = 0;
  buttonName: string;
  movies: Movies;
  moviesList: Movies[];

  constructor(private authService: AuthenticationService, private router: Router,
    private movieService: MovieService, private searchService: SearchService, private el: ElementRef) {
    this.movies = new Movies();
    this.moviesList = [];
   }

  ngOnInit(): void {
    console.log("user id" + localStorage.getItem('userId'));
    if(localStorage.getItem('userId')!= null){
      this.buttonName ="Logout";
    }else{
      this.buttonName ="Login";
      // convert the `keyup` event into an observable stream
    fromEvent(this.el.nativeElement, 'keyup').pipe(
      map((e: any) => e.target.value), // extract the value of the input
      filter(text => text.length > 1), // filter out if empty
      debounceTime(500), // only once every 500ms
      tap(() => this.loading.emit(true)), // enable loading
      map((query: string) => this.searchService.search(query)), // search
      switchAll()) // produces values only from the most recent inner sequence ignoring previous streams
      .subscribe(  // act on the return of the search
        _results => {
          this.loading.emit(false);
          this.results.emit(_results);
        },
        err => {
          console.log(err);
          this.loading.emit(false);
        },
        () => {
          this.loading.emit(false);
        }
      );
    }
    console.log("this.movieList size"+this.moviesList.length);
    
  }
  onLogout() {
    this.authService.logOut();
    this.router.navigate(['/login']);
  }

  doSearch(keyword:string){
    console.log("Search text" + keyword);
    if (keyword != "") {
      this.router.navigate(['/search']);
    } else {
      alert("Empty search query");
    }
  }
  showText(title: string) {
    console.log("Search text" + title);
    if (title != "") {
      this.router.navigate(['/search']);
    } else {
      alert("Empty search query");
    }
  }

}
