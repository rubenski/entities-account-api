CREATE TABLE f_account (
  id SERIAL PRIMARY KEY,
  company_id integer NOT NULL REFERENCES f_company(id),
  firstName varchar(20) NOT NULL,
  lastName varchar(20) NOT NULL,
  email varchar(50) NOT NULL,
  phone varchar(20) NOT NULL,
  expired boolean DEFAULT false,
  enabled boolean DEFAULT false,
  locked boolean DEFAULT false,
  grants varchar(200) NOT NULL,
  password varchar(200) NOT NULL
);

CREATE INDEX f_account_email ON f_account (email);