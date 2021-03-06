BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE COMMITCLASSES CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE COMMITS CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE ISSUES CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE JIRAPRIORITIES CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE JIRASTATUSES CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE JIRATYPES CASCADE CONSTRAINTS';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN
         RAISE;
      END IF;
END;
/
CREATE TABLE JIRATYPES
(
    JIRATYPE_ID                   NUMBER(10),
	VALUE                         VARCHAR2(50) NOT NULL,
	CONSTRAINT CON_JIRATYPE_ID PRIMARY KEY (JIRATYPE_ID)
);

CREATE TABLE JIRASTATUSES
(
    JIRASTATUS_ID                 NUMBER(10),
	VALUE                         VARCHAR2(50) NOT NULL,
	CONSTRAINT CON_JIRASTATUS_ID PRIMARY KEY (JIRASTATUS_ID)
);

CREATE TABLE JIRAPRIORITIES
(
    JIRAPRIORITY_ID               NUMBER(10),
	VALUE                         VARCHAR2(50) NOT NULL,
	CONSTRAINT CON_JIRAPRIORITY_ID PRIMARY KEY (JIRAPRIORITY_ID)
);

CREATE TABLE ISSUES 
(
    ISSUE_ID      		       NUMBER(20),
    ISSUE_KEY 		           VARCHAR2(200) NOT NULL UNIQUE,
	PROJECT_ID                 NUMBER(20) NOT NULL,
	TYPE_ID 	               NUMBER(10) NOT NULL,
	STATUS_ID                  NUMBER(10) NOT NULL,
	PRIORITY_ID                NUMBER(10) NOT NULL,
	START_DATE      		   DATE NOT NULL,
	DUE_DATE      		       DATE NOT NULL,
	USER_ID                    NUMBER(20) NOT NULL,
	REPORTER_ID      		   NUMBER(20) NOT NULL,
	IS_OVERDATED               NUMBER(1),
    CONSTRAINT CON_ISSUE_ID PRIMARY KEY (ISSUE_ID),
	CONSTRAINT CON_ISSUSER_ID FOREIGN KEY (USER_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
	CONSTRAINT CON_ISSREP_ID FOREIGN KEY (REPORTER_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
	CONSTRAINT CON_PROJECT_ID FOREIGN KEY (PROJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
	CONSTRAINT CON_TYPE_ID FOREIGN KEY (TYPE_ID) REFERENCES JIRATYPES (JIRATYPE_ID),
	CONSTRAINT CON_STATUS_ID FOREIGN KEY (STATUS_ID) REFERENCES JIRASTATUSES (JIRASTATUS_ID),
	CONSTRAINT CON_PRIORITY_ID FOREIGN KEY (PRIORITY_ID) REFERENCES JIRAPRIORITIES (JIRAPRIORITY_ID)
);

CREATE TABLE COMMITS 
(
    COMMIT_ID      		          NUMBER(20),
    AUTHOR_ID 		              NUMBER(20) NOT NULL,
	SHA 	                      VARCHAR2(200) NOT NULL UNIQUE,
	PUBLICATION_DATE      	      DATE NOT NULL,
	STATUS_OF_BUILD               NUMBER(1) NOT NULL,
	ISSUE_ID                      NUMBER(10) NOT NULL,
    CONSTRAINT CON_COMMIT_ID PRIMARY KEY (COMMIT_ID),
	CONSTRAINT CON_COMMISSUE_ID FOREIGN KEY (ISSUE_ID) REFERENCES ISSUES (ISSUE_ID),
	CONSTRAINT CON_AUTHORCOM_ID FOREIGN KEY (AUTHOR_ID) REFERENCES OBJECTS (OBJECT_ID)
);

CREATE TABLE COMMITCLASSES 
(
    COMMIT_CLASS_ID      		  NUMBER(20),
    NAME 		                  VARCHAR2(200) NOT NULL,
	NUMBER_OF_LINES_ADDED 	      NUMBER(20) NOT NULL,
	NUMBER_OF_LINES_CHANGED       NUMBER(20) NOT NULL,
	NUMBER_OF_LINES_DELETED       NUMBER(20) NOT NULL,
	COMMIT_ID                     NUMBER(20) NOT NULL,
    CONSTRAINT CON_COMMCL_ID PRIMARY KEY (COMMIT_CLASS_ID),
	CONSTRAINT CON_COMMITLINK_ID FOREIGN KEY (COMMIT_ID) REFERENCES COMMITS (COMMIT_ID)
);