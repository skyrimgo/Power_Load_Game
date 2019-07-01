package com.szy;

import javafx.fxml.FXML;
import sample.Main;

import java.util.ArrayList;
import java.util.List;

/**
 * Main
 */

public class ChartDraw {
    public List<List<Double>> run(double q1, double q2, double q3, double pg1, double pg2, double pg3, int times) {
        ElectricityGeneration electricityGeneration = new ElectricityGeneration();
        electricityGeneration.init();
        ElectricitySupply electricitySupply = new ElectricitySupply();
        electricitySupply.init();
        GA_ElectricityGeneration ga_ElectricityGeneration = new GA_ElectricityGeneration();
        GA_ElectricitySupply ga_ElectricitySupply = new GA_ElectricitySupply();
        ElectricityUsers electricityUsers = new ElectricityUsers();
        //double[] q_array = {1224, 980, 1824};
        double[] q_array = {q1, q2, q3};
        double[] p_f_array = {0.0735, 0.2880, 0.4365};
        //double[] p_g_array = {0.1628, 0.4732, 0.7083, 0.1628, 0.4732, 0.7083};
        double[] p_g_array = {pg1, pg2, pg3, pg1, pg2, pg3};
        List<List<Double>> res = new ArrayList<>();
        List<Double> generate_fitness_lists = new ArrayList<>();
        List<Double> supply_fitness_lists = new ArrayList<>();


        for (int i = 1; i <= times; i++) {
            //System.out.println("第" + i + "次博弈：");
            p_f_array = ga_ElectricityGeneration.run(q_array[0], q_array[1], q_array[2]);
            generate_fitness_lists.add(ga_ElectricityGeneration.getFitness(q_array[0], q_array[1], q_array[2]));

            p_g_array = ga_ElectricitySupply.run(p_f_array[0], p_f_array[1], p_f_array[2], p_g_array);
            supply_fitness_lists.add(ga_ElectricitySupply.getFitness(p_f_array[0], p_f_array[1], p_f_array[2], p_g_array));
            q_array = electricityUsers.run(p_g_array[0], p_g_array[1], p_g_array[2], p_g_array[3], p_g_array[4],
                    p_g_array[5], q_array[0], q_array[1], q_array[2]);

        }
        res.add(generate_fitness_lists);
        res.add(supply_fitness_lists);
        return res;
    }

}