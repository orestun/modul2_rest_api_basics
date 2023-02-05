CREATE TABLE gift_certificate(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(30),
    description VARCHAR(200),
    price DECIMAL(8,2),
    duration INT,
    create_date DATE,
    last_update_date DATE
    );

CREATE TABLE tag(
    id INT PRIMARY KEY auto_increment,
    name VARCHAR(30)
    );

CREATE TABLE gift_certificate_tag(
    gift_certificate_id INT,
    tag_id INT
);