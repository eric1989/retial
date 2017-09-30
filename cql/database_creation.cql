CREATE KEYSPACE IF NOT EXISTS retail WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

CREATE TABLE IF NOT EXISTS retail.product_price (
id timeuuid,
product_id text,
currency_code text,
value double,
PRIMARY KEY (product_id, id)
) WITH CLUSTERING ORDER BY (id DESC);