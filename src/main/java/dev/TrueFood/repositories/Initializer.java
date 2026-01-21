package dev.TrueFood.repositories;

import dev.TrueFood.entity.*;
import dev.TrueFood.entity.Password;
import dev.TrueFood.enums.Role;
import dev.TrueFood.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import jakarta.transaction.Transactional;
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
    private AdminRepository adminRepository;
    @Autowired
    private TaskRepository taskRepository;

    @PersistenceContext
    private EntityManager entityManager;

    List<String> imageUrls = new ArrayList<>();

    @Transactional
    public void initial() {

        imageUrls.add("http://localhost:9000/images/c38924fd-3a78-43db-b817-9e5bf269.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260121%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260121T145236Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c4399676b8e331212ee2a166cdb4b5c750f1e4c6fb15af25152b646e262c4765");
        imageUrls.add("http://localhost:9000/images/c38924fd-3a78-43db-b817-9e5bf269.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260121%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260121T145236Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c4399676b8e331212ee2a166cdb4b5c750f1e4c6fb15af25152b646e262c4765");
        imageUrls.add("http://localhost:9000/images/c38924fd-3a78-43db-b817-9e5bf269.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20260121%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20260121T145236Z&X-Amz-Expires=604800&X-Amz-SignedHeaders=host&X-Amz-Signature=c4399676b8e331212ee2a166cdb4b5c750f1e4c6fb15af25152b646e262c4765");

        Image image2 = new Image(
                null,
                imageUrls
        );
        imageRepository.save(image2);


        Image image1 = entityManager.merge(image2);

        Admin admin = new Admin(
                null,
                "admin",
                Role.ADMIN,
                new Password(null, "admin"),
                true
        );
        adminRepository.save(admin);

        User ludmila = new User(
                null,
                "1",
                image1,
                Role.USER,
                new Password(null, "1"),
                true,
                "Людмила",
                0,
                "tg: @Anton123"
                );
        userRepository.save(ludmila);

        User igor = new User(
                null,
                "2",
                image1,
                Role.USER,
                new Password(null, "2"),
                true,
                "Игорь Гофман",
                0,
                "tg: @Anton123"
        );
        userRepository.save(igor);

        User aleksandr = new User(
                null,
                "3",
                image1,
                Role.USER,
                new Password(null, "3"),
                true,
                "Саня",
                0,
                "tg: @Anton123"
        );
        userRepository.save(aleksandr);

        User vladimir = new User(
                null,
                "4",
                image1,
                Role.USER,
                new Password(null, "4"),
                true,
                "Владимир",
                0,
                "tg: @Anton123"
        );
        userRepository.save(vladimir);

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

        all.setChildren(new ArrayList<>(List.of(readyFood, pelmeni, vareniki, golubci, drinks, meat, beef, pig, sheep)));
        categoryRepository.save(all);

        readyFood.setChildren(new ArrayList<>(List.of(pelmeni, vareniki, golubci)));
        categoryRepository.save(readyFood);

        drinks.setChildren(new ArrayList<>(List.of(voda, pivo, juices)));
        categoryRepository.save(drinks);

        meat.setChildren(new ArrayList<>(List.of(beef, pig, sheep)));
        categoryRepository.save(meat);
        
        // 1. Пицца
        Advertisement advertisement1123 = new Advertisement(
                null,
                "Пицца Маргарита",
                "Свежая домашняя пицца с томатным соусом, сыром моцарелла и базиликом. Приготовлена сегодня.",
                ludmila,
                pelmeni,
                1,
                "ул. Пушкина, 10",
                image1,
                true
        );
        advertisementRepository.save(advertisement1123);

        // 2. Суши
        Advertisement advertisement2234234 = new Advertisement(
                null,
                "Суши сет 'Сакура'",
                "20 штук: филадельфия, калифорния, спайси тунец. Свежие морепродукты.",
                ludmila,
                pelmeni,
                1,
                "пр. Ленина, 25",
                image1,
                true
        );
        advertisementRepository.save(advertisement2234234);

        // 3. Бургер
        Advertisement advertisement23423424 = new Advertisement(
                null,
                "Чизбургер с картошкой",
                "Двойная говяжья котлета, сыр чеддер, свежие овощи. Подается с картофелем фри.",
                ludmila,
                pelmeni,
                1,
                "ул. Гагарина, 15",
                image1,
                true
        );
        advertisementRepository.save(advertisement23423424);

        // 4. Салат
        Advertisement advertisement4 = new Advertisement(
                null,
                "Салат Цезарь с курицей",
                "Хрустящий салат с куриной грудкой, пармезаном, сухариками и соусом цезарь.",
                ludmila,
                pelmeni,
                1,
                "ул. Садовая, 8",
                image1,
                true
        );
        advertisementRepository.save(advertisement4);

        // 5. Паста
        Advertisement advertisement5 = new Advertisement(
                null,
                "Паста Карбонара",
                "Спагетти с беконом, сливочным соусом, яйцом и пармезаном.",
                ludmila,
                pelmeni,
                1,
                "пр. Мира, 33",
                image1,
                true
        );
        advertisementRepository.save(advertisement5);

        // 6. Шашлык
        Advertisement advertisement6 = new Advertisement(
                null,
                "Шашлык из свинины",
                "Нежный шашлык на углях с овощами гриль. Порция 500г.",
                igor,
                pelmeni,
                1,
                "ул. Грильмастеров, 5",
                image1,
                true
        );
        advertisementRepository.save(advertisement6);

        // 7. Суп
        Advertisement advertisement7 = new Advertisement(
                null,
                "Борщ украинский",
                "Наваристый борщ со сметаной и чесночными пампушками.",
                igor,
                pelmeni,
                1,
                "ул. Украинская, 12",
                image1,
                true
        );
        advertisementRepository.save(advertisement7);

        // 8. Роллы
        Advertisement advertisement8 = new Advertisement(
                null,
                "Запеченные роллы с лососем",
                "8 штук, запеченные под сырным соусом с лососем и сливочным сыром.",
                igor,
                pelmeni,
                1,
                "ул. Рыбацкая, 18",
                image1,
                true
        );
        advertisementRepository.save(advertisement8);

        // 9. Стейк
        Advertisement advertisement9 = new Advertisement(
                null,
                "Стейк Рибай",
                "Говяжий стейк 300г, средней прожарки, с овощами гриль и соусом.",
                igor,
                pelmeni,
                1,
                "ул. Мясная, 22",
                image1,
                true
        );
        advertisementRepository.save(advertisement9);

        // 10. Десерт
        Advertisement advertisement10 = new Advertisement(
                null,
                "Тирамису классический",
                "Итальянский десерт с кофейной пропиткой и сыром маскарпоне.",
                igor,
                pelmeni,
                1,
                "ул. Сладкая, 7",
                image1,
                true
        );
        advertisementRepository.save(advertisement10);

        // 11. Лапша
        Advertisement advertisement11 = new Advertisement(
                null,
                "Лапша WOK с курицей",
                "Лапша с курицей, овощами и соевым соусом на воке.",
                aleksandr,
                pelmeni,
                1,
                "ул. Азиатская, 14",
                image1,
                true
        );
        advertisementRepository.save(advertisement11);

        // 12. Пельмени
        Advertisement advertisement12 = new Advertisement(
                null,
                "Пельмени домашние",
                "Домашние пельмени с говядиной и свининой, порция 20 штук.",
                aleksandr,
                pelmeni,
                1,
                "ул. Домашняя, 9",
                image1,
                true
        );
        advertisementRepository.save(advertisement12);

        // 13. Сэндвич
        Advertisement advertisement13 = new Advertisement(
                null,
                "Клаб-сэндвич",
                "Трехслойный сэндвич с курицей, беконом, сыром и овощами.",
                aleksandr,
                pelmeni,
                1,
                "ул. Бутербродная, 3",
                image1,
                true
        );
        advertisementRepository.save(advertisement13);

        // 14. Рыба
        Advertisement advertisement14 = new Advertisement(
                null,
                "Дорадо на гриле",
                "Свежая дорадо с лимоном и травами, подается с рисом.",
                aleksandr,
                pelmeni,
                1,
                "ул. Морская, 11",
                image1,
                true
        );
        advertisementRepository.save(advertisement14);

        // 15. Завтрак
        Advertisement advertisement15 = new Advertisement(
                null,
                "Английский завтрак",
                "Яичница с беконом, сосисками, грибами, помидорами и тостами.",
                aleksandr,
                pelmeni,
                1,
                "ул. Утренняя, 6",
                image1,
                true
        );
        advertisementRepository.save(advertisement15);

        // 16. Креветки
        Advertisement advertisement16 = new Advertisement(
                null,
                "Креветки в чесночном соусе",
                "Крупные тигровые креветки в сливочно-чесночном соусе.",
                aleksandr,
                pelmeni,
                1,
                "ул. Креветочная, 4",
                image1,
                true
        );
        advertisementRepository.save(advertisement16);

        // 17. Омлет
        Advertisement advertisement17 = new Advertisement(
                null,
                "Омлет с ветчиной и сыром",
                "Пышный омлет с ветчиной, сыром и зеленью.",
                aleksandr,
                pelmeni,
                1,
                "ул. Яичная, 2",
                image1,
                true
        );
        advertisementRepository.save(advertisement17);

        // 18. Курица
        Advertisement advertisement18 = new Advertisement(
                null,
                "Курица терияки",
                "Куриное филе в соусе терияки с кунжутом и рисом.",
                aleksandr,
                pelmeni,
                1,
                "ул. Восточная, 17",
                image1,
                true
        );
        advertisementRepository.save(advertisement18);

        // 19. Тако
        Advertisement advertisement19 = new Advertisement(
                null,
                "Тако с говядиной",
                "3 тако с мексиканской говядиной, сальсой и гуакамоле.",
                aleksandr,
                pelmeni,
                1,
                "ул. Мексиканская, 13",
                image1,
                true
        );
        advertisementRepository.save(advertisement19);

            // 20. Чизкейк
        Advertisement advertisement20 = new Advertisement(
                    null,
                    "Чизкейк Нью-Йорк",
                    "Классический чизкейк с ягодным соусом.",
                aleksandr,
                pelmeni,
                    1,
                    "ул. Десертная, 8",
                    image1,
                    true
            );
        advertisementRepository.save(advertisement20);

        Task task1 = new Task(
                null,
                "Торт на детский праздник 1",
                "приготовить торт на день рождения, высота 2 метра, цвет: синий",
                igor,
                pelmeni,
                2000,
                "ул. Десертная, 8",
                image1,
                true
        );
        taskRepository.save(task1);

        Task task2 = new Task(
                null,
                "Торт на детский праздник 2",
                "приготовить торт на день рождения, высота 2 метра, цвет: синий",
                igor,
                pelmeni,
                2000,
                "ул. Десертная, 8",
                image1,
                true
        );
        taskRepository.save(task2);

        Task task3 = new Task(
                null,
                "Торт на детский праздник 3",
                "приготовить торт на день рождения, цвет: синий, вкус: сладкий",
                ludmila,
                pelmeni,
                2000,
                "ул. Десертная, 8",
                image1,
                true
        );
        taskRepository.save(task3);

        }

    }