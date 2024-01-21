import java.util.ArrayList;
import java.util.List;
import java.util.*;

//We are Create a One Car Rental System Project Fully based on Concept Of oop's
class Car
{
    // declare All Variables Are private To Achieve Encapsulation or Security of Data no Anyone can
    //So that no person can change
    private  String carId;
    private  String  model;
    private  String brand;

    private   double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId,String model,String brand,double basePricePerDay)
    {
        this.carId=carId;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvailable=isAvailable=true;
    }
    public String getCarId()
    {
        return carId;
    }

    public  String getModel()
    {
        return model;
    }
    public String getBrand()
    {
        return brand;
    }

    //Suppose our car price is 1500 .customerDemand is 3 days car onrent
    //This method is  calculate  the no of days's rent
    public double calculatePrice(int rentalDays)
    {
        return basePricePerDay*rentalDays;
    }

    //car is Available for rent
    public boolean isAvailable()
    {
        return isAvailable;
    }

    //carr is not  Available for rent
    public void rent()
    {
        isAvailable=false;
    }
    //Customer return the car and now car is Available for rent
    public  void returnCar()
    {
        isAvailable=true;
    }
}
class Customer
{
    //Name and id of Customer
    private String name;
    private  String customerId;

    public  Customer(String name,String customerId)
    {
        this.name=name;
        this.customerId=customerId;
    }
    public String getName()
    {
        return name;
    }
    public String getCustomerId()
    {
        return customerId;
    }
}

//rental class
class Rental
{
    private Car car;
    private  Customer customer;
    private  int days;
    public  Rental(Car car,Customer customer,int days)
    {
        this.car=car;
        this.customer=customer;
        this.days=days;
    }

    public Car getCar()
    {
        return  car;
    }

    public Customer getCustomer()
    {
        return  customer;
    }
    public  int getDays()
    {
        return days;
    }
}
class CarRentalSystem
{
    //create a list to store car customers detail ,rental
    private  List<Car>  cars;
    private  List<Customer>  customers;
    private  List<Rental>  rentals;

    public  CarRentalSystem()
    {
        cars=new ArrayList<>();
        customers=new ArrayList<>();
        rentals=new ArrayList<>();
    }

    //Adding new Cars or Customer for  rent
    public void addCar( Car car)
    {
        cars.add(car);
    }
    public void addCustomer( Customer customer)
    {
        customers.add(customer);
    }

    // Customer will come to take the car on rent, his details and for how many days will he take the car

    public void rentCar(Car car,Customer customer,int days)
    {
        if(car.isAvailable())
        {
            //car rent ke liye de di gyi hai
            car.rent();
            //to hum add kr denge use arraylist me
            rentals.add(new Rental(car,customer,days));
        }
        else
        {
            System.out.println("Car is not Available for rent");
        }
    }

    //Customer return the car
    public void returnCar(Car car)
    {
        car.returnCar();
        Rental rentalToRemove=null;
        for (Rental rental:rentals)
        {
            if(rental.getCar()==car)
            {
                rentalToRemove=rental;
                break;
            }
        }

        //Agr hme car mil gyi to hum rental ko remove kr denge
        if(rentalToRemove!=null)
        {
            rentals.remove(rentalToRemove);
            System.out.println("Car Returned SuccessFully");
        }
        else
        {
            System.out.println("Car Was not returned");
        }
    }
    //Choice menu And select the car,return car,rent a car,Exit

    public  void menu()
    {
        Scanner sc=new Scanner(System.in);

        while (true)//iske andr ki statement  tab tk chlega jab tk hum exit nhi krte
        {
            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter Your Choice:");

            int choice=sc.nextInt();
            sc.nextLine();//Consume new Line

            //Customer ko kon si car chye wo Choose krega

            if(choice==1)
            {
                System.out.println("\n== Rent a car ==");
                System.out.println("Enter your name:");
                String customerName=sc.nextLine();

                System.out.println("\n Available Cars:");
                for(Car car:cars)
                {
                    if (car.isAvailable())
                    {
                        System.out.println(car.getCarId()+"-"+car.getBrand()+"-"+car.getModel());
                    }
                }
                System.out.println("\nEnter the Car ID You Want:");
                String carId=sc.nextLine();

                System.out.println("Enter The NUmber Of Days for Rental:");
                int rentalDays=sc.nextInt();
                sc.nextLine();//Consume space line

                //Create a object of customer and +1 the size of customers size arraylist
                //new Customer is Basically create a constructor
                Customer newCustomer=new Customer("CUS"+(customers.size()+1),customerName);
                addCustomer(newCustomer);

                Car selectedCar=null;
                for(Car car:cars)
                {
                    if(car.getCarId().equals(carId) && car.isAvailable())
                    {
                        selectedCar=car;
                        break;
                    }
                }
                //Agr car null nahi hai uski koi value aa gyi hai to hum uska price calculte krenge no of days ke acc
                if(selectedCar!=null)
                {
                    double totalPrice=selectedCar.calculatePrice(rentalDays);
                    //Rental information of Customer
                    System.out.println("\n==== Rental Information ====\n");
                    System.out.println("Customer ID:"+newCustomer.getName());
                    System.out.println("Car:"+selectedCar.getBrand()+" "+selectedCar.getModel());
                    System.out.println("Rental days:"+rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);
                    System.out.println("\nConfirm Rental(Y/N):");
                    String confirm=sc.nextLine();

                    if(confirm.equalsIgnoreCase("Y"))
                    {
                        rentCar(selectedCar,newCustomer,rentalDays);
                        System.out.println("\nCar Rented Successfully");
                    }
                    else
                    {
                        System.out.println("\nRental Canceled");
                    }
                }
                else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }
            }
            else if(choice==2)
            {
                System.out.println("\n== Return a car==\n");
                System.out.println("Enter the car ID you Want to Return:");
                String carID=sc.nextLine();

                Car carToReturn=null;
                for(Car car:cars)
                {
                    if(car.getCarId().equals(carID) && !car.isAvailable())
                    {
                        carToReturn=car;
                        break;
                    }
                }
                if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rentals) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                }
                else
                {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            }
            // Select This Choice  Exit to Car rental System
            else if (choice==3)
            {
                break;
            }
            else
            {
                System.out.println("invalid choice Please Enter valid Option");
            }
        }
        System.out.println("\nThanks for using Rental system");
    }
}
//Main Class
public class Main {
    public static void main(String[] args)
    {
        //Create a object of a CarRentalSystem Class
        CarRentalSystem obj=new CarRentalSystem();
        //Create a object of Cars
        Car car1=new Car("C001","Toyota","cannery",60.0);
        Car car2=new Car("C002","Honda","Accord",80.0);
        Car car3=new Car("C003","mahindra","Thar",200.0);
        //call All Cars By Object
        obj.addCar(car1);
        obj.addCar(car2);
        obj.addCar(car3);

//Calling menu Method
        obj.menu();

    }
}