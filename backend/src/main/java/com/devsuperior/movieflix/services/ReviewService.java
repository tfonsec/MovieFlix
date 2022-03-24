package com.devsuperior.movieflix.services;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository repository;
	
	@Autowired
	private MovieRepository movieRepository;
    
	@Autowired
	private AuthService authService;
	
	
	
	
	
	@Transactional
	public ReviewDTO insert(ReviewDTO dto) {
		User user = authService.authenticated();
		Review review = new Review();
	    review.setText(dto.getText());
	  
	     Movie movie = movieRepository.getOne(dto.getMovieId());
	    review.setMovie(movie);
	    review.setUser(user);
		review = repository.save(review);
		return new ReviewDTO(review);
	}

	

	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findMovieAndReviews(Long movieId) {
		try {
			List<Review> list = repository.findMovieAndReviews(movieId);
			return list.stream().map(x -> new ReviewDTO(x)).collect(Collectors.toList());
			
		}catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + movieId);
		}	
	}

	

}
