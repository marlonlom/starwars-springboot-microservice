package dev.marlonlom.demos.microservices.starwars_crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.marlonlom.demos.microservices.starwars_crud.dto.MovieInfoDto;
import dev.marlonlom.demos.microservices.starwars_crud.model.MovieInfo;
import dev.marlonlom.demos.microservices.starwars_crud.repository.MovieInfoRepository;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

	@Autowired
	private MovieInfoRepository movieInfoRepository;

	@Override
	public List<MovieInfoDto> getAllMovies() {
		List<MovieInfo> allMovieInfos = (List<MovieInfo>) movieInfoRepository.findAll();
		return allMovieInfos.stream()
				.map((entity) -> MovieInfoDto.builder().movieId(entity.getMovieId().intValue())
						.episodeId(entity.getEpisodeId().intValue()).movieTitle(entity.getMovieTitle())
						.releaseDate(entity.getReleaseDate()).build())
				.toList();
	}

	@Override
	public boolean existsById(Long movieId) {
		return movieInfoRepository.existsById(movieId);
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<MovieInfoDto> getMovieById(Long movieId) {
		try {
			MovieInfo entity = movieInfoRepository.findById(movieId).orElseThrow();
			return Optional.of(MovieInfoDto.builder().movieId(entity.getMovieId().intValue())
					.episodeId(entity.getEpisodeId().intValue()).movieTitle(entity.getMovieTitle())
					.releaseDate(entity.getReleaseDate()).build());
		} catch (Exception exception) {
			return Optional.empty();
		}
	}

	@Transactional
	@Override
	public MovieInfoDto saveMovie(MovieInfoDto movieInfoDto) {
		MovieInfo movieInfo = MovieInfo.builder().movieId(Long.valueOf(movieInfoDto.getMovieId()))
				.episodeId(Long.valueOf(movieInfoDto.getEpisodeId())).movieTitle(movieInfoDto.getMovieTitle())
				.releaseDate(movieInfoDto.getReleaseDate()).build();
		movieInfoRepository.save(movieInfo);
		return movieInfoDto;
	}

	@Transactional
	@Override
	public void deleteMovieById(Long movieId) {
		movieInfoRepository.deleteById(movieId);
	}

}
