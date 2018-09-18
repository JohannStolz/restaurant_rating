package com.graduate.restaurant_rating.domain;


import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Johann Stolz 14.08.2018
 */
@Entity
@Table(name = "users")
public class User extends AbstractBaseEntity {
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_sex", joinColumns = @JoinColumn(name = "user_id"))
    private Sex sex;
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private boolean enabled = true;
    private LocalDate registeredDate = LocalDate.now();

    public User() {
    }

    public User(Integer id, String name, int age, Sex sex, String email, boolean enabled, String password, Collection<Role> roles) {
        super(id);
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.registeredDate = LocalDate.now();
        setRoles(roles);
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getAge(), user.getSex(), user.getEmail(), user.isEnabled(), user.password, user.getRoles());
    }

    public User(Integer id, String name, int age, Sex sex, String email, boolean enabled, String password, Role role, Role... roles) {
        this(id, name, age, sex, email, enabled, password, EnumSet.of(role, roles));
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

    public LocalDate getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(LocalDate registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? Collections.emptySet() : EnumSet.copyOf(roles);
    }
    public void setId(int id){
      super.setId(id);
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
