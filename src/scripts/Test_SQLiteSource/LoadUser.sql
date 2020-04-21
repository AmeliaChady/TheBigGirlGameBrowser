-- games and gamelist
INSERT INTO Games(title, description, gsid) VALUES('ACNH', 'cool game', 1);
INSERT INTO Games(title, description, gsid) VALUES('minecraft', 'also cool', 2);

INSERT INTO GameLists(name) VALUES('Game List');
INSERT INTO GameListsGames(glid, gid) VALUES (1, 1);
INSERT INTO GameListsGames(glid, gid) VALUES (1, 2);
INSERT INTO GameListsGames(glid, gid) VALUES (1, 3);

-- create user and associate game list
INSERT INTO Accounts (username, email, password) VALUES ('kerry', 'kab@yahoo.com', 'pass123!');
INSERT INTO Users (aid, name, glid) VALUES (1, 'kerry', 1);
