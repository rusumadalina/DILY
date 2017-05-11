CREATE SEQUENCE user_id_seq START WITH 1;
CREATE OR REPLACE TRIGGER user_bir 
BEFORE INSERT ON user_table 
FOR EACH ROW

BEGIN
  SELECT user_id_seq.NEXTVAL
  INTO   :new.user_id
  FROM   dual;
END;
/