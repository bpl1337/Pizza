import java.util.*;
import java.time.LocalDate;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Ingredient> ingredients = new ArrayList<>();
    private static ArrayList<PizzaBase> base = new ArrayList<>();
    private static ArrayList<Pizza> pizzas = new ArrayList<>();


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

        Pizza standart = new Pizza("Стандарт", base.getFirst());
        standart.addIngredient(ingredients.get(0));
        standart.addIngredient(ingredients.get(2));
        standart.addIngredient(ingredients.get(3));
        pizzas.add(standart);
    }

    public void run() {
        while (true) {
            System.out.println("\n=== ПИЦЦЕРИЯ ===");
            System.out.println("1. Управление ингредиентами");
            System.out.println("2. Управление основами");
            System.out.println("3. Меню пицц");
            System.out.println("0. Выход");
            System.out.print("Выбор: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: manageIngredients(); break;
                case 2: manageBases(); break;
                case 3: manageMenuPizzas(); break;
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
}
