public class car{
    
    private String VIN;
    private String make;
    private String model;
    private double price;
    private double mileage;
    private String color;
    private int indexP;//index for price PQ
    private int indexM;//index for mileage PQ
    private int indexPMap;//index for price makemodel PQ
    private int indexMMap;//index for mileage makemodel PQ

    public car(String VIN, String make, String model, double price, double mileage, String color){
        this.VIN = VIN;
        this.make = make;
        this.model = model;
        this.price = price;
        this.mileage = mileage;
        this.color = color;
    }

    public String getVIN(){
        return VIN;
    }

    public String getMake(){
        return make;
    }

    public String getModel(){
        return model;
    }

    public double getPrice(){
        return price;
    }

    public double getMileage(){
        return mileage;
    }

    public String getColor(){
        return color;
    }

    public void setVIN(String VIN){
        this.VIN = VIN;
    }

    public void setMake(String make){
        this.make = make;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setMileage(double mileage){
        this.mileage = mileage;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setIndexP(int indexP){
        this.indexP = indexP;
    }

    public int getIndexP(){
        return indexP;
    }

    public void setIndexM(int indexM){
        this.indexM = indexM;
    }

    public int getIndexM(){
        return indexM;
    }

    public void setIndexPMap(int indexPMap){
        this.indexPMap = indexPMap;
    }

    public int getIndexPMap(){
        return indexPMap;
    }

    public void setIndexMMap(int indexMMap){
        this.indexMMap = indexMMap;
    }

    public int getIndexMMap(){
        return indexMMap;
    }

    public String toString(){
        return "VIN: " + VIN + "\nMake: " + make.substring(0,1).toUpperCase()+make.substring(1) + "\nModel: " + 
        model.substring(0,1).toUpperCase()+model.substring(1) + "\nPrice: $" + price + "\nMileage: " + mileage + " miles\nColor: " + 
        color.substring(0,1).toUpperCase()+color.substring(1);
    }



}