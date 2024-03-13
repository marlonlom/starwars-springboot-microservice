package dev.marlonlom.demos.microservices.starwars_crud.service;

import java.util.List;
import java.util.Optional;

import dev.marlonlom.demos.microservices.starwars_crud.dto.MovieInfoDto;

public interface MovieInfoService {

	List<MovieInfoDto> getAllMovies();

	boolean existsById(Long movieId);

	Optional<MovieInfoDto> getMovieById(Long movieId);

	MovieInfoDto saveMovie(MovieInfoDto movieInfoDto);

	void deleteMovieById(Long movieId);

}
