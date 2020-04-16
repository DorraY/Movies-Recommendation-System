
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.IOException;
import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<PlainRater> myPlainRaters;
    
    public SecondRatings() throws IOException {
        // default constructor
        this("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv",
                "/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratings.csv");
    }

    public SecondRatings(String movieFile, String ratingsFile) throws IOException {
        myMovies = FirstRatings.loadMovies(movieFile);
        myPlainRaters = FirstRatings.loadRaters(ratingsFile);
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myPlainRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters) {
        int numberOfRates =FirstRatings.getRatingsPerMovie(myPlainRaters,id);
        if (numberOfRates<minimalRaters) {
            return 0;
        }
        else {
            double Average=0;
            for (PlainRater currentPlainRater : myPlainRaters) {
                if (currentPlainRater.hasRating(id)) {
                    Average+= currentPlainRater.getRating(id);
                }
            }
            Average= Average/numberOfRates;
            return Average ;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> MoviesAverageRating = new ArrayList<>();
        for (Movie currentMovie: myMovies) {
            if (getAverageByID(currentMovie.getID(),minimalRaters)!=0) {
                MoviesAverageRating.add(new Rating(currentMovie.getID(),getAverageByID(currentMovie.getID(),minimalRaters)));
            }

        }
        return MoviesAverageRating;
    }

    public String getTitle(String id) {
        for (Movie currentMovie: myMovies) {
            if (currentMovie.getID().equals(id)) {
                return currentMovie.getTitle();
            }
        }
        return "The id doesn't correspond to any movie.";
    }

    public String  getID(String title) {
        for (Movie currentMovie: myMovies) {
            if (currentMovie.getTitle().equals(title)) {
                return currentMovie.getID();
            }
        }
        return "The title doesn't correspond to any id";
    }
}
