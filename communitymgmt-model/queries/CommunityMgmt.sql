--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.2

-- Started on 2025-03-29 07:00:04 UTC

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 6 (class 2615 OID 26915)
-- Name: communitymgmt; Type: SCHEMA; Schema: -; Owner: acollyte
--

CREATE SCHEMA communitymgmt;


ALTER SCHEMA communitymgmt OWNER TO acollyte;

--
-- TOC entry 3421 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA communitymgmt; Type: COMMENT; Schema: -; Owner: acollyte
--

COMMENT ON SCHEMA communitymgmt IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 230 (class 1259 OID 27011)
-- Name: addresses; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.addresses (
    id integer NOT NULL,
    street character varying(255),
    city character varying(100),
    postal_code character varying(5),
    country character varying(100),
    id_person integer NOT NULL
);


ALTER TABLE communitymgmt.addresses OWNER TO acollyte;

--
-- TOC entry 229 (class 1259 OID 27010)
-- Name: addresses_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.addresses ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.addresses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 26916)
-- Name: app_permission; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.app_permission (
    id integer NOT NULL,
    permission character varying NOT NULL
);


ALTER TABLE communitymgmt.app_permission OWNER TO acollyte;

--
-- TOC entry 216 (class 1259 OID 26921)
-- Name: app_permission_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.app_permission ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.app_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 26922)
-- Name: app_role; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.app_role (
    id integer NOT NULL,
    role_name character varying,
    role_parent integer
);


ALTER TABLE communitymgmt.app_role OWNER TO acollyte;

--
-- TOC entry 218 (class 1259 OID 26927)
-- Name: app_role_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.app_role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.app_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 26928)
-- Name: app_role_permission; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.app_role_permission (
    role_id integer NOT NULL,
    permission_id integer NOT NULL
);


ALTER TABLE communitymgmt.app_role_permission OWNER TO acollyte;

--
-- TOC entry 232 (class 1259 OID 27034)
-- Name: charge; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.charge (
    id integer NOT NULL,
    charge_name character varying(100) NOT NULL
);


ALTER TABLE communitymgmt.charge OWNER TO acollyte;

--
-- TOC entry 231 (class 1259 OID 27033)
-- Name: charge_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.charge ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.charge_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 26931)
-- Name: email_account; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.email_account (
    person_id integer,
    email_account character varying(50),
    id integer NOT NULL
);


ALTER TABLE communitymgmt.email_account OWNER TO acollyte;

--
-- TOC entry 221 (class 1259 OID 26934)
-- Name: email_account_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.email_account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.email_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 26935)
-- Name: mobile_number; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.mobile_number (
    person_id integer,
    mobile_number character(11),
    id integer NOT NULL
);


ALTER TABLE communitymgmt.mobile_number OWNER TO acollyte;

--
-- TOC entry 223 (class 1259 OID 26938)
-- Name: mobile_number_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.mobile_number ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.mobile_number_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 224 (class 1259 OID 26939)
-- Name: person; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.person (
    name character varying(10485760),
    id integer NOT NULL,
    user_id character varying(50),
    social_user_id integer,
    apellido1 character varying(200),
    apellido2 character varying(200)
);


ALTER TABLE communitymgmt.person OWNER TO acollyte;

--
-- TOC entry 225 (class 1259 OID 26944)
-- Name: person_app_role; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.person_app_role (
    person_id integer NOT NULL,
    app_role_id integer NOT NULL
);


ALTER TABLE communitymgmt.person_app_role OWNER TO acollyte;

--
-- TOC entry 234 (class 1259 OID 27041)
-- Name: person_charge; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.person_charge (
    id integer NOT NULL,
    person_id integer NOT NULL,
    charge_id integer NOT NULL
);


ALTER TABLE communitymgmt.person_charge OWNER TO acollyte;

--
-- TOC entry 233 (class 1259 OID 27040)
-- Name: person_charge_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.person_charge ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.person_charge_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 26947)
-- Name: person_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.person ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 238 (class 1259 OID 27063)
-- Name: person_relationship; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.person_relationship (
    id integer NOT NULL,
    person_id integer NOT NULL,
    relationship_id integer NOT NULL
);


ALTER TABLE communitymgmt.person_relationship OWNER TO acollyte;

--
-- TOC entry 237 (class 1259 OID 27062)
-- Name: person_relationship_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.person_relationship ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.person_relationship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 236 (class 1259 OID 27057)
-- Name: relationship; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.relationship (
    id integer NOT NULL,
    relationship_name character varying(100) NOT NULL,
    "from" date,
    "to" date
);


ALTER TABLE communitymgmt.relationship OWNER TO acollyte;

--
-- TOC entry 235 (class 1259 OID 27056)
-- Name: relationship_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.relationship ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.relationship_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 227 (class 1259 OID 26948)
-- Name: social_user; Type: TABLE; Schema: communitymgmt; Owner: acollyte
--

CREATE TABLE communitymgmt.social_user (
    id integer NOT NULL,
    name character varying,
    email character varying,
    image_url character varying,
    email_verified boolean,
    provider character varying,
    user_id character varying,
    provider_id character varying
);


ALTER TABLE communitymgmt.social_user OWNER TO acollyte;

--
-- TOC entry 228 (class 1259 OID 26953)
-- Name: user_id_seq; Type: SEQUENCE; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE communitymgmt.social_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME communitymgmt.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3252 (class 2606 OID 27017)
-- Name: addresses addresses_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.addresses
    ADD CONSTRAINT addresses_pk PRIMARY KEY (id);


--
-- TOC entry 3236 (class 2606 OID 26955)
-- Name: app_permission app_permission_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.app_permission
    ADD CONSTRAINT app_permission_pk PRIMARY KEY (id);


--
-- TOC entry 3240 (class 2606 OID 26957)
-- Name: app_role_permission app_role_permission_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.app_role_permission
    ADD CONSTRAINT app_role_permission_pk PRIMARY KEY (permission_id, role_id);


--
-- TOC entry 3238 (class 2606 OID 26959)
-- Name: app_role app_role_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.app_role
    ADD CONSTRAINT app_role_pk PRIMARY KEY (id);


--
-- TOC entry 3254 (class 2606 OID 27038)
-- Name: charge charge_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.charge
    ADD CONSTRAINT charge_pk PRIMARY KEY (id);


--
-- TOC entry 3242 (class 2606 OID 26961)
-- Name: email_account email_account_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.email_account
    ADD CONSTRAINT email_account_pk PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 26963)
-- Name: mobile_number mobile_number_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.mobile_number
    ADD CONSTRAINT mobile_number_pk PRIMARY KEY (id);


--
-- TOC entry 3248 (class 2606 OID 26965)
-- Name: person_app_role person_app_role_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_app_role
    ADD CONSTRAINT person_app_role_pk PRIMARY KEY (person_id, app_role_id);


--
-- TOC entry 3256 (class 2606 OID 27045)
-- Name: person_charge person_charge_id; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_charge
    ADD CONSTRAINT person_charge_id PRIMARY KEY (id);


--
-- TOC entry 3246 (class 2606 OID 26967)
-- Name: person person_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person
    ADD CONSTRAINT person_pk PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 27067)
-- Name: person_relationship person_relationship_pkey; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_relationship
    ADD CONSTRAINT person_relationship_pkey PRIMARY KEY (id);


--
-- TOC entry 3258 (class 2606 OID 27061)
-- Name: relationship relationship_pkey; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.relationship
    ADD CONSTRAINT relationship_pkey PRIMARY KEY (id);


--
-- TOC entry 3250 (class 2606 OID 26969)
-- Name: social_user social_user_pk; Type: CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.social_user
    ADD CONSTRAINT social_user_pk PRIMARY KEY (id);


--
-- TOC entry 3269 (class 2606 OID 27028)
-- Name: addresses address_person_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.addresses
    ADD CONSTRAINT address_person_fk FOREIGN KEY (id_person) REFERENCES communitymgmt.person(id) NOT VALID;


--
-- TOC entry 3270 (class 2606 OID 27051)
-- Name: person_charge charge_id_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_charge
    ADD CONSTRAINT charge_id_fk FOREIGN KEY (charge_id) REFERENCES communitymgmt.charge(id);


--
-- TOC entry 3264 (class 2606 OID 26970)
-- Name: email_account email_person_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.email_account
    ADD CONSTRAINT email_person_fk FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id) NOT VALID;


--
-- TOC entry 3265 (class 2606 OID 26975)
-- Name: mobile_number mobile_person_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.mobile_number
    ADD CONSTRAINT mobile_person_fk FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id) NOT VALID;


--
-- TOC entry 3262 (class 2606 OID 26980)
-- Name: app_role_permission permission_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.app_role_permission
    ADD CONSTRAINT permission_fk FOREIGN KEY (permission_id) REFERENCES communitymgmt.app_permission(id) NOT VALID;


--
-- TOC entry 3267 (class 2606 OID 26985)
-- Name: person_app_role person_has_role_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_app_role
    ADD CONSTRAINT person_has_role_fk FOREIGN KEY (app_role_id) REFERENCES communitymgmt.app_role(id) NOT VALID;


--
-- TOC entry 3271 (class 2606 OID 27046)
-- Name: person_charge person_id_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_charge
    ADD CONSTRAINT person_id_fk FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id);


--
-- TOC entry 3263 (class 2606 OID 26990)
-- Name: app_role_permission role_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.app_role_permission
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES communitymgmt.app_role(id) NOT VALID;


--
-- TOC entry 3268 (class 2606 OID 26995)
-- Name: person_app_role role_has_person_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_app_role
    ADD CONSTRAINT role_has_person_fk FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id) NOT VALID;


--
-- TOC entry 3261 (class 2606 OID 27000)
-- Name: app_role role_parent_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.app_role
    ADD CONSTRAINT role_parent_fk FOREIGN KEY (role_parent) REFERENCES communitymgmt.app_role(id) NOT VALID;


--
-- TOC entry 3272 (class 2606 OID 27068)
-- Name: person_relationship rp_person_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_relationship
    ADD CONSTRAINT rp_person_fk FOREIGN KEY (person_id) REFERENCES communitymgmt.person(id);


--
-- TOC entry 3273 (class 2606 OID 27073)
-- Name: person_relationship rp_relationship_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person_relationship
    ADD CONSTRAINT rp_relationship_fk FOREIGN KEY (relationship_id) REFERENCES communitymgmt.relationship(id);


--
-- TOC entry 3266 (class 2606 OID 27005)
-- Name: person social_user_fk; Type: FK CONSTRAINT; Schema: communitymgmt; Owner: acollyte
--

ALTER TABLE ONLY communitymgmt.person
    ADD CONSTRAINT social_user_fk FOREIGN KEY (social_user_id) REFERENCES communitymgmt.social_user(id) NOT VALID;


-- Completed on 2025-03-29 07:00:04 UTC

--
-- PostgreSQL database dump complete
--

