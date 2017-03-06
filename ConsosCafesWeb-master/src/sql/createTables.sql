---------------------------------------------------------------
-- script de création des tabless pour l'application ConsosCafe
---------------------------------------------------------------
-- destruction des tables si elles existent
DROP TABLE CONSOS_CAFE;
DROP TABLE PROGRAMMEURS;

-- creation des tables
CREATE TABLE PROGRAMMEURS (
      ID INTEGER NOT NULL,
      NOM VARCHAR(32) NOT NULL,
      PRENOM VARCHAR(32) NOT NULL,
      BUREAU INTEGER,
      PRIMARY KEY (ID)
);

CREATE TABLE CONSOS_CAFE (
   NO_SEMAINE INTEGER NOT NULL,
   PROGRAMMEUR INTEGER NOT NULL,
   NB_TASSES INTEGER NOT NULL,
   PRIMARY KEY (NO_SEMAINE, PROGRAMMEUR)
);

ALTER TABLE CONSOS_CAFE 
    ADD CONSTRAINT fk_ProgConsos
    FOREIGN KEY (PROGRAMMEUR)
    REFERENCES PROGRAMMEURS(ID);

