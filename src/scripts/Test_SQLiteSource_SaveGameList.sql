BEGIN TRANSACTION;

-- Delete GameDevelopers
DELETE FROM GameDevelopers WHERE did=(SELECT did FROM Developers WHERE name='Bobby');

-- Delete DevelopersGameLists
DELETE FROM DevelopersGameLists WHERE did=(SELECT did FROM Developers WHERE name='Bobby');

-- Delete GameListGames
DELETE FROM GameListsGames WHERE glid=(SELECT glid FROM GameLists WHERE name='TestList');
DELETE FROM GameListsGames WHERE glid=(SELECT glid FROM GameLists WHERE name='Bobby''s Games');

-- Delete Dev
DELETE FROM Developers WHERE name='Bobby';

-- Delete Games
DELETE FROM Games WHERE title='testGame1';
DELETE FROM Games WHERE title='testGame2';
DELETE FROM Games WHERE title='testGame3';

-- Delete GameLists
DELETE FROM GameLists WHERE name='TestList';
DELETE FROM GameLists WHERE name='Bobby''s Games';

COMMIT;