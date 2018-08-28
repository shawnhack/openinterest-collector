CREATE TABLE openinterest(
   id SERIAL PRIMARY KEY NOT NULL,
   record_time TIMESTAMP NOT NULL,
   amount REAL
);


DROP TABLE openinterest;


CREATE TABLE dailycandle(
   id SERIAL PRIMARY KEY NOT NULL,
   candle_day DATE NOT NULL UNIQUE,
   open REAL,
   high REAL,
   low REAL,
   close REAL
);


DROP TABLE dailycandle;