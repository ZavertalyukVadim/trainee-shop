
CREATE TABLE IF NOT EXISTS departments (
  id SERIAL,
  name varchar(40)
);

INSERT INTO departments (id, name) VALUES
  (1, 'Board'),
  (2, 'IT'),
  (3, 'HR'),
  (4, 'Security')
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS positions (
  id SERIAL,
  name varchar(40)
);

INSERT INTO positions (id, name) VALUES
  (1, 'President'),
  (2, 'Vice President'),
  (3, 'Head Of Department'),
  (4, 'Developer'),
  (5, 'HR Manager'),
  (6, 'Sequrity Guard')
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS employees (
  id SERIAL,
  name varchar(40),
  gender varchar(1),
  year_of_birth date,
  dep_id integer,
  position_id integer,
  salary integer,
  chief_id integer
);

INSERT INTO employees (id, name,gender,year_of_birth,dep_id,position_id,salary,chief_id) VALUES
  (2, 'Vadimka','M','1961-07-16',2,3,1000,10),
  (3, 'vlad','M','1961-08-16',0,3,50,10),
  (4, 'misha','M','1961-08-16',2,4,1000,2),
  (5, 'sergey','M','1961-10-16',2,4,1000,2),
  (6, 'bob','M','1961-09-16',0,6,50,3),
  (7, 'bill','M','1961-12-16',0,6,50,3),
  (8, 'marta','W','1961-11-16',2,4,500,2),
  (9, 'roks','W','1961-01-16',1,1,2000,0),
  (10, 'elena','W','1961-02-16',1,2,1500,9),
  (11, 'nikon','M','1961-07-16',3,3,1000,10),
  (12, 'kostya','M','1961-06-16',1,3,1000,10)
ON CONFLICT DO NOTHING;


