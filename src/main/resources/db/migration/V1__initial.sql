CREATE TABLE IF NOT EXISTS employee (
    id uuid DEFAULT uuid_generate_v4 (),
    full_name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    birth_day DATE NOT NULL,
    hobbies TEXT,
    PRIMARY KEY (id)
	);