
ALTER TABLE ONLY public.admins
    ADD CONSTRAINT admins_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.advertisements
    ADD CONSTRAINT advertisements_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.base_users
    ADD CONSTRAINT base_users_email_key UNIQUE (email);

ALTER TABLE ONLY public.base_users
    ADD CONSTRAINT base_users_password_id_key UNIQUE (password_id);

ALTER TABLE ONLY public.base_users
    ADD CONSTRAINT base_users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.images
    ADD CONSTRAINT images_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.passwords
    ADD CONSTRAINT passwords_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT reviews_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT tasks_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT fk2rlgtdmsf9rkv0ij8eepy2fbf FOREIGN KEY (id) REFERENCES public.orders(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk4w0qnfg1gsjox5tc0my7cflt FOREIGN KEY (category_id) REFERENCES public.categories(id);

ALTER TABLE ONLY public."task-workers"
    ADD CONSTRAINT fk6nbtxkh285t8wv70fdmxl313g FOREIGN KEY (users_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk985r60nn9fo3ycyuj9pia6ytk FOREIGN KEY (images_id) REFERENCES public.images(id);

ALTER TABLE ONLY public.user_favourites
    ADD CONSTRAINT fk9jxyt42pgti5wapjllcmtjqen FOREIGN KEY (users_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.categories_children
    ADD CONSTRAINT fkcwiwyla89s18q8xxu1dk7mr97 FOREIGN KEY (category_id) REFERENCES public.categories(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fke6k45xxoin4fylnwg2jkehwjf FOREIGN KEY (users_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.user_favourites
    ADD CONSTRAINT fkfpbep3bcstv6e93eogyimtluj FOREIGN KEY (orders_id) REFERENCES public.advertisements(id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fkhm4r73yqc8ftjjypiblihknbw FOREIGN KEY (images_id) REFERENCES public.images(id);

ALTER TABLE ONLY public.reviews
    ADD CONSTRAINT fki1sktfpjrk7gqmcshcinv3m0j FOREIGN KEY (target_user_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.advertisements
    ADD CONSTRAINT fkkvtrg2swmwfvm9eu7q3tjack FOREIGN KEY (id) REFERENCES public.orders(id);

ALTER TABLE ONLY public."task-workers"
    ADD CONSTRAINT fklj9o2nn92bps63f0vdhl4plq9 FOREIGN KEY (tasks_id) REFERENCES public.tasks(id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fklnov3in92uyrqb15vfl6s8gb0 FOREIGN KEY (id) REFERENCES public.base_users(id);

ALTER TABLE ONLY public.tasks
    ADD CONSTRAINT fkn2b0t8jvsha86dfe3lgudqp4e FOREIGN KEY (accepted_worker_id) REFERENCES public.users(id);

ALTER TABLE ONLY public.admins
    ADD CONSTRAINT fkne80gbu06b6q9xpw1bcbymxgv FOREIGN KEY (id) REFERENCES public.base_users(id);

ALTER TABLE ONLY public.base_users
    ADD CONSTRAINT fkpyc0h4su0pqoilmjghs7f8iif FOREIGN KEY (password_id) REFERENCES public.passwords(id);

ALTER TABLE ONLY public.categories_children
    ADD CONSTRAINT fks5er396t9r98ethqjy3v4v4c7 FOREIGN KEY (children_id) REFERENCES public.categories(id);

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT fksaok720gsu4u2wrgbk10b5n8d FOREIGN KEY (parent_id) REFERENCES public.categories(id);
