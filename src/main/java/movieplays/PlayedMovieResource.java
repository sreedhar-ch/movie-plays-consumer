package movieplays;

import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/movies")
public class PlayedMovieResource {

    @Channel("played-movies")
    Multi<PlayedMovie> playedMovies;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<PlayedMovie> stream() {
        return playedMovies;
    }

    @Incoming("movies")
    public void newMovie(Movie movie) {
        //logger.infov("New movie: {0}", movie);
        System.out.println("Consuming movie: id="+ movie.id+"::movie name="+movie.name);
    }

    /*@Incoming("played-movies")
    public void playedMovies(PlayedMovie movie) {
        //logger.infov("New movie: {0}", movie);
        System.out.println("Consuming played-movies: id="+ movie.id+"::movie duration="+movie.duration);
    }*/
}
