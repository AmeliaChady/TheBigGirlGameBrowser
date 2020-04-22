import string
import random

NUM_GAMES = 5000

NUM_USERS = 1000
NUM_DEVELOPERS = 1000
NUM_ADMINS = 10
USER_DEV_SPLIT = 7;
''' out of 10. If lower than number, user connection. Otherwise dev connection'''

NUM_CONNECTIONS = 10000
OUTPUT_FILE = "bigass.sql"

used_names = []
games = [] 
developers = []
users = []
admins = []

num_specials = 0

connections = 0
general_connections = []
dev_connections = []


def dev_connection(dev):
	did = dev[0]
	glid = dev[2]
	gid = games[random.randrange(len(games))][3]
	connection = (gid, did)
	if connection not in dev_connections:	
		dev_connections.append(connection)
		connection = (gid, glid)
		general_connections.append(connection)
		global connections
		connections += 1
		return True
	return False


def user_connection(user):
	glid = user[2]
	gid = games[random.randrange(len(games))][3]
	connection = (gid, glid)
	if connection not in general_connections:	
		general_connections.append(connection)
		global connections
		connections += 1
		return True
	return False





def string_generator(size=64, chars=string.ascii_uppercase + string.digits):
    return ''.join(random.choice(chars) for _ in range(size))
    '''from https://stackoverflow.com/questions/2257441/random-string-generation-with-upper-case-letters-and-digits'''


print("creating games")
count = 0;
while len(games) < NUM_GAMES:
	name = string_generator()
	if name not in used_names:
		print(len(games), " @ ", count)
		gid = len(games)
		description = string_generator()
		gsid = random.randint(0, 3)
		
		used_names.append(name)
		games.append((
			gid,
			name,
			description,
			gsid
			))
	count+=1
print()

print("creating developers")
count = 0
while len(developers) < NUM_DEVELOPERS:
	name = string_generator()
	if name not in used_names:
		print(len(developers), " @ ", count)
		did = len(developers)
		specials = num_specials
		num_specials+=1
		
		developers.append((
			did,
			name,
			specials
			))
	count+=1
print()

print("creating users")
count = 0
while len(users) < NUM_USERS:
	name = string_generator()
	if name not in used_names:
		print(len(users), " @ ", count)
		uid = len(users)
		specials = num_specials
		num_specials+=1
		
		users.append((
			uid,
			name,
			specials
			))
	count+=1
print()

print("creating admins")
count = 0
while len(admins) < NUM_ADMINS:
	name = string_generator()
	if name not in used_names:
		print(len(admins), " @ ", count)
		amid = len(admins)
		aid = num_specials
		num_specials+=1
		
		admins.append((
			amid,
			name,
			aid
			))
	count+=1
print()




print("creating connections")
count = 0;
for g in games:
	worked = False
	while not worked:
		print(connections, " @ ", count)
		gid = g[0]
		developer = developers[random.randrange(len(developers))]
		connection = (gid, developer[0])
		if connection not in dev_connections:
			dev_connections.append(connection)
			connection = (gid, developer[2])	
			general_connections.append(connection)
			worked = True
			connections += 1
		count += 1;

print("connection checkpoint 1")
for d in developers:
	worked = False
	while not worked:
		print(connections, " @ ", count)
		if(dev_connection(d)):
			worked = True
		count += 1;

print("connection checkpoint 1")
for u in users:
	worked = False
	while not worked:
		print(connections, " @ ", count)
		if(user_connection(u)):
			worked = True
		count += 1;


print("connection checkpoint 3")
while connections < NUM_CONNECTIONS:
	print(connections, " @ ", count)
	if random.randint(1, 10) < USER_DEV_SPLIT:
		user_connection(random.choice(users))
	else:
		dev_connection(random.choice(developers))
	count += 1;


'''Saving'''

'''Base Classes'''
print("saving to file")
file = open(OUTPUT_FILE, 'w')
name
file.write('BEGIN TRANSACTION;\n')
game_insert_base = "INSERT INTO Games(gid, title, description, gsid) VALUES({}, '{}', '{}', {});\n"
for g in games:
	file.write(game_insert_base.format(g[0], g[1], g[2], g[3]))

account_insert_base = "INSERT INTO Accounts(aid, username, email, password) VALUES({}, '{}', '{}@f.u.com', 'password');\n"
gamelist_insert_base = "INSERT INTO GameLists(glid, name) VALUES({}, '{}''s games');\n"

dev_insert_base = "INSERT INTO Developers(did, aid, name, glid) VALUES({}, {}, '{}', {});\n"
for d in developers:
	file.write(account_insert_base.format(d[2], d[1], d[1]))
	file.write(gamelist_insert_base.format(d[2], d[1])) 
	file.write(dev_insert_base.format(d[0], d[2], d[1], d[2]))

user_insert_base = "INSERT INTO Users(uid, aid, name, glid) VALUES({}, {}, '{}', {});\n"
for u in users:
	file.write(account_insert_base.format(u[2], u[1], u[1]))
	file.write(gamelist_insert_base.format(u[2], u[1])) 
	file.write(user_insert_base.format(u[0], u[2], u[1], u[2]))

admin_insert_base = "INSERT INTO Administrators(amid, aid, name) VALUES({}, {}, '{}');\n"
for a in admins:
	file.write(account_insert_base.format(a[2], a[1], a[1]))
	file.write(gamelist_insert_base.format(a[2], a[1])) 
	file.write(admin_insert_base.format(a[0], a[2], a[1]))



game_gamelist_base = "INSERT INTO GameListsGames(glid, gid) VALUES({}, {});\n"
for cg in general_connections:
	file.write(game_gamelist_base.format(cg[1], cg[0]))

game_devs_base = "INSERT INTO GameDevelopers(gid, did) VALUES({}, {});\n"
for cd in dev_connections:
	file.write(game_devs_base.format(cd[0], cd[1]))
	

file.write('COMMIT;')
file.close()
print("finished")

















	

