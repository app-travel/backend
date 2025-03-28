create table if not exists invalid_token(
    id uuid primary key,
    token text,
    invalid_type varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    user_id uuid,
    constraint fk_user_id__invalid_token foreign key (user_id) references users(id)
);