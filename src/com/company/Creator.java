package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Creator {
    private int workID = 1;

    public void createWorkOrders() throws IOException {
        // read input, create work orders and write as json files
        WorkOrder workOrder = new WorkOrder();

        Scanner input = new Scanner(System.in);



        System.out.println("Name: ");
        workOrder.setSenderName(input.nextLine());
        System.out.println("Description: ");
        workOrder.setDescription(input.nextLine());
        workOrder.setStatus(Status.INITIAL);
        workOrder.setId(workID);

        ObjectMapper map = new ObjectMapper();
        map.writeValue(new File(workID + ".json"), workOrder);

        // test and see if JSON is extracted
//        String json = map.writeValueAsString(workOrder);
//        System.out.println(json);

        workID++;
    }

    public static void main(String args[]) throws IOException {
        Creator creator = new Creator();
        creator.createWorkOrders();
        creator.createWorkOrders();
        creator.createWorkOrders();
    }
}
