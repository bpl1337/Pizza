import org.jetbrains.annotations.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Ingredient> ingredients = new ArrayList<>();
    private static ArrayList<PizzaBase> base = new ArrayList<>();
    private static ArrayList<Pizza> pizzas = new ArrayList<>();
    private static ArrayList<PizzaSide> sides = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();

    public static void main(String[] args) {
        Main myProgram = new Main();
        myProgram.seedData();
        myProgram.run();
    }

    private void seedData() {
        ingredients.add(new Ingredient("Сыр", 50));
        ingredients.add(new Ingredient("Пепперони", 70));
        ingredients.add(new Ingredient("Томаты", 30));
        ingredients.add(new Ingredient("Грибы", 40));
        ingredients.add(new Ingredient("Кунжут", 20));

        base.add(new PizzaBase("Классическая", 50, true));

        Pizza standartP = new Pizza("Стандарт", base.getFirst(), "Средний");
        standartP.addIngredient(ingredients.get(0));
        standartP.addIngredient(ingredients.get(2));
        standartP.addIngredient(ingredients.get(3));
        pizzas.add(standartP);
        Pizza margaritaP = new Pizza("Маргарита", base.getFirst(), "Средний");
        margaritaP.addIngredient(ingredients.get(1));
        margaritaP.addIngredient(ingredients.get(4));
        margaritaP.addIngredient(ingredients.get(3));
        pizzas.add(margaritaP);

        PizzaSide standartS = new PizzaSide("Базовый");
        standartS.addIngredient(ingredients.get(1));
        standartS.addIngredient(ingredients.get(2));
        sides.add(standartS);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== ПИЦЦЕРИЯ ===");
            System.out.println("1. Управление ингредиентами");
            System.out.println("2. Управление основами");
            System.out.println("3. Меню пицц");
            System.out.println("4. Меню бортиков");
            System.out.println("5. Меню заказов");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: manageIngredients(); break;
                case 2: manageBases(); break;
                case 3: manageMenuPizzas(); break;
                case 4: manageMenuSides(); break;
                case 5: manageMenuOrder(); break;
                case 0: return;
                default:
                    System.out.print("Введен некорректный символ, попробуйте еще раз...");
                    break;
            }
        }
    }

    public void manageIngredients() {
        while (true) {
            System.out.println("\n=== МЕНЮ ИНГРЕДИЕНТОВ ===");
            System.out.println("1. Добавить");
            System.out.println("2. Редактировать");
            System.out.println("3. Удалить");
            System.out.println("4. Вывести список существующих ингредиентов");
            System.out.println("5. Вывести список ингредиентов по цене");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Введите название нового ингредиента (или 0 для выхода): ");
                        String newIng = scanner.nextLine();
                        if (newIng.equals("0")) break;

                        boolean exists = false;
                        for (Ingredient elem : ingredients) {
                            if (elem.getName().equalsIgnoreCase(newIng)) {
                                exists = true;
                                break;
                            }
                        }

                        if (exists) {
                            System.out.println("Данный ингредиент уже существует, попробуйте снова...\n");
                            continue;
                        }

                        System.out.print("Введите стоимость нового ингредиента (или 0 для выхода): ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();

                        if (newPrice == 0) break;

                        if (newPrice < 0) {
                            System.out.println("Некорректная стоимость. Начните сначала...\n");
                            continue;
                        }

                        ingredients.add(new Ingredient(newIng, newPrice));
                        System.out.println("Ингредиент успешно добавлен!\n");
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        displayListOfIngredients(ingredients);
                        System.out.print("Введите название ингредиента для редактирования (или 0 для выхода): ");
                        String name = scanner.nextLine();
                        if (name.equals("0")) break;

                        Ingredient target = null;
                        for (Ingredient elem : ingredients) {
                            if (elem.getName().equalsIgnoreCase(name)) {
                                target = elem;
                                break;
                            }
                        }

                        if (target == null) {
                            System.out.println("Введен некорректный ингредиент, попробуйте снова...\n");
                            continue;
                        }

                        System.out.println("1. Изменить название");
                        System.out.println("2. Изменить цену");
                        System.out.println("0. Отмена");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();

                        if (choice2 == 0) continue;

                        if (choice2 == 1) {
                            System.out.print("Введите новое название (или 0 для отмены): ");
                            String newName = scanner.nextLine();
                            if (!newName.equals("0")) {
                                target.setName(newName);
                                System.out.println("Название обновлено!\n");
                            }
                        } else if (choice2 == 2) {
                            System.out.print("Введите новую цену (или 0 для отмены): ");
                            double newPrice2 = scanner.nextDouble();
                            scanner.nextLine();
                            if (newPrice2 > 0) {
                                target.setPrice(newPrice2);
                                System.out.println("Цена обновлена!\n");
                            } else if (newPrice2 != 0) {
                                System.out.println("Введена некорректная цена...\n");
                            }
                        } else {
                            System.out.println("Введена некорректная цифра...\n");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        displayListOfIngredients(ingredients);
                        System.out.print("Введите название ингредиента для удаления (или 0 для выхода): ");
                        String delIng = scanner.nextLine();
                        if (delIng.equals("0")) break;

                        Ingredient target = null;
                        for (Ingredient elem : ingredients) {
                            if (elem.getName().equalsIgnoreCase(delIng)) {
                                target = elem;
                                break;
                            }
                        }

                        if (target != null) {
                            ingredients.remove(target);
                            System.out.print("Ингредиент '" + target.getName() + "' удален. Нажмите Enter...");
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Был введен некорректный ингредиент. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 4:
                    displayListOfIngredients(ingredients);
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 5:
                    ArrayList<Ingredient> sortedIngr = new ArrayList<>(ingredients);
                    sortedIngr.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
                    displayListOfIngredients(sortedIngr);
                    break;
                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfIngredients(@NotNull ArrayList<Ingredient> arr) {
        int idx = 1;
        System.out.println("Список всех ингредиентов/цены:");
        for (Ingredient elem : arr) {
            System.out.printf("%d. %s - %.2f руб\n", idx, elem.getName(), elem.getPrice());
            idx++;
        }

    }

    public void manageBases() {
        while (true) {
            System.out.println("\n=== МЕНЮ ОСНОВ ДЛЯ ПИЦЦЫ ===");
            System.out.println("1. Добавить");
            System.out.println("2. Редактировать");
            System.out.println("3. Удалить");
            System.out.println("4. Вывести список существующих основ");
            System.out.println("5. Вывести список основ по цене");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Введите название новой основы (или 0 для выхода): ");
                        String newName = scanner.nextLine();
                        if (newName.equals("0")) break;

                        boolean exists = false;
                        for (PizzaBase elem : base) {
                            if (elem.getName().equalsIgnoreCase(newName)) {
                                exists = true;
                                break;
                            }
                        }

                        if (exists) {
                            System.out.println("Данная основа уже существует, попробуйте снова...\n");
                            continue;
                        }

                        System.out.print("Введите стоимость новой основы (или 0 для выхода): ");
                        double newPrice = scanner.nextDouble();
                        scanner.nextLine();

                        if (newPrice == 0) break;

                        if (newPrice < 0) {
                            System.out.println("Некорректная стоимость. Начните сначала...\n");
                            continue;
                        }

                        PizzaBase newBase = new PizzaBase(newName, newPrice, false);
                        base.add(newBase);

                        System.out.printf("Основа '%s' успешно добавлена! Установленная цена: %.2f руб.\n\n",
                                newBase.getName(), newBase.getPrice());
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        displayListOfBases(base);
                        System.out.print("Введите название основы для редактирования (или 0 для выхода): ");
                        String name = scanner.nextLine();
                        if (name.equals("0")) break;

                        PizzaBase target = null;
                        for (PizzaBase elem : base) {
                            if (elem.getName().equalsIgnoreCase(name)) {
                                target = elem;
                                break;
                            }
                        }

                        if (target == null) {
                            System.out.println("Введена некорректная основа, попробуйте снова...\n");
                            continue;
                        }

                        System.out.println("1. Изменить название");
                        System.out.println("2. Изменить цену");
                        System.out.println("0. Отмена");
                        System.out.print("Выбор: ");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();


                        if (choice2 == 0) continue;

                        if (choice2 == 1) {
                            if (name.equalsIgnoreCase("Классическая")) {
                                System.out.println("Ошибка: У классической основы  нельзя менять название!\n");
                                continue;
                            }
                            System.out.print("Введите новое название (или 0 для отмены): ");
                            String newName = scanner.nextLine();
                            if (!newName.equals("0")) {
                                target.setName(newName);
                                System.out.println("Название обновлено!\n");
                            }
                        } else if (choice2 == 2) {
                            System.out.print("Введите новую цену (или 0 для отмены): ");
                            double newPrice2 = scanner.nextDouble();
                            scanner.nextLine();
                            if (newPrice2 == 0) continue;

                            double oldPrice = target.getPrice();
                            target.setPrice(newPrice2);

                            if (target.getPrice() != newPrice2) {
                                System.out.println("Цена обновлена до максимально возможной!\n");
                            } else if (target.getPrice() != oldPrice) {
                                if (name.equalsIgnoreCase("Классическая") && target.getPrice() < oldPrice) {
                                    for (PizzaBase elem : base) {
                                        if (elem.getPrice() > newPrice2 * 1.2) {
                                            elem.setPrice(newPrice2 * 1.2);
                                            System.out.println("Так как цена классической основы изменилась в меньшую сторону," +
                                                    " ценник " + elem.getName() + " основы изменился до максимально возможного " +
                                                    elem.getPrice() + " руб");
                                        }
                                    }
                                }
                                System.out.println("Цена успешно обновлена!");
                            } else {
                                System.out.println("Введена некорректная стоимость...\n");

                            }
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        displayListOfBases(base);
                        System.out.print("Введите название основы для удаления (или 0 для выхода): ");
                        String delBase = scanner.nextLine();
                        if (delBase.equals("0")) break;

                        if (delBase.equalsIgnoreCase("Классическая")) {
                            System.out.println("Ошибка: Классическую основу удалять нельзя!\n");
                            continue;
                        }

                        PizzaBase target = null;
                        for (PizzaBase elem : base) {
                            if (elem.getName().equalsIgnoreCase(delBase)) {
                                target = elem;
                                break;
                            }
                        }

                        if (target != null) {
                            base.remove(target);
                            System.out.print("Основа '" + target.getName() + "' удалена. Нажмите Enter...");
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Была введена некорректная основа. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 4:
                    displayListOfBases(base);
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 5:
                    ArrayList<PizzaBase> sortedBase = new ArrayList<>(base);
                    sortedBase.sort((o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()));
                    displayListOfBases(sortedBase);

                    break;

                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfBases(@NotNull ArrayList<PizzaBase> arr) {
        int idx = 1;
        System.out.println("\nСписок всех основ/цены:");
        for (PizzaBase elem : arr) {
            System.out.printf("%d. %s - %.2f руб\n", idx, elem.getName(), elem.getPrice());
            idx++;
        }
    }

    public void manageMenuPizzas() {
        while (true) {
            System.out.println("\n=== МЕНЮ ПИЦЦ ===");
            System.out.println("1. Создать новую пиццу");
            System.out.println("2. Редактировать пиццу");
            System.out.println("3. Удалить пиццу");
            System.out.println("4. Вывести список всех пицц");
            System.out.println("5. Вывести список всех пицц по цене");
            System.out.println("6. Фильтрация по ингредиенту");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Введите название новой пиццы (или 0 для выхода): ");
                        String newName = scanner.nextLine();
                        if (newName.equals("0")) break;

                        boolean exists = false;
                        for (Pizza p : pizzas) {
                            if (p.getName().equalsIgnoreCase(newName)) {
                                exists = true;
                                break;
                            }
                        }

                        if (exists) {
                            System.out.println("Пицца с таким названием уже существует, попробуйте снова...\n");
                            continue;
                        }

                        System.out.println("Выберите размер пиццы: " +
                                "\n1. Маленький - 6 кусочков. " +
                                "\n2. Средний - 8 кусочков. " +
                                "\n3. Большой - 12 кусочков.");
                        System.out.print("Введите цифру: ");
                        String sizePizza;
                        int choiceS = scanner.nextInt();
                        scanner.nextLine();

                        switch (choiceS) {
                            case 1:
                                sizePizza = "Маленький";
                                System.out.println("Маленький размер установлен");
                                break;
                            case 2:
                                sizePizza = "Средний";
                                System.out.println("Средний размер установлен");
                                break;
                            case 3:
                                sizePizza = "Большой";
                                System.out.println("Большой размер установлен");
                                break;
                            default:
                                System.out.println("Размер не найден. Попробуйте снова...\n");
                                continue;
                        }

                        PizzaBase selectedBase = null;
                        while (selectedBase == null) {
                            displayListOfBases(base);
                            System.out.print("Введите название основы для пиццы (или 0 для отмены): ");
                            String baseName = scanner.nextLine();
                            if (baseName.equals("0")) break;

                            for (PizzaBase b : base) {
                                if (b.getName().equalsIgnoreCase(baseName)) {
                                    selectedBase = b;
                                    break;
                                }
                            }
                            if (selectedBase == null) {
                                System.out.println("Основа не найдена. Попробуйте снова...\n");
                            }
                        }

                        if (selectedBase == null) break;

                        PizzaSide selectedSide = null;
                        while (selectedSide == null) {
                            displayListOfPizzaSides(sides);
                            System.out.print("Введите название бортика для пиццы (или 0 для того чтобы продолжить): ");
                            String sideName = scanner.nextLine();
                            if (sideName.equals("0")) break;

                            for (PizzaSide b : sides) {
                                if (b.getName().equalsIgnoreCase(sideName)) {
                                    selectedSide = b;
                                    break;
                                }
                            }
                            if (selectedSide == null) {
                                System.out.println("Бортик не найден. Попробуйте снова...\n");
                            }
                        }

                        Pizza newPizza;
                        if (selectedSide != null) {
                            newPizza = new Pizza(newName, selectedBase, sizePizza, selectedSide);
                            selectedSide.addPizza(newPizza);
                            System.out.println("Бортик " + selectedSide.getName() + " успешно добавлен.");
                        } else newPizza = new Pizza(newName, selectedBase, sizePizza);

                        while (true) {
                            displayListOfIngredients(ingredients);
                            System.out.println("Текущая сборка: " + newPizza.getName()
                                    + " | Основа: " + selectedBase.getName()
                                    + " | Размер: " + newPizza.getSize());
                            System.out.print("Введите название ингредиента для добавления (или 0 чтобы завершить сборку): ");
                            String ingName = scanner.nextLine();
                            if (ingName.equals("0")) break;

                            Ingredient selectedIng = null;
                            for (Ingredient ing : ingredients) {
                                if (ing.getName().equalsIgnoreCase(ingName)) {
                                    selectedIng = ing;
                                    break;
                                }
                            }

                            if (selectedIng != null) {
                                newPizza.addIngredient(selectedIng);
                                System.out.println("Ингредиент '" + selectedIng.getName() + "' успешно добавлен!\n");
                            } else {
                                System.out.println("Ингредиент не найден. Попробуйте снова...\n");
                            }
                        }

                        pizzas.add(newPizza);
                        System.out.println("Пицца '" + newPizza.getName() + "' успешно создана и сохранена!\n");
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        displayListOfPizzas(pizzas);
                        System.out.print("Введите название пиццы для редактирования (или 0 для выхода): ");
                        String name = scanner.nextLine();
                        if (name.equals("0")) break;

                        Pizza target = null;
                        for (Pizza p : pizzas) {
                            if (p.getName().equalsIgnoreCase(name)) {
                                target = p;
                                break;
                            }
                        }

                        if (target == null) {
                            System.out.println("Введена некорректная пицца, попробуйте снова...\n");
                            continue;
                        }

                        System.out.println("\nРедактирование пиццы: " + target.getName());
                        System.out.println("1. Изменить название");
                        System.out.println("2. Изменить основу");
                        System.out.println("3. Добавить ингредиент");
                        System.out.println("4. Удалить ингредиент из пиццы");
                        System.out.println("5. Изменить размер пиццы");
                        System.out.println("6. Добавить бортик");
                        System.out.println("7. Удалить бортик");
                        System.out.println("0. Отмена");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice2) {
                            case 1: {
                                System.out.print("Введите новое название (или 0 для отмены): ");
                                String newName = scanner.nextLine();
                                if (!newName.equals("0")) {
                                    target.setName(newName);
                                    System.out.println("Название пиццы обновлено!\n");
                                }
                                break;
                            }
                            case 2: {
                                displayListOfBases(base);
                                System.out.print("Введите название новой основы (или 0 для отмены): ");
                                String baseName = scanner.nextLine();
                                if (baseName.equals("0")) continue;

                                PizzaBase newBase = null;
                                for (PizzaBase b : base) {
                                    if (b.getName().equalsIgnoreCase(baseName)) {
                                        newBase = b;
                                        break;
                                    }
                                }
                                if (newBase != null) {
                                    target.setBase(newBase);
                                    System.out.println("Основа успешно изменена!\n");
                                } else {
                                    System.out.println("Основа не найдена...\n");
                                }
                                break;
                            }

                            case 3: {
                                displayListOfIngredients(ingredients);
                                System.out.print("Введите название ингредиента для добавления (или 0 для отмены): ");
                                String ingName = scanner.nextLine();
                                if (ingName.equals("0")) continue;

                                Ingredient newIng = null;
                                for (Ingredient ing : ingredients) {
                                    if (ing.getName().equalsIgnoreCase(ingName)) {
                                        newIng = ing;
                                        break;
                                    }
                                }
                                if (newIng != null) {
                                    target.addIngredient(newIng);
                                } else {
                                    System.out.println("Ингредиент не найден...\n");
                                }
                                break;
                            }
                            case 4: {
                                if (target.getIngredients().isEmpty()) {
                                    System.out.println("В этой пицце пока нет добавленных ингредиентов (только основа).\n");
                                    continue;
                                }

                                System.out.println("Текущий состав пиццы:");
                                for (Map.Entry<Ingredient, Integer> entry : target.getIngredients().entrySet()) {
                                    Ingredient ing = entry.getKey();
                                    Integer count = entry.getValue();
                                    System.out.println("- " + ing.getName() + ": " + count + " порц");
                                }

                                System.out.print("Введите название ингредиента для удаления (или 0 для отмены): ");
                                String ingName = scanner.nextLine();
                                if (ingName.equals("0")) continue;

                                Ingredient ingToRemove = null;
                                for (Map.Entry<Ingredient, Integer> entry : target.getIngredients().entrySet()) {
                                    Ingredient ing = entry.getKey();
                                    Integer count = entry.getValue();
                                    if (ing.getName().equalsIgnoreCase(ingName)) {
                                        ingToRemove = ing;
                                        break;
                                    }
                                }

                                if (ingToRemove != null) {
                                    target.removeIngredient(ingToRemove);
                                } else {
                                    System.out.println("Такого ингредиента нет в составе этой пиццы...\n");
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("Выберите новый размер пиццы: " +
                                        "\n1. Маленький - 6 кусочков. " +
                                        "\n2. Средний - 8 кусочков. " +
                                        "\n3. Большой - 12 кусочков.");
                                System.out.print("Введите цифру: ");
                                int choiceS = scanner.nextInt();
                                scanner.nextLine();

                                switch (choiceS) {
                                    case 1:
                                        target.setSize("Маленький");
                                        System.out.println("Установлен маленький размер!\n");
                                        break;
                                    case 2:
                                        target.setSize("Средний");
                                        System.out.println("Установлен средний размер!\n");
                                        break;
                                    case 3:
                                        target.setSize("Большой");
                                        System.out.println("Установлен большой размер!\n");
                                        break;
                                    default:
                                        System.out.println("Размер не найден. Попробуйте снова...\n");
                                        continue;
                                }
                                break;
                            }

                            case 6: {
                                if (target.side != null) {
                                    System.out.println("У этой пиццы уже есть бортик");
                                    break;
                                }
                                displayListOfPizzaSides(sides);
                                System.out.print("Введите название бортика (или 0 для выхода): ");
                                String str = scanner.nextLine();

                                if(str.equals("0")) break;

                                PizzaSide newSide = null;
                                for (PizzaSide elem: sides) {
                                    if (elem.getName().equalsIgnoreCase(str)) {
                                        newSide = elem;
                                        break;
                                    }
                                }

                                if (newSide == null) {
                                    System.out.println("Некорректное название бортика, попробуйте снова...");
                                    break;
                                }
                                target.setSide(newSide);
                                newSide.addPizza(target);

                                System.out.println("Бортик успешно добавлен...");
                                break;
                            }

                            case 7: {
                                if (target.side == null) {
                                    System.out.println("У этой пиццы нет бортика");
                                    break;
                                }
                                target.side.removePizza(target);
                                target.setSide(null);
                                System.out.println("Бортик успешно удален...");
                                scanner.nextLine();
                                break;
                            }

                            case 0: {
                                continue;
                            }
                            default: {
                                System.out.println("Введена некорректная цифра...\n");
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        displayListOfPizzas(pizzas);
                        System.out.print("Введите название пиццы для удаления (или 0 для выхода): ");
                        String delPizza = scanner.nextLine();
                        if (delPizza.equals("0")) break;

                        Pizza target = null;
                        for (Pizza p : pizzas) {
                            if (p.getName().equalsIgnoreCase(delPizza)) {
                                target = p;
                                break;
                            }
                        }

                        if (target != null) {
                            pizzas.remove(target);
                            if (target.side != null) target.side.removePizza(target);
                            System.out.print("Пицца '" + target.getName() + "' удалена из меню. Нажмите Enter...");
                            break;
                        } else {
                            System.out.println("Была введена некорректная пицца. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 4:
                    displayListOfPizzas(pizzas);
                    break;

                case 5:
                    ArrayList<Pizza> sortedPizza = new ArrayList<>(pizzas);
                    sortedPizza.sort((o1, o2) -> Double.compare(o1.calculatePrice(), o2.calculatePrice()));
                    displayListOfPizzas(sortedPizza);
                    break;

                case 6:
                    displayListOfIngredients(ingredients);
                    System.out.print("Введите название ингредиента (или 0 для выхода):");
                    String nameIng = scanner.nextLine();

                    if (nameIng.equalsIgnoreCase("0")) continue;

                    ArrayList<Pizza> filterPizza = new ArrayList<>();
                    for (Pizza elem : pizzas) {
                        for (Map.Entry<Ingredient, Integer> entry : elem.getIngredients().entrySet()) {
                            String name = entry.getKey().getName();
                            if (nameIng.equalsIgnoreCase(name)) {
                                filterPizza.add(elem);
                                break;
                            }
                        }
                    }
                    displayListOfPizzas(filterPizza);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfPizzas(@NotNull ArrayList<Pizza> arr) {
        System.out.println("\n=== НАШЕ МЕНЮ ПИЦЦ ===");

        int idx = 1;
        for (Pizza p : arr) {
            System.out.printf("%d. Пицца \"%s\" (Основа: %s) (Размер: %s) (Бортик: %s) - %.2f руб.\n",
                    idx, p.getName(), p.getBase().getName(),p.getSize(), p.check(), p.calculatePrice());

            System.out.print("   Состав: ");
            Map<Ingredient, Integer> ings = p.getIngredients();

            if (ings.isEmpty()) {
                System.out.println("только основа");
            } else {
                System.out.print("|   ");
                for (Map.Entry<Ingredient, Integer> entry : ings.entrySet()) {
                    Ingredient ing = entry.getKey();
                    Integer count = entry.getValue();
                    System.out.print(ing.getName() + ": " + count + " порц   |   ");
                }
            }
            System.out.println();
            idx++;
        }
        System.out.println("\n======================\n");
        System.out.print("Нажмите Enter, чтобы продолжить...");
        scanner.nextLine();
    }

    public void manageMenuSides() {
        while (true) {
            System.out.println("\n=== МЕНЮ БОРТИКОВ ===");
            System.out.println("1. Создать новый бортик");
            System.out.println("2. Редактировать бортик");
            System.out.println("3. Удалить бортик");
            System.out.println("4. Вывести список всех бортиков");
            System.out.println("5. Вывести список всех бортиков по цене");
            System.out.println("6. Фильтрация по ингредиенту");
            System.out.println("7. Показать доступные пиццы для бортика");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    while (true) {
                        System.out.print("Введите название нового бортика (или 0 для выхода): ");
                        String newName = scanner.nextLine();
                        if (newName.equals("0")) break;

                        boolean exists = false;
                        for (PizzaSide s : sides) {
                            if (s.getName().equalsIgnoreCase(newName)) {
                                exists = true;
                                break;
                            }
                        }

                        if (exists) {
                            System.out.println("Бортик с таким названием уже существует, попробуйте снова...\n");
                            continue;
                        }

                        PizzaSide newSide = new PizzaSide(newName);

                        System.out.println("\n--- Сборка состава бортика ---");
                        while (true) {
                            displayListOfIngredients(ingredients);
                            System.out.println("Имя бортика: " + newSide.getName() + " | Текущая цена: " + newSide.calculatePrice() + " руб.");
                            System.out.print("Введите название ингредиента для добавления (или 0 чтобы сохранить бортик): ");
                            String ingName = scanner.nextLine();
                            if (ingName.equals("0")) break;

                            Ingredient selectedIng = null;
                            for (Ingredient ing : ingredients) {
                                if (ing.getName().equalsIgnoreCase(ingName)) {
                                    selectedIng = ing;
                                    break;
                                }
                            }

                            if (selectedIng != null) {
                                newSide.addIngredient(selectedIng);
                                System.out.println("Ингредиент '" + selectedIng.getName() + "' успешно добавлен!\n");
                            } else {
                                System.out.println("Ингредиент не найден. Попробуйте снова...\n");
                            }
                        }
                        sides.add(newSide);
                        System.out.println("Бортик '" + newSide.getName() + "' (Цена: " + newSide.calculatePrice() + " руб.) успешно создан!\n");
                        break;
                    }
                    break;

                case 2:
                    while (true) {
                        if (sides.isEmpty()) {
                            System.out.println("Список бортиков пока пуст.\n");
                            break;
                        }

                        displayListOfPizzaSides(sides);
                        System.out.print("Введите название бортика для редактирования (или 0 для выхода): ");
                        String name = scanner.nextLine();
                        if (name.equals("0")) break;

                        PizzaSide target = null;
                        for (PizzaSide s : sides) {
                            if (s.getName().equalsIgnoreCase(name)) {
                                target = s;
                                break;
                            }
                        }

                        if (target == null) {
                            System.out.println("Введен некорректный бортик, попробуйте снова...\n");
                            continue;
                        }

                        System.out.println("\nРедактирование бортика: " + target.getName());
                        System.out.println("1. Изменить название");
                        System.out.println("2. Добавить ингредиент");
                        System.out.println("3. Удалить ингредиент");
                        System.out.println("0. Отмена");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();

                        if (choice2 == 0) continue;

                        if (choice2 == 1) {
                            System.out.print("Введите новое название (или 0 для отмены): ");
                            String newName = scanner.nextLine();
                            if (!newName.equals("0")) {
                                target.setName(newName);
                                System.out.println("Название обновлено!\n");
                            }
                        } else if (choice2 == 2) {
                            displayListOfIngredients(ingredients);
                            System.out.print("Введите название ингредиента для добавления (или 0 для отмены): ");
                            String ingName = scanner.nextLine();
                            if (ingName.equals("0")) continue;

                            Ingredient newIng = null;
                            for (Ingredient ing : ingredients) {
                                if (ing.getName().equalsIgnoreCase(ingName)) {
                                    newIng = ing;
                                    break;
                                }
                            }
                            if (newIng != null) {
                                target.addIngredient(newIng);
                                System.out.println("Ингредиент добавлен! Новая цена бортика: " + target.calculatePrice() + "\n");
                            } else {
                                System.out.println("Ингредиент не найден...\n");
                            }
                        } else if (choice2 == 3) {
                            if (target.getIngredients().isEmpty()) {
                                System.out.println("Состав этого бортика пуст.\n");
                                continue;
                            }
                            System.out.println("Текущий состав бортика:");
                            for (Ingredient ing : target.getIngredients()) {
                                System.out.println("- " + ing.getName());
                            }
                            System.out.print("Введите название ингредиента для удаления (или 0 для отмены): ");
                            String ingName = scanner.nextLine();
                            if (ingName.equals("0")) continue;

                            Ingredient ingToRemove = null;
                            for (Ingredient ing : target.getIngredients()) {
                                if (ing.getName().equalsIgnoreCase(ingName)) {
                                    ingToRemove = ing;
                                    break;
                                }
                            }
                            if (ingToRemove != null) {
                                target.removeIngredient(ingToRemove);
                                System.out.println("Ингредиент удален! Новая цена бортика: " + target.calculatePrice() + "\n");
                            } else {
                                System.out.println("Такого ингредиента нет в этом бортике...\n");
                            }
                        } else {
                            System.out.println("Введена некорректная цифра...\n");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        if (sides.isEmpty()) {
                            System.out.println("Список бортиков пока пуст.\n");
                            break;
                        }

                        displayListOfPizzaSides(sides);
                        System.out.print("Введите название бортика для удаления (или 0 для выхода): ");
                        String delName = scanner.nextLine();
                        if (delName.equals("0")) break;

                        PizzaSide target = null;
                        for (PizzaSide s : sides) {
                            if (s.getName().equalsIgnoreCase(delName)) {
                                target = s;
                                break;
                            }
                        }

                        if (target != null) {
                            sides.remove(target);
                            System.out.print("Бортик '" + target.getName() + "' удален. Нажмите Enter...");
                            scanner.nextLine();
                            for(Pizza elem : target.getPizzas()) {
                                elem.side = null;
                            }
                            break;
                        } else {
                            System.out.println("Был введен некорректный бортик. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 4:
                    displayListOfPizzaSides(sides);
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 5:
                    ArrayList<PizzaSide> sortedPizzaSide = new ArrayList<>(sides);
                    sortedPizzaSide.sort((o1, o2) -> Double.compare(o1.calculatePrice(), o2.calculatePrice()));
                    displayListOfPizzaSides(sortedPizzaSide);
                    break;

                case 6:
                    displayListOfIngredients(ingredients);
                    System.out.print("Введите название ингредиента (или 0 для выхода):");
                    String nameIng = scanner.nextLine();

                    if (nameIng.equalsIgnoreCase("0")) continue;

                    ArrayList<PizzaSide> filterSide = new ArrayList<>();
                    for (PizzaSide elem : sides) {
                        for (Ingredient ing : elem.getIngredients()) {
                            if (nameIng.equalsIgnoreCase(ing.getName())) {
                                filterSide.add(elem);
                                break;
                            }
                        }
                    }
                    displayListOfPizzaSides(filterSide);
                    break;

                case 7:
                    while (true) {
                        displayListOfPizzaSides(sides);
                        System.out.print("Введите название интересующего бортика (или 0 для выхода): ");
                        String sideName = scanner.nextLine();
                        if (sideName.equals("0")) break;

                        PizzaSide target = null;
                        for (PizzaSide s : sides) {
                            if (s.getName().equalsIgnoreCase(sideName)) {
                                target = s;
                                break;
                            }
                        }

                        if (target != null) {
                            System.out.println("\nБортик '" + target.getName() + "' можно использовать со следующими пиццами:");
                            if (target.getPizzas().isEmpty()) {
                                System.out.println(" - (Список разрешенных пицц пуст)");
                            } else {
                                for (Pizza p : target.getPizzas()) {
                                    System.out.println(" - " + p.getName());
                                }
                            }
                            System.out.println();
                        } else {
                            System.out.println("Бортик не найден. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfPizzaSides(@NotNull ArrayList<PizzaSide> arr) {
        System.out.println("\n=== СПИСОК БОРТИКОВ ===");
        if (arr.isEmpty()) {
            System.out.println("Список бортиков пока пуст.");
            return;
        }

        int idx = 1;
        for (PizzaSide s : arr) {
            System.out.printf("%d. Бортик \"%s\" - %.2f руб.\n", idx, s.getName(), s.calculatePrice());
            System.out.print("   Состав: ");

            if (s.getIngredients().isEmpty()) {
                System.out.println("пусто");
            } else {
                ArrayList<String> ingNames = new ArrayList<>();
                for (Ingredient ing : s.getIngredients()) {
                    ingNames.add(ing.getName());
                }
                System.out.println(String.join(", ", ingNames));
            }
            idx++;
        }
        System.out.println("=======================\n");
    }

    public void manageMenuOrder() {
        ArrayList<OrderItem> currentOrderItems = new ArrayList<>();

        while (true) {
            System.out.println("\n=== МЕНЮ ЗАКАЗОВ ===");
            System.out.println("1. Создать заказ");
            System.out.println("2. Удалить заказ");
            System.out.println("3. Вывести список всех заказов");
            System.out.println("4. Фильтр по дате.");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: {
                    boolean isBuildingOrder = true;

                    ArrayList<Guest> currentGuests = new ArrayList<>();
                    System.out.print("Желаете разделить заказ по гостям? (y/n): ");
                    String splitBill = scanner.nextLine();
                    if (splitBill.equalsIgnoreCase("y")) {
                        System.out.print("Введите количество гостей: ");
                        int guestCount = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < guestCount; i++) {
                            System.out.print("Введите имя гостя " + (i + 1) + ": ");
                            currentGuests.add(new Guest(scanner.nextLine()));
                        }
                    }

                    while (isBuildingOrder) {
                        System.out.println("\n--- СБОРКА ЗАКАЗА ---");
                        System.out.println("В корзине позиций: " + currentOrderItems.size());
                        System.out.println("1. Добавить пиццу из списка");
                        System.out.println("2. Создать пиццу 50 на 50 из готового списка");
                        System.out.println("3. Создать кастомную пиццу");
                        System.out.println("4. Перейти к оформлению заказа");
                        System.out.println("0. Отменить сборку и выйти");
                        System.out.print("Выбор: ");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice2) {
                            case 1: {
                                displayListOfPizzas(pizzas);
                                System.out.print("Введите название пиццы (или 0 для отмены): ");
                                String pizzaName = scanner.nextLine();
                                if (pizzaName.equals("0")) continue;

                                Pizza template = null;
                                for (Pizza p : pizzas) {
                                    if (p.getName().equalsIgnoreCase(pizzaName)) {
                                        template = p;
                                        break;
                                    }
                                }

                                if (template == null) {
                                    System.out.println("Пицца не найдена, попробуйте снова...\n");
                                    continue;
                                }

                                Pizza orderedPizza;
                                if (template.getSide() != null) orderedPizza = new Pizza(template.getName(),
                                        template.getBase(),
                                        template.getSize(),
                                        template.getSide());
                                else
                                    orderedPizza = new Pizza(template.getName(), template.getBase(), template.getSize());

                                for (Map.Entry<Ingredient, Integer> entry : template.getIngredients().entrySet()) {
                                    Ingredient ing = entry.getKey();
                                    orderedPizza.addIngredient(ing);
                                }

                                while (true) {
                                    System.out.println("\nТекущий состав пиццы '" + orderedPizza.getName() + "':");
                                    for (Map.Entry<Ingredient, Integer> entry : orderedPizza.getIngredients().entrySet()) {
                                        Ingredient ing = entry.getKey();
                                        Integer count = entry.getValue();
                                        System.out.println("- " + ing.getName() + " " + count + " порц" + " (" + ing.getPrice() + " руб.)");
                                    }

                                    System.out.print("Введите название ингредиента для того чтобы увеличить на одну порцию" +
                                            " (или 0 чтобы добавить пиццу в корзину): ");
                                    String ingName = scanner.nextLine();
                                    if (ingName.equals("0")) break;

                                    Ingredient foundToDouble = null;
                                    for (Map.Entry<Ingredient, Integer> entry : orderedPizza.getIngredients().entrySet()) {
                                        Ingredient ing = entry.getKey();
                                        if (ing.getName().equalsIgnoreCase(ingName)) {
                                            foundToDouble = ing;
                                            break;
                                        }
                                    }

                                    if (foundToDouble != null) {
                                        orderedPizza.addIngredient(foundToDouble);
                                    } else {
                                        System.out.println("Такого ингредиента нет в базовом составе этой пиццы...\n");
                                    }
                                }

                                ArrayList<Guest> itemOwners = new ArrayList<>();
                                if (!currentGuests.isEmpty()) {
                                    System.out.println("\nКто будет платить за пиццу '" + orderedPizza.getName() + "'?");
                                    for (int i = 0; i < currentGuests.size(); i++) {
                                        System.out.println((i + 1) + ". " + currentGuests.get(i).getName());
                                    }

                                    while (true) {
                                        System.out.print("Введите номер гостя (или 0 чтобы завершить): ");
                                        int gIndex = scanner.nextInt();
                                        scanner.nextLine();

                                        if (gIndex == 0) {
                                            if (itemOwners.isEmpty()) {
                                                System.out.println("Ошибка: вы должны выбрать хотя бы одного плательщика!");
                                                continue;
                                            } else {
                                                break;
                                            }
                                        }

                                        if (gIndex > 0 && gIndex <= currentGuests.size()) {
                                            Guest selectedGuest = currentGuests.get(gIndex - 1);

                                            if (!itemOwners.contains(selectedGuest)) {
                                                itemOwners.add(selectedGuest);
                                                System.out.println("Гость '" + selectedGuest.getName() + "' добавлен к оплате.");
                                            } else {
                                                System.out.println("Этот гость уже добавлен.");
                                            }
                                        } else {
                                            System.out.println("Гостя с таким номером нет. Попробуйте снова.");
                                        }
                                    }
                                }

                                currentOrderItems.add(new OrderItem(orderedPizza, itemOwners));
                                System.out.println("Пицца добавлена в корзину!\n");
                                continue;
                            }
                            case 2: {
                                System.out.print("Введите название для вашей новой пиццы 50/50 (или 0 для отмены): ");
                                String customName = scanner.nextLine();
                                if (customName.equals("0")) continue;

                                System.out.println("Выберите размер пиццы:");
                                System.out.println("1. Маленький");
                                System.out.println("2. Средний");
                                System.out.println("3. Большой");
                                System.out.print("Выбор (цифра): ");
                                int sizeChoice = scanner.nextInt();
                                scanner.nextLine();

                                String selectedSize = "";
                                if (sizeChoice == 1) selectedSize = "Маленький";
                                else if (sizeChoice == 2) selectedSize = "Средний";
                                else if (sizeChoice == 3) selectedSize = "Большой";
                                else {
                                    System.out.println("Некорректный размер!\n");
                                    continue;
                                }

                                ArrayList<Pizza> availablePizzas = new ArrayList<>();
                                for (Pizza p : pizzas) {
                                    if (p.getSize().equalsIgnoreCase(selectedSize)) {
                                        availablePizzas.add(p);
                                    }
                                }

                                if (availablePizzas.size() < 2) {
                                    System.out.println("Недостаточно пицц размера '" + selectedSize + "' в меню для создания 50/50 (нужно минимум 2).\n");
                                    continue;
                                }

                                System.out.println("\nДоступные пиццы размера '" + selectedSize + "':");
                                for (Pizza p : availablePizzas) {
                                    System.out.println("- " + p.getName());
                                }

                                Pizza firstHalf = null;
                                while (firstHalf == null) {
                                    System.out.print("Введите название первой пиццы (или 0 для отмены): ");
                                    String pName = scanner.nextLine();
                                    if (pName.equals("0")) break;

                                    for (Pizza p : availablePizzas) {
                                        if (p.getName().equalsIgnoreCase(pName)) {
                                            firstHalf = p;
                                            break;
                                        }
                                    }
                                    if (firstHalf == null) System.out.println("Пицца не найдена, попробуйте снова.");
                                }
                                if (firstHalf == null) continue;

                                Pizza secondHalf = null;
                                while (secondHalf == null) {
                                    System.out.print("Введите название второй пиццы (или 0 для отмены): ");
                                    String pName = scanner.nextLine();
                                    if (pName.equals("0")) break;

                                    if (pName.equalsIgnoreCase(firstHalf.getName())) {
                                        System.out.println("Вторая половинка должна отличаться от первой! Выберите другую пиццу.");
                                        continue;
                                    }

                                    for (Pizza p : availablePizzas) {
                                        if (p.getName().equalsIgnoreCase(pName)) {
                                            secondHalf = p;
                                            break;
                                        }
                                    }
                                    if (secondHalf == null) System.out.println("Пицца не найдена, попробуйте снова.");
                                }
                                if (secondHalf == null) continue;

                                boolean check = true;
                                PizzaBase selectedBase = null;
                                while (check) {
                                    System.out.println("\nВыберите основу для пиццы:");
                                    System.out.println("1. Взять основу от '" + firstHalf.getName() + "' (" + firstHalf.getBase().getName() + ")");
                                    System.out.println("2. Взять основу от '" + secondHalf.getName() + "' (" + secondHalf.getBase().getName() + ")");
                                    System.out.print("Ваш выбор(цифра): ");
                                    int baseChoice = scanner.nextInt();
                                    scanner.nextLine();

                                    switch (baseChoice) {
                                        case 1:
                                            selectedBase = firstHalf.getBase();
                                            check = false;
                                            break;
                                        case 2:
                                            selectedBase = secondHalf.getBase();
                                            check = false;
                                            break;
                                        default:
                                            System.out.println("Некорректный ввод, попробуйте снова...");
                                            break;
                                    }
                                }
                                PizzaFiftyToFifty comboPizza = new PizzaFiftyToFifty(
                                        customName,
                                        selectedBase,
                                        selectedSize,
                                        firstHalf,
                                        secondHalf
                                );

                                ArrayList<Guest> itemOwners = new ArrayList<>();
                                if (!currentGuests.isEmpty()) {
                                    System.out.println("\nКто будет платить за пиццу '" + comboPizza.getName() + "'?");
                                    for (int i = 0; i < currentGuests.size(); i++) {
                                        System.out.println((i + 1) + ". " + currentGuests.get(i).getName());
                                    }

                                    while (true) {
                                        System.out.print("Введите номер гостя (или 0 чтобы завершить): ");
                                        int gIndex = scanner.nextInt();
                                        scanner.nextLine();

                                        if (gIndex == 0) {
                                            if (itemOwners.isEmpty()) {
                                                System.out.println("Ошибка: вы должны выбрать хотя бы одного плательщика!");
                                                continue;
                                            } else {
                                                break;
                                            }
                                        }

                                        if (gIndex > 0 && gIndex <= currentGuests.size()) {
                                            Guest selectedGuest = currentGuests.get(gIndex - 1);

                                            if (!itemOwners.contains(selectedGuest)) {
                                                itemOwners.add(selectedGuest);
                                                System.out.println("Гость '" + selectedGuest.getName() + "' добавлен к оплате.");
                                            } else {
                                                System.out.println("Этот гость уже добавлен.");
                                            }
                                        } else {
                                            System.out.println("Гостя с таким номером нет. Попробуйте снова.");
                                        }
                                    }
                                }

                                currentOrderItems.add(new OrderItem(comboPizza, itemOwners));
                                System.out.println("Пицца добавлена в корзину!\n");
                                continue;
                            }
                            case 3: {
                                System.out.print("Введите название вашей авторской пиццы (или 0 для отмены): ");
                                String customName = scanner.nextLine();
                                if (customName.equals("0")) continue;

                                displayListOfBases(base);
                                System.out.print("Введите название основы для пиццы (или 0 для отмены): ");
                                String baseName = scanner.nextLine();
                                if (baseName.equals("0")) continue;

                                PizzaBase selectedBase = null;
                                for (PizzaBase b : base) {
                                    if (b.getName().equalsIgnoreCase(baseName)) {
                                        selectedBase = b;
                                        break;
                                    }
                                }
                                if (selectedBase == null) {
                                    System.out.println("Основа не найдена...\n");
                                    continue;
                                }

                                System.out.println("Выберите размер пиццы:");
                                System.out.println("1. Маленький (6 кусочков)");
                                System.out.println("2. Средний (8 кусочков)");
                                System.out.println("3. Большой (12 кусочков)");
                                System.out.print("Выбор (цифра): ");
                                int sizeChoice = scanner.nextInt();
                                scanner.nextLine();

                                String selectedSize = "";
                                if (sizeChoice == 1) selectedSize = "Маленький";
                                else if (sizeChoice == 2) selectedSize = "Средний";
                                else if (sizeChoice == 3) selectedSize = "Большой";
                                else {
                                    System.out.println("Некорректный размер!\n");
                                    continue;
                                }

                                PizzaSlice customSlicePizza = new PizzaSlice(customName, selectedBase, selectedSize);
                                boolean cancelPizza = false;

                                while (!customSlicePizza.isFullyFilled()) {
                                    System.out.print("\nОсталось заполнить кусочки: ");
                                    boolean[] status = customSlicePizza.getFilledStatus();
                                    for (int i = 0; i < status.length; i++) {
                                        if (!status[i]) System.out.print((i + 1) + " ");
                                    }
                                    System.out.println();
                                    System.out.print("Введите номер кусочка (например '1') или диапазон (например '1 5') (или 0 для отмены): ");
                                    String rangeInput = scanner.nextLine();

                                    if (rangeInput.equals("0")) {
                                        cancelPizza = true;
                                        break;
                                    }

                                    int startSlice = -1, endSlice = -1;
                                    try {
                                        String[] parts = rangeInput.split(" ");
                                        if (parts.length == 1) {
                                            startSlice = Integer.parseInt(parts[0]);
                                            endSlice = startSlice;
                                        } else if (parts.length == 2) {
                                            startSlice = Integer.parseInt(parts[0]);
                                            endSlice = Integer.parseInt(parts[1]);
                                        } else {
                                            throw new NumberFormatException();
                                        }
                                    } catch (NumberFormatException e) {
                                        System.out.println("Некорректный ввод! Введите одно число или два числа через пробел.");
                                        continue;
                                    }

                                    if (startSlice > endSlice || startSlice < 1 || endSlice > customSlicePizza.getSliceCount()) {
                                        System.out.println("Введен некорректный диапазон кусочков!");
                                        continue;
                                    }

                                    boolean alreadyFilled = false;
                                    for (int i = startSlice - 1; i < endSlice; i++) {
                                        if (status[i]) alreadyFilled = true;
                                    }
                                    if (alreadyFilled) {
                                        System.out.println("В этом диапазоне есть уже заполненные кусочки. Выберите только пустые.");
                                        continue;
                                    }

                                    Map<Ingredient, Integer> tempIngredients = new HashMap<>();
                                    while (true) {
                                        displayListOfIngredients(ingredients);
                                        System.out.println("Текущая начинка для кусочков " + startSlice + "-" + endSlice + ":");
                                        for (Map.Entry<Ingredient, Integer> entry : tempIngredients.entrySet()) {
                                            System.out.println("- " + entry.getKey().getName() + " x" + entry.getValue());
                                        }

                                        System.out.print("Введите название ингредиента (или 0 чтобы завершить начинку): ");
                                        String ingName = scanner.nextLine();
                                        if (ingName.equals("0")) break;

                                        Ingredient selectedIng = null;
                                        for (Ingredient ing : ingredients) {
                                            if (ing.getName().equalsIgnoreCase(ingName)) {
                                                selectedIng = ing;
                                                break;
                                            }
                                        }

                                        if (selectedIng != null) {
                                            tempIngredients.put(selectedIng, tempIngredients.getOrDefault(selectedIng, 0) + 1);
                                            System.out.println("Ингредиент добавлен!\n");
                                        } else {
                                            System.out.println("Ингредиент не найден...\n");
                                        }
                                    }

                                    PizzaSide tempSide = null;
                                    while (true) {
                                        displayListOfPizzaSides(sides);
                                        System.out.print("Введите название бортика для этих кусочков (или 0 если бортик не нужен): ");
                                        String sideName = scanner.nextLine();
                                        if (sideName.equals("0")) break;

                                        for (PizzaSide s : sides) {
                                            if (s.getName().equalsIgnoreCase(sideName)) {
                                                tempSide = s;
                                                break;
                                            }
                                        }

                                        if (tempSide != null) {
                                            System.out.println("Бортик '" + tempSide.getName() + "' успешно применен!\n");
                                            break;
                                        } else {
                                            System.out.println("Бортик не найден...\n");
                                        }
                                    }

                                    customSlicePizza.fillSlices(startSlice, endSlice, tempIngredients, tempSide);
                                    System.out.println("Диапазон кусочков успешно заполнен!\n");
                                }

                                if (cancelPizza) {
                                    System.out.println("Сборка кастомной пиццы отменена.\n");
                                    continue;
                                }

                                ArrayList<Guest> itemOwners = new ArrayList<>();
                                if (!currentGuests.isEmpty()) {
                                    System.out.println("\nКто будет платить за пиццу '" + customSlicePizza.getName() + "'?");
                                    for (int i = 0; i < currentGuests.size(); i++) {
                                        System.out.println((i + 1) + ". " + currentGuests.get(i).getName());
                                    }

                                    while (true) {
                                        System.out.print("Введите номер гостя (или 0 чтобы завершить): ");
                                        int gIndex = scanner.nextInt();
                                        scanner.nextLine();

                                        if (gIndex == 0) {
                                            if (itemOwners.isEmpty()) {
                                                System.out.println("Ошибка: вы должны выбрать хотя бы одного плательщика!");
                                                continue;
                                            } else {
                                                break;
                                            }
                                        }

                                        if (gIndex > 0 && gIndex <= currentGuests.size()) {
                                            Guest selectedGuest = currentGuests.get(gIndex - 1);

                                            if (!itemOwners.contains(selectedGuest)) {
                                                itemOwners.add(selectedGuest);
                                                System.out.println("Гость '" + selectedGuest.getName() + "' добавлен к оплате.");
                                            } else {
                                                System.out.println("Этот гость уже добавлен.");
                                            }
                                        } else {
                                            System.out.println("Гостя с таким номером нет. Попробуйте снова.");
                                        }
                                    }
                                }

                                currentOrderItems.add(new OrderItem(customSlicePizza, itemOwners));
                                System.out.println("Пицца добавлена в корзину!\n");
                                continue;
                            }
                            case 4: {
                                if (currentOrderItems.isEmpty()) {
                                    System.out.println("Ваша корзина пуста! Добавьте хотя бы одну позицию.\n");
                                    continue;
                                }

                                System.out.print("Введите комментарий к заказу (или оставьте пустым): ");
                                String comment = scanner.nextLine();

                                System.out.print("Желаете сделать заказ отложенным? (y/n): ");
                                String choice3 = scanner.nextLine();

                                LocalDateTime targetTime = null;
                                switch (choice3) {
                                    case "y":
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                                        System.out.print("Введите дату и время (формат: 05.03.2026 15:30): ");
                                        String inputTime = scanner.nextLine();
                                        targetTime = LocalDateTime.parse(inputTime, formatter);
                                        break;
                                    case "n":
                                        break;
                                    default:
                                        System.out.println("Некорректный ввод, попробуйте еще раз...");
                                        continue;
                                }

                                Order newOrder;
                                if (targetTime != null)
                                    newOrder = new Order(comment, targetTime);
                                else
                                    newOrder = new Order(comment);

                                for (OrderItem item : currentOrderItems) {
                                    newOrder.addItem(item);
                                }
                                orders.add(newOrder);
                                newOrder.printReceipt();
                                currentOrderItems.clear();
                                isBuildingOrder = false;
                                break;
                            }
                            case 0:
                                currentOrderItems.clear();
                                isBuildingOrder = false;
                                break;
                            default:
                                System.out.println("Введен некорректный символ...\n");
                                break;
                        }
                    }
                    break;
                }
                case 2: {
                    displayListOfOrder(orders);
                    System.out.print("Введите id для удаления заказа (или 0 для отмены):");
                    String choic3 = scanner.nextLine();
                    if (choic3.equals("0")) break;

                    Order delOrder = null;
                    for (Order order : orders) {
                        if (Objects.equals(choic3, order.getId())) {
                            delOrder = order;
                            break;
                        }
                    }

                    if (delOrder != null) {
                        orders.remove(delOrder);
                        System.out.println("Заказ с id " + delOrder.getId() + " успешно удален.");
                    } else {
                        System.out.println("Заказ с таким id " + delOrder.getId() + " не найден, попробуйте снова");
                    }
                    break;
                }
                case 3: {
                    displayListOfOrder(orders);
                    break;
                }
                case 4:
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    System.out.print("Введите дату и время (формат: 05.03.2026 15:30): ");
                    String inputTime = scanner.nextLine();

                    if (inputTime.equalsIgnoreCase("0")) continue;
                    LocalDateTime targetTime = LocalDateTime.parse(inputTime, formatter);

                    ArrayList<Order> filterOrder = new ArrayList<>();
                    for (Order elem : orders) {
                        if (Objects.equals(elem.getOrderTime(), targetTime))
                            filterOrder.add(elem);
                    }
                    displayListOfOrder(filterOrder);
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfOrder(@NotNull ArrayList<Order> arr) {
        System.out.println("\n=== СПИСОК ЗАКАЗОВ ===");
        if (arr.isEmpty()) {
            System.out.println("Список заказов пока пуст.");
            return;
        }

        int idx = 1;
        for (Order o : arr) {
            System.out.printf("ЗАКАЗ № - %d", idx);
            o.printReceipt();
            idx++;
        }
        System.out.println("=======================\n");
    }
}
