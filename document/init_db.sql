drop database if exists `nearbyQA`;
create database nearbyQA;

use nearbyQA;

set character_set_database = utf8;
set character_set_server = utf8;
set names utf8;

create table forum(
	fid bigint unsigned not null primary key auto_increment,
	name char(20)
);

create table user(
	uid bigint unsigned not null primary key auto_increment,
	username char(20),
	password char(20)
);

create table question(
	qid bigint unsigned not null primary key auto_increment,
	user_uid bigint unsigned not null,
	forum_fid bigint unsigned not null,
	content varchar(1000),
	rate int unsigned,
	foreign key(user_uid) references user(uid) on delete cascade on update cascade,
	foreign key(forum_fid) references forum(fid) on delete cascade on update cascade
);

create table answer(
	aid bigint unsigned not null primary key auto_increment,
	user_uid bigint unsigned not null,
	question_qid bigint unsigned not null,
	content varchar(1000),
	rate int unsigned,
	foreign key(user_uid) references user(uid) on delete cascade on update cascade,
	foreign key(question_qid) references question(qid) on delete cascade on update cascade
);

