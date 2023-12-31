CREATE TABLE marcas(
ID SERIAL NOT NULL PRIMARY KEY,
nome_do_fabricante VARCHAR(150) NOT NULL
);

CREATE TABLE modelos(
ID SERIAL NOT NULL PRIMARY KEY,
nome VARCHAR(50) NOT NULL,
cambio VARCHAR(20) NULL,
combustivel VARCHAR(50) NULL,
potencia INTEGER NULL,
carroceria VARCHAR(50),
portas INTEGER NULL,
id_marcas integer NOT NULL,
FOREIGN KEY (id_marcas) REFERENCES marcas(id)
ON DELETE CASCADE
);

CREATE TABLE veiculos(
ID SERIAL NOT NULL PRIMARY KEY,
renavam BIGINT NOT NULL UNIQUE,
placa CHAR(10) NOT NULL UNIQUE,
ano_fabricacao INTEGER NOT NULL,
chassi CHAR(17) NOT NULL UNIQUE,
id_modelos integer NOT NULL,
cor VARCHAR(20) NULL,
FOREIGN KEY (id_modelos) REFERENCES modelos(id)
ON DELETE CASCADE
);