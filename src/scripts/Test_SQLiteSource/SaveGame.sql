INSERT INTO GameLists (glid, name) VALUES (1, 'na');
INSERT INTO GameLists (glid, name) VALUES (2, 'butts');
INSERT INTO GameLists (glid, name) VALUES (3, 'corgis');

INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'fronk', 'fronk@clunk.com', 'password');
INSERT INTO Accounts (aid, username, email, password) VALUES (2, 'tod', 'tod@clunk.com', 'password');
INSERT INTO Accounts (aid, username, email, password) VALUES (3, 'corgis', 'corgis@corgis.net', 'corgis');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 1, 'Frank', 1);
INSERT INTO Developers (did, aid, name, glid) VALUES (2, 2, 'Ted', 2);
INSERT INTO Developers (did, aid, name, glid) VALUES (3, 3, 'CorgiLover87', 3);