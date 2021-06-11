# bdms_project_spark_json
使用方法：
1. 运行generation.py生成数据集。数据集包括200多万行数据，每一行记录即是一个json文件。数据集包括嵌套json、json array结构。
2. 加载pom.xml中的maven依赖，修改ReadParquet.java和ReadTextJson.java中的文件读取路径，运行代码，进行测试。

测试方案：
1. Read file test:
   测试将文件读入内存的所用的时间（ms）
2. Selection test:
   “选择”操作从数据集中选择一些特定行，程序记录“选择”操作所用的时间（ms）
3. Projection test1:
   “投影”操作从数据集中选择一些特定列（嵌套json），程序记录“投影”操作所用的时间（ms）
4. Projection test2:
   “投影”操作从数据集中选择一些特定列（json数组），程序记录“投影”操作所用的时间（ms）
5. Aggregation test:
   测试group by、count、avg操作，程序记录“聚集”操作所用的时间（ms）

总结：

把json存储为parquet, 可以节省50%左右的存储空间，另外，spark读取parque的效率也远高于直接读取json文件的效率。
但是当spark把数据读取到计算机内存后，再对数据进行选择、投影、聚集分析操作的效率不相上下，取决于计算机性能。
