CREATE TABLE USER ( 
	user_id VARCHAR(50) PRIMARY KEY, 
	username VARCHAR(50) NOT NULL, 
	PASSWORD VARCHAR(50) NOT NULL, 
	icon MEDIUMBLOB, 
	sex VARCHAR(10), 
	college VARCHAR(50), 
	grade VARCHAR(20), 
	email VARCHAR(30) 
	); 

CREATE TABLE article (
	art_id VARCHAR(50) PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	content MEDIUMTEXT NOT NULL,
	authorId VARCHAR(50) NOT NULL,
	releaseTime TIMESTAMP NOT NULL,
	lastChangeTime TIMESTAMP NOT NULL,
	classification VARCHAR(30),
	FOREIGN KEY (authorId) REFERENCES t_user(user_id)
);	

CREATE TABLE question (
	question_id VARCHAR(50) PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	content TEXT,
	authorId VARCHAR(50) NOT NULL,
	releaseTime TIMESTAMP,
	FOREIGN KEY (authorId) REFERENCES t_user(user_id)
	);

CREATE TABLE t_comment (
	c_id VARCHAR(40) PRIMARY KEY,
	content TEXT NOT NULL,
	authorId VARCHAR(50) NOT NULL,
	art_id VARCHAR(50) NOT NULL,
	releaseTime TIMESTAMP,
	FOREIGN KEY (authorId) REFERENCES t_user(user_id),
	FOREIGN KEY (art_id) REFERENCES article(art_id)
	);

CREATE TABLE answer (
	ans_id VARCHAR(50) PRIMARY KEY,
	content TEXT NOT NULL,
	authorId VARCHAR(50) NOT NULL,
	ques_id VARCHAR(50) NOT NULL,
	releaseTime TIMESTAMP,	
	FOREIGN KEY (authorId) REFERENCES t_user(user_id),
	FOREIGN KEY (ques_id) REFERENCES question(question_id)
	);