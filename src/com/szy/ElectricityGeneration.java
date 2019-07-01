package com.szy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ElectricityGeneration
 */
public class ElectricityGeneration {
    double q_1=1224;
    double q_2=980;
    double q_3 = 1824;

    public void init() {
        double[] data_of_power_load = { 963.6, 893.2, 641.2, 594.6, 563.6, 547.6, 538.3, 601.5, 704.4, 938.4, 1032.6,
                1187.5, 1287.4, 1340.8, 1288.7, 1194.4, 1117.0, 1207.4, 1262.0, 1301.4, 1278.4, 1320.9, 1251.6,
                1142.2 };
        double n_1 = 0;
        for (double data : data_of_power_load) {
            n_1 = Math.max(data, n_1);
        }
        double w_1 = 1500;
        double j_1 = 8800;
        double delta_1 = 0.0333;
        double c_fd1 = 0.0032;
        double c_fd2 = 0.0032;
        double c_fd3 = 0.0032;

        double q_min = Double.MAX_VALUE;

        //
        List<Double> q_1_list = new ArrayList<>();
        List<Double> q_2_list = new ArrayList<>();
        List<Double> q_3_list = new ArrayList<>();
        for (int i = 9; i <= 21; i++) {
            q_3_list.add(data_of_power_load[i]);
        }
        q_2_list.add(data_of_power_load[7]);
        q_2_list.add(data_of_power_load[8]);
        q_2_list.add(data_of_power_load[22]);
        q_2_list.add(data_of_power_load[23]);
        for (int i = 0; i <= 6; i++) {
            q_1_list.add(data_of_power_load[i]);
        }

        //
        for (double data : data_of_power_load) {
            q_min = Math.min(q_min, data);
        }
        double q_avg = Arrays.stream(data_of_power_load).sum() / 24;

        double q_11 = q_min * 7;
        double q_21 = q_min * 4;
        double q_22 = (q_avg - q_min) * 4;
        double q_31 = q_min * 13;
        double q_32 = (q_avg - q_min) * 13;
        double q_33 = 0;
        for (double data : q_3_list) {
            q_33 += Math.max(data - q_avg, 0);
        }

        double f_1 = w_1 * j_1 * delta_1 / 365;
        double f_2 = f_1 / n_1;
        // System.out.println(f_2);

        double l_1 = 0;
        for (double var : q_1_list) {
            l_1 = Math.max(var, l_1);
        }
        double l_2 = 0;
        for (double var : q_2_list) {
            l_2 = Math.max(var, l_2);
        }
        double l_3 = 0;
        for (double var : q_3_list) {
            l_3 = Math.max(var, l_3);
        }
        double c_j = f_2 * l_1 / (q_11 + q_21 + q_31);
        double c_y = f_2 * l_2 / (q_22 + q_32);
        double c_f = f_2 * l_3 / q_33;

        double c_fr1 = c_j;// 谷时刻固定成本
        double c_fr2 = (c_j * q_21 + c_y * q_22) / (q_21 + q_22);// 平时刻固定成本
        double c_fr3 = (c_j * q_31 + c_y * q_32 + c_f * q_33) / (q_31 + q_32 + q_33);// 峰时刻固定成本
        double c_f1 = c_fd1 + c_fr1;
        double c_f2 = c_fd2 + c_fr2;
        double c_f3 = c_fd3 + c_fr3;
        //System.out.println("初始发电侧成本：" + c_f1 + ":" + c_f2 + ":" + c_f3);
        double A = 2000.318;
        double B = 1500.1;
        double p_f1 = 0.07;
        double p_f2 = 0.28;
        double p_f3 = .047;
        double q_1 = A - B * p_f1;
        double q_2 = A - B * p_f2;
        double q_3 = A - B * p_f3;
        //System.out.println("初始发电侧定价：" + p_f1 + ":" + p_f1 + ":" + p_f1);
        //System.out.println("初始发电侧成交量：" + q_1 + ":" + q_2 + ":" + q_3);
        double maxR_f = (p_f1 - c_f1) * q_1 + (p_f2 - c_f2) * q_2 + (p_f3 - c_f3) * q_3;// 发电侧收益
        //System.out.println("初始发电侧收益：" + maxR_f + "万元");
    }
}