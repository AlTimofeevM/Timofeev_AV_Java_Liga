-- Отношение дружбы между пользователями
CREATE TABLE IF NOT EXISTS relationship
(
    relating_user_id VARCHAR (36) NOT NULL,
    related_user_id VARCHAR (36) NOT NULL,
    FOREIGN KEY (relating_user_id) REFERENCES users (id),
    FOREIGN KEY (related_user_id) REFERENCES users (id),
    PRIMARY KEY (relating_user_id, related_user_id)
);