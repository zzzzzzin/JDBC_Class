
-- C:\class\code\jdbc\Community\script.sql

create table tblUser (
    id varchar2(30) primary key,
    name varchar2(30) not null,
    pw varchar2(30) not null,
    email varchar2(100) not null
);

alter table tblUser
    add (point number default 1000 not null);

create table tblBoard (
    seq number primary key,
    subject varchar2(1000) not null,
    content varchar2(2000) not null,
    regdate date default sysdate not null,
    id varchar2(30) not null
        constraint tblboard_id_fk references tblUser(id)
);
drop sequence seqBoard;
create sequence seqBoard;

insert into tblUser values ('hong', '홍길동', '1111', 'hong@gmail.com');
insert into tblUser values ('dog', '강아지', '1111', 'dog@naver.com');
insert into tblUser values ('cat', '고양이', '1111', 'cat@daum.net');

insert into tblBoard 
    values (seqBoard.nextVal, '게시판입니다.', '내용입니다.', default, 'hong');

commit;

select * from tblUser;
drop table tblBoard;
select * from tblBoard;

delete from tblBoard;






