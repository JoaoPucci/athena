-- DML
INSERT INTO account(name, email, password) VALUES('Goku', 'goku@kamehouse.com', '$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu');
INSERT INTO account(name, email, password) VALUES('Jhonathan Joestar', 'jojo@phantomblood.com', '$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu');
INSERT INTO account(name, email, password) VALUES('Saitama', 'saitama@heroassociation.com', '$2a$10$AzmiQREFLZUnxQKj1ZM.mO1uso0WVOsRcP7kV.MqEVKhZ/bV6vQfu');

INSERT INTO role(name) VALUES('ROLE_ADMIN');
INSERT INTO role(name) VALUES('ROLE_USER');

-- Roles ADMIN
INSERT INTO account_roles(account_id, roles_id) VALUES(1, 1);
INSERT INTO account_roles(account_id, roles_id) VALUES(3, 1);

-- Roles USER
INSERT INTO account_roles(account_id, roles_id) VALUES(2, 2);
