-- create games and add to game list
INSERT INTO Games(title, description, gsid) VALUES('Game 1', 'This is description 1', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 2', 'This is description 2', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 3', 'This is description 3', 1);
INSERT INTO GameLists(name) VALUES('user0 Game List');
INSERT INTO GameListsGames(glid, gid) VALUES (0, 0);
INSERT INTO GameListsGames(glid, gid) VALUES (0, 1);
INSERT INTO GameListsGames(glid, gid) VALUES (0, 2);

-- create user and associate game list
INSERT INTO Accounts (username, email, password) VALUES ('user0', 'user0@mail.com', 'password');
INSERT INTO Users (aid, name, glid) VALUES (0, 'user0', 0);

-- create unowned games
INSERT INTO Games(title, description, gsid) VALUES('Game 4', 'This is description 4', 1);
