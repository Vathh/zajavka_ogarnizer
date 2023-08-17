CREATE TABLE ogarnizer_user
(
    user_id                 SERIAL          NOT NULL,
    user_name               VARCHAR(50)     NOT NULL,
    password                VARCHAR(200)    NOT NULL,
    active                  BOOLEAN         NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (user_name)
);