/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.dto;

import lombok.Data;

/**
 *
 * @author austine.okoroafor
 */
@Data
public class Error {
    
     private String description;

    public Error(String description) {
        this.description = description;

    }
    
}
