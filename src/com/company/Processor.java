package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Processor {

    Map<Status, Set<WorkOrder>> workMap = new HashMap<>();



    public void processWorkOrders() {
        moveIt();
        readIt();
    }

    private void moveIt() {
        // move work orders in map from one state to another
    }

    private void readIt() {
        // read the json files into WorkOrders and put in map
    }

    public static void main(String args[]) {
        Processor processor = new Processor();
        while (processor == processor) {
            try {
                processor.processWorkOrders();
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
