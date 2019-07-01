package com.szy;

/**
 * ElectricitySupply
 */
public class ElectricitySupply {
    double q_1 = 1050;
    double q_2 = 880.1;
    double q_3 = 1523.5;
    double p_f1 = 0.0735;
    double p_f2 = 0.2880;
    double p_f3 = 0.4365;
    double p_g1 = 0.1628;
    double p_g2 = 0.4723;
    double p_g3 = 0.7083;

    public void init() {
        //System.out.println("初始供电侧定价：" + p_g1 + ":" + p_g1 + ":" + p_g1);
        //System.out.println("初始供电侧成交量：" + q_1 + ":" + q_2 + ":" + q_3);
        double maxR_g = (p_g1 - p_f1) * q_1 + (p_g2 - p_f2) * q_2 + (p_g3 - p_f3) * q_3;// 发电侧收益
        //System.out.println("初始供电侧收益：" + maxR_g + "万元");
    }
}