--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.1
-- Dumped by pg_dump version 9.6.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: alliance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE alliance (
    uuid character varying(255) NOT NULL,
    lastmodified timestamp without time zone,
    name character varying(255) NOT NULL
);


ALTER TABLE alliance OWNER TO postgres;

--
-- Name: army; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE army (
    uuid character varying(255) NOT NULL,
    lastmodified timestamp without time zone,
    count integer,
    ownunit boolean NOT NULL,
    type character varying(255),
    ar_village_uuid character varying(255),
    village_uuid character varying(255)
);


ALTER TABLE army OWNER TO postgres;

--
-- Name: player; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE player (
    uuid character varying(255) NOT NULL,
    lastmodified timestamp without time zone,
    race character varying(255),
    alliance_id character varying(255),
    user_uuid character varying(255)
);


ALTER TABLE player OWNER TO postgres;

--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE user_roles (
    user_uuid character varying(255) NOT NULL,
    roles character varying(255)
);


ALTER TABLE user_roles OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    uuid character varying(255) NOT NULL,
    lastmodified timestamp without time zone,
    email character varying(255) NOT NULL,
    login character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    player_uuid character varying(255)
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: village; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE village (
    uuid character varying(255) NOT NULL,
    lastmodified timestamp without time zone,
    iscapital boolean,
    name character varying(255),
    population smallint,
    wall smallint,
    xcoord smallint NOT NULL,
    ycoord smallint NOT NULL,
    player_uuid character varying(255) NOT NULL
);


ALTER TABLE village OWNER TO postgres;

--
-- Data for Name: alliance; Type: TABLE DATA; Schema: public; Owner: postgres
-- Data for Name: alliance; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: army; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: player; Type: TABLE DATA; Schema: public; Owner: postgres
--

-- Alliance Valhala
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('valhala-player-1', '2017-01-06 18:52:08.947', 'ROMANS', 'valhala', 'valhala-user-1');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('valhala-player-2', '2017-01-06 18:52:08.947', 'TEUTONS', 'valhala', 'valhala-user-2');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('valhala-player-3', '2017-01-06 18:52:08.947', 'GAULS', 'valhala', 'valhala-user-3');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('valhala-player-4', '2017-01-06 18:52:08.947', 'TEUTONS', 'valhala', 'valhala-user-4');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('valhala-player-5', '2017-01-06 18:52:08.947', 'GAULS', 'valhala', 'valhala-user-5');

--Alliance Matrix
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('matrix-player-1', '2017-01-06 18:52:08.947', 'ROMANS', 'martix', 'matrix-user-1');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('matrix-player-2', '2017-01-06 18:52:08.947', 'TEUTONS', 'martix', 'matrix-user-2');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('matrix-player-3', '2017-01-06 18:52:08.947', 'GAULS', 'martix', 'matrix-user-3');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('matrix-player-4', '2017-01-06 18:52:08.947', 'TEUTONS', 'martix', 'matrix-user-4');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('matrix-player-5', '2017-01-06 18:52:08.947', 'GAULS', 'martix', 'matrix-user-5');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('matrix-player-6', '2017-01-06 18:52:08.947', 'GAULS', 'martix', 'matrix-user-6');

--Alliance MidleEarth
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('midle-player-1', '2017-01-06 18:52:08.947', 'ROMANS', 'midleeath', 'midle-user-1');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('midle-player-2', '2017-01-06 18:52:08.947', 'TEUTONS', 'midleeath', 'midle-user-2');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('midle-player-3', '2017-01-06 18:52:08.947', 'GAULS', 'midleeath', 'midle-user-3');
INSERT INTO player (uuid, lastmodified, race, alliance_id, user_uuid) VALUES ('midle-player-4', '2017-01-06 18:52:08.947', 'TEUTONS', 'midleeath', 'midle-user-4');


--  Admin
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('9b0c9b2a-0371-480a-916a-7bf40990cdda', '2017-01-06 18:52:08.947', 'morpheus@ukr.net', 'morpheus', '$2a$10$krSAe/921Gq4L2qNgpG4LeEg/2YW8DL8gZ/uy0gtuHkNDlETBebuW', null);

-- Leaders

--Alliance Valhala
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-1', '2017-01-06 18:52:08.947', 'neo@ukr.net', 'neo', '$2a$10$/PDz58CrcIuys5GnX96/jeLG0HmI/X2bAEpsij5UVtUZx9J86Azf6', 'valhala-player-1');

--Alliance Matrix
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-1', '2017-01-06 18:52:08.947', 'borg@ukr.net', 'borg', '$2a$10$/PDz58CrcIuys5GnX96/jeLG0HmI/X2bAEpsij5UVtUZx9J86Azf6', 'matrix-player-1');

-- Alliance MidleErth
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-1', '2017-01-06 18:52:08.947', 'smith@ukr.net', 'smith', '$2a$10$/PDz58CrcIuys5GnX96/jeLG0HmI/X2bAEpsij5UVtUZx9J86Azf6', 'midle-player-1');

--Players
--Alliance Valhala
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-2', '2017-01-06 18:52:08.947', 'golf@ukr.net', 'golf', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'valhala-player-2');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-3', '2017-01-06 18:52:08.947', 'emmi@ukr.net', 'emmi', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'valhala-player-3');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-4', '2017-01-06 18:52:08.947', 'alan@ukr.net', 'alan', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'valhala-player-4');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-5', '2017-01-06 18:52:08.947', 'cole@ukr.net', 'cole', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'valhala-player-5');

--Players
--Alliance Matrix
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-2', '2017-01-06 18:52:08.947', 'trinity@ukr.net', 'trinity', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'matrix-player-2');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-3', '2017-01-06 18:52:08.947', 'zig@ukr.net', 'zig', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'matrix-player-3');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-4', '2017-01-06 18:52:08.947', 'amdrea@ukr.net', 'andrea', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'matrix-player-4');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-5', '2017-01-06 18:52:08.947', 'rick@ukr.net', 'rick', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'matrix-player-5');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-6', '2017-01-06 18:52:08.947', 'deril@ukr.net', 'deril', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'matrix-player-6');

--Players
--Alliance MidleEarth
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-2', '2017-01-06 18:52:08.947', 'frodo@ukr.net', 'frodo', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'midle-player-2');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-3', '2017-01-06 18:52:08.947', 'legolas@ukr.net', 'legolas', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'midle-player-3');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-4', '2017-01-06 18:52:08.947', 'aragorn@ukr.net', 'aragorn', '$2a$10$FAigv62dBfKCsziePFxgXOUipU/YWpbsds.i6/5OKkRm3UfTKo7vC', 'midle-player-4');



-- Admin
INSERT INTO user_roles (user_uuid, roles) VALUES ('9b0c9b2a-0371-480a-916a-7bf40990cdda', 'ADMIN');

--Leaders
INSERT INTO user_roles (user_uuid, roles) VALUES ('valhala-user-1', 'LEADER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-1', 'LEADER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('midle-user-1', 'LEADER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('valhala-user-1', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-1', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('midle-user-1', 'USER');

--Users
--Alliance Valhala
INSERT INTO user_roles (user_uuid, roles) VALUES ('valhala-user-2', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('valhala-user-3', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('valhala-user-4', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('valhala-user-5', 'USER');


--Alliance Matrix
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-2', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-3', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-4', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-5', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('matrix-user-6', 'USER');

--Alliance MidleEarth
INSERT INTO user_roles (user_uuid, roles) VALUES ('midle-user-2', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('midle-user-3', 'USER');
INSERT INTO user_roles (user_uuid, roles) VALUES ('midle-user-4', 'USER');




INSERT INTO alliance (uuid, lastmodified, name) VALUES ('valhala', '2017-01-06 19:28:08.947', 'valhala');
INSERT INTO alliance (uuid, lastmodified, name) VALUES ('martix', '2017-01-06 19:28:08.947', 'martix');
INSERT INTO alliance (uuid, lastmodified, name) VALUES ('midleeath', '2017-01-06 19:28:08.947', 'midleeath');


insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('1','2017-01-06 18:52:08.947',TRUE ,'village1',50,50,12,13,'valhala-player-1');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('2','2017-01-06 18:52:08.947',FALSE ,'village2',40,40,22,23,'valhala-player-1');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('3','2017-01-06 18:52:08.947',FALSE ,'village3',90,90,92,93,'valhala-player-1');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('4','2017-01-06 18:52:08.947',TRUE ,'village4',90,90,93,94,'valhala-player-2');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('5','2017-01-06 18:52:08.947',FALSE ,'village5',90,90,94,95,'valhala-player-2');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('6','2017-01-06 18:52:08.947',TRUE ,'village6',90,90,95,96,'valhala-player-3');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('7','2017-01-06 18:52:08.947',FALSE ,'village7',90,90,96,97,'valhala-player-3');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('8','2017-01-06 18:52:08.947',FALSE ,'village8',90,90,98,99,'valhala-player-3');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('9','2017-01-06 18:52:08.947',TRUE ,'village9',90,90,99,10,'valhala-player-4');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('10','2017-01-06 18:52:08.947',FALSE ,'village10',90,90,10,11,'valhala-player-4');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('11','2017-01-06 18:52:08.947',TRUE ,'village11',90,90,11,12,'valhala-player-5');


insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('23','2017-01-06 18:52:08.947',TRUE ,'village12',50,50,12,14,'matrix-player-1');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('12','2017-01-06 18:52:08.947',FALSE ,'village13',40,40,23,24,'matrix-player-1');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('13','2017-01-06 18:52:08.947',FALSE ,'village14',90,90,24,25,'matrix-player-1');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('14','2017-01-06 18:52:08.947',TRUE ,'village15',90,90,25,26,'matrix-player-2');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('15','2017-01-06 18:52:08.947',FALSE ,'village16',90,90,26,27,'matrix-player-2');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('16','2017-01-06 18:52:08.947',TRUE ,'village17',90,90,27,28,'matrix-player-3');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('17','2017-01-06 18:52:08.947',FALSE ,'village18',90,90,28,29,'matrix-player-3');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('18','2017-01-06 18:52:08.947',FALSE ,'village19',90,90,29,30,'matrix-player-3');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('19','2017-01-06 18:52:08.947',TRUE ,'village20',90,90,30,31,'matrix-player-4');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('20','2017-01-06 18:52:08.947',FALSE ,'village21',90,90,31,32,'matrix-player-4');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('21','2017-01-06 18:52:08.947',TRUE ,'village22',90,90,32,33,'matrix-player-5');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('22','2017-01-06 18:52:08.947',TRUE ,'village23',90,90,33,34,'matrix-player-6');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('24','2017-01-06 18:52:08.947',TRUE ,'village24',50,50,34,35,'midle-player-1');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('25','2017-01-06 18:52:08.947',FALSE ,'village25',40,40,35,36,'midle-player-1');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('26','2017-01-06 18:52:08.947',FALSE ,'village26',90,90,36,37,'midle-player-1');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('27','2017-01-06 18:52:08.947',TRUE ,'village27',90,90,37,38,'midle-player-2');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('28','2017-01-06 18:52:08.947',FALSE ,'village28',90,90,38,39,'midle-player-2');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('29','2017-01-06 18:52:08.947',TRUE ,'village29',90,90,39,40,'midle-player-3');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('30','2017-01-06 18:52:08.947',FALSE ,'village30',90,90,40,41,'midle-player-3');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('31','2017-01-06 18:52:08.947',FALSE ,'village31',90,90,41,42,'midle-player-3');

insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('32','2017-01-06 18:52:08.947',TRUE ,'village32',90,90,42,43,'midle-player-4');
insert into village (uuid, lastmodified, iscapital, name, population, wall, xcoord, ycoord, player_uuid) VALUES ('33','2017-01-06 18:52:08.947',FALSE ,'village33',90,90,43,44,'midle-player-4');

-- Armies


INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('1army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'1');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'2');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('2army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'2');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('3army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'3');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('4army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'4');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('5army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'5');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('6army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'6');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('7army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'7');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('8army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'8');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'9');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('9army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'9');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('10army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'10');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('11army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'11');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'12');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('12army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'12');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('13army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'13');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('14army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'14');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('15army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'15');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('16army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'16');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('17army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'17');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('18army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'18');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'19');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('19army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'19');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('20army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'20');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('21army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'21');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'22');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('22army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'22');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('23army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'23');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('24army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'24');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('25army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'25');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('26army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'26');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('27army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'27');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('28army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'28');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'29');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('29army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'29');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('30army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'30');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'31');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('31army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'31');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'32');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('32army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'32');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army1', '2017-02-07 12:25:18.191', NULL , FALSE, 	'TheutatesThunder',	NULL,	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army2',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Druidrider',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army3',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Haeduan',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army4',	'2017-02-07 12:25:18.191',	155, 	FALSE, 	'RamG',	NULL,	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army5',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Trebuchet',	NULL,	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army6',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chieftain',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army7',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Clubswinger',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army8',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Spearman',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army9',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Axeman',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army10',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Scout',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army11',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Paladin',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army12',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'RamT',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army13',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Catapult',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army14',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Chief',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army15',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Legionnaire',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army16',	'2017-02-07 12:25:18.191',	45, 	FALSE, 	'Praetorian',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army17',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Imperian',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army18',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesLegati',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army19',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesImperatoris',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army20',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'EquitesCaesaris',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army21',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'BatteringRam',	NULL,	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army22',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'FireCatapult',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army23',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Senator',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army24',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Phalanx',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army25',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Swordsman',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army26',	'2017-02-07 12:25:18.191',	11, 	FALSE, 	'TeutonicKnight',	NULL, 	'33');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid, village_uuid) VALUES('33army27',	'2017-02-07 12:25:18.191',	NULL, 	FALSE, 	'Pathfinder',	NULL, 	'33');





--
-- Name: alliance alliance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alliance
    ADD CONSTRAINT alliance_pkey PRIMARY KEY (uuid);


--
-- Name: army army_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY army
    ADD CONSTRAINT army_pkey PRIMARY KEY (uuid);


--
-- Name: player player_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT player_pkey PRIMARY KEY (uuid);


--
-- Name: alliance uk_5wwgan6t1qn6l6kx6foe521gl; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alliance
    ADD CONSTRAINT uk_5wwgan6t1qn6l6kx6foe521gl UNIQUE (name);


--
-- Name: users uk_6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users uk_ow0gan20590jrb00upg3va2fn; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_ow0gan20590jrb00upg3va2fn UNIQUE (login);


--
-- Name: village ukamdt6p3cpf7vccj5twoqgnmax; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY village
    ADD CONSTRAINT ukamdt6p3cpf7vccj5twoqgnmax UNIQUE (xcoord, ycoord);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uuid);


--
-- Name: village village_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY village
    ADD CONSTRAINT village_pkey PRIMARY KEY (uuid);


--
-- Name: army delete_arr_village; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY army
    ADD CONSTRAINT delete_arr_village FOREIGN KEY (ar_village_uuid) REFERENCES village(uuid) ON DELETE CASCADE;


--
-- Name: user_roles fkf4ugphx5wrf9ivs5oldgxw8x7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT fkf4ugphx5wrf9ivs5oldgxw8x7 FOREIGN KEY (user_uuid) REFERENCES users(uuid);


--
-- Name: player fkhyk17jmcujmo1992pv7ibty3y; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT fkhyk17jmcujmo1992pv7ibty3y FOREIGN KEY (alliance_id) REFERENCES alliance(uuid);


--
-- Name: users fkk3sk4yinqk2qclcg8lshqq574; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT fkk3sk4yinqk2qclcg8lshqq574 FOREIGN KEY (player_uuid) REFERENCES player(uuid);


--
-- Name: player user_delete; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY player
    ADD CONSTRAINT user_delete FOREIGN KEY (user_uuid) REFERENCES users(uuid) ON DELETE CASCADE;


--
-- Name: village user_delete; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY village
    ADD CONSTRAINT user_delete FOREIGN KEY (player_uuid) REFERENCES player(uuid) ON DELETE CASCADE;


--
-- Name: army village_delete; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY army
    ADD CONSTRAINT village_delete FOREIGN KEY (village_uuid) REFERENCES village(uuid) ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

CREATE TABLE attack
(
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastmodified timestamp without time zone,
    attacktime timestamp without time zone,
    enemy character varying(255) COLLATE pg_catalog."default",
    owner_uuid character varying(255) COLLATE pg_catalog."default",
    village_uuid character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT attack_pkey PRIMARY KEY (uuid),
    CONSTRAINT fkk6cne8p42ydeigc6qv2dnk3ol FOREIGN KEY (owner_uuid)
        REFERENCES public.player (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT village_delete FOREIGN KEY (village_uuid)
        REFERENCES public.village (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE CASCADE
);

ALTER TABLE public.attack  OWNER to postgres;


-- Table: public.passwordresettoken

-- DROP TABLE public.passwordresettoken;

CREATE TABLE passwordresettoken
(
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastmodified timestamp without time zone,
    expirydate timestamp without time zone,
    token character varying(255) COLLATE pg_catalog."default",
    user_id character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT passwordresettoken_pkey PRIMARY KEY (uuid),
    CONSTRAINT fkg3fbfo1tc9louotgq5r940avr FOREIGN KEY (user_id)
        REFERENCES public.users (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

ALTER TABLE public.passwordresettoken
    OWNER to postgres;


-- Table: public.attackarchive

-- DROP TABLE public.attackarchive;

CREATE TABLE attackarchive
(
    uuid character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastmodified timestamp without time zone,
    archivedata text COLLATE pg_catalog."default",
    CONSTRAINT attackarchive_pkey PRIMARY KEY (uuid)
);

ALTER TABLE public.attackarchive   OWNER to postgres;