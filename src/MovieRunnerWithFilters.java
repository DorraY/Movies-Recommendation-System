/*
 * made with love by DorraY
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class MovieRunnerWithFilters {

    public MovieRunnerWithFilters() throws IOException {
    }

    private ThirdRatings thirdRatings = new ThirdRatings("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratings.csv");


    private  void printAverageRatings() throws IOException {

        System.out.println(thirdRatings.getRaterSize());
        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");

        System.out.println("number of movies in the db"+movieDatabase.size());

        System.out.println("Number of movies with ratings " + thirdRatings.getAverageRatings(69));

    }

    public void printAverageRatingsByYear() throws IOException {
        Filter YearAfterFilter = new YearAfterFilter(1997);
        thirdRatings.getAverageRatingsByFilter(3,YearAfterFilter);

        ArrayList<Rating> ratingArrayList = thirdRatings.getAverageRatingsByFilter(3,YearAfterFilter);
        Collections.sort(ratingArrayList);

        for (Rating rating: ratingArrayList) {
            int year = MovieDatabase.getYear(rating.getItem());
            String title = MovieDatabase.getTitle(rating.getItem());
            System.out.println(rating.getValue() + " " + year + " " + title);
        }
    }

    public void printAverageRatingsByGenre() throws IOException {

        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");

        GenreFilter genreFilter = new GenreFilter("Romance");

        ArrayList<Rating> list = thirdRatings.getAverageRatingsByFilter(1,genreFilter);
        Collections.sort(list);

        for (Rating r:list) {
            String Genre = MovieDatabase.getGenres(r.getItem());
            String title = MovieDatabase.getTitle(r.getItem());
            System.out.println(r.getValue() + " " + title + " [" + Genre + "]");
        }
    }

    public void printAverageRatingsByMinutes() throws IOException {

        int minMinutes = 110;
        int maxMinutes = 170;

        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");

        Filter mr = new MinutesFilter(minMinutes,maxMinutes);
        ArrayList<Rating> list = thirdRatings.getAverageRatingsByFilter(1,mr);
        Collections.sort(list);
        System.out.println("found "+ list.size() + " movies");

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            int Time = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue() + " " + "Time: "+ Time+ " "+ Title);
        }
    }

    public void printAverageRatingsByDirectors() throws IOException {

        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");
        System.out.println("read data for "+ MovieDatabase.size() + " movies");
        String directors = "Charles Chaplin,Michael Mann,Spike Jonze";

        Filter dr = new DirectorsFilter(directors);
        ArrayList<Rating> list = thirdRatings.getAverageRatingsByFilter(1,dr);
        Collections.sort(list);
        System.out.println("found "+ list.size() + " movies");

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Directors = MovieDatabase.getDirector(r.getItem());
            System.out.println(r.getValue()+ " " + Title);
            System.out.println("       " + Directors);
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(AllFilters allFilters) throws IOException {

        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");

        YearAfterFilter yearAfterFilter = new YearAfterFilter(1997);
        GenreFilter genreFilter = new GenreFilter("Romance");

        AllFilters filtersList = new AllFilters();
        filtersList.addFilter(yearAfterFilter);
        filtersList.addFilter(genreFilter);

        ArrayList<Rating> list = thirdRatings.getAverageRatingsByFilter(1,filtersList);
        Collections.sort(list);

        for(Rating r:list){
            String Title = MovieDatabase.getTitle(r.getItem());
            String Genre = MovieDatabase.getGenres(r.getItem());
            int Year = MovieDatabase.getYear(r.getItem());

            System.out.println(r.getValue()+ " " + Year +" "+ Title );
            System.out.println("       "+ Genre);
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() throws IOException {

        MovieDatabase movieDatabase = new MovieDatabase();
        movieDatabase.initialize("/home/dorra/Documents/Professional stuff/projects/RecommendationSystem/src/data/ratedmoviesfull.csv");

        String directors = "Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola";


        MinutesFilter minutesFilter = new MinutesFilter(30,170);
        DirectorsFilter directorsFilter = new DirectorsFilter(directors);

        AllFilters filtersList = new AllFilters();

        filtersList.addFilter(directorsFilter);
        filtersList.addFilter(minutesFilter);


        ArrayList<Rating> list = thirdRatings.getAverageRatingsByFilter(1,filtersList);
        Collections.sort(list);

        for(Rating r:list){
            String matechedTitle = MovieDatabase.getTitle(r.getItem());
            String matchedDirectors = MovieDatabase.getDirector(r.getItem());
            int matchMinutes = MovieDatabase.getMinutes(r.getItem());
            System.out.println(r.getValue()+ " "+ " Time: "+matchMinutes+ " " +matechedTitle);
            System.out.println("      " + matchedDirectors);
        }

    }





}
