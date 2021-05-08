/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.repository;

import com.cardscheme.details.entity.CardScheme;
import java.util.List;
import javax.persistence.Cacheable;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author austine.okoroafor
 */
@Repository


public interface CardSchemeRepository extends JpaRepository<CardScheme, Long>{
    
  
    CardScheme findByCardnum(String cardnum);
    
    public List<CardScheme> findByCardnum(String cardnum, Pageable pageable);
    
}
