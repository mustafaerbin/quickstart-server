INSERT INTO $$$raw_schema$$$.account_role_grant (grant_id, role_id)
SELECT g.id, (select id from $$$raw_schema$$$.account_role where code = 'ROLE_ADMIN')
FROM $$$raw_schema$$$.account_grant g;

INSERT INTO $$$raw_schema$$$.account_role_grant (grant_id, role_id)
SELECT g.id, (select id from $$$raw_schema$$$.account_role where code = 'ROLE_SUPER_ADMIN')
FROM $$$raw_schema$$$.account_grant g;
