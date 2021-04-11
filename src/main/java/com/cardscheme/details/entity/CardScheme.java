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
import org.hibernate.annotations.DynamicUpdate;

/**
 *
 * @author austine.okoroafor
 */
@Entity
@Data
@Table(name="CardScheme")
@DynamicUpdate
public class CardScheme  {
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull(message = "card number field is required")
    private String cardnum;
   @NotNull(message = "scheme field is required")
    private String scheme;
   
    private String type;
     @NotNull(message = "bank field is required")
    private String bank;
    private long card_numberof_calls;
    
}
