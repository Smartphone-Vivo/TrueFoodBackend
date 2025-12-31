package dev.TrueFood.repositories;

import dev.TrueFood.entity.Adverticement;
import dev.TrueFood.entity.Category;
import dev.TrueFood.entity.Image;
import dev.TrueFood.entity.users.Password;
import dev.TrueFood.entity.users.Role;
import dev.TrueFood.entity.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer {
    @Autowired
    private AdverticementRepository adverticementRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;

    List<String> imageUrls = new ArrayList<>();

    public void initial() {



        imageUrls.add("http://127.0.0.1:9000/images/5b909523-b4ab-4930-83fa-90c2adf5.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20251229%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251229T205751Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=9377cc55da28d60d472a3f033911bf37cf8f949b107930c08124cef343d4ade6");
        imageUrls.add("http://127.0.0.1:9000/images/02cc6c40-1081-4444-99ae-dcbb2e6d.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20251229%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251229T205751Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=903a54d54be28b0aea666545a7b84b52f6eb79408b8839bd263d9a66157d9b43");
        imageUrls.add("http://127.0.0.1:9000/images/33867e5e-e0e9-4075-85ea-71a4853c.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20251229%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20251229T205751Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=2816e7a3b1c1d77935cdcc52c4a6c7d29e13c0049df03560a648fb260b805b70");

        Image image1 = new Image(
                null,
                imageUrls
        );
        imageRepository.save(image1);

        Category readyFood = new Category(
                null,
                null,
                "Готовые блюда"
        );
        categoryRepository.save(readyFood);

        Password password1 = new Password(
                null,
                "1"
        );

        Password password2 = new Password(
                null,
                "2"
        );

        User user1 = new User(
                null,
                "1",
                image1,
                Role.USER,
                password1,
                true,
                "Людмила",
                5
                );
        userRepository.save(user1);

        User user2 = new User(
                null,
                "2",
                image1,
                Role.USER,
                password2,
                true,
                "Игорь Гофман",
                5
        );
        userRepository.save(user2);

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
                "Жареный суп",
                1L,
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

            // 1. Пицца
            Adverticement adverticement1123 = new Adverticement(
                    null,
                    "Пицца Маргарита",
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    1L,
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
                    2L,
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
                    2L,
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
                    2L,
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
                    2L,
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
                    2L,
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
                    2L,
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
                    2L,
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
                    2L,
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
                    2L,
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





