
create table if not exists provinces_location(
    id varchar(255) primary key not null,
    name varchar(255),
    name_en varchar(255),
    full_name varchar(255),
    full_name_en varchar(255),
    code_name varchar(255)
);

create table if not exists districts_location(
    id varchar(255) primary key not null,
    name varchar(255),
    name_en varchar(255),
    full_name varchar(255),
    full_name_en varchar(255),
    code_name varchar(255),
    province_location_id varchar(255) not null,
    constraint fk_province_location_id__districts_location foreign key(province_location_id) references provinces_location(id)
);

create table if not exists wards_location(
    id varchar(255) primary key not null,
    name varchar(255),
    name_en varchar(255),
    full_name varchar(255),
    full_name_en varchar(255),
    code_name varchar(255),
    district_location_id varchar(255) not null,
    constraint fk_district_location_id__wards_location foreign key(district_location_id) references districts_location(id)
);

create table if not exists roles(
    id uuid primary key,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    role_name varchar(255)
);

create table if not exists users(
    id uuid primary key,
    username varchar(255) not null unique ,
    password varchar(255) not null,
    is_enabled boolean default true,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    role_id uuid not null,
    constraint fk_role_id__users foreign key(role_id) references roles(id)
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
    constraint fk_ward_location_id__founder_tours foreign key(ward_location_id) references wards_location(id),
    constraint fk_user_id__founder_tours foreign key(user_id) references users(id)
);

create table if not exists places(
    id uuid primary key,
    place_name varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    is_foreign boolean
);

create table if not exists tag_tours(
    id uuid primary key,
    tag_name varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
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
    booking_tickets integer default 0,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tag_tour_id uuid,
    founder_tour_id uuid not null,
    place_depart_id uuid not null,
    place_travel_id uuid not null,
    constraint fk_tag_tour_id__tours foreign key(tag_tour_id) references tag_tours(id),
    constraint fk_founder_tour_id__tours foreign key(founder_tour_id) references founder_tours(id),
    constraint fk_place_depart_id__tours foreign key(place_depart_id) references places(id),
    constraint fk_place_travel_id__tours foreign key(place_travel_id) references places(id)
);

create table if not exists tour_gallery(
    id uuid primary key,
    image_url text,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_id uuid not null,
    constraint fk_tour_id__tour_gallery foreign key (tour_id) references tours(id)
);

create table if not exists tour_information_notice(
    id uuid primary key,
    notice_name varchar(255),
    information_html text,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_id uuid not null,
    constraint fk_tour_id__tour_information_notice foreign key (tour_id) references tours(id)
);

create table if not exists tour_packs(
    id uuid primary key,
    pack_name varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    is_enabled boolean
);

create table if not exists packs_of_tour(
    id uuid primary key,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_id uuid not null,
    tour_pack_id uuid not null,
    constraint fk_tour_id_packs__of_tour foreign key (tour_id) references tours(id),
    constraint fk_tour_pack_id__packs_of_tour foreign key (tour_pack_id) references tour_packs(id)
);

create table if not exists place_travels(
    id uuid primary key,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_id uuid not null,
    place_id uuid not null,
    constraint fk_tour_id__place_travels foreign key (tour_id) references tours(id),
    constraint fk_place_id__place_travels foreign key (place_id) references places(id)
);

create table if not exists tour_timeline(
    id uuid primary key,
    tour_name varchar(255),
    tour_day integer,
    image_url text,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_id uuid not null,
    constraint fk_tour_id__tour_timeline foreign key (tour_id) references tours(id)
);

create table if not exists tour_timeline_overview(
    id uuid primary key,
    overview_html text,
    image_url text,
    image_name varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_timeline_id uuid not null,
    constraint fk_tour_timeline_id__tour_timeline_overview foreign key (tour_timeline_id) references tour_timeline(id)
);

create table if not exists hotels(
    id uuid primary key,
    hotel_name varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    money numeric(12, 2)
);

create table if not exists schedules_tour(
    id uuid primary key,
    start_date timestamp with time zone,
    end_date timestamp with time zone,
    remaining_tickets integer,
    tickets_status boolean,
    money numeric(12, 2),
    money_type varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    tour_id uuid not null,
    hotel_id uuid,
    constraint fk_tour_id__schedules_tour foreign key (tour_id) references tours(id),
    constraint fk_hotel_id__schedules_tour foreign key (hotel_id) references hotels(id)
);

create table if not exists bookings_tour(
    id uuid primary key,
    tickets_number integer,
    money_tour numeric(15, 2),
    money_tour_type varchar(255),
    booking_status boolean,
    tour_image_url text,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    schedules_tour_id uuid not null,
    user_id uuid not null,
    constraint fk_schedules_tour_id__bookings_tour foreign key (schedules_tour_id) references schedules_tour(id),
    constraint fk_user_id__bookings_tour foreign key (user_id) references users(id)
);

create table if not exists feedbacks(
    id uuid primary key,
    rating float,
    content varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    user_id uuid not null,
    tour_id uuid not null,
    constraint fk_user_id__feedbacks foreign key (user_id) references users(id),
    constraint fk_tour_id__feedbacks foreign key (tour_id) references tours(id)
);

create table if not exists feedback_gallery(
    id uuid primary key,
    image_url text,
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    feedback_id uuid not null,
    constraint fk_feedback_id__feedback_gallery foreign key (feedback_id) references feedbacks(id)
);

create table if not exists comment_feedback(
    id uuid primary key,
    content varchar(255),
    created_at timestamp with time zone,
    updated_at timestamp with time zone,
    feedback_id uuid not null,
    founder_tour_id uuid not null,
    constraint fk_feedback_id__comment_feedback foreign key (feedback_id) references feedbacks(id),
    constraint fk_founder_tour_id__comment_feedback foreign key (founder_tour_id) references founder_tours(id)
);