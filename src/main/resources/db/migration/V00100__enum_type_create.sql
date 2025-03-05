-- enumstatus
DO
$$
BEGIN
   IF
NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'enumstatus') THEN
create type $$$raw_schema$$$.EnumStatus as enum ('AKTIF','MANTIKSAL_SILINMIS','PASIF');
create cast (varchar as $$$raw_schema$$$.EnumStatus) with inout as implicit;
create cast ($$$raw_schema$$$.EnumStatus as varchar) with inout as implicit;
END IF;
END $$;
