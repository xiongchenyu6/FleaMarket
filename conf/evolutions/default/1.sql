# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table booking (
  id                            bigint auto_increment not null,
  user_model_id                 bigint,
  outlet_model_id               bigint,
  outlet_name                   varchar(255),
  outlet_intro                  varchar(255),
  qq_no                         varchar(255),
  phone                         varchar(255),
  status                        varchar(255),
  time_stamp                    datetime(6) not null,
  constraint pk_booking primary key (id)
);

create table outlet (
  id                            bigint auto_increment not null,
  outlet_name                   varchar(255),
  available                     tinyint(1) default 0 not null,
  time_stamp                    datetime(6) not null,
  constraint uq_outlet_outlet_name unique (outlet_name),
  constraint pk_outlet primary key (id)
);

create table settings (
  id                            varchar(255) not null,
  value                         varchar(255),
  constraint pk_settings primary key (id)
);

create table user (
  id                            bigint auto_increment not null,
  matric_no                     varchar(255),
  school                        varchar(255),
  name                          varchar(255),
  phone                         varchar(255),
  qq_no                         bigint not null,
  password                      varchar(255),
  role                          varchar(255),
  time_stamp                    datetime(6) not null,
  constraint uq_user_matric_no unique (matric_no),
  constraint pk_user primary key (id)
);

create index ix_booking_user_model_id on booking (user_model_id);
alter table booking add constraint fk_booking_user_model_id foreign key (user_model_id) references user (id) on delete restrict on update restrict;

create index ix_booking_outlet_model_id on booking (outlet_model_id);
alter table booking add constraint fk_booking_outlet_model_id foreign key (outlet_model_id) references outlet (id) on delete restrict on update restrict;


# --- !Downs

alter table booking drop foreign key fk_booking_user_model_id;
drop index ix_booking_user_model_id on booking;

alter table booking drop foreign key fk_booking_outlet_model_id;
drop index ix_booking_outlet_model_id on booking;

drop table if exists booking;

drop table if exists outlet;

drop table if exists settings;

drop table if exists user;

