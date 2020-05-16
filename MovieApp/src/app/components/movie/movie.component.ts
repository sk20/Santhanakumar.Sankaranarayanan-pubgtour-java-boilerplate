import { Component, OnInit } from '@angular/core';
import { Movies } from 'src/app/movies';
import { MovieService } from 'src/app/service/movie.service';
import { Router } from '@angular/router';
import { FavouriteService } from 'src/app/service/favourite.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrls: ['./movie.component.css']
})
export class MovieComponent implements OnInit {
  movies: Movies;
  moviesList: Movies[];
  favmoviesList: Array<String> = [];
  addFavMessage: string;
  buttonTextDisplay: string;
  constructor(private movieService: MovieService, private router: Router, private favMovieService: FavouriteService) { 
    this.movies = new Movies();
    this.moviesList = [];
  }
  toggle = true;
  activeButtonId = null;

  ngOnInit(): void {
    console.log("this.movieList size"+this.moviesList.length);
    this.movieService.getAllMovies().subscribe(
      movieData => {
        this.moviesList = movieData
        this.moviesList.forEach(f=>f.isSelected = false)
        console.log("movie Data "+this.moviesList[0])
      },
      err => console.log(err)
    )
  }

  addRemoveCollection(movieindex) {
    if(localStorage.getItem('userId')!= null){
      let selectdMovie = this.moviesList[movieindex];
      console.log("Message Id: ", selectdMovie.movieId);
      this.toggle = !this.toggle;
      this.activeButtonId = selectdMovie.movieId;
      this.favmoviesList.push(selectdMovie.movieId);
      console.log("selectdBook.isSelected: ", selectdMovie.isSelected);
      if (!selectdMovie.isSelected) {
        selectdMovie.isSelected =true;
        this.addFavoriteService(this.moviesList.find(f => f.movieId === selectdMovie.movieId))
      } else {
        this.removeFavoriteService(this.favmoviesList)
        selectdMovie.isSelected =false;
      }
    }else{
      this.router.navigate(['login'])
    }
   
  }
  getButtonText(id) {
    let buttonText;
    id === this.activeButtonId ? buttonText = "Remove" : buttonText = "Add";
    this.buttonTextDisplay = buttonText;
    return buttonText;
  }

  addFavoriteService(movie: Movies) {
    this.favMovieService.addToCollection(movie).subscribe(
      (response) => {
        console.log(response);
      },
      err => this.handleErrorResponse(err)
    );
  }

  removeFavoriteService(movieIdList: Array<String>) {
    this.favMovieService.removeFromCollection(movieIdList).subscribe(
      (response) => {
        console.log(response);
      },
      err => this.handleErrorResponse(err)
    );
  }

  handleErrorResponse(error: HttpErrorResponse): void {
    if (error.status === 404) {
      this.addFavMessage = `Http failure response for 404 Not Found`;
    } else {
      this.addFavMessage = error.error.message;
    }
  }

}
