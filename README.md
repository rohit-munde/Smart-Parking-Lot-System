# 🚗 Smart Parking Lot System

A Java-based Low-Level Design (LLD) implementation of a multi-floor parking lot management system with support for different vehicle types, dynamic fare calculation, and automated spot allocation.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Project Structure](#project-structure)
- [Class Descriptions](#class-descriptions)
- [Design Patterns Used](#design-patterns-used)
- [Fare Structure](#fare-structure)
- [How to Run](#how-to-run)
- [Usage Guide](#usage-guide)
- [Sample Output](#sample-output)
- [Future Enhancements](#future-enhancements)

---

## 📖 Overview

The Smart Parking Lot System is a console-based application that simulates a real-world parking lot management system. It handles vehicle entry, automatic spot allocation based on vehicle type, duration-based fare calculation, and generates parking receipts upon checkout.

### Key Highlights
- **Multi-floor parking** with 5 floors and 20 spots per floor (100 total spots)
- **Vehicle type segregation**: Trucks (Floor 1), Motorcycles (Floor 2), Cars (Floors 3-5)
- **Dynamic fare calculation** based on parking duration and vehicle type
- **Unique vehicle validation** to prevent duplicate parking
- **Formatted receipt generation** with ASCII art display

---

## ✨ Features

| Feature | Description |
|---------|-------------|
| 🅿️ **View Parking Spots** | Display all parking spots with their status in a tabular format |
| 🚘 **Book Parking Spot** | Allocate an appropriate spot based on vehicle type |
| 🧾 **Checkout & Receipt** | Generate fare and print a formatted parking receipt |
| 🔒 **Unique Vehicle Check** | Prevents the same vehicle from being parked twice |
| ⚠️ **Error Handling** | Beautifully formatted error messages in box format |
| 🏢 **Multi-Floor Support** | Different floors designated for different vehicle types |

---

## 🏗️ System Architecture

```
┌─────────────────────────────────────────────────────────────────┐
│                          MAIN CLASS                             │
│                    (Application Entry Point)                    │
└─────────────────────────┬───────────────────────────────────────┘
                          │
        ┌─────────────────┼─────────────────┐
        ▼                 ▼                 ▼
┌───────────────┐ ┌───────────────┐ ┌───────────────┐
│   Parking     │ │   Checkout    │ │    Fare       │
│  Allocation   │ │   Service     │ │  Calculation  │
│   Service     │ │               │ │   Service     │
└───────┬───────┘ └───────┬───────┘ └───────────────┘
        │                 │
        └────────┬────────┘
                 ▼
        ┌───────────────┐
        │ ParkingSpot   │
        │   Service     │
        │  (Singleton)  │
        └───────┬───────┘
                │
                ▼
        ┌───────────────┐
        │   DataStore   │
        │   <Generic>   │
        └───────────────┘
```

---

## 📁 Project Structure

```
Smart Parking Lot System/
├── src/
│   ├── Main.java                          # Application entry point
│   ├── enums/
│   │   └── VehicleType.java               # Vehicle types with fare rates
│   ├── models/
│   │   ├── ParkingSpot.java               # Parking spot entity
│   │   ├── Ticket.java                    # Parking ticket/receipt
│   │   └── Vehicle.java                   # Vehicle entity
│   ├── services/
│   │   ├── CheckoutService.java           # Handles checkout operations
│   │   ├── FareCalculationService.java    # Calculates parking fare
│   │   ├── ParkingAllocationService.java  # Allocates parking spots
│   │   └── ParkingSpotService.java        # Manages parking spots (Singleton)
│   └── utils/
│       ├── DataStore.java                 # Generic in-memory data storage
│       ├── ErrorHandler.java              # Formatted error display
│       └── IdGenerator.java               # Auto-increment ID generation
├── target/                                # Compiled classes
└── README.md                              # This documentation
```

---

## 📚 Class Descriptions

### Enums

#### `VehicleType`
Defines the types of vehicles supported with their per-minute parking rates.

| Type | Rate (Rs/min) |
|------|---------------|
| TRUCK | 20 |
| CAR | 10 |
| MOTORCYCLE | 5 |

**Key Methods:**
- `getRatePerMinute()` - Returns the fare rate for the vehicle type
- `getVehicleTypeFromChoice(int)` - Maps user input (1,2,3) to vehicle type

---

### Models

#### `Vehicle`
Represents a vehicle entering the parking lot.

| Field | Type | Description |
|-------|------|-------------|
| `id` | int | Auto-generated unique identifier |
| `vehicleNo` | String | Registration number (unique) |
| `vehicleType` | VehicleType | Type of vehicle |

#### `ParkingSpot`
Represents a single parking spot in the lot.

| Field | Type | Description |
|-------|------|-------------|
| `id` | int | Auto-generated spot identifier |
| `vehicleId` | int | ID of parked vehicle (0 if empty) |
| `vehicleNo` | String | Registration number of parked vehicle |
| `floor` | int | Floor number (1-5) |
| `spotType` | VehicleType | Type of vehicles allowed |
| `isOccupied` | boolean | Availability status |
| `checkInTime` | long | Entry timestamp (milliseconds) |

#### `Ticket`
Represents the parking receipt generated at checkout.

| Field | Type | Description |
|-------|------|-------------|
| `id` | int | Auto-generated ticket ID |
| `vehicleId` | int | Associated vehicle ID |
| `entryTime` | long | Check-in timestamp |
| `exitTime` | long | Check-out timestamp |
| `totalFare` | double | Calculated parking fare |

---

### Services

#### `ParkingSpotService` (Singleton)
Central service for managing all parking spot operations.

| Method | Description |
|--------|-------------|
| `getInstance()` | Returns the singleton instance |
| `initStore(DataStore)` | Initializes the data store |
| `ensureSeeded(floors, spotsPerFloor)` | Creates initial parking spots |
| `getAllSpots()` | Returns all parking spots |
| `printSpotTable()` | Displays spots in tabular format |
| `findAppropriateSpot(VehicleType)` | Finds available spot for vehicle type |
| `isVehicleAlreadyParked(vehicleNo)` | Checks for duplicate vehicle |
| `assignSpot(spotId, vehicleId, vehicleNo)` | Assigns spot to vehicle |
| `GetParkingSpotById(spotId)` | Retrieves spot by ID |

#### `ParkingAllocationService`
Handles the vehicle parking flow.

| Method | Description |
|--------|-------------|
| `allocateParkingSpot()` | Main method to park a vehicle |
| `getVehicleDetailsFromUser()` | Collects vehicle information |

#### `CheckoutService`
Handles the vehicle checkout flow.

| Method | Description |
|--------|-------------|
| `checkoutFromParkingSpot()` | Main checkout method |
| `takeUserInputForCheckout()` | Validates and returns parking spot |

#### `FareCalculationService` (Singleton)
Calculates parking fare based on duration and vehicle type.

| Method | Description |
|--------|-------------|
| `getInstance()` | Returns the singleton instance |
| `calculateFare(checkIn, checkOut, vehicleType)` | Computes total fare |

---

### Utilities

#### `DataStore<T>` (Generic)
In-memory storage with CRUD operations.

| Method | Description |
|--------|-------------|
| `create(id, item)` | Adds new item |
| `read(id)` | Retrieves item by ID |
| `readAll()` | Returns all items |
| `update(id, item)` | Updates existing item |
| `delete(id)` | Removes item |
| `printAll()` | Displays all items |

#### `IdGenerator`
Static utility for generating unique IDs.

| Method | Description |
|--------|-------------|
| `generateVehicleId()` | Returns next vehicle ID |
| `generateParkingSpotId()` | Returns next spot ID |
| `generateTicketId()` | Returns next ticket ID |

#### `ErrorHandler`
Displays errors in formatted box style.

| Method | Description |
|--------|-------------|
| `handle(String, String)` | Display custom error |
| `handle(Exception)` | Display exception details |

---

## 🎨 Design Patterns Used

### 1. Singleton Pattern
Used in services that should have only one instance throughout the application lifecycle.

**Applied in:**
- `ParkingSpotService`
- `FareCalculationService`

```java
public class ParkingSpotService {
    private static final ParkingSpotService INSTANCE = new ParkingSpotService();
    
    private ParkingSpotService() {}  // Private constructor
    
    public static ParkingSpotService getInstance() {
        return INSTANCE;
    }
}
```

### 2. Repository Pattern
The `DataStore<T>` class acts as a generic repository providing CRUD operations for any model type.

### 3. Service Layer Pattern
Business logic is separated into dedicated service classes:
- `ParkingAllocationService` - Parking operations
- `CheckoutService` - Checkout operations
- `FareCalculationService` - Fare calculation

---

## 💰 Fare Structure

| Vehicle Type | Rate | Example (60 min) |
|--------------|------|------------------|
| Truck | Rs 20/min | Rs 1,200 |
| Car | Rs 10/min | Rs 600 |
| Motorcycle | Rs 5/min | Rs 300 |

**Formula:** `Total Fare = Duration (minutes) × Rate per minute`

---

## 🚀 How to Run

### Prerequisites
- Java JDK 17 or higher
- Any Java IDE (IntelliJ IDEA recommended) or terminal

### Steps

1. **Clone/Download the project**
   ```bash
   git clone https://github.com/rohit-munde/Smart-Parking-Lot-System.git
   cd Smart-Parking-Lot-System
   ```

2. **Compile the project**
   ```bash
   find "./src" -name "*.java" -print0 | xargs -0 javac -d target/classes
   ```

3. **Run the application**
   ```bash
   java -cp target/classes Main
   ```

### Using IntelliJ IDEA
1. Open the project in IntelliJ IDEA
2. Navigate to `src/Main.java`
3. Click the green "Run" button or press `Shift + F10`

---

## 📖 Usage Guide

### Main Menu
```
--- Parking Lot Management ---
1. View Parking Spots
2. Book a Parking Spot
3. Checkout from Parking Spot
0. Exit
Enter your choice:
```

### Option 1: View Parking Spots
Displays all 100 parking spots in a table:
```
ID     FLOOR  TYPE         OCCUPIED  
----------------------------------
1      1      TRUCK        false     
2      1      TRUCK        false     
...
```

### Option 2: Book a Parking Spot
1. Enter vehicle registration number
2. Select vehicle type (1-Truck, 2-Car, 3-Bike)
3. System automatically assigns an appropriate spot

```
--- Parking Lot Management ---
Vehicle Registration Number: 
MH12AB1234
Vehicle Type (1. Truck, 2. Car, 3. Bike): 
2

✅ Parking Spot Allocated Successfully!
SpotId   FLOOR  TYPE   OCCUPIED     VehicleId    CheckInTime
-------------------------------------------------------------
41       3      CAR    true         1            1709161234567
```

### Option 3: Checkout
1. Enter the spot ID
2. System calculates fare and prints receipt

---

## 📄 Sample Output

### Parking Receipt
```
+------------------------------------+
|        PARKING RECEIPT             |
+------------------------------------+
|  Ticket ID    :  1                 |
|  Vehicle ID   :  1                 |
+------------------------------------+
|  Entry Time   :                    |
|    2026-02-28 14:30:25             |
|  Exit Time    :                    |
|    2026-02-28 15:45:30             |
|  Duration     :  75            min |
+------------------------------------+
|  TOTAL FARE   :  Rs 750.00         |
+------------------------------------+
```

### Error Display
```
+--------------------------------------------------+
|                                                  |
| ERROR: RuntimeException                          |
| Vehicle with registration MH12AB1234 is already  |
| parked!                                          |
|                                                  |
+--------------------------------------------------+
```

---

## 🔮 Future Enhancements

| Feature | Priority | Description |
|---------|----------|-------------|
| Persistence | Medium | Save/load data to file or database |
| Vehicle Search | Low | Find parked vehicle by registration number |
| Spot Reservation | Low | Reserve spots in advance |
| Admin Panel | Low | Add/remove parking spots dynamically |
| Unit Tests | Medium | JUnit test coverage for all services |
| Concurrency | High | Thread-safe operations for multi-user access |
| Payment Gateway | Low | Integration with payment systems |

---

## 📝 Notes

- `ParkingSpotService` seeds 100 spots on startup when the in-memory store is empty.
- Seeding is per process run because `DataStore` is in-memory.

---

## 👨‍💻 Author

**Rohit Munde**

---

## 📝 License

This project is created for educational purposes as part of Java LLD Practice.


