-- Criar o banco de dados
CREATE DATABASE loja;

-- Usar o banco
USE loja;

-- Criar a tabela
CREATE TABLE funcionario (
  codigo INT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50),
  sobrenome VARCHAR(50),
  idade INT,
  salario DOUBLE
);

-- Inserir dados de exemplo
INSERT INTO funcionario (nome, sobrenome, idade, salario) VALUES ('Luigi', 'Corleone', 28, 2322.39);
INSERT INTO funcionario (nome, sobrenome, idade, salario) VALUES ('Maria', 'Santos', 30, 4000.00);