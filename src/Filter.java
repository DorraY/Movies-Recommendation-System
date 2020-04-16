
/*
 * made with love by DorraY
 */

import java.io.IOException;

public interface Filter {
	boolean satisfies(String id) throws IOException;
}
