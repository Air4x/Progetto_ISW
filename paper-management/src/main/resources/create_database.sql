DROP DATABASE IF EXISTS testDB;
CREATE DATABASE testDB;

USE testDB;
-- Tabella Utenti
CREATE TABLE Utenti (
       ID VARCHAR(36) PRIMARY KEY,
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
       ID VARCHAR(36) PRIMARY KEY,
       TITOLO VARCHAR(140) NOT NULL,
       ABSTRACT VARCHAR(250) NOT NULL
);

-- Tabella Conferenze
CREATE TABLE Conferenze (
       ID VARCHAR(36) PRIMARY KEY,
       TITOLO VARCHAR(140) NOT NULL,
       DESCRIZIONE VARCHAR(250) NOT NULL,
       SCADENZA    DATE NOT NULL,
       ORGANIZZATORE VARCHAR(36) NOT NULL,
       CONSTRAINT FK_CONFERENZE_UTENTI FOREIGN KEY(ORGANIZZATORE) REFERENCES Utenti(ID)
);

-- Tabella Autori
CREATE TABLE Autori (
       id_aut VARCHAR(36),
       id_art VARCHAR(36),
       CONSTRAINT PK_AUTORE PRIMARY KEY(id_aut, id_art),
       CONSTRAINT FK_AUTORI_UTENTI FOREIGN KEY(id_aut) REFERENCES Utenti(ID),
       CONSTRAINT FK_AUTORI_ARTICOLI FOREIGN KEY(id_art) REFERENCES Articoli(ID)
);

-- Tabella Registro
CREATE TABLE Registro (
       id_conf VARCHAR(36),
       id_art VARCHAR(36),
       STATUS VARCHAR(14),
       CONSTRAINT PK_REGISTRO PRIMARY KEY(id_conf, id_art),
       CONSTRAINT FK_REGISTRO_CONFERENZE FOREIGN KEY(id_conf) REFERENCES Conferenze(ID),
       CONSTRAINT FK_REGISTRO_ARTICOLI FOREIGN KEY(id_art) REFERENCES Articoli(ID),
       CONSTRAINT CHECK_STATUS CHECK (STATUS='sottomesso' OR STATUS='in_revisione')
);

-- Tabella Revisori
CREATE TABLE Revisori (
       id_art VARCHAR(36),
       id_rev VARCHAR(36),
       CONSTRAINT PK_REVISORI PRIMARY KEY(id_art, id_rev),
       CONSTRAINT FK_REVISORI_ARTICOLI FOREIGN KEY(id_art) REFERENCES Articoli(id),
       CONSTRAINT FK_REVISORI_UTENTI FOREIGN KEY (id_rev) REFERENCES Utenti(id)
);

-- Inserimento Utenti
INSERT INTO Utenti
VALUES ('9c388e06-3c9e-43bd-9327-acbffed869d3', 'Gianmarco', 'Rombanini', 'Seconda Università di Napoli', 'gian.rombanini@outlook.it', 'batuffolino', 'autore');
INSERT INTO Utenti
VALUES ('3a9e468f-ff6b-4a84-bbc0-fb3f9e9c5024', 'Giuseppe', 'Aceto', 'Università degli Studi di Napoli Federico II', 'toolvpstaiscal@gmail.com', '12345678!', 'autore');
INSERT INTO Utenti
VALUES ('ee719226-43d5-4bfc-bf46-3e409bbbf425', 'Domenico', 'Cotroneo', 'Università degli Studi di Napoli Federico II', 'domenico.cotroneo@unina.it', 'virtualizzazione', 'organizzatore');

-- Inserimento Articoli
INSERT INTO Articoli
VALUES ('7cf18f80-b41a-42f0-af41-fbb9b60303ab', 'Titolo di prova', 'Un abstract molto corto');
INSERT INTO Articoli
Values ('2e24cd58-a3d7-4057-a1b8-ce9a24669cea', 'Lonely spacecraft can navigate the stars', 'NASA`s New Horizons probe, which hurtled past Pluto in 2015, demonstrates that it can sail through interstellar space using its onboard camera');
INSERT INTO Articoli
VALUES ('90d0f680-b4c1-416f-903c-3d2976025efb', 'Evidence for a sub-jovian planet in the young TWA 7 disk', 'Using the James Webb Space Telescope Mid-Infrared Instrument, a study reports evidence for a direct detection of a cold, sub-Jupiter-mass planet in the disk of the star TWA 7');

-- Inserimento Conferenze
INSERT INTO Conferenze
VALUES ('6279c9e1-b121-4c7a-a196-7a43b57fc16d', 'Interstellar UniNa', 'Viaggi interstellari nel mondo universitario', '2025-12-31', 'ee719226-43d5-4bfc-bf46-3e409bbbf425');
INSERT INTO Conferenze
VALUES ('6279c9e1-b121-4c7a-a196-7a43b57fc03d', 'Nintendo', 'Perche nintendo dovrebbe essere un monopolio', '2024-12-31', 'ee719226-43d5-4bfc-bf46-3e409bbbf425');
INSERT INTO Conferenze
VALUES ('611f4dff-28c2-42b9-982d-762a3e9e2b3a', 'Nvidia', 'Perche nvidia dovrebbe essere un monopolio', '2025-08-26', 'ee719226-43d5-4bfc-bf46-3e409bbbf425');

-- Inserimento Autori
INSERT INTO Autori
VALUES ('9c388e06-3c9e-43bd-9327-acbffed869d3', '2e24cd58-a3d7-4057-a1b8-ce9a24669cea');
INSERT INTO Autori
VALUES ('9c388e06-3c9e-43bd-9327-acbffed869d3', '7cf18f80-b41a-42f0-af41-fbb9b60303ab');
INSERT INTO Autori
VALUES ('3a9e468f-ff6b-4a84-bbc0-fb3f9e9c5024', '7cf18f80-b41a-42f0-af41-fbb9b60303ab');
-- L'istruzione seguente è stata rimossa perché l'ID dell'utente non esiste:
-- INSERT INTO Autori VALUES ('ee719226-43d5-4bfc-bf46-3e409bbbf425', '90d0f680-b4c1-416f-903c-3d2976025efb');


-- Inserimento Registro
INSERT INTO Registro
VALUES ('6279c9e1-b121-4c7a-a196-7a43b57fc16d', '2e24cd58-a3d7-4057-a1b8-ce9a24669cea', 'sottomesso');
-- La riga seguente è stata rimossa perché era un duplicato:
-- INSERT INTO Registro VALUES ('6279c9e1-b121-4c7a-a196-7a43b57fc16d', '2e24cd58-a3d7-4057-a1b8-ce9a24669cea');
