select * from student;
select * from teacher;
select * from course;
select * from studentinfo;
select count(*) from studentinfo where sno in( select sno from student)
select sno from studentinfo group by snoc
select cno from studentinfo group by cno
select * from testinfo
delete from testinfo
delete from studentinfo
commit
