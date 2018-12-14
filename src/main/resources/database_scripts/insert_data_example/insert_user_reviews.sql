BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Alex_Review_01', 34);                             -- 34 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (15, comment_id.currval, 'test comment')            -- 15 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (28, comment_id.currval, auth_id)                 -- 28 = REVIEW_AUTHOR.
    INTO attributes (attrn_id, object_id, value)
             VALUES (16, comment_id.currval, '8')                       -- 16 = JOB_QUALITY.
    INTO attributes (attrn_id, object_id, value)
             VALUES (17, comment_id.currval, '9')                       -- 17 = JOB_AMOUNT.
    INTO attributes (attrn_id, object_id, value)
             VALUES (18, comment_id.currval, '9')                       -- 18 = COMMUNICATION.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (29, comment_id.currval, proj_id)                 -- 29 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (26, reciever_id, comment_id.currval)             -- 26 = REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           o.object_id AS auth_id,
           u.object_id AS reciever_id
      FROM objects p, objects o, objects u
     WHERE p.object_type_id = 32
       AND p.name = 'DEVSTR'
       AND o.object_type_id = 31
       AND o.name = 'alex_r'
       AND u.object_type_id = 31
       AND u.name = 'oladushek';
END;
/

BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Robert_Review_02', 34);                           -- 34 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (15, comment_id.currval, 'another test comment')    -- 15 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (28, comment_id.currval, auth_id)                 -- 28 = REVIEW_AUTHOR.
    INTO attributes (attrn_id, object_id, value)
             VALUES (16, comment_id.currval, '9')                       -- 16 = JOB_QUALITY.
    INTO attributes (attrn_id, object_id, value)
             VALUES (17, comment_id.currval, '7')                       -- 17 = JOB_AMOUNT.
    INTO attributes (attrn_id, object_id, value)
             VALUES (18, comment_id.currval, '10')                      -- 18 = COMMUNICATION.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (29, comment_id.currval, proj_id)                 -- 29 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (26, reciever_id, comment_id.currval)             -- 26 = REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           o.object_id AS auth_id,
           u.object_id AS reciever_id
      FROM objects p, objects o, objects u
     WHERE p.object_type_id = 32
       AND p.name = 'DEVSTR'
       AND o.object_type_id = 31
       AND o.name = 'robert_t'
       AND u.object_type_id = 31
       AND u.name = 'holinkonik';
END;
/

BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Nik_Review_03', 34);                                  -- 34 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (15, comment_id.currval, 'some test comment')       -- 15 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (28, comment_id.currval, auth_id)                 -- 28 = REVIEW_AUTHOR.
    INTO attributes (attrn_id, object_id, value)
             VALUES (16, comment_id.currval, '9')                       -- 16 = JOB_QUALITY.
    INTO attributes (attrn_id, object_id, value)
             VALUES (17, comment_id.currval, '10')                      -- 17 = JOB_AMOUNT.
    INTO attributes (attrn_id, object_id, value)
             VALUES (18, comment_id.currval, '9')                       -- 18 = COMMUNICATION.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (29, comment_id.currval, proj_id)                 -- 29 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (26, reciever_id, comment_id.currval)             -- 26 =  REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           o.object_id AS auth_id,
           u.object_id AS reciever_id
      FROM objects p, objects o, objects u
     WHERE p.object_type_id = 32
       AND p.name = 'DEVSTR'
       AND o.object_type_id = 31
       AND o.name = 'holinkonik'
       AND u.object_type_id = 31
       AND u.name = 'oladushek';
END;
/

BEGIN
INSERT INTO objects (name, object_type_id)
             VALUES ('Ksu_Review_04', 34);                              -- 34 = USERREVIEW.
INSERT ALL
    INTO attributes (attrn_id, object_id, date_value)
             VALUES (5, comment_id.currval, SYSDATE)                    -- 5 = CREATION_DATE.
    INTO attributes (attrn_id, object_id, value)
             VALUES (15, comment_id.currval, 'any comment')             -- 15 = REVIEW_TEXT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (28, comment_id.currval, auth_id)                 -- 28 = REVIEW_AUTHOR.
    INTO attributes (attrn_id, object_id, value)
             VALUES (16, comment_id.currval, '6')                       -- 16 = JOB_QUALITY.
    INTO attributes (attrn_id, object_id, value)
             VALUES (17, comment_id.currval, '6')                       -- 17 = JOB_AMOUNT.
    INTO attributes (attrn_id, object_id, value)
             VALUES (18, comment_id.currval, '6')                       -- 18 = COMMUNICATION.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (29, comment_id.currval, proj_id)                 -- 29 = PROJECT.
    INTO objreference (attrn_id, object_id, reference)
               VALUES (26, reciever_id, comment_id.currval)             -- 26 =  REVIEW REFERENCE.
    SELECT p.object_id AS proj_id,
           o.object_id AS auth_id,
           u.object_id AS reciever_id
      FROM objects p, objects o, objects u
     WHERE p.object_type_id = 32
       AND p.name = 'DEVSTR'
       AND o.object_type_id = 31
       AND o.name = 'ksusha_m'
       AND u.object_type_id = 31
       AND u.name = 'robert_t';
END;
/

COMMIT;






