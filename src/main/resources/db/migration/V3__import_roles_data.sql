CREATE OR REPLACE PROCEDURE insert_into_roles(
    _role_name varchar
)
    LANGUAGE plpgsql
AS $$
DECLARE
    --
BEGIN
    insert into roles(id, created_at, updated_at, role_name)
    values (gen_random_uuid(), now(), now(), _role_name);
END;
$$;

----------------------------------------------

CREATE OR REPLACE FUNCTION select_role_name_from_roles(
    _role_name varchar
)
    RETURNS varchar
    LANGUAGE plpgsql
AS $$
DECLARE
    --
    _result varchar;
BEGIN

    select role_name into _result from roles where role_name = _role_name;

    RETURN _result;
END;
$$;

--------------------------------------------------

CREATE OR REPLACE PROCEDURE import_roles_data_default ()
    LANGUAGE plpgsql
AS $$
DECLARE
    _version integer;
    _role_fetching varchar;
    _role_name varchar;
BEGIN

    select split_part(version(), ' ', 2)::FLOAT::INTEGER INTO _version;

    if _version < 13 then
        execute 'create extension if not exists pgcrypto';
    end if;

    _role_fetching := 'ADMIN';

    _role_name := select_role_name_from_roles(_role_fetching);

    if _role_name is null then
        call insert_into_roles(_role_fetching);
    end if;

    _role_fetching := 'USER';

    _role_name := select_role_name_from_roles(_role_fetching);

    if _role_name is null then
        call insert_into_roles(_role_fetching);
    end if;

    _role_fetching := 'FOUNDER';

    _role_name := select_role_name_from_roles(_role_fetching);

    if _role_name is null then
        call insert_into_roles(_role_fetching);
    end if;

END;
$$;

call import_roles_data_default();