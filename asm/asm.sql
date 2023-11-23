wDrop database asm;
Create database asm;
Use asm;

Create table users(
	id varchar(30) primary key,
	email varchar(50),
	fullname nvarchar(50),
	password varchar(30),
	admin bit
);

Create table videos(
	id varchar(20) primary key,
	active bit,
	shortDescription nvarchar(500),
	description nvarchar(4000),
	poster varchar(255),
	title nvarchar(255),
	views int
);

Create table shares(
	id bigint identity,
	emails varchar(255),
	sharedate datetime,
	userid varchar(30),
	videoid varchar(20)
);

Create table favorites(
	id bigint identity,
	likedate datetime,
	userid varchar(30),
	videoid varchar(20)
);
ALTER table favorites add constraint fk_videos_favo foreign key (videoid) references videos(id);
ALTER table favorites add constraint fk_users_favo foreign key (userid) references users(id);
ALTER table shares add constraint fk_videos_shares foreign key (videoid) references videos(id);
ALTER table shares add constraint fk_users_shares foreign key (userid) references users(id);

Insert into users(id, email, fullname, admin,password) values ('admin', 'ducdnps22361@fpt.edu.vn', 'ADMIN',1,'admin');
/*Select * from videos where id = '36huP7ely-w'
select * from videos;
select * from users;
select * from favorites;
select * from videos where id ='_5-TV94obqA';
Alter table videos Add shortDescription nvarchar(500);
update videos set description ='';
update users set admin = 1 where id = 'admin'
update users set password = 11111 where id = 'admin'
select * from shares;*/