SELECT id, username, substring(password from 1 for 10) AS password, role, access FROM users;
