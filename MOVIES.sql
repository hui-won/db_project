create table movie_personnel(
Ssn int not null,
Name varchar(20) not null,
Bdate date,
primary key(Ssn));
create index ssn_index on movie_personnel(ssn) using hash;
create index bdate_index on movie_personnel(bdate) using btree;

create table movie(
Movie_id int not null,
Title varchar(45) not null,
Dssn int not null,
Opening_date date,
primary key(movie_id),
foreign key(dssn) references movie_personnel(ssn)
on delete cascade on update cascade);
create index movie_id_index on movie(movie_id) using hash;
create index opening_date_index on movie(opening_date) using btree;

create table performs_in( 
Assn int not null,
Movie_id int not null,
primary key(assn, movie_id),
foreign key(assn) references movie_personnel(ssn)
on delete cascade on update cascade,
foreign key(movie_id) references movie(movie_id)
on delete cascade on update cascade);
create index assn_index on performs_in(assn) using hash;
create index movie_id_index on performs_in(movie_id) using hash;

create table lead_role( 
Lssn int not null,
Movie_id int not null,
primary key(lssn, movie_id),
foreign key(lssn) references movie_personnel(ssn)
on delete cascade on update cascade,
foreign key(movie_id) references movie(movie_id)
on delete cascade on update cascade);
create index lssn_index on lead_role(lssn) using hash;
create index movie_id_index on lead_role(movie_id) using hash;

create table produces(Pssn int not null,
Movie_id int not null,
primary key(pssn, movie_id),
foreign key(pssn) references movie_personnel(ssn)
on delete cascade on update cascade,
foreign key(movie_id) references movie(movie_id)
on delete cascade on update cascade);
create index pssn_index on produces(pssn) using hash;
create index movie_id_index on produces(movie_id) using hash;

delimiter //
create trigger insert_hero before insert on lead_role
for each row
begin
set @assn=(select assn from performs_in where movie_id=new.movie_id and assn=new.lssn);
if @assn is null then
insert into performs_in set assn=new.lssn, movie_id=new.movie_id;
end if;
end;//
delimiter ;

delimiter //
create trigger update_hero before update on lead_role
for each row
begin
set @assn=(select assn from performs_in where movie_id=new.movie_id and assn=new.lssn);
if @assn is null then
insert into performs_in set assn=new.lssn, movie_id=new.movie_id;
end if;
end;//
delimiter ;

delimiter //
create trigger delete_hero before delete on lead_role
for each row
begin
set @cnt=(select count(*) from lead_role where movie_id=old.movie_id);
if @cnt<=1 then
insert into lead_role set lssn=old.lssn,movie_id=old.movie_id;
end if;
end;//
delimiter ;

delimiter //
create trigger delete_prod before delete on produces
for each row
begin
set @cnt=(select count(*) from produces where movie_id=old.movie_id);
if @cnt<=1 then
insert into produces set pssn=old.pssn,movie_id=old.movie_id;
end if;
end;//
delimiter ;

create view movie_info(title,num_of_hero,num_of_actor,num_of_producer) as 
select title, count(distinct lssn) , count(distinct assn), count(distinct pssn)
from movie as m, performs_in as a, lead_role as l, produces as p
where m.movie_id=a.movie_id and m.movie_id=l.movie_id and m.movie_id=p.movie_id 
group by m.movie_id;

-- 아래에 insert문은 모두 웹페이지에서 인서트한 내용과 같습니다. 
-- 확인하실 때 웹 페이지에서 모두 insert 하여 확인하시기 어려운 관계로 아래 적어두겠습니다. 

insert into movie_perssonnel values
(1,'a','2000-11-10'),
(2,'b','1997-01-12'),
(3,'c','1955-12-31'),
(4,'d','2010-04-28'),
(5,'e','2001-02-28');

insert into movie values
(1,'hello',1,'1998-04-30'),
(2,'hihi',1,'2020-12-06');

insert into produces values
(2,1),
(4,2);

insert into lead_role values
(3,1),
(4,1),
(5,2);

insert into performs_in values
(1,1),
(3,2),
(4,2),
(5,1);


