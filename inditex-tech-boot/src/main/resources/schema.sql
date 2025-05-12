DROP TABLE IF EXISTS PRICE;
DROP TABLE IF EXISTS BRAND;

CREATE TABLE BRAND (
                       ID INTEGER NOT NULL,
                       GROUP_NAME VARCHAR,
                       CONSTRAINT GROUP_PK PRIMARY KEY (ID)
);

CREATE UNIQUE INDEX BRAND_ID_UINDEX ON BRAND (ID);

CREATE TABLE PRICE (
                       BRAND_ID   INTEGER,
                       START_DATE TIMESTAMP,
                       END_DATE   TIMESTAMP,
                       PRICE_LIST INTEGER,
                       PRODUCT_ID INTEGER,
                       PRIORITY   INTEGER,
                       PRICE      DOUBLE PRECISION,
                       CURR       VARCHAR,
                       CONSTRAINT BRAND_PRICES_FK FOREIGN KEY (BRAND_ID) REFERENCES BRAND(ID)
);
