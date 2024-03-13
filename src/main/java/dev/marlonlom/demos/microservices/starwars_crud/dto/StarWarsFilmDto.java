package dev.marlonlom.demos.microservices.starwars_crud.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StarWarsFilmDto {
	private String title;
	private float episode_id;
	private String opening_crawl;
	private String director;
	private String producer;
	private String release_date;
	private List<String> characters;
	private List<String> planets;
	private List<String> starships;
	private List<String> vehicles;
	private List<String> species;
	private String created;
	private String edited;
	private String url;
}
