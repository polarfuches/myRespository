create tablespace graduate datafile 'D:\DataBase\GraduationProject\graduate.dbf' size 20m autoextend on;
alter user system default tablespace graduate
--课程表
create table course(
  cno varchar2(20),
  cname varchar2(50)
);
alter table course add constraint pk_course_cno primary key(cno);

--学生表
create table student(
  sno varchar2(20),
  sname varchar2(50)
);
alter table student add constraint pk_student_sno primary key(sno);
alter table student add spassw varchar2(30);
--教室
create table floors(
  fno varchar2(20),
  fname varchar2(50)
);

--补考场次表
create table times(
  tyear integer,
  starttime varchar2(20),
  endtime varchar2(20)
);
alter table times add constraint pk_times_tid primary key(tid);
--补考分数
create table score(
 sno varchar2(20),
 cno varchar2(20),
 score varchar2(3)
);select * from score drop table score
alter table score add constraint fk_score_sno foreign key(sno) references student(sno);
alter table score add constraint fk_score_cno foreign key(cno) references course(cno);

--补考学生名单表
create table studentinfo(
  sno varchar2(20),
  cno varchar2(20)
);
drop table studentinfo
alter table studentinfo add constraint fk_studentinfo_sno foreign key(sno) references student(sno);
alter table studentinfo add constraint fk_studentinfo_cno foreign key(cno) references course(cno);
alter table studentinfo drop constraint fk_studentinfo_cno;
--课程补考信息表
create table testinfo(
  cno varchar2(20),
  addr varchar2(20),
  times varchar2(20),
  startime varchar2(20),
  endtime varchar2(20)
);
commit
drop table testinfo
alter table testinfo add constraint pk_testinfo_tino primary key(tino);
alter table testinfo add constraint fk_testinfo_cno foreign key(cno) references course(cno);
alter table testinfo add constraint fk_testinfo_tid foreign key(tid) references times(tid);

