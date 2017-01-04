--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.2
-- Dumped by pg_dump version 9.5.2

-- Started on 2017-01-04 11:44:18

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;


INSERT INTO users (uuid, lastmodified, email, login, password) VALUES ('6ac0dd7d-1d9c-492c-b217-48cea00925c7', '2017-01-04 00:00:00', 'qqwert@tre.net', 'jack21', '111');
INSERT INTO users (uuid, lastmodified, email, login, password) VALUES ('a1bb9425-dea4-43d5-bdce-d6bf3c36467b', '2017-01-03 00:00:00', 'asdf@qwe.net', 'ralf11', '222');

INSERT INTO user_roles (user_uuid, role) VALUES ('6ac0dd7d-1d9c-492c-b217-48cea00925c7', 'ADMIN')
INSERT INTO user_roles (user_uuid, role) VALUES ('6ac0dd7d-1d9c-492c-b217-48cea00925c7', 'USER')
INSERT INTO user_roles (user_uuid, role) VALUES ('a1bb9425-dea4-43d5-bdce-d6bf3c36467b', 'USER')