package com.example.annotations;

import validation.Length;
import validation.NotNull;
import validation.Range;

public class User {
    @NotNull(message = "Le nom est obligatoire")
    @Length(min = 2, max = 50, message = "Le nom doit faire 2-50 caractères")
    private String name;
    
    @NotNull
    @Length(min = 3, max = 20)
    private String login;
    
    @Range(min = 18, max = 100, message = "L'âge doit être entre 18 et 100")
    private int age;
    
    // Constructeurs
    public User(String name, String login, int age) {
        this.name = name;
        this.login = login;
        this.age = age;
    }
    
    // Getters/Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
