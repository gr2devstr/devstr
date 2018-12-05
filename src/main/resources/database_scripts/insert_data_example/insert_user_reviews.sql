DECLARE p_id NUMBER;
BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Review_01', 32);                                  -- 32 = REVIEW.
p_id := comment_id.currval;             
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)  
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (14, comment_id.currval, 'test comment')            -- 14 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (27, comment_id.currval, auth_id)                 -- 27 = REVIEW_AUTHOR.
    SELECT o.object_id AS auth_id
      FROM objects o
     WHERE o.object_type_id = 30 
       AND o.name = 'alex_r';
INSERT INTO objects (name, object_type_id, parent_id)
             VALUES ('Alex_Review', 33, p_id);                          -- 33 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, value)  
             VALUES (15, comment_id.currval, '8')                       -- 15 = JOB_QUALITY.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (16, comment_id.currval, '9')                       -- 15 = JOB_AMOUNT.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (17, comment_id.currval, '9')                       -- 16 = COMMUNICATION.              
    INTO objreference (attrn_id, object_id, reference)  
               VALUES (28, comment_id.currval, proj_id)                 -- 28 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (25, reciever_id, comment_id.currval)             -- 25 = REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           u.object_id AS reciever_id
      FROM objects p, objects u
     WHERE p.object_type_id = 31
       AND p.name = 'DEVSTR'
       AND u.object_type_id = 30
       AND u.name = 'oladushek';
END;
/

DECLARE p_id NUMBER;
BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Review_02', 32);                                  -- 32 = REVIEW.
p_id := comment_id.currval;             
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)  
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (14, comment_id.currval, 'another test comment')    -- 14 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (27, comment_id.currval, auth_id)                 -- 27 = REVIEW_AUTHOR.
    SELECT o.object_id AS auth_id
      FROM objects o
     WHERE o.object_type_id = 30 
       AND o.name = 'robert_t';
INSERT INTO objects (name, object_type_id, parent_id)
             VALUES ('Robert_Review', 33, p_id);                        -- 33 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, value)  
             VALUES (15, comment_id.currval, '9')                       -- 15 = JOB_QUALITY.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (16, comment_id.currval, '7')                       -- 15 = JOB_AMOUNT.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (17, comment_id.currval, '10')                      -- 16 = COMMUNICATION.              
    INTO objreference (attrn_id, object_id, reference)  
               VALUES (28, comment_id.currval, proj_id)                 -- 28 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (25, reciever_id, comment_id.currval)             -- 25 = REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           u.object_id AS reciever_id
      FROM objects p, objects u
     WHERE p.object_type_id = 31
       AND p.name = 'DEVSTR'
       AND u.object_type_id = 30
       AND u.name = 'holinkonik';
END;
/             

DECLARE p_id NUMBER;
BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Review_03', 32);                                  -- 32 = REVIEW.
p_id := comment_id.currval;             
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)  
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (14, comment_id.currval, 'some test comment')       -- 14 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (27, comment_id.currval, auth_id)                 -- 27 = REVIEW_AUTHOR.
    SELECT o.object_id AS auth_id
      FROM objects o
     WHERE o.object_type_id = 30 
       AND o.name = 'holinkonik';
INSERT INTO objects (name, object_type_id, parent_id)
             VALUES ('Nik_Review', 33, p_id);                           -- 33 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, value)  
             VALUES (15, comment_id.currval, '9')                       -- 15 = JOB_QUALITY.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (16, comment_id.currval, '10')                      -- 15 = JOB_AMOUNT.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (17, comment_id.currval, '9')                       -- 16 = COMMUNICATION.              
    INTO objreference (attrn_id, object_id, reference)  
               VALUES (28, comment_id.currval, proj_id)                 -- 28 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (25, reciever_id, comment_id.currval)             -- 25 =  REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           u.object_id AS reciever_id
      FROM objects p, objects u
     WHERE p.object_type_id = 31
       AND p.name = 'DEVSTR'
       AND u.object_type_id = 30
       AND u.name = 'oladushek';
END;
/

DECLARE p_id NUMBER;
BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Review_04', 32);                                  -- 32 = REVIEW.
p_id := comment_id.currval;             
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)  
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (14, comment_id.currval, 'any comment')             -- 14 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (27, comment_id.currval, auth_id)                 -- 27 = REVIEW_AUTHOR.
    SELECT o.object_id AS auth_id
      FROM objects o
     WHERE o.object_type_id = 30 
       AND o.name = 'ksusha_m';
INSERT INTO objects (name, object_type_id, parent_id)
             VALUES ('Ksu_Review', 33, p_id);                           -- 33 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, value)  
             VALUES (15, comment_id.currval, '6')                       -- 15 = JOB_QUALITY.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (16, comment_id.currval, '6')                       -- 15 = JOB_AMOUNT.  
    INTO attributes (attrn_id, object_id, value)  
             VALUES (17, comment_id.currval, '6')                       -- 16 = COMMUNICATION.              
    INTO objreference (attrn_id, object_id, reference)  
               VALUES (28, comment_id.currval, proj_id)                 -- 28 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (25, reciever_id, comment_id.currval)             -- 25 =  REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           u.object_id AS reciever_id
      FROM objects p, objects u
     WHERE p.object_type_id = 31
       AND p.name = 'DEVSTR'
       AND u.object_type_id = 30
       AND u.name = 'robert_t';
END;
/

COMMIT;






