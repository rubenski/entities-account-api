CREATE TABLE f_account (
  id SERIAL PRIMARY KEY,
  firstName varchar(80) NOT NULL,
  lastName varchar(80) NOT NULL,
  email varchar(80) NOT NULL,
  expired boolean DEFAULT false,
  enabled boolean DEFAULT false,
  locked boolean DEFAULT false
);

CREATE INDEX f_account_email ON f_account (email);