-- Пользователи
CREATE TABLE IF NOT EXISTS users
(
    id VARCHAR(36) PRIMARY KEY,
    login VARCHAR(256) NOT NULL,
    first_name VARCHAR(128) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    age INTEGER NOT NULL,
    gender VARCHAR(1) NOT NUll,
    interests VARCHAR(1024) NOT NULL,
    city VARCHAR(40) NOT NULL
);

COMMENT ON TABLE users
IS 'Пользователь';

COMMENT ON COLUMN users.login
IS 'Логин';

COMMENT ON COLUMN users.first_name
IS 'Имя';

COMMENT ON COLUMN users.last_name
IS 'Фамилия';

COMMENT ON COLUMN users.age
IS 'Возраст';

COMMENT ON COLUMN users.gender
IS 'Пол';

COMMENT ON COLUMN users.interests
IS 'Интересы';

COMMENT ON COLUMN users.city
IS 'Город';