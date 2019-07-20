insert into posts (id,title, author, content, regdate, moddate) values (1,'테스트1', 'test1@gmail.com', '테스트1의 본문', now(), now());
insert into posts (id,title, author, content, regdate, moddate) values (2,'테스트2', 'test2@gmail.com', '테스트2의 본문', now(), now());
insert into users(id,moddate,name,phone,regdate,user_id,user_pw) values(1,'2019-06-28 17:00:00','최성훈','01024677304','2019-06-28 17:00:00','tjdgns461','1234');
INSERT INTO COMMON(USER_ID,TYPE_CODE,TYPE_NAME,REGDATE,MODDATE) VALUES('tjdgns461','01','식비','2019-07-13 01:07:10','2019-07-13 01:07:10');
INSERT INTO COMMON(USER_ID,TYPE_CODE,TYPE_NAME,REGDATE,MODDATE) VALUES('tjdgns461','02','기타비','2019-07-13 01:07:10','2019-07-13 01:07:10');
INSERT INTO COMMON(USER_ID,TYPE_CODE,TYPE_NAME,REGDATE,MODDATE) VALUES('tjdgns461','03','사치비','2019-07-13 01:07:10','2019-07-13 01:07:10');
INSERT INTO USER_MONEY_ITEM(USER_ID,MODDATE,MONTH,PRICE,RATIO,REGDATE,TYPE_CODE,YEAR) VALUES('tjdgns461', '2019-06-28 17:00:00', '07', '50000', '496743', '2019-06-28 17:00:00','03','2019');
INSERT INTO USER_MONEY(USER_ID,MODDATE,MONEY,MONTH,REGDATE,YEAR) VALUES('tjdgns461', '2019_06_28 17:00:00', '546743', '07', '2019-06-28 17:00:00','2019');