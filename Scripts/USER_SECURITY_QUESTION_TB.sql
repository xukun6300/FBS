CREATE TABLE USER_SECURITY_QUESTION_TB
( USERSECURITYQUESTIONID NUMBER(20,0) NOT NULL,
  USERID NUMBER(20,0),
  SECURITYQUESTIONID NUMBER(20,0),
  ANSWER VARCHAR2(1000),
  MODIFIEDBY NUMBER(20,0),
  DATEMODIFIED TIMESTAMP,
  CREATEDBY NUMBER(20,0) NOT NULL,
  DATECREATED TIMESTAMP NOT NULL,
  ACTIVE_IND CHAR(1) NOT NULL,
  CONSTRAINT USER_SECURITY_QUESTION_PK PRIMARY KEY (USERSECURITYQUESTIONID)
);

CREATE SEQUENCE USER_SECURITY_QUESTION_SEQ
 START WITH 1
 INCREMENT BY 1
 MINVALUE 1 
 MAXVALUE 9223372036854775807
 NOCYCLE
 CACHE 20 ;

COMMIT;