package com.on.dao;

import java.util.UUID;

public class Uuiddao {

	public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString(); 
        String uuidStr=str.replace("-", "");
        return uuidStr;
      }

}
