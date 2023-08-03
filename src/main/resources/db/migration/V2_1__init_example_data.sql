insert into ogarnizer_role values
(1, 'serviceman'),
(2, 'admin');

insert into ogarnizer_client values
(1, 'Padma', 'Brylantowa 8 Suwalki', '834 123 15 51', '512 233 135'),
(2, 'Marcinex', 'Diamentowa 15 Suwalki', '456 111 22 33', '111 333 222'),
(3, 'Maciejex', 'Szafirowa 33 Suwalki', '159 444 66 77', '753 753 753'),
(4, 'Pawelex', 'Rubinowa 45 Suwalki', '753 333 88 99', '951 951 951'),
(5, 'Jolantex', 'Ametystowa 7 Suwalki', '357 789 14 41', '953 375 359');

insert into ogarnizer_user values
(1, 'Karol', 'karol123', '$2a$12$WtGwTWfG3RR9cuP1z1pgiupwCL2sK63Ts2GmvrPxKeOYyDYqJ0du6', true),
(2, 'Marian', 'marian123', '$2a$12$tgknuYcWJuLxuzc6jXhnCeY1Xzv2ElCyECyv5aXapgVrKO84dKWN6', true),
(3, 'Norbert', 'norbert123', '$2a$12$0QiavKu05usb2kCKVrtbFudUkBX4XF1fFO5GX598NE6X0ZtZ8yzZG', true),
(4, 'Wladek', 'wladek123', '$2a$12$VasTXpHMYig05oacsr8CMezX/0t4pKWHYamQ4O6CJQRuRDinl7dwG', true),
(5, 'Wojtek', 'wojtek123', '$2a$12$8NFmSojsYg1QE69Ff.C3luEUnwJQacpsIQWFj6s5wJVzuNW6J.CSK', true);

insert into ogarnizer_priority values
(1, 'low'),
(2, 'medium'),
(3, 'high');

insert into ogarnizer_stage values
(1, 'just_added'),
(2, 'in_progress'),
(3, 'waiting_for_parts'),
(4, 'to_invoice');

insert into ogarnizer_order values
(1, 2, '2020-02-02 22:22:22', 1, 2, 'procesor do laptopa', 'dell latitude 5490', '', '', 1),
(2, 3, '2030-03-03 13:13:13', 2, 3, 'piec uzywany', 'bizhub 3020', 'aefaefaexae', 'qwe12easdasd', 2),
(3, 1, '2040-04-04 14:14:14', 3, 1, 'rolka poboru papieru', 'hp 4200', '', 'fbq3ervqeQ', 3),
(4, 5, '2050-05-05 15:15:15', 3, 5, 'folia do pieca', 'hp 2035', 'erbtertserctesrt', '', 4),
(5, 4, '2060-06-06 15:15:15', 2, 4, 'toner + beben', 'ricoh mp501', 'asefasefxcaes', 'sertesrtert', 2);

insert into ogarnizer_closed_order values
(1, 2, '2020-02-02 22:22:22', 1, 'procesor do laptopa', 'dell latitude 5490', '', '',  4, '2060-06-06 15:15:15', true),
(2, 3, '2030-03-03 13:13:13', 2, 'piec uzywany', 'bizhub 3020', 'aefaefaexae', 'qwe12easdasd',  5, '2050-05-05 15:15:15', false),
(3, 1, '2040-04-04 14:14:14', 3, 'rolka poboru papieru', 'hp 4200', '', 'fbq3ervqeQ',  1, '2040-04-04 14:14:14', false),
(4, 5, '2050-05-05 15:15:15', 3, 'folia do pieca', 'hp 2035', 'erbtertserctesrt', '', 3, '2030-03-03 13:13:13', true),
(5, 4, '2060-06-06 15:15:15', 2, 'toner + beben', 'ricoh mp501', 'asefasefxcaes', 'sertesrtert', 2, '2020-02-02 22:22:22', true);

insert into ogarnizer_away_work values
(1, 5, '2090-09-09 19:19:19', 1, 1, 'do wymiany rolka poboru papieru', 'padma 3.0', 'bizhub c356', '', '', 1),
(2, 4, '2080-08-08 18:18:18', 3, 2, 'do wymiany zolty beben', 'biuro na gorze', 'bizhub c234', 'wrtcywrdyww', 'wrtcywrtyvw', 2),
(3, 3, '2070-07-07 17:17:17', 2, 3, 'brudzi na czarno', 'piwnica', 'hp 123', 'rtywrtyvwrtyr', '', 3),
(4, 2, '2060-05-05 16:16:16', 1, 4, 'zaciecie w strefie bocznych drzwiczek', 'sekretariat', 'ricoh 9851', 'dsrftvyydrt', '', 4),
(5, 1, '2050-03-03 13:13:13', 3, 5, 'nie pobiera papieru', 'magazyn', 'xerox 357', '', 'dfghdfhrv', 3);

insert into ogarnizer_closed_away_work values
(1, 5, '2090-09-09 19:19:19', 1, 'do wymiany rolka poboru papieru', 'padma 3.0', 'bizhub c356', '', '', 4, '2060-06-06 15:15:15', true),
(2, 4, '2080-08-08 18:18:18', 3, 'do wymiany zolty beben', 'biuro na gorze', 'bizhub c234', 'wrtcywrdyww', 'wrtcywrtyvw', 5, '2050-05-05 15:15:15', false),
(3, 3, '2070-07-07 17:17:17', 2, 'brudzi na czarno', 'piwnica', 'hp 123', 'rtywrtyvwrtyr', '', 1, '2040-04-04 14:14:14', false),
(4, 2, '2060-05-05 16:16:16', 1, 'zaciecie w strefie bocznych drzwiczek', 'sekretariat', 'ricoh 9851', 'dsrftvyydrt', '', 3, '2030-03-03 13:13:13', true),
(5, 1, '2050-03-03 13:13:13', 3, 'nie pobiera papieru', 'magazyn', 'xerox 357', '', 'dfghdfhrv', 2, '2020-02-02 22:22:22', true);

insert into ogarnizer_service values
(1, 3, '2013-03-03 13:13:13', 3, 3, 'brak zasilania', 'laptop dell latitude 5120', '', 'dsfcasfxa', 2),
(2, 4, '2014-04-04 14:14:14', 2, 1, 'uszkodzona matryca', 'laptop hp', 'asdfxase', '', 3),
(3, 5, '2015-05-05 15:15:15', 1, 2, 'nie dziala touchpad', 'komputer dell precision tower 8160', 'asdfxqwef', 'qwexfwxfawf', 4),
(4, 1, '2011-11-11 11:11:11', 2, 5, 'brudzenie na wydruku', 'hp 2035', '', 'awefwaefwaef', 1),
(5, 2, '2012-12-12 12:12:12', 3, 4, 'brudzi na zolto i czerwono', 'bizhub 3350', '', '', 2);

insert into ogarnizer_closed_service values
(1, 3, '2013-03-03 13:13:13', 3, 'brak zasilania', 'laptop dell latitude 5120', '', 'dsfcasfxa', 4, '2012-12-12 12:12:12', false),
(2, 4, '2014-04-04 14:14:14', 2, 'uszkodzona matryca', 'laptop hp', 'asdfxase', '', 5, '2011-11-11 11:11:11', false),
(3, 5, '2015-05-05 15:15:15', 1, 'nie dziala touchpad', 'komputer dell precision tower 8160', 'asdfxqwef', 'qwexfwxfawf', 2, '2015-05-05 15:15:15', true),
(4, 1, '2011-11-11 11:11:11', 2, 'brudzenie na wydruku', 'hp 2035', '', 'awefwaefwaef', 1, '2015-05-05 15:15:15', true),
(5, 2, '2012-12-12 12:12:12', 3, 'brudzi na zolto i czerwono', 'bizhub 3350', '', '', 3, '2014-04-04 14:14:14', true);

insert into ogarnizer_user_role values
(1,1), (2,1), (3,1), (4,2), (5,2);

