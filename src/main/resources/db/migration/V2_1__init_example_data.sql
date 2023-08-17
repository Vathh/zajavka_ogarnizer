insert into ogarnizer_role (role) values
('serviceman'),
('admin');

insert into ogarnizer_user (user_name, password, active) values
('Karol', '$2a$12$WtGwTWfG3RR9cuP1z1pgiupwCL2sK63Ts2GmvrPxKeOYyDYqJ0du6', true),
('Marian', '$2a$12$tgknuYcWJuLxuzc6jXhnCeY1Xzv2ElCyECyv5aXapgVrKO84dKWN6', true),
('Norbert', '$2a$12$0QiavKu05usb2kCKVrtbFudUkBX4XF1fFO5GX598NE6X0ZtZ8yzZG', true),
('Wladek', '$2a$12$VasTXpHMYig05oacsr8CMezX/0t4pKWHYamQ4O6CJQRuRDinl7dwG', true),
('Wojtek', '$2a$12$8NFmSojsYg1QE69Ff.C3luEUnwJQacpsIQWFj6s5wJVzuNW6J.CSK', true);

insert into ogarnizer_priority (name) values
('low'),
('medium'),
('high');

insert into ogarnizer_stage (name) values
('just_added'),
('in_progress'),
('waiting_for_parts'),
('to_invoice');

insert into ogarnizer_user_role values
(1,1), (2,1), (3,1), (4,2), (5,2);