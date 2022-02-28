package uk.co.lalev.hello4;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String name;
    private String email;
    private String phone;
    private boolean vip;

    public static List<Client> clients;

    static {
        clients = generate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Client(String name, String email, String phone, boolean vip) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.vip = vip;
    }

    static List<Client> generate() {
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Ангелин Лалев", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Иван Лалев", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Петко Лалев", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Драган Лалев", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Стоян Лалев", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Ангелин Петров", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Ангел Кузманов", "lalev@uni-svishtov.bg", "+359885478222", true));
        clients.add(new Client("Ангел Иванов", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Ангел Стоянов", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Петко Петков", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Драган Цанов", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Емил Георгиев", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Венелин Грозев", "lalev@uni-svishtov.bg", "+359885478222", true));
        clients.add(new Client("Станислав Цанов", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Иванка Ангелова", "lalev@uni-svishtov.bg", "+359885478222", false));
        clients.add(new Client("Елена Петрова", "lalev@uni-svishtov.bg", "+359885478222", true));
        return clients;
    }
}
