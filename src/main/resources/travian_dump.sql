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
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('9b0c9b2a-0371-480a-916a-7bf40990cdda', '2017-01-06 18:52:08.947', 'morpheus@ukr.net', 'morpheus', '333', null);

-- Leaders

--Alliance Valhala
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-1', '2017-01-06 18:52:08.947', 'neo@ukr.net', 'neo', '111', 'valhala-player-1');

--Alliance Matrix
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-1', '2017-01-06 18:52:08.947', 'borg@ukr.net', 'borg', '111', 'matrix-player-1');

-- Alliance MidleErth
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-1', '2017-01-06 18:52:08.947', 'smith@ukr.net', 'smith', '111', 'midle-player-1');

--Players
--Alliance Valhala
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-2', '2017-01-06 18:52:08.947', 'golf@ukr.net', 'golf', '222', 'valhala-player-2');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-3', '2017-01-06 18:52:08.947', 'emmi@ukr.net', 'emmi', '222', 'valhala-player-3');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-4', '2017-01-06 18:52:08.947', 'alan@ukr.net', 'alan', '222', 'valhala-player-4');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('valhala-user-5', '2017-01-06 18:52:08.947', 'cole@ukr.net', 'cole', '222', 'valhala-player-5');

--Players
--Alliance Matrix
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-2', '2017-01-06 18:52:08.947', 'trinity@ukr.net', 'trinity', '222', 'matrix-player-2');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-3', '2017-01-06 18:52:08.947', 'zig@ukr.net', 'zig', '222', 'matrix-player-3');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-4', '2017-01-06 18:52:08.947', 'amdrea@ukr.net', 'andrea', '222', 'matrix-player-4');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-5', '2017-01-06 18:52:08.947', 'rick@ukr.net', 'rick', '222', 'matrix-player-5');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('matrix-user-6', '2017-01-06 18:52:08.947', 'deril@ukr.net', 'deril', '222', 'matrix-player-6');

--Players
--Alliance MidleEarth
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-2', '2017-01-06 18:52:08.947', 'frodo@ukr.net', 'frodo', '222', 'midle-player-2');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-3', '2017-01-06 18:52:08.947', 'legolas@ukr.net', 'legolas', '222', 'midle-player-3');
INSERT INTO users (uuid, lastmodified, email, login, password, player_uuid) VALUES ('midle-user-4', '2017-01-06 18:52:08.947', 'aragorn@ukr.net', 'aragorn', '222', 'midle-player-4');



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

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f11', '2017-01-06 18:52:08.947', 1 , 'false', 'Legionnaire', '1' , '1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f12', '2017-01-06 18:52:08.947', 100 , 'false', 'Imperian', '2' , '1');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f13', '2017-01-06 18:52:08.947', 40 , 'false', 'EquitesImperatoris', '3' , '1');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f14', '2017-01-06 18:52:08.947', 150 , 'true', 'Praetorian', '1' , '2');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f15', '2017-01-06 18:52:08.947', 15 , 'true', 'Catapult', '3' , '3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f16', '2017-01-06 18:52:08.947', 54 , 'true', 'EquitesLegati', '3' , '3');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f17', '2017-01-06 18:52:08.947', 76 , 'true', 'BatteringRam', '3' , '3');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f18', '2017-01-06 18:52:08.947', 4 , 'true', 'EquitesLegati', '3' , '4');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f19', '2017-01-06 18:52:08.947', 86 , 'true', 'Catapult', '3' , '4');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f20', '2017-01-06 18:52:08.947', 65 , 'true', 'BatteringRam', '3' , '5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f21', '2017-01-06 18:52:08.947', 86 , 'true', 'EquitesLegati', '3' , '5');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f22', '2017-01-06 18:52:08.947', 35 , 'true', 'EquitesLegati', '3' , '5');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f23', '2017-01-06 18:52:08.947', 75 , 'true', 'EquitesLegati', '3' , '6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f24', '2017-01-06 18:52:08.947', 68 , 'true', 'FireCatapult', '3' , '6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f25', '2017-01-06 18:52:08.947', 66 , 'true', 'EquitesLegati', '3' , '6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f26', '2017-01-06 18:52:08.947', 24 , 'true', 'BatteringRam', '3' , '6');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f27', '2017-01-06 18:52:08.947', 456 , 'true', 'EquitesLegati', '3' , '6');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f28', '2017-01-06 18:52:08.947', 56, 'true', 'EquitesLegati', '3' , '7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f29', '2017-01-06 18:52:08.947', 35 , 'true', 'Catapult', '3' , '7');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f30', '2017-01-06 18:52:08.947', 145 , 'true', 'EquitesLegati', '3' , '7');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f31', '2017-01-06 18:52:08.947', 157 , 'true', 'EquitesLegati', '3' , '8');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f32', '2017-01-06 18:52:08.947', 17 , 'true', 'Paladin', '3' , '8');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f33', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '9');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f34', '2017-01-06 18:52:08.947', 15 , 'true', 'FireCatapult', '3' , '10');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f35', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '10');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f36', '2017-01-06 18:52:08.947', 1 , 'false', 'Legionnaire', '1' , '11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f37', '2017-01-06 18:52:08.947', 100 , 'false', 'Imperian', '2' , '11');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f38', '2017-01-06 18:52:08.947', 40 , 'false', 'EquitesImperatoris', '3' , '11');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f39', '2017-01-06 18:52:08.947', 150 , 'true', 'Praetorian', '1' , '12');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f40', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f41', '2017-01-06 18:52:08.947', 15 , 'true', 'Paladin', '3' , '13');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f42', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '13');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f43', '2017-01-06 18:52:08.947', 15 , 'true', 'FireCatapult', '3' , '14');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f44', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '14');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f45', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f46', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesImperatoris', '3' , '15');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f47', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '15');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f48', '2017-01-06 18:52:08.947', 15 , 'true', 'Imperian', '3' , '16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f49', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f50', '2017-01-06 18:52:08.947', 15 , 'true', 'Paladin', '3' , '16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f51', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '16');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f52', '2017-01-06 18:52:08.947', 15 , 'true', 'TheutatesThunder', '3' , '16');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f53', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f54', '2017-01-06 18:52:08.947', 15 , 'true', 'Imperian', '3' , '17');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f55', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '17');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f56', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '18');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f57', '2017-01-06 18:52:08.947', 15 , 'true', 'Imperian', '3' , '18');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f58', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '19');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f59', '2017-01-06 18:52:08.947', 15 , 'true', 'Legionnaire', '3' , '20');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f60', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '20');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f61', '2017-01-06 18:52:08.947', 1 , 'false', 'Legionnaire', '1' , '21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f62', '2017-01-06 18:52:08.947', 100 , 'false', 'Imperian', '2' , '21');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f63', '2017-01-06 18:52:08.947', 40 , 'false', 'EquitesImperatoris', '3' , '21');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f64', '2017-01-06 18:52:08.947', 150 , 'true', 'Praetorian', '1' , '22');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f65', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f66', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '23');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f67', '2017-01-06 18:52:08.947', 15 , 'true', 'Praetorian', '3' , '23');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f68', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '24');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f69', '2017-01-06 18:52:08.947', 15 , 'true', 'TheutatesThunder', '3' , '24');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f70', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f71', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '25');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f72', '2017-01-06 18:52:08.947', 15 , 'true', 'Legionnaire', '3' , '25');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f73', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesImperatoris', '3' , '26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f74', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f75', '2017-01-06 18:52:08.947', 15 , 'true', 'RamG', '3' , '26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f76', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '26');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f77', '2017-01-06 18:52:08.947', 15 , 'true', 'Legionnaire', '3' , '26');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f78', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f79', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '27');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f80', '2017-01-06 18:52:08.947', 15 , 'true', 'Praetorian', '3' , '27');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f81', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '28');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f82', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesImperatoris', '3' , '28');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f83', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '29');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f84', '2017-01-06 18:52:08.947', 15 , 'true', 'Praetorian', '3' , '30');
INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f85', '2017-01-06 18:52:08.947', 15 , 'true', 'EquitesLegati', '3' , '30');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f86', '2017-01-06 18:52:08.947', 40 , 'false', 'EquitesImperatoris', '3' , '31');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f87', '2017-01-06 18:52:08.947', 40 , 'false', 'EquitesImperatoris', '3' , '32');

INSERT INTO army (uuid, lastmodified, count, ownunit, type, ar_village_uuid , village_uuid) VALUES ('7564d81e-b4c7-440f-8f55-ea8938ec0f88', '2017-01-06 18:52:08.947', 40 , 'false', 'EquitesImperatoris', '3' , '33');



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