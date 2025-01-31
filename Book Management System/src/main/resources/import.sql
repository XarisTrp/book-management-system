
-- populate user table
INSERT INTO `pharmacydb`.`app_user` (`firstname`, `lastname`, `username`, `userpassword`, `role`)
VALUES ('Lakis', 'Spiros', 'Spiros', '$2a$12$TWdtQ4zIqOYp4fkWXDe6Xe.mSJHjEArlZ22rHt1mjBGtFgmfKv/Fi', 'librarian');
INSERT INTO `pharmacydb`.`app_user` (`firstname`, `lastname`, `username`, `userpassword`, `role`)
VALUES ('Lakis', 'Spiros', 'Kostas', '$2a$12$TWdtQ4zIqOYp4fkWXDe6Xe.mSJHjEArlZ22rHt1mjBGtFgmfKv/Fi', 'librarian');
INSERT INTO `pharmacydb`.`app_user` (`firstname`, `lastname`, `username`, `userpassword`, `role`)
VALUES ('Lakis', 'Lakis', 'admin', '$2a$12$TWdtQ4zIqOYp4fkWXDe6Xe.mSJHjEArlZ22rHt1mjBGtFgmfKv/Fi', 'Admin');

-- populate medicines
INSERT INTO `pharmacydb`.`book` (`medicineprice`, `medicinename`)
VALUES (23, 'book 1');
INSERT INTO `pharmacydb`.`book` (`medicineprice`, `medicinename`)
VALUES (2, 'book 2');
INSERT INTO `pharmacydb`.`book` (`medicineprice`, `medicinename`)
VALUES (56, 'book 3');

-- populate library table
INSERT INTO `pharmacydb`.`library` (`pharmacyaddress`, `pharmacycity`, `pharmacyname`, `lat`, `lng`, `username`)
VALUES ('lala', 'lala', 'lala', 38.1725, 23.725, 'Spiros');
INSERT INTO `pharmacydb`.`library` (`pharmacyaddress`, `pharmacycity`, `pharmacyname`, `lat`, `lng`, `username`)
VALUES ('lala', 'lala', 'lala', 38.1725, 23.725, 'Kostas');

-- populate stock table
INSERT INTO `pharmacydb`.`stock` (`dateofcount`, `medicineid`, `pharmacyid`, `quantity`)
VALUES ('2025-11-03', 1, 1, 33);
INSERT INTO `pharmacydb`.`stock` (`dateofcount`, `medicineid`, `pharmacyid`, `quantity`)
VALUES ('2025-11-03', 2, 1, 33);
INSERT INTO `pharmacydb`.`stock` (`dateofcount`, `medicineid`, `pharmacyid`, `quantity`)
VALUES ('2025-11-03', 3, 1, 33);
INSERT INTO `pharmacydb`.`stock` (`dateofcount`, `medicineid`, `pharmacyid`, `quantity`)
VALUES ('2025-11-03', 2, 1, 33);
INSERT INTO `pharmacydb`.`stock` (`dateofcount`, `medicineid`, `pharmacyid`, `quantity`)
VALUES ('2025-11-03', 1, 1, 33);

-- populate orders
INSERT INTO `pharmacydb`.`pharmaorder` (`orderdate`, `pharmacyid`, `orderid`)
VALUES ('2024-11-16', 1, 1);
INSERT INTO `pharmacydb`.`pharmaorder` (`orderdate`, `pharmacyid`, `orderid`)
VALUES ('2024-11-16', 2, 2);

INSERT INTO `pharmacydb`.`pharmaorderitem` (`medicineid`, `orderitemid`, `quantity`, `orderid`, `iscompleted`)
VALUES (1, 1, 22, 1, false);
INSERT INTO `pharmacydb`.`pharmaorderitem` (`medicineid`, `orderitemid`, `quantity`, `orderid`, `iscompleted`)
VALUES (2, 2, 22, 1, false);
INSERT INTO `pharmacydb`.`pharmaorderitem` (`medicineid`, `orderitemid`, `quantity`, `orderid`, `iscompleted`)
VALUES (2, 3, 22, 2, false);
