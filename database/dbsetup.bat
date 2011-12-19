mysql -h localhost -u bis -pbis bis < delete_data.sql
mysql -h localhost -u bis -pbis bis < drop_tables.sql
mysql -h localhost -u bis -pbis bis < create_tables.sql
mysql -h localhost -u bis -pbis bis < Reference_Date/hawker_script.sql
mysql -h localhost -u bis -pbis bis < Reference_Date/vendor_script.sql
mysql -h localhost -u bis -pbis bis < Reference_Date/item_script.sql

