create table if not exists tokens(
    id uuid primary key,
    token text,
    token_type varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    user_id uuid,
    constraint fk_user_id__session_token foreign key (user_id) references users(id)
);