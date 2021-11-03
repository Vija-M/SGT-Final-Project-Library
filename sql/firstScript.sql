-- Books definition

CREATE TABLE Books (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	title TEXT NOT NULL,
	isbn TEXT NOT NULL,
	authorID INTEGER NOT NULL,
	yearPublished INTEGER NOT NULL,
	"language" TEXT DEFAULT English NOT NULL,pages INTEGER NOT NULL,
	genre TEXT NOT NULL,
	publisher TEXT NOT NULL,
	edition TEXT NOT NULL,
	hasHardCover NUMERIC DEFAULT 0 NOT NULL,
	clientID INTEGER,
	orderID INTEGER
);


-- Authors definition

CREATE TABLE Authors (
	authorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	authorName TEXT NOT NULL,
	dateOfBirth DATE NOT NULL,
	dateOfDeath DATE,
	authorInfo TEXT
);


INSERT INTO Books (