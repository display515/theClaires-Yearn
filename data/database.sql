PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS yarn_projects_colors;
DROP TABLE IF EXISTS beads_projects_colors;
DROP TABLE IF EXISTS beads_projects;
DROP TABLE IF EXISTS yarn_projects;
DROP TABLE IF EXISTS user_inventory;
DROP TABLE IF EXISTS user_crafts;
DROP TABLE IF EXISTS users;


 CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
); 
INSERT INTO users (username, email, password) VALUES ('kin_crochets', 'kin_crochets@example.com', 'password123' ), ('beadmaker', 'ana@example.com', 'password456'),
 ('crafty_creator', 'crafty_creator@example.com', 'password789'), ('knit_knack', 'knit_knack@example.com', 'password012' ), 
 ('bead_babe', 'bead_babe@example.com', 'password345');

SELECT * FROM users;

CREATE TABLE user_crafts (
    user_id INTEGER NOT NULL,
    craft TEXT NOT NULL CHECK (craft IN ('crocheting', 'beading')),
    PRIMARY KEY (user_id, craft),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO user_crafts (user_id, craft) VALUEs (1, 'beading'), (1, 'crocheting'), (2, 'beading'), (3, 'crocheting'), (4, 'crocheting'), (5, 'beading');

SELECT *  FROM user_crafts;

CREATE TABLE user_inventory (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    item_name TEXT NOT NULL,
    item_color TEXT NOT NULL,
    inventory_type TEXT NOT NULL CHECK (inventory_type IN ('crocheting','beading')),
    grams INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO user_inventory (user_id, item_name,item_color, inventory_type, grams) VALUES 
(1, 'chunky yarn', 'green', 'crocheting', 10),
(1, 'mini chunky yarn', 'cream', 'crocheting', 5),
(2, 'Beads', 'purple', 'beading', 20),
(2, 'Beads','pink', 'beading', 10),
(4, '4ply yarn','green', 'crocheting', 6),
(4, '4ply yarn','white', 'crocheting', 12),
(5, 'Beads','black', 'beading', 25),
(5, 'Beads','white', 'beading', 10);

SELECT * FROM user_inventory
JOIN users ON user_inventory.user_id = users.id;

CREATE TABLE yarn_projects(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    amount_yarn INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    description TEXT,
    status TEXT CHECK (status IN ('in progress', 'completed')) DEFAULT 'in progress',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO yarn_projects (name, amount_yarn, user_id, description, status) VALUES 
('Cozy Blanket', 550, 1, 'A warm and cozy blanket for cold nights', 'in progress'),
('Summer Scarf', 100, 1, 'A light and breezy scarf for summer days', 'in progress'),
('Winter Hat', 150, 4, 'A snug hat for winter warmth', 'in progress'),
('Spring Shawl', 200, 4, 'A beautiful shawl for spring outings', 'in progress');

SELECT * FROM yarn_projects;


CREATE TABLE yarn_projects_colors(
    project_id INTEGER NOT NULL,
    color TEXT NOT NULL,
    grams INTEGER NOT NULL,
    PRIMARY KEY (project_id, color),
    FOREIGN KEY (project_id) REFERENCES yarn_projects(id) ON DELETE CASCADE

);

INSERT INTO yarn_projects_colors (project_id, color, grams) VALUES (1,'cream white',550),
(2, 'light blue', 40), (2, 'pale yellow' , 25) , (2, 'dark blue', 25), (2 , 'white', 10),
(3, 'white', 150), (4, 'pink', 50), (4, 'sage green', 150);


SELECT * FROM yarn_projects_colors;

CREATE TABLE beads_projects(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    amount_bead INTEGER NOT NULL,
    user_id INTEGER NOT NULL,
    description TEXT,
    status TEXT CHECK (status IN ('in progress', 'completed')) DEFAULT 'in progress',
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

INSERT INTO beads_projects (name, amount_bead, user_id, description, status) VALUES
('Spider man head', 50, 2, 'A colorful head for a Spider-Man costume', 'in progress'),
('Kurambit', 75, 2, 'A traditional Kurambit weapon', 'in progress'),
('Cat', 30, 5, 'A cute cat-shaped bead accessory', 'in progress'),
('Toothless', 200, 5, 'A detailed Toothless dragon model', 'in progress');

SELECT * FROM beads_projects;

CREATE TABLE beads_projects_colors(
    project_id INTEGER NOT NULL,
    color TEXT NOT NULL,
    grams INTEGER NOT NULL,
    PRIMARY KEY (project_id, color),
    FOREIGN KEY (project_id) REFERENCES beads_projects(id) ON DELETE CASCADE

);

INSERT INTO beads_projects_colors(project_id,color,grams)  VALUES (1,'red', 30) , (1, 'black', 12) , (1, 'white', 8), (2, 'black', 40),
(2, 'silver',25),(2,'red',10), (3, 'orange', 18),(3,'white',8),(3,'black',4),(4,'green',35),(4,'black',150),(4,'gray',10),(4,'white',5);
SELECT * FROM beads_projects_colors;