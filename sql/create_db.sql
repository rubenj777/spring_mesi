CREATE TABLE app_user(
   id_user INT AUTO_INCREMENT,
   last_name VARCHAR(50),
   first_name VARCHAR(50),
   username VARCHAR(50),
   password VARCHAR(50),
   email VARCHAR(50),
   subscription_date DATE,
   PRIMARY KEY(id_user)
);

CREATE TABLE product(
   id_product INT AUTO_INCREMENT,
   name VARCHAR(50),
   price DECIMAL(15,2),
   description VARCHAR(2000),
   id_user INT NOT NULL,
   PRIMARY KEY(id_product),
   FOREIGN KEY(id_user) REFERENCES app_user(id_user)
);

CREATE TABLE app_order(
   id_order INT AUTO_INCREMENT,
   order_date DATE,
   total DECIMAL(15,2),
   id_user INT NOT NULL,
   PRIMARY KEY(id_order),
   FOREIGN KEY(id_user) REFERENCES app_user(id_user)
);

CREATE TABLE comment(
   id_comment INT AUTO_INCREMENT,
   comment VARCHAR(2000),
   rating INT,
   id_user INT NOT NULL,
   id_product INT NOT NULL,
   PRIMARY KEY(id_comment),
   FOREIGN KEY(id_user) REFERENCES app_user(id_user),
   FOREIGN KEY(id_product) REFERENCES product(id_product)
);

CREATE TABLE message(
   id_message INT AUTO_INCREMENT,
   content VARCHAR(8000),
   id_user INT NOT NULL,
   id_user_1 INT NOT NULL,
   PRIMARY KEY(id_message),
   FOREIGN KEY(id_user) REFERENCES app_user(id_user),
   FOREIGN KEY(id_user_1) REFERENCES app_user(id_user)
);

CREATE TABLE category(
   id_category INT AUTO_INCREMENT,
   name VARCHAR(50),
   PRIMARY KEY(id_category)
);

CREATE TABLE role(
    id_role INT AUTO_INCREMENT,
    name VARCHAR(50),
    PRIMARY KEY(id_role)
);


CREATE TABLE product_order(
   id_product INT,
   id_order INT,
   PRIMARY KEY(id_product, id_order),
   FOREIGN KEY(id_product) REFERENCES product(id_product),
   FOREIGN KEY(id_order) REFERENCES app_order(id_order)
);

CREATE TABLE user_product(
   id_user INT,
   id_product INT,
   PRIMARY KEY(id_user, id_product),
   FOREIGN KEY(id_user) REFERENCES app_user(id_user),
   FOREIGN KEY(id_product) REFERENCES product(id_product)
);

CREATE TABLE product_category(
   id_product INT,
   id_category INT,
   PRIMARY KEY(id_product, id_category),
   FOREIGN KEY(id_product) REFERENCES product(id_product),
   FOREIGN KEY(id_category) REFERENCES category(id_category)
);

CREATE TABLE user_role(
    id_user INT,
    id_role INT,
    PRIMARY KEY(id_user, id_role),
    FOREIGN KEY(id_user) REFERENCES app_user(id_user),
    FOREIGN KEY(id_role) REFERENCES role(id_role)
);

