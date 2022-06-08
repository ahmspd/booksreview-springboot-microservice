CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--database name : user-service

CREATE TABLE t_roles (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	role_code varchar(5) NOT NULL,
	role_name varchar(100) NOT NULL,
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
ALTER TABLE t_roles ADD CONSTRAINT role_pk PRIMARY KEY(id);
ALTER TABLE t_roles ADD CONSTRAINT role_bk UNIQUE(role_code);

CREATE TABLE t_users (
	id varchar(36) DEFAULT uuid_generate_v4 (),
	email varchar(100) NOT NULL,
	"password" varchar(255) NOT NULL,
	role_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
ALTER TABLE t_users ADD CONSTRAINT user_pk PRIMARY KEY(id);
ALTER TABLE t_users ADD CONSTRAINT user_bk UNIQUE(email);
ALTER TABLE t_users ADD CONSTRAINT role_fk FOREIGN KEY(role_id) REFERENCES t_roles(id);

CREATE TABLE t_profile (
	id  varchar(36) DEFAULT uuid_generate_v4 (),
	profile_name varchar(100) NOT NULL,
	profile_phone varchar(20) NOT NULL,
	user_id varchar(36),
	created_by varchar(36) NOT NULL,
	created_at timestamp without time zone NOT NULL,
	updated_by varchar(36),
	updated_at timestamp without time zone,
	"version" int NOT NULL,
	is_active boolean
);
ALTER TABLE t_profile ADD CONSTRAINT profile_pk PRIMARY KEY(id);
ALTER TABLE t_profile ADD CONSTRAINT user_fk FOREIGN KEY(user_id) REFERENCES t_users(id);

INSERT INTO t_roles (role_code, role_name, created_by, created_at, "version", is_active) VALUES 
	('ADM', 'Admin', 1, now(), 0, true),
	('USR', 'User', 1, now(), 0, true);