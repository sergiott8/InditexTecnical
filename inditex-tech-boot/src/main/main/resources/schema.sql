
DROP TABLE IF EXISTS PRICE;

DROP TABLE IF EXISTS BRAND;

create table BRAND
(
    ID         INTEGER not null,
    GROUP_NAME CHARACTER VARYING,
    constraint GROUP_PK
        primary key (ID)
);

create unique index BRAND_ID_UINDEX
    on BRAND (ID);


create table PRICE
(
    BRAND_ID   INTEGER,
    START_DATE TIMESTAMP(0),
    END_DATE   TIMESTAMP(0),
    PRICE_LIST INTEGER,
    PRODUCT_ID INTEGER,
    PRIORITY   INTEGER,
    PRICE      DOUBLE PRECISION,
    CURR       CHARACTER VARYING,
    constraint BRAND_PRICES_FK
        foreign key (BRAND_ID) references "BRAND"
);



