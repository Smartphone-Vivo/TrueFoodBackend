package dev.TrueFood.repositories;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    @Autowired
    private AdverticementRepository adverticementRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void initial() {

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
                123L,
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
                123L,
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
                123L,
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
                    123L,
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
                    456L,
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
                    789L,
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
                    321L,
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
                    654L,
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
                    987L,
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
                    147L,
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
                    258L,
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
                    369L,
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
                    741L,
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
                    852L,
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
                    963L,
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
                    159L,
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
                    753L,
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
                    357L,
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
                    486L,
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
                    624L,
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
                    792L,
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
                    531L,
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
                    468L,
                    "Десерты",
                    "2024-01-15",
                    true
            );
            adverticementRepository.save(adverticement20);
        }
    }





