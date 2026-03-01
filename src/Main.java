import java.util.*;

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
        Pizza standartP = new Pizza("Стандарт", base.getFirst(), "Средний");
        standartP.addIngredient(ingredients.get(0));
        standartP.addIngredient(ingredients.get(2));
        standartP.addIngredient(ingredients.get(3));
        pizzas.add(standartP);

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

                        PizzaSide selectedSide = null;
                        while (selectedSide == null) {
                            displayListOfPizzaSides();
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
                            displayListOfIngredients();
                            System.out.println("Текущая сборка: " + newPizza.getName()
                                    + " | Основа: " + selectedBase.getName()
                                    + " Размер: " + newPizza.getSize()
                                    + " Бортик: " + selectedSide.getName());
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
                                break;
                            }

                            case 3: {
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
                                break;
                            }
                            case 4: {
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
                                displayListOfPizzaSides();
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
                            if (target.side != null) target.side.removePizza(target);
                            System.out.print("Пицца '" + target.getName() + "' удалена из меню. Нажмите Enter...");
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

        int idx = 1;
        for (Pizza p : pizzas) {
            System.out.printf("%d. Пицца \"%s\" (Основа: %s) (Размер: %s) (Бортик: %s) - %.2f руб.\n",
                    idx, p.getName(), p.getBase().getName(),p.getSize(), p.check(), p.calculatePrice());

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

                        System.out.println("\n--- Сборка состава бортика ---");
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
                    displayListOfPizzaSides();
                    System.out.print("Нажмите Enter, чтобы продолжить...");
                    scanner.nextLine();
                    break;

                case 5:
                    while (true) {
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

    public void manageMenuOrder() {
        ArrayList<Priceable> currentOrderItems = new ArrayList<>();

        while (true) {
            System.out.println("\n=== МЕНЮ ЗАКАЗОВ ===");
            System.out.println("1. Создать заказ");
            System.out.println("2. Редактировать заказ");
            System.out.println("3. Удалить заказ");
            System.out.println("4. Вывести список всех заказов");
            System.out.println("0. Выйти");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    boolean isBuildingOrder = true;
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
                            case 1:
                                displayListOfPizzas();
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
                                else orderedPizza = new Pizza(template.getName(), template.getBase(), template.getSize());
                                for (Ingredient ing : template.getIngredients()) {
                                    orderedPizza.addIngredient(ing);
                                }

                                while (true) {
                                    System.out.println("\nТекущий состав пиццы '" + orderedPizza.getName() + "':");
                                    for (Ingredient ing : orderedPizza.getIngredients()) {
                                        System.out.println("- " + ing.getName() + " (" + ing.getPrice() + " руб.)");
                                    }

                                    System.out.print("Введите название ингредиента для удвоения (или 0 чтобы добавить пиццу в корзину): ");
                                    String ingName = scanner.nextLine();
                                    if (ingName.equals("0")) break;

                                    Ingredient foundToDouble = null;
                                    for (Ingredient ing : orderedPizza.getIngredients()) {
                                        if (ing.getName().equalsIgnoreCase(ingName)) {
                                            foundToDouble = ing;
                                            break;
                                        }
                                    }

                                    if (foundToDouble != null) {
                                        orderedPizza.addIngredient(foundToDouble);
                                        System.out.println("Порция '" + foundToDouble.getName() + "' успешно удвоена!\n");
                                    } else {
                                        System.out.println("Такого ингредиента нет в базовом составе этой пиццы...\n");
                                    }
                                }

                                currentOrderItems.add(orderedPizza);
                                System.out.println("Пицца добавлена в корзину!\n");
                                continue;

                            case 2:
                                System.out.println("В разработке...");
                                break;

                            case 3:
                                System.out.println("В разработке...");
                                break;

                            case 4:
                                if (currentOrderItems.isEmpty()) {
                                    System.out.println("Ваша корзина пуста! Добавьте хотя бы одну позицию.\n");
                                    continue;
                                }

                                System.out.print("Введите комментарий к заказу (или оставьте пустым): ");
                                String comment = scanner.nextLine();

                                Order newOrder = new Order(comment);
                                for (Priceable item : currentOrderItems) {
                                    newOrder.addItem(item);
                                }

                                newOrder.printReceipt();

                                currentOrderItems.clear();
                                isBuildingOrder = false;
                                break;

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

                case 2:
                case 3:
                case 4:
                case 0:
                    return;
                default:
                    System.out.println("Введен некорректный символ, попробуйте еще раз...\n");
                    break;
            }
        }
    }

}
