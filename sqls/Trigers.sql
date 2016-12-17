CREATE OR REPLACE TRIGGER ROLES_DATA_INS
  AFTER INSERT OR UPDATE OR DELETE
  ON ROLES_DATA
  FOR EACH ROW
  DECLARE
  ResultString VARCHAR2(255);  
  ActionStr VARCHAR2(255);

  BEGIN

  IF INSERTING THEN
  ResultString := 'INSERTING:'||:NEW.ID||' '||:NEW.ROLENAME;
  ActionStr:='INSERTING';
  END IF;

  IF deleting THEN
  ResultString := 'DELETING:'||:OLD.ID||' '||:OLD.ROLENAME;
  ActionStr:='DELETING';
  END IF;

  IF updating THEN
  ResultString := 'REPLACE:'||:OLD.ID||' '||:OLD.ROLENAME ||' with '||:NEW.ID||' '||:NEW.ROLENAME;
  ActionStr:='UPDATE';
  END IF;

 

  INSERT INTO ACTION_LOG (ACTION, TARGET, ActionDesc, ActionDate)
  VALUES(ActionStr,'ROLES_DATA',ResultString, SYSDATE);

  END;
/
CREATE OR REPLACE TRIGGER USERS_DATA_INS
  BEFORE INSERT OR UPDATE OR DELETE
  ON USERS_DATA
  FOR EACH ROW
  DECLARE
  ResultString VARCHAR2(255);  
  ActionStr VARCHAR2(255);

  UserStr VARCHAR2(255);

  BEGIN

  IF INSERTING THEN
  ResultString := 'INSERTING:'||:NEW.ID||' '||:NEW.LOGIN||' '||:NEW.HASHPWD||' '||:NEW.SALT;
  ActionStr:='INSERTING';
  UserStr:= :NEW.LOGIN;
  END IF;

  IF deleting THEN
  ResultString := 'DELETING:'||:OLD.ID||' '||:OLD.LOGIN||' '||:OLD.HASHPWD||' '||:OLD.SALT;
  ActionStr:='DELETING';
  UserStr:= :OLD.LOGIN;
  END IF;

  IF updating THEN
  ResultString := 'REPLACE:'||:OLD.ID||' '||:OLD.LOGIN||' '||:OLD.HASHPWD||' '||:OLD.SALT ||' with '||:NEW.ID||' '||:NEW.LOGIN||' '||:NEW.HASHPWD||' '||:NEW.SALT;
  ActionStr:='UPDATE';
  UserStr:= :NEW.LOGIN;
  END IF;

 

  INSERT INTO ACTION_LOG (ACTION, TARGET,ACTIONUSER, ActionDesc, ActionDate)
  VALUES(ActionStr,'USERS_DATA', UserStr, ResultString, SYSDATE);

  END;
/
CREATE OR REPLACE TRIGGER USERS_ROLES_INS
  BEFORE INSERT OR UPDATE OR DELETE
  ON USERS_ROLES
  FOR EACH ROW
  DECLARE
  ResultString VARCHAR2(255);  
  ActionStr VARCHAR2(255);

  USERNAME VARCHAR2(255);
  ROLENAME VARCHAR2(255);
  BEGIN



  IF INSERTING THEN
        SELECT Login
      INTO USERNAME
      FROM USERS_DATA ud
    WHERE ud.ID = :NEW.USERID;

     SELECT rd.ROLENAME
      INTO ROLENAME
      FROM ROLES_DATA rd
    WHERE rd.ID = :NEW.ROLEID;
  ResultString := 'INSERTING:'||USERNAME||' '||ROLENAME;
  ActionStr:='INSERTING';
  END IF;

  IF deleting THEN
        SELECT Login
      INTO USERNAME
      FROM USERS_DATA ud
    WHERE ud.ID = :OLD.USERID;

     SELECT rd.ROLENAME
      INTO ROLENAME
      FROM ROLES_DATA rd
    WHERE rd.ID = :OLD.ROLEID;
  ResultString := 'DELETING:'||USERNAME||' '||ROLENAME;
  ActionStr:='DELETING';
  END IF;

  IF updating THEN
        SELECT Login
      INTO USERNAME
      FROM USERS_DATA ud
    WHERE ud.ID = :NEW.USERID;

     SELECT rd.ROLENAME
      INTO ROLENAME
      FROM ROLES_DATA rd
    WHERE rd.ID = :NEW.ROLEID;
  ResultString := 'REPLACE. This one is new:'||USERNAME||' '||ROLENAME;
  ActionStr:='UPDATE';
  END IF;

 

  INSERT INTO ACTION_LOG (ACTION, TARGET, ACTIONUSER, ActionDesc, ActionDate)
  VALUES(ActionStr,'USERS_ROLES',USERNAME, ResultString, SYSDATE);

  END;

                             