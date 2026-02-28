package services;

import enums.VehicleType;

public class FareCalculationService {
        private static final FareCalculationService INSTANCE = new FareCalculationService();

        private FareCalculationService() {}

        public static FareCalculationService getInstance() {
            return INSTANCE;
        }

        public double calculateFare(long checkInTimeMillis, long checkOutTimeMillis, VehicleType vehicleType) {
            long durationInMinutes = (checkOutTimeMillis - checkInTimeMillis) / (1000 * 60);
            return durationInMinutes * vehicleType.getRatePerMinute();
        }
}
