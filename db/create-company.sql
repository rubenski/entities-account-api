CREATE TABLE f_company (
  id SERIAL PRIMARY KEY,
  name varchar(80) NOT NULL,
  street varchar(80) NOT NULL,
  number varchar(80) NOT NULL,
  zip varchar(10) NOT NULL,
  commerceNumber varchar(20) NOT NULL,
  country varchar(3) NOT NULL,
  website varchar(30)
)