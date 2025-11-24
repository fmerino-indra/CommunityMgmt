-- DROP SCHEMA communitymgmt;

CREATE SCHEMA communitymgmt AUTHORIZATION acollyte;

COMMENT ON SCHEMA communitymgmt IS 'standard public schema';

-- DROP SEQUENCE communitymgmt.community_id_seq;

CREATE SEQUENCE communitymgmt.community_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.email_account_id_seq;

CREATE SEQUENCE communitymgmt.email_account_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.email_account_id_seq1;

CREATE SEQUENCE communitymgmt.email_account_id_seq1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.image_id_seq;

CREATE SEQUENCE communitymgmt.image_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.invitation_id_seq;

CREATE SEQUENCE communitymgmt.invitation_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.invitation_relationship_seq;

CREATE SEQUENCE communitymgmt.invitation_relationship_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.membership_id_seq;

CREATE SEQUENCE communitymgmt.membership_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.mobile_number_id_seq;

CREATE SEQUENCE communitymgmt.mobile_number_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.person_id_seq;

CREATE SEQUENCE communitymgmt.person_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.relationship_seq;

CREATE SEQUENCE communitymgmt.relationship_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.social_user_id_seq;

CREATE SEQUENCE communitymgmt.social_user_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.t_charges_id_seq;

CREATE SEQUENCE communitymgmt.t_charges_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;
-- DROP SEQUENCE communitymgmt.t_membership_id_seq;

CREATE SEQUENCE communitymgmt.t_membership_id_seq
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 2147483647
    START 1
    CACHE 1
    NO CYCLE;-- communitymgmt.address definition

-- Drop table

-- DROP TABLE communitymgmt.address;

CREATE TABLE communitymgmt.address (
    id int4 NOT NULL,
    city varchar(255) NULL,
    cp varchar(255) NULL,
    door varchar(255) NULL,
    floor varchar(255) NULL,
    street_name varchar(255) NULL,
    street_number varchar(255) NULL,
    CONSTRAINT address_pkey PRIMARY KEY (id)
);


-- communitymgmt.app_permission definition

-- Drop table

-- DROP TABLE communitymgmt.app_permission;

CREATE TABLE communitymgmt.app_permission (
    id int4 NOT NULL,
    "permission" varchar(255) NULL,
    CONSTRAINT app_permission_pkey PRIMARY KEY (id)
);


-- communitymgmt.community definition

-- Drop table

-- DROP TABLE communitymgmt.community;

CREATE TABLE communitymgmt.community (
    activated bool NULL,
    id serial4 NOT NULL,
    community_number varchar(255) NULL,
    country varchar(255) NULL,
    parish varchar(255) NULL,
    parish_address varchar(255) NULL,
    parish_address_city varchar(255) NULL,
    parish_address_number varchar(255) NULL,
    parish_address_postal_code varchar(255) NULL,
    CONSTRAINT community_pkey PRIMARY KEY (id)
);


-- communitymgmt.email_account_fk definition

-- Drop table

-- DROP TABLE communitymgmt.email_account_fk;

CREATE TABLE communitymgmt.email_account_fk (
    id int4 DEFAULT nextval('email_account_id_seq'::regclass) NOT NULL,
    email_account varchar(255) NULL,
    person_id int4 NULL,
    CONSTRAINT email_account_pkey PRIMARY KEY (id)
);


-- communitymgmt.image definition

-- Drop table

-- DROP TABLE communitymgmt.image;

CREATE TABLE communitymgmt.image (
    id serial4 NOT NULL,
    mime_type varchar(255) NULL,
    photo_small bytea NOT NULL,
    photo_tiny bytea NOT NULL,
    CONSTRAINT image_pkey PRIMARY KEY (id)
);


-- communitymgmt.service_type definition

-- Drop table

-- DROP TABLE communitymgmt.service_type;

CREATE TABLE communitymgmt.service_type (
    id int4 NOT NULL,
    service_type varchar(255) NULL,
    CONSTRAINT service_type_pkey PRIMARY KEY (id)
);


-- communitymgmt.stage definition

-- Drop table

-- DROP TABLE communitymgmt.stage;

CREATE TABLE communitymgmt.stage (
    id int4 NOT NULL,
    stage varchar(255) NULL,
    feature varchar(255) NULL,
    CONSTRAINT stage_pkey PRIMARY KEY (id)
);


-- communitymgmt.t_charges definition

-- Drop table

-- DROP TABLE communitymgmt.t_charges;

CREATE TABLE communitymgmt.t_charges (
    id serial4 NOT NULL,
    charge varchar(255) NULL,
    CONSTRAINT t_charges_pkey PRIMARY KEY (id)
);


-- communitymgmt.t_membership definition

-- Drop table

-- DROP TABLE communitymgmt.t_membership;

CREATE TABLE communitymgmt.t_membership (
    id serial4 NOT NULL,
    same_time bool NULL,
    membership varchar(255) NULL,
    CONSTRAINT t_membership_pkey PRIMARY KEY (id)
);


-- communitymgmt.t_tripod definition

-- Drop table

-- DROP TABLE communitymgmt.t_tripod;

CREATE TABLE communitymgmt.t_tripod (
    id int4 NOT NULL,
    frequency int4 NULL,
    tripod_name varchar(255) NULL,
    CONSTRAINT t_tripod_pkey PRIMARY KEY (id)
);


-- communitymgmt.app_role definition

-- Drop table

-- DROP TABLE communitymgmt.app_role;

CREATE TABLE communitymgmt.app_role (
    id int4 NOT NULL,
    role_parent int4 NULL,
    role_name varchar(255) NULL,
    CONSTRAINT app_role_pkey PRIMARY KEY (id),
    CONSTRAINT fkgudn3jyaly4d0f1tyj2mr7c55 FOREIGN KEY (role_parent) REFERENCES communitymgmt.app_role(id)
);


-- communitymgmt.app_role_permission definition

-- Drop table

-- DROP TABLE communitymgmt.app_role_permission;

CREATE TABLE communitymgmt.app_role_permission (
    permission_id int4 NOT NULL,
    role_id int4 NOT NULL,
    CONSTRAINT fk7mxlaa4assrcd5k3vfxgl3i5j FOREIGN KEY (role_id) REFERENCES communitymgmt.app_role(id),
    CONSTRAINT fkko58fow4h0kc4mhnlvchavkhu FOREIGN KEY (permission_id) REFERENCES communitymgmt.app_permission(id)
);


-- communitymgmt.community_settings definition

-- Drop table

-- DROP TABLE communitymgmt.community_settings;

CREATE TABLE communitymgmt.community_settings (
    word_day int2 NOT NULL,
    convivence_day int2 NOT NULL,
    brothers int2 NOT NULL,
    brother_group int2 NOT NULL,
    community_id int4 NOT NULL,
    CONSTRAINT id_pk PRIMARY KEY (community_id),
    CONSTRAINT community_fk FOREIGN KEY (community_id) REFERENCES communitymgmt.community(id)
);


-- communitymgmt.invitation_relationship definition

-- Drop table

-- DROP TABLE communitymgmt.invitation_relationship;

CREATE TABLE communitymgmt.invitation_relationship (
    address_id int4 NULL,
    id int4 NOT NULL,
    order_list int4 NULL,
    relationship_name varchar(255) NULL,
    CONSTRAINT invitation_relationship_pkey PRIMARY KEY (id),
    CONSTRAINT fk7l086e6vo7e1bsh7nyoh6itsv FOREIGN KEY (address_id) REFERENCES communitymgmt.address(id)
);


-- communitymgmt.person definition

-- Drop table

-- DROP TABLE communitymgmt.person;

CREATE TABLE communitymgmt.person (
    gender bpchar(1) NOT NULL,
    id serial4 NOT NULL,
    image_id int4 NULL,
    married bool NOT NULL,
    passing_date date NULL,
    birthday int8 NULL,
    person_type varchar(31) NOT NULL,
    "name" varchar(255) NULL,
    nickname varchar(255) NULL,
    surname1 varchar(255) NULL,
    surname2 varchar(255) NULL,
    CONSTRAINT person_gender_check CHECK ((gender = ANY (ARRAY['M'::bpchar, 'F'::bpchar]))),
    CONSTRAINT person_pkey PRIMARY KEY (id),
    CONSTRAINT fkrd2qmgc0f3txfheux3f5qeqgv FOREIGN KEY (image_id) REFERENCES communitymgmt.image(id)
);


-- communitymgmt.person_app_role definition

-- Drop table

-- DROP TABLE communitymgmt.person_app_role;

CREATE TABLE communitymgmt.person_app_role (
    app_role_id int4 NOT NULL,
    person_id int4 NOT NULL,
    CONSTRAINT fkg4lyrte38h0m7ltnsrdufc2tv FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkp24a8bybdfvxcclqlxygnv39e FOREIGN KEY (app_role_id) REFERENCES communitymgmt.app_role(id)
);


-- communitymgmt.relationship definition

-- Drop table

-- DROP TABLE communitymgmt.relationship;

CREATE TABLE communitymgmt.relationship (
    address_id int4 NULL,
    id int4 NOT NULL,
    order_list int4 NOT NULL,
    relationship_name varchar(255) NULL,
    CONSTRAINT relationship_pkey PRIMARY KEY (id),
    CONSTRAINT fkrgl3fubpo0uu1i53fab41gbth FOREIGN KEY (address_id) REFERENCES communitymgmt.address(id)
);


-- communitymgmt.social_user definition

-- Drop table

-- DROP TABLE communitymgmt.social_user;

CREATE TABLE communitymgmt.social_user (
    email_verified bool NULL,
    id serial4 NOT NULL,
    person_id int4 NOT NULL,
    email varchar(255) NULL,
    image_url varchar(255) NULL,
    iss varchar(255) NULL,
    "name" varchar(255) NULL,
    provider varchar(255) NULL,
    provider_id varchar(255) NULL,
    upn varchar(255) NULL,
    user_id varchar(255) NULL,
    CONSTRAINT social_user_pkey PRIMARY KEY (id),
    CONSTRAINT fkedwltg04qikracvkgoqnxailn FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id)
);


-- communitymgmt.step definition

-- Drop table

-- DROP TABLE communitymgmt.step;

CREATE TABLE communitymgmt.step (
    id int4 NOT NULL,
    step varchar(255) NULL,
    stage_id int4 NOT NULL,
    CONSTRAINT step_pkey PRIMARY KEY (id),
    CONSTRAINT fkiecpoxr1bcwnsaqjnaripkwxl FOREIGN KEY (stage_id) REFERENCES communitymgmt.stage(id)
);


-- communitymgmt.celebration_cycle_template definition

-- Drop table

-- DROP TABLE communitymgmt.celebration_cycle_template;

CREATE TABLE communitymgmt.celebration_cycle_template (
    id int4 NOT NULL,
    "number" int4 NULL,
    team_neaded bool NULL,
    "name" varchar(255) NULL,
    step_id int4 NULL,
    CONSTRAINT celebration_cycle_template_pkey PRIMARY KEY (id),
    CONSTRAINT fkdbtkcoe9ncv5325hmtnlheea8 FOREIGN KEY (step_id) REFERENCES communitymgmt.step(id)
);


-- communitymgmt.celebration_event_template definition

-- Drop table

-- DROP TABLE communitymgmt.celebration_event_template;

CREATE TABLE communitymgmt.celebration_event_template (
    celebration_order int4 NULL,
    cycle_id int4 NOT NULL,
    id int4 NOT NULL,
    team_neaded bool NULL,
    "name" varchar(255) NULL,
    CONSTRAINT celebration_event_template_pkey PRIMARY KEY (id),
    CONSTRAINT fkdwc6pd16pwytdn1my4hreuc2m FOREIGN KEY (cycle_id) REFERENCES communitymgmt.celebration_cycle_template(id)
);


-- communitymgmt.email_account definition

-- Drop table

-- DROP TABLE communitymgmt.email_account;

CREATE TABLE communitymgmt.email_account (
    id serial4 NOT NULL,
    person_id int4 NOT NULL,
    email_account varchar(255) NULL,
    CONSTRAINT email_account_pkey1 PRIMARY KEY (id),
    CONSTRAINT fkgghgflgpm40klea9owddachtr FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id)
);


-- communitymgmt.invitation definition

-- Drop table

-- DROP TABLE communitymgmt.invitation;

CREATE TABLE communitymgmt.invitation (
    community_id int4 NULL,
    for_marriage bool NOT NULL,
    id serial4 NOT NULL,
    invitation_relationship_id int4 NULL,
    invitation_type int4 NULL,
    person_id int4 NULL,
    state bpchar(1) NOT NULL,
    "exp" int8 NULL,
    iat int8 NULL,
    nbf int8 NULL,
    "name" varchar(255) NULL,
    person_public_key varchar(255) NULL,
    person_signature varchar(255) NULL,
    responsible_public_key varchar(255) NULL,
    responsible_signature varchar(255) NULL,
    CONSTRAINT invitation_pkey PRIMARY KEY (id),
    CONSTRAINT invitation_state_check CHECK ((state = ANY (ARRAY['G'::bpchar, 'P'::bpchar, 'F'::bpchar]))),
    CONSTRAINT fk1p5erdumkmrflrcm970bsbw5m FOREIGN KEY (community_id) REFERENCES communitymgmt.community(id),
    CONSTRAINT fkew8wyihyhpdsq75mtyb8geb8m FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkiorcy9koum1jqlxsfkbfev5ja FOREIGN KEY (invitation_relationship_id) REFERENCES communitymgmt.invitation_relationship(id),
    CONSTRAINT fkqjnqk8vi2ojw1r47u5itqb38l FOREIGN KEY (invitation_type) REFERENCES communitymgmt.t_membership(id)
);


-- communitymgmt.invitation_r_marriage definition

-- Drop table

-- DROP TABLE communitymgmt.invitation_r_marriage;

CREATE TABLE communitymgmt.invitation_r_marriage (
    "date" date NULL,
    husband_id int4 NULL,
    id int4 NOT NULL,
    wife_id int4 NULL,
    description varchar(255) NULL,
    CONSTRAINT invitation_r_marriage_pkey PRIMARY KEY (id),
    CONSTRAINT fk3bs1pwlm2fecrda8ueyq66dim FOREIGN KEY (husband_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkaha25dvhkuntbcta1sk1gslht FOREIGN KEY (wife_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkdxcjqu4tevbto2g2uk40k91sq FOREIGN KEY (id) REFERENCES communitymgmt.invitation_relationship(id)
);


-- communitymgmt.invitation_r_single definition

-- Drop table

-- DROP TABLE communitymgmt.invitation_r_single;

CREATE TABLE communitymgmt.invitation_r_single (
    id int4 NOT NULL,
    person_id int4 NULL,
    CONSTRAINT invitation_r_single_pkey PRIMARY KEY (id),
    CONSTRAINT fkg4op26vnh0ua7q2ndl4w8qxbo FOREIGN KEY (id) REFERENCES communitymgmt.invitation_relationship(id),
    CONSTRAINT fkgokl3t967or2gso0lrnv3ejqk FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id)
);


-- communitymgmt.membership definition

-- Drop table

-- DROP TABLE communitymgmt.membership;

CREATE TABLE communitymgmt.membership (
    community_id int4 NULL,
    id serial4 NOT NULL,
    order_list int4 NULL,
    person_id int4 NULL,
    relationship_id int4 NULL,
    type_id int4 NULL,
    CONSTRAINT membership_pkey PRIMARY KEY (id),
    CONSTRAINT fk4a4o7fx3xersm0wlav1d0hmqm FOREIGN KEY (community_id) REFERENCES communitymgmt.community(id),
    CONSTRAINT fk4ey9rm4dbiliosjiioqy1r4e1 FOREIGN KEY (relationship_id) REFERENCES communitymgmt.relationship(id),
    CONSTRAINT fk8xshuq9mbdid8xk8ppcqa16dj FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkpe5vsd3u36tgg5w7pq4y5itw8 FOREIGN KEY (type_id) REFERENCES communitymgmt.t_membership(id)
);


-- communitymgmt.mobile_number definition

-- Drop table

-- DROP TABLE communitymgmt.mobile_number;

CREATE TABLE communitymgmt.mobile_number (
    id serial4 NOT NULL,
    person_id int4 NOT NULL,
    mobile_number varchar(255) NULL,
    CONSTRAINT mobile_number_pkey PRIMARY KEY (id),
    CONSTRAINT fkle22sm5xuim6vc2xv6smb0rd4 FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id)
);


-- communitymgmt.r_marriage definition

-- Drop table

-- DROP TABLE communitymgmt.r_marriage;

CREATE TABLE communitymgmt.r_marriage (
    "date" date NULL,
    husband_id int4 NULL,
    id int4 NOT NULL,
    wife_id int4 NULL,
    description varchar(255) NULL,
    CONSTRAINT r_marriage_pkey PRIMARY KEY (id),
    CONSTRAINT fkgxijseppmyffwov3he1vu5f7l FOREIGN KEY (wife_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkj3jde8xtmb9ksafcgd3bpi00n FOREIGN KEY (id) REFERENCES communitymgmt.relationship(id),
    CONSTRAINT fkldudn7aixcymitvpp8v5vumqv FOREIGN KEY (husband_id) REFERENCES communitymgmt.person(id)
);


-- communitymgmt.r_others definition

-- Drop table

-- DROP TABLE communitymgmt.r_others;

CREATE TABLE communitymgmt.r_others (
    count int4 NULL,
    id int4 NOT NULL,
    description varchar(255) NULL,
    CONSTRAINT r_others_pkey PRIMARY KEY (id),
    CONSTRAINT fktcliadruondjo5nji61h0a0tu FOREIGN KEY (id) REFERENCES communitymgmt.relationship(id)
);


-- communitymgmt.r_others_persons definition

-- Drop table

-- DROP TABLE communitymgmt.r_others_persons;

CREATE TABLE communitymgmt.r_others_persons (
    r_others_id int4 NOT NULL,
    related_persons_id int4 NOT NULL,
    CONSTRAINT fk4okbv2w4r7v69mt5ekocphung FOREIGN KEY (related_persons_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkt5q0xoipxq4od20r9wjjwceb9 FOREIGN KEY (r_others_id) REFERENCES communitymgmt.r_others(id)
);


-- communitymgmt.r_single definition

-- Drop table

-- DROP TABLE communitymgmt.r_single;

CREATE TABLE communitymgmt.r_single (
    id int4 NOT NULL,
    person_id int4 NULL,
    CONSTRAINT r_single_pkey PRIMARY KEY (id),
    CONSTRAINT fk3srpph0jybholnuux6apa8er0 FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id),
    CONSTRAINT fkmqyjnrvbspdelj1vppxe8uis7 FOREIGN KEY (id) REFERENCES communitymgmt.relationship(id)
);


-- communitymgmt.community_charges definition

-- Drop table

-- DROP TABLE communitymgmt.community_charges;

CREATE TABLE communitymgmt.community_charges (
    charge_type_id int4 NOT NULL,
    membership_id int4 NOT NULL,
    CONSTRAINT fkaohddx1i4roe5n7759n84p1dy FOREIGN KEY (membership_id) REFERENCES communitymgmt.membership(id),
    CONSTRAINT fki0d3xv0s70o4ao2rb9nl9vsfb FOREIGN KEY (charge_type_id) REFERENCES communitymgmt.t_charges(id)
);