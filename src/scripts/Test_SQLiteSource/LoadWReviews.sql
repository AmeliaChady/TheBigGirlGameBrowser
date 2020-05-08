-- 4 accounts
INSERT INTO Accounts(aid, username, email, password) VALUES
    (0, 'a', 'a@a.com', 'a'),
    (1, 'b', 'b@b.com', 'b'),
    (2, 'c', 'c@c.com', 'c'),
    (3, 'd', 'd@d.com', 'd');
-- 5 game lists
INSERT INTO GameLists(aid, name) VALUES
    (0, 'Master Game List'),
    (1, 'a DevList'),
    (2, 'b UserList'),
    (3, 'c UserList'),
    (4, 'd UserList');
-- 1 dev
INSERT INTO Developers(did, aid, name, glid) VALUES
    (0, 0, 'a', 1);
-- 3 users
INSERT INTO Users(uid, aid, name, glid) VALUES
    (0, 1, 'b', 2),
    (1, 2, 'c', 3),
    (2, 3, 'd', 4);
-- 3 Games
INSERT INTO Games(gid, title, description, gsid) VALUES
    (0, '9pm Coding sucks', 'na', 1),
    (1, '10pm Coding sucks', 'na', 1),
    (2, '11pm Coding sucks', 'na', 1);
-- Dev Game Connections
INSERT INTO GameDevelopers(gid, did) VALUES
    (0, 0),
    (1, 0),
    (2, 0);
-- Game Game List Connections
INSERT INTO GameListsGames(glid, gid) VALUES
    (0, 0), (1, 0), (2, 0), (3, 0), (4, 0),
    (0, 1), (1, 1), (2, 1), (3, 1), (4, 2),
    (0, 2), (1, 2), (2, 2), (3, 2), (4, 2);
-- 4 reviews
INSERT INTO Reviews(gid, uid, rating, comment) VALUES
    (1, 0, 3, 'na'),
    (2, 0, 4, 'good test description'),
    (2, 1, 3, 'na'),
    (2, 2, 3, 'na');