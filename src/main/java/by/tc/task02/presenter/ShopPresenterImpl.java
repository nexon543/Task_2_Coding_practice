package by.tc.task02.presenter;

import by.tc.task02.main.PrintSportEquipment;
import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;
import by.tc.task02.model.criteria.CriteriaConstants;
import by.tc.task02.service.ShopService;
import by.tc.task02.service.ShopServiceImpl;
import by.tc.task02.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShopPresenterImpl implements ShopPresenter {

    private static String ENTER_USERNAME_MESSAGE = "Please, enter your account name: ";
    private static String ENTER_RENTED_QUIPMENT_MESSAGE = "Which one would you like to rent? Enter the number: ";
    private static String MAIN_MENY_MESSAGE = "Hello! How can I help you? Enter the number: ";
    private static String RENT_SUCCEED = "You have rented: ";
    private static String RENT_FAILD = "Sorry, you are not available to rent.";
    private static String USER_RENT_LIST_MESSAGE="List of your rented sport equipments:";

    private ShopService shopService;
    private String renter;
    private Scanner scanner;
    private int lastChoice;
    private SportEquipment objectOfChoice;


    public ShopPresenterImpl() throws ServiceException {
        shopService = new ShopServiceImpl();
        renter = CriteriaConstants.NO_RENTER;
        scanner = new Scanner(System.in);
    }

    private int getChoiceInt(String message) {
        System.out.println(message);
        int choice = 0;
        if (scanner.hasNext()) {
            choice = scanner.nextInt();
        }
        return choice;
    }

    private String getChoiceString(String message) {
        System.out.println(message);
        String choice = null;
        if (scanner.hasNext()) {
            choice = scanner.next();
        }
        return choice;
    }

    private void printMainMenu() {
        System.out.println("1. Enter account\n" +
                "2. See the number of sport equipments\n" +
                "3. Get list of all sport equipments\n" +
                "4. Get list of my rented equipments\n" +
                "5. See rented equipments of all users\n");
    }

    public void mainMenu() throws ServiceException {
        int choice = -1;
        while (choice != 0) {
            printMainMenu();
            choice = getChoiceInt(MAIN_MENY_MESSAGE);
            serviceMainMenuChoice(choice);
        }
    }

    private void serviceMainMenuChoice(int choice) throws ServiceException {
        switch (choice) {
            case 1:
                renter = getChoiceString(ENTER_USERNAME_MESSAGE);
                System.out.println("You have entered as: " + renter);
                shopService.setRenter(renter);
                break;
            case 2:
                PrintSportEquipment.printEquipmentTypeMenuMap(shopService.getAllAvailableCount());
                System.out.println("Go to menu number 3 to choose specific product");
                break;
            case 3:
                showAvailableSportEquipmentAndRent();
                break;
            case 4:
                System.out.println(USER_RENT_LIST_MESSAGE);
                PrintSportEquipment.printList(shopService.getCurrentUserRentedSportEquipments());
                break;
            case 5:
                PrintSportEquipment.printList(shopService.getAllRented());
                break;
        }
    }

    private void showAvailableSportEquipmentAndRent() throws ServiceException {
        List<SportEquipment> available = shopService.getAllAvailable();
        Map<Integer, SportEquipment> menuMap = getMenuMap(available);
        menuMap.forEach((k, v) -> System.out.println(k + ") " + v));
        System.out.println(ENTER_RENTED_QUIPMENT_MESSAGE);
        lastChoice = scanner.nextInt();
        objectOfChoice = menuMap.get(lastChoice);
        try {
            if (shopService.rentSportEquipment(objectOfChoice)) {
                System.out.println(RENT_SUCCEED+objectOfChoice);
            } else {
                System.out.println(RENT_FAILD);
            }
        }
        catch(ServiceException se){System.out.println(RENT_FAILD);}
    }

    private Map<Integer, SportEquipment> getMenuMap(List<SportEquipment> sportEquipments) {
        Map menuMap = new TreeMap();
        int recordNumber = 1;
        for (SportEquipment sportEquipment : sportEquipments) {
            menuMap.put(recordNumber++, sportEquipment);
        }
        return menuMap;
    }

}
