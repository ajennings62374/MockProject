public class AddressBook    //public keyword means class is available to other classes in this program
{
    // These are the attributes (variables that each instance is given a set of), private meaning accesible only within the declared class itself
    private String name;
    private String housenum;
    private String houseroad;
    private String housetown;
    private String housecounty;
    private String housepostcode;

    //This section is the constructor
    public AddressBook(String name, String housenum, String houseroad, String housetown, String housecounty, String housepostcode)
    {        
        this.name = name;
        this.housenum = housenum;
        this.houseroad = houseroad;
        this.housetown = housetown;
        this.housecounty = housecounty;
        this.housepostcode = housepostcode;
    }

    //override the toString() method, if you don't have this when outputting you get a reference to the object
    // not the values of the object
    @Override   
    public String toString() 
    {
        return this.name + ", " + this.housenum + ", " +   this.houseroad + ", " + this.housetown + ", " +   this.housecounty + ", " +   this.housepostcode;
    }

    //methods - getters and setters, also known as accessor methods
    public String getname() { return name;}

    public String gethousenum() {return housenum;}

    public String gethouseroad() {return houseroad;}
    
    public String gethousetown() {return housetown;}

    public String gethousecounty() {return housecounty;}
    
    public String gethousepostcode() {return housepostcode;}

    //this keyword highlights that the attribute is being assigned to rather than the parameter being assigned to itself
    public void setname(String name){this.name = name;}

    public void sethousenum(String housenum){this.housenum = housenum;}
    
    public void sethouseroad(String houseroad){this.houseroad = houseroad;}
    
    public void sethousetown(String housetown){this.housetown = housetown;}
        
    public void sethousecounty(String housecounty){this.housecounty = housecounty;}
            
    public void sethousepostcode(String housepostcode){this.housepostcode = housepostcode;}
}
