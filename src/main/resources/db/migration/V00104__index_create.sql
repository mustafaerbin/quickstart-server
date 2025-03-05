/**
  account_user - create index
 */
create unique index uk_account_user_username on $$$raw_schema$$$.account_user using btree (username);
create index uk_account_user_durum on $$$raw_schema$$$.account_user using btree (status);
/**
    account_role
 */
create unique index uk_account_role_code on $$$raw_schema$$$.account_role using btree (code);
create index uk_account_role_durum on $$$raw_schema$$$.account_role using btree (status);
/**
    account_grant
 */
create unique index uk_account_grant_code on $$$raw_schema$$$.account_grant using btree (code);
create index uk_account_grant_durum on $$$raw_schema$$$.account_grant using btree (status);
