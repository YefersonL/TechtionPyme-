/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.proyecto.LogicaDeNegocio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "username")
@NoArgsConstructor          // Constructor sin argumentos
@AllArgsConstructor         // Constructor con todos los argumentos
@Data
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String role;

  
    
    
    
    
    
}
