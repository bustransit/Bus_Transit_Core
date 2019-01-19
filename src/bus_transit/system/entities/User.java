/*
 * BESTLINK COLLEGE OF THE PHILIPPINES
 * This is under License of BSIT 4201
 * Provincial Bus Transportation System
 */

package bus_transit.system.entities;

/**
 *
 * @author NelsonDelaTorre
 */

public class User{
    public static String firstname = "";
    public static String lastname = "";
    public static String middlename = "";
    public static String fullname = (lastname + ", "+ firstname +" "+ middlename).toString();
    public String username = "";
    public String password = "";
    public String position = "";
    public String department = "";
    public String positionId = "";
    public String uli = "";  
    public int userLevel = 0;
}   
