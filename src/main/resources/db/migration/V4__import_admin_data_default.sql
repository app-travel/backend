--- password real is 'admin123@travel.app'

-------------------------------------------------

CREATE OR REPLACE PROCEDURE import_admin_data_default ()
    LANGUAGE plpgsql
AS $$
DECLARE
    _version integer;
    _record record;
    _username varchar := 'admin@travel.app';
    _password varchar := '$2a$12$4jpB1QXq2ICnBEhWGii4ve02uUe5zgvSXUdbGCMEuKgPE1iwtLRjS';
    _enabled boolean := 1 = 1;
BEGIN

    select split_part(version(), ' ', 2)::FLOAT::INTEGER INTO _version;

    if _version < 13 then
        execute 'create extension if not exists pgcrypto';
    end if;

    select * into _record
    from users u
    inner join roles r
    on u.role_id = r.id
    and r.role_name = 'ADMIN'
    limit 1;

    if _record is not null then
        return;
    end if;

    select * into _record from roles where role_name = 'ADMIN';

    if _record is null then
        call import_roles_data_default();
    end if;

    insert into users(id, username, password, is_enabled, created_at, updated_at, role_id)
    values (gen_random_uuid(), _username, _password, _enabled, now(), now(), _record.id);

END;
$$;

call import_admin_data_default();
