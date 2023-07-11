CREATE TABLE app_user(
   idUser INT AUTO_INCREMENT,
   lastName VARCHAR(50),
   firstName VARCHAR(50),
   username VARCHAR(50),
   password VARCHAR(500),
   email VARCHAR(50),
   subscriptionDate DATE,
   PRIMARY KEY(idUser)
);

CREATE TABLE category(
    idCategory INT AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY(idCategory)
);

CREATE TABLE product(
   idProduct INT AUTO_INCREMENT,
   name VARCHAR(50),
   price DECIMAL(15,2),
   description VARCHAR(2000),
   idUser INT NOT NULL,
   idCategory INT NOT NULl,
   PRIMARY KEY(idProduct),
   FOREIGN KEY(idUser) REFERENCES app_user(idUser),
   FOREIGN KEY(idCategory) REFERENCES category(idCategory)
);

CREATE TABLE app_order(
   idOrder INT AUTO_INCREMENT,
   orderDate DATE,
   total DECIMAL(15,2),
   idUser INT NOT NULL,
   PRIMARY KEY(idOrder),
   FOREIGN KEY(idUser) REFERENCES app_user(idUser)
);

CREATE TABLE comment(
   idComment INT AUTO_INCREMENT,
   comment VARCHAR(2000),
   rating INT,
   idUser INT NOT NULL,
   idProduct INT NOT NULL,
   PRIMARY KEY(idComment),
   FOREIGN KEY(idUser) REFERENCES app_user(idUser),
   FOREIGN KEY(idProduct) REFERENCES product(idProduct)
);

CREATE TABLE message(
   idMessage INT AUTO_INCREMENT,
   content VARCHAR(8000),
   idUser INT NOT NULL,
   idUser1 INT NOT NULL,
   PRIMARY KEY(idMessage),
   FOREIGN KEY(idUser) REFERENCES app_user(idUser),
   FOREIGN KEY(idUser1) REFERENCES app_user(idUser)
);

CREATE TABLE role(
    idRole INT AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY(idRole)
);


CREATE TABLE product_order(
   idProduct INT,
   idOrder INT,
   PRIMARY KEY(idProduct, idOrder),
   FOREIGN KEY(idProduct) REFERENCES product(idProduct),
   FOREIGN KEY(idOrder) REFERENCES app_order(idOrder)
);

CREATE TABLE user_product(
   idUser INT,
   idProduct INT,
   PRIMARY KEY(idUser, idProduct),
   FOREIGN KEY(idUser) REFERENCES app_user(idUser),
   FOREIGN KEY(idProduct) REFERENCES product(idProduct)
);

CREATE TABLE user_role(
    idUser INT,
    idRole INT,
    PRIMARY KEY(idUser, idRole),
    FOREIGN KEY(idUser) REFERENCES app_user(idUser),
    FOREIGN KEY(idRole) REFERENCES role(idRole)
);

