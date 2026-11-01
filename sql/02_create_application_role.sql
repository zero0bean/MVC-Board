\set ON_ERROR_STOP ON

\if :{?board_db_password}
\else
    \echo 'The board_db_password must psql variable is required.'
    \quit 1
\endif

SELECT format(
            'CREATE ROLE board_db LOGIN PASSWORD %L',
            :'board_db_password'
        )
WHERE NOT EXISTS
(
    SELECT 1
    FROM pg_roles
    WHERE rolname = 'board_db'
) \gexec

SELECT format(
            'ALTER ROLE board_db WITH LOGIN PASSWORD %L' ,
            :'board_db_password'
        ) \gexec

GRANT CONNECT ON DATABASE board_db TO board_db;

\connect board_db

CREATE SCHEMA IF NOT EXISTS board;
GRANT USAGE ON SCHEMA board TO board_db;
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA board TO board_db;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA board TO board_db;

ALTER DEFAULT PRIVILEGES IN SCHEMA board
    GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO board_db;
ALTER DEFAULT PRIVILEGES IN SCHEMA board
    GRANT USAGE, SELECT ON SEQUENCES TO board_db;

