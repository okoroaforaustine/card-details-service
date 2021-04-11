/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.repository;

import com.cardscheme.details.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author austine.okoroafor
 */
@Repository
public interface UserRepository extends CrudRepository<AppUser, Long>{
      AppUser  findByAppKey(String  appKey);
       
      
    
}
