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

    @Override
    public void initUser() {
        System.out.println("Please, enter your account name: ");
        if (scanner.hasNext()) {
            renter = scanner.nextLine();
        }
    }

    @Override
    public int showAvailableSportEquipment() throws ServiceException {
        List<SportEquipment> available = shopService.getAllAvailable();
        Map<Integer, SportEquipment> menuMap=getMenuMap(available);
        menuMap.forEach((k,v)-> System.out.println(k+") "+v));
        System.out.println("Which one do you want to rent? ");
        lastChoice=scanner.nextInt();
        objectOfChoice=menuMap.get(lastChoice);
        return lastChoice;
    }


    private Map<SportEquipmentType, Integer> getTypeMenuMap(Map<SportEquipmentType, Integer> sportEquipmentQuantity) {
        Map<SportEquipmentType, Integer> counterAvailableTypes=shopService.getAllAvailableCount();
        Map menuMap = new TreeMap();
        Integer recordNumber = 1;

        return menuMap;
    }

    private Map<Integer, SportEquipment> getMenuMap(List<SportEquipment> sportEquipments) {
        Map menuMap = new TreeMap();
        int recordNumber = 1;
        for (SportEquipment sportEquipment : sportEquipments) {
            menuMap.put(recordNumber++, sportEquipment);
        }
        return menuMap;
    }

    @Override
    public int showSportEquipmentOfType(SportEquipmentType sportEquipmentType) throws ServiceException {
        PrintSportEquipment.printList(shopService.getAvailable(sportEquipmentType));
        return scanner.nextInt();
    }

    @Override
    public void rentSportEquipment() {

    }
}
