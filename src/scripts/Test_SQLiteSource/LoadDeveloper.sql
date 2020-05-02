INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'no', 'f@y.ou', 'no');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'LoadDeveloperTest', 1);

INSERT INTO Games (gid, title, description, gsid) VALUES (1, 'LoadDeveloperTestGame', 'no', 0);

INSERT INTO GameLists (glid, name) VALUES (1, 'load dev gl');

INSERT INTO GameDevelopers (gid, did) VALUES (1, 1);

INSERT INTO GameListsGames (glid, gid) VALUES (1, 1);