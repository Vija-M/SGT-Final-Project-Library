-- Books definition

CREATE TABLE Books (
	id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	title TEXT NOT NULL,
	isbn VARCHAR(13),
	authorID INTEGER NOT NULL,
	yearPublished VARCHAR(4),
	"language" TEXT DEFAULT English,
	pages INTEGER NOT NULL,
	genre TEXT,
	publisher TEXT,
	edition VARCHAR(20),
	hasHardCover NUMERIC DEFAULT 0,
	information TEXT,
	userID INTEGER,
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

-- Users definition

CREATE TABLE Users (
	userID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	userFirstName TEXT NOT NULL,
	userLastName TEXT NOT NULL,
	email VARCHAR,
	phone VARCHAR(10),
	birthDate DATE,
	address VARCHAR(70),
	city TEXT(20) DEFAULT Riga,
	country TEXT(20) DEFAULT Latvia,
	postalCode VARCHAR(10),
	userHistory TEXT(200) DEFAULT Good
);

-- Roles and access definition

CREATE TABLE RolesAndAccess (
	roleID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	role TEXT NOT NULL,
    isLibrarian NUMERIC DEFAULT 0
);

-- User access definition

CREATE TABLE UserAccess (
	userAccessID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	userID INTEGER NOT NULL,
	roleID INTEGER NOT NULL,
);

-- Orders definition

CREATE TABLE Orders (
	orderID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
	bookID INTEGER NOT NULL,
	userID INTEGER NOT NULL,
	issueDate DATE NOT NULL,
	returnDate DATE NOT NULL,
	information VARCHAR(200)
);



INSERT INTO Books (id, title, isbn, authorID, yearPublished, pages, genre, publisher, edition, hasHardCover, userID, orderID)
VALUES  (1, 'The Great Gatsby', '9780743273565', 1, 2004, 180, 'Classic Literature & Fiction', 'Scribner Book Company', 'first', 1, NULL, NULL),
        (2, 'This Side of Paradise', '9781593082437', 1, 2005, 320, 'Classic Literature & Fiction', 'Barnes & Noble', 'third', 0, NULL, NULL);


INSERT INTO Books (title, isbn, authorID, yearPublished, "language", pages, genre, publisher, edition, hasHardCover, information, userID, orderID)
VALUES  ('Zemes dziesma', '9984000389', 2, 1994, 'Latvian', 179, 'Latviešu esejas', 'Preses nams', 'first', 0, '[mākslinieks Vilnis Didrihsons un tehniskā redaktore Olga Lovnika]', NULL, NULL),
        ('Baltais ceļš', '99984043045', 2, 1997, 'Latvian', 164, 'Latviešu literatūras pētniecība', 'Zvaigzne ABC', 'first', 0, 'studija par Annu Brigaderi', NULL, NULL),
        ('Dzejnieka uzdevums mūsdienās', '9984048829', 2, 1997, 'Latvian', 117, 'Esejas', 'Zvaigzne ABC', 'first', 1, 'Dzejnieki un akrobāti, kas manipulē ar vārdiem ; Dzīvesgudrie sirdsapziņas un ticības dziesminieki [no vācu valodas tulkojusi Rasma Vīlipa ; redaktore B. Cimermane ; pēcvārda autore Ingrīda Sokolova]', NULL, NULL);

INSERT INTO Books (title, isbn, authorID, yearPublished, pages, genre, publisher, hasHardCover, information, userID, orderID)
VALUES  ('The Jack London Collection: The Call of the Wild, White Fang, To Build a Fire', '9781706754138', 3, 2019, 540, 'novels', 'Independently Published', 0, 'The Call of the Wild and White Fang, both set in the Klondike Gold Rush, as well as the short story To Build a Fire. Here you will find all three of these great works to enjoy in this John London collection', NULL, NULL),
        ('White Fang ; The Call of the Wild', '97801406211143',3,1994, 278, 'novels', 'Penguin Books', 0, 'American novels', NULL, NULL);


INSERT INTO Authors
(authorID, authorName, dateOfBirth, dateOfDeath, authorInfo)
VALUES  (1, 'F. Scott Fitzgerald ', '1896-09-24', '1940-12-21', 'F. Scott Fitzgerald was born in St. Paul, Minnesota, in 1896. He attended Princeton University, joined the United States Army during World War I, and published his first novel, This Side of Paradise, in 1920. That same year he married Zelda Sayre and for the next decade the couple lived in New York, Paris, and on the Riviera. Fitzgerald’s masterpieces include The Beautiful and Damned, The Great Gatsby, and Tender Is the Night. He died at the age of forty-four while working on The Last Tycoon. Fitzgerald’s fiction has secured his reputation as one of the most important American writers of the twentieth century.');(2, 'Zenta Mauriņa', '15.12.1897.', '25.04.1978.', 'Rakstniece Zenta Mauriņa (1897–1978) ir latviešu literāri filozofiskās esejas žanra iedibinātāja un izkopēja.  Mauriņas filozofisko un estētisko platformu veido dažādu skolu un virzienu atziņu sintēze, filolozifiskajā metodē dominē subjektīvs skatījums, brīva iztēle, intuīcija. Pievērsusies galvenokārt lielām, izcilām personībām, romantiski un eksistenciāli noskaņotiem autoriem, kuru garīgās pasaules daudzšķautņainību un bagātību parasti idealizē un heroizē. Mauriņas literāri kritiskajos rakstos galvenais objekts ir rakstnieka personība, no kuras tiek meklēts ceļš uz daiļrades īpatnībām.'),
        (2, 'Zenta Mauriņa', '1897-12-15', '1978-04-25', 'Rakstniece Zenta Mauriņa (1897–1978) ir latviešu literāri filozofiskās esejas žanra iedibinātāja un izkopēja.  Mauriņas filozofisko un estētisko platformu veido dažādu skolu un virzienu atziņu sintēze, filolozifiskajā metodē dominē subjektīvs skatījums, brīva iztēle, intuīcija. Pievērsusies galvenokārt lielām, izcilām personībām, romantiski un eksistenciāli noskaņotiem autoriem, kuru garīgās pasaules daudzšķautņainību un bagātību parasti idealizē un heroizē. Mauriņas literāri kritiskajos rakstos galvenais objekts ir rakstnieka personība, no kuras tiek meklēts ceļš uz daiļrades īpatnībām.'),
        (3, 'Jack London', '1876-01-12', '1916-11-22', 'John London (January 12, 1876 - November 22, 1916) was an American novelist, journalist, and social activist. A pioneer in the world of commercial magazine fiction, he was one of the first writers to become a worldwide celebrity and earn a large fortune from writing.');

INSERT INTO Users
(userID, userFirstName,	userLastName, email, phone,	birthDate,	address, postalCode)
VALUES  (1, 'Anna', 'Smith', 'smith@library.com', '1234567', '1978-09-25',  'London str.2-5', 'AA-1234'),
        (2, 'Anna', 'Green', 'green@library.com', '1334567', '1987-09-24',  'Ligo str.2-5', 'AA-2234'),
        (3, 'Peter', 'Green', 'greenp@library.com', '1434567', '1948-12-24',  'Ligo str.2-5', 'AA-2234'),
        (4, 'John', 'Cron', 'cron@shop.com', '1534567', '2001-09-18',  'History str.2-5', 'AA-2334'),
        (5, 'Alice', 'Doe', 'alice@minister.com', '1734567', '2002-11-27',  'Leather str.2-5', 'AA-2434');

INSERT INTO Users
(userFirstName,	userLastName, email, phone,	birthDate,	address, city, postalCode, userHistory)
VALUES  ('Alex', 'Smith', 'smith@library.com', '1234567', '1978-09-25',  'France str.2-5', 'Paris', 'AA-1234', 'very emotional'),
        ('Cris', 'Frog', 'frog@frog.com', '1334567', '1987-09-24',  'Ligo str.2-5', 'Antananarivo', 'AA-2234', 'always late'),
        ('Catrin', 'Green', 'catrin@gov.com', '1434567', '1948-12-24',  'Ligo str.2-5', 'Stockholm', 'AA-2234', 'a lot of strange jokes'),
        ('John', 'Cron', 'john.cron@shop.com', '1534567', '2001-09-18',  'History str.2-5','Panama', 'AA-2334', 'RED LIST'),
        ('Marta', 'Liepa', 'liepa@minister.com', '1734567', '2002-11-27',  'Leather str.2-5', 'Ventspils', 'AA-2434', 'VIP');

INSERT INTO RolesAndAccess
(roleID, role, isLibrarian)
VALUES  (1, 'client', 0),
        (2, 'manager', 1),
        (3, 'client administrator', 1),
        (4, 'director', 1);

INSERT INTO UserAccess
(userAccessID, userID, roleID)
VALUES  (1, 1, 2),
        (2, 1, 1),
        (3, 2, 3),
        (4, 3, 4),
        (5, 3, 1);

INSERT INTO UserAccess
(userID, roleID)
VALUES  (6, 1),
        (7, 1),
        (8, 1),
        (9, 1),
        (5, 1);


INSERT INTO Orders (bookID, userID, issueDate, returnDate)
VALUES  (1, 1, '2021-11-01', '2021-12-01'),
        (2, 1, '2021-11-01', '2021-12-01'),
        (3, 6, '2021-11-10', '2021-12-10'),
        (4, 7, '2021-11-15', '2021-12-15');

