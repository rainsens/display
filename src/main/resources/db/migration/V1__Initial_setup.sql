create table user (
    id bigint auto_increment primary key,
    email varchar(128) not null,
    username varchar(128) not null,
    password varchar(128) not null,
    avatar varchar(256),
    title varchar(128),
    address varchar(500),
    social text,
    interest text,
    credit text,
    admin tinyint default 0 comment '0:false,1:true',
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table project (
    id bigint auto_increment primary key,
    user_id bigint default 0,
    initiator varchar(128) not null,
    title varchar(128) not null,
    description varchar(256) not null,
    cover varchar(256),
    detail longtext not null,
    team tinyint default 1 comment '1:true,0:false',
    progress int default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table member (
    id bigint auto_increment primary key,
    user_id bigint default 0,
    project_id bigint default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table slide (
    id bigint auto_increment primary key,
    project_id bigint default 0,
    path varchar(256) not null,
    title varchar(128) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table image (
       id bigint auto_increment primary key,
       project_id bigint default 0,
       title varchar(128) not null,
       path varchar(256) not null,
       created_at timestamp default current_timestamp,
       updated_at timestamp default current_timestamp
);

create table timeline (
    id bigint auto_increment primary key,
    project_id bigint default 0,
    time timestamp default current_timestamp,
    event varchar(128) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table link (
    id bigint auto_increment primary key,
    project_id bigint default 0,
    title varchar(128) not null,
    link varchar(256) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table material (
    id bigint auto_increment primary key,
    project_id bigint default 0,
    title varchar(128) not null,
    path varchar(256) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table comment (
    id bigint auto_increment primary key,
    user_id bigint default 0,
    project_id bigint default 0,
    content varchar(256) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table permit (
    id bigint auto_increment primary key,
    user_id bigint default 0 comment 'have permssion to see corresponding project',
    project_id bigint default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table survey (
    id bigint auto_increment primary key,
    user_id bigint default 0 comment 'who created the questionnaire',
    title varchar(256) not null,
    open tinyint default 1 comment '1:true,0:false',
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table question (
    id bigint auto_increment primary key,
    survey_id bigint default 0,
    question varchar(256) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table answer (
    id bigint auto_increment primary key,
    user_id bigint default 0,
    survey_id bigint default 0,
    question_id bigint default 0,
    answer varchar(512) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);