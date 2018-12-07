/*
Create project 'DEVSTR' with Project manager 'ksusha_m'.
*/
BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('DEVSTR', 32);                       -- 31 = PROJECT.
INSERT ALL
INTO objreference (attrn_id, reference, object_id)
           VALUES (27, 62, comment_id.currval)            -- 26 = PROJECT_USERS; 70 = 'holinkonik'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (27, 63, comment_id.currval)            -- 26 = PROJECT_USERS; 71 = 'robert_t'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (27, 64, comment_id.currval)            -- 26 = PROJECT_USERS; 72 = 'ksusha_m'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (27, 65, comment_id.currval)            -- 26 = PROJECT_USERS; 73 = 'oladushek'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (27, 66, comment_id.currval)            -- 26 = PROJECT_USERS; 74 = 'alex_r'.
INTO attributes (attrn_id, object_id, date_value)
         VALUES (5, comment_id.currval, SYSDATE)          -- 5 = CREATION_DATE.
INTO attributes (attrn_id, object_id, list_value_id)      
         VALUES (7, comment_id.currval, 41)               -- 7 = STATUS; 40 = ACTIVE.
INTO objreference (attrn_id, reference, object_id)
           VALUES (30, comment_id.currval, 62)            -- 29 = USER_PROJECT; 70 = 'holinkonik'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (30, comment_id.currval, 63)            -- 29 = USER_PROJECT; 71 = 'robert_t'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (30, comment_id.currval, 64)            -- 29 = USER_PROJECT; 72 = 'ksusha_m'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (30, comment_id.currval, 65)            -- 29 = USER_PROJECT; 73 = 'oladushek'.
INTO objreference (attrn_id, reference, object_id)
           VALUES (30, comment_id.currval, 66)            -- 29 = USER_PROJECT; 74 = 'alex_r'.
SELECT * FROM dual;
END;
/
COMMIT;
