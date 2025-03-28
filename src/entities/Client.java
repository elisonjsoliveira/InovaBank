package entities;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Client {

    private static final AtomicLong sequence = new AtomicLong(1);
    private final long id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private LocalDate birthDate;

    public Client(String name, String cpf, String phone, String email, LocalDate birthDate) {
        this.id = sequence.getAndIncrement();
        this.name = name;
        this.cpf = cpf;
        this.phone = phone;
        this.email = email;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
