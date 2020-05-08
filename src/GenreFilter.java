/*
 * made with love by DorraY
 */

import java.io.IOException;

public class GenreFilter implements Filter {

    private String genre;

    public GenreFilter(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String id) throws IOException {
        return MovieDatabase.getGenres(id).contains(genre) ;
    }


}
