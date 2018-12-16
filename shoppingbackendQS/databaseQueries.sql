CREATE TABLE category (

	id IDENTITY,
	name VARCHAR(50),
	description VARCHAR(255),
	imager_url VARCHAR(50),
	is_active BOOLEAN,
	
	CONSTRAINT pk_category_id primary key(id)

);