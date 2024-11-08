/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.proyecto.Persistencia;

import com.example.proyecto.LogicaDeNegocio.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Yeferson
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    
    User findUserByUsername(String username);
    
    
    
}
