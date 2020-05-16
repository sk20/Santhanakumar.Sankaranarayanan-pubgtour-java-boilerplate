import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  private term: string = "";

  constructor(private router: Router, private movieService: MovieService) { }

 
  ngOnInit(): void {
  }

}
