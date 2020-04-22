BEGIN TRANSACTION;

INSERT INTO Accounts (aid, username, email, password) VALUES (1, 'Amelia', 'amelia@awesome.com', 'fuckyeahImademyselfadmin');
INSERT INTO Accounts (aid, username, email, password) VALUES (2, 'KerryAnne', 'kerryanne@awesome.com', 'smellypantschady');
INSERT INTO Accounts (aid, username, email, password) VALUES (3, 'Kelsey', 'kelsey@awesome.com', 'dislikesmyletters');
INSERT INTO Accounts (aid, username, email, password) VALUES (4, 'Robert', 'robert@awesome.com', 'hisphonemicquietondiscord');
INSERT INTO Accounts (aid, username, email, password) VALUES (5, 'Scott', 'scott@awesome.com', 'discordcrashedagain');
INSERT INTO Accounts (aid, username, email, password) VALUES (6, 'Eugene', 'eugene@client.awesome', 'doiexist');
INSERT INTO Accounts (aid, username, email, password) VALUES (7, 'Elias', 'elias@client.awesome', 'sorryeliasidontknowyouenough');
INSERT INTO Accounts (aid, username, email, password) VALUES (8, 'Watson', 'watson@client.awesome', 'likesanime');
INSERT INTO Accounts (aid, username, email, password) VALUES (9, 'Christian', 'christian@client.awesome', 'sorrychristianidontknowyouenough');
INSERT INTO Accounts (aid, username, email, password) VALUES (10, 'Smellypants', 'smell@pants.com', 'fragrancediffusersaremyenemy');

INSERT INTO GameLists (glid, name) VALUES (1, 'Master Game List');
INSERT INTO GameLists (glid, name) VALUES (2, 'KerryAnne''s Dev Games');
INSERT INTO GameLists (glid, name) VALUES (3, 'Kelsey''s Dev Games');
INSERT INTO GameLists (glid, name) VALUES (4, 'Kelsey''s User Games');
INSERT INTO GameLists (glid, name) VALUES (5, 'Robert''s Dev Games');
INSERT INTO GameLists (glid, name) VALUES (6, 'Scott''s Dev Games');
INSERT INTO GameLists (glid, name) VALUES (7, 'Eugene''s User Games');
INSERT INTO GameLists (glid, name) VALUES (8, 'Elias''s User Games');
INSERT INTO GameLists (glid, name) VALUES (9, 'Watson''s User Games');
INSERT INTO GameLists (glid, name) VALUES (10, 'Christian''s User Games');
INSERT INTO GameLists (glid, name) VALUES (11, 'Smellypants''s User Games');

INSERT INTO Developers (did, aid, name, glid) VALUES (1, 2, 'KerryAnne', 2);
INSERT INTO Developers (did, aid, name, glid) VALUES (2, 3, 'Kelsey', 3);
INSERT INTO Developers (did, aid, name, glid) VALUES (3, 4, 'Robert', 5);
INSERT INTO Developers (did, aid, name, glid) VALUES (4, 5, 'Scott', 6);

INSERT INTO Administrators (amid, aid, name) VALUES (1, 1, 'Amelia');

INSERT INTO Users (uid, aid, name, glid) VALUES (1, 3, 'Kelsey', 4);
INSERT INTO Users (uid, aid, name, glid) VALUES (2, 6, 'Eugene', 7);
INSERT INTO Users (uid, aid, name, glid) VALUES (3, 7, 'Elias', 8);
INSERT INTO Users (uid, aid, name, glid) VALUES (4, 8, 'Watson', 9);
INSERT INTO Users (uid, aid, name, glid) VALUES (5, 9, 'Christian', 10);
INSERT INTO Users (uid, aid, name, glid) VALUES (6, 10, 'Smellypants', 11);

INSERT INTO Games (gid, title, description, gsid) VALUES (1, 'Mammal Crossing', 'Down with Nook!', 1);
INSERT INTO Games (gid, title, description, gsid) VALUES (2, 'Frankie the Plumber', 'Jump on rabbits. squeeze down non colorful pipes. DEV NOTE: WHAT DO YOU MEAN MARIO CLONE', 1);
INSERT INTO Games (gid, title, description, gsid) VALUES (3, 'Grand Touring 7', 'Racing. Please join.', 1);
INSERT INTO Games (gid, title, description, gsid) VALUES (4, 'Landcraft 3', 'You might need more minerals', 1);
INSERT INTO Games (gid, title, description, gsid) VALUES (5, 'Woofframe', 'Dog Ninjas in Spaaaaaaaaaaaaaaaaaaaaaaace', 0);

INSERT INTO GameDevelopers (gid, did) VALUES (1, 1);
INSERT INTO GameDevelopers (gid, did) VALUES (1, 2);
INSERT INTO GameDevelopers (gid, did) VALUES (2, 1);
INSERT INTO GameDevelopers (gid, did) VALUES (2, 2);
INSERT INTO GameDevelopers (gid, did) VALUES (3, 3);
INSERT INTO GameDevelopers (gid, did) VALUES (4, 4);
INSERT INTO GameDevelopers (gid, did) VALUES (5, 4);

INSERT INTO GameListsGames (glid, gid) VALUES (2, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (3, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (2, 2);
INSERT INTO GameListsGames (glid, gid) VALUES (3, 2);
INSERT INTO GameListsGames (glid, gid) VALUES (5, 3);
INSERT INTO GameListsGames (glid, gid) VALUES (6, 4);
INSERT INTO GameListsGames (glid, gid) VALUES (4, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (4, 2);
INSERT INTO GameListsGames (glid, gid) VALUES (7, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (7, 2);
INSERT INTO GameListsGames (glid, gid) VALUES (7, 3);
INSERT INTO GameListsGames (glid, gid) VALUES (7, 4);
INSERT INTO GameListsGames (glid, gid) VALUES (8, 4);
INSERT INTO GameListsGames (glid, gid) VALUES (9, 1);
INSERT INTO GameListsGames (glid, gid) VALUES (9, 4);
INSERT INTO GameListsGames (glid, gid) VALUES (10, 4);
INSERT INTO GameListsGames (glid, gid) VALUES (11, 4);

COMMIT;
