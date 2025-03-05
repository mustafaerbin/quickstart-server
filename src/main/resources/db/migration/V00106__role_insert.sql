INSERT INTO $$$raw_schema$$$.account_role (name, code, description, create_date, update_date, version, status)
VALUES ('ADMIN', 'ROLE_ADMIN', 'Admin role', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'AKTIF'),
       ('SUPER ADMIN', 'ROLE_SUPER_ADMIN', 'Super Admin role', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'AKTIF'),
       ('USER', 'ROLE_USER', 'User role', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'AKTIF'),
       ('URUN', 'ROLE_URUN_MODULE', 'Urun role', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'AKTIF'),
       ('SIPARIS', 'ROLE_SIPARIS_MODULE', 'Siparis role', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'AKTIF'),
       ('FATURA', 'ROLE_FATURA_MODULE', 'Fatura role', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 'AKTIF');


-- `hesap_kullanici_roles` tablosuna roller ekleme
INSERT INTO $$$raw_schema$$$.account_user_roles (role_id, user_id)
SELECT g.id, (select id from $$$raw_schema$$$.account_user where username = 'admin')
FROM $$$raw_schema$$$.account_role g;

INSERT INTO $$$raw_schema$$$.account_user_roles (role_id, user_id)
SELECT g.id, (select id from $$$raw_schema$$$.account_user where username = 'super')
FROM $$$raw_schema$$$.account_role g;
