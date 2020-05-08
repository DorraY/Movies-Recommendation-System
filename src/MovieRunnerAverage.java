/*
 * made with love by DorraY
 */



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
    private static void printAverageRatings() throws IOException {
        SecondRatings secondRatings = new SecondRatings();
        System.out.println("Number of movies " + secondRatings.getMovieSize());
        System.out.println("Number of raters " + secondRatings.getRaterSize());

        ArrayList<Rating> AverageRatings = secondRatings.getAverageRatings(69);
        Collections.sort(AverageRatings);

        for (Rating currentRating: AverageRatings) {
            System.out.print(currentRating.getValue() + " "
            +secondRatings.getTitle(currentRating.getItem()));
            System.out.println();
        }
    }

    private static void getAverageRatingOneMovie() throws IOException {
        SecondRatings secondRatings = new SecondRatings();

        String movieTitle = "Moneyball";

        String movieId = secondRatings.getID(movieTitle);
        System.out.println("*************");
        System.out.println(secondRatings.getAverageByID(movieId,1));

    }






    public static void main(String[] args) throws IOException {

        printAverageRatings();
        getAverageRatingOneMovie();

        ThirdRatings thirdRatings = new ThirdRatings("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratings.csv");
        thirdRatings.getAverageRatings(3);



    }
}
