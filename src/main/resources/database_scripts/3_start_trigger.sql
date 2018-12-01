BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE obj_type_id';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -2289 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE attr_name_id';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -2289 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE list_id';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -2289 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP SEQUENCE obj_id';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -2289 THEN
         RAISE;
      END IF;
END;
/


--создаем сиквенсис
create SEQUENCE attr_name_id
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

--создаем триггер сиквенсис для таблицы ATTRNAMES
create or replace trigger Trigger_attr_name
BEFORE INSERT 
on ATTRNAMES
for each row
begin 
select attr_name_id.nextval
into :new.ATTRN_ID
from dual;
end;
/

--создаем сиквенсис
create SEQUENCE obj_id
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

--создаем триггер сиквенсис для таблицы OBJECTS
create or replace trigger Trigger_obj
BEFORE INSERT 
on OBJECTS
for each row
begin 
select obj_id.nextval
into :new.OBJECT_ID
from dual;
end;
/

--создаем сиквенсис
create SEQUENCE obj_type_id
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

--создаем триггер сиквенсис для таблицы OBJTYPE
create or replace trigger Trigger_obj_type
BEFORE INSERT 
on OBJTYPE
for each row
begin 
select obj_type_id.nextval
into :new.OBJECT_TYPE_ID
from dual;
end;
/

--создаем сиквенсис
create SEQUENCE list_id
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

--создаем триггер сиквенсис для таблицы LISTS
create or replace trigger Trigger_list
BEFORE INSERT 
on LISTS
for each row
begin 
select list_id.nextval
into :new.LIST_VALUE_ID
from dual;
end;
/

--создаем триггер сиквенсис для таблицы jiratypes
create or replace trigger Trigger_jiratype
BEFORE INSERT 
on jiratypes
for each row
begin 
select list_id.nextval
into :new.JIRATYPE_ID
from dual;
end;
/

--создаем триггер сиквенсис для таблицы JIRASTATUSES
create or replace trigger Trigger_jirastatus
BEFORE INSERT 
on JIRASTATUSES
for each row
begin 
select list_id.nextval
into :new.JIRASTATUS_ID
from dual;
end;
/

--создаем триггер сиквенсис для таблицы JIRAPRIORITIES 
create or replace trigger Trigger_jirapriorities
BEFORE INSERT 
on JIRAPRIORITIES 
for each row
begin 
select list_id.nextval
into :new.JIRAPRIORITY_ID
from dual;
end;
/