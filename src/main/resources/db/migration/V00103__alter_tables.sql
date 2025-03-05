/**
    account_user_roles
 */
alter table $$$raw_schema$$$.account_user_roles
    add foreign key (role_id) references $$$raw_schema$$$.account_role (id) match simple on update no action on delete no action;
alter table $$$raw_schema$$$.account_user_roles
    add foreign key (user_id) references $$$raw_schema$$$.account_user (id) match simple on update no action on delete no action;
/**
    account_role_grant
 */
alter table $$$raw_schema$$$.account_role_grant
    add foreign key (grant_id) references $$$raw_schema$$$.account_grant (id) match simple on update no action on delete no action;
alter table $$$raw_schema$$$.account_role_grant
    add foreign key (role_id) references $$$raw_schema$$$.account_role (id) match simple on update no action on delete no action;
