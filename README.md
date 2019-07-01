# Power_Load_Game

基于遗传算法优化的发电侧与供电侧的利益博弈模型。

1、发电侧根据用户的用电量，平衡发电侧成交电量与发电价格的关系，为了达到发电侧利润最大化调整发电电价（求解利润最优解用采用遗传算法实现）。

2、供电侧根据发电侧的成交量，平衡供电侧成交电量与供电价格的关系，为了达到供电侧利润最大化调整供电价格。

3、供电价格影响用户用电行为，用户用电量通过用电行为反馈矩阵反馈到发电侧。

4、发电侧根据行为再次拟定方案。

只做了开始的发电侧与供电侧博弈，后续相关的功能会慢慢开发，代码也会慢慢优化。

界面可能会有一些图片加载不全的情况，请在代码中修改图片路径

若无法正常打开，通过IDEA等导入lib文件夹下的jar包

Maven工程直接添加依赖

![image](https://github.com/skyrimgo/Power_Load_Game/blob/master/UI.png)

注：目前程序的博弈模型还不太完善，后续将会加入政府的根据用户满意度实行的限价约束。