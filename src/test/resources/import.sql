INSERT INTO measure_type(id, name) VALUES (1, 'gramos');
INSERT INTO measure_type(id, name) VALUES (2, 'litros');

INSERT INTO content(id, value, id_measure_type_fk) VALUES (1, 500, 1);
INSERT INTO content(id, value, id_measure_type_fk) VALUES (2, 750, 1);
INSERT INTO content(id, value, id_measure_type_fk) VALUES (3, 1, 2);

INSERT INTO product(id, brand, current_num_items, description, minimum_stock, name, price, id_content_fk) VALUES (1, 'Diana', 20, 'Arroz Diana x 500 gramos', 10, 'Arroz', 1500, 1);
INSERT INTO product(id, brand, current_num_items, description, minimum_stock, name, price, id_content_fk) VALUES (2, 'Diana', 20, 'Frijol Diana x 500 gramos', 10, 'Frijol', 2500, 1);
INSERT INTO product(id, brand, current_num_items, description, minimum_stock, name, price, id_content_fk) VALUES (3, 'Diana', 20, 'Lenteja Diana x 500 gramos', 10, 'Lenteja', 1100, 1);
INSERT INTO product(id, brand, current_num_items, description, minimum_stock, name, price, id_content_fk) VALUES (4, 'Cristal', 20, 'Agua Cristal x 1 litro', 10, 'Agua', 2500, 2);