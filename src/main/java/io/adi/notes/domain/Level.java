package io.adi.notes.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.adi.notes.exception.EnumInvalidValuesException;

public enum Level {
    LOW,
    MEDIUM,
    HIGH;


    @JsonCreator
    public static Level fromString(String value){
         for(Level level : Level.values()){
             if(level.name().equalsIgnoreCase(value)){
                 return level;
             }
         }

         throw new EnumInvalidValuesException( "Invalid level value: " + value + ". Allowed values: LOW, MEDIUM, HIGH.");
    }
}
