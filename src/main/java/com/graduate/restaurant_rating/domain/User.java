package com.graduate.restaurant_rating.domain;



import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {
    private String name;
    private int age;
    private Set<Role> roles;
    private Sex sex;
    private String email;
    private String password;
    private LocalDateTime registeredDate = LocalDateTime.now();

    public User() {
    }

    public User(Integer id, String name, int age, Set<Role> roles, Sex sex, String email, String password, LocalDateTime registeredDate) {
        super(id);
        this.name =name;
        this.age = age;
        this.roles = roles;
        this.sex = sex;
        this.email = email;
        this.password = password;
        this.registeredDate = registeredDate;
    }

    public User(User user) {
        this(user.id, user.name, user.age, user.roles, user.sex, user.email, user.password, user.registeredDate);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDateTime registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return getAge() == user.getAge() &&
                Objects.equals(getName(), user.getName()) &&
                Objects.equals(getRoles(), user.getRoles()) &&
                getSex() == user.getSex() &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getRegisteredDate(), user.getRegisteredDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getAge(), getRoles(), getSex(), getEmail(), getRegisteredDate());
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", roles=" + roles +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", registeredDate=" + registeredDate +
                ", id=" + id +
                '}';
    }
}
