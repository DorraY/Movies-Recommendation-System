/*
 * made with love by DorraY
 */

import java.io.IOException;

public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) throws IOException {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
