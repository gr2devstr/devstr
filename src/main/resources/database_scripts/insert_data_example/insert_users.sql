/*
List of users : Mykola Holinko, Robert Talabishka, Ksenia Miroshnichenko, Vladushka Oladushka, Alexey Rudyk.
Attributes:
   login : holinkonik, robert_t, ksusha_m, oladushek, alex_r;
   password : 9Str0ng#%passW0rD!6;
   first_name : Mykola, Robert, Ksenia, Vladushka, Alexey;
   last_name : Holinko, Talabishka, Miroshnichenko, Oladushka, Rudyk;
   email : holinkonik@gmail.com, talabishka.r@gmail.com, mirosh.ksu@gmail.com, oladushka@gmail.com, rudykalex@gmail.com;
   user_role : developer, developer, project_manager, technical_manager, group_manager;
*/
--Create USER objects
--Create user 'holinkonik'
BEGIN
    INSERT INTO OBJECTS (OBJECT_TYPE_ID, NAME)      
         VALUES (30, 'holinkonik');                                       -- 30 = USER.
    INSERT ALL
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (1, COMMENT_ID.CURRVAL, 'Mykola')                 -- 1 = FIRST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (2, COMMENT_ID.CURRVAL, 'Holinko')                -- 2 = LAST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (3, COMMENT_ID.CURRVAL, 'holinkonik@gmail.com')   -- 3 = EMAIL.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (4, COMMENT_ID.CURRVAL, 39)                       -- 4 = ROLE; 39 = DEVELOPER.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, DATE_VALUE)
                 VALUES (5, COMMENT_ID.CURRVAL, SYSDATE)                  -- 5 = CREATION_DATE.            
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (6, COMMENT_ID.CURRVAL, '9Str0ng#%passW0rD!6')    -- 6 = PASSWORD.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (7, COMMENT_ID.CURRVAL, 40)                       -- 7 = STATUS; 40 = ACTIVE.
    SELECT * FROM DUAL;
END;
/
--Create user 'robert_t'
BEGIN
    INSERT INTO OBJECTS (OBJECT_TYPE_ID, NAME)      
         VALUES (30, 'robert_t');                                         -- 30 = USER.
    INSERT ALL
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (1, COMMENT_ID.CURRVAL, 'Robert')                 -- 1 = FIRST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (2, COMMENT_ID.CURRVAL, 'Talabishka')             -- 2 = LAST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (3, COMMENT_ID.CURRVAL, 'talabishka.r@gmail.com') -- 3 = EMAIL.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (4, COMMENT_ID.CURRVAL, 39)                       -- 4 = ROLE; 39 = DEVELOPER.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, DATE_VALUE)
                 VALUES (5, COMMENT_ID.CURRVAL, SYSDATE)                  -- 5 = CREATION_DATE.            
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (6, COMMENT_ID.CURRVAL, '9Str0ng#%passW0rD!6')    -- 6 = PASSWORD.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (7, COMMENT_ID.CURRVAL, 40)                       -- 7 = STATUS; 40 = ACTIVE.
    SELECT * FROM DUAL;
END;
/
--Create user 'ksusha_m'
BEGIN
    INSERT INTO OBJECTS (OBJECT_TYPE_ID, NAME)      
         VALUES (30, 'ksusha_m');                                         -- 30 = USER.
    INSERT ALL
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (1, COMMENT_ID.CURRVAL, 'Ksenia')                 -- 1 = FIRST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (2, COMMENT_ID.CURRVAL, 'Miroshnichenko')         -- 2 = LAST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (3, COMMENT_ID.CURRVAL, 'mirosh.ksu@gmail.com')   -- 3 = EMAIL.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (4, COMMENT_ID.CURRVAL, 36)                       -- 4 = ROLE; 36 = PROJECT_MANAGER.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, DATE_VALUE)
                 VALUES (5, COMMENT_ID.CURRVAL, SYSDATE)                  -- 5 = CREATION_DATE.            
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (6, COMMENT_ID.CURRVAL, '9Str0ng#%passW0rD!6')    -- 6 = PASSWORD.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (7, COMMENT_ID.CURRVAL, 40)                       -- 7 = STATUS; 40 = ACTIVE.
    SELECT * FROM DUAL;
END;
/
--Create user 'oladushek'
BEGIN
    INSERT INTO OBJECTS (OBJECT_TYPE_ID, NAME)      
         VALUES (30, 'oladushek');                                        -- 30 = USER.
    INSERT ALL
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (1, COMMENT_ID.CURRVAL, 'Vladushka')              -- 1 = FIRST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (2, COMMENT_ID.CURRVAL, 'Oladushka')              -- 2 = LAST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (3, COMMENT_ID.CURRVAL, 'oladushka@gmail.com')    -- 3 = EMAIL.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (4, COMMENT_ID.CURRVAL, 37)                       -- 4 = ROLE; 37 = TECHNICAL_MANAGER.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, DATE_VALUE)
                 VALUES (5, COMMENT_ID.CURRVAL, SYSDATE)                  -- 5 = CREATION_DATE.            
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (6, COMMENT_ID.CURRVAL, '9Str0ng#%passW0rD!6')    -- 6 = PASSWORD.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (7, COMMENT_ID.CURRVAL, 40)                       -- 7 = STATUS; 40 = ACTIVE.
    SELECT * FROM DUAL;
END;
/   
--Create user 'alex_r'
BEGIN
    INSERT INTO OBJECTS (OBJECT_TYPE_ID, NAME)      
         VALUES (30, 'alex_r');                                           -- 30 = USER.
    INSERT ALL
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (1, COMMENT_ID.CURRVAL, 'Alexey')                 -- 1 = FIRST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (2, COMMENT_ID.CURRVAL, 'Rudyk')                  -- 2 = LAST_NAME.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (3, COMMENT_ID.CURRVAL, 'rudykalex@gmail.com')    -- 3 = EMAIL.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (4, COMMENT_ID.CURRVAL, 38)                       -- 4 = ROLE; 38 = GROUP_MANAGER.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, DATE_VALUE)
                 VALUES (5, COMMENT_ID.CURRVAL, SYSDATE)                  -- 5 = CREATION_DATE.            
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, VALUE)
                 VALUES (6, COMMENT_ID.CURRVAL, '9Str0ng#%passW0rD!6')    -- 6 = PASSWORD.
        INTO ATTRIBUTES (ATTRN_ID, OBJECT_ID, LIST_VALUE_ID)
                 VALUES (7, COMMENT_ID.CURRVAL, 40)                       -- 7 = STATUS; 40 = ACTIVE.
    SELECT * FROM DUAL;
END;
/ 
COMMIT;