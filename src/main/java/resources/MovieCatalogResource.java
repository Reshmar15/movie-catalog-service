package resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import models.CatalogItem;
import models.Movie;
import models.Rating;
import models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	WebClient.Builder webClientBuilder;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping (value ="/{userId}", method = RequestMethod.GET)
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
	
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratings/users/"+userId
		, UserRating.class);
		
		System.out.println(ratings.getRatings());
		return ratings.getRatings().stream().map(
				rating -> {
			Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId()
							, Movie.class);	
//			Movie movie = webClientBuilder.build()
//			.get()
//			.uri("http://localhost:8082/movies/"+rating.getMovieId())
//			.retrieve()
//			.bodyToMono(Movie.class)
//			.block();
			 return new CatalogItem(movie.getMovieId(), "test", rating.getRating());
			}).collect(Collectors.toList());
		}


}
