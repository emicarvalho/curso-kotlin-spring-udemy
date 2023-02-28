CREATE TABLE customer(
    id int auto_increment not null,
    name varchar(255) not null,
    email varchar(255) not null unique
);