
CREATE TABLE IF NOT EXISTS Users(
	uuid INTEGER PRIMARY KEY, 
	username VARCHAR(9) NOT NULL, 
	company_name TEXT NOT NULL, 
	email TEXT NOT NULL, 
	password VARCHAR(15) NOT NULL
);
