package ru.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private JewelryRepository jewelryRepository;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) {
        while (true) {
            System.out.println("\n1.Добавить украшение\n2. Показать все\n3. Редактировать по ID\n4. Удалить по ID\n5. Искать по материалу\n6. Выход");
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1 -> addJewelry();
                case 2 -> showAllJewelry();
                case 3 -> editJewelry();
                case 4 -> deleteJewelry();
                case 5 -> searchJewelryByMaterial();
                case 6 -> System.exit(0);
                default -> System.out.println("Неправильный выбор!");
            }
        }
    }

    private void addJewelry() {
        System.out.print("Введите название: ");
        String name = scanner.nextLine();
        System.out.print("Введите материал: ");
        String material = scanner.nextLine();
        System.out.print("Введите описание: ");
        String description = scanner.nextLine();
        System.out.print("Введите вес: ");
        double weight = Double.parseDouble(scanner.nextLine());
        System.out.print("Введите цену: ");
        double price = Double.parseDouble(scanner.nextLine());

        Jewelry jewelry = new Jewelry(name, material, description, weight, price);
        jewelryRepository.save(jewelry);
        System.out.println("Украшение добавлено: " + jewelry);
    }

    private void showAllJewelry() {
        List<Jewelry> jewelries = jewelryRepository.findAll();
        jewelries.forEach(System.out::println);
    }

    private void editJewelry() {
        System.out.print("Введите ID для редактирования: ");
        Long id = Long.parseLong(scanner.nextLine());

        Jewelry jewelry = jewelryRepository.findById(id).orElse(null);
        if (jewelry == null) {
            System.out.println("Украшение не найдено!");
            return;
        }

        System.out.print("Введите новое название: ");
        jewelry.setName(scanner.nextLine());
        System.out.print("Введите новый материал: ");
        jewelry.setMaterial(scanner.nextLine());
        System.out.print("Введите новое описание: ");
        jewelry.setDescription(scanner.nextLine());
        System.out.print("Введите новый вес: ");
        jewelry.setWeight(Double.parseDouble(scanner.nextLine()));
        System.out.print("Введите новую цену: ");
        jewelry.setPrice(Double.parseDouble(scanner.nextLine()));

        jewelryRepository.save(jewelry);
        System.out.println("Украшение обновлено: " + jewelry);
    }

    private void deleteJewelry() {
        System.out.print("Введите ID для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());
        jewelryRepository.deleteById(id);
        System.out.println("Украшение удалено: " + id);
    }

    private void searchJewelryByMaterial() {
        System.out.print("Введите материал для поиска: ");
        String material = scanner.nextLine();
        List<Jewelry> results = jewelryRepository.findByMaterial(material);
        results.forEach(System.out::println);
    }
}
