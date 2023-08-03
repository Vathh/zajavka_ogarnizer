CREATE TABLE ogarnizer_role(
  role_id SERIAL NOT NULL,
  role VARCHAR(20) NOT NULL,
  PRIMARY KEY (role_id)
);

CREATE TABLE ogarnizer_user_role(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  CONSTRAINT fk_ogarnizer_user_role_user
    FOREIGN KEY (user_id)
      REFERENCES ogarnizer_user (user_id),
  CONSTRAINT fk_ogarnizer_user_role_role
    FOREIGN KEY (role_id)
      REFERENCES ogarnizer_role (role_id)
);