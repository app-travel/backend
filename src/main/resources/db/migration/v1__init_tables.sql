create table provinces_location(
    id varchar(255) primary key not null,
    name varchar(255),
    name_en varchar(255),
    full_name varchar(255),
    full_name_en varchar(255),
    code_name varchar(255)
);

create table districts_location(
    id varchar(255) primary key not null,
    name varchar(255),
    name_en varchar(255),
    full_name varchar(255),
    full_name_en varchar(255),
    code_name varchar(255),
    province_id varchar(255) not null,
    constraint fk_province_id_districts_location foreign key(province_id) references provinces_location(id)
);

create table wards_location(
    id varchar(255) primary key not null,
    name varchar(255),
    name_en varchar(255),
    full_name varchar(255),
    full_name_en varchar(255),
    code_name varchar(255),
    district_id varchar(255) not null,
    constraint fk_district_id_wards_location foreign key(district_id) references districts_location(id)
);

create table if not exists roles(
    id uuid primary key,
    role_name varchar(255)
);

create table if not exists users(
    id uuid primary key,
    username varchar(255),
    password varchar(255),
    is_enabled boolean,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    role_id uuid not null,
    constraint fk_role_id_users foreign key(role_id) references roles(id)
);

create table if not exists founder_tours(
    id uuid primary key,
    company_name varchar(255),
    email varchar(255),
    phone_number varchar(255),
    enterprise_code varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    address_location varchar(255),
    ward_location_id varchar(255),
    user_id uuid not null,
    constraint fk_ward_location_id_founder_tours foreign key(ward_location_id) references wards_location(id),
    constraint fk_user_id_founder_tours foreign key(user_id) references users(id)
);

create table if not exists places(
    id uuid primary key,
    place_name varchar(255),
    is_foreign boolean
);

create table if not exists tag_tours(
    id uuid primary key,
    tag_name varchar(255),
    tag_type varchar(255)
);

create table if not exists tours(
    id uuid primary key,
    tour_name varchar(255),
    tour_code varchar(255),
    extension_items text,
    rating_avg float,
    tour_experience_html text,
    tour_video_url text,
    tag_tour_id uuid,
    founder_tour_id uuid not null,
    place_depart_id uuid not null,
    place_travel_id uuid not null,
    constraint fk_tag_tour_id_tours foreign key(tag_tour_id) references tag_tours(id),
    constraint fk_founder_tour_id_tours foreign key(founder_tour_id) references founder_tours(id),
    constraint fk_place_depart_id_tours foreign key(place_depart_id) references places(id),
    constraint fk_place_travel_id_tours foreign key(place_depart_id) references places(id)
);