package heardaware.backend;

import blazing.BlazingLog;
import blazing.BlazingResponse;
import blazing.Initializer;
import blazing.Post;
import blazing.WebServer;
import java.sql.*;
import blazing.crypto.HashUtils;
import blazing.json.JSon;
import java.util.HashMap;

/**
 *
 * @author School EC
 */
@WebServer
public class BackendService {

	private static Connection conn;

	@Initializer
	public static void init() {
		String url = "jdbc:sqlite:db/packages.db";
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			BlazingLog.panic(ex.getMessage());
		}
	}

	@Post("/v1/users/signup")
	public static void signup(BlazingResponse response) {
		var params = response.params();
		String company_name = params.get("company_name");
		String email = params.get("email");
		String password = params.get("password");
		String password_hash = HashUtils.hash(password).unwrap();

		String sql
			= """
 INSERT 
 	INTO Users (company_name, email, password) 
 	VALUES (?, ?, ?)
  """;

		HashMap<String, Object> map = new HashMap<>();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, company_name);
			stmt.setString(2, email);
			stmt.setString(3, password_hash);
			int rows = stmt.executeUpdate();
			if (rows != 1) {
				map.put("status", 500);
				map.put("message", "Failed to insert user");
			} else {
				map.put("status", 200);
				map.put("message", "User signup successful");
			}
		} catch (SQLException ex) {
			map.put("status", 404);
			map.put("message", "Fatal: " + ex.getMessage());
		} finally {
			var json = JSon.from(map).unwrap();
			response.sendResponse(json);
		}
	}

	@Post("/v1/users/login")
	public static void emailLogin(BlazingResponse response) {
		var params = response.params();
		String email = params.get("email");
		String password = params.get("password");

		String sql
			= """
  	SELECT * FROM users WHERE email = ? and password = ?
    """;

		HashMap<String, Object> map = new HashMap<>();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}

			if (count == 0) {
				map.put("status", 404);
				map.put("message", "User does not exits");
			} else {
				map.put("status", 200);
				map.put("message", "Login successful");
			}
		} catch (SQLException ex) {
			map.put("status", 500);
			map.put("message", "Fatal: " + ex.getMessage());
		} finally {
			var json = JSon.from(map);
			response.sendResponse(sql);
		}
	}

	@Post("/v1/users/email/exits")
	public static void emailExists(BlazingResponse response) {
		var params = response.params();
		String email = params.get("email");
		String sql
			= """
  	SELECT * FROM users WHERE email = ? 
    """;

		HashMap<String, Object> map = new HashMap<>();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			int count = 0;
			while (rs.next()) {
				count++;
			}

			if (count == 0) {
				map.put("status", 200);
				map.put("message", "User does not exits");
			} else {
				map.put("status", 400);
				map.put("message", "Email already exist");
			}
		} catch (SQLException ex) {
			map.put("status", 500);
			map.put("message", "Fatal: " + ex.getMessage());
		} finally {
			var json = JSon.from(map);
			response.sendResponse(sql);
		}
	}

}
