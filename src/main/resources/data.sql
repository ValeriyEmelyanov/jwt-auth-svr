INSERT INTO app_user (login, password, enabled)
SELECT 'admin', '$2a$10$ZK4HDvNNx75n1gwkKC7cMONZzFRsehFl2.x1.82N4a2oQP5zA.4Du', TRUE
    WHERE NOT EXISTS (SELECT * FROM app_user WHERE login = 'admin');

INSERT INTO app_user_roles (user_id, role)
SELECT u.id, 'ADMIN' FROM app_user AS u
WHERE u.login = 'admin' AND NOT EXISTS (SELECT * FROM app_user_roles WHERE role = 'ADMIN');
