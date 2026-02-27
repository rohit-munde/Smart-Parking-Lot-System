# Smart Parking Lot System

## Run

Compile and run from the project root:

```zsh
find "./src" -name "*.java" -print0 | xargs -0 javac -d /tmp/parkinglot_out
java -cp /tmp/parkinglot_out Main
```

## Notes

- `ParkingSpotService` seeds 100 spots on startup when the in-memory store is empty.
- Seeding is per process run because `DataStore` is in-memory.

