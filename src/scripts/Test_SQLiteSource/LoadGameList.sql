INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'a', 'a@a.com', 'password');
INSERT INTO Accounts (aid, username, email, password) VALUES (2, 'b', 'b@b.com', 'password');

INSERT INTO GameLists (glid, name) VALUES (1, 'bells');
INSERT INTO GameLists (glid, name) VALUES (2, 'amelias');
INSERT INTO GameLists (glid, name) VALUES (3, 'Games I Have Time To Play');
INSERT INTO GameLists (glid, name) VALUES (4, 'Everyone Else is Playing');
INSERT INTO GameLists (glid, name) VALUES (5, 'The Kerry Anne Experience');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'HoarderOfBells', 1);
INSERT INTO Developers (did, aid, name, glid) VALUES (2, 2, 'Amelia Chady', 2);

INSERT INTO Games (gid, title, description, gsid) VALUES (1, 'Crossing Mammals', 'No Description Given', 0);
INSERT INTO Games (gid, title, description, gsid) VALUES (2, 'Confusion Level Increasing', 'No Description Given', 0);

INSERT INTO GameDevelopers (gid, did) VALUES (1, 1);
INSERT INTO GameDevelopers (gid, did) VALUES (2, 2);

INSERT INTO GameListsGames (glid, gid) VALUES (4, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (5, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (5, 2);