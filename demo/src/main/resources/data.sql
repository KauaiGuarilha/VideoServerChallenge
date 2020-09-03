insert into videoserver.user (id, mobile_token, name, password) values (1, '123456', 'user1', '$2a$10$bpxm2.iWrDvBjp5MYE.pB.bruZi2XKGBHpnssCBv40oPqBSdpWv.G');
insert into videoserver.user (id, mobile_token, name, password) values (2, '12345', 'user2', '$2a$10$cYZb9PGZr6CLYcNXQBqOIuD3Pe1kvVavCP44xwBTwDD/9R3ELg02a');
insert into videoserver.user (id, mobile_token, name, password) values (2, '12345', 'user', '$2a$10$cYZb9PGZr6CLYcNXQBqOIuD3Pe1kvVavCP44xwBTwDD/9R3ELg02a');

insert into videoserver.user (id, name) values (1, 'ROLE_USER');
insert into videoserver.user (id, name) values (1, 'ROLE_ADMIN');

insert into videoserver.user_roles (user_id, role_id) values (1, 1);
insert into videoserver.user_roles (user_id, role_id) values (2, 2);
insert into videoserver.user_roles (user_id, role_id) values (3, 1);