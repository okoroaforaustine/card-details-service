/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.service;

import com.cardscheme.details.entity.CardScheme;
import com.cardscheme.details.repository.CardSchemeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author austine.okoroafor
 */
@Service
@CacheConfig(cacheNames = {"CardScheme"})
public class CardService {
    
    @Autowired
    CardSchemeRepository cardRepo;
    
     public List<CardScheme> getAllCards(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<CardScheme> pagedResult = cardRepo.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<CardScheme>();
        }
    }
     @Cacheable
     public CardScheme findBycardNumber(String cardNum){
       CardScheme card=cardRepo.findByCardnum(cardNum);
       
       return card;
     
     }
    
}
