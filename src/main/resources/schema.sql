// VARBINARY because binary cannot hold large value coming from image.

CREATE TABLE IF NOT EXISTS profiles (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        name VARCHAR(255),
                                        content_type VARCHAR(255),
                                        data VARBINARY(max)
);