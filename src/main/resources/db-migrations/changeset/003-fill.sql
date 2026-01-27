--liquibase formatted sql
--changeset admin:test_data splitStatements:true endDelimiter:;

-- 1. Пароли
INSERT INTO passwords (id, password) VALUES
                                         (1, 'admin'),
                                         (2, 'user1'),
                                         (3, 'user2');

-- 2. Изображения
INSERT INTO images (id, image_urls) VALUES
    (1, ARRAY[
         'http://localhost:9000/images/f79f8104-946f-48a3-9e82-3de05bbb.jpg',
         'http://localhost:9000/images/1c9135ea-15cd-4f02-956c-c89eda0c.jpg',
         'http://localhost:9000/images/5a9bf9fa-2426-461d-b359-b760d2a2.jpg'
         ]);

-- 3. Базовые пользователи (админ + 2 обычных)
INSERT INTO base_users (id, enable, password_id, email, role) VALUES
                                                                  (1, true, 1, 'admin@truefood.ru', 'ADMIN'),
                                                                  (2, true, 2, 'user1@truefood.ru', 'USER'),
                                                                  (3, true, 3, 'user2@truefood.ru', 'USER');

-- 4. Админ
INSERT INTO admins (id) VALUES (1);

-- 5. Обычные пользователи
INSERT INTO users (id, rating, images_id, contacts, fio) VALUES
                                                             (2, 0, 1, 'tg: @user1', 'Пользователь 1'),
                                                             (3, 0, 1, 'tg: @user2', 'Пользователь 2');

-- 6. Категории
INSERT INTO categories (id, parent_id, name) VALUES
                                                 (1, NULL, 'Все'),
                                                 (2, 1, 'Готовые блюда'),
                                                 (3, 2, 'Пельмени'),
                                                 (4, 2, 'Вареники'),
                                                 (5, 2, 'Голубцы'),
                                                 (6, 1, 'Напитки'),
                                                 (7, 6, 'Вода'),
                                                 (8, 6, 'Пиво'),
                                                 (9, 6, 'Соки'),
                                                 (10, 1, 'Мясные блюда'),
                                                 (11, 10, 'Говядина'),
                                                 (12, 10, 'Свинина'),
                                                 (13, 10, 'Баранина');

-- 7. Категории-дети
INSERT INTO categories_children (category_id, children_id) VALUES
                                                               (1, 2), (1, 3),
                                                               (1, 4),(1, 5),
                                                               (1, 6),(1, 7),
                                                               (1, 8),(1, 9),
                                                               (1, 10),(2, 3),
                                                               (2, 4),(2, 5),
                                                               (6, 7),(6, 8),
                                                               (6, 9),(10, 11),
                                                               (10, 12),(10, 13);

-- 8. Объявления (теперь напрямую в advertisements)
INSERT INTO advertisements (id, enable, price, category_id, created_at, images_id, users_id, description, location, title) VALUES
                                                                                                                               (1, true, 500, 3, NOW(), 1, 2, 'Домашние пельмени с говядиной и свининой, порция 20 штук', 'ул. Пушкина, 10', 'Пельмени домашние'),
                                                                                                                               (2, true, 350, 4, NOW(), 1, 2, 'Вареники с творогом и сметаной, свежие', 'ул. Ленина, 15', 'Вареники с творогом'),
                                                                                                                               (3, true, 450, 5, NOW(), 1, 3, 'Голубцы с мясом и рисом в томатном соусе', 'ул. Гагарина, 20', 'Голубцы домашние'),
                                                                                                                               (4, true, 100, 7, NOW(), 1, 3, 'Бутилированная вода 1.5л', 'ул. Садовая, 5', 'Вода минеральная'),
                                                                                                                               (5, true, 200, 9, NOW(), 1, 2, 'Свежевыжатый апельсиновый сок', 'ул. Мира, 12', 'Сок апельсиновый');

-- 9. Задачи (теперь напрямую в tasks)
INSERT INTO tasks (id, enable, price, accepted_worker_id, category_id, created_at, images_id, users_id, description, location, title) VALUES
                                                                                                                                          (6, true, 2000, NULL, 11, NOW(), 1, 2, 'Приготовить говяжий стейк на 4 персоны', 'ул. Центральная, 1', 'Стейк из говядины'),
                                                                                                                                          (7, true, 1500, NULL, 12, NOW(), 1, 3, 'Сделать свиные отбивные с гарниром', 'ул. Северная, 8', 'Отбивные свиные'),
                                                                                                                                          (8, true, 1800, NULL, 13, NOW(), 1, 2, 'Приготовить шашлык из баранины', 'ул. Южная, 3', 'Шашлык бараний');

-- 10. Работники задач
INSERT INTO "task-workers" (tasks_id, users_id) VALUES
                                                    (6, 3),
                                                    (7, 2),
                                                    (8, 2),
                                                    (8, 3);

-- 11. Отзывы
INSERT INTO reviews (id, rating, author_id, target_user_id, review_text) VALUES
                                                                             (1, 5, 2, 3, 'Отличный повар! Голубцы были великолепны!'),
                                                                             (2, 4, 3, 2, 'Пельмени очень вкусные, рекомендую!');

-- 12. Избранное (только объявления, не задачи)
INSERT INTO user_favourites (orders_id, users_id) VALUES
                                                      (1, 2),  -- user1 → пельмени
                                                      (3, 2),  -- user1 → голубцы
                                                      (2, 3);  -- user2 → вареники
-- (6, 3)   -- УБРАНО: задача не может быть в избранном