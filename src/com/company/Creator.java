package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Creator {
    public void createWorkOrders() throws IOException {
        // read input, create work orders and write as json files
        Scanner input = new Scanner(System.in);
        WorkOrder workOrder = new WorkOrder();
        int workID = 1;

        System.out.println("Name: ");
        workOrder.setSenderName(input.nextLine());
        System.out.println("Description: ");
        workOrder.setDescription(input.nextLine());
        workOrder.setStatus(Status.INITIAL);
        workOrder.setId(workID);

        ObjectMapper map = new ObjectMapper();
        map.writeValue(new File(workID + ".json"), workOrder);

        workID++;
    }

    public static void main(String args[]) throws IOException {
        Creator creator = new Creator();
        creator.createWorkOrders();
    }
}
