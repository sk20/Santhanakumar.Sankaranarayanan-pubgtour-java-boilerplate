import { Component, OnInit } from '@angular/core';
import { Movies } from 'src/app/movies';
import { MovieService } from 'src/app/service/movie.service';
import { Router } from '@angular/router';
import { FavouriteService } from 'src/app/service/favourite.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  movies: Movies;
  moviesList: Array<Movies> = [];
  favMovieList: Array<String> = [];
  addFavMsg: string;

  constructor(private movieService: MovieService, private router: Router, private favMovieService: FavouriteService) {
    this.movies = new Movies();
    this.moviesList = [];
   }

   toggle = true;
   activeButtonId = null;
   ngOnInit(): void {
     this.favMovieService.getFavouriteMovies().subscribe(
       moviedata => {
         this.moviesList = moviedata
         console.log("Fav Movie Data" + this.moviesList.length)
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
       this.favMovieList.push(selectdMovie.movieId);
       if (selectdMovie.isSelected) {
         this.addFavoriteService(this.moviesList.find(f => f.movieId === selectdMovie.movieId))
       } else {
         this.removeFavoriteService(this.favMovieList)
       }
     }else{
       this.router.navigate(['login'])
     }
    
   }
 
   getButtonText(id) {
     let buttonText;
     id === this.activeButtonId ? buttonText = "Remove" : buttonText = "Add";
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
       this.addFavMsg = `Http failure response for 404 Not Found`;
     } else {
       this.addFavMsg = error.error.message;
     }
   }

}
