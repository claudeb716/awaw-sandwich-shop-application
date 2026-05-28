package com.pluralsight.interfaces;

public interface Display {
    //No Fields or Constructor:

    //Methods:
    //Every item MUST be able to PRINT OUT its own FORMATTED description
    default String getDescription(String size){
        return "";
    }

    //Override:
    //Display
}
