INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,1);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,2);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,3);

-- ListValue
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,4);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,5);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,6);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (31,7);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,5);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,8);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,7);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,9);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,10);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,11);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,12);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32,13);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (32, 14);
 
-- Нереференсные атрибуты сущности "Отзыв" 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (33,5);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (33,15);
 
 -- Нереференсные атрибуты сущности "Відгук на юзера"
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (34,16);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (34,17);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (34,18);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (35,19);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (35,20);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (35,21);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (35,22);
  
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (36,23);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID) -- reference project
 VALUES (36,32,24);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,ATTRN_ID)
 VALUES (36,25);

INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID)
 VALUES (31,34,26);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID)
 VALUES (32,31,27);

-- связь с отзывами
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID)
 VALUES (32,35,26);
 
 -- Референсные атрибуты сущности "Отзыв":
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID)
 VALUES (33,31,28);
 
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID)
 VALUES (34,32,29);

--Reference from user to current project ID
INSERT INTO ATTRTYPE (OBJECT_TYPE_ID,OBJECT_TYPE_ID_REF,ATTRN_ID)
 VALUES (31,32,30);

-- Заполнение листовых значений:
-- Role
INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (4,'PROJECT_MANAGER');
 
INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (4,'TECHNICAL_MANAGER');

INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (4,'GROUP_MANAGER');
 
INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (4, 'DEVELOPER');
 
 -- статус 
INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (7, 'ACTIVE');

INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (7, 'INACTIVE');

--User role ADMIN
INSERT INTO LISTS (ATTRN_ID, VALUE)
 VALUES (4, 'ADMIN');
 
 COMMIT;