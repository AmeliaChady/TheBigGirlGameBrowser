INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'pasta', 'a@b.com', 'password');
INSERT INTO GameLists (glid, name) VALUES (1, 'no care');
INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'pasta', 1);
INSERT INTO Games (gid, title, description, gsid) VALUES (1, 'apple', 'bacon', 1);
INSERT INTO GameDevelopers (gid, did) VALUES (1, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (1, 1);