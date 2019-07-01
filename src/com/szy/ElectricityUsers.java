package com.szy;

/**
 * ElectricityUsers
 */
public class ElectricityUsers {

    public double[] run(double p_g1, double p_g2, double p_g3, double p_g1_new, double p_g2_new, double p_g3_new,
            double q_1, double q_2, double q_3) {
        double del_1 = (p_g1_new - p_g1) / p_g1;// 基荷电价增长
        double del_2 = (p_g2_new - p_g2) / p_g2;// 平荷电价增长
        double del_3 = (p_g3_new - p_g3) / p_g3;// 峰荷电价增长
        // System.out.println(p_g1+":"+ p_g1_new+":"+p_g2+":"+ p_g2_new+":"+p_g3+":"+
        // p_g3_new);
        // System.out.println(del_1+":"+del_2+":"+del_3);
        // double q_1 = 1295.311;
        // double q_2 = 980.29;
        // double q_3 = 1329.813;
        // q_1 =q_1- (0.035 * del_1 + 0.01 * del_2 + 0.02 * del_3) * q_1;
        // q_2 = q_2 - (0.01 * del_1 + 0.035 * del_2 + 0.01 * del_3) * q_2;
        // q_3 = q_3 - (0.02 * del_1 + 0.02 * del_2 + 0.035 * del_3) * q_3;
        q_1 = q_1 - (0.35 * del_1 + 0.1 * del_2 + 0.2 * del_3) * q_1;
        q_2 = q_2 - (0.1 * del_1 + 0.35 * del_2 + 0.1 * del_3) * q_2;
        q_3 = q_3 - (0.2 * del_1 + 0.2 * del_2 + 0.35 * del_3) * q_3;
        // q_1 = Math.max(q_1, 700+100*Math.random());
        // q_2 = Math.max(q_2, 600 + 80 * Math.random());
        // q_3 = Math.max(q_3, 1000 + 200 * Math.random());
        double consume = (p_g1_new * q_1 + p_g2_new * q_2 + p_g3_new * p_g3) / (q_1 + q_2 + q_3);
        //String str = "用户（不参与博弈）行为下的用电量为" + q_1 + ":" + q_2 + ":" + q_3 + '\n' + "用户消费金额：" + consume + '\n';

        //System.out.println(str);
        double[] q_array = { q_1, q_2, q_3 };
        return q_array;
    }
}