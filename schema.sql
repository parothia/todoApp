CREATE TABLE PROJECT(
  id SERIAL PRIMARY KEY,
  title VARCHAR(32) NOT NULL,
  created_date DATE NOT NULL,
  updated_date DATE
);

CREATE TABLE TASK(
  task_id SERIAL PRIMARY KEY,
  description VARCHAR(128),
  status VARCHAR(64),
  created_date DATE NOT NULL,
  updated_date DATE,
  p_fk INT REFERENCES PROJECT(id)
);

CREATE TABLE USERS (
  user_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(45) NOT NULL,
  password varchar(64) NOT NULL
);

