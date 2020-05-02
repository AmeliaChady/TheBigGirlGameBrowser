INSERT INTO GameLists (glid, name) VALUES (1, 'a');
INSERT INTO GameLists (glid, name) VALUES (2, 'b');
INSERT INTO GameLists (glid, name) VALUES (3, 'c');

INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'a', 'a@a.com', 'a');
INSERT INTO Accounts (aid, username, email, password) VALUES (2, 'b', 'b@b.com', 'b');
INSERT INTO Accounts (aid, username, email, password) VALUES (3, 'c', 'c@c.com', 'c');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'LGT_A', 1);
INSERT INTO Developers (did, aid, name, glid) VALUES (2, 2, 'LGT_B', 2);
INSERT INTO Developers (did, aid, name, glid) VALUES (3, 3, 'LGT_C', 3);

INSERT INTO Games (gid, title, description, gsid) VALUES (1, 'LoadGameTest1', 'description', 0);
INSERT INTO Games (gid, title, description, gsid) VALUES (2, 'LoadGameTest2', 'noitpircsed', 1);

INSERT INTO GameDevelopers (gid, did) VALUES (1, 1);
INSERT INTO GameDevelopers (gid, did) VALUES (2, 2);
INSERT INTO GameDevelopers (gid, did) VALUES (2, 3);