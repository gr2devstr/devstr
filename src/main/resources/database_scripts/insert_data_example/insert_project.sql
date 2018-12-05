/*
Create project 'DEVSTR' with Project manager 'ksusha_m'.
*/
BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('DEVSTR', 31);                       -- 31 = PROJECT.
INSERT ALL
INTO objreference (attrn_id, reference, object_id)
           VALUES (26, 70, comment_id.currval)            -- 26 = PROJECT_USERS; 70 = 'holinkonik'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (26, 71, comment_id.currval)            -- 26 = PROJECT_USERS; 71 = 'robert_t'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (26, 72, comment_id.currval)            -- 26 = PROJECT_USERS; 72 = 'ksusha_m'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (26, 73, comment_id.currval)            -- 26 = PROJECT_USERS; 73 = 'oladushek'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (26, 74, comment_id.currval)            -- 26 = PROJECT_USERS; 74 = 'alex_r'.
INTO attributes (attrn_id, object_id, date_value)
         VALUES (5, comment_id.currval, SYSDATE)          -- 5 = CREATION_DATE.
INTO attributes (attrn_id, object_id, list_value_id)      
         VALUES (7, comment_id.currval, 40)               -- 7 = STATUS; 40 = ACTIVE.
INTO objreference (attrn_id, reference, object_id)
           VALUES (29, comment_id.currval, 70)            -- 29 = USER_PROJECT; 70 = 'holinkonik'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (29, comment_id.currval, 71)            -- 29 = USER_PROJECT; 71 = 'robert_t'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (29, comment_id.currval, 72)            -- 29 = USER_PROJECT; 72 = 'ksusha_m'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (29, comment_id.currval, 73)            -- 29 = USER_PROJECT; 73 = 'oladushek'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (29, comment_id.currval, 74)            -- 29 = USER_PROJECT; 74 = 'alex_r'.
SELECT * FROM dual;
END;
/
COMMIT;
