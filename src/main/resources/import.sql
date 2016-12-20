--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2016-12-13 16:04:37

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2116 (class 0 OID 125303)
-- Dependencies: 182
-- Data for Name: groups; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO groups(id, name, count) VALUES (1, '11', 20);
INSERT INTO groups(id, name, count) VALUES (2, '12', 22);
INSERT INTO groups(id, name, count) VALUES (3, '13', 5);
INSERT INTO groups(id, name, count) VALUES (4, '14', 7);
INSERT INTO groups(id, name, count) VALUES (5, '21', 10);
INSERT INTO groups(id, name, count) VALUES (6, '22', 9);
INSERT INTO groups(id, name, count) VALUES (7, '23', 5);
INSERT INTO groups(id, name, count) VALUES (8, '24', 4);
INSERT INTO groups(id, name, count) VALUES (9, '31', 12);
INSERT INTO groups(id, name, count) VALUES (10, '32', 7);
INSERT INTO groups(id, name, count) VALUES (11, '33', 6);
INSERT INTO groups(id, name, count) VALUES (12, '34', 7);


--
-- TOC entry 2118 (class 0 OID 125313)
-- Dependencies: 184
-- Data for Name: subjects; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO subjects(id, name, type, course) VALUES (1, 'Algebra', 'LECTURE', 2);
INSERT INTO subjects(id, name, type, course) VALUES (2, 'Algebra', 'PRACTICE', 2);
INSERT INTO subjects(id, name, type, course) VALUES (3, 'Python', 'LECTURE', 2);
INSERT INTO subjects(id, name, type, course) VALUES (4, 'Python', 'LAB', 2);
INSERT INTO subjects(id, name, type, course) VALUES (5, 'Python', 'PRACTICE', 2);
INSERT INTO subjects(id, name, type, course) VALUES (6, 'Philosophy', 'LECTURE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (7, 'Philosophy', 'SEMINAR', 1);
INSERT INTO subjects(id, name, type, course) VALUES (8, 'Algebra', 'LECTURE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (9, 'Algebra', 'PRACTICE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (10, '.Net', 'LECTURE', 3);
INSERT INTO subjects(id, name, type, course) VALUES (11, '.Net', 'PRACTICE', 3);
INSERT INTO subjects(id, name, type, course) VALUES (12, 'C++', 'LECTURE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (13, 'C++', 'PRACTICE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (14, 'C++', 'LAB', 1);
INSERT INTO subjects(id, name, type, course) VALUES (15, 'MA', 'LECTURE', 2);
INSERT INTO subjects(id, name, type, course) VALUES (16, 'MA', 'PRACTICE', 2);
INSERT INTO subjects(id, name, type, course) VALUES (17, 'MA', 'LECTURE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (18, 'MA', 'PRACTICE', 1);
INSERT INTO subjects(id, name, type, course) VALUES (19, 'MA', 'PRACTICE', 3);
INSERT INTO subjects(id, name, type, course) VALUES (20, 'Transformation', 'SEMINAR', 3);


--
-- TOC entry 2119 (class 0 OID 125319)
-- Dependencies: 185
-- Data for Name: group_subject; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO group_subject(group_id, subject_id) VALUES (1, 8);
INSERT INTO group_subject(group_id, subject_id) VALUES (1, 9);
INSERT INTO group_subject(group_id, subject_id) VALUES (1, 6);
INSERT INTO group_subject(group_id, subject_id) VALUES (1, 7);
INSERT INTO group_subject(group_id, subject_id) VALUES (2, 8);
INSERT INTO group_subject(group_id, subject_id) VALUES (2, 9);
INSERT INTO group_subject(group_id, subject_id) VALUES (2, 6);
INSERT INTO group_subject(group_id, subject_id) VALUES (2, 7);
INSERT INTO group_subject(group_id, subject_id) VALUES (3, 8);
INSERT INTO group_subject(group_id, subject_id) VALUES (3, 9);
INSERT INTO group_subject(group_id, subject_id) VALUES (3, 6);
INSERT INTO group_subject(group_id, subject_id) VALUES (3, 7);
INSERT INTO group_subject(group_id, subject_id) VALUES (4, 8);
INSERT INTO group_subject(group_id, subject_id) VALUES (4, 9);
INSERT INTO group_subject(group_id, subject_id) VALUES (4, 6);
INSERT INTO group_subject(group_id, subject_id) VALUES (4, 7);
INSERT INTO group_subject(group_id, subject_id) VALUES (5, 3);
INSERT INTO group_subject(group_id, subject_id) VALUES (5, 4);
INSERT INTO group_subject(group_id, subject_id) VALUES (5, 5);
INSERT INTO group_subject(group_id, subject_id) VALUES (6, 3);
INSERT INTO group_subject(group_id, subject_id) VALUES (6, 4);
INSERT INTO group_subject(group_id, subject_id) VALUES (6, 5);
INSERT INTO group_subject(group_id, subject_id) VALUES (7, 1);
INSERT INTO group_subject(group_id, subject_id) VALUES (7, 2);
INSERT INTO group_subject(group_id, subject_id) VALUES (8, 1);
INSERT INTO group_subject(group_id, subject_id) VALUES (8, 2);
INSERT INTO group_subject(group_id, subject_id) VALUES (9, 10);
INSERT INTO group_subject(group_id, subject_id) VALUES (9, 11);
INSERT INTO group_subject(group_id, subject_id) VALUES (10, 10);
INSERT INTO group_subject(group_id, subject_id) VALUES (10, 11);
INSERT INTO group_subject(group_id, subject_id) VALUES (11, 20);
INSERT INTO group_subject(group_id, subject_id) VALUES (12, 19);