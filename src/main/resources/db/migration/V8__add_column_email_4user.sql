alter table users
    add column if not exists
    email varchar(255);

update users u
set email = 'admin@travel.app'
where u.role_id = (
    select r.id from roles r where r.role_name = 'SUPER_ADMIN'
    );

alter table users
alter column email set not null,
      add constraint unique_email__users unique(email);