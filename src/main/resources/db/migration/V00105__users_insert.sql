-- `account_user` tablosuna örnek kullanıcı ekleme
INSERT INTO $$$raw_schema$$$.account_user (username, password,  name, surname, email, create_date,
                                              update_date, status,  version)
VALUES ('admin', '$2a$10$PLjW/FHhq04CjPKBuNOxI.ahuwSmObBuLL6Lx/c41hci/n3hteXz6',  'Admin', 'Admin',
        'admin@admin.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF',  0),
       ('super', '$2a$10$PLjW/FHhq04CjPKBuNOxI.ahuwSmObBuLL6Lx/c41hci/n3hteXz6',  'Super Admin',
        'Super Admin', 'adm(in@admin.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF',  0),
       ('user', '$2a$10$PLjW/FHhq04CjPKBuNOxI.ahuwSmObBuLL6Lx/c41hci/n3hteXz6',  'User', 'User',
        'user@admin.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF',  0);
