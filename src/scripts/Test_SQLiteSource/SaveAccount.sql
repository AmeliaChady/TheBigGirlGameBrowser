-- create game lists for a user and a developer
INSERT INTO GameLists(name) VALUES('user0 Game List');
INSERT INTO GameLists(name) VALUES('developer0 Game List');

-- create user and developer and associate game lists
INSERT INTO Accounts (username, email, password) VALUES ('user0', 'user0@mail.com', 'password');
INSERT INTO Accounts (username, email, password) VALUES ('developer0', 'developer0@mail.com', 'password');
INSERT INTO Users (aid, name, glid) VALUES (1, 'user0', 1);
INSERT INTO Developers (aid, name, glid) VALUES (2, 'developer0', 2);

