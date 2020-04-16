/*
 * made with love by DorraY
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;

import org.apache.commons.csv.*;
public class FirstRatings {


    private static ArrayList<Movie> getSpecificCondition(ArrayList<Movie> moviesList, Predicate<Movie> moviePredicate) {
        ArrayList<Movie> result = new ArrayList<>();
        for (Object m : moviesList.stream().filter(moviePredicate).toArray(Movie[]::new)) {
            result.add((Movie) m);
        }
        return  result;
    }


    public static ArrayList<Movie> loadMovies(String filename) throws IOException {
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        CSVParser parser = new CSVParser(new FileReader(filename), format);

        ArrayList<Movie> movies = new ArrayList<>();
        for (CSVRecord record: parser) {
            Movie movie = new Movie(record.get("id"),record.get("title"),record.get("year"),record.get("genre"),record.get("director"),record.get("country"),record.get("poster"),Integer.parseInt(record.get("minutes")));
            movies.add(movie);
        }
        parser.close();

        return  movies;
    }

    public static int getRatingsPerMovie(ArrayList<PlainRater> plainRaters, String movie_id) {
        int numberofRatings=0;
        for (PlainRater currentPlainRater : plainRaters) {
            if (currentPlainRater.hasRating(movie_id)) {
                numberofRatings++;
            }
        }
        return numberofRatings;
    }

    public static ArrayList<PlainRater> loadRaters(String filename) throws IOException {
        CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
        CSVParser parser = new CSVParser(new FileReader(filename), format);
        ArrayList<PlainRater> ratersList = new ArrayList<>();
        ArrayList<String> raterIDList = new ArrayList<>();
        for (CSVRecord record: parser) {
            if (!raterIDList.contains(record.get("rater_id"))) {
                raterIDList.add(record.get("rater_id"));
                PlainRater newPlainRater = new PlainRater(record.get("rater_id"));
                ratersList.add(newPlainRater);
                newPlainRater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));
            }
            else {
                PlainRater currentPlainRater = ratersList.get(Integer.parseInt(record.get("rater_id"))-1);
                currentPlainRater.addRating(record.get("movie_id"),Double.parseDouble(record.get("rating")));


            }
        }
        parser.close();
        return  ratersList;

    }


    public static void testLoadRaters() throws IOException {
        ArrayList<PlainRater> ratersList = loadRaters("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratings.csv");

        System.out.println("Number of raters is " + ratersList.size());

        int numberOfRatingsPerRater = ratersList.get(193-1).numRatings();
        System.out.println("Rater with id 193 has " + numberOfRatingsPerRater + " ratings");

        HashMap<String,Integer> ratersWithNumbers = new HashMap<>();

        for (PlainRater currentPlainRater : ratersList) {
            ratersWithNumbers.put(currentPlainRater.getID(), currentPlainRater.numRatings());

        }

        System.out.print("Raters with Maximum number of rates with a number of " + (Collections.max(ratersWithNumbers.values())) + " are ");
        for (String s: ratersWithNumbers.keySet()) {
            if (ratersWithNumbers.get(s).equals(Collections.max(ratersWithNumbers.values()))) {
                System.out.println(s);
            }
        }

        System.out.println("Movie with id 0068646 has a number of ratings of " + getRatingsPerMovie(ratersList,"0068646"));

        HashMap<String,Integer> movieRatingCounts = new HashMap<>();

        for (PlainRater currentPlainRater : ratersList) {
            for (int j = 0; j < currentPlainRater.numRatings(); j++) {
                String currentMovieID = currentPlainRater.getItemsRated().get(j);
                if (movieRatingCounts.containsKey(currentMovieID)) {
                    movieRatingCounts.put(currentMovieID, movieRatingCounts.get(currentMovieID) + 1);
                } else {
                    movieRatingCounts.put(currentMovieID, 1);
                }
            }
        }
        System.out.println("Number of different movies rated by all raters  " + movieRatingCounts.size());

    }

    public static void main(String[] args) throws IOException {
        ArrayList<Movie> movieFullList ;
        ArrayList<String> directorsList;


        movieFullList = loadMovies("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");

        System.out.println(movieFullList);

        System.out.println("Number of movies which include the Comedy genre "+getSpecificCondition(movieFullList,m -> m.getGenres().contains("Comedy")).size());

        System.out.println("Number of movies which are longer than 150 minutes " +getSpecificCondition(movieFullList,m -> m.getMinutes()>=150).size());

        HashMap<String,Integer> directors = new HashMap<>();

        for (Movie currentMovie : movieFullList) {
            String[] mutipleDirectors = currentMovie.getDirector().split(",");
            for (String director : mutipleDirectors) {

                if (!directors.containsKey(director)) {
                    directors.put(director, 1);
                } else {
                    directors.put(director, directors.get(director) + 1);
                }
            }

        }

        System.out.print("Directors with Maximum number of movies : ");

        for (String s: directors.keySet()) {
            if (directors.get(s).equals(Collections.max(directors.values()))) {
                System.out.println(s);
            }
        }

        System.out.println("Number of movies he directed is " + Collections.max(directors.values()));

        testLoadRaters();
    }

}
