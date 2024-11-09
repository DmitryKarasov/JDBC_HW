CREATE TABLE customers
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(50)  NOT NULL UNIQUE,
    surname      VARCHAR(100) NOT NULL,
    age          INT          NOT NULL,
    phone_number VARCHAR(50) UNIQUE
);

CREATE TABLE orders
(
    id           SERIAL PRIMARY KEY,
    date         DATE         NOT NULL,
    customer_id  INT          NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    amount       INT          NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customers (id) ON DELETE CASCADE
);

