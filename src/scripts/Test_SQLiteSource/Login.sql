INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'userTest', 'userTest@test.com', 'user');
INSERT INTO Accounts (aid, username, email, password) VALUES (2, 'devTest', 'devTest@test.com', 'dev');
INSERT INTO Accounts (aid, username, email, password) VALUES (3, 'adminTest', 'adminTest@test.com', 'admin');
INSERT INTO Accounts (aid, username, email, password) VALUES (4, 'doubleTest', 'doubleTest@test.com', 'double');
INSERT INTO Accounts (aid, username, email, password) VALUES (5, 'nothingTest', 'nothingTest@test.com', 'nothing');
INSERT INTO Accounts (aid, username, email, password) VALUES (6, 'fails', 'fails@test.com', 'failure');

INSERT INTO Administrators (amid, aid, name) VALUES (1, 3, 'testAdmin');

INSERT INTO GameLists (glid, name) VALUES (1, 'userTest');
INSERT INTO GameLists (glid, name) VALUES (2, 'devTest');
INSERT INTO GameLists (glid, name) VALUES (3, 'doubleTestUser');
INSERT INTO GameLists (glid, name) VALUES (4, 'doubleTestDev');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 2, 'devTest', 2);
INSERT INTO Developers (did, aid, name, glid) VALUES (2, 4, 'doubleTest', 4);

INSERT INTO Users (uid, aid, name, glid) VALUES (1, 1, 'userTest', 1);
INSERT INTO Users (uid, aid, name, glid) VALUES (2, 4, 'doubleTest', 3);