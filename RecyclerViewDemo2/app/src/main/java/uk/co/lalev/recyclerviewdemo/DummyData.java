package uk.co.lalev.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

public class DummyData {
    private String name;
    private String email;
    private String phone;

    public DummyData(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    static List<DummyData> generate() {
        ArrayList<DummyData> result = new ArrayList<DummyData>(12);
        result.add(new DummyData("Ангелин Лалев", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Петър Ганев", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Цанко Спасов", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Юлиан Веселинов", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Катя Пенчева", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Георги Маринов", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Кристиян Николов", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Вилиан Москов", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Кирил Тенчев", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Марин Лилов", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Алина Чочева", "lalev@uni-svishtov.bg", "+359 885 478260"));
        result.add(new DummyData("Петър Цанков", "lalev@uni-svishtov.bg", "+359 885 478260"));
        return result;
    };
}
