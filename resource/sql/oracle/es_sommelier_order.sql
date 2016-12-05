--创建es_sommelier_order表
BEGIN
	EXECUTE IMMEDIATE 'DROP TRIGGER TIB_ES_SOMMELIER_ORDER';
	EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
	EXECUTE IMMEDIATE 'DROP TABLE ES_SOMMELIER_ORDER';
	EXCEPTION WHEN OTHERS THEN NULL;
END;

BEGIN
	EXECUTE IMMEDIATE 'DROP SEQUENCE S_ES_SOMMELIER_ORDER';
	EXCEPTION WHEN OTHERS THEN NULL;
END;

CREATE TABLE ES_SOMMELIER_ORDER (
  ID NUMBER(30) NOT NULL,
  TYPEID NUMBER(30) DEFAULT NULL,
  SOMMELIER_ID NUMBER(30) DEFAULT NULL,
  MEMID NUMBER(30) DEFAULT NULL,
  STIME NUMBER(30) DEFAULT NULL,
  ETIME NUMBER(30) DEFAULT NULL,
  STATUS NUMBER(1) DEFAULT NULL,
  NAME VARCHAR(200) DEFAULT NULL,
  ADDRESS VARCHAR(300) DEFAULT NULL,
  AREA VARCHAR(100) DEFAULT NULL,
  ZIPCODE VARCHAR(100) DEFAULT NULL,
  MOBILE VARCHAR(200) DEFAULT NULL,
  REMARK VARCHAR(500) DEFAULT NULL,
  PRIMARY KEY (ID)
);

CREATE SEQUENCE S_ES_SOMMELIER_ORDER;

CREATE TRIGGER "TIB_ES_SOMMELIER_ORDER" BEFORE INSERT
	ON ES_SOMMELIER_ORDER FOR EACH ROW
	DECLARE
	INTEGRITY_ERROR  EXCEPTION;
	ERRNO            INTEGER;
	ERRMSG           CHAR(200);
	MAXID						 INTEGER;
	BEGIN
		IF :NEW."ID" IS NULL THEN
			SELECT MAX("ID") INTO MAXID FROM ES_SOMMELIER_ORDER;
			SELECT S_ES_SOMMELIER_ORDER.NEXTVAL INTO :NEW."ID" FROM DUAL;
			IF MAXID>:NEW."ID" THEN
				LOOP
					SELECT S_ES_SOMMELIER_ORDER.NEXTVAL INTO :NEW."ID" FROM DUAL;
					EXIT WHEN MAXID<:NEW."ID";
				END LOOP;
			END IF;
		END IF;
	EXCEPTION
		WHEN INTEGRITY_ERROR THEN
			RAISE_APPLICATION_ERROR(ERRNO, ERRMSG);
END;