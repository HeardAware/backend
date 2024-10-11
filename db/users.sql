
CREATE TABLE IF NOT EXISTS Users(
	uuid INTEGER PRIMARY KEY, 
	company_name TEXT NOT NULL, 
	email TEXT NOT NULL, 
	password TEXT NOT NULL
);


INSERT 
	INTO Users (company_name, email, password) 
	VALUES ('herdaware', 'admin@herdaware.com', 'admin');
	
--SELECT username FROM Users WHERE username = ?

