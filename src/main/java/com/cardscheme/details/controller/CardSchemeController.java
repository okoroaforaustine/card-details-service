/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.controller;

import com.cardscheme.details.Utils.AppUtils;
import com.cardscheme.details.dto.Response;
import com.cardscheme.details.entity.CardScheme;
import com.cardscheme.details.repository.CardSchemeRepository;
import com.cardscheme.details.service.CardService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author austine.okoroafor
 */
@RestController
@RequestMapping("/CardScheme")
@Slf4j
@CrossOrigin
public class CardSchemeController {

    @Autowired
    CardService cardService;
    @Autowired
    AppUtils util;
    @Autowired
    CardSchemeRepository cardRepo;

    @PostMapping(value = "/AddCard", produces = "Application/json", consumes = "Application/json")
@ApiOperation(value = "Add new Card",
            notes = "Create a new card details to populate the table..",
            response = Response.class)
    public ResponseEntity<?> createCard(@Valid @RequestBody CardScheme request, @ApiIgnore Errors errors) {

        if (errors.hasErrors()) {
            log.debug("Error:::: ");
            return util.returnPostValidationErrors(errors);
        }

        cardRepo.save(request);

        return util.returnSuccessResponse(request, "Card Creatded succesfully");
    }

    @GetMapping(value = "/verify/{card_number}", produces = "Application/json", consumes = "Application/json")
    @ApiOperation(value = "Verifies card number",
            notes = "verifies card number",
            response = Response.class)
    public ResponseEntity<?> cardScheme(@PathVariable("card_number") String card_number) throws IOException {
        int count=0;
        CardScheme card = cardRepo.findByCardnum(card_number);
        if (card == null) {
           return util.returnErrorResponse("card number not found", HttpStatus.NOT_FOUND);
        }
        count++;
         card.setCard_numberof_calls(count);
         cardRepo.save(card);
        Map data = new HashMap();
        data.put("scheme", card.getScheme());
        data.put("type", card.getType());
        data.put("bank", card.getBank());

        return util.returnSuccessResponse(data, "");
    }

    @GetMapping(value = "/stats", produces = "Application/json", consumes = "Application/json")
    @ApiOperation(value = "paginate card details",
            notes = "Paginates card details..",
            response = Response.class)
    public ResponseEntity<?> findAll(@RequestParam("start") int start, @RequestParam("limit") int limit) throws JsonProcessingException {

        List<CardScheme> list = cardService.getAllCards(start, limit, "cardnum");
        int size = list.size();
        Map data = new HashMap();
        for (int i = 0; i < list.size(); i++) {

            String cardnumber = list.get(i).getCardnum();
            long count = list.get(i).getCard_numberof_calls();

            data.put(cardnumber, count);
        }

        return util.returnSuccessResponse(data, start, limit, size);

    }

}
