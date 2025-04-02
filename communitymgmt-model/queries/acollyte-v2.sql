--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2 (Debian 15.2-1.pgdg110+1)
-- Dumped by pg_dump version 15.2

-- Started on 2025-03-28 14:56:33 UTC

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: acollyte; Type: SCHEMA; Schema: -; Owner: pg_database_owner
--

CREATE SCHEMA acollyte;


ALTER SCHEMA acollyte OWNER TO pg_database_owner;

--
-- TOC entry 3482 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA acollyte; Type: COMMENT; Schema: -; Owner: pg_database_owner
--

COMMENT ON SCHEMA acollyte IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 229 (class 1259 OID 17822)
-- Name: absence; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.absence (
    id integer NOT NULL,
    day date,
    person_id integer
);


ALTER TABLE acollyte.absence OWNER TO acollyte;

--
-- TOC entry 230 (class 1259 OID 17827)
-- Name: absence_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.absence ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.absence_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 238 (class 1259 OID 17888)
-- Name: app_permission; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.app_permission (
    id integer NOT NULL,
    permission character varying NOT NULL
);


ALTER TABLE acollyte.app_permission OWNER TO acollyte;

--
-- TOC entry 242 (class 1259 OID 17930)
-- Name: app_permission_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.app_permission ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.app_permission_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 240 (class 1259 OID 17900)
-- Name: app_role; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.app_role (
    id integer NOT NULL,
    role_name character varying,
    role_parent integer
);


ALTER TABLE acollyte.app_role OWNER TO acollyte;

--
-- TOC entry 243 (class 1259 OID 17931)
-- Name: app_role_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.app_role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.app_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 241 (class 1259 OID 17912)
-- Name: app_role_permission; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.app_role_permission (
    role_id integer NOT NULL,
    permission_id integer NOT NULL
);


ALTER TABLE acollyte.app_role_permission OWNER TO acollyte;

--
-- TOC entry 234 (class 1259 OID 17859)
-- Name: candidates_raffle; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.candidates_raffle (
    id integer NOT NULL,
    person_id integer NOT NULL,
    raffle_id integer NOT NULL,
    n_assigned integer,
    n_reserved integer,
    survive_assigned boolean,
    survive_reserve boolean
);


ALTER TABLE acollyte.candidates_raffle OWNER TO acollyte;

--
-- TOC entry 235 (class 1259 OID 17864)
-- Name: candidates_raffle_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.candidates_raffle ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.candidates_raffle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 17721)
-- Name: email_account; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.email_account (
    person_id integer,
    email_account character varying(50),
    id integer NOT NULL
);


ALTER TABLE acollyte.email_account OWNER TO acollyte;

--
-- TOC entry 224 (class 1259 OID 17757)
-- Name: email_account_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.email_account ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.email_account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 218 (class 1259 OID 17730)
-- Name: exception; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.exception (
    id integer,
    person_id integer,
    day date
);


ALTER TABLE acollyte.exception OWNER TO acollyte;

--
-- TOC entry 217 (class 1259 OID 17727)
-- Name: mobile_number; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.mobile_number (
    person_id integer,
    mobile_number character(11),
    id integer NOT NULL
);


ALTER TABLE acollyte.mobile_number OWNER TO acollyte;

--
-- TOC entry 223 (class 1259 OID 17752)
-- Name: mobile_number_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.mobile_number ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.mobile_number_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 221 (class 1259 OID 17741)
-- Name: person; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.person (
    name character varying(10485760),
    comunidad integer,
    id integer NOT NULL,
    user_id character varying(50),
    social_user_id integer
);


ALTER TABLE acollyte.person OWNER TO acollyte;

--
-- TOC entry 239 (class 1259 OID 17895)
-- Name: person_app_role; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.person_app_role (
    person_id integer NOT NULL,
    app_role_id integer NOT NULL
);


ALTER TABLE acollyte.person_app_role OWNER TO acollyte;

--
-- TOC entry 222 (class 1259 OID 17746)
-- Name: person_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.person ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 17770)
-- Name: raffle; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.raffle (
    id integer NOT NULL,
    date timestamp with time zone NOT NULL,
    raffle_date timestamp without time zone NOT NULL,
    n_candidates integer,
    n_reserves integer,
    service_id integer,
    n_survived_candidates integer,
    n_survived_reserves integer
);


ALTER TABLE acollyte.raffle OWNER TO acollyte;

--
-- TOC entry 227 (class 1259 OID 17803)
-- Name: raffle_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.raffle ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.raffle_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 231 (class 1259 OID 17833)
-- Name: raffle_person; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.raffle_person (
    id integer NOT NULL,
    selected_type integer NOT NULL,
    can_go boolean,
    has_gone boolean,
    have_gone boolean,
    person_id integer NOT NULL,
    raffle_id integer NOT NULL,
    random integer
);


ALTER TABLE acollyte.raffle_person OWNER TO acollyte;

--
-- TOC entry 232 (class 1259 OID 17838)
-- Name: raffle_person_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.raffle_person ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.raffle_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 219 (class 1259 OID 17733)
-- Name: service; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.service (
    service_name character varying,
    service_date timestamp with time zone,
    service_type integer NOT NULL,
    eve boolean,
    fixed boolean,
    id integer NOT NULL,
    needed integer,
    n_reserves integer
);


ALTER TABLE acollyte.service OWNER TO acollyte;

--
-- TOC entry 225 (class 1259 OID 17761)
-- Name: service_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.service ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.service_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 17724)
-- Name: service_person; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.service_person (
    id integer NOT NULL,
    service_id integer NOT NULL,
    person_id integer NOT NULL,
    reserve_id integer,
    can_go boolean,
    have_gone boolean,
    has_gone boolean
);


ALTER TABLE acollyte.service_person OWNER TO acollyte;

--
-- TOC entry 228 (class 1259 OID 17806)
-- Name: service_person_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.service_person ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.service_person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 17736)
-- Name: service_type; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.service_type (
    id integer NOT NULL,
    service_type character varying(10485760)
);


ALTER TABLE acollyte.service_type OWNER TO acollyte;

--
-- TOC entry 236 (class 1259 OID 17875)
-- Name: social_user; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.social_user (
    id integer NOT NULL,
    name character varying,
    email character varying,
    image_url character varying,
    email_verified boolean,
    provider character varying,
    user_id character varying,
    provider_id character varying
);


ALTER TABLE acollyte.social_user OWNER TO acollyte;

--
-- TOC entry 233 (class 1259 OID 17849)
-- Name: solemnity; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.solemnity (
    id integer NOT NULL,
    name character varying,
    definite boolean,
    date date,
    day smallint,
    month smallint,
    priority smallint
);


ALTER TABLE acollyte.solemnity OWNER TO acollyte;

--
-- TOC entry 214 (class 1259 OID 17718)
-- Name: substitution; Type: TABLE; Schema: acollyte; Owner: acollyte
--

CREATE TABLE acollyte.substitution (
    id integer NOT NULL,
    service_person_id integer,
    person_id integer
);


ALTER TABLE acollyte.substitution OWNER TO acollyte;

--
-- TOC entry 237 (class 1259 OID 17882)
-- Name: user_id_seq; Type: SEQUENCE; Schema: acollyte; Owner: acollyte
--

ALTER TABLE acollyte.social_user ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME acollyte.user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3462 (class 0 OID 17822)
-- Dependencies: 229
-- Data for Name: absence; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.absence (id, day, person_id) FROM stdin;
\.


--
-- TOC entry 3471 (class 0 OID 17888)
-- Dependencies: 238
-- Data for Name: app_permission; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.app_permission (id, permission) FROM stdin;
1	me
2	me_raffle_view
3	me_raffle_confirm
\.


--
-- TOC entry 3473 (class 0 OID 17900)
-- Dependencies: 240
-- Data for Name: app_role; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.app_role (id, role_name, role_parent) FROM stdin;
1	acollyte	\N
2	acollyte_admin	\N
\.


--
-- TOC entry 3474 (class 0 OID 17912)
-- Dependencies: 241
-- Data for Name: app_role_permission; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.app_role_permission (role_id, permission_id) FROM stdin;
1	1
1	2
1	3
\.


--
-- TOC entry 3467 (class 0 OID 17859)
-- Dependencies: 234
-- Data for Name: candidates_raffle; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.candidates_raffle (id, person_id, raffle_id, n_assigned, n_reserved, survive_assigned, survive_reserve) FROM stdin;
78	12	9	0	1	t	f
137	11	15	1	1	t	t
80	15	9	0	1	t	f
72	8	9	1	0	f	t
74	9	9	1	0	f	t
75	10	9	0	0	t	t
76	16	9	1	0	f	t
79	14	9	0	0	t	t
81	7	10	1	1	f	f
21	7	4	0	0	t	t
22	8	4	0	0	t	t
23	13	4	0	0	t	t
24	9	4	0	0	t	t
25	10	4	0	0	t	t
26	16	4	0	0	t	t
27	11	4	0	0	t	t
28	12	4	0	0	t	t
29	14	4	0	0	t	t
30	15	4	0	0	t	t
83	13	10	1	1	f	f
84	9	10	1	1	f	f
37	11	5	0	1	t	f
88	12	10	1	1	f	f
31	7	5	0	0	t	t
32	8	5	0	0	t	t
33	13	5	0	0	t	t
34	9	5	0	0	t	t
35	10	5	0	0	t	t
36	16	5	1	0	f	t
38	12	5	0	0	t	t
39	14	5	0	0	t	t
40	15	5	0	0	t	t
139	14	15	1	1	t	t
87	11	10	0	1	t	f
140	15	15	1	1	t	t
90	15	10	0	1	t	f
47	11	6	0	1	t	f
48	12	6	0	1	t	f
82	8	10	1	0	f	t
85	10	10	0	0	t	t
41	7	6	0	0	t	t
42	8	6	0	0	t	t
43	13	6	0	0	t	t
44	9	6	1	0	f	t
45	10	6	0	0	t	t
46	16	6	1	0	f	t
49	14	6	0	0	t	t
50	15	6	0	0	t	t
86	16	10	1	0	f	t
53	13	7	0	1	t	f
89	14	10	0	0	t	t
57	11	7	0	1	t	f
58	12	7	0	1	t	f
91	7	11	1	1	f	f
51	7	7	1	0	f	t
52	8	7	0	0	t	t
54	9	7	1	0	f	t
55	10	7	0	0	t	t
56	16	7	1	0	f	t
59	14	7	0	0	t	t
60	15	7	0	0	t	t
63	13	8	1	1	f	f
93	13	11	1	1	f	f
94	9	11	1	1	f	f
67	11	8	0	1	t	f
68	12	8	0	1	t	f
70	15	8	0	1	t	f
61	7	8	1	0	f	t
62	8	8	0	0	t	t
64	9	8	1	0	f	t
65	10	8	0	0	t	t
66	16	8	1	0	f	t
69	14	8	0	0	t	t
71	7	9	1	1	f	f
73	13	9	1	1	f	f
77	11	9	0	1	t	f
98	12	11	1	1	f	f
97	11	11	0	1	t	f
99	14	11	0	1	t	f
100	15	11	0	1	t	f
92	8	11	1	0	f	t
95	10	11	1	0	f	t
96	16	11	1	0	f	t
101	7	12	1	1	f	f
103	13	12	1	1	f	f
104	9	12	1	1	f	f
105	10	12	1	1	f	f
108	12	12	1	1	f	f
109	14	12	1	1	f	f
107	11	12	0	1	t	f
110	15	12	0	1	t	f
102	8	12	1	0	f	t
106	16	12	1	0	f	t
111	7	13	1	1	f	f
112	8	13	1	1	f	f
113	13	13	1	1	f	f
114	9	13	1	1	f	f
115	10	13	1	1	f	f
117	11	13	1	1	f	f
118	12	13	1	1	f	f
119	14	13	1	1	f	f
120	15	13	0	1	t	f
116	16	13	1	0	f	t
147	11	16	1	1	t	t
138	12	15	1	2	t	f
143	13	16	1	2	t	f
149	14	16	1	1	t	t
131	7	15	1	1	t	t
121	7	14	1	1	t	t
122	8	14	1	1	t	t
123	13	14	1	1	t	t
124	9	14	1	1	t	t
125	10	14	1	1	t	t
126	16	14	1	1	t	t
127	11	14	1	1	t	t
128	12	14	1	1	t	t
129	14	14	1	1	t	t
130	15	14	1	1	t	t
132	8	15	1	1	t	t
133	13	15	1	1	t	t
134	9	15	1	1	t	t
135	10	15	2	1	f	t
136	16	15	1	1	t	t
142	8	16	1	1	t	t
150	15	16	2	1	f	t
152	8	17	1	1	t	t
148	12	16	1	2	t	f
154	9	17	1	1	t	t
141	7	16	1	1	t	t
144	9	16	1	1	t	t
145	10	16	2	1	f	t
146	16	16	1	1	t	t
153	13	17	1	2	t	f
155	10	17	2	1	f	t
156	16	17	1	2	t	f
159	14	17	2	1	f	t
158	12	17	1	2	t	f
151	7	17	1	1	t	t
157	11	17	1	1	t	t
160	15	17	2	1	f	t
162	8	18	2	1	f	t
163	13	18	1	2	t	f
164	9	18	1	2	t	f
166	16	18	1	2	t	f
161	7	18	1	1	t	t
165	10	18	2	1	f	t
168	12	18	1	2	t	f
167	11	18	1	1	t	t
169	14	18	2	1	f	t
170	15	18	2	1	f	t
175	10	19	2	2	f	f
176	16	19	2	2	f	f
221	7	24	1	0	t	t
173	13	19	1	2	t	f
174	9	19	1	2	t	f
222	8	24	1	0	t	t
178	12	19	1	2	t	f
171	7	19	1	1	t	t
172	8	19	2	1	f	t
177	11	19	1	1	t	t
179	14	19	2	1	f	t
180	15	19	2	1	f	t
224	9	24	1	0	t	t
225	10	24	1	0	t	t
226	16	24	1	0	t	t
228	12	24	1	0	t	t
229	14	24	1	0	t	t
233	13	25	2	1	f	f
181	7	20	0	0	t	t
182	8	20	0	0	t	t
183	13	20	0	0	t	t
184	9	20	0	0	t	t
185	10	20	0	0	t	t
186	16	20	0	0	t	t
187	11	20	0	0	t	t
188	12	20	0	0	t	t
189	14	20	0	0	t	t
190	15	20	0	0	t	t
237	11	25	2	1	f	f
191	7	21	1	0	f	t
192	8	21	1	0	f	t
193	13	21	0	0	t	t
194	9	21	1	0	f	t
195	10	21	0	0	t	t
196	16	21	1	0	f	t
197	11	21	0	0	t	t
198	12	21	1	0	f	t
199	14	21	1	0	f	t
200	15	21	0	0	t	t
203	13	22	0	1	t	f
259	14	27	2	0	t	t
201	7	22	1	0	f	t
202	8	22	1	0	f	t
204	9	22	1	0	f	t
205	10	22	0	0	t	t
206	16	22	1	0	f	t
207	11	22	1	0	f	t
208	12	22	1	0	f	t
209	14	22	1	0	f	t
210	15	22	0	0	t	t
213	13	23	1	1	f	f
217	11	23	1	1	f	f
220	15	23	0	1	t	f
211	7	23	1	0	f	t
212	8	23	1	0	f	t
214	9	23	1	0	f	t
215	10	23	1	0	f	t
216	16	23	1	0	f	t
218	12	23	1	0	f	t
219	14	23	1	0	f	t
223	13	24	1	1	t	f
263	13	28	3	1	f	f
227	11	24	1	1	t	f
230	15	24	1	1	t	f
261	7	28	2	1	t	f
281	7	30	2	1	t	f
240	15	25	1	1	t	f
231	7	25	1	0	t	t
232	8	25	1	0	t	t
234	9	25	1	0	t	t
235	10	25	1	0	t	t
236	16	25	1	0	t	t
238	12	25	2	0	f	t
239	14	25	1	0	t	t
243	13	26	2	1	f	f
247	11	26	2	1	f	f
250	15	26	2	1	f	f
264	9	28	2	1	t	f
241	7	26	2	0	f	t
242	8	26	2	0	f	t
244	9	26	2	0	f	t
245	10	26	2	0	f	t
246	16	26	2	0	f	t
248	12	26	2	0	f	t
249	14	26	1	0	t	t
251	7	27	2	1	t	f
265	10	28	2	1	t	f
253	13	27	2	1	t	f
282	8	30	2	1	t	f
255	10	27	2	1	t	f
267	11	28	2	1	t	f
257	11	27	2	1	t	f
268	12	28	2	1	t	f
285	10	30	2	1	t	f
260	15	27	2	1	t	f
252	8	27	2	0	t	t
254	9	27	2	0	t	t
256	16	27	2	0	t	t
258	12	27	2	0	t	t
270	15	28	2	1	t	f
262	8	28	2	0	t	t
266	16	28	2	0	t	t
269	14	28	3	0	f	t
273	13	29	3	1	f	f
274	9	29	3	1	f	f
271	7	29	2	1	t	f
286	16	30	2	1	t	f
275	10	29	2	1	t	f
276	16	29	2	1	t	f
277	11	29	2	1	t	f
278	12	29	2	1	t	f
280	15	29	2	1	t	f
272	8	29	2	0	t	t
279	14	29	3	0	f	t
283	13	30	3	1	f	f
284	9	30	3	1	f	f
287	11	30	3	1	f	f
288	12	30	2	1	t	f
290	15	30	2	1	t	f
289	14	30	3	0	f	t
292	8	31	3	1	f	t
296	16	31	2	1	t	t
297	11	31	3	1	f	t
299	14	31	3	1	f	t
302	8	32	3	1	f	t
291	7	31	2	1	t	t
293	13	31	3	1	f	t
294	9	31	3	1	f	t
295	10	31	2	1	t	t
298	12	31	2	1	t	t
300	15	31	2	1	t	t
306	16	32	3	1	f	t
308	12	32	2	2	t	f
311	7	33	3	1	f	t
301	7	32	2	1	t	t
303	13	32	3	1	f	t
304	9	32	3	1	f	t
305	10	32	2	1	t	t
307	11	32	3	1	f	t
309	14	32	3	1	f	t
310	15	32	2	1	t	t
312	8	33	3	1	f	t
315	10	33	2	2	t	f
318	12	33	2	2	t	f
313	13	33	3	1	f	t
314	9	33	3	1	f	t
316	16	33	3	1	f	t
317	11	33	3	1	f	t
319	14	33	3	1	f	t
320	15	33	2	1	t	t
324	9	34	3	2	f	f
325	10	34	2	2	t	f
328	12	34	2	2	t	f
321	7	34	3	1	f	t
322	8	34	3	1	f	t
323	13	34	3	1	f	t
326	16	34	3	1	f	t
327	11	34	3	1	f	t
329	14	34	3	1	f	t
330	15	34	3	1	f	t
332	8	35	3	2	f	f
334	9	35	3	2	f	f
335	10	35	3	2	f	f
338	12	35	2	2	t	f
331	7	35	3	1	f	t
333	13	35	3	1	f	t
336	16	35	3	1	f	t
337	11	35	3	1	f	t
339	14	35	3	1	f	t
340	15	35	3	1	f	t
342	8	36	3	2	t	f
343	13	36	3	2	t	f
344	9	36	3	2	t	f
345	10	36	3	2	t	f
348	12	36	3	2	t	f
341	7	36	3	1	t	t
346	16	36	3	1	t	t
347	11	36	3	1	t	t
349	14	36	3	1	t	t
350	15	36	3	1	t	t
353	13	37	4	2	f	f
351	7	37	3	2	t	f
352	8	37	3	2	t	f
354	9	37	3	2	t	f
355	10	37	3	2	t	f
358	12	37	3	2	t	f
356	16	37	3	1	t	t
357	11	37	3	1	t	t
359	14	37	3	1	t	t
360	15	37	3	1	t	t
362	8	38	4	2	f	f
363	13	38	4	2	f	f
361	7	38	3	2	t	f
364	9	38	3	2	t	f
365	10	38	3	2	t	f
368	12	38	3	2	t	f
369	14	38	3	2	t	f
366	16	38	3	1	t	t
367	11	38	3	1	t	t
370	15	38	3	1	t	t
372	8	39	4	2	f	f
373	13	39	4	2	f	f
374	9	39	4	2	f	f
371	7	39	3	2	t	f
375	10	39	3	2	t	f
377	11	39	3	2	t	f
378	12	39	3	2	t	f
379	14	39	3	2	t	f
376	16	39	3	1	t	t
380	15	39	3	1	t	t
\.


--
-- TOC entry 3448 (class 0 OID 17721)
-- Dependencies: 215
-- Data for Name: email_account; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.email_account (person_id, email_account, id) FROM stdin;
7	manuelmontero777@gmail.com	2
9	pedrotedelval@telefonica.net	3
10	felix.merino@gmail.com	4
11	esanzmonge@gmail.com	5
12	jacgarrod@gmail.com	6
13	pb.guerrero.vc@gmail.com	7
14	luissolercarmona@gmail.com	8
15	carlos10rodriguez@hotmail.com	9
\.


--
-- TOC entry 3451 (class 0 OID 17730)
-- Dependencies: 218
-- Data for Name: exception; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.exception (id, person_id, day) FROM stdin;
\.


--
-- TOC entry 3450 (class 0 OID 17727)
-- Dependencies: 217
-- Data for Name: mobile_number; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.mobile_number (person_id, mobile_number, id) FROM stdin;
7	609069136  	3
8	610633926  	4
9	670867381  	5
10	660959325  	6
11	655183112  	7
12	687723441  	8
13	659559311  	9
14	638480930  	10
15	679356309  	11
16	657560401  	12
\.


--
-- TOC entry 3454 (class 0 OID 17741)
-- Dependencies: 221
-- Data for Name: person; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.person (name, comunidad, id, user_id, social_user_id) FROM stdin;
Manuel Montero	1	7	manuelmontero777@gmail.com	\N
Juanvi	1	8	juanvi	\N
Pedro Gutiérrez Gustín	2	9	pedrotedelval@telefonica.net	\N
Eugenio Sanz	6	11	esanzmonge@gmail.com	\N
Jacobo García Rodríguez	6	12	jacgarrod@gmail.com	\N
Pablo Guerrero	1	13	pb.guerrero.vc@gmail.com	\N
Luis Soler Carmona	7	14	luissolercarmona@gmail.com	\N
Carlos Rodríguez	7	15	carlos10rodriguez@hotmail.com	\N
José Aurelio Prado	5	16	joseaurelio	\N
Félix Merino	2	10	felix.merino@gmail.com	3
\.


--
-- TOC entry 3472 (class 0 OID 17895)
-- Dependencies: 239
-- Data for Name: person_app_role; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.person_app_role (person_id, app_role_id) FROM stdin;
10	1
\.


--
-- TOC entry 3459 (class 0 OID 17770)
-- Dependencies: 226
-- Data for Name: raffle; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.raffle (id, date, raffle_date, n_candidates, n_reserves, service_id, n_survived_candidates, n_survived_reserves) FROM stdin;
4	2023-01-01 11:00:00+00	2023-05-03 09:12:59.420664	10	10	592	10	10
5	2023-01-01 11:00:00+00	2023-05-03 09:12:59.420664	10	10	576	9	9
6	2023-01-06 11:00:00+00	2023-05-03 09:12:59.420664	10	10	577	8	8
7	2023-01-08 11:00:00+00	2023-05-03 09:12:59.420664	10	10	593	7	7
8	2023-01-15 11:00:00+00	2023-05-03 09:12:59.420664	10	10	594	6	6
9	2023-01-22 11:00:00+00	2023-05-03 09:12:59.420664	10	10	595	5	5
10	2023-01-29 11:00:00+00	2023-05-03 09:12:59.420664	10	10	596	4	4
11	2023-02-05 11:00:00+00	2023-05-03 09:12:59.420664	10	10	597	3	3
12	2023-02-12 11:00:00+00	2023-05-03 09:12:59.420664	10	10	598	2	2
13	2023-02-19 11:00:00+00	2023-05-03 09:12:59.420664	10	10	599	1	1
14	2023-02-26 11:00:00+00	2023-05-03 09:12:59.420664	10	10	600	10	10
15	2023-03-05 11:00:00+00	2023-05-03 09:12:59.420664	10	10	601	9	9
16	2023-03-12 11:00:00+00	2023-05-03 09:12:59.420664	10	10	602	8	8
17	2023-03-19 11:00:00+00	2023-05-03 09:12:59.420664	10	10	578	7	7
18	2023-03-19 11:00:00+00	2023-05-03 09:12:59.420664	10	10	603	6	6
19	2023-03-26 10:00:00+00	2023-05-03 09:12:59.420664	10	10	604	5	5
20	2023-04-02 06:30:00+00	2023-05-03 09:47:39.478147	10	10	585	10	10
21	2023-04-02 10:00:00+00	2023-05-03 09:47:39.478147	10	10	605	4	10
22	2023-04-02 10:00:00+00	2023-05-03 09:47:39.478147	10	10	586	3	9
23	2023-04-06 15:00:00+00	2023-05-03 09:47:39.478147	10	10	587	1	7
24	2023-04-07 15:00:00+00	2023-05-03 09:47:39.478147	10	10	588	10	7
25	2023-04-08 19:00:00+00	2023-05-03 09:47:39.478147	10	10	589	7	7
26	2023-04-09 08:30:00+00	2023-05-03 09:47:39.478147	10	10	590	1	7
27	2023-04-09 10:00:00+00	2023-05-03 09:47:39.478147	10	10	591	10	5
28	2023-04-09 10:00:00+00	2023-05-03 09:47:39.478147	10	10	606	8	3
29	2023-04-16 10:00:00+00	2023-05-03 09:47:39.478147	10	10	607	7	2
30	2023-04-23 10:00:00+00	2023-05-03 09:47:39.478147	10	10	608	6	1
31	2023-04-30 10:00:00+00	2023-05-03 09:47:39.478147	10	10	609	5	10
32	2023-05-07 10:00:00+00	2023-05-03 09:47:39.478147	10	10	610	4	9
33	2023-05-14 10:00:00+00	2023-05-03 09:47:39.478147	10	10	611	3	8
34	2023-05-21 10:00:00+00	2023-05-03 09:47:39.478147	10	10	612	2	7
35	2023-05-28 10:00:00+00	2023-05-03 09:47:39.478147	10	10	613	1	6
36	2023-06-04 10:00:00+00	2023-05-03 09:47:39.478147	10	10	614	10	5
37	2023-06-11 10:00:00+00	2023-05-03 09:47:39.478147	10	10	615	9	4
38	2023-06-18 10:00:00+00	2023-05-03 09:47:39.478147	10	10	616	8	3
39	2023-06-25 10:00:00+00	2023-05-03 09:47:39.478147	10	10	617	7	2
\.


--
-- TOC entry 3464 (class 0 OID 17833)
-- Dependencies: 231
-- Data for Name: raffle_person; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.raffle_person (id, selected_type, can_go, has_gone, have_gone, person_id, raffle_id, random) FROM stdin;
2	1	\N	\N	\N	16	4	5
3	2	\N	\N	\N	11	4	5
4	1	\N	\N	\N	9	5	3
5	2	\N	\N	\N	12	5	5
6	1	\N	\N	\N	7	6	0
7	2	\N	\N	\N	13	6	1
8	1	\N	\N	\N	13	7	1
9	2	\N	\N	\N	15	7	6
10	1	\N	\N	\N	8	8	0
11	2	\N	\N	\N	7	8	0
12	1	\N	\N	\N	12	9	2
13	2	\N	\N	\N	9	9	1
14	1	\N	\N	\N	10	10	0
15	2	\N	\N	\N	14	10	2
16	1	\N	\N	\N	14	11	1
17	2	\N	\N	\N	10	11	1
18	1	\N	\N	\N	11	12	0
19	2	\N	\N	\N	8	12	0
20	1	\N	\N	\N	15	13	0
21	2	\N	\N	\N	16	13	0
22	1	\N	\N	\N	10	14	4
23	2	\N	\N	\N	12	14	6
24	1	\N	\N	\N	15	15	8
25	2	\N	\N	\N	13	15	2
26	1	\N	\N	\N	14	16	7
27	2	\N	\N	\N	16	16	4
28	1	\N	\N	\N	8	17	1
29	2	\N	\N	\N	9	17	1
30	1	\N	\N	\N	16	18	3
31	2	\N	\N	\N	10	18	2
32	1	\N	\N	\N	9	19	2
33	2	\N	\N	\N	8	19	1
34	1	\N	\N	\N	12	20	7
35	1	\N	\N	\N	7	20	0
36	1	\N	\N	\N	9	20	3
37	1	\N	\N	\N	8	20	1
38	1	\N	\N	\N	16	20	5
39	1	\N	\N	\N	14	20	8
40	1	\N	\N	\N	11	21	2
41	2	\N	\N	\N	13	21	2
42	1	\N	\N	\N	13	22	0
43	1	\N	\N	\N	10	22	1
44	2	\N	\N	\N	15	22	7
45	2	\N	\N	\N	11	22	4
46	1	\N	\N	\N	15	23	0
47	1	\N	\N	\N	12	24	7
48	1	\N	\N	\N	13	24	2
49	1	\N	\N	\N	11	24	6
50	1	\N	\N	\N	15	25	6
51	1	\N	\N	\N	8	25	1
52	1	\N	\N	\N	10	25	3
53	1	\N	\N	\N	7	25	0
54	1	\N	\N	\N	16	25	4
55	1	\N	\N	\N	9	25	2
56	1	\N	\N	\N	14	26	0
57	2	\N	\N	\N	7	26	0
58	2	\N	\N	\N	10	26	3
59	1	\N	\N	\N	14	27	8
60	1	\N	\N	\N	13	27	2
61	2	\N	\N	\N	12	27	3
62	2	\N	\N	\N	9	27	1
63	1	\N	\N	\N	9	28	2
64	2	\N	\N	\N	16	28	1
65	1	\N	\N	\N	11	29	4
66	2	\N	\N	\N	8	29	0
67	1	\N	\N	\N	8	30	1
68	2	\N	\N	\N	14	30	0
69	1	\N	\N	\N	16	31	2
70	2	\N	\N	\N	12	31	6
71	1	\N	\N	\N	7	32	0
72	2	\N	\N	\N	10	32	3
73	1	\N	\N	\N	15	33	2
74	2	\N	\N	\N	9	33	3
75	1	\N	\N	\N	10	34	0
76	2	\N	\N	\N	8	34	1
77	1	\N	\N	\N	12	35	0
78	2	\N	\N	\N	13	35	1
79	1	\N	\N	\N	13	36	2
80	2	\N	\N	\N	7	36	0
81	1	\N	\N	\N	8	37	1
82	2	\N	\N	\N	14	37	2
83	1	\N	\N	\N	9	38	1
84	2	\N	\N	\N	11	38	1
85	1	\N	\N	\N	10	39	1
86	2	\N	\N	\N	16	39	0
\.


--
-- TOC entry 3452 (class 0 OID 17733)
-- Dependencies: 219
-- Data for Name: service; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.service (service_name, service_date, service_type, eve, fixed, id, needed, n_reserves) FROM stdin;
Santa María Madre de Dios	2023-01-01 11:00:00+00	2	f	t	576	1	1
Epifanía del Señor	2023-01-06 11:00:00+00	2	f	t	577	1	1
San José Esposo de la Santísima Virgen María	2023-03-19 11:00:00+00	2	f	t	578	1	1
Santiago, apóstol	2023-07-25 10:00:00+00	2	f	t	579	1	1
Asunción de la bienaventurada Virgen María	2023-08-15 10:00:00+00	2	f	t	580	1	1
Todos los Santos	2023-11-01 11:00:00+00	2	f	t	581	1	1
Víspera de Inmaculada Concepción de la bienaventurada Virgen María	2023-12-07 20:00:00+00	2	f	t	582	4	0
Inmaculada Concepción de la bienaventurada Virgen María	2023-12-08 11:00:00+00	2	f	t	583	2	2
Natividad del Señor	2023-12-25 11:00:00+00	2	f	t	584	1	1
Domingo de ramos en la pasión del Señor	2023-04-02 06:30:00+00	2	f	f	585	6	0
Domingo de ramos en la pasión del Señor	2023-04-02 10:00:00+00	2	f	f	586	2	2
Jueves Santo en la Cena del Señor	2023-04-06 15:00:00+00	2	f	f	587	6	0
Viernes Santo en la Pasión del Señor	2023-04-07 15:00:00+00	2	f	f	588	3	0
Vigilia Pascual en la Noche Santa	2023-04-08 19:00:00+00	2	f	f	589	6	0
Domingo de Pascua de la Resurrección del Señor	2023-04-09 08:30:00+00	2	f	f	590	2	2
Domingo de Pascua de la Resurrección del Señor	2023-04-09 10:00:00+00	2	f	f	591	2	2
Sunday	2023-01-01 11:00:00+00	1	f	f	592	1	1
Sunday	2023-01-08 11:00:00+00	1	f	f	593	1	1
Sunday	2023-01-15 11:00:00+00	1	f	f	594	1	1
Sunday	2023-01-22 11:00:00+00	1	f	f	595	1	1
Sunday	2023-01-29 11:00:00+00	1	f	f	596	1	1
Sunday	2023-02-05 11:00:00+00	1	f	f	597	1	1
Sunday	2023-02-12 11:00:00+00	1	f	f	598	1	1
Sunday	2023-02-19 11:00:00+00	1	f	f	599	1	1
Sunday	2023-02-26 11:00:00+00	1	f	f	600	1	1
Sunday	2023-03-05 11:00:00+00	1	f	f	601	1	1
Sunday	2023-03-12 11:00:00+00	1	f	f	602	1	1
Sunday	2023-03-19 11:00:00+00	1	f	f	603	1	1
Sunday	2023-03-26 10:00:00+00	1	f	f	604	1	1
Sunday	2023-04-02 10:00:00+00	1	f	f	605	1	1
Sunday	2023-04-09 10:00:00+00	1	f	f	606	1	1
Sunday	2023-04-16 10:00:00+00	1	f	f	607	1	1
Sunday	2023-04-23 10:00:00+00	1	f	f	608	1	1
Sunday	2023-04-30 10:00:00+00	1	f	f	609	1	1
Sunday	2023-05-07 10:00:00+00	1	f	f	610	1	1
Sunday	2023-05-14 10:00:00+00	1	f	f	611	1	1
Sunday	2023-05-21 10:00:00+00	1	f	f	612	1	1
Sunday	2023-05-28 10:00:00+00	1	f	f	613	1	1
Sunday	2023-06-04 10:00:00+00	1	f	f	614	1	1
Sunday	2023-06-11 10:00:00+00	1	f	f	615	1	1
Sunday	2023-06-18 10:00:00+00	1	f	f	616	1	1
Sunday	2023-06-25 10:00:00+00	1	f	f	617	1	1
Sunday	2023-07-02 10:00:00+00	1	f	f	618	1	1
Sunday	2023-07-09 10:00:00+00	1	f	f	619	1	1
Sunday	2023-07-16 10:00:00+00	1	f	f	620	1	1
Sunday	2023-07-23 10:00:00+00	1	f	f	621	1	1
Sunday	2023-07-30 10:00:00+00	1	f	f	622	1	1
Sunday	2023-08-06 10:00:00+00	1	f	f	623	1	1
Sunday	2023-08-13 10:00:00+00	1	f	f	624	1	1
Sunday	2023-08-20 10:00:00+00	1	f	f	625	1	1
Sunday	2023-08-27 10:00:00+00	1	f	f	626	1	1
Sunday	2023-09-03 10:00:00+00	1	f	f	627	1	1
Sunday	2023-09-10 10:00:00+00	1	f	f	628	1	1
Sunday	2023-09-17 10:00:00+00	1	f	f	629	1	1
Sunday	2023-09-24 10:00:00+00	1	f	f	630	1	1
Sunday	2023-10-01 10:00:00+00	1	f	f	631	1	1
Sunday	2023-10-08 10:00:00+00	1	f	f	632	1	1
Sunday	2023-10-15 10:00:00+00	1	f	f	633	1	1
Sunday	2023-10-22 10:00:00+00	1	f	f	634	1	1
Sunday	2023-10-29 11:00:00+00	1	f	f	635	1	1
Sunday	2023-11-05 11:00:00+00	1	f	f	636	1	1
Sunday	2023-11-12 11:00:00+00	1	f	f	637	1	1
Sunday	2023-11-19 11:00:00+00	1	f	f	638	1	1
Sunday	2023-11-26 11:00:00+00	1	f	f	639	1	1
Sunday	2023-12-03 11:00:00+00	1	f	f	640	1	1
Sunday	2023-12-10 11:00:00+00	1	f	f	641	1	1
Sunday	2023-12-17 11:00:00+00	1	f	f	642	1	1
Sunday	2023-12-24 11:00:00+00	1	f	f	643	1	1
Sunday	2023-12-31 11:00:00+00	1	f	f	644	1	1
\.


--
-- TOC entry 3449 (class 0 OID 17724)
-- Dependencies: 216
-- Data for Name: service_person; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.service_person (id, service_id, person_id, reserve_id, can_go, have_gone, has_gone) FROM stdin;
\.


--
-- TOC entry 3453 (class 0 OID 17736)
-- Dependencies: 220
-- Data for Name: service_type; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.service_type (id, service_type) FROM stdin;
1	Sunday
2	Solemnity
\.


--
-- TOC entry 3469 (class 0 OID 17875)
-- Dependencies: 236
-- Data for Name: social_user; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.social_user (id, name, email, image_url, email_verified, provider, user_id, provider_id) FROM stdin;
6	Felix Merino Martínez de Pinillos	felix.merino.fotografo@gmail.com	https://lh3.googleusercontent.com/a/AGNmyxYaEuwa1GJOxKbIVnf-ha9_JvEKyO_tvmPaRr5s=s96-c	t	google	\N	114749612519976518758
7	Felix Merino Cabanes	felix.cuarto.sensei@gmail.com	https://lh3.googleusercontent.com/a/AGNmyxaUuYU63D6Tg0909DgTqh92ATE4F7IdCkPVjh1a=s96-c	t	google	\N	111134883415880727197
3	Felix Merino	felix.merino@gmail.com	https://lh3.googleusercontent.com/a/ACg8ocLP8Qo5lslxrU7s6TkKSGiyc1Dzc3hgu5NvDYBGT7x5DRtB3Qds=s96-c	t	google	\N	110020632341192920624
\.


--
-- TOC entry 3466 (class 0 OID 17849)
-- Dependencies: 233
-- Data for Name: solemnity; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.solemnity (id, name, definite, date, day, month, priority) FROM stdin;
1	Santa María Madre de Dios	t	2023-01-01	1	1	\N
2	Epifanía del Señor	t	2023-01-06	6	1	\N
3	San José Esposo de la Santísima Virgen María	t	2023-03-19	19	3	\N
4	Santiago, apóstol	t	2023-07-25	25	7	\N
5	Asunción de la bienaventurada Virgen María	t	2023-08-15	15	8	\N
6	Todos los Santos	t	2023-11-01	1	11	\N
7	Inmaculada Concepción de la bienaventurada Virgen María	t	2023-12-08	8	12	\N
8	Natividad del Señor	t	2023-12-25	25	12	\N
10	Jueves Santo en la Cena del Señor	f	2023-04-06	6	4	\N
11	Viernes Santo en la Pasión del Señor	f	2023-04-07	7	4	\N
12	Vigilia Pascual en la Noche Santa	f	2023-04-08	8	4	\N
13	Domingo de Pascua de la Resurrección del Señor	f	2023-04-09	9	4	\N
9	Domingo de Ramos en la Pasión del Señor	f	2023-04-02	2	4	\N
\.


--
-- TOC entry 3447 (class 0 OID 17718)
-- Dependencies: 214
-- Data for Name: substitution; Type: TABLE DATA; Schema: acollyte; Owner: acollyte
--

COPY acollyte.substitution (id, service_person_id, person_id) FROM stdin;
\.


--
-- TOC entry 3483 (class 0 OID 0)
-- Dependencies: 230
-- Name: absence_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.absence_id_seq', 1, false);


--
-- TOC entry 3484 (class 0 OID 0)
-- Dependencies: 242
-- Name: app_permission_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.app_permission_id_seq', 3, true);


--
-- TOC entry 3485 (class 0 OID 0)
-- Dependencies: 243
-- Name: app_role_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.app_role_id_seq', 2, true);


--
-- TOC entry 3486 (class 0 OID 0)
-- Dependencies: 235
-- Name: candidates_raffle_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.candidates_raffle_id_seq', 380, true);


--
-- TOC entry 3487 (class 0 OID 0)
-- Dependencies: 224
-- Name: email_account_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.email_account_id_seq', 9, true);


--
-- TOC entry 3488 (class 0 OID 0)
-- Dependencies: 223
-- Name: mobile_number_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.mobile_number_id_seq', 12, true);


--
-- TOC entry 3489 (class 0 OID 0)
-- Dependencies: 222
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.person_id_seq', 16, true);


--
-- TOC entry 3490 (class 0 OID 0)
-- Dependencies: 227
-- Name: raffle_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.raffle_id_seq', 39, true);


--
-- TOC entry 3491 (class 0 OID 0)
-- Dependencies: 232
-- Name: raffle_person_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.raffle_person_id_seq', 86, true);


--
-- TOC entry 3492 (class 0 OID 0)
-- Dependencies: 225
-- Name: service_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.service_id_seq', 644, true);


--
-- TOC entry 3493 (class 0 OID 0)
-- Dependencies: 228
-- Name: service_person_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.service_person_id_seq', 1, false);


--
-- TOC entry 3494 (class 0 OID 0)
-- Dependencies: 237
-- Name: user_id_seq; Type: SEQUENCE SET; Schema: acollyte; Owner: acollyte
--

SELECT pg_catalog.setval('acollyte.user_id_seq', 7, true);


--
-- TOC entry 3270 (class 2606 OID 17826)
-- Name: absence absence_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.absence
    ADD CONSTRAINT absence_pk PRIMARY KEY (id);


--
-- TOC entry 3280 (class 2606 OID 17894)
-- Name: app_permission app_permission_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.app_permission
    ADD CONSTRAINT app_permission_pk PRIMARY KEY (id);


--
-- TOC entry 3286 (class 2606 OID 17919)
-- Name: app_role_permission app_role_permission_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.app_role_permission
    ADD CONSTRAINT app_role_permission_pk PRIMARY KEY (permission_id, role_id);


--
-- TOC entry 3284 (class 2606 OID 17906)
-- Name: app_role app_role_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.app_role
    ADD CONSTRAINT app_role_pk PRIMARY KEY (id);


--
-- TOC entry 3276 (class 2606 OID 17863)
-- Name: candidates_raffle candidates_raffle_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.candidates_raffle
    ADD CONSTRAINT candidates_raffle_pk PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 17790)
-- Name: email_account email_account_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.email_account
    ADD CONSTRAINT email_account_pk PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 17797)
-- Name: mobile_number mobile_number_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.mobile_number
    ADD CONSTRAINT mobile_number_pk PRIMARY KEY (id);


--
-- TOC entry 3282 (class 2606 OID 17899)
-- Name: person_app_role person_app_role_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.person_app_role
    ADD CONSTRAINT person_app_role_pk PRIMARY KEY (person_id, app_role_id);


--
-- TOC entry 3266 (class 2606 OID 17769)
-- Name: person person_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.person
    ADD CONSTRAINT person_pk PRIMARY KEY (id);


--
-- TOC entry 3272 (class 2606 OID 17837)
-- Name: raffle_person raffle_person_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.raffle_person
    ADD CONSTRAINT raffle_person_pk PRIMARY KEY (id);


--
-- TOC entry 3268 (class 2606 OID 17774)
-- Name: raffle raffle_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.raffle
    ADD CONSTRAINT raffle_pk PRIMARY KEY (id);


--
-- TOC entry 3258 (class 2606 OID 17805)
-- Name: service_person service_person_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service_person
    ADD CONSTRAINT service_person_pk PRIMARY KEY (id);


--
-- TOC entry 3262 (class 2606 OID 17776)
-- Name: service service_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service
    ADD CONSTRAINT service_pk PRIMARY KEY (id);


--
-- TOC entry 3264 (class 2606 OID 17778)
-- Name: service_type service_type_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service_type
    ADD CONSTRAINT service_type_pk PRIMARY KEY (id);


--
-- TOC entry 3278 (class 2606 OID 17881)
-- Name: social_user social_user_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.social_user
    ADD CONSTRAINT social_user_pk PRIMARY KEY (id);


--
-- TOC entry 3274 (class 2606 OID 17855)
-- Name: solemnity solemnity_pk; Type: CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.solemnity
    ADD CONSTRAINT solemnity_pk PRIMARY KEY (id);


--
-- TOC entry 3295 (class 2606 OID 17828)
-- Name: absence absence_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.absence
    ADD CONSTRAINT absence_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3298 (class 2606 OID 17865)
-- Name: candidates_raffle candidates_raffle_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.candidates_raffle
    ADD CONSTRAINT candidates_raffle_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3299 (class 2606 OID 17870)
-- Name: candidates_raffle candidates_raffle_raffle_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.candidates_raffle
    ADD CONSTRAINT candidates_raffle_raffle_fk FOREIGN KEY (raffle_id) REFERENCES acollyte.raffle(id) NOT VALID;


--
-- TOC entry 3287 (class 2606 OID 17791)
-- Name: email_account email_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.email_account
    ADD CONSTRAINT email_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3291 (class 2606 OID 17798)
-- Name: mobile_number mobile_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.mobile_number
    ADD CONSTRAINT mobile_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3303 (class 2606 OID 17925)
-- Name: app_role_permission permission_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.app_role_permission
    ADD CONSTRAINT permission_fk FOREIGN KEY (permission_id) REFERENCES acollyte.app_permission(id) NOT VALID;


--
-- TOC entry 3300 (class 2606 OID 17932)
-- Name: person_app_role person_has_role_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.person_app_role
    ADD CONSTRAINT person_has_role_fk FOREIGN KEY (app_role_id) REFERENCES acollyte.app_role(id) NOT VALID;


--
-- TOC entry 3294 (class 2606 OID 17784)
-- Name: raffle raffle_service_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.raffle
    ADD CONSTRAINT raffle_service_fk FOREIGN KEY (service_id) REFERENCES acollyte.service(id) NOT VALID;


--
-- TOC entry 3304 (class 2606 OID 17920)
-- Name: app_role_permission role_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.app_role_permission
    ADD CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES acollyte.app_role(id) NOT VALID;


--
-- TOC entry 3301 (class 2606 OID 17937)
-- Name: person_app_role role_has_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.person_app_role
    ADD CONSTRAINT role_has_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3302 (class 2606 OID 17907)
-- Name: app_role role_parent_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.app_role
    ADD CONSTRAINT role_parent_fk FOREIGN KEY (role_parent) REFERENCES acollyte.app_role(id) NOT VALID;


--
-- TOC entry 3296 (class 2606 OID 17844)
-- Name: raffle_person rp_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.raffle_person
    ADD CONSTRAINT rp_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3297 (class 2606 OID 17839)
-- Name: raffle_person rp_raffle_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.raffle_person
    ADD CONSTRAINT rp_raffle_fk FOREIGN KEY (raffle_id) REFERENCES acollyte.raffle(id) NOT VALID;


--
-- TOC entry 3292 (class 2606 OID 17779)
-- Name: service service_st_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service
    ADD CONSTRAINT service_st_fk FOREIGN KEY (service_type) REFERENCES acollyte.service_type(id) NOT VALID;


--
-- TOC entry 3293 (class 2606 OID 17883)
-- Name: person social_user_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.person
    ADD CONSTRAINT social_user_fk FOREIGN KEY (social_user_id) REFERENCES acollyte.social_user(id) NOT VALID;


--
-- TOC entry 3288 (class 2606 OID 17807)
-- Name: service_person sp_person_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service_person
    ADD CONSTRAINT sp_person_fk FOREIGN KEY (person_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3289 (class 2606 OID 17817)
-- Name: service_person sp_reserve_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service_person
    ADD CONSTRAINT sp_reserve_fk FOREIGN KEY (reserve_id) REFERENCES acollyte.person(id) NOT VALID;


--
-- TOC entry 3290 (class 2606 OID 17812)
-- Name: service_person sp_service_fk; Type: FK CONSTRAINT; Schema: acollyte; Owner: acollyte
--

ALTER TABLE ONLY acollyte.service_person
    ADD CONSTRAINT sp_service_fk FOREIGN KEY (service_id) REFERENCES acollyte.service(id) NOT VALID;


-- Completed on 2025-03-28 14:56:33 UTC

--
-- PostgreSQL database dump complete
--

