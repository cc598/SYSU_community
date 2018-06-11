
INSERT INTO t_user VALUES(
	'15331138', '赖达强', '1234', NULL, '男', '数据科学与计算机学院', '2013', '2qq@qq.com'
);	
INSERT INTO t_user VALUES(
	'15331073', '方虹', '1234', NULL, '女', '数据科学与计算机学院', '2015', 'sysu@163.com'
);
INSERT INTO t_user VALUES(
	'15331075', '方晓吟', '1234', NULL, '女', '数据科学与计算机学院', '2015', 'sysu@163.com'
);	
	
INSERT INTO question VALUES('1', '测试问题', '下雨吗？', '15331138',NULL); 
INSERT INTO question VALUES('2', '测试问题2', '放假？', '15331073',NULL); 

INSERT INTO answer VALUES (NULL, '不知道呢','15331073','1',NULL) ;
INSERT INTO answer VALUES (NULL, '不会下雨的吧','15331075','1',NULL) ;
INSERT INTO answer VALUES (NULL, '端午放假！','15331138','2',NULL) ;

INSERT INTO article VALUES (NULL, '论学习的重要性','blablabla','15331138',NULL, NULL, '学术');
INSERT INTO article VALUES (NULL, '怎么更好的打代码','blablabla','15331075',NULL, NULL, '编程');

INSERT INTO t_comment VALUES(NULL, '沙发！', '15331073', '1',NULL);
INSERT INTO t_comment VALUES(NULL, '板凳！', '15331075', '1',NULL);
