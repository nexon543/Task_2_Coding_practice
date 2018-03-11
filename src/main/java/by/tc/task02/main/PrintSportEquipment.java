package by.tc.task02.main;

import by.tc.task02.model.SportEquipment;
import by.tc.task02.model.SportEquipmentType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class PrintSportEquipment {
    public static void print(Map<SportEquipmentType, List<SportEquipment>> sportEquipments) {
        Set<Map.Entry<SportEquipmentType, List<SportEquipment>>> entries = sportEquipments.entrySet();
        for (Map.Entry<SportEquipmentType, List<SportEquipment>> entry : entries) {
            System.out.println(entry.getKey().toString() + " Quantity: " + entry.getValue().size());
        }
    }

    public static void printList(List<SportEquipment> sportEquipments) {
        int recordNumber = 1;
        for (SportEquipment sportEquipment : sportEquipments) {
            System.out.println(recordNumber + ") " + sportEquipment.toString());
            recordNumber++;
        }
    }

    public static void printEquipmentsMenuMap(Map<SportEquipment, Integer> menuMap) {
        menuMap.forEach((k, v) -> System.out.println(v + ") " + k));
    }
    public static void printEquipmentTypeMenuMap(Map<SportEquipmentType, Integer> menuMap) {
        menuMap.forEach((k, v) -> System.out.println(k + " - " + v));
    }

}
