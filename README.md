# ts
SELECT * FROM livros_bd.usuario;

CREATE TABLE `db_produto`.`vendedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `sobrenome` VARCHAR(45) NOT NULL,
  `data_entrada` DATE NOT NULL,
  `habilitado` boolean NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `db_produto`.`venda` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qtde` INT NOT NULL,
  `data` DATE NOT NULL,
  `valor_total` double NOT NULL,
  PRIMARY KEY (`id`));
  

  CREATE TABLE `db_produto`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `qtde_estoque` INT NOT NULL,
  `valor_unitario` double NOT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `db_produto`.`vendaproduto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `qtde` INT NOT NULL,
  `valor_unitario` double NOT NULL,
  `valor_total_produto` double NOT NULL,
	FOREIGN KEY (`id`)
    REFERENCES `db_produto`.`venda` (`id`),
    
    
	FOREIGN KEY (`id`)
    REFERENCES `db_produto`.`produto` (`id`),
    
  PRIMARY KEY (`id`));
  
  
  
