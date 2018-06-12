
INSERT INTO t_user VALUES(
	'15331138', '赖达强', '1234', NULL, '男', '数据科学与计算机学院', '2013', '2qq@qq.com'
);	
INSERT INTO t_user VALUES(
	'15331073', '方虹', '1234', NULL, '女', '数据科学与计算机学院', '2015', 'sysu@163.com'
);
INSERT INTO t_user VALUES(
	'15331075', '方晓吟', '1234', NULL, '女', '数据科学与计算机学院', '2015', 'sysu@163.com'
);	
	
INSERT INTO question VALUES('1', '测试问题', '下雨吗？', '15331138',NULL); 
INSERT INTO question VALUES('2', '测试问题2', '放假？', '15331073',NULL); 

INSERT INTO answer VALUES (NULL, '不知道呢','15331073','1',NULL) ;
INSERT INTO answer VALUES (NULL, '不会下雨的吧','15331075','1',NULL) ;
INSERT INTO answer VALUES (NULL, '端午放假！','15331138','2',NULL) ;

INSERT INTO article VALUES (NULL, '论学习的重要性','blablabla','15331138',NULL, NULL, '学术');
INSERT INTO article VALUES (NULL, '怎么更好的打代码','blablabla','15331075',NULL, NULL, '编程');

INSERT INTO t_comment VALUES(NULL, '沙发！', '15331073', '1',NULL);
INSERT INTO t_comment VALUES(NULL, '板凳！', '15331075', '1',NULL);


insert into article values(null, '全文搜索引擎 Elasticsearch 入门教程', '全文搜索属于最常见的需求，开源的 Elasticsearch （以下简称 Elastic）是目前全文搜索引擎的首选。

它可以快速地储存、搜索和分析海量数据。维基百科、Stack Overflow、Github 都采用它。



Elastic 的底层是开源库 Lucene。但是，你没法直接用 Lucene，必须自己写代码去调用它的接口。Elastic 是 Lucene 的封装，提供了 REST API 的操作接口，开箱即用。

本文从零开始，讲解如何使用 Elastic 搭建自己的全文搜索引擎。每一步都有详细的说明，大家跟着做就能学会。

一、安装
Elastic 需要 Java 8 环境。如果你的机器还没安装 Java，可以参考这篇文章，注意要保证环境变量JAVA_HOME正确设置。

安装完 Java，就可以跟着官方文档安装 Elastic。直接下载压缩包比较简单。


$ wget https://artifacts.elastic.co/downloads/elasticsearch/elasticsearch-5.5.1.zip
$ unzip elasticsearch-5.5.1.zip
$ cd elasticsearch-5.5.1/ 
接着，进入解压后的目录，运行下面的命令，启动 Elastic。


$ ./bin/elasticsearch
如果这时报错"max virtual memory areas vm.maxmapcount [65530] is too low"，要运行下面的命令。


$ sudo sysctl -w vm.max_map_count=262144
如果一切正常，Elastic 就会在默认的9200端口运行。这时，打开另一个命令行窗口，请求该端口，会得到说明信息。


$ curl localhost:9200

{
  "name" : "atntrTf",
  "cluster_name" : "elasticsearch",
  "cluster_uuid" : "tf9250XhQ6ee4h7YI11anA",
  "version" : {
    "number" : "5.5.1",
    "build_hash" : "19c13d0",
    "build_date" : "2017-07-18T20:44:24.823Z",
    "build_snapshot" : false,
    "lucene_version" : "6.6.0"
  },
  "tagline" : "You Know, for Search"
}
上面代码中，请求9200端口，Elastic 返回一个 JSON 对象，包含当前节点、集群、版本等信息。

按下 Ctrl + C，Elastic 就会停止运行。

默认情况下，Elastic 只允许本机访问，如果需要远程访问，可以修改 Elastic 安装目录的config/elasticsearch.yml文件，去掉network.host的注释，将它的值改成0.0.0.0，然后重新启动 Elastic。


network.host: 0.0.0.0
上面代码中，设成0.0.0.0让任何人都可以访问。线上服务不要这样设置，要设成具体的 IP。

二、基本概念
2.1 Node 与 Cluster
Elastic 本质上是一个分布式数据库，允许多台服务器协同工作，每台服务器可以运行多个 Elastic 实例。

单个 Elastic 实例称为一个节点（node）。一组节点构成一个集群（cluster）。

2.2 Index
Elastic 会索引所有字段，经过处理后写入一个反向索引（Inverted Index）。查找数据的时候，直接查找该索引。

所以，Elastic 数据管理的顶层单位就叫做 Index（索引）。它是单个数据库的同义词。每个 Index （即数据库）的名字必须是小写。

下面的命令可以查看当前节点的所有 Index。


$ curl -X GET http://localhost:9200/_cat/indices?v
2.3 Document
Index 里面单条的记录称为 Document（文档）。许多条 Document 构成了一个 Index。

Document 使用 JSON 格式表示，下面是一个例子。


{
  "user": "张三",
  "title": "工程师",
  "desc": "数据库管理"
}
同一个 Index 里面的 Document，不要求有相同的结构（scheme），但是最好保持相同，这样有利于提高搜索效率。

2.4 Type
Document 可以分组，比如weather这个 Index 里面，可以按城市分组（北京和上海），也可以按气候分组（晴天和雨天）。这种分组就叫做 Type，它是虚拟的逻辑分组，用来过滤 Document。

不同的 Type 应该有相似的结构（schema），举例来说，id字段不能在这个组是字符串，在另一个组是数值。这是与关系型数据库的表的一个区别。性质完全不同的数据（比如products和logs）应该存成两个 Index，而不是一个 Index 里面的两个 Type（虽然可以做到）。

下面的命令可以列出每个 Index 所包含的 Type。


$ curl localhost:9200/_mapping?pretty=true
根据规划，Elastic 6.x 版只允许每个 Index 包含一个 Type，7.x 版将会彻底移除 Type。

三、新建和删除 Index
新建 Index，可以直接向 Elastic 服务器发出 PUT 请求。下面的例子是新建一个名叫weather的 Index。


$ curl -X PUT localhost:9200/weather
服务器返回一个 JSON 对象，里面的acknowledged字段表示操作成功。


{
  "acknowledged":true,
  "shards_acknowledged":true
}
然后，我们发出 DELETE 请求，删除这个 Index。


$ curl -X DELETE localhost:9200/weather
四、中文分词设置
首先，安装中文分词插件。这里使用的是 ik，也可以考虑其他插件（比如 smartcn）。


$ ./bin/elasticsearch-plugin install https://github.com/medcl/elasticsearch-analysis-ik/releases/download/v5.5.1/elasticsearch-analysis-ik-5.5.1.zip
上面代码安装的是5.5.1版的插件，与 Elastic 5.5.1 配合使用。

接着，重新启动 Elastic，就会自动加载这个新安装的插件。

然后，新建一个 Index，指定需要分词的字段。这一步根据数据结构而异，下面的命令只针对本文。基本上，凡是需要搜索的中文字段，都要单独设置一下。


$ curl -X PUT localhost:9200/accounts -d 
{
  "mappings": {
    "person": {
      "properties": {
        "user": {
          "type": "text",
          "analyzer": "ik_max_word",
          "search_analyzer": "ik_max_word"
        },
        "title": {
          "type": "text",
          "analyzer": "ik_max_word",
          "search_analyzer": "ik_max_word"
        },
        "desc": {
          "type": "text",
          "analyzer": "ik_max_word",
          "search_analyzer": "ik_max_word"
        }
      }
    }
  }
}
上面代码中，首先新建一个名称为accounts的 Index，里面有一个名称为person的 Type。person有三个字段。

user
title
desc
这三个字段都是中文，而且类型都是文本（text），所以需要指定中文分词器，不能使用默认的英文分词器。

Elastic 的分词器称为 analyzer。我们对每个字段指定分词器。


"user": {
  "type": "text",
  "analyzer": "ik_max_word",
  "search_analyzer": "ik_max_word"
}
上面代码中，analyzer是字段文本的分词器，search_analyzer是搜索词的分词器。ik_max_word分词器是插件ik提供的，可以对文本进行最大数量的分词。

五、数据操作
5.1 新增记录
向指定的 /Index/Type 发送 PUT 请求，就可以在 Index 里面新增一条记录。比如，向/accounts/person发送请求，就可以新增一条人员记录。


$ curl -X PUT localhost:9200/accounts/person/1 -d 
{
  "user": "张三",
  "title": "工程师",
  "desc": "数据库管理"
} 
服务器返回的 JSON 对象，会给出 Index、Type、Id、Version 等信息。


{
  "_index":"accounts",
  "_type":"person",
  "_id":"1",
  "_version":1,
  "result":"created",
  "_shards":{"total":2,"successful":1,"failed":0},
  "created":true
}
如果你仔细看，会发现请求路径是/accounts/person/1，最后的1是该条记录的 Id。它不一定是数字，任意字符串（比如abc）都可以。

新增记录的时候，也可以不指定 Id，这时要改成 POST 请求。


$ curl -X POST localhost:9200/accounts/person -d 
{
  "user": "李四",
  "title": "工程师",
  "desc": "系统管理"
}
上面代码中，向/accounts/person发出一个 POST 请求，添加一个记录。这时，服务器返回的 JSON 对象里面，_id字段就是一个随机字符串。


{
  "_index":"accounts",
  "_type":"person",
  "_id":"AV3qGfrC6jMbsbXb6k1p",
  "_version":1,
  "result":"created",
  "_shards":{"total":2,"successful":1,"failed":0},
  "created":true
}
注意，如果没有先创建 Index（这个例子是accounts），直接执行上面的命令，Elastic 也不会报错，而是直接生成指定的 Index。所以，打字的时候要小心，不要写错 Index 的名称。

5.2 查看记录
向/Index/Type/Id发出 GET 请求，就可以查看这条记录。


$ curl localhost:9200/accounts/person/1?pretty=true
上面代码请求查看/accounts/person/1这条记录，URL 的参数pretty=true表示以易读的格式返回。

返回的数据中，found字段表示查询成功，_source字段返回原始记录。


{
  "_index" : "accounts",
  "_type" : "person",
  "_id" : "1",
  "_version" : 1,
  "found" : true,
  "_source" : {
    "user" : "张三",
    "title" : "工程师",
    "desc" : "数据库管理"
  }
}
如果 Id 不正确，就查不到数据，found字段就是false。


$ curl localhost:9200/weather/beijing/abc?pretty=true

{
  "_index" : "accounts",
  "_type" : "person",
  "_id" : "abc",
  "found" : false
}
5.3 删除记录
删除记录就是发出 DELETE 请求。


$ curl -X DELETE localhost:9200/accounts/person/1
这里先不要删除这条记录，后面还要用到。

5.4 更新记录
更新记录就是使用 PUT 请求，重新发送一次数据。


$ curl -X PUT localhost:9200/accounts/person/1 -d 
{
    "user" : "张三",
    "title" : "工程师",
    "desc" : "数据库管理，软件开发"
} 

{
  "_index":"accounts",
  "_type":"person",
  "_id":"1",
  "_version":2,
  "result":"updated",
  "_shards":{"total":2,"successful":1,"failed":0},
  "created":false
}
上面代码中，我们将原始数据从"数据库管理"改成"数据库管理，软件开发"。 返回结果里面，有几个字段发生了变化。


"_version" : 2,
"result" : "updated",
"created" : false
可以看到，记录的 Id 没变，但是版本（version）从1变成2，操作类型（result）从created变成updated，created字段变成false，因为这次不是新建记录。

六、数据查询
6.1 返回所有记录
使用 GET 方法，直接请求/Index/Type/_search，就会返回所有记录。


$ curl localhost:9200/accounts/person/_search

{
  "took":2,
  "timed_out":false,
  "_shards":{"total":5,"successful":5,"failed":0},
  "hits":{
    "total":2,
    "max_score":1.0,
    "hits":[
      {
        "_index":"accounts",
        "_type":"person",
        "_id":"AV3qGfrC6jMbsbXb6k1p",
        "_score":1.0,
        "_source": {
          "user": "李四",
          "title": "工程师",
          "desc": "系统管理"
        }
      },
      {
        "_index":"accounts",
        "_type":"person",
        "_id":"1",
        "_score":1.0,
        "_source": {
          "user" : "张三",
          "title" : "工程师",
          "desc" : "数据库管理，软件开发"
        }
      }
    ]
  }
}
上面代码中，返回结果的 took字段表示该操作的耗时（单位为毫秒），timed_out字段表示是否超时，hits字段表示命中的记录，里面子字段的含义如下。

total：返回记录数，本例是2条。
max_score：最高的匹配程度，本例是1.0。
hits：返回的记录组成的数组。
返回的记录中，每条记录都有一个_score字段，表示匹配的程序，默认是按照这个字段降序排列。

6.2 全文搜索
Elastic 的查询非常特别，使用自己的查询语法，要求 GET 请求带有数据体。


$ curl localhost:9200/accounts/person/_search  -d 
{
  "query" : { "match" : { "desc" : "软件" }}
}
上面代码使用 Match 查询，指定的匹配条件是desc字段里面包含"软件"这个词。返回结果如下。


{
  "took":3,
  "timed_out":false,
  "_shards":{"total":5,"successful":5,"failed":0},
  "hits":{
    "total":1,
    "max_score":0.28582606,
    "hits":[
      {
        "_index":"accounts",
        "_type":"person",
        "_id":"1",
        "_score":0.28582606,
        "_source": {
          "user" : "张三",
          "title" : "工程师",
          "desc" : "数据库管理，软件开发"
        }
      }
    ]
  }
}
Elastic 默认一次返回10条结果，可以通过size字段改变这个设置。


$ curl localhost:9200/accounts/person/_search  -d 
{
  "query" : { "match" : { "desc" : "管理" }},
  "size": 1
}
上面代码指定，每次只返回一条结果。

还可以通过from字段，指定位移。


$ curl localhost:9200/accounts/person/_search  -d 
{
  "query" : { "match" : { "desc" : "管理" }},
  "from": 1,
  "size": 1
}
上面代码指定，从位置1开始（默认是从位置0开始），只返回一条结果。

6.3 逻辑运算
如果有多个搜索关键字， Elastic 认为它们是or关系。


$ curl localhost:9200/accounts/person/_search  -d 
{
  "query" : { "match" : { "desc" : "软件 系统" }}
}
上面代码搜索的是软件 or 系统。

如果要执行多个关键词的and搜索，必须使用布尔查询。


$ curl localhost:9200/accounts/person/_search  -d 
{
  "query": {
    "bool": {
      "must": [
        { "match": { "desc": "软件" } },
        { "match": { "desc": "系统" } }
      ]
    }
  }
}', '15331138',null,null,'学习'),
(null, 'Matlab自带PCA分析', 'ＰＣＡ原理：


PCA的原理就是将原来的样本数据投影到一个新的空间中，相当于我们在矩阵分析里面学习的将一组矩阵映射到另外的坐标系下。通过一个转换坐标，也可以理解成把一组坐标转换到另外一组坐标系下，但是在新的坐标系下，表示原来的原本不需要那么多的变量，只需要原来样本的最大的一个线性无关组的特征值对应的空间的坐标即可。

比如，原来的样本是30*1000000的维数，就是说我们有30个样本，每个样本有1000000个特征点，这个特征点太多了，我们需要对这些样本的特征点进行降维。那么在降维的时候会计算一个原来样本矩阵的协方差矩阵，这里就是1000000*1000000，当然，这个矩阵太大了，计算的时候有其他的方式进行处理，这里只是讲解基本的原理，然后通过这个1000000*1000000的协方差矩阵计算它的特征值和特征向量，最后获得具有最大特征值的特征向量构成转换矩阵。比如我们的前29个特征值已经能够占到所有特征值的99%以上，那么我们只需要提取前29个特征值对应的特征向量即可。这样就构成了一个1000000*29的转换矩阵，然后用原来的样本乘以这个转换矩阵，就可以得到原来的样本数据在新的特征空间的对应的坐标。30*1000000 * 1000000*29 = 30 *29， 这样原来的训练样本每个样本的特征值的个数就降到了29个。



一般来说，PCA降维后的每个样本的特征的维数，不会超过训练样本的个数，因为超出的特征是没有意义的。



下面是百度百科中对pca降维的一段解释，还是挺清晰的：

“对于一个训练集，100个对象模板，特征是10维，那么它可以建立一个100*10的矩阵，作为样本。求这个样本的协方差矩阵，得到一个10*10的协方差矩阵，然后求出这个协方差矩阵的特征值和特征向量，应该有10个特征值和特征向量，我们根据特征值的大小，取前四个特征值所对应的特征向量，构成一个10*4的矩阵，这个矩阵就是我们要求的特征矩阵，100*10的样本矩阵乘以这个10*4的特征矩阵，就得到了一个100*4的新的降维之后的样本矩阵，每个特征的维数下降了。

　　当给定一个测试的特征集之后，比如1*10维的特征，乘以上面得到的10*4的特征矩阵，便可以得到一个1*4的特征，用这个特征去分类。”


我对 ＰＣＡ的一些了解
我的pca迷惑
迷惑一

 刚开始接触ＰＣＡ的时候，咨询了一个浙大的博士朋友，这朋友告诉我，如果对训练样本进行降维，那么样本的数量必须大于特征的维数，然后我当时就迷惑了，那我怎么办啊，我的人脸表情图像顶多有几百张就算多的了，但是每个图像提取的特征的维数将近有几十万，我不可能找那么多样本去啊。当时有这个迷惑也是因为matlab给出的一个实现在pca降维的函数的说明，就是princomp，这个函数的说明也是用的样本的个数多余特征的维数。后来经过试验是证实，证实了那个浙大的博士的认识是错误的，pca降维肯定不需要样本的个数大于特征的维数，要不然还降维个什么意思。比如我有30*1000000的特征矩阵，那么降维后肯定是每个样本在新的空间中的表示的特征维数不超过30.

迷惑二
   
另外一个迷惑，在最初刚开始做的时候，就是为什么这么大的数据，比如30*1000000直接就降到了30*29，这不是减少的数据有点太多了么，会不会对性能造成影响。之所以有这个迷惑，是因为最初并不了解pca的工作方式。 pca并不是直接对原来的数据进行删减，而是把原来的数据映射到新的一个特征空间中继续表示，所有新的特征空间如果有29维，那么这29维足以能够表示非常非常多的数据，并没有对原来的数据进行删减，只是把原来的数据映射到新的空间中进行表示，所以你的测试样本也要同样的映射到这个空间中进行表示，这样就要求你保存住这个空间坐标转换矩阵，把测试样本同样的转换到相同的坐标空间中。

    有些同学在网上发帖子问对训练样本降维以后，怎么对测试样本降维，是不是还是使用princomp这个函数进行降维，这个是错误的。如果你要保证程序运行正常，就要保证训练样本和测试样本被映射到同一个特征空间，这样才能保证数据的一致性。

迷惑三

网上有不同的pca降维的代码，每个代码也实现的不一样，那么对于同一个数据是否是pca降维以后都是获得相同的数据呢，也就是说不管你用哪种方式进行pca降维，不管你是从哪里下载到的或者自己根据算法实现的pca降维，同样的矩阵降维以后的数据是否一致？这个我个人认为，不同的算法最后导致的pca降维的数据肯定不一致。因为pca降维以后，只是把原来的数据映射到新的特征空间，所以如果你的算法不同，那么选择的协方差矩阵肯定就不同，最后获得的转换矩阵肯定也不一样。那么训练样本和测试样本和不同的转换矩阵相乘以后最终肯定会获得不同的降维坐标。所以使用不同的算法应该最后不会有相同的坐标结果，这个也是我一直实验的结果，我也使用了matlab自带的princomp降维，并且使用相同的数据使用网上下载的一些降维方法进行降维，得到的数据都不一致。

比如说princomp这个matlab自带的函数，在降维之前就将每一个样本减去了一个所有样本的平均值，也可能有很多样本没有减去平均值。princomp这里使用一行表示一个样本，每行包括这个样本的所有的特征值。而网上大部分都是每一列表示一个样本，这样这一列的所有行都表示这个样本的特征值。网上的程序使用列表示样本是有一定好处的，比如我的样本是1000000*30，总共有30个训练样本，每个样本的特征值个数是1000000，那么这个矩阵获得的协方差矩阵是30*30，计算起来非常的方便，不想30*1000000这样的矩阵获得到的协方差矩阵式1000000*1000000，直接就内存溢出了，不过matlab有自己的实现方式，巧妙的解决了这个问题'
,'15331073',null, null, 'matlab');

INSERT INTO article values(null, 'Java并发教程（Oracle官方资料)', '计算机的使用者一直以为他们的计算机可以同时做很多事情。他们认为当其他的应用程序在下载文件，管理打印队列或者缓冲音频的时候他们可以继续在文字处理程序上工作。甚至对于单个应用程序，他们任然期待它能在在同一时间做很多事情。举个例子，一个流媒体播放程序必须能同时完成以下工作：从网络上读取数字音频，解压缩数字音频，管理播放和更新程序显示。甚至文字处理器也应该能在忙于重新格式化文本和刷新显示的情况下同时响应键盘和鼠标事件。这样的软件就被称为并发软件。 

通过Java语言和Java类库对于基础并发的支持，Java平台具有完全（from the ground up ）支持并发编程的能力。从JDK5.0起，Java平台还引入了高级并发APIs。这个课程不仅涵盖了Java平台基础并发内容，还对高级并发APIs有一定的阐述。
目 录 [ - ]
进程和线程
线程对象
同步
活跃度
保护块（Guarded Blocks）
不可变对象
高级并发对象
进程和线程 Top


（本部分原文链接，译文链接，译者：bjsuo，校对：郑旭东） 
在并发编程中，有两个基本的执行单元：进程和线程。在java语言中，并发编程最关心的是线程，然而，进程也是非常重要的。 

即使在只有单一的执行核心的计算机系统中，也有许多活动的进程和线程。因此，在任何给定的时刻，只有一个线程在实际执行。处理器的处理时间是通过操作系统的时间片在进程和线程中共享的。 

现在具有多处理器或有多个执行内核的多处理器的计算机系统越来越普遍，这大大增强了系统并发执行的进程和线程的吞吐量–但在不没有多个处理器或执行内核的简单的系统中，并发任然是可能的。 

进程 

进程具有一个独立的执行环境。通常情况下，进程拥有一个完整的、私有的基本运行资源集合。特别地，每个进程都有自己的内存空间。 

进程往往被看作是程序或应用的代名词，然而，用户看到的一个单独的应用程序实际上可能是一组相互协作的进程集合。为了便于进程之间的通信，大多数操作系统都支持进程间通信（IPC），如pipes 和sockets。IPC不仅支持同一系统上的通信，也支持不同的系统。 

Java虚拟机的大多数实现是单进程的。Java应用可以使用的ProcessBuilder对象创建额外的进程，多进程应用超出了本课的范围。 

线程 

线程有时也被称为轻量级的进程。进程和线程都提供了一个执行环境，但创建一个新的线程比创建一个新的进程需要的资源要少。 

线程是在进程中存在的 — 每个进程最少有一个线程。线程共享进程的资源，包括内存和打开的文件。这样提高了效率，但潜在的问题就是线程间的通信。 

多线程的执行是Java平台的一个基本特征。每个应用都至少有一个线程 – 或几个，如果算上“系统”线程的话，比如内存管理和信号处理等。但是从程序员的角度来看，启动的只有一个线程，叫主线程。这个线程有能力创建额外的线程，我们将在下一节演示。 
线程对象 Top

（本部分原文链接，译文链接，译者：郑旭东） 
在Java中，每个线程都是Thread类的实例。并发应用中一般有两种不同的线程创建策略。 

直接控制线程的创建和管理，每当应用程序需要执行一个异步任务的时候就为其创建一个线程
将线程的管理从应用程序中抽象出来作为执行器，应用程序将任务传递给执行器，有执行器负责执行。
这一节，我们将讨论Thread对象，有关Executors将在高级并发对象一节中讨论。 

定义并启动一个线程 

应用程序在创建一个线程实例时，必须提供需要在线程中运行的代码。有两种方式去做到这一点： 

提供一个Runnable对象。Runnable对象仅包含一个run()方法，在这个方法中定义的代码将在会线程中执行。将Runnable对象传递给Thread类的构造函数即可，如下面这个HelloRunnable的例子：

Java代码 
public class HelloRunnable implements Runnable {  
  
    public void run() {  
        System.out.println("Hello from a thread!");  
    }  
  
    public static void main(String args[]) {  
        (new Thread(new HelloRunnable())).start();  
    }  
  
}  



继承Thread类。Thread类自身已实现了Runnable接口，但它的run()方法中并没有定义任何代码。应用程序可以继承与Thread类，并复写run()方法。如例子HelloThread

Java代码 
public class HelloThread extends Thread {  
  
    public void run() {  
        System.out.println("Hello from a thread!");  
    }  
  
    public static void main(String args[]) {  
        (new HelloThread()).start();  
    }  
  
}  


需要注意的是，上述两个例子都需要调用Thread.start()方法来启动一个新的线程。 哪一种方式是我们应该使用的？相对来说，第一种更加通用，因为Runnable对象可以继承于其他类（Java只支持单继承，当一个类继承与Thread类后，就无法继承与其他类）。第二种方法更易于在简单的应用程序中使用，但它的局限就是：你的任务类必须是Thread的子类。这个课程更加聚焦于第一种将Runnable任务和Thread类分离的方式。不仅仅是因为这种方式更加灵活，更因为它更适合后面将要介绍的高级线程管理API。 Thread类定义了一些对线程管理十分有用的的方法。在这些方法中，有一些静态方法可以给当前线程调用，它们可以提供一些有关线程的信息，或者影响线程的状态。而其他一些方法可以由其他线程进行调用，用于管理线程和Thread对象。我们将在下面的章节中，深入探讨这些内容。 

使用Sleep方法暂停一个线程 

使用Thread.sleep()方法可以暂停当前线程一段时间。这是一种使处理器时间可以被其他线程或者运用程序使用的有效方式。sleep()方法还可以用于调整线程执行节奏（见下面的例子）和等待其他有执行时间需求的线程（这个例子将在下一节演示）。 
在Thread中有两个不同的sleep()方法，一个使用毫秒表示休眠的时间，而另一个是用纳秒。由于操作系统的限制休眠时间并不能保证十分精确。休眠周期可以被interrups所终止，我们将在后面看到这样的例子。不管在任何情况下，我们都不应该假定调用了sleep()方法就可以将一个线程暂停一个十分精确的时间周期。 

SleepMessages程序为我们展示了使用sleep()方法每四秒打印一个信息的例子 

Java代码 
public class SleepMessages {  
    public static void main(String args[])  
        throws InterruptedException {  
        String importantInfo[] = {  
            "Mares eat oats",  
            "Does eat oats",  
            "Little lambs eat ivy",  
            "A kid will eat ivy too"  
        };  
  
        for (int i = 0;  
             i < importantInfo.length;  
             i++) {  
            //Pause for 4 seconds  
            Thread.sleep(4000);  
            //Print a message  
            System.out.println(importantInfo[i]);  
        }  
    }  
}  



main()方法声明了它有可能抛出InterruptedException。当其他线程中断当前线程时，sleep()方法就会抛出该异常。由于这个应用程序并没有定义其他的线程，所以并不用关心如何处理该异常。 

中断（Interrupts） 

中断是给线程的一个指示，告诉它应该停止正在做的事并去做其他事情。一个线程究竟要怎么响应中断请求取决于程序员，不过让其终止是很普遍的做法。这是本文重点强调的用法。 

一个线程通过调用对被中断线程的Thread对象的interrupt()方法，发送中断信号。为了让中断机制正常工作，被中断的线程必须支持它自己的中断（即要自己处理中断） 

中断支持 

线程如何支持自身的中断？这取决于它当前正在做什么。如果线程正在频繁调用会抛InterruptedException异常的方法，在捕获异常之后，它只是从run()方法中返回。例如，假设在SleepMessages的例子中，关键的消息循环在线程的Runnable对象的run方法中，代码可能会被修改成下面这样以支持中断： 

Java代码 
for (int i = 0; i < importantInfo.length; i++) {  
    // Pause for 4 seconds  
    try {  
       Thread.sleep(4000);  
    } catch (InterruptedException e) {  
       // Weve been interrupted: no more messages.  
      return;  
 }  
 // Print a message  
 System.out.println(importantInfo[i]);  
}  



许多会抛InterruptedException异常的方法(如sleep()），被设计成接收到中断后取消它们当前的操作，并在立即返回。 

如果一个线程长时间运行而不调用会抛InterruptedException异常的方法会怎样？ 那它必须周期性地调用Thread.interrupted()方法，该方法在接收到中断请求后返回true。例如： 

Java代码 
for (int i = 0; i < inputs.length; i++) {  
    heavyCrunch(inputs[i]);  
    if (Thread.interrupted()) {  
        // Weve been interrupted: no more crunching.  
        return;  
    }  
}  


在这个简单的例子中，代码只是检测中断，并在收到中断后退出线程。在更复杂的应用中，抛出一个InterruptedException异常可能更有意义。 

Java代码 
if (Thread.interrupted()){  
   throw new InterruptedException();  
}  


这使得中断处理代码能集中在catch语句中。 

中断状态标记 

中断机制通过使用称为中断状态的内部标记来实现。调用Thread.interrupt()设置这个标记。当线程通过调用静态方法Thread.interrupted()检测中断时，中断状态会被清除。非静态的isInterrupted()方法被线程用来检测其他线程的中断状态，不改变中断状态标记。 

按照惯例，任何通过抛出一个InterruptedException异常退出的方法，当抛该异常时会清除中断状态。不过，通过其他的线程调用interrupt()方法，中断状态总是有可能会立即被重新设置。 

Joins 

Join()方法可以让一个线程等待另一个线程执行完成。若t是一个正在执行的Thread对象， 

Java代码 
t.join();  


将会使当前线程暂停执行并等待t执行完成。重载的join()方法可以让开发者自定义等待周期。然而，和sleep()方法一样join()方法依赖于操作系统的时间处理机制，你不能假定join()方法将会精确的等待你所定义的时长。 
如同sleep()方法，join()方法响应中断并在中断时抛出InterruptedException。 

一个简单的线程例子 

下面这个简单的例子将会把这一节的一些概念放到一起演示。SimpleThreads程序有两个线程组成，第一个是主线程，它从创建了一个线程并等待它执行完成。如果MessageLoop线程执行了太长时间，主线程将会将其中断。
MessageLoop现场将会打印一系列的信息。如果中断在它打印完所有信息前发生，它将会打印一个特定的消息并退出。 

Java代码 
public class SimpleThreads {  
  
    // Display a message, preceded by  
    // the name of the current thread  
    static void threadMessage(String message) {  
        String threadName =  
            Thread.currentThread().getName();  
        System.out.format("%s: %s%n",  
                          threadName,  
                          message);  
    }  
  
    private static class MessageLoop  
        implements Runnable {  
        public void run() {  
            String importantInfo[] = {  
                "Mares eat oats",  
                "Does eat oats",  
                "Little lambs eat ivy",  
                "A kid will eat ivy too"  
            };  
            try {  
                for (int i = 0;  
                     i < importantInfo.length;  
                     i++) {  
                    // Pause for 4 seconds  
                    Thread.sleep(4000);  
                    // Print a message  
                    threadMessage(importantInfo[i]);  
                }  
            } catch (InterruptedException e) {  
                threadMessage("I wasnt done!");  
            }  
        }  
    }  
  
    public static void main(String args[])  
        throws InterruptedException {  
  
        // Delay, in milliseconds before  
        // we interrupt MessageLoop  
        // thread (default one hour).  
        long patience = 1000 * 60 * 60;  
  
        // If command line argument  
        // present, gives patience  
        // in seconds.  
        if (args.length > 0) {  
            try {  
                patience = Long.parseLong(args[0]) * 1000;  
            } catch (NumberFormatException e) {  
                System.err.println("Argument must be an integer.");  
                System.exit(1);  
            }  
        }  
  
        threadMessage("Starting MessageLoop thread");  
        long startTime = System.currentTimeMillis();  
        Thread t = new Thread(new MessageLoop());  
        t.start();  
  
        threadMessage("Waiting for MessageLoop thread to finish");  
        // loop until MessageLoop  
        // thread exits  
        while (t.isAlive()) {  
            threadMessage("Still waiting...");  
            // Wait maximum of 1 second  
            // for MessageLoop thread  
            // to finish.  
            t.join(1000);  
            if (((System.currentTimeMillis() - startTime) > patience)  
                  && t.isAlive()) {  
                threadMessage("Tired of waiting!");  
                t.interrupt();  
                // Shouldnt be long now  
                // -- wait indefinitely  
                t.join();  
            }  
        }  
        threadMessage("Finally!");  
    }  
}  


同步 Top


（本部分原文链接，译文链接，译者：蘑菇街-小宝，Greenster，李任  校对：丁一，郑旭东，李任） 
线程间的通信主要是通过共享域和引用相同的对象。这种通信方式非常高效，不过可能会引发两种错误：线程干扰和内存一致性错误。防止这些错误发生的方法是同步。 

不过，同步会引起线程竞争，当两个或多个线程试图同时访问相同的资源，随之就导致Java运行时环境执行其中一个或多个线程比原先慢很多，甚至执行被挂起，这就出现了线程竞争。线程饥饿和活锁都属于线程竞争的范畴。关于线程竞争的更多信息可参考活跃度一节。 

本节内容包括以下这些主题： 

线程干扰讨论了当多个线程访问共享数据时错误是怎么发生的。
内存一致性错误讨论了不一致的共享内存视图导致的错误。
同步方法讨论了 一种能有效防止线程干扰和内存一致性错误的常见做法。
内部锁和同步讨论了更通用的同步方法，以及同步是如何基于内部锁实现的。
原子访问讨论了不能被其他线程干扰的操作的总体思路。', '15331138', null, null, 'java'),
(null, 'spring3和spring4的一些需要注意的地方', '
最近搭建了一个框架，开始用的spring3.1.1 后来升级到spring4.2.0，把遇到的问题记录一下当做备份了



1、java.lang.NoSuchMethodError: org.springframework.aop.scope.ScopedProxyUtils.isScopedTarget(Ljava/lang/String;)Z

这个问题是因为，我的项目里有两个aop的jar包一个是之前的3.1.1一个是4.2.0，把3.1.1的删除掉就好了





2、org.springframework.web.servlet.view.ContentNegotiatingViewResolver的配置

invalid property mediatypes of bean [org.springframework.web.servlet.view.ContentNegotiatingViewResolver] 遇到一个这个错误

spring3 是这样的

[html] view plain copy
<bean  
        class="<span style="color:#ff0000;">org.springframework.web.servlet.view.ContentNegotiatingViewResolver</span>">  
        <property name="order" value="1"></property>  
        <property name="mediaTypes">  
            <map>  
                <!-- 告诉视图解析器，返回的类型为json格式 -->  
                <entry key="json" value="application/json" />  
                <entry key="xml" value="application/xml" />  
                <entry key="htm" value="text/htm" />  
            </map>  
        </property>  
        <property name="defaultViews">  
            <list>  
                <!-- ModelAndView里的数据变成JSON -->  
                <bean  
                    class="org.springframework.web.servlet.view.json.MappingJacksonJsonView" />  
            </list>  
        </property>  
        <property name="ignoreAcceptHeader" value="true"></property>  
    </bean>  


spring4 就得改为下面这个样子，问题就解决了
[html] view plain copy
<bean  
        class="<span style="color:#ff0000;">org.springframework.web.accept.ContentNegotiationManagerFactoryBean</span>">  
        <property name="favorPathExtension" value="true" />  
        <property name="favorParameter" value="true" />  
        <property name="ignoreAcceptHeader" value="true"></property>  
        <property name="defaultContentType" value="text/html" />  
        <property name="mediaTypes">  
            <map>  
                <!-- 告诉视图解析器，返回的类型为json格式 -->  
                <entry key="json" value="application/json" />  
                <entry key="xml" value="application/xml" />  
                <entry key="htm" value="text/htm" />  
                <entry key="file" value="application/octet-stream" />  
                <entry key="image" value="image/*" />  
            </map>  
        </property>         
    </bean>  


3、问题找不到了，直接说一下修改的地方吧就是jackson的问题


spring3的配置如下

[html] view plain copy
<!--避免IE执行AJAX时，返回JSON出现下载文件 -->  
    <bean id="mappingJacksonHttpMessageConverter"  
        class="org.springframework.http.converter.json.<span style="color:#ff0000;">MappingJacksonHttpMessageConverter</span>">  
        <property name="supportedMediaTypes">  
            <list>  
                <value>text/html;charset=UTF-8</value>  
            </list>  
        </property>  
    </bean>  

spring4的配置如下
[html] view plain copy
<!--避免IE执行AJAX时，返回JSON出现下载文件 -->  
    <bean id="mappingJacksonHttpMessageConverter"  
        class="org.springframework.http.converter.json.<span style="color:#ff0000;">MappingJackson2HttpMessageConverter</span>">  
        <property name="supportedMediaTypes">  
            <list>  
                <value>text/html;charset=UTF-8</value>  
            </list>  
        </property>  
    </bean>  

两个的区别就是 文件名字变了，已做标红处理


4、有个asm的jar包在spring4已经不需要单独加载这个jar包了，删掉即可
	', '15331138', null, null, 'java');
#2
