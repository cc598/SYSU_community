CREATE DATABASE sysu_community;
USE sysu_community;
CREATE TABLE t_user ( 
	user_id VARCHAR(50) PRIMARY KEY, 
	username VARCHAR(50) NOT NULL, 
	PASSWORD VARCHAR(50) NOT NULL, 
	icon VARCHAR(50), 
	sex VARCHAR(10), 
	college VARCHAR(50), 
	grade VARCHAR(20), 
	email VARCHAR(30) 
	); 

CREATE TABLE article (
	art_id int PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	content MEDIUMTEXT NOT NULL,
	authorId VARCHAR(50),
	releaseTime TIMESTAMP NOT NULL,
	lastChangeTime TIMESTAMP NOT NULL,
	classification VARCHAR(30),
	FOREIGN KEY (authorId) REFERENCES t_user(user_id) ON DELETE SET NULL
);	

CREATE TABLE question (
	question_id int PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(50) NOT NULL,
	content TEXT,
	authorId VARCHAR(50),
	releaseTime TIMESTAMP,
	FOREIGN KEY (authorId) REFERENCES t_user(user_id) ON DELETE SET NULL
	);

CREATE TABLE t_comment (
	c_id int PRIMARY KEY AUTO_INCREMENT,
	content TEXT NOT NULL,
	authorId VARCHAR(50),
	art_id int NOT NULL,
	releaseTime TIMESTAMP,
	FOREIGN KEY (authorId) REFERENCES t_user(user_id) ON DELETE SET NULL,
	FOREIGN KEY (art_id) REFERENCES article(art_id) ON DELETE CASCADE
	);

CREATE TABLE answer (
	ans_id int PRIMARY KEY AUTO_INCREMENT,
	content TEXT NOT NULL,
	authorId VARCHAR(50),
	ques_id int NOT NULL,
	releaseTime TIMESTAMP,	
	FOREIGN KEY (authorId) REFERENCES t_user(user_id) ON DELETE SET NULL,
	FOREIGN KEY (ques_id) REFERENCES question(question_id) ON DELETE CASCADE
	);
