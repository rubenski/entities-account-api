CREATE TABLE f_company (
  uuid uuid primary key default uuid_generate_v4(),
  name varchar(80) NOT NULL,
  street varchar(80) NOT NULL,
  number varchar(80) NOT NULL,
  zip varchar(10) NOT NULL,
  commerceNumber varchar(20) NOT NULL UNIQUE,
  country varchar(3) NOT NULL,
  website varchar(30)
)