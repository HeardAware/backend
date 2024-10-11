package herdaware;

import blazing.Blazing;
import heardaware.backend.BackendService;

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
