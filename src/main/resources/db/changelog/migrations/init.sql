create table  faq
(
    id serial primary key,
    question text,
    answer text
);

create table groups
(
    id serial primary key,
    name text not null,
    specialization varchar (256)
);

create table teachers
(
    id serial primary key,
    name varchar(256) not null,
    department text,
    room integer
);

create table students
(
    id serial primary key,
    name varchar(64) not null,
    surname varchar (64) not null,
    middlename varchar (64),
    group_id integer not null,
    is_headman boolean,
    email varchar (64),
    addition_info text,

    constraint group_fkey foreign key (group_id)
    references  groups (id)
);

create table subjects
(
    id serial primary key,
    name varchar(32) not null,
    module_start_date timestamp,
    module_end_date timestamp,
    teacher_id integer,

    constraint teacher_fkey foreign key (teacher_id)
    references teachers (id)
);

create table studiying_subjects
(
    student_id integer not null,
    subject_id integer not null,

    constraint students_studiying_fkey foreign key (student_id)
    references students (id),

    constraint studiying_subjects_fkey foreign key (subject_id)
    references subjects (id)
);