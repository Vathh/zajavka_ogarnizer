CREATE TABLE ogarnizer_closed_service
(
    closed_service_id       SERIAL          NOT NULL,
    created_by_user_id      INT             NOT NULL,
    created_date            TIMESTAMP       NOT NULL,
    client_id               INT             NOT NULL,
    description             VARCHAR(100),
    device                  VARCHAR(50)     NOT NULL,
    additional_info         VARCHAR(100),
    update_info             VARCHAR(100),
    closed_by_user_id       INT             NOT NULL,
    closed_date             TIMESTAMP       NOT NULL,
    success                 BOOLEAN         NOT NULL,
    PRIMARY KEY (closed_service_id),
    CONSTRAINT fk_closed_service_created_by_user
        FOREIGN KEY (created_by_user_id)
            REFERENCES ogarnizer_user (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_closed_service_client
        FOREIGN KEY (client_id)
            REFERENCES ogarnizer_client (client_id) ON DELETE CASCADE,
    CONSTRAINT fk_closed_service_closed_by_user
            FOREIGN KEY (closed_by_user_id)
                REFERENCES ogarnizer_user (user_id) ON DELETE CASCADE
);