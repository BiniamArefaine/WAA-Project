package edu.miu.cs.auctionproject.verificationAPI;

import java.util.HashMap;
import java.util.Map;

public class Verification {

    public static boolean verify(String name, String liscenceNumber){
        return isVerified(name, liscenceNumber);
    }



    private static Map<String,String> listOfLiscNumbers(){

        Map<String, String> licenseNumbers = new HashMap<String, String>();
        licenseNumbers.put("bini" ,"B12345");
        licenseNumbers.put("Dawit" , "D12345");
        licenseNumbers.put("Essey" ,"E12345");
        licenseNumbers.put("Eyob" ,"EY12345");
        licenseNumbers.put("Wegahta" ,"W12345");
        return licenseNumbers;

    }

    private static boolean isVerified(String name, String licenceNumber){
        Map<String, String> map = listOfLiscNumbers();
        if(!licenceNumber.equals(map.get(name)) || (!map.containsKey(name))){
            return false;
        }
        return true;
    }
}

