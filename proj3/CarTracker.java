import java.io.*;
import java.util.*;

public class CarTracker{
    private static Scanner reader = new Scanner(System.in);
    private static PQ PQp = new PQ(100);
    private static PQ PQm = new PQ(100);
    private static int counterP = 1;
    private static int counterM = 1;
    private static int counterPMap = 1;
    private static int counterMMap = 1;
    private static TreeMap<String, car> VINmap = new TreeMap<>();//switch hashmaps to DLBs or TSTs
    private static TreeMap<String, PQ> priceMap = new TreeMap<>();
    private static TreeMap<String, PQ> mileageMap = new TreeMap<>();
    public static void main(String[] args) {
        int option = -1;
        
        load(); //loads the cars.txt file

        while(option != 0){
            do{//input validation for option choice
                System.out.print("\n1. Add a car \n2. Update a car \n3. Remove a car \n4. Retrieve a car \n0. Exit \nChoose one of the above:");
                if(reader.hasNextInt()){
                    option = reader.nextInt();
                    break;
                }else{
                    reader.nextLine();
                    System.out.println("\nNot an integer!\n");
                }
            }while(true);
            

            switch(option){
                case 0: 
                    break;
                case 1:
                    System.out.println();
                    add();
                    break;
                case 2: 
                    System.out.println();
                    update();
                    break;
                case 3:
                    System.out.println();
                    remove();
                    break;
                case 4:
                    System.out.println();
                    retrieve();
                    break;
                default:
                    System.out.println("Invalid number. Try again.");
                    System.out.println();
                    break;
            }
        }
    }

    public static void add(){
        double price, mileage; 
        String VIN, make, model, color;
        reader.nextLine();

        do{//input validation for VIN
            System.out.print("Enter the VIN(Exclude I, i, O, o, Q, q): ");//make method to verify
            VIN = reader.nextLine();
        }while(!validateVIN(VIN));
        
        System.out.print("Enter the make: ");
        make = reader.nextLine().toLowerCase().trim();
        System.out.print("Enter the model: ");
        model = reader.nextLine().toLowerCase().trim();

        do{//validation for price
            System.out.print("Enter the price: ");
            if(reader.hasNextDouble()){
                 price = reader.nextDouble();
                 break;
            }else{
                reader.nextLine();
                System.out.println("\nNot a number!\n");
            }
           
        }while(true);
        reader.nextLine();
        
        do{//validation for mileage
            System.out.print("Enter the mileage: ");
            if(reader.hasNextDouble()){
                mileage = reader.nextDouble();
                break;
            }else{
                reader.nextLine();

                System.out.println("\nNot a number!\n");
            }
           
        }while(true);
        
        reader.nextLine();
        System.out.print("Enter the color: ");
        color = reader.nextLine().trim();

        car newCar = new car(VIN, make, model, price, mileage, color);

        System.out.println("\nAdding: " + newCar.toString() + "\n");

        VINmap.put(VIN, newCar);//add VIN to treemap with the car as the value
        newCar.setIndexP(counterP++);//set the index for the price PQ
        newCar.setIndexM(counterM++);//set the index for the mileage PQ
        PQp.insert(newCar, true, false);//insert the car into the price PQ
        PQm.insert(newCar, false, false);//insert the car into the mileage PQ

        addTree(newCar, make, model);//adds the car to its makemodel PQ in its tree

    }

    public static void update(){
        int option = -1;
        String VIN;
        reader.nextLine();
        do{//input validation for VIN
            System.out.print("Enter the VIN(Exclude I, i, O, o, Q, q): ");//make method to verify
            VIN = reader.nextLine();
        }while(!validateVIN(VIN));
        car updateCar = VINmap.get(VIN);

        while(option == -1){
            do{
                System.out.print("1. Price \n2. Mileage \n3. Color \n0. Exit \nWhich would you like to update: ");
                if(reader.hasNextInt()){
                    option = reader.nextInt();
                    break;
                }else{
                    reader.nextLine();
                    System.out.println("\nNot an integer!\n");
                }
            }while(true);
            
            
            switch(option){
                case 0:
                    break;
                case 1:
                    reader.nextLine();
                    do{//sets the price
                        System.out.print("Enter the new price: ");
                        if(reader.hasNextDouble()){
                            updateCar.setPrice(reader.nextDouble());
                            System.out.println("\nPrice changed to " + updateCar.getPrice() + "\n");
                            break;
                        }else{
                            reader.nextLine();
                            System.out.println("\nNot a number!\n");
                        }
                    }while(true);
                    break;
                case 2: 
                    reader.nextLine();
                    do{//sets the mileage
                        System.out.print("Enter the new mileage: ");
                        if(reader.hasNextDouble()){
                            updateCar.setMileage(reader.nextDouble());
                            System.out.println("\nMileage changed to " + updateCar.getMileage() + "\n");
                            break;
                        }else{
                            reader.nextLine();
                            System.out.println("\nNot a number!\n");
                        }
                    }while(true);
                   
                    break;
                case 3:
                    reader.nextLine(); 
                    do{//sets the color
                        System.out.print("Enter the new color: ");
                        if(reader.hasNextDouble()){
                            updateCar.setColor(reader.nextLine().trim());  
                            System.out.println("\nColor changed to " + updateCar.getColor() + "\n"); 
                            break;
                        }else{
                            reader.nextLine();
                            System.out.println("\nNot a number!\n");
                        }
                    }while(true);   
                    break;
                default:
                    System.out.println("\nInvalid number. Try again.\n");
                    break;
            }
        }
        PQp.update(updateCar.getIndexP(), true, false);//update the price PQ for the updated car
        PQm.update(updateCar.getIndexM(), false, false);//update the mileage PQ for the updated car
        priceMap.get(updateCar.getMake()+updateCar.getModel()).update(updateCar.getIndexPMap(), true, true);//update the price PQ for the specific makemodel
        mileageMap.get(updateCar.getMake()+updateCar.getModel()).update(updateCar.getIndexMMap(), false, true);//update the mileage PQ for the specific makemodel
    }

    public static void remove(){
        String VIN;
        reader.nextLine();
        
        do{
            do{//input validation for VIN
                System.out.print("Enter the VIN(Exclude I, i, O, o, Q, q): ");//make method to verify
                VIN = reader.nextLine();
            }while(!validateVIN(VIN));

            if(VINmap.containsKey(VIN)){
                car removeCar = VINmap.get(VIN); 
                System.out.println("\nRemoving: \n" + removeCar.toString() + "\n");

                PQp.delete(removeCar.getIndexP(), true, false);//removes the car from the price PQ
                PQm.delete(removeCar.getIndexM(), false, false);//removes the car from the mileage PQ
                priceMap.get(removeCar.getMake()+removeCar.getModel()).delete(removeCar.getIndexPMap(), true, true);//gets the price PQ according to the makemodel and removes the specific car
                mileageMap.get(removeCar.getMake()+removeCar.getModel()).delete(removeCar.getIndexMMap(), false, true);//gets the mileage PQ according to the makemodel and removes the specific car

                VINmap.remove(VIN);//removes the VIN from the library
                if(priceMap.get(removeCar.getMake()+removeCar.getModel()).minKey() == null){//if the makemodels PQs for price/mileage is empty then remove the makemodel
                    priceMap.remove(removeCar.getMake()+removeCar.getModel());
                    mileageMap.remove(removeCar.getMake()+removeCar.getModel());
                }
                break;
            }else{
                System.out.println("\nThe VIN does not exist!\n");
            }
        }while(true);
    }

    public static void retrieve(){
        int option = -1;
        String make,model;

        while(option == -1){
            System.out.print("1. Lowest price\n2. Lowest mileage\n3. Lowest price by make and model\n"+
            "4. Lowest mileage by make and model\n0. Exit\nWhich would one would you like to retrieve: ");

            do{
                if(reader.hasNextInt()){
                    option = reader.nextInt();
                    break;
                }else{
                    reader.nextLine();
                    System.out.println("\nNot an integer!\n");
                }
            }while(true);
        
            switch(option){
                case 0:
                    break;
                case 1:
                    System.out.println("\nThe car with the lowest price is: \n" + PQp.minKey().toString() + "\n");//removes the lowest price from price PQ
                    break;
                case 2: 
                    System.out.println("\nThe car with the lowest mileage is: \n" + PQm.minKey().toString() + "\n");//removes the lowest mileage from the mileage PQ
                    break;
                case 3:
                    reader.nextLine();
                    do{
                        System.out.print("Enter the make: ");
                        make = reader.nextLine().toLowerCase().trim();
                        System.out.print("Enter the model: ");
                        model = reader.nextLine().toLowerCase().trim();

                        if(!priceMap.containsKey(make+model)){
                            System.out.println("\nMake or model doesn't exist yet OR check your spelling!\n");
                        }else{
                            System.out.println("\nThe " + make.substring(0,1).toUpperCase()+make.substring(1) + " " + 
                            model.substring(0,1).toUpperCase()+model.substring(1) + " with the lowest price is: \n"+priceMap.get(make+model).minKey().toString()+"\n");//gets the makemodel value from pricetree and gets the lowest price
                            break;
                        }      
                    }while(true);
                    
                    break;
                case 4:
                    reader.nextLine();
                    do{
                        System.out.print("Enter the make: ");
                        make = reader.nextLine().toLowerCase().trim();
                        System.out.print("Enter the model: ");
                        model = reader.nextLine().toLowerCase().trim();

                        if(!mileageMap.containsKey(make+model)){
                            System.out.println("\nMake or model doesn't exist yet OR check your spelling!\n");
                        }else{
                            System.out.println("\nThe " + make.substring(0,1).toUpperCase()+make.substring(1) + " " + 
                            model.substring(0,1).toUpperCase()+model.substring(1) + " with the lowest mileage is: \n"+mileageMap.get(make+model).minKey().toString()+"\n");//gets the makemodel value from mileagetree and gets the lowest mileage
                            break;
                        }
                    }while(true);
                    
                    break;
                default:
                    System.out.println("Invalid number. Try again.");
                    break;
            }
        }
    }

    public static boolean validateVIN(String VIN){//validates the VIN
        boolean valid = true;
        if(VIN.length() != 17){
            System.out.println("\nVIN is not 17 characters!\n");
            valid = false;
        }else if(VIN.contains("I") || VIN.contains("i") || VIN.contains("O") || VIN.contains("o") || VIN.contains("Q") || VIN.contains("q")){
            System.out.println("\nVIN contains the characters I, i, O, o, Q, or q!\n");
            valid = false;
        }
        return valid;
    }

    public static void load(){//loads the cars.txt file
        try {
            BufferedReader input = new BufferedReader(new FileReader("cars.txt"));
            String line;
            input.readLine();
            while((line = input.readLine()) != null) {
                String[] info = line.split(":");
                String make = info[1].toLowerCase();
                String model = info[2].toLowerCase();
                car c = new car(info[0], make, model, Integer.parseInt(info[3]), Integer.parseInt(info[4]), info[5]);
                c.setIndexP(counterP++);
                c.setIndexM(counterM++);
                PQp.insert(c, true, false);
                PQm.insert(c, false, false);

                addTree(c, make, model);

                VINmap.put(info[0], c);
            }
        } catch(FileNotFoundException e) {
            System.out.println("Could not load [cars.txt] file.");
        } catch(IOException e) {
            System.out.println("Error processing [cars.txt] file.");
        }
    }

    private static void addTree(car c, String make, String model){//adds the car into the appropriate PQ according to the makemodel
        
        if(!priceMap.containsKey(make+model)){//insert into price map
            counterPMap = 1;
            c.setIndexPMap(counterPMap++);
            priceMap.put(make+model, new PQ(100));
            priceMap.get(make+model).insert(c, true, true);
        }else{
            c.setIndexPMap(counterPMap++);
            priceMap.get(make+model).insert(c, true, true);
        }
        
        if(!mileageMap.containsKey(make+model)){//insert into mileage map
            counterMMap = 1;
            c.setIndexMMap(counterMMap++);
            mileageMap.put(make+model, new PQ(100));
            mileageMap.get(make+model).insert(c, false, true);
        }else{
            c.setIndexMMap(counterMMap++);
            mileageMap.get(make+model).insert(c, false, true);
        }
    }
}