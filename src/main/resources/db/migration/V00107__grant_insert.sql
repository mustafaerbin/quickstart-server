-- `HESAP_YETKI` tablosuna örnek yetkiler ekleme
INSERT INTO $$$raw_schema$$$.account_grant (code, name, permission, create_date, update_date, status, version)
VALUES( 'GRANT_APP', 'Uygulama yetkisidir. Tüm rollere verilir.', 'READ_PRIVILEGES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0),
      ( 'GRANT_ROLE_VIEW', 'Rollerin görüntülenmesi. Kullanıcılar mevcut rollerin detaylarına bakabilir.', 'READ_PRIVILEGES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0),
      ( 'GRANT_ROLE_CREATE', 'Yeni rollerin oluşturulması. Yeni kullanıcı rolleri tanımlanabilir.', 'WRITE_PRIVILEGES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0),
      ( 'GRANT_ROLE_UPDATE', 'Roller üzerinde güncelleme yapılması. Mevcut rollerin izinleri ve açıklamaları değiştirilebilir.', 'UPDATE_PRIVILEGES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0),
      ( 'GRANT_ROLE_DELETE', 'Roller üzerinde silme işlemi yapılması. Kullanıcılar rollerini silebilir.', 'DELETE_PRIVILEGES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0),
      ( 'GRANT_REPORT_VIEW', 'Raporlama ekranının görüntülenmesi ve ilgili raporların indirilebilmesi.', 'READ_PRIVILEGES', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0),
      ( 'GRANT_SUPER_ADMIN', 'SUPER_ADMIN yetkisi.', 'GRANT_SUPER_ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'AKTIF', 0);


