BEGIN TRANSACTION;

-- Dropping Old --
DROP TABLE IF EXISTS Games;
DROP TABLE IF EXISTS Developers;
DROP TABLE IF EXISTS GameStatuses;

-- Tables --
CREATE TABLE Developers(
    did INTEGER PRIMARY KEY AUTOINCREMENT,
    name INTEGER UNIQUE NOT NULL
    );

CREATE TABLE GameStatuses(
    gsid INTEGER PRIMARY KEY AUTOINCREMENT,
    status TEXT UNIQUE NOT NULL
    );

CREATE TABLE Games(
    gid INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT UNIQUE NOT NULL,
    did INTEGER NOT NULL,
    description TEXT,
    gsid INTEGER NOT NULL,
    FOREIGN KEY(did) REFERENCES Developers(did),
    FOREIGN KEY(gsid) REFERENCES GameStatuses(gsid)
    );


-- Constraints --


-- Views --


-- Inserts --
INSERT INTO GameStatuses VALUES
    (0, 'Pending'),
    (1, 'Accepted'),
    (2, 'Rejected');


COMMIT;