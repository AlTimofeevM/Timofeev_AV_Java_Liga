-- Пользователи
CREATE TABLE IF NOT EXISTS users
(
    id VARCHAR(36) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    age INTEGER NOT NULL,
    gender CHAR NOT NUll,
    interests VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL
);

COMMENT ON TABLE users
IS 'Пользователь';

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

-- Отношение дружбы между пользователями
CREATE TABLE IF NOT EXISTS relationship
(
    relating_user_id VARCHAR (36) NOT NULL,
    related_user_id VARCHAR (36) NOT NULL,
    FOREIGN KEY (relating_user_id) REFERENCES users (id),
    FOREIGN KEY (related_user_id) REFERENCES users (id),
    PRIMARY KEY (relating_user_id, related_user_id)
);