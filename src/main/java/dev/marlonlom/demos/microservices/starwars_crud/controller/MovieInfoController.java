package dev.marlonlom.demos.microservices.starwars_crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.marlonlom.demos.microservices.starwars_crud.dto.MovieInfoDto;
import dev.marlonlom.demos.microservices.starwars_crud.service.MovieInfoService;
import dev.marlonlom.demos.microservices.starwars_crud.service.StarWarsApiService;

@RestController
@RequestMapping("movies_info/api/v1")
public class MovieInfoController {

	@Autowired
	private MovieInfoService movieInfoService;

	@Autowired
	private StarWarsApiService starWarsApiService;

	@GetMapping("films")
	public List<MovieInfoDto> getAllMovies() {
		return movieInfoService.getAllMovies();
	}

	@GetMapping("films/{filmId}")
	public ResponseEntity<?> getMovieById(@PathVariable("filmId") String movieId) {
		if (!movieId.matches("^[0-9]{1,2}$")) {
			return new ResponseEntity<>("Error en la solicitud", HttpStatus.BAD_REQUEST);
		}

		final Long validMovieId = Long.valueOf(movieId);

		boolean existsMovieInfo = movieInfoService.existsById(validMovieId);
		if (!existsMovieInfo) {
			Optional<MovieInfoDto> movieFromRemote = starWarsApiService.getFilmById(validMovieId.intValue());
			if (movieFromRemote.isPresent()) {
				MovieInfoDto dto = movieFromRemote.get();
				movieInfoService.saveMovie(dto);
				return ResponseEntity.ok(dto);
			}
			return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
		}

		Optional<MovieInfoDto> movieFromLocal = movieInfoService.getMovieById(validMovieId);
		if (movieFromLocal.isPresent()) {
			return ResponseEntity.ok(movieFromLocal.get());
		}

		return (ResponseEntity<?>) ResponseEntity.notFound();

	}

	@PutMapping("films/{filmId}")
	public ResponseEntity<?> updateMovie(@RequestBody MovieInfoDto movieInfoDto, @PathVariable("filmId") String movieId) {
		if (!movieId.matches("^[0-9]{1,2}$")) {
			return new ResponseEntity<>("Error en la solicitud", HttpStatus.BAD_REQUEST);
		}

		final Long validMovieId = Long.valueOf(movieId);

		boolean existsMovieInfo = movieInfoService.existsById(validMovieId);
		if (!existsMovieInfo) {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}
		
		movieInfoDto.setMovieId(validMovieId.intValue());
		movieInfoService.saveMovie(movieInfoDto);
		
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@DeleteMapping("films/{filmId}")
	public ResponseEntity<?> deleteMovieById(@PathVariable("filmId") String movieId) {
		if (!movieId.matches("^[0-9]{1,2}$")) {
			return new ResponseEntity<>("Error en la solicitud", HttpStatus.BAD_REQUEST);
		}

		final Long validMovieId = Long.valueOf(movieId);

		boolean existsMovieInfo = movieInfoService.existsById(validMovieId);
		if (!existsMovieInfo) {
			return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
		}

		movieInfoService.deleteMovieById(validMovieId);
		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
