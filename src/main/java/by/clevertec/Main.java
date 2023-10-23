package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
        task5();
        task6();
        task7();
        task8();
        task9();
        task10();
        task11();
        task12();
        task13();
        task14();
        task15();
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();
    }

    public static void task1() {
        List<Animal> animals = Util.getAnimals();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        animals.stream()
                .filter(animal -> animal.getAge() >= 10 && animal.getAge() <= 20)
                .sorted(Comparator.comparing(Animal::getAge))
                .collect(Collectors.groupingBy(it -> (atomicInteger.getAndIncrement() / 7) + 1))
                .forEach((key, value) -> {
                    if (key.equals(3)) {
                        System.out.println(key + " - " + value);
                    }
                });
    }

    public static void task2() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Japanese"))
                .map(animal -> {
                    if (animal.getGender().equals("Female")) {
                        return animal.getBread().toUpperCase();
                    }
                    else {
                        return animal.getBread();
                    }
                })
                .forEach(System.out::println);
    }

    public static void task3() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getAge() > 30)
                .map(Animal::getOrigin)
                .filter(origin -> origin.startsWith("A"))
                .collect(Collectors.toSet())
                .forEach(System.out::println);
    }

    public static void task4() {
        List<Animal> animals = Util.getAnimals();
        Stream.of(animals.stream()
                        .filter(animal -> animal.getGender().equals("Female"))
                        .count())
                .forEach(System.out::println);
    }

    public static void task5() {
        List<Animal> animals = Util.getAnimals();
        Stream.of(animals.stream()
                        .filter(animal -> animal.getAge() >= 20 && animal.getAge() <= 30)
                        .anyMatch(animal -> animal.getOrigin().equals("Hungarian")))
                .forEach(System.out::println);
    }

    public static void task6() {
        List<Animal> animals = Util.getAnimals();
        Stream.of(animals.stream()
                        .allMatch(animal -> animal.getGender().equals("Male") || animal.getGender().equals("Female")))
                .forEach(System.out::println);
    }

    public static void task7() {
        List<Animal> animals = Util.getAnimals();
        Stream.of(animals.stream()
                        .noneMatch(animal -> animal.getOrigin().equals("Oceania")))
                .forEach(System.out::println);
    }

    public static void task8() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getAge)
                .ifPresent(System.out::println);
    }

    public static void task9() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .map(Animal::getBread)
                .map(String::toCharArray)
                .mapToInt(chars -> chars.length)
                .min()
                .ifPresent(System.out::println);
    }

    public static void task10() {
        List<Animal> animals = Util.getAnimals();
        Stream.of(animals.stream()
                        .mapToInt(Animal::getAge)
                        .sum())
                .forEach(System.out::println);
    }

    public static void task11() {
        List<Animal> animals = Util.getAnimals();
        animals.stream()
                .filter(animal -> animal.getOrigin().equals("Indonesian"))
                .mapToInt(Animal::getAge)
                .average()
                .ifPresent(System.out::println);
    }

    public static void task12() {
        List<Person> persons = Util.getPersons();
        persons.stream()
                .filter(person -> {
                    int age = Period.between(person.getDateOfBirth(), LocalDate.now()).getYears();
                    return age >= 18 && age < 27 && person.getGender().equals("Male");
                })
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .forEach(System.out::println);
    }

    public static void task13() {
        List<House> houses = Util.getHouses();
        houses.stream()
                .flatMap(house -> {
                    if (house.getBuildingType().equals("Hospital")) {
                        return house.getPersonList()
                                .stream();
                    }
                    else {
                        return house.getPersonList()
                                .stream()
                                .sorted((person1, person2) -> {
                                    int personAge1 = Period.between(person1.getDateOfBirth(), LocalDate.now())
                                            .getYears();
                                    int personAge2 = Period.between(person2.getDateOfBirth(), LocalDate.now())
                                            .getYears();
                                    if (personAge1 < 18 || personAge1 >= 65) {
                                        if (personAge2 < 18 || personAge2 >= 65) {
                                            return 0;
                                        }
                                        else {
                                            return -1;
                                        }
                                    }
                                    else if (personAge2 < 18 || personAge2 >= 65) {
                                        return 1;
                                    }
                                    else {
                                        return 0;
                                    }
                                });
                    }
                })
                .limit(500)
                .forEach(System.out::println);
    }

    public static void task14() {
        List<Car> cars = Util.getCars();
        Stream.of(cars.stream()
                        .collect(Collectors.groupingBy(car -> {
                            if (car.getCarMake().equals("Jaguar") || car.getColor().equalsIgnoreCase("White")) {
                                return "Туркменистан";
                            }
                            else if (car.getMass() < 1500 || Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota")
                                    .contains(car.getCarMake())) {
                                return "Узбекистан";
                            }
                            else if (car.getColor()
                                    .equals("Black") && car.getMass() > 4000 || Arrays.asList("GMC", "Dodge")
                                    .contains(car.getCarMake())) {
                                return "Казахстан";
                            }
                            else if (car.getReleaseYear() <= 1982 || Arrays.asList("Civic", "Cherokee")
                                    .contains(car.getCarModel())) {
                                return "Кыргызстан";
                            }
                            else if (!Arrays.asList("Yellow", "Red", "Green", "Blue")
                                    .contains(car.getColor()) || car.getPrice() > 40000) {
                                return "Россия";
                            }
                            else if (car.getVin().contains("59")) {
                                return "Монголия";
                            }
                            return "Остальные";
                        }, Collectors.summingDouble(car -> car.getMass() / 1000.0 * 7.14)))
                        .entrySet()
                        .stream()
                        .mapToDouble(entry -> {
                            if (!entry.getKey().equals("Остальные")) {
                                System.out.println("shipping cost for " + entry.getKey() + ": $" + entry.getValue());
                            }
                            return entry.getValue();
                        })
                        .sum())
                .forEach(aDouble -> System.out.println("total revenues: " + aDouble));
    }

    public static void task15() {
        List<Flower> flowers = Util.getFlowers();
        Stream.of(flowers.stream()
                        .sorted(Comparator.comparing(Flower::getOrigin)
                                .reversed()
                                .thenComparing(Comparator.comparing(Flower::getPrice)
                                        .reversed())
                                .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay)
                                        .reversed()))
                        .filter(flower -> flower.getCommonName().charAt(0) >= 'C' &&
                                flower.getCommonName().charAt(0) <= 'S' &&
                                flower.isShadePreferred() &&
                                flower.getFlowerVaseMaterial().stream()
                                        .anyMatch(material -> material.equals("Glass") ||
                                                material.equals("Aluminum") ||
                                                material.equals("Steel")))
                        .mapToDouble(flower -> flower.getPrice() + flower.getWaterConsumptionPerDay() * 1.39 * 5 * 366)
                        .sum())
                .forEach(System.out::println);
    }

    public static void task16() {
        List<Student> students = Util.getStudents();
        students.stream()
                .filter(student -> student.getAge() <= 18)
                .sorted(Comparator.comparing(Student::getSurname))
                .forEach(System.out::println);
    }

    public static void task17() {
        List<Student> students = Util.getStudents();
        students.stream()
                .map(Student::getGroup)
                .distinct()
                .forEach(System.out::println);
    }

    public static void task18() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy((Student::getFaculty),
                        Collectors.averagingDouble(Student::getAge)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    public static void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        students.stream()
                .filter(student -> student.getGroup().equals("P-1") && examinations.stream()
                        .anyMatch(examination -> examination.getStudentId() == student.getId() && examination.getExam3() > 4))
                .forEach(System.out::println);
    }

    public static void task20() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        Stream.of(examinations.stream()
                        .collect(Collectors.groupingBy(examination -> students.stream()
                                        .filter(student -> student.getId() == examination.getStudentId())
                                        .findFirst()
                                        .map(Student::getFaculty)
                                        .orElseThrow(),
                                Collectors.averagingDouble(Examination::getExam1)))
                        .entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue()))
                .forEach(value -> value.ifPresent(System.out::println));
    }

    public static void task21() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getGroup, Collectors.counting()))
                .forEach((group, count) -> System.out.println("group: " + group + " number of students: " + count));
    }

    public static void task22() {
        List<Student> students = Util.getStudents();
        students.stream()
                .collect(Collectors.groupingBy(Student::getFaculty, Collectors.minBy(Comparator.comparing(Student::getAge))))
                .forEach((faculty, student) -> student.ifPresent(value -> System.out.println("faculty: " + faculty + " min age: " + value.getAge())));
    }
}