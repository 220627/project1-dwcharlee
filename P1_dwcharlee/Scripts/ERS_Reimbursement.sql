CREATE TABLE ers_reimbursement(
	reimb_id serial,
	reimb_amount int,
	reimb_submitted timestamp,
	reimb_author int,
	reimb_resolver int,
	reimb_status_id int,
	reimb_type_id int,
	PRIMARY KEY (reimb_id),
	FOREIGN KEY (reimb_author) REFERENCES ers_users (ers_users_id),
	FOREIGN KEY (reimb_resolver) REFERENCES ers_users (user_role_id),
	FOREIGN KEY (reimb_status_id) REFERENCES ers_users (reimb_status_id),
	FOREIGN KEY (reimb_type_id) REFERENCES ers_users (reimb_type_id)
	--ers_users_fk_auth int REFERENCES ers_users (ers_users_id),
	--ers_users_fk_reslvr int REFERENCES ers_users (user_role_id),
	--ers_reimbursement_status_fk int REFERENCES ers_reimbursement_status (reimb_status_id),
	--ers_reimbursement_type_fk int REFERENCES ers_reimbursement_type (reimb_type_id)
);

DROP TABLE ers_reimbursement;

INSERT INTO ers_reimbursement (reimb_amount, reimb_submitted, reimb_author, reimb_type_id, 
ers_users_fk_auth, ers_reimbursement_status_fk, ers_reimbursement_type_fk) VALUES 
(425.00, to_timestamp('2022-07-02', 'YY-MM-DD'), 1, 1, 1, 1, 1), (375.00, to_timestamp('2022-07-10', 'YY-MM-DD'), 
2, 2, 2, 2, 2), (515.00, to_timestamp('2022-07-15', 'YY-MM-DD'), 3, 3, 3, 3, 3), (475.00,
to_timestamp('2022-07-22', 'YY-MM-DD'), 4, 4, 4, 4, 4);


CREATE TABLE ers_users(
	ers_users_id serial,
	ers_username TEXT,
	ers_password TEXT,
	user_first_name TEXT,
	user_last_name TEXT,
	user_email TEXT,
	user_role_id int,
	PRIMARY KEY (ers_users_id),
	UNIQUE (ers_username, user_email),
	FOREIGN KEY (user_role_id) REFERENCES ers_user_roles (ers_user_role_id)
	--user_roles_fk int REFERENCES ers_user_roles (ers_user_role_id)
);

INSERT INTO ers_users (ers_username, ers_password, user_first_name, user_last_name, user_email,
user_role_id) VALUES ('username', 'password', 'Peter', 'Parker', 'spiderman@revature.com', 1),
('dwchar', 'password', 'Clark', 'Kent', 'superman@revature.com', 2), ('user', 'password',
'Miles', 'Morales', 'scarletspider@revature.com', 3), ('big_daddy', 'password', 'Bruce',
'Wayne', 'batman@revature.com', 4);


DROP TABLE ers_users;


CREATE TABLE ers_reimbursement_status(
	reimb_status_id serial PRIMARY KEY,
	reimb_status TEXT
);

INSERT INTO ers_reimbursement_status (reimb_status) VALUES (1), (2), (3), (4);


DROP TABLE ers_reimbursement_status;


CREATE TABLE ers_reimbursement_type(
	reimb_type_id serial PRIMARY KEY,
	reimb_type TEXT
);

INSERT INTO ers_reimbursement_type (reimb_type) VALUES ('lodging'), ('food'), ('travel'),
('loding');

DROP TABLE ers_reimbursement_type;


CREATE TABLE ers_user_roles(
	ers_user_role_id serial PRIMARY KEY,
	user_role TEXT
);

INSERT INTO ers_user_roles (user_role) VALUES ('employee'), ('guest'), ('associate'), ('owner');

DROP TABLE ers_user_roles;