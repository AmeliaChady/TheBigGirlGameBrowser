
BEGIN TRANSACTION;
-- Insert Games
REPLACE INTO Games (title, description, gsid) VALUES ('LoadGameTest1', 'description', 0);
REPLACE INTO Games (title, description, gsid) VALUES ('LoadGameTest2', 'noitpircsed', 1);
DELETE FROM Games WHERE title='LoadGameTest3';

-- Insert Developers
REPLACE INTO Developers (name, listName) VALUES ('LGT_A', 'LGT_A''s Games');
REPLACE INTO Developers (name, listName) VALUES ('LGT_B', 'LGT_B''s Games');
REPLACE INTO Developers (name, listName) VALUES ('LGT_C', 'LGT_C''s Games');

-- Insert GameLists
REPLACE INTO GameLists (name) VALUES ('LGT_A''s Games');
REPLACE INTO GameLists (name) VALUES ('LGT_B''s Games');
REPLACE INTO GameLists (name) VALUES ('LGT_C''s Games');

-- Linking
REPLACE INTO DevelopersGameLists (did, glid)
    VALUES (
    (SELECT did FROM Developers WHERE name='LGT_A'),
    (SELECT glid FROM GameLists WHERE name='LGT_A''s Games'));
REPLACE INTO DevelopersGameLists (did, glid)
    VALUES (
    (SELECT did FROM Developers WHERE name='LGT_B'),
    (SELECT glid FROM GameLists WHERE name='LGT_B''s Games'));
REPLACE INTO DevelopersGameLists (did, glid)
    VALUES (
    (SELECT did FROM Developers WHERE name='LGT_A'),
    (SELECT glid FROM GameLists WHERE name='LGT_A''s Games'));

REPLACE INTO GameListsGames (glid, gid)
    VALUES (
    (SELECT glid FROM GameLists WHERE name='LGT_A''s Games'),
    (SELECT gid FROM Games WHERE title='LoadGameTest1'));
REPLACE INTO GameListsGames (glid, gid)
    VALUES (
    (SELECT glid FROM GameLists WHERE name='LGT_B''s Games'),
    (SELECT gid FROM Games WHERE title='LoadGameTest2'));
REPLACE INTO GameListsGames (glid, gid)
    VALUES (
    (SELECT glid FROM GameLists WHERE name='LGT_C''s Games'),
    (SELECT gid FROM Games WHERE title='LoadGameTest2'));

COMMIT;