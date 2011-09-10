CREATE TABLE Hawker (
  Hawker_ID INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Hawker_Name VARCHAR  NULL  ,
  Address VARCHAR  NOT NULL  ,
  Phone_Number CHAR(15)  NULL  ,
  Hawker_Discount FLOAT(4,2)  NULL  ,
  Billing_Cycle CHAR(1)  NULL  ,
  Alternate_Phone CHAR(15)  NULL    ,
PRIMARY KEY(Hawker_ID));



CREATE TABLE Item (
  Item_Code INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Item_Name VARCHAR  NULL  ,
  Description VARCHAR  NULL  ,
  Item_Life CHAR(1)  NOT NULL   COMMENT 'Frequency of the Item.' ,
  Returnable CHAR(1)  NOT NULL    ,
PRIMARY KEY(Item_Code));




CREATE TABLE Vendor (
  Vendor_ID INT(5) UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Vendor_Name VARCHAR  NOT NULL  ,
  Address VARCHAR  NULL  ,
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
  Remarks VARCHAR  NULL    ,
PRIMARY KEY(Payment_ID)  ,
INDEX Payment_History_Sales_FKIndex1(Hawker_ID),
  FOREIGN KEY(Hawker_ID)
    REFERENCES Hawker(Hawker_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Stock (
  Date_Of_Publishing DATETIME  NOT NULL  ,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Quantity INT  NULL    ,
PRIMARY KEY(Date_Of_Publishing, Item_Code),
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



CREATE TABLE Item_Price (
  Price_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Price FLOAT(6,2)  NOT NULL  ,
  Start_Date DATETIME  NOT NULL  ,
  End_Time DATETIME  NULL    ,
PRIMARY KEY(Price_ID),
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Billing_Sales (
  Bill_ID INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Hawker_ID INT(5) UNSIGNED  NOT NULL  ,
  Start_Date DATETIME  NULL  ,
  End_Date DATETIME  NULL  ,
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
  Remarks VARCHAR  NULL    ,
PRIMARY KEY(Payment_ID)  ,
INDEX Payment_History_Procurement_FKIndex1(Vendor_ID),
  FOREIGN KEY(Vendor_ID)
    REFERENCES Vendor(Vendor_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE ST_Details (
  Date_Of_Publishing DATETIME  NOT NULL  ,
  Transaction_ID INTEGER UNSIGNED  NOT NULL  ,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Quantity INT UNSIGNED  NULL  ,
  Amount FLOAT(12,2)  NULL    ,
PRIMARY KEY(Date_Of_Publishing, Transaction_ID, Item_Code)  ,
INDEX ST_Details_FKIndex1(Transaction_ID),
  FOREIGN KEY(Transaction_ID)
    REFERENCES Sales_Transaction(Transaction_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE PT_Details (
  Date_Of_Publishing DATETIME  NOT NULL  ,
  Transaction_ID INTEGER UNSIGNED  NOT NULL  ,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Quantity INT UNSIGNED  NULL  ,
  Amount FLOAT(12,2)  NULL    ,
PRIMARY KEY(Date_Of_Publishing, Transaction_ID, Item_Code)  ,
INDEX PT_Details_FKIndex1(Transaction_ID),
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Transaction_ID)
    REFERENCES Procurement_Transaction(Transaction_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Item_Vendor (
  Item_Vendor_ID INT(5))  NOT NULL   AUTO_INCREMENT,
  Item_Code INT(5) UNSIGNED  NOT NULL  ,
  Vendor_ID INT(5) UNSIGNED  NOT NULL    ,
PRIMARY KEY(Item_Vendor_ID)  ,
INDEX Item_Vendor_Link_FKIndex2(Vendor_ID),
  FOREIGN KEY(Item_Code)
    REFERENCES Item(Item_Code)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(Vendor_ID)
    REFERENCES Vendor(Vendor_ID)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




