INSERT INTO measure_type(id, name) VALUES (1, 'gramos');
INSERT INTO measure_type(id, name) VALUES (2, 'litros');

INSERT INTO content(id, value, id_measure_type_fk) VALUES (1, 500, 1);
INSERT INTO content(id, value, id_measure_type_fk) VALUES (2, 750, 1);
INSERT INTO content(id, value, id_measure_type_fk) VALUES (3, 1, 2);

INSERT INTO brand(id, name) VALUES (1, 'Diana');
INSERT INTO brand(id, name) VALUES (2, 'Blancox');
INSERT INTO brand(id, name) VALUES (3, 'Cristal');

INSERT INTO product(id, current_num_items, description, minimum_stock, name, price, id_content_fk, id_brand_fk) VALUES (1, 20, 'Arroz Diana x 500 gramos', 10, 'Arroz', 1500, 1, 1);
INSERT INTO product(id, current_num_items, description, minimum_stock, name, price, id_content_fk, id_brand_fk) VALUES (2, 20, 'Frijol Diana x 500 gramos', 10, 'Frijol', 2500, 1, 1);
INSERT INTO product(id, current_num_items, description, minimum_stock, name, price, id_content_fk, id_brand_fk) VALUES (3, 20, 'Lenteja Diana x 500 gramos', 10, 'Lenteja', 1100, 1, 1);
INSERT INTO product(id, current_num_items, description, minimum_stock, name, price, id_content_fk, id_brand_fk) VALUES (4, 20, 'Agua Cristal x 1 litro', 10, 'Agua', 2500, 2, 3);