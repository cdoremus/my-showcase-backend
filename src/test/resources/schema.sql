drop table app_user if exists;
create table app_user (
    user_id bigint  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    login_id varchar NOT NULL,
    first_name varchar,
    last_name varchar,
    email varchar,
    create_date timestamp DEFAULT CURRENT_TIMESTAMP
);

drop table display_item if exists;
create table display_item (
    item_id bigint  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    user_id bigint,
    title varchar,
    description varchar,
    filename varchar,
    height int,
    width int,
    is_visible boolean,
    create_date timestamp DEFAULT CURRENT_TIMESTAMP
);

