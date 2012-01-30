insert into role values ('ROLE_USER', 'User');
insert into role values ('ROLE_ADMIN', 'Administrator');
insert into role values ('ROLE_REMOTE', 'Remote User');

insert into app_user values ('clarence', 'clarence', 'Clarence Ho', 'prospring3', '2011-10-21', 'prospring3', '2011-10-21');
insert into app_user values ('admin', 'admin', 'Administrator', 'prospring3', '2011-10-21', 'prospring3', '2011-10-21');
insert into app_user values ('remote', 'remote', 'Remote User', 'prospring3', '2011-10-21', 'prospring3', '2011-10-21');
insert into app_user values ('user', 'user', 'Normal User', 'prospring3', '2011-10-21', 'prospring3', '2011-10-21');

insert into user_role_detail values ('clarence','ROLE_ADMIN');
insert into user_role_detail values ('clarence','ROLE_USER');
insert into user_role_detail values ('admin','ROLE_ADMIN');
insert into user_role_detail values ('admin','ROLE_USER');
insert into user_role_detail values ('remote','ROLE_REMOTE');
insert into user_role_detail values ('user','ROLE_USER');

insert into category values ('Java', null);
insert into category values ('Spring', null);
insert into category values ('JPA', null);
insert into category values ('Spring Batch', 'Spring');
insert into category values ('Spring Integration', 'Spring');
insert into category values ('Spring Webflow', 'Spring');
insert into category values ('Spring Roo', 'Spring');
insert into category values ('Hibernate', 'JPA');
insert into category values ('Eclipse Link', 'JPA');
insert into category values ('Collections', 'Java');
insert into category values ('JSR-303', 'Java');