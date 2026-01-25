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
        'http://localhost:9000/images/c38924fd-3a78-43db-b817-9e5bf269.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260121%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260121T145236Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c4399676b8e331212ee2a166cdb4b5c750f1e4c6fb15af25152b646e262c4765',
     'http://localhost:9000/images/c38924fd-3a78-43db-b817-9e5bf269.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260121%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260121T145236Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c4399676b8e331212ee2a166cdb4b5c750f1e4c6fb15af25152b646e262c4765',
     'http://localhost:9000/images/c38924fd-3a78-43db-b817-9e5bf269.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260121%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260121T145236Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c4399676b8e331212ee2a166cdb4b5c750f1e4c6fb15af25152b646e262c4765'
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
                                                               (1, 2), (1, 6), (1, 10),
                                                               (2, 3), (2, 4), (2, 5),
                                                               (6, 7), (6, 8), (6, 9),
                                                               (10, 11), (10, 12), (10, 13);

-- 8. Заказы (5 объявлений + 3 задачи)
INSERT INTO orders (id, enable, price, category_id, created_at, images_id, users_id, description, location, title) VALUES
                                                                                                                       -- Объявления 1-5
                                                                                                                       (1, true, 500, 3, NOW(), 1, 2, 'Домашние пельмени с говядиной и свининой, порция 20 штук', 'ул. Пушкина, 10', 'Пельмени домашние'),
                                                                                                                       (2, true, 350, 4, NOW(), 1, 2, 'Вареники с творогом и сметаной, свежие', 'ул. Ленина, 15', 'Вареники с творогом'),
                                                                                                                       (3, true, 450, 5, NOW(), 1, 3, 'Голубцы с мясом и рисом в томатном соусе', 'ул. Гагарина, 20', 'Голубцы домашние'),
                                                                                                                       (4, true, 100, 7, NOW(), 1, 3, 'Бутилированная вода 1.5л', 'ул. Садовая, 5', 'Вода минеральная'),
                                                                                                                       (5, true, 200, 9, NOW(), 1, 2, 'Свежевыжатый апельсиновый сок', 'ул. Мира, 12', 'Сок апельсиновый'),
                                                                                                                       -- Задачи 6-8
                                                                                                                       (6, true, 2000, 11, NOW(), 1, 2, 'Приготовить говяжий стейк на 4 персоны', 'ул. Центральная, 1', 'Стейк из говядины'),
                                                                                                                       (7, true, 1500, 12, NOW(), 1, 3, 'Сделать свиные отбивные с гарниром', 'ул. Северная, 8', 'Отбивные свиные'),
                                                                                                                       (8, true, 1800, 13, NOW(), 1, 2, 'Приготовить шашлык из баранины', 'ул. Южная, 3', 'Шашлык бараний');

-- 9. Объявления
INSERT INTO advertisements (id) VALUES
                                    (1), (2), (3), (4), (5);

-- 10. Задачи
INSERT INTO tasks (id, accepted_worker_id) VALUES
                                               (6, NULL),
                                               (7, NULL),
                                               (8, NULL);

-- 11. Работники задач
INSERT INTO "task-workers" (tasks_id, users_id) VALUES
                                                    (6, 3),
                                                    (7, 2),
                                                    (8, 2),
                                                    (8, 3);

-- 12. Отзывы
INSERT INTO reviews (id, rating, author_id, target_user_id, review_text) VALUES
                                                                             (1, 5, 2, 3, 'Отличный повар! Голубцы были великолепны!'),
                                                                             (2, 4, 3, 2, 'Пельмени очень вкусные, рекомендую!');

-- -- 13. Избранное
-- INSERT INTO user_favourites (orders_id, users_id) VALUES
--                                                       (1, 2),
--                                                       (3, 2),
--                                                       (2, 3),
--                                                       (6, 3);