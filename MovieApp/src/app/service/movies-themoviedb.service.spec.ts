import { TestBed } from '@angular/core/testing';

import { MoviesThemoviedbService } from './movies-themoviedb.service';

describe('MoviesThemoviedbService', () => {
  let service: MoviesThemoviedbService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MoviesThemoviedbService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
