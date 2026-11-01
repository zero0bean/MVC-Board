\set ON_ERROR_STOP ON

SELECT 'CREATE DATABASE board_db WITH ENCODING ''UTF8'' TEMPLATE template0'
WHERE NOT EXISTS
(
    SELECT 1
    FROM pg_database
    WHERE datname = 'board_db'
) \gexec

SELECT datname AS database_name,
        pg_encoding_to_char(encoding) AS encoding,
        datcollate AS collation,
        datctype AS character_type
FROM pg_database
WHERE datname = 'board_db';