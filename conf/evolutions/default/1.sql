# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table admin_model (
  id                            bigint auto_increment not null,
  admin_name                    bigint not null,
  password                      varchar(255),
  time_stamp                    timestamp not null,
  constraint uq_admin_model_admin_name unique (admin_name),
  constraint pk_admin_model primary key (id)
);

create table booking_model (
  id                            bigint auto_increment not null,
  user_model_id                 bigint,
  outlet_model_id               bigint,
  outlet_name                   varchar(255),
  outlet_intro                  varchar(255),
  qq_no                         varchar(255),
  phone                         varchar(255),
  status                        varchar(255),
  time_stamp                    timestamp not null,
  constraint pk_booking_model primary key (id)
);

create table outlet_model (
  id                            bigint auto_increment not null,
  outlet_name                   varchar(255),
  available                     boolean default false not null,
  time_stamp                    timestamp not null,
  constraint uq_outlet_model_outlet_name unique (outlet_name),
  constraint pk_outlet_model primary key (id)
);

create table user_model (
  id                            bigint auto_increment not null,
  matric_no                     bigint not null,
  school                        varchar(255),
  name                          varchar(255),
  phone                         varchar(255),
  qq_no                         bigint not null,
  password                      varchar(255),
  role                          varchar(255),
  time_stamp                    timestamp not null,
  constraint uq_user_model_matric_no unique (matric_no),
  constraint pk_user_model primary key (id)
);

create index ix_booking_model_user_model_id on booking_model (user_model_id);
alter table booking_model add constraint fk_booking_model_user_model_id foreign key (user_model_id) references user_model (id) on delete restrict on update restrict;

create index ix_booking_model_outlet_model_id on booking_model (outlet_model_id);
alter table booking_model add constraint fk_booking_model_outlet_model_id foreign key (outlet_model_id) references outlet_model (id) on delete restrict on update restrict;


# --- !Downs

alter table booking_model drop constraint if exists fk_booking_model_user_model_id;
drop index if exists ix_booking_model_user_model_id;

alter table booking_model drop constraint if exists fk_booking_model_outlet_model_id;
drop index if exists ix_booking_model_outlet_model_id;

drop table if exists admin_model;

drop table if exists booking_model;

drop table if exists outlet_model;

drop table if exists user_model;

