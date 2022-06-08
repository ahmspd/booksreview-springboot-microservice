CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--database name : book-transaction-service

create table t_review(
	id varchar(36) default uuid_generate_v4 (),
	rating integer not null,
	book_comment text null,
	id_book varchar(36) not null,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
alter table t_review add constraint review_pk primary key(id);
alter table t_review add constraint review_ck unique(id_book, created_by);

create table t_bookmark(
	id varchar(36) default uuid_generate_v4 (),
	id_book varchar(36) not null,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
alter table t_bookmark add constraint bookmark_pk primary key(id);
alter table t_bookmark add constraint bookmark_ck unique(id_book, created_by);

