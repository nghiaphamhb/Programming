--DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS "coordinates" CASCADE;
DROP TABLE IF EXISTS "dragon_head" CASCADE;
DROP TABLE IF EXISTS "dragons" CASCADE;

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE "coordinates" (
    id BIGSERIAL PRIMARY KEY,
    x INT,
    y INT
);

CREATE TABLE "dragon_head" (
    id BIGSERIAL PRIMARY KEY,
    eyes_count INT
);

CREATE TABLE "dragons" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    coordinates_id BIGINT REFERENCES coordinates(id),
    creation_date VARCHAR(255) NOT NULL,
    age INT NOT NULL,
    weight BIGINT,
    speaking VARCHAR(255) NOT NULL,
    color VARCHAR(255),
    dragon_head_id BIGINT REFERENCES dragon_head(id),
    user_id BIGINT REFERENCES users(id)
);




