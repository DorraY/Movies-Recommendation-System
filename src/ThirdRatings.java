/*
 * made with love by DorraY
 */

import java.io.IOException;
import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<PlainRater> myPlainRaters;

    public ThirdRatings() throws IOException {
        // default constructor
        this("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratings.csv");
    }

    public ThirdRatings(String ratingsFile) throws IOException {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        myPlainRaters = FirstRatings.loadRaters(ratingsFile);
        getAverageRatings(1);
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


    public void getAverageRatings(int minimalRaters) throws IOException{
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> MoviesAverageRating = new ArrayList<>();
        int i=-1;
        for (String currentMovie: movies) {
            i++;
            if (getAverageByID(currentMovie,minimalRaters)!=0) {
                MoviesAverageRating.add(new Rating(currentMovie,getAverageByID(currentMovie,minimalRaters)));
                System.out.println(MoviesAverageRating.get(i));
            }
        }
        //return MoviesAverageRating;
    }





}

