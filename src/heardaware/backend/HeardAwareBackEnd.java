package heardaware.backend;

import blazing.Blazing;

/**
 *
 * @author School EC
 */
public class HeardAwareBackEnd {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		Blazing.createServer(BackendService.class);
	}
}
