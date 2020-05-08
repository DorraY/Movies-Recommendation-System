/*
 * made with love by DorraY
 */

/*
 * made with love by DorraY
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DirectorsFilter implements Filter {

    private String directors;

    public DirectorsFilter(String directors) {
        this.directors = directors;
    }

    @Override
    public boolean satisfies(String id) throws IOException {
        ArrayList<String> dirList= new ArrayList(Arrays.asList(directors.split(",")));
        for(String dir:dirList){
            if(MovieDatabase.getDirector(id).contains(dir)){
                return true;
            }
        }
        return false;

    }


}
