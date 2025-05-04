package com.criteo;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.config.solver.SolverConfig;
import org.optaplanner.core.config.solver.termination.TerminationConfig;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<Item> items = loadItems();
        List<Slot> slots = loadSlots();
        Scheduler scheduler = new Scheduler(slots, items);

        SolverConfig solverConfig = SolverConfig.createFromXmlResource("solverConfig.xml");
        solverConfig.withTerminationConfig(new TerminationConfig().withMinutesSpentLimit(1L));

        SolverFactory<Scheduler> solverFactory = SolverFactory.create(solverConfig);
        Solver<Scheduler> solver = solverFactory.buildSolver();


        Scheduler solvedScheduler = solver.solve(scheduler);
        System.out.println(solvedScheduler);
    }

    private static List<Slot> loadSlots() {
        List<Slot> result = List.of(
                new Slot(1, 1),
                new Slot(2, 1),
                new Slot(1, 2),
                new Slot(2, 2),
                new Slot(1, 3),
                new Slot(2, 3),
                new Slot(1, 4),
                new Slot(2, 4),
                new Slot(1, 5),
                new Slot(2, 5)
        );
        return result;
    }

    private static List<Item> loadItems() {
        List<Item> result = List.of(
                new Item(1, 1),
                new Item(1, 2),
                new Item(1, 3),
                new Item(2, 4),
                new Item(2, 5),
                new Item(2, 6)
        );
        return result;
    }
}