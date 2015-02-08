
------------------------------------------------SYSDBA------------------------------------------------
CREATE TABLESPACE DARAM
   DATAFILE 'daram.dat' SIZE 10M REUSE 
   AUTOEXTEND ON NEXT 500K MAXSIZE 40M
   LOGGING;

CREATE PROFILE DARAM LIMIT 
   SESSIONS_PER_USER          UNLIMITED 
   CPU_PER_SESSION            UNLIMITED 
   CPU_PER_CALL               3000 
   CONNECT_TIME               45 
   LOGICAL_READS_PER_SESSION  DEFAULT 
   LOGICAL_READS_PER_CALL     1000 
   PRIVATE_SGA                15K
   COMPOSITE_LIMIT            5000000; 
   
CREATE USER daram 
    IDENTIFIED BY daram 
    DEFAULT TABLESPACE DARAM 
    QUOTA 10M ON DARAM 
    TEMPORARY TABLESPACE temp
    QUOTA 5M ON system 
    PROFILE daram;

GRANT CONNECT, RESOURCE TO DARAM;

ALTER SYSTEM SET SEC_CASE_SENSITIVE_LOGON=FALSE;

------------------------------------------------DARAM------------------------------------------------
DROP SEQUENCE MONITOR_SEQ;
CREATE SEQUENCE MONITOR_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE
 MAXVALUE 9999999999;

 DROP SEQUENCE DASH_BOARD_SEQ;
 CREATE SEQUENCE DASH_BOARD_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE
 MAXVALUE 9999999999;
 
DROP TABLE TB_DASH_BOARD CASCADE CONSTRAINTS;
CREATE TABLE TB_DASH_BOARD
( DASH_BOARD_ID NUMBER NOT NULL
, DASH_BOARD_NAME VARCHAR2(100) NOT NULL
, SORT NUMBER NOT NULL
, CREATED_TIME TIMESTAMP NOT NULL
, MODIFIED_TIME TIMESTAMP NOT NULL
, CONSTRAINT DASH_BOARD_PK PRIMARY KEY (DASH_BOARD_ID)
);

DROP TABLE TB_DESIGN_PER_DASH_BOARD CASCADE CONSTRAINTS;
CREATE TABLE TB_DESIGN_PER_DASH_BOARD
( DASH_BOARD_ID NUMBER NOT NULL
, DESIGN_NAME VARCHAR2(100) NOT NULL
, SORT NUMBER NOT NULL
, CREATED_TIME TIMESTAMP NOT NULL
, MODIFIED_TIME TIMESTAMP NOT NULL
, CONSTRAINT DESIGN_PER_DASH_BOARD_PK PRIMARY KEY (DASH_BOARD_ID, DESIGN_NAME)
);

DROP TABLE TB_DESIGN CASCADE CONSTRAINTS;
CREATE TABLE TB_DESIGN
( DESIGN_NAME VARCHAR2(100) NOT NULL
, DENOMINATION VARCHAR2(50)
, X_TAG VARCHAR2(100) NOT NULL
, Y_TAG VARCHAR2(100) NOT NULL
, CREATED_TIME TIMESTAMP NOT NULL
, MODIFIED_TIME TIMESTAMP NOT NULL
, CONSTRAINT DESIGN_PK PRIMARY KEY (DESIGN_NAME)
) ;

DROP TABLE TB_MONITOR CASCADE CONSTRAINTS;
CREATE TABLE TB_MONITOR
( MONITOR_ID NUMBER NOT NULL
, MONITOR_TIME TIMESTAMP NOT NULL
, CREATED_TIME TIMESTAMP NOT NULL
, MODIFIED_TIME TIMESTAMP NOT NULL
, CONSTRAINT MONITOR_PK PRIMARY KEY (MONITOR_ID)
) ;

DROP TABLE TB_MONITOR_DESIGN CASCADE CONSTRAINTS;
CREATE TABLE TB_MONITOR_DESIGN
( MONITOR_ID NUMBER NOT NULL
, DESIGN_NAME VARCHAR2(100) NOT NULL
, CAPTION VARCHAR2(1000) NOT NULL
, CREATED_TIME TIMESTAMP NOT NULL
, MODIFIED_TIME TIMESTAMP NOT NULL
, CONSTRAINT MONITOR_DESIGN_PK PRIMARY KEY (MONITOR_ID, DESIGN_NAME)
, CONSTRAINT MONITOR_DESIGN_FK1 FOREIGN KEY (MONITOR_ID) REFERENCES TB_MONITOR(MONITOR_ID)
, CONSTRAINT MONITOR_DESIGN_FK2 FOREIGN KEY (DESIGN_NAME) REFERENCES TB_DESIGN(DESIGN_NAME)
) ;

DROP TABLE TB_MONITOR_GRAPH CASCADE CONSTRAINTS;
CREATE TABLE TB_MONITOR_GRAPH
( MONITOR_ID NUMBER NOT NULL
, DESIGN_NAME VARCHAR2(100) NOT NULL
, GRAPH_NAME VARCHAR2(100) NOT NULL
, Y_VALUE NUMBER NOT NULL
, CREATED_TIME TIMESTAMP NOT NULL
, MODIFIED_TIME TIMESTAMP NOT NULL
, CONSTRAINT MONITOR_GRAPH_PK PRIMARY KEY (MONITOR_ID, DESIGN_NAME, GRAPH_NAME)
, CONSTRAINT MONITOR_GRAPH_FK1 FOREIGN KEY (MONITOR_ID, DESIGN_NAME) REFERENCES TB_MONITOR_DESIGN(MONITOR_ID, DESIGN_NAME)
) ;
