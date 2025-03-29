create table if not exists profile_user(
    id uuid primary key,
    first_name varchar(255),
    last_name varchar(255),
    phone_number varchar(255),
    identify_code varchar(255),
    profile_image text,
    user_id uuid not null,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    constraint fk_user_id__users foreign key (user_id) references users(id)
);