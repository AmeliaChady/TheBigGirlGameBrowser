BEGIN TRANSACTION;

--- Dropping Old ---
DROP VIEW IF EXISTS Combined;
DROP VIEW IF EXISTS GameListTest;
DROP VIEW IF EXISTS DevelopersWithListName;
DROP VIEW IF EXISTS UsersWithListName;
-- connecting tables
DROP TABLE IF EXISTS GameDevelopers;
DROP TABLE IF EXISTS GameListsGames;
-- base tables
DROP TABLE IF EXISTS Reviews;
DROP TABLE IF EXISTS Games;
DROP TABLE IF EXISTS GameStatuses;
DROP TABLE IF EXISTS Developers;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Administrators;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS GameLists;


--- Tables ---
CREATE TABLE GameLists(
    glid INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
);

CREATE TABLE Accounts(
    aid INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE,
    email TEXT UNIQUE,
    password TEXT
    );

CREATE TABLE Administrators(
    amid INTEGER PRIMARY KEY AUTOINCREMENT,
    aid INTEGER UNIQUE NOT NULL,
    name TEXT UNIQUE,
    FOREIGN KEY(aid) REFERENCES Accounts(aid)
    );

CREATE TABLE Users(
    uid INTEGER PRIMARY KEY AUTOINCREMENT,
    aid INTEGER UNIQUE NOT NULL,
    name TEXT UNIQUE,
    glid INTEGER UNIQUE NOT NULL,
    FOREIGN KEY(aid) REFERENCES Accounts(aid),
    FOREIGN KEY(glid) REFERENCES GameLists(glid)
    );

CREATE TABLE Developers(
    did INTEGER PRIMARY KEY AUTOINCREMENT,
    aid INTEGER UNIQUE NOT NULL,
    name TEXT UNIQUE NOT NULL,
    glid INTEGER UNIQUE NOT NULL,
    FOREIGN KEY(aid) REFERENCES Accounts(aid),
    FOREIGN KEY(glid) REFERENCES GameLists(glid)
    );

CREATE TABLE GameStatuses(
    gsid INTEGER PRIMARY KEY AUTOINCREMENT,
    status TEXT UNIQUE NOT NULL
    );

CREATE TABLE Games(
    gid INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT UNIQUE NOT NULL,
    description TEXT,
    gsid INTEGER NOT NULL,
    FOREIGN KEY(gsid) REFERENCES GameStatuses(gsid)
    );

CREATE TABLE GameDevelopers(
    gid INTEGER NOT NULL,
    did INTEGER NOT NULL,
    FOREIGN KEY(gid) REFERENCES Games(gid),
    FOREIGN KEY(did) REFERENCES Developers(did),
    PRIMARY KEY(did, gid)
    );



CREATE TABLE GameListsGames(
    glid INTEGER NOT NULL,
    gid INTEGER NOT NULL,
    FOREIGN KEY(gid) REFERENCES Games(gid),
    FOREIGN KEY(glid) REFERENCES GameLists(glid),
    PRIMARY KEY(glid, gid)
    );

CREATE TABLE Reviews(
    gid INTEGER NOT NULL,
    uid INTEGER NOT NULL,
    rating INTEGER NOT NULL CHECK (rating >= 1 AND rating <= 5),
    comment TEXT,
    PRIMARY KEY(gid, uid),
    FOREIGN KEY(gid) REFERENCES Games(gid),
    FOREIGN KEY(uid) REFERENCES Users(uid)
    );


--- Constraints ---


--- Views ---
CREATE VIEW Combined AS
SELECT title, name, description, status
FROM GameDevelopers
         INNER JOIN Developers D on GameDevelopers.did = D.did
         INNER JOIN Games G on GameDevelopers.gid = G.gid
         INNER JOIN GameStatuses GS on G.gsid = GS.gsid
ORDER BY title, name;

CREATE VIEW GameListTest AS
SELECT name, title
FROM GameListsGames
         INNER JOIN Games
         INNER JOIN GameLists
ORDER BY name, title;

CREATE VIEW DevelopersWithListName AS
SELECT * FROM Developers INNER JOIN GameLists USING(glid);

CREATE VIEW UsersWithListName AS
SELECT * FROM Users INNER JOIN GameLists USING(glid);

--- Inserts ---
INSERT INTO GameStatuses VALUES
    (0, 'PENDING'),
    (1, 'ACCEPTED'),
    (2, 'REJECTED'),
    (3, 'LIMBO');


COMMIT;