-- Xóa các bảng nếu chúng tồn tại
DROP TABLE IF EXISTS "users" CASCADE;
DROP TABLE IF EXISTS "roles" CASCADE;
DROP TABLE IF EXISTS "coordinates" CASCADE;
DROP TABLE IF EXISTS "dragon_head" CASCADE;
DROP TABLE IF EXISTS "dragons" CASCADE;

-- Tạo bảng roles
CREATE TABLE roles (
    id BIGSERIAL PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    access VARCHAR(255) NOT NULL
);

-- Tạo bảng users với khóa ngoại tham chiếu đến roles
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT REFERENCES roles(id)
);

-- Chèn dữ liệu vào bảng roles
INSERT INTO roles (role, access) VALUES
('admin', 'cuder'),
('creator', 'c----'),
('developer', '-u---'),
('cleaner', '--d--'),
('tester', '---e-'),
('analyst', '----r');

-- Chèn dữ liệu vào bảng users
INSERT INTO users (username, password, role_id) VALUES
('admin', '3627909a29c31381a071ec27f7c9ca97726182aed29a7ddd2e54353322cfb30abb9e3a6df2ac2c20fe23436311d678564d0c8d305930575f60e2d3d048184d79', 1);

-- Tạo bảng coordinates
CREATE TABLE coordinates (
    id BIGSERIAL PRIMARY KEY,
    x INT,
    y INT
);

-- Tạo bảng dragon_head
CREATE TABLE dragon_head (
    id BIGSERIAL PRIMARY KEY,
    eyes_count INT
);

-- Tạo bảng dragons với các khóa ngoại tham chiếu đến coordinates, dragon_head và users
CREATE TABLE dragons (
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
