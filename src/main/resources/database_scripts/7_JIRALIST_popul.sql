INSERT INTO JIRATYPES (VALUE)
 VALUES ('BUG');

INSERT INTO JIRATYPES (VALUE)
 VALUES ('IMPROVEMENT');
 
INSERT INTO JIRATYPES (VALUE)
 VALUES ('TASK');

INSERT INTO JIRATYPES (VALUE)
 VALUES ('NEW_FEATURE');

INSERT INTO JIRATYPES (VALUE)
 VALUES ('DESIGN_BUG');

INSERT INTO JIRATYPES (VALUE)
 VALUES ('EPIC');

-- Issue status
INSERT INTO JIRASTATUSES (VALUE)
 VALUES ('OPEN');

INSERT INTO JIRASTATUSES (VALUE)
 VALUES ('IN_PROGRESS');

INSERT INTO JIRASTATUSES (VALUE)
 VALUES ('READY_FOR_TESTING');
 
INSERT INTO JIRASTATUSES (VALUE)
 VALUES ('CLOSED');
 
INSERT INTO JIRASTATUSES (VALUE)
 VALUES ('REOPEN');

 
-- Issue priority
INSERT INTO JIRAPRIORITIES (VALUE)
 VALUES ('BLOCKER');
 
INSERT INTO JIRAPRIORITIES (VALUE)
 VALUES ('CRITICAL');
 
INSERT INTO JIRAPRIORITIES (VALUE)
 VALUES ('HIGH');
 
INSERT INTO JIRAPRIORITIES (VALUE)
 VALUES ('MEDIUM');
 
INSERT INTO JIRAPRIORITIES (VALUE)
 VALUES ('LOW');
 
INSERT INTO JIRAPRIORITIES (VALUE)
 VALUES ('LOWEST');

COMMIT;