CREATE TABLE Alert_Type (
  Alert_Type_Id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Alert_Name VARCHAR(16)  NOT NULL  ,
  Alert_Message VARCHAR(255)  NOT NULL    ,
PRIMARY KEY(Alert_Type_Id));



CREATE TABLE Alert_Config (
  Alert_Config_Id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Alert_Type_Id INTEGER UNSIGNED  NOT NULL  ,
  Alert_Parameters VARCHAR(255)  NULL  ,
  Default_Config CHAR(1)  NULL    ,
PRIMARY KEY(Alert_Config_Id)  ,
INDEX Alert_Config_FKIndex1(Alert_Type_Id),
  FOREIGN KEY(Alert_Type_Id)
    REFERENCES Alert_Type(Alert_Type_Id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Alerts (
  Alert_Num INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Alert_Type_Id INTEGER UNSIGNED  NOT NULL  ,
  Altert_Text VARCHAR(255)  NULL  ,
  Alert_Status CHAR(1)  NULL  ,
  Snooze_Time INTEGER UNSIGNED  NULL  ,
  Alert_Time TIMESTAMP  NULL    ,
PRIMARY KEY(Alert_Num)  ,
INDEX Alerts_FKIndex1(Alert_Type_Id),
  FOREIGN KEY(Alert_Type_Id)
    REFERENCES Alert_Type(Alert_Type_Id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Alert_Association (
  Association_Id INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Alert_Config_Id INTEGER UNSIGNED  NOT NULL  ,
  Item_Id INTEGER UNSIGNED  NULL  ,
  Hawker_Id INTEGER UNSIGNED  NULL  ,
  Vendor_Id INTEGER UNSIGNED  NULL    ,
PRIMARY KEY(Association_Id)  ,
INDEX Alert_Association_FKIndex1(Alert_Config_Id),
  FOREIGN KEY(Alert_Config_Id)
    REFERENCES Alert_Config(Alert_Config_Id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);
