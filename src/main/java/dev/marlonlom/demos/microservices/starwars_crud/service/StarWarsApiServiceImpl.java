package dev.marlonlom.demos.microservices.starwars_crud.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import dev.marlonlom.demos.microservices.starwars_crud.dto.MovieInfoDto;
import dev.marlonlom.demos.microservices.starwars_crud.dto.StarWarsFilmDto;

@Service
public class StarWarsApiServiceImpl implements StarWarsApiService {

	@Override
	public Optional<MovieInfoDto> getFilmById(Integer filmId) {
		try {
			final String uri = "https://swapi.info/api/films/".concat(String.valueOf(filmId));
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<StarWarsFilmDto> foundMovieInfoResponse = restTemplate.getForEntity(uri,
					StarWarsFilmDto.class);
			if (foundMovieInfoResponse.hasBody() && !foundMovieInfoResponse.getStatusCode().isError()) {
				StarWarsFilmDto filmDto = foundMovieInfoResponse.getBody();
				MovieInfoDto movieInfoDto = MovieInfoDto.builder().movieId(filmId)
						.episodeId((int) filmDto.getEpisode_id()).movieTitle(filmDto.getTitle())
						.releaseDate(filmDto.getRelease_date()).build();
				return Optional.of(movieInfoDto);
			}
			return Optional.empty();
		} catch (Exception e) {
			return Optional.empty();
		}
	}

}
