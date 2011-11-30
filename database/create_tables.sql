CREATE TABLE Hawker (
  Hawker_ID INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Hawker_Name VARCHAR(127)  NULL  ,
  Address VARCHAR(255)  NOT NULL  ,
  Phone_Number CHAR(15)  NULL  ,
  Hawker_Discount FLOAT(4,2)  NULL  ,
  Billing_Cycle CHAR(1)  NULL  ,
  Alternate_Phone CHAR(15)  NULL    ,
PRIMARY KEY(Hawker_ID));



CREATE TABLE Item (
  Item_Code INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Item_Name VARCHAR(127)  NULL  ,
  Description VARCHAR(255)  NULL  ,
  Item_Life CHAR(1)  NOT NULL   COMMENT 'Frequency of the Item.' ,
  Returnable CHAR(1)  NOT NULL    ,
  Default_Price FLOAT(6,2)  NOT NULL  ,
PRIMARY KEY(Item_Code));




CREATE TABLE Vendor (
  Vendor_ID INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Vendor_Name VARCHAR(127)  NOT NULL  ,
  Address VARCHAR(255)  NULL  ,
  Phone_Number CHAR(15)  NULL  ,
  Vendor_Discount FLOAT(4,2)  NULL  ,
  Billing_Cycle CHAR(1)  NULL  ,
  Alternate_Phone CHAR(15)  NULL    ,
PRIMARY KEY(Vendor_ID));



CREATE TABLE Procurement_Transaction (
  Transaction_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Vendor_ID INT(5) UNSIGNED  NOT NULL  ,
  Date DATETIME  NULL  ,
  Transaction_Type CHAR(1)  NULL  ,
  Total_Amount FLOAT(12,2)  NULL    ,
PRIMARY KEY(Transaction_ID)  ,
INDEX Procurement_Transaction_FKIndex1(Vendor_ID),
  FOREIGN KEY(Vendor_ID)
    REFERENCES Vendor(Vendor_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Payment_History_Sales (
  Payment_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Hawker_ID INT(5) UNSIGNED  NOT NULL  ,
  Date DATETIME  NULL  ,
  Amount FLOAT(12,2)  NULL  ,
  Receipt_Num CHAR(10)  NULL  ,
  Mode CHAR(1)  NULL  ,
  Remarks VARCHAR(255)  NULL    ,
PRIMARY KEY(Payment_ID)  ,
INDEX Payment_History_Sales_FKIndex1(Hawker_ID),
  FOREIGN KEY(Hawker_ID)
    REFERENCES Hawker(Hawker_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Stock (
  Stock_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Date_Of_Publishing DATETIME  NOT NULL  ,
  Quantity INT  NULL    ,
PRIMARY KEY(Stock_ID)  ,
INDEX Stock_FKIndex1(Item_Code),
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Sales_Transaction (
  Transaction_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Hawker_ID INT(5) UNSIGNED  NOT NULL  ,
  Date DATETIME  NULL  ,
  Transaction_Type CHAR(1)  NULL  ,
  Total_Amount FLOAT(12,2)  NULL    ,
PRIMARY KEY(Transaction_ID)  ,
INDEX Sales_Transaction_FKIndex1(Hawker_ID),
  FOREIGN KEY(Hawker_ID)
    REFERENCES Hawker(Hawker_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Billing_Sales (
  Bill_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Hawker_ID INT(5) UNSIGNED  NOT NULL  ,
  Start_Date DATETIME  NULL  ,
  End_Date DATETIME  NULL  ,
  Sales_Total FLOAT(12,2)  NULL    ,
  Balance_Amount FLOAT(12,2)  NULL    ,
PRIMARY KEY(Bill_ID)  ,
INDEX Billing_Sales_FKIndex1(Hawker_ID),
  FOREIGN KEY(Hawker_ID)
    REFERENCES Hawker(Hawker_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Billing_Procurement (
  Bill_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Vendor_ID INT(5) UNSIGNED  NOT NULL  ,
  Start_Date DATETIME  NULL  ,
  End_Date DATETIME  NULL  ,
  Procurement_Total FLOAT(12,2)  NULL    ,
  Balance_Amount FLOAT(12,2)  NULL    ,
PRIMARY KEY(Bill_ID)  ,
INDEX Billing_Procurement_FKIndex1(Vendor_ID),
  FOREIGN KEY(Vendor_ID)
    REFERENCES Vendor(Vendor_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Payment_History_Procurement (
  Payment_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Vendor_ID INT(5) UNSIGNED  NOT NULL  ,
  Date DATETIME  NULL  ,
  Amount FLOAT(12,2)  NULL  ,
  Receipt_Num CHAR(10)  NULL  ,
  Mode CHAR(1)  NULL  ,
  Remarks VARCHAR(255)  NULL    ,
PRIMARY KEY(Payment_ID)  ,
INDEX Payment_History_Procurement_FKIndex1(Vendor_ID),
  FOREIGN KEY(Vendor_ID)
    REFERENCES Vendor(Vendor_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE ST_Details (
  Details_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Transaction_ID INTEGER UNSIGNED  NOT NULL  ,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Date_Of_Publishing DATETIME  NOT NULL  ,
  Quantity INT UNSIGNED  NULL  ,
  Amount FLOAT(12,2)  NULL    ,
  Mrp FLOAT(6,2)  NOT NULL  ,
PRIMARY KEY(Details_ID)  ,
INDEX ST_Details_FKIndex1(Item_Code)  ,
INDEX ST_Details_FKIndex2(Transaction_ID),
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Transaction_ID)
    REFERENCES Sales_Transaction(Transaction_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE PT_Details (
  Details_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Transaction_ID INTEGER UNSIGNED  NOT NULL  ,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Date_Of_Publishing DATETIME  NOT NULL  ,
  Quantity INT UNSIGNED  NULL  ,
  Amount FLOAT(12,2)  NULL    ,
  Mrp FLOAT(6,2)  NOT NULL  ,
PRIMARY KEY(Details_ID)  ,
INDEX PT_Details_FKIndex1(Transaction_ID)  ,
INDEX PT_Details_FKIndex2(Item_Code),
  FOREIGN KEY(Transaction_ID)
    REFERENCES Procurement_Transaction(Transaction_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Item_Vendor (
  Item_Vendor_ID INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Vendor_ID INT(5) UNSIGNED  NOT NULL    ,
PRIMARY KEY(Item_Vendor_ID)  ,
INDEX Item_Vendor_Link_FKIndex2(Vendor_ID)  ,
INDEX Item_Vendor_FKIndex2(Item_Code),
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Vendor_ID)
    REFERENCES Vendor(Vendor_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




