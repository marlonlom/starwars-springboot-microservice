package dev.marlonlom.demos.microservices.starwars_crud.service;

import java.util.Optional;

import dev.marlonlom.demos.microservices.starwars_crud.dto.MovieInfoDto;

public interface StarWarsApiService {
	Optional<MovieInfoDto> getFilmById(Integer filmId);
}
