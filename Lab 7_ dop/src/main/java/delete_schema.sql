
DO $$
DECLARE
  sql text;
BEGIN
  SELECT 'TRUNCATE TABLE '
         || string_agg(format('%I.%I', schemaname, tablename), ', ')
         || ' RESTART IDENTITY CASCADE'
  INTO sql
  FROM pg_tables
  WHERE schemaname NOT IN ('pg_catalog', 'information_schema');

  IF sql IS NOT NULL THEN
    EXECUTE sql;
  END IF;
END$$;
