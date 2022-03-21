Drop table if exists comics;
create table if not exists comics(
id serial primary key,
comic varchar(30) unique not null,
description text,
genre varchar(20) not null,
price numeric(7,2) not null
);


select * from comics where to_tsvector(genre) @@ to_tsquery('Fijian');

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

		

insert into comics (id, comic, description, genre, price) values (1, 'Arabele', 'background', 'Maltese', '8705.91');
insert into comics (id, comic, description, genre, price) values (2, 'Paolo', 'Multi-layered', 'Fijian', '5842.87');
insert into comics (id, comic, description, genre, price) values (3, 'Ebony', 'Phased', 'West Frisian', '3653.27');
insert into comics (id, comic, description, genre, price) values (4, 'Aldrich', 'Networked', 'Fijian', '7962.60');
insert into comics (id, comic, description, genre, price) values (5, 'Zachary', 'Visionary', 'Luxembourgish', '9403.53');