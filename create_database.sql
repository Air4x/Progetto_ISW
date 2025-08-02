DROP DATABASE IF EXISTS testDB;
CREATE DATABASE testDB;

USE testDB;
-- Tabella Utenti
CREATE TABLE Utenti (
       ID VARCHAR(5) PRIMARY KEY,
       NOME VARCHAR(100) NOT NULL,
       COGNOME VARCHAR(100) NOT NULL,
       AFFILIAZIONE VARCHAR(100) NOT NULL,
       EMAIL VARCHAR(200) NOT NULL,
       PASSWORD VARCHAR(130) NOT NULL,
       RUOLO VARCHAR(13) NOT NULL,
       CONSTRAINT CHECK_RUOLO CHECK (RUOLO = 'autore' OR RUOLO = 'organizzatore')	
);

-- Tabella Articoli
CREATE TABLE Articoli (
       ID VARCHAR(5) PRIMARY KEY,
       TITOLO VARCHAR(140) NOT NULL,
       ABSTRACT VARCHAR(250) NOT NULL
);

-- Tabella Conferenze
CREATE TABLE Conferenze (
       ID VARCHAR(5) PRIMARY KEY,
       TITOLO VARCHAR(140) NOT NULL,
       DESCRIZIONE VARCHAR(250) NOT NULL,
       SCADENZA	   DATE NOT NULL
);

-- Tabella Autori
CREATE TABLE Autori (
       id_aut VARCHAR(5),
       id_art VARCHAR(5),
       CONSTRAINT PK_AUTORE PRIMARY KEY(id_aut, id_art),
       CONSTRAINT FK_AUTORI_UTENTI FOREIGN KEY(id_aut) REFERENCES Utenti(ID),
       CONSTRAINT FK_AUTORI_ARTICOLI FOREIGN KEY(id_art) REFERENCES Articoli(ID)
);

-- Tabella Registro
CREATE TABLE Registro (
       id_conf VARCHAR(5),
       id_art VARCHAR(5),
       stato  VARCHAR(13),
       CONSTRAINT PK_REGISTRO PRIMARY KEY(id_conf, id_art),
       CONSTRAINT FK_REGISTRO_CONFERENZE FOREIGN KEY(id_conf) REFERENCES Conferenze(ID),
       CONSTRAINT FK_REGISTRO_ARTICOLI FOREIGN KEY(id_art) REFERENCES Articoli(ID)
);

-- Tabella Revisori
CREATE TABLE Revisori (
       id_art VARCHAR(5),
       id_rev VARCHAR(5),
       CONSTRAINT PK_REVISORI PRIMARY KEY(id_conf, id_art, id_rev),
       CONSTRAINT FK_REVISORI_ARTICOLI FOREIGN KEY(id_art) REFERENCES Articoli(id),
       CONSTRAINT FK_REVISORI_UTENTI FOREIGN KEY (id_rev) REFERENCES Utenti(id)
);

-- Inserimento Utenti
INSERT INTO Utenti
VALUES ('12342', 'Gianmarco', 'Rombanini', 'Seconda Università di Napoli', 'gian.rombanini@outlook.it', 'batuffolino', 'autore');
INSERT INTO Utenti
VALUES ('12354', 'Giuseppe', 'Aceto', 'Università degli Studi di Napoli Federico II', 'giuseppe.aceto@unina.it', '12345678!', 'autore');
INSERT INTO Utenti
VALUES ('11111', 'Domenico', 'Cotroneo', 'Università degli Studi di Napoli Federico II', 'domenico.cotroneo@unina.it', 'virtualizzazione', 'organizzatore');

-- Inserimento Articoli
INSERT INTO Articoli
VALUES ('14141', 'Titolo di prova', 'Un abstract molto corto');
INSERT INTO Articoli
Values ('51512', 'Lonely spacecraft can navigate the stars', 'NASA`s New Horizons probe, which hurtled past Pluto in 2015, demonstrates that it can sail through interstellar space using its onboard camera');
INSERT INTO Articoli
VALUES ('53672', 'Evidence for a sub-jovian planet in the young TWA 7 disk', 'Using the James Webb Space Telescope Mid-Infrared Instrument, a study reports evidence for a direct detection of a cold, sub-Jupiter-mass planet in the disk of the star TWA 7');

-- Inserimento Conferenze
INSERT INTO Conferenze
VALUES ('51232', 'Interstellar UniNa', 'Viaggi interstellari nel mondo universitario');

-- Inserimento Autori
INSERT INTO Autori
VALUES ('12354', '51512');
INSERT INTO Autori
VALUES ('12354', '14141');

INSERT INTO Autori
VALUES ('12342', '51512');


-- Inserimento Registro
INSERT INTO Registro
VALUES ('51232', '51512');
INSERT INTO Registro
VALUES ('51232', '53672');
