Drop table if exists comics;
create table if not exists comics(
id serial primary key,
comic varchar(30) not null,
description text,
genre varchar(20) not null,
price numeric(7,2) 
);

insert into users (username, password, role) values ('david', 'pass4', 'ADMIN');
insert into users (username, password, role) values ('jay', 'pass3', 'USER');
insert into users (username, password, role) values ('dylan', 'pass2', 'USER');
insert into users (username, password, role) values ('angel', 'pass1', 'ADMIN');


--select * from comics where to_tsvector(genre) @@ to_tsquery('Fijian');

--purchase_id integer references users(user_id)
--insert into comics(comic_name) values ('spiderman');
--update comics set purchase_id = 1 where comic_id = 1;

--Drop table if exists users;
--create table if not exists users(
--user_id serial primary key,
--user_name varchar(20) unique not null
--);

--select comic_name from comics;
--
--insert into users(user_name) values ('Anggel');

		

insert into comics (id, comic, description, genre, price) values (1, 'Batman', 'fighting crime in a dark city', 'action', '705');
insert into comics (id, comic, description, genre, price) values (2, 'Berserk', 'former mercenary', 'darkfantasy', '24');
insert into comics (id, comic, description, genre, price) values (3, 'Vagabond', 'wandering fighter', 'action', '36');
insert into comics (id, comic, description, genre, price) values (4, 'Spiderman', 'witty superhero', 'comedy', '79');
insert into comics (id, comic, description, genre, price) values (5, 'MadeinAbyss', 'the abyss', 'exploration', '94');