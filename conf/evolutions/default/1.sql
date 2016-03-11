# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table git_commit (
  id                        bigserial not null,
  git_notification_id       bigint not null,
  commit_id                 varchar(255),
  message                   varchar(255),
  commit_url                varchar(255),
  committed_at              timestamp,
  committer_name            varchar(255),
  email                     varchar(255),
  user_name                 varchar(255),
  constraint pk_git_commit primary key (id))
;

create table git_notification (
  id                        bigserial not null,
  notification_title        varchar(255),
  repository                varchar(255),
  repository_branch         varchar(255),
  no_of_commits             integer,
  committed_by              varchar(255),
  pusher_email              varchar(255),
  constraint pk_git_notification primary key (id))
;

create table india_details (
  id                        bigserial not null,
  state                     varchar(255),
  district                  varchar(255),
  city                      varchar(255),
  locality                  varchar(255),
  constraint pk_india_details primary key (id))
;

alter table git_commit add constraint fk_git_commit_git_notification_1 foreign key (git_notification_id) references git_notification (id);
create index ix_git_commit_git_notification_1 on git_commit (git_notification_id);



# --- !Downs

drop table if exists git_commit cascade;

drop table if exists git_notification cascade;

drop table if exists india_details cascade;

