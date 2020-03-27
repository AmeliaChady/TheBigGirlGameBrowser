BEGIN TRANSACTION;

-- Dropping Old --
DROP TABLE IF EXISTS Games;

-- Tables --
CREATE TABLE Developers(
    did INTEGER PRIMARY KEY AUTOINCREMENT,
    name INTEGER UNIQUE NOT NULL
    );

CREATE TABLE Games(
    gid INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT UNIQUE NOT NULL,
    developer TEXT NOT NULL,
    description TEXT,
    status TEXT
    );

-- Constraints --


-- Inserts --


COMMIT;