/**
 * Абстрактный базовый класс для всех транспортных средств
 */
public abstract class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    protected String purpose;
    
    public Vehicle(String brand, String model, int year, String purpose) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.purpose = purpose;
    }
    
    // Абстрактные методы
    public abstract String getEnvironment();
    public abstract String getDriveType();
    
    @Override
    public String toString() {
        return String.format("%s %s (%d) - %s, Среда: %s, Привод: %s", 
            brand, model, year, purpose, getEnvironment(), getDriveType());
    }
}

/**
 * Абстрактный класс для наземного транспорта
 */
public abstract class GroundVehicle extends Vehicle {
    protected int wheelCount;
    
    public GroundVehicle(String brand, String model, int year, String purpose, int wheelCount) {
        super(brand, model, year, purpose);
        this.wheelCount = wheelCount;
    }
    
    @Override
    public String getEnvironment() {
        return "Наземная";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Колес: " + wheelCount;
    }
}

/**
 * Абстрактный класс для водного транспорта
 */
public abstract class WaterVehicle extends Vehicle {
    protected double displacement; // водоизмещение
    
    public WaterVehicle(String brand, String model, int year, String purpose, double displacement) {
        super(brand, model, year, purpose);
        this.displacement = displacement;
    }
    
    @Override
    public String getEnvironment() {
        return "Водная";
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Водоизмещение: " + displacement + "т";
    }
}

/**
 * Интерфейс для транспортных средств с двигателем внутреннего сгорания
 */
public interface CombustionEngine {
    void refuel(String fuelType);
    String getFuelType();
}

/**
 * Интерфейс для транспортных средств с электродвигателем
 */
public interface ElectricMotor {
    void recharge(int kWh);
    int getBatteryCapacity();
}

/**
 * Интерфейс для военных транспортных средств
 */
public interface WeaponSystem {
    void activateWeaponSystem();
    void loadAmmunition(String ammoType);
}

/**
 * Гражданский автомобиль с ДВС
 */
public class CivilCar extends GroundVehicle implements CombustionEngine {
    private String fuelType;
    private int passengerCapacity;
    
    public CivilCar(String brand, String model, int year, int wheelCount, int passengerCapacity, String fuelType) {
        super(brand, model, year, "Гражданский", wheelCount);
        this.passengerCapacity = passengerCapacity;
        this.fuelType = fuelType;
    }
    
    @Override
    public String getDriveType() {
        return "ДВС";
    }
    
    @Override
    public void refuel(String fuelType) {
        this.fuelType = fuelType;
        System.out.println("Заправка " + brand + " " + model + " топливом: " + fuelType);
    }
    
    @Override
    public String getFuelType() {
        return fuelType;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Пассажиров: " + passengerCapacity + ", Топливо: " + fuelType;
    }
}

/**
 * Электрический автобус
 */
public class ElectricBus extends GroundVehicle implements ElectricMotor {
    private int batteryCapacity;
    private int passengerCapacity;
    
    public ElectricBus(String brand, String model, int year, int wheelCount, int passengerCapacity, int batteryCapacity) {
        super(brand, model, year, "Гражданский", wheelCount);
        this.passengerCapacity = passengerCapacity;
        this.batteryCapacity = batteryCapacity;
    }
    
    @Override
    public String getDriveType() {
        return "Электрический";
    }
    
    @Override
    public void recharge(int kWh) {
        System.out.println("Зарядка " + brand + " " + model + " на " + kWh + " кВтч");
    }
    
    @Override
    public int getBatteryCapacity() {
        return batteryCapacity;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Пассажиров: " + passengerCapacity + ", Батарея: " + batteryCapacity + " кВтч";
    }
}

/**
 * Военный танк с ДВС и системой вооружения
 */
public class MilitaryTank extends GroundVehicle implements CombustionEngine, WeaponSystem {
    private String fuelType;
    private String mainGun;
    
    public MilitaryTank(String brand, String model, int year, String fuelType, String mainGun) {
        super(brand, model, year, "Военный", 12);
        this.fuelType = fuelType;
        this.mainGun = mainGun;
    }
    
    @Override
    public String getDriveType() {
        return "ДВС";
    }
    
    @Override
    public void refuel(String fuelType) {
        this.fuelType = fuelType;
        System.out.println("Заправка танка " + brand + " " + model + " топливом: " + fuelType);
    }
    
    @Override
    public String getFuelType() {
        return fuelType;
    }
    
    @Override
    public void activateWeaponSystem() {
        System.out.println("Система вооружения танка " + brand + " " + model + " активирована");
    }
    
    @Override
    public void loadAmmunition(String ammoType) {
        System.out.println("Загрузка боеприпасов в танк: " + ammoType);
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Основное орудие: " + mainGun + ", Топливо: " + fuelType;
    }
}

/**
 * Грузовое судно с ДВС
 */
public class CargoShip extends WaterVehicle implements CombustionEngine {
    private String fuelType;
    private double cargoCapacity;
    
    public CargoShip(String brand, String model, int year, double displacement, double cargoCapacity, String fuelType) {
        super(brand, model, year, "Коммерческий", displacement);
        this.cargoCapacity = cargoCapacity;
        this.fuelType = fuelType;
    }
    
    @Override
    public String getDriveType() {
        return "ДВС";
    }
    
    @Override
    public void refuel(String fuelType) {
        this.fuelType = fuelType;
        System.out.println("Заправка судна " + brand + " " + model + " топливом: " + fuelType);
    }
    
    @Override
    public String getFuelType() {
        return fuelType;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Грузоподъемность: " + cargoCapacity + "т, Топливо: " + fuelType;
    }
}

/**
 * Гибридный автомобиль
 */
public class HybridCar extends GroundVehicle implements CombustionEngine, ElectricMotor {
    private String fuelType;
    private int batteryCapacity;
    private int passengerCapacity;
    
    public HybridCar(String brand, String model, int year, int wheelCount, int passengerCapacity, String fuelType, int batteryCapacity) {
        super(brand, model, year, "Гражданский", wheelCount);
        this.passengerCapacity = passengerCapacity;
        this.fuelType = fuelType;
        this.batteryCapacity = batteryCapacity;
    }
    
    @Override
    public String getDriveType() {
        return "Гибридный";
    }
    
    @Override
    public void refuel(String fuelType) {
        this.fuelType = fuelType;
        System.out.println("Заправка " + brand + " " + model + " топливом: " + fuelType);
    }
    
    @Override
    public String getFuelType() {
        return fuelType;
    }
    
    @Override
    public void recharge(int kWh) {
        System.out.println("Зарядка " + brand + " " + model + " на " + kWh + " кВтч");
    }
    
    @Override
    public int getBatteryCapacity() {
        return batteryCapacity;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Пассажиров: " + passengerCapacity + ", Топливо: " + fuelType + ", Батарея: " + batteryCapacity + " кВтч";
    }
}

/**
 * Фабрика для создания транспортных средств
 */
public class VehicleFactory {
    
    public static Vehicle createVehicle(String type) {
        switch (type.toLowerCase()) {
            case "car":
                return new CivilCar("Toyota", "Camry", 2023, 4, 5, "Бензин");
            case "bus":
                return new ElectricBus("Volvo", "7900 Electric", 2024, 6, 80, 300);
            case "tank":
                return new MilitaryTank("Уралвагонзавод", "Т-90", 2022, "Дизель", "125-мм пушка");
            case "ship":
                return new CargoShip("Hyundai", "Heavy210", 2021, 50000, 40000, "Мазут");
            case "hybrid":
                return new HybridCar("Toyota", "Prius", 2023, 4, 5, "Бензин", 8);
            default:
                throw new IllegalArgumentException("Неизвестный тип транспортного средства: " + type);
        }
    }
}

/**
 * Демонстрационный класс для тестирования иерархии транспортных средств
 */
/**
 * Демонстрационный класс для тестирования иерархии транспортных средств
 */
public class Main {
    public static void main(String[] args) {
        // Создание транспортных средств через фабрику
        Vehicle[] vehicles = {
            VehicleFactory.createVehicle("car"),
            VehicleFactory.createVehicle("bus"),
            VehicleFactory.createVehicle("tank"),
            VehicleFactory.createVehicle("submarine"),
            VehicleFactory.createVehicle("drone"),
            VehicleFactory.createVehicle("hybrid")
        };
        
        System.out.println("=== Демонстрация иерархии транспортных средств ===\n");
        
        // Демонстрация полиморфного поведения
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.toString());
            
            // Демонстрация интерфейсов
            if (vehicle instanceof CombustionEngine) {
                ((CombustionEngine) vehicle).refuel("Дизель");
            }
            
            if (vehicle instanceof ElectricMotor) {
                ((ElectricMotor) vehicle).recharge(50);
            }
            
            if (vehicle instanceof WeaponSystem) {
                ((WeaponSystem) vehicle).activateWeaponSystem();
                ((WeaponSystem) vehicle).loadAmmunition("Стандартные боеприпасы");
            }
            
            System.out.println();
        }
        
        // Попытка создать неизвестный тип
        try {
            VehicleFactory.createVehicle("spaceship");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}