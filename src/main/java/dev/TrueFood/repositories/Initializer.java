package dev.TrueFood.repositories;

import dev.TrueFood.entity.*;
import dev.TrueFood.entity.users.OrderType;
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
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    List<String> imageUrls = new ArrayList<>();
    @Autowired
    private OrderRepository orderRepository;

    public void initial() {

        imageUrls.add("http://localhost:9000/images/c21eab68-33e6-414d-a47b-35d34b9f.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260105%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260105T223404Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=86c24ba4e09795d6e0a8c3dce75f5b9c8652e8360ec0adefba65bfbaacfe558b");
        imageUrls.add("http://localhost:9000/images/ee4cf0c1-cfc7-4f62-8abc-521198a4.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260105%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260105T223404Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=0d4e98971518cc1f4bc6463e53ebefdd2049ae73007d0b8c6b967fb9068e87ae");
        imageUrls.add("http://localhost:9000/images/b5ab84ef-a4bf-448f-96d1-5dfd8b48.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260105%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260105T223404Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=42c6f87a852ccc2a5282ee47c746fe4394070ffe021634e416d49d8cb82f4041");

        Image image1 = new Image(
                null,
                imageUrls
        );
        imageRepository.save(image1);

        User user1 = new User(
                null,
                "1",
                image1,
                Role.USER,
                new Password(null, "1"),
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
                new Password(null, "2"),
                true,
                "Игорь Гофман",
                5
        );
        userRepository.save(user2);

        User user3 = new User(
                null,
                "3",
                image1,
                Role.USER,
                new Password(null, "3"),
                true,
                "Саня",
                5
        );
        userRepository.save(user3);

        User user4 = new User(
                null,
                "4",
                image1,
                Role.USER,
                new Password(null, "4"),
                true,
                "Владимир",
                5
        );
        userRepository.save(user4);

        Category all = new Category(null, "Все");
        categoryRepository.save(all);

        Category readyFood = new Category(all, "Готовые блюда");
        categoryRepository.save(readyFood);

        Category pelmeni = new Category(readyFood, "Пельмени");
        categoryRepository.save(pelmeni);

        Category vareniki = new Category(readyFood, "Вареники");
        categoryRepository.save(vareniki);

        Category golubci = new Category(readyFood, "Голубцы");
        categoryRepository.save(golubci);

        Category drinks = new Category(all, "Напитки");
        categoryRepository.save(drinks);

        Category voda = new Category(drinks, "Вода");
        categoryRepository.save(voda);

        Category pivo = new Category(drinks, "Пиво");
        categoryRepository.save(pivo);

        Category juices = new Category(drinks, "Соки");
        categoryRepository.save(juices);

        Category meat = new Category(all, "Мясные блюда");
        categoryRepository.save(meat);

        Category beef = new Category(meat, "Говядина");
        categoryRepository.save(beef);

        Category pig = new Category(meat, "Свинина");
        categoryRepository.save(pig);

        Category sheep = new Category(meat, "Баранина");
        categoryRepository.save(sheep);

        all.setChildrenId(List.of(readyFood.getId(), pelmeni.getId(), vareniki.getId(), golubci.getId(), drinks.getId(), meat.getId(), beef.getId(), pig.getId(), sheep.getId()));
        categoryRepository.save(all);

        readyFood.setChildrenId(List.of(pelmeni.getId(), vareniki.getId(), golubci.getId()));
        categoryRepository.save(readyFood);

        drinks.setChildrenId(List.of(voda.getId(), pivo.getId(), juices.getId()));
        categoryRepository.save(drinks);

        meat.setChildrenId(List.of(beef.getId(), pig.getId(), sheep.getId()));
        categoryRepository.save(meat);

        Order advertisement1 = new Order(
                null,
                "Жареный суп",
                1L,
                "опасно",
                3L,
                123,
                "location1",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        orderRepository.save(advertisement1);

        // 1. Пицца
        Advertisement advertisement1123 = new Advertisement(
                null,
                "Пицца Маргарита",
                "Свежая домашняя пицца с томатным соусом, сыром моцарелла и базиликом. Приготовлена сегодня.",
                1L,
                3L,
                1,
                "ул. Пушкина, 10",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement1123);

        // 2. Суши
        Advertisement advertisement2234234 = new Advertisement(
                null,
                "Суши сет 'Сакура'",
                "20 штук: филадельфия, калифорния, спайси тунец. Свежие морепродукты.",
                1L,
                3L,
                1,
                "пр. Ленина, 25",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement2234234);

        // 3. Бургер
        Advertisement advertisement23423424 = new Advertisement(
                null,
                "Чизбургер с картошкой",
                "Двойная говяжья котлета, сыр чеддер, свежие овощи. Подается с картофелем фри.",
                1L,
                3L,
                1,
                "ул. Гагарина, 15",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement23423424);

        // 4. Салат
        Advertisement advertisement4 = new Advertisement(
                null,
                "Салат Цезарь с курицей",
                "Хрустящий салат с куриной грудкой, пармезаном, сухариками и соусом цезарь.",
                1L,
                3L,
                1,
                "ул. Садовая, 8",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement4);

        // 5. Паста
        Advertisement advertisement5 = new Advertisement(
                null,
                "Паста Карбонара",
                "Спагетти с беконом, сливочным соусом, яйцом и пармезаном.",
                1L,
                3L,
                1,
                "пр. Мира, 33",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement5);

        // 6. Шашлык
        Advertisement advertisement6 = new Advertisement(
                null,
                "Шашлык из свинины",
                "Нежный шашлык на углях с овощами гриль. Порция 500г.",
                1L,
                3L,
                1,
                "ул. Грильмастеров, 5",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement6);

        // 7. Суп
        Advertisement advertisement7 = new Advertisement(
                null,
                "Борщ украинский",
                "Наваристый борщ со сметаной и чесночными пампушками.",
                1L,
                3L,
                1,
                "ул. Украинская, 12",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement7);

        // 8. Роллы
        Advertisement advertisement8 = new Advertisement(
                null,
                "Запеченные роллы с лососем",
                "8 штук, запеченные под сырным соусом с лососем и сливочным сыром.",
                1L,
                3L,
                1,
                "ул. Рыбацкая, 18",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement8);

        // 9. Стейк
        Advertisement advertisement9 = new Advertisement(
                null,
                "Стейк Рибай",
                "Говяжий стейк 300г, средней прожарки, с овощами гриль и соусом.",
                1L,
                3L,
                1,
                "ул. Мясная, 22",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement9);

        // 10. Десерт
        Advertisement advertisement10 = new Advertisement(
                null,
                "Тирамису классический",
                "Итальянский десерт с кофейной пропиткой и сыром маскарпоне.",
                1L,
                3L,
                1,
                "ул. Сладкая, 7",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement10);

        // 11. Лапша
        Advertisement advertisement11 = new Advertisement(
                null,
                "Лапша WOK с курицей",
                "Лапша с курицей, овощами и соевым соусом на воке.",
                1L,
                3L,
                1,
                "ул. Азиатская, 14",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement11);

        // 12. Пельмени
        Advertisement advertisement12 = new Advertisement(
                null,
                "Пельмени домашние",
                "Домашние пельмени с говядиной и свининой, порция 20 штук.",
                2L,
                3L,
                1,
                "ул. Домашняя, 9",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement12);

        // 13. Сэндвич
        Advertisement advertisement13 = new Advertisement(
                null,
                "Клаб-сэндвич",
                "Трехслойный сэндвич с курицей, беконом, сыром и овощами.",
                2L,
                3L,
                1,
                "ул. Бутербродная, 3",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement13);

        // 14. Рыба
        Advertisement advertisement14 = new Advertisement(
                null,
                "Дорадо на гриле",
                "Свежая дорадо с лимоном и травами, подается с рисом.",
                2L,
                3L,
                1,
                "ул. Морская, 11",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement14);

        // 15. Завтрак
        Advertisement advertisement15 = new Advertisement(
                null,
                "Английский завтрак",
                "Яичница с беконом, сосисками, грибами, помидорами и тостами.",
                2L,
                3L,
                1,
                "ул. Утренняя, 6",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement15);

        // 16. Креветки
        Advertisement advertisement16 = new Advertisement(
                null,
                "Креветки в чесночном соусе",
                "Крупные тигровые креветки в сливочно-чесночном соусе.",
                2L,
                3L,
                1,
                "ул. Креветочная, 4",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement16);

        // 17. Омлет
        Advertisement advertisement17 = new Advertisement(
                null,
                "Омлет с ветчиной и сыром",
                "Пышный омлет с ветчиной, сыром и зеленью.",
                2L,
                3L,
                1,
                "ул. Яичная, 2",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement17);

        // 18. Курица
        Advertisement advertisement18 = new Advertisement(
                null,
                "Курица терияки",
                "Куриное филе в соусе терияки с кунжутом и рисом.",
                2L,
                3L,
                1,
                "ул. Восточная, 17",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement18);

        // 19. Тако
        Advertisement advertisement19 = new Advertisement(
                null,
                "Тако с говядиной",
                "3 тако с мексиканской говядиной, сальсой и гуакамоле.",
                2L,
                3L,
                1,
                "ул. Мексиканская, 13",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        advertisementRepository.save(advertisement19);

            // 20. Чизкейк
        Advertisement advertisement20 = new Advertisement(
                    null,
                    "Чизкейк Нью-Йорк",
                    "Классический чизкейк с ягодным соусом.",
                    2L,
                    3L,
                    1,
                    "ул. Десертная, 8",
                    image1,
                    OrderType.ADVERTISEMENT,
                    true
            );
        advertisementRepository.save(advertisement20);

        Task task1 = new Task(
                null,
                "Торт на детский праздник 1",
                "приготовить торт на день рождения, высота 2 метра, цвет: синий",
                2L,
                3L,
                2000,
                "ул. Десертная, 8",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        taskRepository.save(task1);

        Task task2 = new Task(
                null,
                "Торт на детский праздник 2",
                "приготовить торт на день рождения, высота 2 метра, цвет: синий",
                2L,
                3L,
                2000,
                "ул. Десертная, 8",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        taskRepository.save(task2);

        Task task3 = new Task(
                null,
                "Торт на детский праздник 3",
                "приготовить торт на день рождения, цвет: синий, вкус: сладкий",
                2L,
                3L,
                2000,
                "ул. Десертная, 8",
                image1,
                OrderType.ADVERTISEMENT,
                true
        );
        taskRepository.save(task3);

        }

    }