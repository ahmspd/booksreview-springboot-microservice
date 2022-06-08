CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--database name : book-data-service

create table t_genre (
	id varchar(36) default uuid_generate_v4 (),
	genre_code varchar(5) not null,
	genre_name varchar(100) not null,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
alter table t_genre add constraint genre_pk primary key(id);
alter table t_genre add constraint genre_bk unique(genre_code);

create table t_book (
	id varchar(36) default uuid_generate_v4 (),
	title varchar(100) not null,
	year_published int,
	author varchar(100) not null,
	publisher varchar(100) null,
	synopsis text,
	isbn varchar(100) null,
	num_pages int null,
	book_language varchar(100) null,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
alter table t_book add constraint book_pk primary key(id);
alter table t_book add constraint book_isbn_bk unique(isbn);

create table t_genre_book (
	id varchar(36) default uuid_generate_v4 (),
	id_book varchar(36) not null,
	id_genre varchar(36) not null,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
alter table t_genre_book add constraint genre_book_pk primary key(id);
alter table t_genre_book add constraint genre_book_book_fk foreign key(id_book) references t_book(id);
alter table t_genre_book add constraint genre_book_genre_fk foreign key(id_genre) references t_genre(id);
alter table t_genre_book add constraint genre_book_ck unique(id_book, id_genre);

select * from t_genre_book tgb ;