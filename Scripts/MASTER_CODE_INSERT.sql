--for salutation
INSERT INTO MASTER_CODETYPE_TB  VALUES (100000, 'SALUTATION','salutation',1,1,null , null, null,null,0,sysdate,'Y');
COMMIT;

INSERT INTO MASTER_CODEVALUE_TB  VALUES (100000, 'Dr','Dr','Dr',1,100000,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100001, 'Mdm','Mdm','Mdm',1,100000,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100002, 'Miss','Miss','Miss',1,100000,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100003, 'Mr','Mr','Mr',1,100000,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100004, 'Mrs','Mrs','Mrs',1,100000,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100005, 'Ms','Ms','Ms',1,100000,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100006, 'Others','Others','Others',1,100000,null , null, null,null,0,sysdate,'Y');
COMMIT;


--for gender
INSERT INTO MASTER_CODETYPE_TB  VALUES (100001, 'GENDER','gender',1,1,null , null, null,null,0,sysdate,'Y');
COMMIT;

INSERT INTO MASTER_CODEVALUE_TB  VALUES (100007, 'Female','Female','Female',1,100001,null , null, null,null,0,sysdate,'Y');
INSERT INTO MASTER_CODEVALUE_TB  VALUES (100008, 'Male','Male','Male',1,100001,null , null, null,null,0,sysdate,'Y');

COMMIT;