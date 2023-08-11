

-- Verwijder de tabel orders
DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE IF NOT EXISTS
    orders (id SERIAL PRIMARY KEY, item_Info VARCHAR(255), date_Info DATE, user_username VARCHAR(100));

DROP TABLE IF EXISTS accounts CASCADE;
CREATE TABLE IF NOT EXISTS accounts (
                                        id SERIAL PRIMARY KEY,
                                        user_Info VARCHAR(255),
    subscription_Info VARCHAR(255),
    email VARCHAR(255),
    comment VARCHAR(255),
    subscription_id INT,
    uploads_file_name VARCHAR(255), -- Add the uploads_file_name column
    user_username VARCHAR(255),
    expiration_date DATE,
    subscription_Status VARCHAR(255)
    );




INSERT INTO users ( username, password, email) VALUES ( 'user', '$2a$12$8l8G9.kf.foUupHgCHYM3.LHw8ANhNp250T7wkpB9xL390I0OKm9e', 'user@nl.nl');  -- user
INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');

INSERT INTO users ( username, password, email) VALUES ( 'admin@nl.nl', '$2a$12$Xpni1jOxCYAEJyJgf9yqo.44o8EEkcWVsfEKnjwjd.qfov0WB7XM6', 'admin@nl.nl');
INSERT INTO authorities (username, authority) VALUES ('admin@nl.nl', 'ROLE_ADMIN');

INSERT INTO users ( username, password, email) VALUES ( 'jftalavera@hotmail.com', '$2a$12$vzybNIIL34IK5CrtOpLBveE/219wFcRN5ghFWJ0WKUywJfX4e9Lfm', 'jftalavera@hotmail.com'); --talavera
INSERT INTO authorities (username, authority) VALUES ('jftalavera@hotmail.com', 'ROLE_ADMIN');


INSERT INTO users ( username, password, email) VALUES('user1', '$2a$12$7kOvnzB.nI7AWFFpSAZ1Q.xH3f90TUK2vOwQ1Eq.QMZhn.ul0nD4a', 'user1@test.nl');
INSERT INTO users ( username, password, email) VALUES('user2', '$2a$12$HO8Xax.52fuTSzmM0zpW7OjWKHJYn32bslwgRalYvch7ZeWeiDJWS', 'user2@test.nl');
INSERT INTO users ( username, password, email) VALUES('user3', '$2a$12$ZXhEbUVs71kvT/T6GDOwk.i.IbyU0vuiKj9GEt9GjY.D0kZkr/XqK', 'user3@test.nl');
INSERT INTO users ( username, password, email) VALUES('user4', '$2a$12$U0Zwrz40h2JNOKow5Y3RGem/Zv2d8zaiHdF3n5SK4kpQ4Ew8aX0ke', 'user4@test.nl');
INSERT INTO users ( username, password, email) VALUES('user5', '$2a$12$TtmhuzuISG7CpCO3kQBow.QYudV7wRh0GDlXwHh0LYQnx.UHHUzLe', 'user5@test.nl');
INSERT INTO users ( username, password, email) VALUES('user6', '$2a$12$vk5Y/w4oAlhXf.hTWSi8wO0luz/UbquMmTysZ81BhsWa66oikjoRK', 'user6@test.nl');


INSERT INTO authorities (username, authority) VALUES ('user1', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('user2', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('user3', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('user4', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('user5', 'ROLE_USER');
INSERT INTO authorities (username, authority) VALUES ('user6', 'ROLE_USER');


INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user1', 'annual', 'user1@test.nl', '"it is ok and i love it"', 21,  'user1', 'bag1 of leather.jpg', '2023-12-01', 'ACTIVE');
INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status) VALUES ( 'user2', 'on occasion', 'user2@test.nl', '"super nice people"', 22,  'user2', 'bag2 of leather.jpg', '2023-05-11', 'EXPIRE');
INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user3', 'annual', 'user3@test.nl', '"wonderfully ideas"', 23, 'user3', 'bag1 of leather.jpg', '2023-11-15', 'ACTIVE' );
INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status) VALUES ( 'user4', 'permanent', 'user4@test.nl', '"amazing crazy bib"', 24,  'user4', 'colbert of cotton.jpg', '2024-02-01', 'ACTIVE');
INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user5', 'once in while', 'user5@test.nl', '"its great & a super place"', 25,  'user5', 'coat of silk.jpg', '2023-12-05', 'ACTIVE');
INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user6', 'annual', 'user6@test.nl', '"amazing project"', 26, 'user6', 'Gloss of alpaca wool.jpg', '2023-12-15', 'ACTIVE');


INSERT INTO subscriptions (id, type_Subscription, expiration_Date) VALUES (21,'annual', '2023-12-01');
INSERT INTO subscriptions (id, type_Subscription, expiration_Date) VALUES (22,'annual', '2023-05-11');
INSERT INTO subscriptions (id, type_Subscription, expiration_Date) VALUES (23,'annual', '2023-11-15');
INSERT INTO subscriptions (id, type_Subscription, expiration_Date) VALUES (24,'annual', '2024-02-01');
INSERT INTO subscriptions (id, type_Subscription, expiration_Date) VALUES (25,'annual', '2023-12-05');
INSERT INTO subscriptions (id, type_Subscription, expiration_Date) VALUES (26,'annual', '2023-12-15');


INSERT INTO subscription_Subscription_Status (subscription_id, subscription_Status) VALUES (21, 'ACTIVE');
INSERT INTO subscription_Subscription_Status (subscription_id, subscription_Status) VALUES (22, 'EXPIRE');
INSERT INTO subscription_Subscription_Status (subscription_id, subscription_Status) VALUES (23, 'ACTIVE');
INSERT INTO subscription_Subscription_Status (subscription_id, subscription_Status) VALUES (24, 'ACTIVE');
INSERT INTO subscription_Subscription_Status (subscription_id, subscription_Status) VALUES (25, 'ACTIVE');
INSERT INTO subscription_Subscription_Status (subscription_id, subscription_Status) VALUES (26, 'ACTIVE');


INSERT INTO uploads (file_name, text_Type, url) VALUES ('bag1 of leather.jpg', 'image/jpeg', 'http://localhost:8083/download/bag1ofleather.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('bag2 of leather.jpg', 'image/jpeg', 'http://localhost:8083/download/bag2ofleather.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('coat of silk.jpg', 'image/jpeg', 'http://localhost:8083/download/coatofsilk.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('colbert of cotton.jpg', 'image/jpeg', 'http://localhost:8083/download/colbertofcotton.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('colbert of wool.jpg', 'image/jpeg', 'http://localhost:8083/download/colbertofwool.jpg');
INSERT INTO uploads (file_name, text_Type, url) VALUES ('hat of linen.jpg', 'image/jpeg', 'http://localhost:8083/download/hatoflinen.jpg');




-- ALTER TABLE items  ADD COLUMN order_id INT;
INSERT INTO items (id, name_Info, user_username, order_id, uploads_file_name) VALUES (1001, 'bag1 of leather', 'user1', 11, 'bag1 of leather.jpg');
INSERT INTO items (id, name_Info, user_username, order_id, uploads_file_name) VALUES (1002, 'bag2 of leather', 'user2', 12, 'bag2 of leather.jpg');
INSERT INTO items (id, name_Info, user_username, order_id, uploads_file_name) VALUES (1003, 'coat of silk', 'user3', 13, 'coat of silk.jpg');
INSERT INTO items (id, name_Info, user_username, order_id, uploads_file_name) VALUES (1004, 'colbert of cotton', 'user4', 14, 'colbert of cotton.jpg');
INSERT INTO items (id, name_Info, user_username, order_id, uploads_file_name) VALUES (1005, 'colbert of wool', 'user5', 15, 'colbert of wool.jpg');
INSERT INTO items (id, name_Info, user_username, order_id, uploads_file_name) VALUES (1006, 'hat of linen', 'user6', 16, 'hat of linen.jpg');

INSERT INTO item_tags(item_id, tags) VALUES (1001, 'WOOL_');
INSERT INTO item_tags(item_id, tags) VALUES (1001, 'ADDITIVE_FREE');
INSERT INTO item_tags(item_id, tags) VALUES (1002, 'LINEN_');
INSERT INTO item_tags(item_id, tags) VALUES (1002, 'ORGANIC');
INSERT INTO item_tags(item_id, tags) VALUES (1003, 'LEATHER_');
INSERT INTO item_tags(item_id, tags) VALUES (1003, 'SUSTAINABLE');
INSERT INTO item_tags(item_id, tags) VALUES (1004, 'COTTON_');
INSERT INTO item_tags(item_id, tags) VALUES (1004, 'NON_CHEMICAL');
INSERT INTO item_tags(item_id, tags) VALUES (1005, 'SILK_');
INSERT INTO item_tags(item_id, tags) VALUES (1005, 'SUSTAINABLE');
INSERT INTO item_tags(item_id, tags) VALUES (1006, 'LEATHER_');
INSERT INTO item_tags(item_id, tags) VALUES (1006, 'ADDITIVE_FREE');


-- INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user1', 'annual', 'user1@test.nl', '"it is ok and i love it"', 21,  'user1', 'bag1 of leather.jpg', '2023-12-01', 'ACTIVE');
-- INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status) VALUES ( 'user2', 'on occasion', 'user2@test.nl', '"super nice people"', 22,  'user2', 'bag2 of leather.jpg', '2023-05-11', 'EXPIRE');
-- INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user3', 'annual', 'user3@test.nl', '"wonderfully ideas"', 23, 'user3', 'bag1 of leather.jpg', '2023-11-15', 'ACTIVE' );
-- INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status) VALUES ( 'user4', 'permanent', 'user4@test.nl', '"amazing crazy bib"', 24,  'user4', 'colbert of cotton.jpg', '2024-02-01', 'ACTIVE');
-- INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user5', 'once in while', 'user5@test.nl', '"its great & a super place"', 25,  'user5', 'coat of silk.jpg', '2023-12-05', 'ACTIVE');
-- INSERT INTO accounts (user_Info, subscription_Info,  email, comment, subscription_id,  user_username, uploads_file_name, expiration_Date, subscription_Status ) VALUES ( 'user6', 'annual', 'user6@test.nl', '"amazing project"', 26, 'user6', 'Gloss of alpaca wool.jpg', '2023-12-15', 'ACTIVE');

ALTER TABLE orders  ADD COLUMN uploads_file_name VARCHAR;
ALTER TABLE orders  ADD COLUMN item_tags_tags VARCHAR;
INSERT INTO orders (id, item_Info, date_Info, user_username, uploads_file_name, item_tags_tags)VALUES (11, 'hat of linen ', '2023-07-11', 'user6', 'hat of linen.jpg ', 'ADDITIVE_FREE');
INSERT INTO orders (id, item_Info, date_Info, user_username, uploads_file_name, item_tags_tags)VALUES (12, 'colbert of wool', '2023-08-01', 'user5', 'colbert of wool.jpg', 'ORGANIC');
INSERT INTO orders (id, item_Info, date_Info, user_username, uploads_file_name, item_tags_tags)VALUES (13, 'colbert of cotton', '2023-07-08', 'user4','colbert of cotton.jpg', 'SUSTAINABLE');
INSERT INTO orders (id, item_Info, date_Info, user_username, uploads_file_name, item_tags_tags)VALUES (14, 'coat of silk', '2023-07-07', 'user3', 'coat of silk.jpg', 'NON_CHEMICAL');
INSERT INTO orders (id, item_Info, date_Info, user_username, uploads_file_name, item_tags_tags)VALUES (15, 'bag2 of leather', '2023-07-11', 'user2', 'bag2 of leather.jpg', 'SUSTAINABLE');
INSERT INTO orders (id, item_Info, date_Info, user_username, uploads_file_name, item_tags_tags)VALUES (16, 'bag1 of leather', '2023-07-12', 'user1','bag1 of leather.jpg' , 'ADDITIVE_FREE');

