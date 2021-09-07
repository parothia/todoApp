INSERT INTO PROJECT (id, title, created_date) VALUES ('1', 'P1', '1992-10-1');
INSERT INTO PROJECT (id, title, created_date) VALUES ('2', 'P2', '1992-10-2');

INSERT INTO TASK (task_id, description, created_date, p_fk) VALUES('1','task1','1992-10-1','1');
INSERT INTO TASK (task_id, description, created_date, p_fk) VALUES('2','task2','1992-10-1','1');

INSERT INTO USERS (username,password,enabled) VALUES ('admin','$2a$10$zxvEq8XzYEYtNjbkRsJEbukHeRx3XS6MDXHMu8cNuNsRfZJWwswDy', 1);