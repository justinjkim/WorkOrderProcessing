package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Processor {

    public static Map<Status, Set<WorkOrder>> workMap = new HashMap<>();


    public void processWorkOrders() {
        moveIt();
        readIt();
    }

    private void moveIt() {
        // move work orders in map from one state to another
        System.out.println(workMap);
        for (Status status: workMap.keySet()) {
            Set<WorkOrder> currentSet = workMap.get(status);
            switch(status.toString()) {
                case "IN_PROGRESS":

                    // iterate thrr set of work orders under current status
                    for (WorkOrder workorder: currentSet) {
                        currentSet.remove(workorder);
                        workorder.setStatus(Status.DONE);
                        workMap.get(Status.DONE).add(workorder);

                    }

                    // for each work order
                        // remove from current work order set
                        // change status to next status
                        // add to next work order set




                    break;
            }
        }
        System.out.println(workMap);
    }

    private void readIt() {
        // read the json files into WorkOrders and put in map
    }

    public static void main(String args[]) {
        Processor processor = new Processor();
        while (processor == processor) {
            try {
                System.out.println(workMap);
                processor.processWorkOrders();
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
