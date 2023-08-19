import java.util.*;

class Notebook {
    private String brand;
    private int ram;
    private int hddSize;
    private String os;
    private String color;

    public Notebook(String brand, int ram, int hddSize, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hddSize = hddSize;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHddSize() {
        return hddSize;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hddSize=" + hddSize +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Notebook> notebooks = new HashSet<>();

        // Добавляем ноутбуки
        notebooks.add(new Notebook("HP", 8, 500, "Windows", "Silver"));
        notebooks.add(new Notebook("Lenovo", 16, 1000, "Windows", "Black"));
        notebooks.add(new Notebook("Dell", 12, 500, "Linux", "Silver"));
        notebooks.add(new Notebook("Asus", 16, 500, "Linux", "Gold"));
        notebooks.add(new Notebook("Apple", 8, 1000, "macOS", "Gray"));

        // Запрашиваем у пользователя критерии фильтрации
        Map<Integer, Object> criteria = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        

        System.out.println("Введите цифру соответствующую критерию фильтрации:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int choice = scanner.nextInt();
        

        if (choice >= 1 && choice <= 4) {
            System.out.println("Введите значение для выбранного критерия:");

            switch (choice) {
                case 1:
                    int minRam = scanner.nextInt();
                    criteria.put(choice, minRam);
                    break;
                case 2:
                    int minHddSize = scanner.nextInt();
                    criteria.put(choice, minHddSize);
                    break;
                case 3:
                    scanner.nextLine(); 
                    String os = scanner.nextLine();
                    criteria.put(choice, os);
                    break;
                case 4:
                    scanner.nextLine(); 
                    String color = scanner.nextLine();
                    criteria.put(choice, color);
                    break;
            }
        }
        scanner.close();
        // Фильтруем ноутбуки
        Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, criteria);

        // Выводим результат
        System.out.println("Отфильтрованные ноутбуки:");
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }

    public static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<Integer, Object> criteria) {
        Set<Notebook> filteredSet = new HashSet<>();
        for (Notebook notebook : notebooks) {
            boolean match = true;

            for (Map.Entry<Integer, Object> entry : criteria.entrySet()) {
                int criteriaId = entry.getKey();
                Object criteriaValue = entry.getValue();

                switch (criteriaId) {
                    case 1:
                        int minRam = (int) criteriaValue;
                        if (notebook.getRam() < minRam) {
                            match = false;
                        }
                        break;
                    case 2:
                        int minHddSize = (int) criteriaValue;
                        if (notebook.getHddSize() < minHddSize) {
                            match = false;
                        }
                        break;
                    case 3:
                        String os = (String) criteriaValue;
                        if (!notebook.getOs().equals(os)) {
                            match = false;
                        }
                        break;
                    case 4:
                        String color = (String) criteriaValue;
                        if (!notebook.getColor().equals(color)) {
                            match = false;
                        }
                        break;
                }

                if (!match) {
                    break;
                }
            }

            if (match) {
                filteredSet.add(notebook);
            }
        }
        return filteredSet;
    }
}    