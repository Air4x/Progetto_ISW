DROP DATABASE IF EXISTS testDB;
CREATE DATABASE testDB;

USE testDB;

-- Tabelle
---- Utenti
CREATE TABLE Utenti (
       ID varchar(36) primary key,
       NOME varchar(100) not null,
       COGNOME varchar(100) not null,
       AFFILIAZIONE varchar(100) not null,
       EMAIL varchar(200) not null,
       PASSWORD varchar(130) not null,
       RUOLO varchar(13) not null,
       constraint CHECK_RUOLO check (RUOLO = 'autore' or RUOLO = 'organizzatore')
);

---- Articoli
CREATE TABLE Articoli (
       ID varchar(36) primary key,
       TITOLO varchar(140) not null,
       ABSTRACT varchar(250) not null,
       STATO varchar(11) not null,
       constraint CHECK_STATO check (STATO = 'sottomesso' or STATO = 'in revisione')
);

---- Autori (Tabella di supporto per relazionare Utenti e Articoli)
CREATE TABLE Autori (
       ID_UTENTE VARCHAR(36),
       ID_ARTICOLO VARCHAR(36),
       constraint PK_AUTORI primary key(ID_UTENTE, ID_ARTICOLO),
       constraint FK_AUTORI_UTENTI foreign key(ID_UTENTE) references Utenti(ID),
       constraint FK_AUTORI_ARTICOLI foreign key(ID_ARTICOLO) references Articoli(ID)
);

---- Conferenze
CREATE TABLE Conferenze (
       ID varchar(36) primary key,
       TITOLO varchar(140),
       DESCRIZIONE varchar(250),
       SCADENZA date not null,
       ORGANIZZATORE varchar(36),
       constraint FK_CONFERENZE_UTENTI foreign key(ORGANIZZATORE) references Utenti(ID)
);

---- Sottomissioni (Tabella di supporto per relazionare Utenti, Articolo e Conferenza)
CREATE TABLE Sottomissioni (
       ID_ARTICOLO varchar(36),
       ID_CONFERENZA varchar(36),
       constraint PK_SOTTOMISSIONI primary key(ID_ARTICOLO, ID_CONFERENZA),
       constraint FK_SOTTOMISSIONI_ARTICOLI foreign key(ID_ARTICOLO) references Articoli(ID),
       constraint FK_SOTTOMISSIONI_CONFERENZE foreign key(ID_CONFERENZA) references Conferenze(ID)
);

---- Revisione (Tabella che implementa le revisioni e i revisori)
CREATE TABLE Revisioni (
       ID varchar(36),
       ID_REVISORE varchar(36),
       ID_ARTICOLO varchar(36),
       PUNTEGGIO number,
       ESITO varchar(9),
       constraint FK_REVISIONI_UTENTI foreign key(ID_REVISORE) references Utenti(ID),
       constraint FK_REVISIONI_ARTICOLI foreign key(ID_ARTICOLO) references Articoli(ID),
       constraint CHEK_ESITO check (ESITO = 'accettato' or ESITO = 'rifiutato')
);

-- Inserimento dati di test
--- Questa parte è stata autogenerata ---
---- Utenti
------ RUOLO = 'autore'
INSERT INTO Utenti VALUES ('31353664-3930-4465-a334-323164633932','Cindy', 'Barrows', 'Federico II', 'test1@test.com', 'autore');
INSERT INTO Utenti VALUES ('38343465-3166-4636-b166-386433333335','Missouri', 'Grady', 'Federico II', 'test2@test.com', 'autore');
INSERT INTO Utenti VALUES ('64636266-6162-4162-a536-346632626632','Frederic', 'Weissnat', 'Federico II', 'test3@test.com', 'autore');
INSERT INTO Utenti VALUES ('65393966-3531-4738-b662-653232663438','Missouri', 'Ledner', 'Federico II', 'test4@test.com', 'autore');
INSERT INTO Utenti VALUES ('66643462-6636-4665-b364-383037373830','Rosendo', 'Smith', 'Federico II', 'test5@test.com', 'autore');
INSERT INTO Utenti VALUES ('65303532-6633-4464-b134-373061616332','Emanuel', 'Monahan', 'Federico II', 'test6@test.com', 'autore');
INSERT INTO Utenti VALUES ('34633933-3933-4832-b533-373065323631','Garth', 'Ledner', 'Federico II', 'test7@test.com', 'autore');
INSERT INTO Utenti VALUES ('35323634-3435-4463-b330-646636643738','Cindy', 'Barrows', 'Federico II', 'test8@test.com', 'autore');
INSERT INTO Utenti VALUES ('32643432-3962-4764-a465-313536313537','Cedrick', 'Steuber', 'Federico II', 'test9@test.com', 'autore');
INSERT INTO Utenti VALUES ('64393265-3937-4261-b563-326137626135','Cindy', 'Bartell', 'Federico II', 'test10@test.com', 'autore');
------ RUOLO = 'organizzatore'
INSERT INTO Utenti VALUES ('32373832-3632-4463-a262-666532653338','Wade', 'Nitzsche', 'Federico II', 'test1@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('65666239-6464-4537-b137-353835643834','Johathan', 'Bauch', 'Federico II', 'test2@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('39653463-3133-4164-b234-333262356365','Garth', 'Bartell', 'Federico II', 'test3@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('33396633-3862-4632-b431-643432383362','Cindy', 'Nitzsche', 'Federico II', 'test4@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('34626163-6130-4834-a334-376239323830','Nicola', 'Monahan', 'Federico II', 'test5@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('63326637-6263-4131-a431-386563313861','Johathan', 'Smith', 'Federico II', 'test6@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('32613962-3730-4662-a639-336563363339','Garth', 'Schowalt', 'Federico II', 'test7@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('34616635-3562-4137-b761-373661353931','Cedrick', 'Monahan', 'Federico II', 'test8@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('61356363-3734-4835-a137-323533376165','Garth', 'Pfeffer', 'Federico II', 'test9@test.com', 'organizzatore');
INSERT INTO Utenti VALUES ('64326466-3534-4961-b966-373932313433','Cedrick', 'Smith', 'Federico II', 'test10@test.com', 'organizzatore');
---- Articoli
INSERT INTO Articoli VALUES ('62366665-3430-4435-b764-316461623265','interdum orci condimentum', 'Test Abstract', 'sottomesso');
INSERT INTO Articoli VALUES ('35393530-6534-4234-b263-346463643536','Mauris congue orci porttitor', 'Test Abstract', 'sottomesso');
INSERT INTO Articoli VALUES ('64383965-3762-4265-a539-316463343833','lectus condimentum eleifend', 'Test Abstract', 'sottomesso');
---- Autori
INSERT INTO Autori VALUES ('64393265-3937-4261-b563-326137626135', '62366665-3430-4435-b764-316461623265');
INSERT INTO Autori VALUES ('34633933-3933-4832-b533-373065323631', '62366665-3430-4435-b764-316461623265');
INSERT INTO Autori VALUES ('66643462-6636-4665-b364-383037373830', '35393530-6534-4234-b263-346463643536');
INSERT INTO Autori VALUES ('34633933-3933-4832-b533-373065323631', '35393530-6534-4234-b263-346463643536');
INSERT INTO Autori VALUES ('66643462-6636-4665-b364-383037373830', '64383965-3762-4265-a539-316463343833');
INSERT INTO Autori VALUES ('65303532-6633-4464-b134-373061616332', '64383965-3762-4265-a539-316463343833');
---- Conferenze
INSERT INTO Conferenze VALUES ('62646636-3962-4238-b630-343665376536', 'TITOLO1', 'DESCRIZIONE1', '2026-01-31', '35343966-6162-4466-a434-656139373138');
INSERT INTO Conferenze VALUES ('61356266-3165-4638-b965-326166616666', 'TITOLO2', 'DESCRIZIONE2', '2026-01-31', '38313862-6161-4230-b936-393835356438');
INSERT INTO Conferenze VALUES ('64326639-6330-4261-a239-373564646163', 'TITOLO3', 'DESCRIZIONE3', '2026-01-31', '31633865-3262-4161-b965-643637633836');
---- sottomissioni
INSERT INTO Sottomissioni VALUES ('62366665-3430-4435-b764-316461623265', '62646636-3962-4238-b630-343665376536');
INSERT INTO Sottomissioni VALUES ('64383965-3762-4265-a539-316463343833', '61356266-3165-4638-b965-326166616666');
INSERT INTO Sottomissioni VALUES ('62366665-3430-4435-b764-316461623265', '64326639-6330-4261-a239-373564646163');
---- Revisioni
INSERT INTO Revisioni VALUES ('63363139-6632-4363-a532-663438343262', '34633933-3933-4832-b533-373065323631', '62366665-3430-4435-b764-316461623265', 0);
INSERT INTO Revisioni VALUES ('30336261-3431-4861-b034-396361393935', '64393265-3937-4261-b563-326137626135', '62366665-3430-4435-b764-316461623265', 0);
INSERT INTO Revisioni VALUES ('66326162-6561-4439-a335-386465353363', '64636266-6162-4162-a536-346632626632', '35393530-6534-4234-b263-346463643536', 0);
INSERT INTO Revisioni VALUES ('37646663-6133-4138-b265-653334366133', '66643462-6636-4665-b364-383037373830', '35393530-6534-4234-b263-346463643536', 0);
