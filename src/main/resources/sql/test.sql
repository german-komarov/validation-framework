create table departments
                         (
                             id   serial  not null
                                 constraint roles_pk
                                     primary key,
                             name varchar not null
                         );

alter table departments
    owner to postgres;

create unique index departments_name_uindex
    on departments(name);


create table employees
(
    id    serial  not null
        constraint users_pk
            primary key,
    email varchar not null,
    department varchar not null,
    nickname varchar not null,
    foreign key (department) references departments(name)
);

alter table employees
    owner to postgres;

create unique index employees_email_uindex
    on employees (email);





insert into departments(name) values ('IT');
insert into departments(name) values ('HR');
insert into employees (email,nickname, department) values ('test1@example.com', 'foo', 'IT');
insert into employees (email, nickname, department) values ('test2@example.com', 'bar', 'HR');