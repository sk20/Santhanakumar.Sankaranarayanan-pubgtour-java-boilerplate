package com.sansen.movieinfoservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieInfoServiceApplication {

//	private static final Logger LOGGER = LoggerFactory.getLogger(MovieInfoServiceApplication.class);
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Autowired
//	private Environment environment;

	// Initialize the Lowest and Highest Year when a Movie was/wud be released.
	// Call Rest Endpoint of MovieDb with sorted by release_date param.
//	@Bean
//	CommandLineRunner getCommandLineRunner(){
//		return args -> {
//
//			String apiUrl = environment.getProperty("REST_API_URL_WITH_KEY");
//
//			ResponseEntity<Movie> oldestMovies = restTemplate.getForEntity(apiUrl+"&sort_by=release_date.asc",
//					Movie.class);
//
//			Result oldestMovieResult = oldestMovies.getBody().getResults().get(0);
//			String[] release_date_oldestMovie = oldestMovieResult.getReleaseDate().split("-");
//			MoviesDates.LOWEST_YEAR = Integer.parseInt(release_date_oldestMovie[0]);
//
//			ResponseEntity<Movie> lastestMovies = restTemplate.getForEntity(apiUrl+"&sort_by=release_date.desc",
//					Movie.class);
//			Result lastestMovieResult = lastestMovies.getBody().getResults().get(0);
//			String[] release_date_lastestMovie = lastestMovieResult.getReleaseDate().split("-");
//			MoviesDates.HIGHEST_YEAR = Integer.parseInt(release_date_lastestMovie[0]);
//
//			LOGGER.info("*************************************************************************************");
//			LOGGER.info("The Movie DB Service Contains moves from the Year - {} till the Year - {}",
//					MoviesDates.LOWEST_YEAR, MoviesDates.HIGHEST_YEAR);
//			LOGGER.info("*************************************************************************************");
//
//		};
//	}
	public static void main(String[] args) {
		SpringApplication.run(MovieInfoServiceApplication.class, args);
	}

}
