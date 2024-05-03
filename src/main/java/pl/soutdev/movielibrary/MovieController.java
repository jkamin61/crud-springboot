package pl.soutdev.movielibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping("")
    public List<Movie> getAll() {
        return movieRepository.getAll();
    }

    @GetMapping("/{id}")
    public Movie getByID(@PathVariable("id") int id) {
        return movieRepository.getByID(id);
    }

    @PostMapping("")
    public int add(@RequestBody List<Movie> movies) {
        return movieRepository.save(movies);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getByID(id);
        if (movie != null) {
            movie.setName(updatedMovie.getName());
            movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 201;
        } else {
            return 404;
        }
    }

    @PatchMapping("/{id}")
    public int partiallyUpdate(@PathVariable("id") int id, @RequestBody Movie updatedMovie) {
        Movie movie = movieRepository.getByID(id);
        if (movie != null) {
            if (updatedMovie.getName() != null) movie.setName(updatedMovie.getName());
            if (updatedMovie.getRating() > 0) movie.setRating(updatedMovie.getRating());

            movieRepository.update(movie);

            return 201;
        } else return 404;
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable("id") int id) {
        Movie movie = movieRepository.getByID(id);
        if (movie != null) {
            movieRepository.delete(id);
            return 200;
        } else return 404;
    }
}
