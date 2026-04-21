CREATE TABLE beneficiaries (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(1000) NOT NULL,
    category VARCHAR(255) NOT NULL
);

CREATE TABLE rations (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE volunteers (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE help_requests (
    id BIGSERIAL PRIMARY KEY,
    beneficiary_id BIGINT NOT NULL REFERENCES beneficiaries(id),
    ration_id BIGINT NOT NULL REFERENCES rations(id),
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    admin_comment VARCHAR(1000)
);

CREATE TABLE deliveries (
    id BIGSERIAL PRIMARY KEY,
    help_request_id BIGINT NOT NULL UNIQUE REFERENCES help_requests(id),
    volunteer_id BIGINT REFERENCES volunteers(id),
    scheduled_at TIMESTAMP,
    delivered_at TIMESTAMP,
    status VARCHAR(50) NOT NULL,
    result_comment VARCHAR(1000)
);
