/* Заполнение объектных типов */

INSERT INTO OBJTYPE (PARENT_ID,NAME)
 VALUES (NULL,'USER');
 
INSERT INTO OBJTYPE (PARENT_ID,NAME)
 VALUES (NULL,'PROJECT');

-- Отзыв и оценка 
INSERT INTO OBJTYPE (PARENT_ID,NAME)
 VALUES (NULL,'REVIEW');
 
INSERT INTO OBJTYPE (PARENT_ID,NAME)
 VALUES (3,'USERREVIEW');
 
INSERT INTO OBJTYPE (PARENT_ID,NAME)
 VALUES (3,'PROJECTREVIEW');
 
 -- Token
INSERT INTO OBJTYPE (PARENT_ID,NAME)
 VALUES (NULL,'TOKEN');
 
-- Нереференсные атрибуты пользователя
INSERT INTO ATTRNAMES (NAME)
 VALUES ('first_name');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('last_name');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('email');

INSERT INTO ATTRNAMES (NAME)
 VALUES ('role');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('creation_date');

INSERT INTO ATTRNAMES (NAME)
 VALUES ('password');

INSERT INTO ATTRNAMES (NAME)
 VALUES ('status');

-- Нереференсные атрибуты проекта:

INSERT INTO ATTRNAMES (NAME)
 VALUES ('to_date');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('git_login');
 
 INSERT INTO ATTRNAMES (NAME)
 VALUES ('git_pass');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('jira_login');

INSERT INTO ATTRNAMES (NAME)
 VALUES ('jira_pass');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('repository_name');

 -- Нереференсные атрибуты сущности "Отзыв"
INSERT INTO ATTRNAMES (NAME)
 VALUES ('review_text');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('job_quality');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('job_amount');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('communication');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('xp_quality');

INSERT INTO ATTRNAMES (NAME)
 VALUES ('team_spirit');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('organization');
 
 INSERT INTO ATTRNAMES (NAME)
 VALUES ('time_mng');
 
 -- Нереференсные атрибуты сущности "Токен"
INSERT INTO ATTRNAMES (NAME)
 VALUES ('service_name');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('token_project'); -- reference attribute

INSERT INTO ATTRNAMES (NAME)
 VALUES ('token_code');

-- Референсные атрибуты пользователя:
-- связь с отзывами
INSERT INTO ATTRNAMES (NAME)
 VALUES ('reviews');
 
-- Референсные атрибуты проекта:
 -- связь с работничками
INSERT INTO ATTRNAMES (NAME)
 VALUES ('project_users');
 
 -- Референсные атрибуты отзыва
INSERT INTO ATTRNAMES (NAME)
 VALUES ('review_author');
 
INSERT INTO ATTRNAMES (NAME)
 VALUES ('project');
 
 
 COMMIT;