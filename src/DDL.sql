BEGIN TRANSACTION;

-- Dropping Old --
DROP VIEW IF EXISTS Combined;
DROP VIEW IF EXISTS GameListTest;
DROP TABLE IF EXISTS GameDevelopers;
DROP TABLE IF EXISTS Games;
DROP TABLE IF EXISTS Developers;
DROP TABLE IF EXISTS GameStatuses;
DROP TABLE IF EXISTS GameLists;
DROP TABLE IF EXISTS GameListsGames;
DROP TABLE IF EXISTS DevelopersGameLists;



-- Tables --
CREATE TABLE Developers(
    did INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL,
    listName TEXT UNIQUE NOT NULL
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

CREATE TABLE GameLists(
    glid INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE NOT NULL
    );

CREATE TABLE GameListsGames(
    glid INTEGER NOT NULL,
    gid INTEGER NOT NULL,
    FOREIGN KEY(gid) REFERENCES Games(gid),
    FOREIGN KEY(glid) REFERENCES GameLists(glid),
    PRIMARY KEY(glid, gid)
    );

CREATE TABLE DevelopersGameLists(
    did INTEGER NOT NULL,
    glid INTEGER NOT NULL,
    FOREIGN KEY(did) REFERENCES Developers(did),
    FOREIGN KEY(glid) REFERENCES GameLists(glid),
    PRIMARY KEY(did, glid)
    );



-- Constraints --
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
-- Views --


-- Inserts --
INSERT INTO GameStatuses VALUES
    (0, 'PENDING'),
    (1, 'ACCEPTED'),
    (2, 'REJECTED'),
    (3, 'LIMBO');


COMMIT;