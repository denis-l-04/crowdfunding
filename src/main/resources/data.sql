CREATE DATABASE crowdfunding;
USE crowdfunding;
INSERT INTO category (id, name, slug) values (1, "Творчество", "creativity");
INSERT INTO category (id, name, slug) values (2, "Общественное", "social");
INSERT INTO category (id, name, slug) values (3, "Животные", "animals");
INSERT INTO category (id, name, slug) values (4, "Другое", "other");
INSERT INTO role (id, name) values (1, "ROLE_USER");