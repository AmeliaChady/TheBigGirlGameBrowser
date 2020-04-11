BEGIN TRANSACTION;
DELETE FROM Games WHERE title='testGame';
DELETE FROM Games WHERE title='Test-zx the Game';
DELETE FROM Games WHERE title='Toot Scooters';
COMMIT;