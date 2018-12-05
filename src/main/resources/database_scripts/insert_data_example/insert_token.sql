BEGIN
INSERT INTO OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID) 
            VALUES (COMMENT_ID.nextval, 'TOKEN_1', 35); --35 = token
INSERT ALL 
           INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (22, COMMENT_ID.currval, 'jira token')--22 = token name
            INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (24, COMMENT_ID.currval, 'fvjksdfij124')--24 = encode token
            INTO OBJREFERENCE (ATTRN_ID, REFERENCE, OBJECT_ID) VALUES (23, PROJ_ID, COMMENT_ID.currval)--23 = project id
            SELECT o.object_id AS PROJ_ID FROM OBJECTS o
            WHERE o.NAME = 'DEVSTR'; 
END;

BEGIN
INSERT INTO OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID) 
            VALUES (COMMENT_ID.nextval, 'TOKEN_2', 35);-- 35 = token 
INSERT ALL 
           INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (22, COMMENT_ID.currval, 'git token')--22 = token name
            INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (24, COMMENT_ID.currval, 'asvjksdfsw')--24 = encode token
            INTO OBJREFERENCE (ATTRN_ID, REFERENCE, OBJECT_ID) VALUES (23, PROJ_ID, COMMENT_ID.currval)--23 = project id
            SELECT o.object_id AS PROJ_ID FROM OBJECTS o
            WHERE o.NAME = 'DEVSTR';
END;

COMMIT;

