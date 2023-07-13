CREATE TABLE medicamento (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(255) NOT NULL,
                             descricao VARCHAR(255) NOT NULL,
                             preco DECIMAL(10, 2) NOT NULL,
                             precoDesconto DECIMAL(10, 2) NOT NULL,
                             marca VARCHAR(255) NOT NULL,
                             fabricante VARCHAR(255) NOT NULL
);

CREATE TABLE cliente (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL
);

CREATE TABLE medicamento_injetavel (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       tipoAplicacao VARCHAR(255) NOT NULL,
                                       nome VARCHAR(255) NOT NULL,
                                       descricao VARCHAR(255) NOT NULL,
                                       preco DECIMAL(10, 2) NOT NULL,
                                       precoDesconto DECIMAL(10, 2) NOT NULL,
                                       marca VARCHAR(255) NOT NULL,
                                       fabricante VARCHAR(255) NOT NULL
);

CREATE TABLE carrinho (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          cliente_id BIGINT NOT NULL,
                          CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE TABLE carrinho_medicamentos (
                                       carrinho_id BIGINT NOT NULL,
                                       medicamentos_id BIGINT NOT NULL,
                                       CONSTRAINT pk_carrinho_medicamentos PRIMARY KEY (carrinho_id, medicamentos_id),
                                       CONSTRAINT fk_carrinho FOREIGN KEY (carrinho_id) REFERENCES carrinho (id),
                                       CONSTRAINT fk_medicamentos FOREIGN KEY (medicamentos_id) REFERENCES medicamento (id)
);
