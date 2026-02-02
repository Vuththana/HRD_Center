import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

void main() {
        // ANSI Colors
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_PINK = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_PURPLE = "\u001B[0;35m";
        final String ANSI_GREEN = "\u001B[32m";

        // Pattern
        String isNumber = "^\\d+$";
        String isPlateNumber = "^(\\d[a-zA-Z]{2})(-\\d{4})$";
        String isFloor = "^F[0-9]+-[0-9]+$";
        String floorSplitter = "[-F]";

        // Formatter
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Scanner
        Scanner scanner = new Scanner(System.in);

        // Fake Time
        LocalDateTime fakeTime = LocalDateTime.now().plusHours(30);

        // Pagination Setting
        int perPage = 2;

        // Setup Parking Lot
        String floor;
        String room;

        /* Parking Price */
        float perDay = 8;
        float firstHour = 0.5f;
        float additionalHour = 1.5f;
        /* Floors */
        System.out.println("=".repeat(10) + " Setup Parking Lot System " + "=".repeat(10));
        do {
                System.out.print("--> Enter number of floors (max 50): ");
                floor = scanner.nextLine();
                if(!floor.matches(isNumber)) {
                        System.out.println(ANSI_RED + "Invalid Number" + ANSI_RESET);
                } else if(Integer.parseInt(floor) > 50 || Integer.parseInt(floor) < 1) {
                        System.out.println(ANSI_RED + "Invalid Number" + ANSI_RESET);
                } else {
                        break;
                }

        } while(true);

        /* Rooms */
        do {
                System.out.print("--> Enter number of spots (10-100): ");
                room = scanner.nextLine();
                if(!room.matches(isNumber)) {
                        System.out.println(ANSI_RED + "Invalid Number" + ANSI_RESET);
                } else if(Integer.parseInt(room) > 100 || Integer.parseInt(room) < 10) {
                        System.out.println(ANSI_RED + "Invalid Number" + ANSI_RESET);
                } else {
                        break;
                }
        } while(true);

        /* Setup Floors and Rooms */
        String[][] parkingLot = new String[Integer.parseInt(floor)][Integer.parseInt(room)];
        LocalDateTime[][] entryTime = new LocalDateTime[Integer.parseInt(floor)][Integer.parseInt(room)];
        // Parking Operation
        String operation;
        /* Operation */

        mainMenu:
        do {
                System.out.println(ANSI_PINK + "=".repeat(100) + ANSI_RESET);
                System.out.println(" ".repeat(38) + ANSI_CYAN + "PARKING MANAGEMENT SYSTEM" + ANSI_RESET);
                System.out.println(ANSI_PINK + "=".repeat(100) + ANSI_RESET);
                System.out.print("""
                        1. Parking Operation
                        2. View Parking Information
                        3. System Setting
                        4. Exit
                        """);
                System.out.println("-".repeat(100));
                System.out.print("--> Enter your choice (1-4): ");
                operation = scanner.nextLine();
                if (!operation.matches(isNumber)) {
                        System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                } else if (Integer.parseInt(operation) > 4 || Integer.parseInt(operation) < 1) {
                        System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                } else {
                        switch (Integer.parseInt(operation)) {
                                // Check InOut Operation
                                case 1: {
                                        checkInOut:
                                        do {
                                                System.out.println("-".repeat(100));
                                                System.out.println(" ".repeat(40) + "Parking Operation");
                                                System.out.println("-".repeat(100));
                                                System.out.print("""
                                                1. Check In
                                                2. Check Out
                                                3. Back
                                                """);
                                                System.out.println("-".repeat(100));
                                                System.out.print("--> Enter your choice (1-3): ");
                                                String checkInOut = scanner.nextLine();
                                                if(checkInOut.matches(isNumber)) {
                                                        switch(Integer.parseInt(checkInOut)) {
                                                                case 1: {
                                                                        System.out.println("-".repeat(100));
                                                                        System.out.println(" ".repeat(45) + "CHECKIN");
                                                                        System.out.println("-".repeat(100));
                                                                        outerAssign:
                                                                        do {
                                                                                System.out.print("--> Enter vehicle plate number (e.g., 1ad-1902 or 2KL-4839): ");
                                                                                String plateNumber = scanner.nextLine().toUpperCase();
                                                                                if (!plateNumber.matches(isPlateNumber)) {
                                                                                        System.out.println(ANSI_RED + "Invalid plate!" + ANSI_RESET);
                                                                                } else {
                                                                                        while(true) {
                                                                                                innerAssign:
                                                                                                for (int i = 0; i < parkingLot.length; i++) {
                                                                                                        for (int j = 0; j < parkingLot[i].length; j++) {
                                                                                                                if(parkingLot[i][j] != null) {
                                                                                                                        if(parkingLot[i][j].equals(plateNumber)) {
                                                                                                                                System.out.println(ANSI_RED + "Error: Plate number already exists in the parking lot!" + ANSI_RESET);
                                                                                                                                System.out.println();
                                                                                                                                break innerAssign;
                                                                                                                        }
                                                                                                                }
                                                                                                                if(parkingLot[i][j] == null) {
                                                                                                                        System.out.println(ANSI_CYAN + "Checking nearest available parking space..." + ANSI_RESET);
                                                                                                                        parkingLot[i][j] = plateNumber;
                                                                                                                        entryTime[i][j] = LocalDateTime.now();
                                                                                                                        System.out.println(ANSI_GREEN + "Vehicle parked successfully" + ANSI_RESET);
                                                                                                                        System.out.println();
                                                                                                                        System.out.println("Vehicle: " + plateNumber);
                                                                                                                        System.out.println("Floor: " + (i + 1));
                                                                                                                        System.out.println("Spot: " + (j + 1));
                                                                                                                        System.out.println("Entry Time: " + LocalDateTime.now().format(fmt));
                                                                                                                        System.out.println();
                                                                                                                        break innerAssign;
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                                do {
                                                                                                        System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                        String yesOrNo = scanner.nextLine();
                                                                                                        if(yesOrNo.equalsIgnoreCase("y")) continue outerAssign;
                                                                                                        if(yesOrNo.equalsIgnoreCase("n")) continue checkInOut;
                                                                                                        else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                        System.out.println();
                                                                                                } while(true);

                                                                                        }
                                                                                }
                                                                        } while(true);
                                                                }
                                                                case 2: {
                                                                        checkOut:
                                                                        do {
                                                                                System.out.println("-".repeat(100));
                                                                                System.out.println(" ".repeat(45) + "CHECKOUT");
                                                                                System.out.println("-".repeat(100));
                                                                                boolean found = false;
                                                                                System.out.print("--> Enter vehicle plate number (e.g., 1ad-1902 or 2KL-4839): ");
                                                                                String plateNumber = scanner.nextLine().toUpperCase();
                                                                                if (!plateNumber.matches(isPlateNumber)) {
                                                                                        System.out.println(ANSI_RED + "Invalid plate!" + ANSI_RESET);
                                                                                } else {
                                                                                        while(true) {
                                                                                                for (int i = 0; i < parkingLot.length; i++) {
                                                                                                        for (int j = 0; j < parkingLot[i].length; j++) {
                                                                                                                if(parkingLot[i][j] != null) {
                                                                                                                        if(!parkingLot[i][j].equals(plateNumber)) {
                                                                                                                                System.out.println(ANSI_RED + "Failed to checkout vehicle not found!" + ANSI_RESET);
                                                                                                                        } else {
                                                                                                                                Duration remaining = Duration.between(entryTime[i][j], fakeTime);
                                                                                                                                long remainingHours = remaining.toHours();
                                                                                                                                long remainingMinutes = remaining.toMinutes() % 60;
                                                                                                                                float price;
                                                                                                                                if(remainingHours <= 1) {
                                                                                                                                        price = firstHour;
                                                                                                                                } else if (remainingHours < 24) {
                                                                                                                                        price = firstHour + (remainingHours - 1) * additionalHour;
                                                                                                                                } else {
                                                                                                                                        long days = remainingHours / 24;
                                                                                                                                        long remainingHoursDay = remainingHours % 24;

                                                                                                                                        price = days * perDay;

                                                                                                                                        if(remainingHoursDay > 0) {
                                                                                                                                                price += firstHour;
                                                                                                                                                if(remainingHoursDay > 1) {
                                                                                                                                                        price += (remainingHoursDay - 1) * additionalHour;
                                                                                                                                                }
                                                                                                                                        }
                                                                                                                                }
                                                                                                                                System.out.println("Vehicle Found");
                                                                                                                                System.out.println("Floor: " + (i + 1)  + " | " + (j + 1));
                                                                                                                                System.out.println("Entry Time: " + entryTime[i][j].format(fmt));
                                                                                                                                System.out.println("Exit Time: " + fakeTime.format(fmt));
                                                                                                                                System.out.println();
                                                                                                                                System.out.println("Parking duration: " + remainingHours + "hours " + remainingMinutes + "minutes");
                                                                                                                                System.out.println("Parking Fees: $" + price);
                                                                                                                                parkingLot[i][j] = null;
                                                                                                                                entryTime[i][j] = null;
                                                                                                                                do {
                                                                                                                                        System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                                        String yesOrNo = scanner.nextLine();
                                                                                                                                        if(yesOrNo.equalsIgnoreCase("y")) continue checkOut;
                                                                                                                                        if(yesOrNo.equalsIgnoreCase("n")) continue checkInOut;
                                                                                                                                        else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                                } while (true);
                                                                                                                        }
                                                                                                                }
                                                                                                        }
                                                                                                }
                                                                                                if(!found) {
                                                                                                        System.out.println(ANSI_RED + "Failed to checkout vehicle not found!" + ANSI_RESET);
                                                                                                        do {
                                                                                                                System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) continue checkOut;
                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue checkInOut;
                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                        } while (true);
                                                                                                }
                                                                                        }
                                                                                }
                                                                        } while(true);
                                                                }
                                                                case 3:
                                                                        continue mainMenu;
                                                        }
                                                } else {
                                                        System.out.println(ANSI_RED + "Invalid Input!" + ANSI_RESET);
                                                }
                                        } while (true);
                                }
                                case 2: {
                                        outerPagination:
                                        do {
                                                CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);
                                                for (int i = 0; i < parkingLot.length;) {
                                                        Table table = new Table(150, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                                                        table.addCell("Display All Floors Report", center, 150);

                                                        table.addCell("Floor ID", center, 30);
                                                        table.addCell("Total Spots", center, 30);
                                                        table.addCell("Occupied Spots", center, 30);
                                                        table.addCell("Available Spots", center, 30);
                                                        table.addCell("Rate", center, 30);
                                                        for (int j = i * perPage; j < (i + 1) * perPage && j < parkingLot.length; j++) {
                                                                int occupied = 0;
                                                                for (int k = 0; k < parkingLot[j].length; k++) {
                                                                        if(parkingLot[j][k] != null) occupied++;
                                                                }
                                                                int available = parkingLot[j].length - occupied;
                                                                        table.addCell(String.valueOf(j + 1), center, 30);
                                                                        table.addCell(String.valueOf(parkingLot[j].length), center, 30);
                                                                        table.addCell(String.valueOf(occupied), center, 30);
                                                                        table.addCell(String.valueOf(available), center, 30);
                                                                        table.addCell(String.format("%.2f%%", (occupied * 100.0) / parkingLot[j].length), center, 30);
                                                        }
                                                        System.out.println(table.render());
                                                        System.out.println("1. Display spot by Floor        2. Search for Parked Car        3. First        4. Next         5. Previous             6. Last                 7. Back     Page " + (i + 1) + " of " + ((parkingLot.length / perPage) + (parkingLot.length % perPage == 0 ? 0 : 1)));
                                                        System.out.println("-".repeat(100));
                                                        String action;
                                                        do {
                                                                System.out.print("--> Please input your choice (1-7): ");
                                                                action = scanner.nextLine();
                                                                if (!action.matches(isNumber) || Integer.parseInt(action) > 8 || Integer.parseInt(action) < 1)
                                                                        System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                else
                                                                        break;
                                                        } while(true);
                                                        switch(Integer.parseInt(action)) {
                                                                case 1: {
                                                                        String floorId;
                                                                        do {
                                                                                System.out.print("--> Enter the floor ID (" +  1  + (parkingLot.length == 1 ? "" : "-" + parkingLot.length) + "): ");
                                                                                floorId = scanner.nextLine();
                                                                                if (!floorId.matches(isNumber) || Integer.parseInt(floorId) > parkingLot.length || Integer.parseInt(floorId) < 1)
                                                                                        System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                else {
                                                                                        break;
                                                                                }
                                                                        } while(true);
                                                                        showParkingLot:
                                                                        do {
                                                                                for (int j = 0; j < 1; j++) {
                                                                                        Table showParkingLot = new Table( 80, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
                                                                                        showParkingLot.addCell("Floor #" + (Integer.parseInt(floorId)), center,  80);
                                                                                        for (int k = 0; k < parkingLot[Integer.parseInt(floorId) - 1].length; k++) {
                                                                                                if (parkingLot[Integer.parseInt(floorId) - 1][k] != null) {
                                                                                                        showParkingLot.addCell("F" + Integer.parseInt(floorId)  + " - " + (k + 1) + " [" + parkingLot[Integer.parseInt(floorId) - 1][k] + "]", center, 20);
                                                                                                } else {
                                                                                                        showParkingLot.addCell("F" + Integer.parseInt(floorId)  + " - " + (k + 1) + " [EMPTY]" , center, 20);
                                                                                                }
                                                                                        }
                                                                                        showParkingLot.addCell(" ", center, 80);
                                                                                        System.out.println(showParkingLot.render());
                                                                                }
                                                                                System.out.println("1. Move Vehicle       2. Update Parking Spot Status           3. Back");
                                                                                System.out.println("-".repeat(100));
                                                                                String actionShowParkingLot;
                                                                                do {
                                                                                        System.out.print("--> Enter your choice: ");
                                                                                        actionShowParkingLot = scanner.nextLine();
                                                                                        if (!actionShowParkingLot.matches(isNumber) || Integer.parseInt(actionShowParkingLot) > 4 || Integer.parseInt(actionShowParkingLot) < 1)
                                                                                                System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                        else
                                                                                                break;
                                                                                } while(true);
                                                                                switch (Integer.parseInt(actionShowParkingLot))   {
                                                                                        case 1: {
                                                                                                String moveVehicleInput;
                                                                                                System.out.println("-".repeat(100));
                                                                                                System.out.println(" ".repeat(32) + "MOVE VEHICLE");
                                                                                                System.out.println("-".repeat(100));
                                                                                                moveCarInput:
                                                                                                do {
                                                                                                        boolean found = false;
                                                                                                        System.out.print("--> Enter vehicle plate number to move (e.g., 1ad-1902 or 2KL-4839): ");
                                                                                                        moveVehicleInput = scanner.nextLine().toUpperCase();
                                                                                                        if(!moveVehicleInput.matches(isPlateNumber))
                                                                                                                System.out.println(ANSI_RED + "Invalid plate!" + ANSI_RESET);
                                                                                                        else {
                                                                                                                for (int j = 0; j < parkingLot.length; j++) {
                                                                                                                        for (int k = 0; k < parkingLot[j].length; k++) {
                                                                                                                                if (parkingLot[j][k] != null) {
                                                                                                                                        if (parkingLot[j][k].equals(moveVehicleInput)) {
                                                                                                                                                System.out.println("Vehicle Found at Floor " + (j + 1) + ", Spot " + (k + 1));
                                                                                                                                                String moveCarTo;
                                                                                                                                                do
                                                                                                                                                {
                                                                                                                                                        System.out.print("--> Where do you want to move the vehicle (F1-4): ");
                                                                                                                                                        moveCarTo = scanner.nextLine().toUpperCase();
                                                                                                                                                        String[] parts = moveCarTo.split(floorSplitter);
                                                                                                                                                        if (!moveCarTo.matches(isFloor)) {
                                                                                                                                                                System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                                                        } else {
                                                                                                                                                                if(Integer.parseInt(parts[1]) <= parkingLot.length && Integer.parseInt(parts[2]) <= parkingLot[i].length) {
                                                                                                                                                                        if(Integer.parseInt(parts[1]) - 1 != j || Integer.parseInt(parts[2]) - 1 != k) {

                                                                                                                                                                                if(parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] == null) {
                                                                                                                                                                                        parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] = moveVehicleInput;
                                                                                                                                                                                        entryTime[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] = entryTime[j][k];
                                                                                                                                                                                        parkingLot[j][k] = null;
                                                                                                                                                                                        entryTime[j][k] = null;
                                                                                                                                                                                        System.out.println(ANSI_GREEN + "Vehicle moved successful!" + ANSI_RESET);
                                                                                                                                                                                        System.out.println("-".repeat(100));
                                                                                                                                                                                        System.out.println("Vehicle: " + parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1]);
                                                                                                                                                                                        System.out.println("Floor: " + (Integer.parseInt(parts[1])));
                                                                                                                                                                                        System.out.println("Spot: " + (Integer.parseInt(parts[2])));
                                                                                                                                                                                        System.out.println();
                                                                                                                                                                                } else {
                                                                                                                                                                                        System.out.println(ANSI_RED + "Already occupied" + ANSI_RESET);
                                                                                                                                                                                }
                                                                                                                                                                        } else  {
                                                                                                                                                                                System.out.println(ANSI_RED + "You can't enter the same location!" + ANSI_RESET);
                                                                                                                                                                        }
                                                                                                                                                                        do {
                                                                                                                                                                                System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) continue moveCarInput;
                                                                                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue showParkingLot;
                                                                                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                                                                        } while (true);
                                                                                                                                                                } else {
                                                                                                                                                                        System.out.println(ANSI_RED + "Invalid location. Floor or spot number is out of range." + ANSI_RESET);
                                                                                                                                                                        do {
                                                                                                                                                                                System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) continue moveCarInput;
                                                                                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue showParkingLot;
                                                                                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                                                                        } while (true);
                                                                                                                                                                }
                                                                                                                                                        }
                                                                                                                                                } while (true);
                                                                                                                                        }
                                                                                                                                }
                                                                                                                        }
                                                                                                                }
                                                                                                                if(!found) {
                                                                                                                        System.out.println(ANSI_RED + "No Vehicle Found with the plate number: " + moveVehicleInput + ANSI_RESET);
                                                                                                                        do {
                                                                                                                                System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) break;
                                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue showParkingLot;
                                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                        } while (true);
                                                                                                                }
                                                                                                        }
                                                                                                } while(true);
                                                                                        }
                                                                                        case 2 : {
                                                                                                showSpotVehicle:
                                                                                                do {
                                                                                                        System.out.println("-".repeat(100));
                                                                                                        System.out.println(" ".repeat(35) + "UPDATE PARKING STATUS");
                                                                                                        System.out.println("-".repeat(100));
                                                                                                        System.out.println();
                                                                                                        System.out.print("--> Enter spotId (e.g., F1-1): ");
                                                                                                        String spotId = scanner.nextLine().toUpperCase();
                                                                                                        String[] parts = spotId.split(floorSplitter);
                                                                                                        if(!spotId.matches(isFloor)) {
                                                                                                                System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                        } else {
                                                                                                                if(Integer.parseInt(parts[1]) <= parkingLot.length && Integer.parseInt(parts[2]) <= parkingLot[i].length)  {
                                                                                                                        String empty = parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] != null ?  parkingLot[Integer.parseInt(parts[1]) -1][Integer.parseInt(parts[2]) - 1] : "EMPTY";
                                                                                                                        String occupied = parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] != null ?  "Occupied" : "Empty";
                                                                                                                        do {
                                                                                                                                System.out.println("Vehicle: " + empty);
                                                                                                                                System.out.println("Floor: " + Integer.parseInt(parts[1]));
                                                                                                                                System.out.println("Spot: " + Integer.parseInt(parts[2]));
                                                                                                                                System.out.println("Status: " + occupied);
                                                                                                                                if(parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] == null) {
                                                                                                                                        System.out.println(ANSI_RED + "The spot is already empty." + ANSI_RESET);
                                                                                                                                        do {
                                                                                                                                                System.out.print("--> Do you want " + ANSI_PURPLE + "to continue" + ANSI_RESET + "? (Y/N): ");
                                                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) continue showSpotVehicle;
                                                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue showParkingLot;
                                                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                                        } while(true);
                                                                                                                                } else {
                                                                                                                                        System.out.print("--> Do you want " + ANSI_PURPLE + "to update the status to EMPTY" + ANSI_RESET + "? (Y/N): ");
                                                                                                                                        String delete = scanner.nextLine();
                                                                                                                                        if(delete.equalsIgnoreCase("y")) {
                                                                                                                                                parkingLot[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] = null;
                                                                                                                                                entryTime[Integer.parseInt(parts[1]) - 1][Integer.parseInt(parts[2]) - 1] = null;
                                                                                                                                                System.out.println(ANSI_GREEN +  "Update status successfully" + ANSI_RESET);
                                                                                                                                        } if(delete.equalsIgnoreCase("n")) continue showParkingLot;
                                                                                                                                        while(true) {
                                                                                                                                                System.out.print("--> Do you want " + ANSI_PURPLE + "to continue" + ANSI_RESET + "? (Y/N): ");
                                                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) continue showSpotVehicle;
                                                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue outerPagination;
                                                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                                        }
                                                                                                                                }
                                                                                                                        } while(true);
                                                                                                                } else {
                                                                                                                        System.out.println(ANSI_RED + "Invalid location. Floor or spot number is out of range." + ANSI_RESET);
                                                                                                                        do {
                                                                                                                                System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                                String yesOrNo = scanner.nextLine();
                                                                                                                                if(yesOrNo.equalsIgnoreCase("y")) break;
                                                                                                                                if(yesOrNo.equalsIgnoreCase("n")) continue showParkingLot;
                                                                                                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                        } while (true);
                                                                                                                }
                                                                                                        }
                                                                                                }while(true);
                                                                                        }
                                                                                        case 3:
                                                                                                continue outerPagination;
                                                                                }
                                                                        } while (true);

                                                                }
                                                                case 2: {
                                                                        enterPlateNumber:
                                                                        do {
                                                                                System.out.print("--> Enter plate number " + ANSI_PURPLE + "to search" + ANSI_RESET+ "(e.g., 1ad-1902 or 2KL-4839): ");
                                                                                String searchVehicle = scanner.nextLine().toUpperCase();
                                                                                if(!searchVehicle.matches(isPlateNumber)) {
                                                                                        System.out.println(ANSI_RED + "Invalid plate!" + ANSI_RESET);
                                                                                } else {
                                                                                        boolean found = false;
                                                                                        for (int j = 0; j < parkingLot.length; j++) {
                                                                                                for (int k = 0; k < parkingLot[j].length; k++) {
                                                                                                        if (parkingLot[j][k] != null) {
                                                                                                                if (parkingLot[j][k].equals(searchVehicle)) {
                                                                                                                        System.out.println("Vehicle Found at Floor " + (j + 1) + ", Spot " + (k + 1));
                                                                                                                } else {
                                                                                                                        System.out.println(ANSI_RED + "Vehicle Not Found");
                                                                                                                }
                                                                                                                do {
                                                                                                                        System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                        String yesOrNo = scanner.nextLine();
                                                                                                                        if(yesOrNo.equalsIgnoreCase("y")) break;
                                                                                                                        if(yesOrNo.equalsIgnoreCase("n")) continue outerPagination;
                                                                                                                } while(true);
                                                                                                        }
                                                                                                        if(!found) {
                                                                                                                System.out.println(ANSI_RED + "No Vehicle Found with the plate number: " + searchVehicle + ANSI_RESET);
                                                                                                                do {
                                                                                                                        System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                                                                        String yesOrNo = scanner.nextLine();
                                                                                                                        if(yesOrNo.equalsIgnoreCase("y")) continue enterPlateNumber;
                                                                                                                        if(yesOrNo.equalsIgnoreCase("n")) continue outerPagination;
                                                                                                                        else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                                                                } while (true);
                                                                                                        }
                                                                                                }
                                                                                        }
                                                                                }
                                                                        } while(true);
                                                                }
                                                                case 3: {
                                                                        if(i * perPage < parkingLot.length && i > 0) {
                                                                                i = 0;
                                                                        } else {
                                                                                System.out.println(ANSI_RED + "You reached the end" + ANSI_RESET);
                                                                        }
                                                                        break;
                                                                }
                                                                case 4: {
                                                                        if((i + 1) * perPage < parkingLot.length) {
                                                                                i++;
                                                                                System.out.println(i);
                                                                        } else {
                                                                                System.out.println(ANSI_RED + "You reached the end" + ANSI_RESET);
                                                                        }
                                                                        break;
                                                                }
                                                                case 5: {
                                                                        if(i * perPage < parkingLot.length && i > 0) {
                                                                                i--;
                                                                        } else {
                                                                                System.out.println(ANSI_RED + "You reached the end" + ANSI_RESET);
                                                                        }
                                                                        break;
                                                                }
                                                                case 6: {
                                                                        if((i + 1) * perPage < parkingLot.length) {
                                                                                 i = (parkingLot.length - 1) / perPage;
                                                                                System.out.println(i);
                                                                        } else {
                                                                                System.out.println(ANSI_RED + "You reached the end" + ANSI_RESET);
                                                                        }
                                                                        break;
                                                                }
                                                                case 7: {
                                                                        continue mainMenu;
                                                                }
                                                        }
                                                }
                                        } while(true);
                                }
                                case 3: {
                                        systemSetting:
                                        do {
                                                System.out.println("-".repeat(100));
                                                System.out.println(" ".repeat(40) + "SYSTEM SETTING");
                                                System.out.println("-".repeat(100));
                                                System.out.print("""
                                                1. Reset Parking Space
                                                2. Set number of Floor to display
                                                3. Back
                                                """);
                                                System.out.println("-".repeat(100));
                                                String action;
                                                do {
                                                        System.out.print("--> Enter your choice(1-3): ");
                                                        action = scanner.nextLine();
                                                        if(!action.matches(isNumber) || Integer.parseInt(action) > 4 || Integer.parseInt(action) < 1)
                                                                System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                        else break;
                                                } while(true);
                                                switch(Integer.parseInt(action)) {
                                                        case 1: {
                                                                do {
                                                                        System.out.println(ANSI_YELLOW + "WARNING: This will remove all parked vehicles." + ANSI_RESET);
                                                                        System.out.print("--> Do you want to continue" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                                        String yesOrNo = scanner.nextLine();
                                                                        if(yesOrNo.equalsIgnoreCase("y")) {
                                                                                System.out.println(ANSI_CYAN + "Resetting parking space..." + ANSI_RESET);
                                                                                parkingLot = new String[Integer.parseInt(floor)][Integer.parseInt(room)];
                                                                                System.out.println();
                                                                                System.out.print("Press Enter " + ANSI_PURPLE + "to continue" + ANSI_RESET + "...");
                                                                                scanner.nextLine();
                                                                                continue mainMenu;
                                                                        }
                                                                        if(yesOrNo.equalsIgnoreCase("n")) continue systemSetting;
                                                                        else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                } while(true);
                                                        }
                                                        case 2: {
                                                                String showFloor;
                                                                do {
                                                                        System.out.print("--> Enter your how many floors to show: (max: " + parkingLot.length + ") ");
                                                                        showFloor = scanner.nextLine();
                                                                        if(!showFloor.matches(isNumber) || Integer.parseInt(showFloor) < 1) {
                                                                                System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                                                        } else if(Integer.parseInt(showFloor) > parkingLot.length) {
                                                                                System.out.println(ANSI_RED + "Input must not be higher than the current floor" + ANSI_RESET);
                                                                        }
                                                                        else {
                                                                                perPage = Integer.parseInt(showFloor);
                                                                                break;
                                                                        }
                                                                } while(true);
                                                        }
                                                        case 3:
                                                        {
                                                                continue mainMenu;
                                                        }
                                                }
                                        } while(true);
                                }
                                case 4: {
                                        do {
                                                System.out.print("--> Are you sure you want to quit" + ANSI_PURPLE + "(Y/N): " + ANSI_RESET);
                                                String yesOrNo = scanner.nextLine();
                                                if(yesOrNo.equalsIgnoreCase("y")) break mainMenu;
                                                if(yesOrNo.equalsIgnoreCase("n")) break;
                                                else System.out.println(ANSI_RED + "Invalid Input" + ANSI_RESET);
                                        } while(true);
                                }
                        }
                }
        } while (true);
}