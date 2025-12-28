package dev.TrueFood.repositories;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private AdverticementRepository adverticementRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;


    public void initial() {

        Image image1 = new Image(
                null,
                "http://127.0.0.1:9000/images/item_123/88c7071e-0c18-432d-bf26-06b79cb19b72.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20251228%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251228T221931Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=facb94c7140578288d232df8ab8a93d57237e05921d9d62c3f86f7a35b253436"
        );
        imageRepository.save(image1);

        Category readyFood = new Category(
                null,
                null,
                "Готовые блюда"
        );
        categoryRepository.save(readyFood);

        Category pelmeni = new Category(
                null,
                readyFood,
                "Пельмени"
        );
        categoryRepository.save(pelmeni);

        Category vareniki = new Category(
                null,
                readyFood,
                "Вареники"
        );
        categoryRepository.save(vareniki);

        Category golubci = new Category(
                null,
                readyFood,
                "Голубцы"
        );
        categoryRepository.save(golubci);

        Category drinks = new Category(
                null,
                null,
                "Напитки"
        );
        categoryRepository.save(drinks);

        Category voda = new Category(
                null,
                drinks,
                "Вода"
        );
        categoryRepository.save(voda);

        Category pivo = new Category(
                null,
                drinks,
                "Пиво"
        );
        categoryRepository.save(pivo);

        Category juices = new Category(
                null,
                drinks,
                "Соки"
        );
        categoryRepository.save(juices);

        Category meat = new Category(
                null,
                null,
                "Мясные блюда"
        );
        categoryRepository.save(meat);

        Category beef = new Category(
                null,
                meat,
                "Говядина"
        );
        categoryRepository.save(beef);

        Category pig = new Category(
                null,
                meat,
                "Свинина"
        );
        categoryRepository.save(pig);

        Category sheep = new Category(
                null,
                meat,
                "Баранина"
        );
        categoryRepository.save(sheep);






        Adverticement adverticement1 = new Adverticement(
                null,
                "Жареный суп со свиными кишками",
                "опасно",
                3L,
                123,
                "location1",
                image1,
                "itemType1",
                "createdAt1",
                true
        );
        adverticementRepository.save(adverticement1);

        Adverticement adverticement2 = new Adverticement(
                null,
                "Жареный суп со свиными кишками2",
                "опасно",
                3L,
                123,
                "location1",
                image1,
                "itemType1",
                "createdAt1",
                true
        );
        adverticementRepository.save(adverticement2);

        Adverticement adverticement3 = new Adverticement(
                null,
                "Жареный суп со свиными кишками3",
                "опасно",
                3L,
                123,
                "location1",
                image1,
                "itemType1",
                "createdAt1",
                true
        );
        adverticementRepository.save(adverticement3);


            // 1. Пицца
            Adverticement adverticement1123 = new Adverticement(
                    null,
                    "Пицца Маргарита",
                    "Свежая домашняя пицца с томатным соусом, сыром моцарелла и базиликом. Приготовлена сегодня.",
                    3L,
                    1,
                    "ул. Пушкина, 10",
                    image1,
                    "Итальянская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement1123);

            // 2. Суши
            Adverticement adverticement2234234 = new Adverticement(
                    null,
                    "Суши сет 'Сакура'",
                    "20 штук: филадельфия, калифорния, спайси тунец. Свежие морепродукты.",
                    3L,
                    1,
                    "пр. Ленина, 25",
                    image1,
                    "Японская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement2234234);

            // 3. Бургер
            Adverticement adverticemen23423424t3 = new Adverticement(
                    null,
                    "Чизбургер с картошкой",
                    "Двойная говяжья котлета, сыр чеддер, свежие овощи. Подается с картофелем фри.",
                    3L,
                    1,
                    "ул. Гагарина, 15",
                    image1,
                    "Фастфуд",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticemen23423424t3);

            // 4. Салат
            Adverticement adverticement4 = new Adverticement(
                    null,
                    "Салат Цезарь с курицей",
                    "Хрустящий салат с куриной грудкой, пармезаном, сухариками и соусом цезарь.",
                    3L,
                    1,
                    "ул. Садовая, 8",
                    image1,
                    "Салаты",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement4);

            // 5. Паста
            Adverticement adverticement5 = new Adverticement(
                    null,
                    "Паста Карбонара",
                    "Спагетти с беконом, сливочным соусом, яйцом и пармезаном.",
                    3L,
                    1,
                    "пр. Мира, 33",
                    image1,
                    "Итальянская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement5);

            // 6. Шашлык
            Adverticement adverticement6 = new Adverticement(
                    null,
                    "Шашлык из свинины",
                    "Нежный шашлык на углях с овощами гриль. Порция 500г.",
                    3L,
                    1,
                    "ул. Грильмастеров, 5",
                    image1,
                    "Барбекю",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement6);

            // 7. Суп
            Adverticement adverticement7 = new Adverticement(
                    null,
                    "Борщ украинский",
                    "Наваристый борщ со сметаной и чесночными пампушками.",
                    3L,
                    1,
                    "ул. Украинская, 12",
                    image1,
                    "Первые блюда",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement7);

            // 8. Роллы
            Adverticement adverticement8 = new Adverticement(
                    null,
                    "Запеченные роллы с лососем",
                    "8 штук, запеченные под сырным соусом с лососем и сливочным сыром.",
                    3L,
                    1,
                    "ул. Рыбацкая, 18",
                    image1,
                    "Японская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement8);

            // 9. Стейк
            Adverticement adverticement9 = new Adverticement(
                    null,
                    "Стейк Рибай",
                    "Говяжий стейк 300г, средней прожарки, с овощами гриль и соусом.",
                    3L,
                    1,
                    "ул. Мясная, 22",
                    image1,
                    "Мясные блюда",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement9);

            // 10. Десерт
            Adverticement adverticement10 = new Adverticement(
                    null,
                    "Тирамису классический",
                    "Итальянский десерт с кофейной пропиткой и сыром маскарпоне.",
                    3L,
                    1,
                    "ул. Сладкая, 7",
                    image1,
                    "Десерты",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement10);

            // 11. Лапша
            Adverticement adverticement11 = new Adverticement(
                    null,
                    "Лапша WOK с курицей",
                    "Лапша с курицей, овощами и соевым соусом на воке.",
                    3L,
                    1,
                    "ул. Азиатская, 14",
                    image1,
                    "Азиатская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement11);

            // 12. Пельмени
            Adverticement adverticement12 = new Adverticement(
                    null,
                    "Пельмени домашние",
                    "Домашние пельмени с говядиной и свининой, порция 20 штук.",
                    3L,
                    1,
                    "ул. Домашняя, 9",
                    image1,
                    "Русская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement12);

            // 13. Сэндвич
            Adverticement adverticement13 = new Adverticement(
                    null,
                    "Клаб-сэндвич",
                    "Трехслойный сэндвич с курицей, беконом, сыром и овощами.",
                    3L,
                    1,
                    "ул. Бутербродная, 3",
                    image1,
                    "Фастфуд",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement13);

            // 14. Рыба
            Adverticement adverticement14 = new Adverticement(
                    null,
                    "Дорадо на гриле",
                    "Свежая дорадо с лимоном и травами, подается с рисом.",
                    3L,
                    1,
                    "ул. Морская, 11",
                    image1,
                    "Рыбные блюда",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement14);

            // 15. Завтрак
            Adverticement adverticement15 = new Adverticement(
                    null,
                    "Английский завтрак",
                    "Яичница с беконом, сосисками, грибами, помидорами и тостами.",
                    3L,
                    1,
                    "ул. Утренняя, 6",
                    image1,
                    "Завтраки",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement15);

            // 16. Креветки
            Adverticement adverticement16 = new Adverticement(
                    null,
                    "Креветки в чесночном соусе",
                    "Крупные тигровые креветки в сливочно-чесночном соусе.",
                    3L,
                    1,
                    "ул. Креветочная, 4",
                    image1,
                    "Морепродукты",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement16);

            // 17. Омлет
            Adverticement adverticement17 = new Adverticement(
                    null,
                    "Омлет с ветчиной и сыром",
                    "Пышный омлет с ветчиной, сыром и зеленью.",
                    3L,
                    1,
                    "ул. Яичная, 2",
                    image1,
                    "Завтраки",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement17);

            // 18. Курица
            Adverticement adverticement18 = new Adverticement(
                    null,
                    "Курица терияки",
                    "Куриное филе в соусе терияки с кунжутом и рисом.",
                    3L,
                    1,
                    "ул. Восточная, 17",
                    image1,
                    "Азиатская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement18);

            // 19. Тако
            Adverticement adverticement19 = new Adverticement(
                    null,
                    "Тако с говядиной",
                    "3 тако с мексиканской говядиной, сальсой и гуакамоле.",
                    3L,
                    1,
                    "ул. Мексиканская, 13",
                    image1,
                    "Мексиканская кухня",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement19);

            // 20. Чизкейк
            Adverticement adverticement20 = new Adverticement(
                    null,
                    "Чизкейк Нью-Йорк",
                    "Классический чизкейк с ягодным соусом.",
                    3L,
                    1,
                    "ул. Десертная, 8",
                    image1,
                    "Десерты",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement20);
        }
    }





