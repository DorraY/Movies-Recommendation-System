/*
 * made with love by DorraY
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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


    public int getAverageRatings(int minimalRaters) throws IOException{
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> MoviesAverageRating = new ArrayList<>();

        int i=0;
        for (String currentMovie: movies) {
            if (getAverageByID(currentMovie,minimalRaters)!=0) {
                MoviesAverageRating.add(new Rating(currentMovie,getAverageByID(currentMovie,minimalRaters)));
                System.out.println(MoviesAverageRating.get(i));
                System.out.println(i);
                i++;
            }
        }
        return MoviesAverageRating.size();
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) throws IOException {

        ArrayList<Rating> averageRatingListByFilter = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);

        for (String id:movies) {
            double avg = getAverageByID(id,minimalRaters);
            if (avg!=0) {
                Rating currentRating = new Rating(id,avg);
                averageRatingListByFilter.add(currentRating);
            }
        }
        return averageRatingListByFilter;



    }





}

