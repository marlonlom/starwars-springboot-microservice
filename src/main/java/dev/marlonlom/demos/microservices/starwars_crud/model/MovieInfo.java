package dev.marlonlom.demos.microservices.starwars_crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "starwars_movie")
public class MovieInfo {
	@Id
	@Column(name = "movie_id")
	@GeneratedValue
	private Long movieId;
	@Column(name = "movie_episode_id")
	private Long episodeId;
	@Column(name = "movie_title")
	private String movieTitle;
	@Column(name = "movie_release_data")
	private String releaseDate;
}
