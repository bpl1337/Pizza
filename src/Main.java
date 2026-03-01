import java.util.*;
import java.time.LocalDate;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Ingredient> ingredients = new ArrayList<>();
    private static ArrayList<PizzaBase> base = new ArrayList<>();
    private static ArrayList<Pizza> pizzas = new ArrayList<>();
    private static ArrayList<PizzaSide> sides = new ArrayList<>();


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

        Pizza standartP = new Pizza("Стандарт", base.getFirst());
        standartP.addIngredient(ingredients.get(0));
        standartP.addIngredient(ingredients.get(2));
        standartP.addIngredient(ingredients.get(3));
        pizzas.add(standartP);

        PizzaSide standartS = new PizzaSide("Базовый");
        standartS.addIngredient(ingredients.get(1));
        standartS.addIngredient(ingredients.get(2));
        standartS.addPizza(pizzas.getFirst());
        sides.add(standartS);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== ПИЦЦЕРИЯ ===");
            System.out.println("1. Управление ингредиентами");
            System.out.println("2. Управление основами");
            System.out.println("3. Меню пицц");
            System.out.println("4. Меню бортиков");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: manageIngredients(); break;
                case 2: manageBases(); break;
                case 3: manageMenuPizzas(); break;
                case 4: manageMenuSides(); break;
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
                        displayListOfIngredients();
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
                        displayListOfIngredients();
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
                    displayListOfIngredients();
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfIngredients() {
        int idx = 1;
        System.out.println("Список всех ингредиентов/цены:");
        for (Ingredient elem : ingredients) {
            System.out.printf("%d. %s / %.2f \n", idx, elem.getName(), elem.getPrice());
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
                        displayListOfBases();
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

                            if (newPrice2 == 0) continue;

                            double oldPrice = target.getPrice();
                            target.setPrice(newPrice2);

                            if (target.getPrice() != oldPrice) {
                                System.out.println("Цена успешно обновлена!\n");
                            }
                        } else {
                            System.out.println("Введена некорректная цифра...\n");
                        }
                    }
                    break;

                case 3:
                    while (true) {
                        displayListOfBases();
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
                    displayListOfBases();
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfBases() {
        int idx = 1;
        System.out.println("\nСписок всех основ/цены:");
        for (PizzaBase elem : base) {
            System.out.printf("%d. %s / %.2f \n", idx, elem.getName(), elem.getPrice());
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

                        PizzaBase selectedBase = null;
                        while (selectedBase == null) {
                            displayListOfBases();
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

                        Pizza newPizza = new Pizza(newName, selectedBase);

                        while (true) {
                            displayListOfIngredients();
                            System.out.println("Текущая сборка: " + newPizza.getName() + " | Основа: " + selectedBase.getName());
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
                        if (pizzas.isEmpty()) {
                            System.out.println("Меню пицц пока пусто.\n");
                            break;
                        }

                        displayListOfPizzas();
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
                        System.out.println("0. Отмена");
                        int choice2 = scanner.nextInt();
                        scanner.nextLine();

                        if (choice2 == 0) continue;

                        if (choice2 == 1) {
                            System.out.print("Введите новое название (или 0 для отмены): ");
                            String newName = scanner.nextLine();
                            if (!newName.equals("0")) {
                                target.setName(newName);
                                System.out.println("Название пиццы обновлено!\n");
                            }
                        } else if (choice2 == 2) {
                            displayListOfBases();
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
                        } else if (choice2 == 3) {
                            displayListOfIngredients();
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
                                System.out.println("Ингредиент успешно добавлен!\n");
                            } else {
                                System.out.println("Ингредиент не найден...\n");
                            }
                        } else if (choice2 == 4) {
                            if (target.getIngredients().isEmpty()) {
                                System.out.println("В этой пицце пока нет добавленных ингредиентов (только основа).\n");
                                continue;
                            }

                            System.out.println("Текущий состав пиццы:");
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
                                System.out.println("Ингредиент '" + ingToRemove.getName() + "' успешно удален из пиццы!\n");
                            } else {
                                System.out.println("Такого ингредиента нет в составе этой пиццы...\n");
                            }
                        } else {
                            System.out.println("Введена некорректная цифра...\n");
                        }
                    }
                    break;
                case 3:
                    while (true) {
                        if (pizzas.isEmpty()) {
                            System.out.println("Меню пицц пока пусто.\n");
                            break;
                        }

                        displayListOfPizzas();
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
                            System.out.print("Пицца '" + target.getName() + "' удалена из меню. Нажмите Enter...");
                            scanner.nextLine();
                            break;
                        } else {
                            System.out.println("Была введена некорректная пицца. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 4:
                    displayListOfPizzas();
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

    public void displayListOfPizzas() {
        System.out.println("\n=== НАШЕ МЕНЮ ПИЦЦ ===");
        if (pizzas.isEmpty()) {
            System.out.println("Список пицц пока пуст.");
            return;
        }

        int idx = 1;
        for (Pizza p : pizzas) {
            System.out.printf("%d. Пицца \"%s\" (Основа: %s) - %.2f руб.\n",
                    idx, p.getName(), p.getBase().getName(), p.calculatePrice());

            System.out.print("   Состав: ");
            ArrayList<Ingredient> ings = p.getIngredients();

            if (ings.isEmpty()) {
                System.out.println("только основа");
            } else {
                ArrayList<String> ingNames = new ArrayList<>();
                for (Ingredient ing : ings) {
                    ingNames.add(ing.getName());
                }
                System.out.println(String.join(", ", ingNames));
            }
            idx++;
        }
        System.out.println("======================\n");
    }

    public void manageMenuSides() {
        while (true) {
            System.out.println("\n=== МЕНЮ БОРТИКОВ ===");
            System.out.println("1. Создать новый бортик");
            System.out.println("2. Редактировать бортик");
            System.out.println("3. Удалить бортик");
            System.out.println("4. Вывести список всех бортиков");
            System.out.println("5. Показать доступные пиццы для бортика");
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

                        System.out.println("\n--- Шаг 1: Сборка состава бортика ---");
                        while (true) {
                            displayListOfIngredients();
                            System.out.println("Имя бортика: " + newSide.getName() + " | Текущая цена: " + newSide.calculatePrice() + " руб.");
                            System.out.print("Введите название ингредиента для добавления (или 0 чтобы перейти к выбору пицц): ");
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

                        System.out.println("\n--- Шаг 2: Привязка бортика к пиццам ---");
                        if (pizzas.isEmpty()) {
                            System.out.println("В меню пока нет пицц. Бортик будет сохранен без привязок.");
                        } else {
                            while (true) {
                                displayListOfPizzas();
                                System.out.println("Разрешенные пиццы для '" + newSide.getName() + "': " + newSide.getPizzas().size() + " шт.");
                                System.out.print("Введите название пиццы, с которой можно использовать этот бортик (или 0 чтобы завершить): ");
                                String pizzaName = scanner.nextLine();
                                if (pizzaName.equals("0")) break;

                                Pizza selectedPizza = null;
                                for (Pizza p : pizzas) {
                                    if (p.getName().equalsIgnoreCase(pizzaName)) {
                                        selectedPizza = p;
                                        break;
                                    }
                                }

                                if (selectedPizza != null) {
                                    if (!newSide.getPizzas().contains(selectedPizza)) {
                                        newSide.addPizza(selectedPizza);
                                        System.out.println("Пицца '" + selectedPizza.getName() + "' добавлена в список разрешенных!\n");
                                    } else {
                                        System.out.println("Эта пицца уже есть в списке разрешенных!\n");
                                    }
                                } else {
                                    System.out.println("Пицца не найдена. Попробуйте снова...\n");
                                }
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

                        displayListOfPizzaSides();
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
                        System.out.println("4. Разрешить для новой пиццы");
                        System.out.println("5. Запретить для пиццы");
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
                            displayListOfIngredients();
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
                        } else if (choice2 == 4) {
                            displayListOfPizzas();
                            System.out.print("Введите название пиццы для добавления в разрешенные (или 0 для отмены): ");
                            String pName = scanner.nextLine();
                            if (pName.equals("0")) continue;

                            Pizza newAllowedPizza = null;
                            for (Pizza p : pizzas) {
                                if (p.getName().equalsIgnoreCase(pName)) {
                                    newAllowedPizza = p;
                                    break;
                                }
                            }
                            if (newAllowedPizza != null) {
                                if (!target.getPizzas().contains(newAllowedPizza)) {
                                    target.addPizza(newAllowedPizza);
                                    System.out.println("Пицца разрешена для этого бортика!\n");
                                } else {
                                    System.out.println("Эта пицца уже разрешена!\n");
                                }
                            } else {
                                System.out.println("Пицца не найдена...\n");
                            }
                        } else if (choice2 == 5) {
                            if (target.getPizzas().isEmpty()) {
                                System.out.println("Список разрешенных пицц пуст.\n");
                                continue;
                            }
                            System.out.println("Разрешенные пиццы:");
                            for (Pizza p : target.getPizzas()) {
                                System.out.println("- " + p.getName());
                            }
                            System.out.print("Введите название пиццы для удаления из разрешенных (или 0 для отмены): ");
                            String pName = scanner.nextLine();
                            if (pName.equals("0")) continue;

                            Pizza pToRemove = null;
                            for (Pizza p : target.getPizzas()) {
                                if (p.getName().equalsIgnoreCase(pName)) {
                                    pToRemove = p;
                                    break;
                                }
                            }
                            if (pToRemove != null) {
                                target.removePizza(pToRemove);
                                System.out.println("Пицца удалена из списка разрешенных!\n");
                            } else {
                                System.out.println("Этой пиццы нет в списке разрешенных...\n");
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

                        displayListOfPizzaSides();
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
                            break;
                        } else {
                            System.out.println("Был введен некорректный бортик. Попробуйте снова...\n");
                        }
                    }
                    break;

                case 4:
                    displayListOfPizzaSides();
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 5:
                    while (true) {
                        if (sides.isEmpty()) {
                            System.out.println("Список бортиков пока пуст.\n");
                            break;
                        }

                        displayListOfPizzaSides();
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

    public void displayListOfPizzaSides() {
        System.out.println("\n=== СПИСОК БОРТИКОВ ===");
        if (sides.isEmpty()) {
            System.out.println("Список бортиков пока пуст.");
            return;
        }

        int idx = 1;
        for (PizzaSide s : sides) {
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
    
}
