/*
 * made with love by DorraY
 */

import java.io.IOException;

public class MinutesFilter implements Filter {

    private int max;
    private int min;

    public MinutesFilter(int max,int min) {
        this.max = max;
        this.min = min;
    }

    @Override
    public boolean satisfies(String id) throws IOException {
        return MovieDatabase.getMinutes(id)>=min && MovieDatabase.getMinutes(id)<=max;

    }

}
