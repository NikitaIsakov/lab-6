CREATE TABLE IF NOT EXISTS jewelry_store (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    material VARCHAR(255),
    price float(53) NOT NULL,
    weight float(53) NOT NULL
);