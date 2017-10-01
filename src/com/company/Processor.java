package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
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
        
        Set<WorkOrder> currentSet = workMap.get(Status.IN_PROGRESS);
        for (WorkOrder workorder: currentSet) {
            workorder.setStatus(Status.DONE);
            workMap.get(Status.DONE).add(workorder);
        }
        workMap.put(Status.IN_PROGRESS, new HashSet<WorkOrder>());

        currentSet = workMap.get(Status.ASSIGNED);
        for (WorkOrder workorder: currentSet) {
            workorder.setStatus(Status.IN_PROGRESS);
            workMap.get(Status.IN_PROGRESS).add(workorder);
        }
        workMap.put(Status.ASSIGNED, new HashSet<WorkOrder>());

        currentSet = workMap.get(Status.INITIAL);
        for (WorkOrder workorder: currentSet) {
            workorder.setStatus(Status.ASSIGNED);
            workMap.get(Status.ASSIGNED).add(workorder);
        }
        workMap.put(Status.INITIAL, new HashSet<WorkOrder>());


//        for (Status status: workMap.keySet()) {
//            Set<WorkOrder> currentSet = workMap.get(status);
//            switch(status.toString()) {
//                case "IN_PROGRESS":
//                    // iterate through set of work orders under current status
//                    for (WorkOrder workorder: currentSet) {
//                        workorder.setStatus(Status.DONE);
//                        workMap.get(Status.DONE).add(workorder);
//                    }
//                    break;
//                case "ASSIGNED":
//                    for (WorkOrder workorder: currentSet) {
//                        workorder.setStatus(Status.IN_PROGRESS);
//                        workMap.get(Status.IN_PROGRESS).add(workorder);
//                    }
//                    break;
//                case "INITIAL":
//                    for (WorkOrder workorder: currentSet) {
//                        workorder.setStatus(Status.ASSIGNED);
//                        workMap.get(Status.ASSIGNED).add(workorder);
//                    }
//                    break;
//            } // end of switch statement
//
//            // this overwrites the previously existing set in order to avoid the ConcurrentModificationException
//            workMap.put(status, new HashSet<WorkOrder>());
//        } // end of for loop


        System.out.println(workMap);
    } // end of moveIt method

    private void readIt() {
        // read the json files into WorkOrders and put in map
        File currentDirectory = new File(".");
        File files[] = currentDirectory.listFiles();

        for (File f : files) {
            if (f.getName().endsWith(".json")) {
                // f is a reference to a json file
                ObjectMapper mapper = new ObjectMapper();
                WorkOrder wo = null;
                try {
                    wo = mapper.readValue(f, WorkOrder.class);
                    workMap.get(Status.INITIAL).add(wo);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // f.delete(); will delete the file
                f.delete();
            }
        }
    }

    public static void main(String args[]) {
        Processor processor = new Processor();

        Set<WorkOrder> initial = new HashSet<>();
        Set<WorkOrder> assigned = new HashSet<>();
        Set<WorkOrder> in_progress = new HashSet<>();
        Set<WorkOrder> done = new HashSet<>();

        workMap.put(Status.INITIAL, initial);
        workMap.put(Status.ASSIGNED, assigned);
        workMap.put(Status.IN_PROGRESS, in_progress);
        workMap.put(Status.DONE, done);

        while (processor == processor) {
            try {
                processor.processWorkOrders();
                Thread.sleep(3000l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
