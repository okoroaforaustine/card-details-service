/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.Utils;

import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author austine.okoroafor
 */
@Slf4j
public class SecurityUtils {
    
       private static final String ALGO = "HmacSHA256";
    private static final String CHARSET = "UTF-8";

 
    public static String encode(String key, String data) throws Exception {
        String hashed = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

             hashed = new String(org.apache.commons.codec.binary.Base64.encodeBase64(sha256_HMAC.doFinal(data.getBytes())));
         
            log.info("hashed generated value" + hashed);
        } catch (Exception e) {
            //    log.error(e.getMessage(),e);
            e.printStackTrace();

        }

        return hashed;
    }
    
     public static String generateUniquePaymentCode(String mobile) {

        int sequence = 0;

        String uniqueId = new Random(System.currentTimeMillis()).nextInt(26) + new Random(System.nanoTime()).nextInt(26) + mobile.substring(5, 6);
        int pos = new Random(System.currentTimeMillis()).nextInt(mobile.length());
        uniqueId += mobile.substring(pos, pos + 1);
        java.text.DecimalFormat sequ = new java.text.DecimalFormat("000");
        long nxtSeq = sequence++;
        if (nxtSeq > 998) {
            sequence = 0;
        }
        
        String inCnt = sequ.format(nxtSeq);
        pos = new Random(System.nanoTime()).nextInt(32);
        pos = new Random(System.currentTimeMillis()).nextInt(32);
        String randomNo = new Random(System.nanoTime()).nextInt(10000) + "";
        while (randomNo.length() < 4) {
            randomNo = "0" + randomNo;
        }
        uniqueId += randomNo;
        return "APIKEY_"+uniqueId;
    }
    
}
