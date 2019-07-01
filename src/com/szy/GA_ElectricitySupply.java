package com.szy;

public class GA_ElectricitySupply {

    private int ChrNum = 10; // 染色体数量
    private String[] ipop = new String[ChrNum]; // 一个种群中染色体总数
    private int generation = 0; // 染色体代号
    public static final int GENE = 69; // 基因数***
    private double bestfitness = Double.MAX_VALUE; // 函数最优解
    private int bestgenerations; // 所有子代与父代中最好的染色体
    private String beststr; // 最优解的染色体的二进制码

    /**
     * 初始化一条染色体（用二进制字符串表示）
     */
    private String initChr() {
        String res = "";
        for (int i = 0; i < GENE; i++) {
            if (Math.random() > 0.5) {
                res += "0";
            } else {
                res += "1";
            }
        }
        return res;
    }

    /**
     * 初始化一个种群(10条染色体)
     */
    private String[] initPop() {
        String[] ipop = new String[ChrNum];
        for (int i = 0; i < ChrNum; i++) {
            ipop[i] = initChr();
        }
        return ipop;
    }

    /**
     * 将染色体转换成x,y变量的值
     */
    private double[] calculatefitnessvalue(String str, double p_f1, double p_f2, double p_f3) {

        // 二进制数前23位为x的二进制字符串，后23位为y的二进制字符串
        int a = Integer.parseInt(str.substring(0, 23), 2);
        int b = Integer.parseInt(str.substring(23, 46), 2);
        int c = Integer.parseInt(str.substring(46, 69), 2);

        // double x = a * (6.0 - 0.0) / (Math.pow(2, 23) - 1); // x的基因
        // double y = b * (6.0 - 0.0) / (Math.pow(2, 23) - 1); // y的基因
        // double z = b * (6.0 - 0.0) / (Math.pow(2, 23) - 1);
        double x = a * (1.0 - 0.0) / (Math.pow(2, 23) - 1); // x的基因
        double y = b * (1.0 - 0.0) / (Math.pow(2, 23) - 1); // y的基因
        double z = c * (1.0 - 0.0) / (Math.pow(2, 23) - 1) + 0.5;

        // 需优化的函数
        // double fitness = 3 - Math.sin(2 * x) * Math.sin(2 * x) - Math.sin(2 * y) *
        // Math.sin(2 * y);
        // System.out.println(p_f3);
        double fitness = -2000 * ((x - p_f1) * (0.25 - x) + (y - p_f2) * (0.62 - y) + (z - p_f3) * (0.8 - z));
        // double fitness = x + y - z;
        // double fitness = x + y+z;

        double[] returns = { x, y, z, fitness };
        return returns;

    }

    /**
     * 轮盘选择 计算群体上每个个体的适应度值; 按由个体适应度值所决定的某个规则选择将进入下一代的个体;
     */
    private void select(double p_f1, double p_f2, double p_f3) {
        double evals[] = new double[ChrNum]; // 所有染色体适应值
        double p[] = new double[ChrNum]; // 各染色体选择概率
        double q[] = new double[ChrNum]; // 累计概率
        double F = 0; // 累计适应值总和
        for (int i = 0; i < ChrNum; i++) {
            evals[i] = calculatefitnessvalue(ipop[i], p_f1, p_f2, p_f3)[3];
            if (evals[i] < bestfitness) { // 记录下种群中的最小值，即最优解
                bestfitness = evals[i];
                bestgenerations = generation;
                beststr = ipop[i];
            }

            F = F + evals[i]; // 所有染色体适应值总和
        }

        for (int i = 0; i < ChrNum; i++) {
            p[i] = evals[i] / F;
            if (i == 0)
                q[i] = p[i];
            else {
                q[i] = q[i - 1] + p[i];
            }
        }
        for (int i = 0; i < ChrNum; i++) {
            double r = Math.random();
            if (r <= q[0]) {
                ipop[i] = ipop[0];
            } else {
                for (int j = 1; j < ChrNum; j++) {
                    if (r < q[j]) {
                        ipop[i] = ipop[j];
                    }
                }
            }
        }
    }

    /**
     * 交叉操作 交叉率为60%，平均为60%的染色体进行交叉
     */
    private void cross() {
        String temp1, temp2;
        for (int i = 0; i < ChrNum; i++) {
            if (Math.random() < 0.60) {
                int pos = (int) (Math.random() * GENE) + 1; // pos位点前后二进制串交叉
                temp1 = ipop[i].substring(0, pos) + ipop[(i + 1) % ChrNum].substring(pos);
                temp2 = ipop[(i + 1) % ChrNum].substring(0, pos) + ipop[i].substring(pos);
                ipop[i] = temp1;
                ipop[(i + 1) / ChrNum] = temp2;
            }
        }
    }

    /**
     * 基因突变操作 1%基因变异
     */
    private void mutation() {
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * GENE * ChrNum + 1);
            int chromosomeNum = (int) (num / GENE) + 1; // 染色体号

            int mutationNum = num - (chromosomeNum - 1) * GENE; // 基因号
            if (mutationNum == 0)
                mutationNum = 1;
            chromosomeNum = chromosomeNum - 1;
            if (chromosomeNum >= ChrNum)
                chromosomeNum = 9;
            String temp;
            String a; // 记录变异位点变异后的编码
            if (ipop[chromosomeNum].charAt(mutationNum - 1) == '0') { // 当变异位点为0时
                a = "1";
            } else {
                a = "0";
            }
            // 当变异位点在首、中段和尾时的突变情况
            if (mutationNum == 1) {
                temp = a + ipop[chromosomeNum].substring(mutationNum);
            } else {
                if (mutationNum != GENE) {
                    temp = ipop[chromosomeNum].substring(0, mutationNum - 1) + a
                            + ipop[chromosomeNum].substring(mutationNum);
                } else {
                    temp = ipop[chromosomeNum].substring(0, mutationNum - 1) + a;
                }
            }
            // 记录下变异后的染色体
            ipop[chromosomeNum] = temp;
        }
    }

    public double[] run(double p_f1, double p_f2, double p_f3, double[] p_g_array) {

        GA_ElectricitySupply Tryer = new GA_ElectricitySupply();
        Tryer.ipop = Tryer.initPop(); // 产生初始种群
        String str = "";

        // 迭代次数
        for (int i = 0; i < 1000; i++) {
            Tryer.select(p_f1, p_f2, p_f3);
            Tryer.cross();
            Tryer.mutation();
            Tryer.generation = i;
        }

        double[] x = Tryer.calculatefitnessvalue(Tryer.beststr, p_f1, p_f2, p_f3);

      //  str = "供电收益最大值" + -Tryer.bestfitness + "万元" + '\n' + "第" + Tryer.bestgenerations + "个染色体:<" + Tryer.beststr
                //+ ">" + '\n' + "电价分别为" + "x=" + x[0] + '\n' + "y=" + x[1] + '\n' + "z=" + x[2];

        //System.out.println(str);
        p_g_array[0] = p_g_array[3];
        p_g_array[1] = p_g_array[4];
        p_g_array[2] = p_g_array[5];
        p_g_array[3] = x[0];
        p_g_array[4] = x[1];
        p_g_array[5] = x[2];
        return p_g_array;

    }

    public double getFitness(double p_f1, double p_f2, double p_f3, double[] p_g_array) {

        GA_ElectricitySupply Tryer = new GA_ElectricitySupply();
        Tryer.ipop = Tryer.initPop(); // 产生初始种群
        String str = "";

        // 迭代次数
        for (int i = 0; i < 1000; i++) {
            Tryer.select(p_f1, p_f2, p_f3);
            Tryer.cross();
            Tryer.mutation();
            Tryer.generation = i;
        }
        double[] x = Tryer.calculatefitnessvalue(Tryer.beststr, p_f1, p_f2, p_f3);
        return -Tryer.bestfitness;

    }
}