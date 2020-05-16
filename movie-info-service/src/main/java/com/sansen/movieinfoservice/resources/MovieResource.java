package com.sansen.movieinfoservice.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sansen.movieinfoservice.model.Movie;
import com.sansen.movieinfoservice.model.MovieInfo;
import com.sansen.movieinfoservice.model.MovieSummary;

@RestController
@CrossOrigin
@RequestMapping("/movies")
public class MovieResource {

	@Value("${api.key}")
	private String apiKey;
	
	@Value("${REST_API_URL_WITH_KEY}")
	private String apiURI;
	
	@Value("${REST_API_SEARCH_URL}")
	private String searchAPIURI;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" + apiKey, MovieSummary.class);
		return new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview(), movieSummary.getPoster_path());
	}
	
	@RequestMapping("/search/{keyword}")
	public List<MovieInfo> getSearchedMovie(@PathVariable("keyword") String keyword) {
		List<MovieInfo> movieList = new ArrayList<MovieInfo>();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(searchAPIURI+keyword, HttpMethod.GET, entity, String.class);
//		MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + keyword, MovieSummary.class);
		movieList = parseBookJson(response.getBody());
		return movieList;
	}

	@RequestMapping("/")
	public List<MovieInfo> getNowPlayingMovie() {
		List<MovieInfo> movieList = new ArrayList<MovieInfo>();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(apiURI, HttpMethod.GET, entity,String.class);
		System.out.println("############## ----->"+response.getBody());
		movieList = parseBookJson(response.getBody());
		return movieList;

	}

	public static List<MovieInfo> parseBookJson(String response) {
		// TODO Auto-generated method stub
		JSONObject movieJsonObject = new JSONObject(response.trim());
		JSONArray docArray = movieJsonObject.getJSONArray("results");
		List<MovieInfo> movieList = new ArrayList<MovieInfo>();
		for (int pos = 0; pos < docArray.length(); pos++) {
			movieList.add(getBookfromJson(docArray.getJSONObject(pos)));
			// System.out.print(docArray.getJSONObject(0));
		}
		return movieList;
	}

	private static MovieInfo getBookfromJson(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		MovieInfo movie = new MovieInfo();
        try {
            if (jsonObject.has("id")) {
            	movie.setMovieId(jsonObject.get("id").toString());
            }
            movie.setPoster_path(jsonObject.has("poster_path") ? jsonObject.get("poster_path").toString() : "");
            movie.setName(jsonObject.has("original_title") ? jsonObject.get("title").toString() : "");
            movie.setDesc(jsonObject.has("overview") ? jsonObject.get("overview").toString() : "");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return movie;
	}
}
