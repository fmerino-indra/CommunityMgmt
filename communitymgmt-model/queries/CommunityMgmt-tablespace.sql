-- Conectar:
---- psql -h localhost -p 5432 -U acollyte

CREATE TABLESPACE acollyte_ts LOCATION '/var/lib/postgresql/tablespaces/acollyte_ts';

SELECT spcname, pg_tablespace_location(oid) FROM pg_tablespace;

create database CommunityMgmt TABLESPPACE fmmp_ts;
alter database CommunityMgmt set default_tablespace = fmmp_ts;

-- Info del tablespace
select * from pg_tablespace where spcname = 'fmmp_ts';

select datname, spcname
from pg_database d
left join pg_tablespace t on d.dattablespace = t.oid;

SELECT tablename, tablespace 
FROM pg_tables 
WHERE schemaname = 'public';

--En psql: Cambia a BBDD 
---- \c CommunityMgmt
-- En qué base de datos estoy
---- \c
-- Listar BBDD
---- \l

select current_database();

-- Listar bases de datos
select datname, pg_size_pretty(pg_database_size(datname)) as size from pg_database;


create schema CommunityMgmt authorization acollyte;

-- Listar schemas:
---- \dn
select schema_name from information_schema.schemata;

alter database communitymgmt set search_path to communitymgmt, public;
show search_path;

set search_path to communitymgmt;

-- psql -h localhost -p 5432 -U acollyte -d communitymgmt