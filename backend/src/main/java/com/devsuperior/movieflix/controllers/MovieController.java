package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dto.MovieDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import com.devsuperior.movieflix.services.ReviewService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping   
    public ResponseEntity<Page<MovieDTO>> findByGenre(
		@RequestParam(value = "genreId", defaultValue = "0")
		Long genreId, Pageable pegeable) {
				
    	Page<MovieDTO> list = service.findByGenre(genreId, pegeable);
        return ResponseEntity.ok().body(list);
     }
	
	
	
	@GetMapping(value ="/{id}")
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id) {
		MovieDTO movieDTO = service.findById(id);
		return ResponseEntity.ok().body(movieDTO);
	}
	
	@GetMapping(value= "/{movieId}/reviews")
	public ResponseEntity<List<ReviewDTO>> findMovieAndReviews(@PathVariable Long movieId){
		List<ReviewDTO> review = reviewService.findMovieAndReviews(movieId);
		return ResponseEntity.ok().body(review);
	}
}	
