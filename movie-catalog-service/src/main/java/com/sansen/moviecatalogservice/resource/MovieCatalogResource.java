package com.sansen.moviecatalogservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sansen.moviecatalogservice.model.CatalogItem;
import com.sansen.moviecatalogservice.model.Movie;
import com.sansen.moviecatalogservice.model.UserRating;

@RestController
@CrossOrigin
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private DiscoveryClient discoveryClient;
	
	 @RequestMapping("/{userId}")
	    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
	        UserRating userRating = restTemplate.getForObject("http://rating-data-service/ratingsdata/user/" + userId, UserRating.class);
	        return userRating.getRatings().stream()
	                .map(rating -> {
	                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
	                    return new CatalogItem(movie.getName(), movie.getDesc(), rating.getRating());
	                })
	                .collect(Collectors.toList());
	    }
	 
	@RequestMapping(value = "/nowplayingmovies/{userId}", method = RequestMethod.GET, headers = "Accept=application/json")
	 public String getNowPlaying(@PathVariable("userId") String userId) {
		ResponseEntity<String> movieInfo = restTemplate.getForEntity("http://movie-info-service/movies/", String.class);
		System.out.println(movieInfo.getBody().toString());
		return movieInfo.getBody();
	 }
}
//return Collections.singletonList(new CatalogItems("Transformer","Test",2));
//@Autowired
//private WebClient.Builder webClientBuilder;
//Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId()).retrieve().bodyToMono(Movie.class).block();