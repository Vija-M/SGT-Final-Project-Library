-- Books definition

CREATE TABLE Books (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	title TEXT NOT NULL,
	isbn TEXT,
	authorID INTEGER NOT NULL,
	yearPublished INTEGER,
	"language" TEXT DEFAULT English,
	pages INTEGER NOT NULL,
	genre TEXT,
	publisher TEXT,
	edition TEXT,
	hasHardCover NUMERIC DEFAULT 0,
	information TEXT,
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


INSERT INTO Books (id, title, isbn, authorID, yearPublished, "language", pages, genre, publisher, edition, hasHardCover, clientID, orderID)
VALUES  (1, 'The Great Gatsby', '9780743273565', 1, 2004, 'English', 180, 'Classic Literature & Fiction', 'Scribner Book Company', 'first', 1, NULL, NULL),
        (2, 'This Side of Paradise', '9781593082437', 1, 2005, 'English', 320, 'Classic Literature & Fiction', 'Barnes & Noble', 'third', 0, NULL, NULL);

INSERT INTO Books (id, title, isbn, authorID, yearPublished, "language", pages, genre, publisher, edition, hasHardCover, information, clientID, orderID)
VALUES(3, 'Zemes dziesma', '9984000389', 2, 1994, 'Latvian', 179, 'Latviešu esejas', 'Preses nams', 'first', 0, '[mākslinieks Vilnis Didrihsons un tehniskā redaktore Olga Lovnika]', NULL, NULL);

INSERT INTO Authors
(authorID, authorName, dateOfBirth, dateOfDeath, authorInfo)
VALUES  (1, 'F. Scott Fitzgerald ', '24.09.1896.', '21.12.1940.', 'F. Scott Fitzgerald was born in St. Paul, Minnesota, in 1896. He attended Princeton University, joined the United States Army during World War I, and published his first novel, This Side of Paradise, in 1920. That same year he married Zelda Sayre and for the next decade the couple lived in New York, Paris, and on the Riviera. Fitzgerald’s masterpieces include The Beautiful and Damned, The Great Gatsby, and Tender Is the Night. He died at the age of forty-four while working on The Last Tycoon. Fitzgerald’s fiction has secured his reputation as one of the most important American writers of the twentieth century.');(2, 'Zenta Mauriņa', '15.12.1897.', '25.04.1978.', 'Rakstniece Zenta Mauriņa (1897–1978) ir latviešu literāri filozofiskās esejas žanra iedibinātāja un izkopēja.  Mauriņas filozofisko un estētisko platformu veido dažādu skolu un virzienu atziņu sintēze, filolozifiskajā metodē dominē subjektīvs skatījums, brīva iztēle, intuīcija. Pievērsusies galvenokārt lielām, izcilām personībām, romantiski un eksistenciāli noskaņotiem autoriem, kuru garīgās pasaules daudzšķautņainību un bagātību parasti idealizē un heroizē. Mauriņas literāri kritiskajos rakstos galvenais objekts ir rakstnieka personība, no kuras tiek meklēts ceļš uz daiļrades īpatnībām.');
        (2, 'Zenta Mauriņa', '15.12.1897.', '25.04.1978.', 'Rakstniece Zenta Mauriņa (1897–1978) ir latviešu literāri filozofiskās esejas žanra iedibinātāja un izkopēja.  Mauriņas filozofisko un estētisko platformu veido dažādu skolu un virzienu atziņu sintēze, filolozifiskajā metodē dominē subjektīvs skatījums, brīva iztēle, intuīcija. Pievērsusies galvenokārt lielām, izcilām personībām, romantiski un eksistenciāli noskaņotiem autoriem, kuru garīgās pasaules daudzšķautņainību un bagātību parasti idealizē un heroizē. Mauriņas literāri kritiskajos rakstos galvenais objekts ir rakstnieka personība, no kuras tiek meklēts ceļš uz daiļrades īpatnībām.');

