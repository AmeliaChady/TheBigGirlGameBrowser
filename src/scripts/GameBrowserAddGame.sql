INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'a', 'a@b.com', 'a');
INSERT INTO Accounts (aid, username, email, password) VALUES (2, 'b', 'b@a.com', 'b');

INSERT INTO GameLists (glid, name) VALUES (1, 'a');
INSERT INTO GameLists (glid, name) VALUES (2, 'b');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'Anita', 1);
INSERT INTO Developers (did, aid, name, glid) VALUES (2, 2, 'Robert', 2);