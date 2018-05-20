CREATE TABLE IF NOT EXISTS empresa (
  idempresa SERIAL,
  nomefantasia VARCHAR(200) NULL,
  cnpj VARCHAR(20) NULL,
  flagativo CHAR(1) NULL,
  PRIMARY KEY (idempresa));

CREATE TABLE IF NOT EXISTS setor (
  idsetor SERIAL,
  idempresa INT NOT NULL,
  nome VARCHAR(50),
  flagativo CHAR(1),
  PRIMARY KEY (idsetor, idempresa));


CREATE TABLE IF NOT EXISTS pessoa (
  idpessoa SERIAL,
  idsetor INT NOT NULL,
  nome VARCHAR(200),
  imagem BYTEA,
  PRIMARY KEY (idpessoa));

CREATE TABLE IF NOT EXISTS usuario (
  idusuario SERIAL,
  idpessoa INT NOT NULL,
  idempresa INT NOT NULL,
  login VARCHAR(45),
  senha VARCHAR(45),
  flagativo CHAR(1),
  tipo CHAR(1) NULL,
  PRIMARY KEY (idusuario));

CREATE TABLE IF NOT EXISTS feedback (
  idfeedback SERIAL,
  idusuarioremetente INT NOT NULL,
  idempresa INT NOT NULL,
  idusuariodestinatario INT NOT NULL,
  tipofeedback CHAR(1),
  dtmovimento timestamp,
  status CHAR(1),
  observacao VARCHAR(255),
  PRIMARY KEY (idfeedback));


CREATE TABLE IF NOT EXISTS feedback_solicitacao (
  idfeedback INT NOT NULL,
  tiposolicitacao VARCHAR(45),
  dtlimite timestamp);


CREATE TABLE IF NOT EXISTS feedback_envio (
  idfeedback INT NOT NULL);

