
CREATE TABLE IF NOT EXISTS Users(
	uuid INTEGER PRIMARY KEY, 
	company_name TEXT NOT NULL, 
	email TEXT NOT NULL, 
	password TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS LiveStock(
	lid INTEGER PRIMARY KEY, 
	kind TEXT NOT NULL, 
	temp DECIMAL NOT NULL,
	heart_rate DECIMAL NOT NULL, 
	age INTEGER NOT NULL,
	births INTEGER NOT NULL,
	location TEXT NOT NULL
); 

INSERT 
	INTO Users (company_name, email, password) 
	VALUES ('herdaware', 'admin@herdaware.com', 'admin');
	
INSERT 
	INTO LiveStock(kind, temp, heart_rate, age, births, location) 
	values ('Cow', 33.5, 56.433, 10, 4, '-22.9818761,30.4635316,3a,75y,269.52h,90t');
	
--SELECT username FROM Users WHERE username = ?


