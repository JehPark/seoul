DROP TABLE IF EXISTS users, stocks;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(250) NOT NULL,
    email VARCHAR(250) DEFAULT NULL,
    signed_up_at TIMESTAMP NOT NULL,
    password VARCHAR(250) NOT NULL
);

CREATE TABLE stocks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fullname VARCHAR(250) NOT NULL,
    ticker VARCHAR(250) NOT NULL,
    last_day_price FLOAT NOT NULL
);

CREATE TABLE stocksandusers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    stock_ids INT NOT NULL,
    user_id INT NOT NULL
);