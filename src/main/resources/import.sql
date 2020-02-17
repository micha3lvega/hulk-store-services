INSERT INTO provider (name) VALUES('Proveedor uno');

INSERT INTO measurement_unit (name) VALUES('Bolsa');
INSERT INTO measurement_unit (name) VALUES('Botella');
INSERT INTO measurement_unit (name) VALUES('Caja');
INSERT INTO measurement_unit (name) VALUES('Lata');
INSERT INTO measurement_unit (name) VALUES('Litro');

INSERT INTO PRODUCT ( CREATED_AT , MEASUREMENT_UNIT_ID , NAME , PROVIDER_ID , UNITE_PRICE , UPDATED_AT ) VALUES (now(),1,'Producto uno', 1, '10000',now())