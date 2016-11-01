# Vinci_TestTask
Vinci 笔试代码  2016.11.1



## 目录说明：

./Vinci_TestTask_Java 内容为笔试第2,、3题 

> 2.排序（java程序）
>
> 3.把排序完成后的文件生成一个json数组存入文件结果类似（java程序）



./Vinci_TestTask_Android 内容为笔试第4题 (Android Studio)

> 4.写一个android程序，把json文件插入数据库，并且设计一个列表，把所有数据展示出来

./result 内容为代码生成的文件及演示素材

>newfile   第二题结果文件
>
>jsonfile   第三题结果文件



第一题 答案：  

````mysql
select Music.MusicName,Music.Singer,Address.DownloadUrl,Address.Size from Music inner join Address on Music.MusicId=Address.MusicId
````

之后在答案在代码中  





### 演示效果

![image](https://github.com/zhaolewei/Vinci_TestTask/blob/master/result/result.png)











