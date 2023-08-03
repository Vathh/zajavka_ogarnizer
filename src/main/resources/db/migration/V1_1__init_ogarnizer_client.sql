CREATE TABLE ogarnizer_client
(
    client_id               SERIAL        NOT NULL,
    name                    VARCHAR(100)    NOT NULL,
    address                 VARCHAR(100)    NOT NULL,
    NIP                     VARCHAR(30)     NOT NULL,
    phone_number            VARCHAR(20)     NOT NULL,
    UNIQUE (NIP),
    PRIMARY KEY (client_id)
);