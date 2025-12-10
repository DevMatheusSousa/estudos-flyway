CREATE TABLE orders(
    id SERIAL PRIMARY KEY,
    user_id BIGINT,
    total DECIMAL(10,2) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP NULL
);