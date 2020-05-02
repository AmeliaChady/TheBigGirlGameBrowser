INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'bob', 'bob@awesome.com', 'catsRbest');

INSERT INTO GameLists (glid, name) VALUES (1, 'bob needed');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'Bobby', 1);

INSERT INTO Games (gid, title, description, gsid) VALUES (1, 'testGame1', 'no matter', 0);
INSERT INTO Games (gid, title, description, gsid) VALUES (2, 'testGame2', 'no matter', 0);
INSERT INTO Games (gid, title, description, gsid) VALUES (3, 'testGame3', 'no matter', 0);

INSERT INTO GameDevelopers (gid, did) VALUES (1, 1);
INSERT INTO GameDevelopers (gid, did) VALUES (2, 1);
INSERT INTO GameDevelopers (gid, did) VALUES (3, 1);