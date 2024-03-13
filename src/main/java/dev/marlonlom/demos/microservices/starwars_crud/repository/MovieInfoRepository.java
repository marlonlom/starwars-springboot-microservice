package dev.marlonlom.demos.microservices.starwars_crud.repository;

import org.springframework.data.repository.CrudRepository;

import dev.marlonlom.demos.microservices.starwars_crud.model.MovieInfo;

public interface MovieInfoRepository extends CrudRepository<MovieInfo, Long> {

}
