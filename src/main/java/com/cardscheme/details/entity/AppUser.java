/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author austine.okoroafor
 */

@Entity
@Data
@Table(name = "AppUser")
public class AppUser  {
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
     
    private String appKey;
      @NotNull(message = "APPID field is required")
    private String appID;
    
    
}
