package mate.academy.controller;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.model.Movie;
import mate.academy.model.dto.MovieDto;
import mate.academy.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public String add(@RequestBody MovieDto movieDto) {
        movieService.add(convertFromDto(movieDto));
        return "Success";
    }

    @GetMapping
    public List<MovieDto> getAll() {
        return movieService.getAll().stream()
        .map(this::convertToMovieDto)
                .collect(Collectors.toList());
    }

    private Movie convertFromDto(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        return movie;
    }

    private MovieDto convertToMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setTitle(movie.getTitle());
        movieDto.setDescription(movie.getDescription());
        return movieDto;
    }
}
