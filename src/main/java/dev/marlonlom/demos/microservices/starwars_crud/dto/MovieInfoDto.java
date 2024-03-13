package dev.marlonlom.demos.microservices.starwars_crud.dto;

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
public class MovieInfoDto {
	private Integer movieId;
	private Integer episodeId;
	private String movieTitle;
	private String releaseDate;
}
