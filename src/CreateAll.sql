CREATE TABLE IF NOT EXISTS users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       balance DECIMAL(10, 2) DEFAULT 0.0,
                       type VARCHAR(50) NOT NULL,  -- 'admin' or 'regular'
    -- For RegularUser
                       subscription_type VARCHAR(50) DEFAULT 'ORDINARY'
);

CREATE TABLE IF NOT EXISTS  products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          price DECIMAL(10, 2) NOT NULL,
                          quantity INT NOT NULL,
                          description TEXT
);

CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL,
                        order_date TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(50) DEFAULT 'PENDING',
                        FOREIGN KEY (user_id) REFERENCES users(id)
);


CREATE TABLE IF NOT EXISTS  order_items (
                             id SERIAL PRIMARY KEY,
                             order_id INT NOT NULL,
                             product_id INT NOT NULL,
                             quantity INT NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orders(id),
                             FOREIGN KEY (product_id) REFERENCES products(id)
);

