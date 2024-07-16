CREATE DATABASE library;
USE library;

-- creating tables
CREATE TABLE authors(
id INT NOT NULL AUTO_INCREMENT,
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
age INT,
PRIMARY KEY(id)
);

CREATE TABLE books(
id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(200) NOT NULL,
author_id INT,
genre VARCHAR(50),
publish_date DATE,
PRIMARY KEY(id),
FOREIGN KEY(author_id) REFERENCES authors(id)
	ON DELETE SET NULL
    ON UPDATE CASCADE
);

CREATE TABLE customers(
first_name VARCHAR(100) NOT NULL,
last_name VARCHAR(100) NOT NULL,
id INT NOT NULL AUTO_INCREMENT,
age INT,
PRIMARY KEY(id)
);

CREATE TABLE borrowbook(
book_id INT NOT NULL,
customer_id INT NOT NULL,
borrow_date DATE,
return_date DATE,
PRIMARY KEY(customer_id, book_id),
FOREIGN KEY(book_id) REFERENCES books(id)
	ON DELETE CASCADE
    ON UPDATE CASCADE,
FOREIGN KEY(customer_id) REFERENCES customers(id)
	ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- adding records
INSERT INTO authors(first_name, last_name, age)
VALUES ('Noor', 'AlHadidi', 20),
	   ('Dina', 'Salem', 46),
       ('Marwan', 'Ahmed', 21),
       ('Nadeen', 'Samir', NULL);

INSERT INTO books(title, author_id, genre, publish_date)
VALUES ('The Cousins', 1, 'Mystery', '2019-04-21'),
	   ('Gone with the Wind', 3, 'Young Adult', '2008-11-06'),
       ('The Midnight Library', 2, 'Young Adult', '2016-08-15'),
       ('The Silent Patient', 3, 'Psychological Thriller', '2014-09-06'),
       ('Circe', 4, 'Greek Mythology', '2021-03-05');

INSERT INTO customers(first_name, last_name, age)
VALUES ('Salma', 'Mohamed', 16),
	   ('Ahmed', 'Mostafa', 37);

INSERT INTO borrowbook(book_id, customer_id, borrow_date, return_date)
VALUES (2, 1, '2024-06-20', NULL),
	   (3, 2, '2023-12-24', '2024-02-17');