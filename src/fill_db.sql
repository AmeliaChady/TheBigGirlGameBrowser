--insert games
INSERT INTO Games(title, description, gsid) VALUES('Game 1', 'This is description 1', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 2', 'This is description 2', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 3', 'This is description 3', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 4', 'This is description 4', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 5', 'This is description 5', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 6', 'This is description 6', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 7', 'This is description 7', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 8', 'This is description 8', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 9', 'This is description 9', 1);
INSERT INTO Games(title, description, gsid) VALUES('Game 10', 'This is description 10', 1);

--insert developers
INSERT INTO Developers(name, listName) VALUES('Developer 1', 'Developer 1 Game List');
INSERT INTO Developers(name, listName) VALUES('Developer 2', 'Developer 2 Game List');
INSERT INTO Developers(name, listName) VALUES('Developer 3', 'Developer 3 Game List');
INSERT INTO Developers(name, listName) VALUES('Developer 4', 'Developer 4 Game List');
INSERT INTO Developers(name, listName) VALUES('Developer 5', 'Developer 5 Game List');

--insert game lists
INSERT INTO GameLists(name) VALUES('Developer 1 Game List');
INSERT INTO GameLists(name) VALUES('Developer 2 Game List');
INSERT INTO GameLists(name) VALUES('Developer 3 Game List');
INSERT INTO GameLists(name) VALUES('Developer 4 Game List');
INSERT INTO GameLists(name) VALUES('Developer 5 Game List');
INSERT INTO GameLists(name) VALUES('Master Game List');

--insert games into game lists
--  -all games into master list
INSERT INTO GameListsGames(glid, gid) VALUES (6, 1);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 2);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 3);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 4);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 5);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 6);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 7);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 8);
INSERT INTO GameListsGames(glid, gid) VALUES (6, 9);
INSERT INTO GameListsGames(glid, gid) VALUES (6,10);
--  -games into developer lists (two per dev -> two per dev list)
INSERT INTO GameListsGames(glid, gid) VALUES (1, 1);
INSERT INTO GameListsGames(glid, gid) VALUES (1, 2);
INSERT INTO GameListsGames(glid, gid) VALUES (2, 3);
INSERT INTO GameListsGames(glid, gid) VALUES (2, 4);
INSERT INTO GameListsGames(glid, gid) VALUES (3, 5);
INSERT INTO GameListsGames(glid, gid) VALUES (3, 6);
INSERT INTO GameListsGames(glid, gid) VALUES (4, 7);
INSERT INTO GameListsGames(glid, gid) VALUES (4, 8);
INSERT INTO GameListsGames(glid, gid) VALUES (5, 9);
INSERT INTO GameListsGames(glid, gid) VALUES (5,10);

-- associate games lists with developers
INSERT INTO DevelopersGameLists(did, glid) VALUES (1, 1);
INSERT INTO DevelopersGameLists(did, glid) VALUES (2, 2);
INSERT INTO DevelopersGameLists(did, glid) VALUES (3, 3);
INSERT INTO DevelopersGameLists(did, glid) VALUES (4, 4);
INSERT INTO DevelopersGameLists(did, glid) VALUES (5, 5);

--associate games worked by developers (two per dev)
INSERT INTO GameDevelopers(gid, did) VALUES (1, 1);
INSERT INTO GameDevelopers(gid, did) VALUES (2, 1);
INSERT INTO GameDevelopers(gid, did) VALUES (3, 2);
INSERT INTO GameDevelopers(gid, did) VALUES (4, 2);
INSERT INTO GameDevelopers(gid, did) VALUES (5, 3);
INSERT INTO GameDevelopers(gid, did) VALUES (6, 3);
INSERT INTO GameDevelopers(gid, did) VALUES (7, 4);
INSERT INTO GameDevelopers(gid, did) VALUES (8, 4);
INSERT INTO GameDevelopers(gid, did) VALUES (9, 5);
INSERT INTO GameDevelopers(gid, did) VALUES (10,5);
