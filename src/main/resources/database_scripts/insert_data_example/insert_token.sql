BEGIN
INSERT INTO OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID) 
            VALUES (COMMENT_ID.nextval, 'TOKEN_1', 36); -- 36 = token
INSERT ALL 
           INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (23, COMMENT_ID.currval, 'jira token')--23 = token name
            INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (25, COMMENT_ID.currval, 'fvjksdfij124')--25 = encode token
            INTO OBJREFERENCE (ATTRN_ID, REFERENCE, OBJECT_ID) VALUES (24, PROJ_ID, COMMENT_ID.currval)--24 = project id
            SELECT o.object_id AS PROJ_ID FROM OBJECTS o
            WHERE o.NAME = 'DEVSTR'; 
END;
/

BEGIN
INSERT INTO OBJECTS (OBJECT_ID, NAME, OBJECT_TYPE_ID) 
            VALUES (COMMENT_ID.nextval, 'TOKEN_2', 36); -- 36 = token 
INSERT ALL 
           INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (23, COMMENT_ID.currval, 'git token')--23 = token name
            INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE) VALUES (25, COMMENT_ID.currval, 'asvjksdfsw')--25 = encode token
            INTO OBJREFERENCE (ATTRN_ID, REFERENCE, OBJECT_ID) VALUES (24, PROJ_ID, COMMENT_ID.currval)--24 = project id
            SELECT o.object_id AS PROJ_ID FROM OBJECTS o
            WHERE o.NAME = 'DEVSTR';
END;
/

COMMIT;

