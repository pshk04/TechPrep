package Sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class SpaceXEmployeeItemSorting {

    public static void main(String[] args) {
        Object[][][] itemsArray = {
                {
                        {"helmet", 1, 500},
                        {"wrench", 2, 300},
                        {"gloves", 1, 200},
                        {"boots", 2, 300},
                        {"visor", 1, 200}
                },
                {
                        {"oxygen",3,800},
                        {"food",1,150},
                        {"water",1,200},
                        {"toolkit",2,400}
                }
        };
        List<List<Object>> itemsList;
        List<Object> itemList;

        for(int i = 0; i < itemsArray.length; i++) {
            itemsList = new ArrayList<>();
            for (int j = 0; j < itemsArray[i].length; j++) {
                itemList = new ArrayList<>();
                Object[] itemArray = itemsArray[i][j];
                for(int k = 0; k < itemArray.length; k++) {
                    System.out.println("Adding: "+String.valueOf(itemArray[k]));
                    itemList.add(itemArray[k]);
                }
                itemsList.add(itemList);
            }
            System.out.println("Sorted items list: "+sortItems(itemsList));
        }

    }

    public static List<String> sortItems(List<List<Object>> items) {

        List<String> priorityList = new ArrayList<>();

        PriorityQueue<Object[]> itemsMinHeap = new PriorityQueue<>((a, b) -> {
            int p1 = Integer.compare(Integer.parseInt(String.valueOf(a[1])), Integer.parseInt(String.valueOf(b[1])));

            if (p1 == 0) {
                int p2 = Integer.compare(java.lang.Integer.parseInt(a[2].toString()), java.lang.Integer.parseInt(b[2].toString()));

                if (p2 == 0) {
                    return ((String) a[0]).compareTo((String) b[0]);
                }
                return p2;
            }
            return p1;
        });

        System.out.println("Items size: "+items.size());

        for(int i = 0; i < items.size(); i++) {
            List<Object> itemList = items.get(i);
            Object[] objArray = new Object[itemList.size()];
            for(int j = 0; j < itemList.size(); j++) {
                String itemName = String.valueOf(itemList.get(j));
                objArray[j] = itemName;
            }
            itemsMinHeap.offer(objArray);
        }

        while(!itemsMinHeap.isEmpty()){
            Object A = ((Object) itemsMinHeap.poll()[0]);
            priorityList.add(String.valueOf(A));
        }

        return priorityList;
    }
}
