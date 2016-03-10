# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table india_details (
  id                        bigserial not null,
  state                     varchar(255),
  district                  varchar(255),
  city                      varchar(255),
  locality                  varchar(255),
  constraint pk_india_details primary key (id))
;




# --- !Downs

drop table if exists india_details cascade;

