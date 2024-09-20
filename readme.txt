作业查重软件antiplag研发笔记：

2013.9.20
算法选择：
通常情况下，存储空间、性能消耗方面，Shingle > bloom filter > simhash；
相似性计算精度方面，Shingle > bloom filter > simhash。
海量数据集的相似性计算往往采用simhash算法，小规模高精度比较使用shingle.

0 算法： tika 文本抽取 ；IK精确分词+停用词(标点符号、1-9等，停用词未实现)；shinglecloud ;报告
使用IKAnalyzer分词主要考虑到其快速，规模小，支持中英文分词。
shinglecloud：基于文本指纹算法，性能较好，是线性的，必要时可以获取相似文本片段，进而定位到在实际文本中的位置。
tika: 支持多种格式文档的文本抽取，易用。

对gst\singlecloud\moss做过文本对比测试，singlecloud性能远优于gst，准确度相近。

1  将基于shingle算法的相似性比较代码从nlp移植到plag中，
  含TextExtractor、IKAnalyzer、ShingleSim
  并补充了对比较结果按相似性进行排序\命令行参数的功能。
2 导出为可执行jar包，自动抽取相关jar文件，在D:\fh\教学\20141\java\学生作业，runshingle.bat运行,ok

实现了sim程序的java调用、结果分析

2013.12.15
想法：
构建一个代码和文本作业查重的系统：
 代码查重： a moss（远程） ；b sim（本地）
 文本查重： ShingleSim（本地）
 
 2013.12.16
 1 实现moss结果上传、获取结果功能(已实现)
 2 分析moss结果、提取url、下载网页，提取信息、排序输出的功能。(已实现)
 3 
 2013.12.20
 1 实现文件转换功能：解压、将作业文件提取出来，识别是否有名字前缀，若无，添加前缀后，复制到
 指定目录,以适应相似度比较程序的要求。
 2 测试了解压winrar以zip方式压缩的文件，ok；测试了作业类型2，类型3文件的复制
 3 集成进PlagGUI, 修复了2个bug。
 4 执行评分无结果，也可能是门限值高了。
 5 使用sim_java比较378个文件，陷入长时间计算，改为200份，10分 cpu 25%
   界面也锁死，考虑在线程中执行计算
   
 2013.12.28
 1 发现moss方式提示执行失败，经过检查，发现是mossnet在进行文件检查时失败，cmd moss.bat方式下会提示退出，
 但gui exec运行时则会导致程序死锁。经查，与路径参数有关系，原来采用相对路径./testdata/javakaifasim/*.java方式
 一个是相对路径，一个是/形式，笔记本和台式机器均装有cygwin及perl，导致gui方式下window风格的绝对路径无法识别。
 经测试，在当前机器配置下(windowxp+cygwin+perl)，必须采用linux风格相对路径形式
 同时将mossnet中检查文件是否存在、是否是文本、可读的代码注释掉
  需用cmd /c方式执行perl ,如果直接执行perl，一旦perl锁死，则主程序程序退出后，perl也不会退出，且占用cpu33%
 
由于采用相对路径，要求被比较的文件必须放在user.dir(程序当前路径)下的子目录中。  

2 实现并测试了moss方式（在windowxp+cygwin+perl）。
3 测试发现有时无法提取数据，原来是提取结果页面信息的正则表达式，对文件名有约束，需要改进
 （已经改进）
 
 2014.3.3-5
 0 发现使用mossnet perl脚本方式需要perl+cygwin ,安装配置复杂
 1 根据mossnet perl脚本，开发出了java版的moss客户端，这样无需安装perl及cygwin
   通过测试。
 2 moss方式，作业必须复制到testdata下。
 3 更新了用户手册，在google code \sourceforge\baidu云盘上更新了相似度比较系统。
 
 2015.10.19
 sy_jvyvanzhen_21 出现   
 java.lang.UnsupportedOperationException  from tika
 不影响其它文档的比较
 
 
 2019.9.22
 项目源代码发布到github，编写了readme.md。
 将要做的：
 1 美化界面
 2 更新分词组件
         使用： https://github.com/hankcs/HanLP
 3 升级库：tika
 4 提供文档存储功能，实现提交文档，与数据库中的文档比较匹配功能。
 5 做成web版。
 
 
 学习：
 https://github.com/shibing624/similarity
 相似度计算工具包，java编写。用于词语、短语、句子、词法分析、情感分析、语义分析等相关的相似度计算
 
 9.24
 http://www.sohu.com/a/106963046_400678
 k-shingle算法、google提出的simhash算法、Minhash算法、top k最长句子签名算法介绍
 提到汉字转拼音，可以解决字符集编码不一致的问题，可以利用成熟的英文指纹算法，减小分布空间，同时可以解决同音字替代问题;

 发现jplag https://github.com/jplag/jplag 6个月前开源了全部代码，可以支持本地进行
 java,python,c/c++,文本文件的查重。也支持web应用方式。
 

 测试jplag发布版：
 使用eclipse2018版导入项目
 发布版jar包形式，复制到项目的res下
对java代码的测试：
java -jar jplag-2.12.1.jar  -l java17 -r D:/prjs/Plag/testdata/jplagres/ -s D:/prjs/Plag/testdata/jplagsrc/
需要使用jdk11，才能执行成功，jdk7无法执行代码。结果是指定目录下的系列网页文件，可以进行相似代码的比对，这个
类似于moss。
代码文件的输入格式是：
父目录
   学生1
       文件1 。。。
  学生2
      文件1。。。
      
https://segmentfault.com/a/1190000015066788  JPLAG原理及性能的论文
使用经过优化的GST（Greedy String Tiling）算法，性能：n
JPlag的运行时间随着程序集中程序数量的增加而呈二次曲线增长，并且与程序的大小略微超线性。
与moss做了比较
能够检测到的及不能检测到的作弊行为。
Lutz Prechelt , Guido Malpohl , Michael Philippsen. Finding Plagiarisms among a Set of Programs with JPlag[J]

测试文本：
java -jar jplag-2.12.1.jar  -l text -r D:/prjs/Plag/testdata/jplagres/ -s D:/prjs/Plag/testdata/jplagtxt/
java -jar jplag-2.12.1.jar -vl  -l text -r D:/prjs/Plag/testdata/jplagres/ -s D:/prjs/Plag/testdata/jplagtxt/


输入格式：
父目录
   文件1.txt
   文件2.txt 
支持扩展名为txt，TXT,
英文能正确比对出结果，对于汉字，貌似能进行相似比较（时间不能，汉字符被过滤），并在页面进行比对，但页面展示有点问题。
对于文本文档./jplagtxt/，将jplg与shiglecloud做了比较，准确度上大体一致，性能上jplag较慢。

9.25 
使用新的eclipse2018下使用git方式从github.com/fanghon/jplag clone项目在:D:\prjs\jplag
在eclipse中导入代码，maven方式，具体：
1 clone jplag
file,import,git.project from git,clone uri, git协议，填入如：git@github.com:fanghon/jplag.git ，选git协议，选择clone的分支，确定本地git repo
目录路径（不一定实际存在），clone，
2 file，import，maven，existing maven project，选择主项目jplag pom.xml，或者，选择aggregator(会导入整个完整项目)，然后需要长时间等待，系统去下载构建pom.xml需要的jar包。
问题：
  若干项目，出现若干类没有建立的错误，如jplag项目，此时进入该项目，选择pom.xml,
右键，run as，maven generate source, 生成部分源代码，则错误消失。
要想重新安装整个项目，选jplag项目，右键，run as，maven install。

研读源代码：
 基本处理是，输入是包含文件的目录，先通过具体的语法解析器从每个文件中解析出token，然后使用gst算法进行比较，最终结果
 输出是一个结果目录下的一系列html文件。
研究text文本解析器，解析代码通过antrl自动生成，language-parse-lex ，默认最少匹配5个词。
输入文件读取解码未设定，默认使用os的编码设置。文本逐行读取并转成小写，解析每行文本，提取出词（去除了各种标点符号，特殊字符），
加入token结构。 

检索下载了若干论文moss、jplag、查重工具比较代码

https://plagiarism.bloomfieldmedia.com/software/wcopyfind/  一个稳定比较程序 c++，gui界面

2019.9.28
更新了查重软件程序界面，添加了皮肤（substance），修改了一些界面文字，相应更新了readme，将发布版分离出来，放到了
releases部分。

10.12 
将jplag jar包导入项目，发现只有在jdk11，eclipse2018上测试代码才能正确编译、运行。
主要调用了args.add(INPUT_FILE_FOLDER_NAME); 
这就不需要从命令行执行java -jar的方式来调用jplag了。
目前的问题是jdk版本冲突，Plag项目基于jdk1.6，在eclipse3.7(不能配置jdk11),jdk1.7下不支持jplag jar包(jdk11 build),将Plag升级到jdk11，
则其eclipse开发环境貌似不支持windowbuider图形构建工具。

在eclipse j2ee 2018安装windowbuider 的 swing designer,测试一个图形界面代码，ok。
打算项目都移植到高版eclipse下，但项目的jdk 编译水平设置成低版的，如jdk1.6.

2019.10.18
实现了将jplag集成进系统的工作。
由于jplag的jar需要是jdk11，所以将项目迁移到eclipse2018，D:\github\antiplag 下。
增加了jplag、moss对python的支持，添加了python测试数据集。
增加了点击“查看结果”按钮，自动打开默认浏览器，显示结果网页的功能。

文件编码应该是utf-8编码，否则比对时会出现乱码。


下一步：
增强jplag的文本比较功能，使其能支持汉字。
jplag目前提供扩展名为txt的char（基于asc127字符的）和text（基于英文单词的）相似度比较功能。text逐行比较，默认连续5个词相同，才算一条相似信息。
这样可以利用jplag的文本可视化比对功能。

jplag添加了text文档比较方式，增加了docen测试数据，并进行了测试，对于纯英文文档，比对正确；中英文混排的代码文档，也能比对其中的英文。

上述修改已push到github。

可以考虑将中文文档，转成拼音，然后比对。

发现jplagresult目录下，多次查重后，会生成较多文件，考虑定期清除这些文件。

*考虑将界面的“关于”替换成帮助功能。

2019.10.19
在github上发布了antiplag2.8.0版。

2019.10.21
使用jplag的text方式测试了sql作业，sql文件是中文名称，内部有少量汉字，共30个文件，实际比对27（其中有3个文件的扩展名不是txt），汉字文件名可以正常处理，可以进行可视化比对，文件内汉字为乱码。

2019.10.23
1 进一步的开发，支持对html，jsp网页代码进行查重的功能。
jsp文件是复合代码：html（js，css，html）+java，
思路：
1将jsp编译成java文件，然后比对java，需要测试对println方法输出的文本是否比对？
2 html页签，css，js，java分开计算

10.30
如何实现网页的相似度比较：
基本思路，将html中的html页签、css、文本、js分类，分开计算相似度，然后合并计算总相似度。
https://github.com/matiskay/html-similarity  python
比较网页页签结构及css的相似度。页签结构：将网页解析成dom树，深度遍历树，生成页签序列，然后使用difflib的字符串匹配比较方法（与LCS算法类似），计算出相似度。
css：xpath取出css字符串，放入set，计算两个集合a&b / a+b-a&b jaccard相似度。
合并：
k * structural_similarity(document_1, document_2) + (1 - k) * style_similarity(document_1, document_2)   k推荐取0.3
其网页结构相似的计算参考了https://github.com/TeamHG-Memex/page-compare
该项目还提供的测试数据。
*TeamHG-Memex团队在github开源了爬虫及网页自动处理相关的系列项目。
https://github.com/SPuerBRead/HTMLSimilarity  python 
网页相似度判断：根据网页结构判断页面相似性；国内专利实现项目

网页文本相似度：
解析html网页，抽取文本；分词（标点符合、分词）；计算simhash；比较文档相似度；指纹存储及检索。

实现语言，通过github检索，发现相关应用还是python较多，java其次。但原来作业查重项目是使用java实现的，所以考虑先使用java实现。

试用：
1 https://github.com/sing1ee/simhash-java
将项目下载，建立新项目simhash,
分析代码：输入是utf-8编码文件（内有多行相似文本），输出是结果文件（逐行标识出相似文本）

2019.11.4
1 https://github.com/agranya99/MOSS-winnowing-seqMatcher
moss implementation in python - checks software codes for plagiarism
还提供了代码的预处理，使用了python的difflib，比较代码及展示共同点
其中提到的Pygments ，可以对上百种语言代码进行词法解析（支持html,jsp）
下一步，测试Pygments 对js、html、jsp代码的词法解析能力。
可以在java中调用Pygments 库代码http://pygments.org/docs/java/
2 jplag使用antrl根据定义的语法文件.g ,生成词法、语法解析代码，供上层调用，处理目标文件（如java、c/c++、text)。
api调用次序，各个具体的语言实现Language定义的接口，其中的parse方法会调用具体语言实现的Parse类中的parse方法，调parseFile方法，生成TextParser，调file方法，
解析的结果放在TokenStructure中，

2019.11.27
根据D:\fh\ml\nlp\textsimilarity readme.txt确定的技术路线，准备基于JPlag实现：
 1 html文件查重，基于antrl
 2 jsp文件查重
 3 中英文文档查重

2019.11.28
antrl学习：
Jplag的词法解析接口类：
分析java-1.7前端词法解析器：
在\src\main\antlr4\jplag\java17\grammar 下有java7.g4文件，生成的源代码在：
target\generated-sources\antlr4 下，有
java7.tokens,java7lexer.tokens,  java7lexer.java,java7parse.java,java7listener.java 等文件。
使用了antlr4.2生成，该项目加入了相应的运行时antlr版本
在项目的test代码目录下，有测试代码，可以观察JPlag对antlr生成代码的使用。
项目还提供了测试用的多个java代码文件。


JavaToken：表示java词token（所在文件、行、列、长度，词类型及转换方法，hash码） 
Parser： parse方法，读取目标文件，解析文件，返回Structure类对象结果struct；采用遍历AST树，加入自定监听器的方法JplagJava7Listener，解析代码文件，生成相应token的类型。
Language 对应某类语言代码文件，对外接口方法是：parse(File dir, String[] files)，返回jplag.Structure类型的对象。最终会调用Parser类的parse方法进行解析。
可定制的参数：
  定义文件扩展名，
  * min_token_match 最小匹配的字符长度。java默认是9 ，即连续9个token作为最小匹配单元。
jplag.Structure结构：词Token的集合

Submission 代表一个学生提交的作业（一个目录，可含多个文件），包含Language 及Structure；对外方法是 boolean parse()，调用具体语言的parse(File dir, String[] files)方法，

两个Structure比较前，Structure中的tokens生成hash（基于token的整数类型的type值）。

在语法解析过程中，空格、换行；类名、方法名、变量名；访问控制符、方法中参数等；+-等操作符构成的表达式，一些关键字等都忽略掉了。
输入代码：
public class J7Generics {
	public static void main(String[] args) {
		GenericPocket<String> pocketNew = new GenericPocket<>();
	}
}
解析Parse后token输出序列，根据token的type整数值，转成的字符串输出：
expected = ""// @formatter:off
				+ "CLASS{  \n"  //public class J7Generics {
				+ "VOID    \n"
				+ "METHOD{ \n"  \\public static void main(String[] args) {
				+ "GENERIC \n"    \\ GenericPocket<String>
				+ "VARDEF  \n"  \\ pocketNew
				+ "ASSIGN  \n"  \\ =
				+ "GENERIC \n"
				+ "NEWCLASS\n" \\  new GenericPocket<>()
				+ "}METHOD \n"
				+ "}CLASS  \n"
				+ "********\n";   //文件结束
转换后的token字符串，比源代码抽象了不少。

具体比较算法：参考“JPlag: Finding plagiarisms among a set of programs”
实际比较的是token type值组成的数字字符串；使用了Rabin-Karp算法进行性能优化，大部分情况是O(n-m+1)，最差O((n-m+1)m。

研究HTMLexer.g4，发现对应js及java后台脚本，只是标识出来，并未对脚本进一步分析的代码；所以需要额外较多编码，处理js、css、java。

分析java-1.9前端词法解析器：
使用了javac编译器编译代码，获取ast树，然后遍历树，进行相应处理，形成JavaToken。

分析text前端词法分析器：
使用text.g antrl2.7.7生成
TextToken.java 
执行Parser.java中的main方法，运行参数是 pom.xml ,观察解析的输出，
类型都是<abstract> ，但有text token的行、列、长度信息。
每个不同文本单词（全部转成小写）的编码：递增的整数，放在一个hash表中（键是词，值是整数编号）
每个词token的type就是这个整数编码值。
标点符号等在解析过程中被忽略。


如何支持汉字文档？
觉得不需要使用文本解析器，直接使用分词软件，能分词并返回词所在行列长度信息就可以。

分析前端cpp词法分析器：
使用javacc进行词法分析

Jplag的GST算法研究：
支持


* 鉴于antrl对于html。jsp复合文档的支持不是很好（即不能同时解析出js、css、html、java），考虑使用jflex实现html，jsp的词法解析。

实现的方法：
在Plag项目中，基于jplag jar包，实现jplag.doc ,jplag.html 的Language，Parser，Token
实现对中文的支持：
1 分词软件选择？
 需要能支持停用词，过滤掉标点符号的分词软件。选择hanlp
2019.11.30
2 在Plag项目中使用Hanlp的分词器
   Tokenizer.segment(String text,String sep) 先规范化输入字符串，然后去除停用词分隔符。
 测试：
   发现中英文文本相似度及性能方面与原分词器差不多。
2019.12.1
 增加了对pdf文档的支持（已测试）
 修复了tika 不能正确获取gbk编码的txt文件内容的错误
 修改了“文件提取”功能代码，支持pdf、html文件的提取。
 在testdata\doccn下添加了几个不同格式、不同编码的文件，供测试。
 在github上更新了项目，发布的v2.8.6版。

实现基于jplag的对汉字文档的查重：
 根据jplag.text项目代码，将其移植到Plag项目的jplag.doc包下，
  a 重命名TextToken为DocToken，并重新生成serialVersionUID
  b 使用tika读取文档，使用hanlp生成token，关键是保持分词token在原始文件中的位置信息（行号，列）
2019.12.2
   jplag.txt 中TextToken中的行列初始值是1，记录了在原始文件中的位置。空行被过滤，但行号依然计算，（标点符号被过滤）
  重新实现了DocToken、Language、Parse以及jplag.options下的CommandLineOptionsExt，
  CommandLineOptionsExt的作用是便于加入新的语言支持。

  加入一个dongxiao-2.html文本文档测试，发现并修复，文档方式下，由于扩展名过滤，不能读取html文件的问题。

  使用刚实现的jplag版的doc类型，测试文档相似度（doccn）：
  a 发现可以计算出相似度，但文件文本比对时，出现乱码，无法比对。估计是原比对部分代码是使用java普通读取文件方式（使用utf-8解码）直接读的doc文件，可视化比对部分，正确读取文件的问题如何解决？

  b 性能上感觉shinglecloud 比jplag快5倍，jplag花费大量时间在写结果文件上。

  在report.java类的writeNormalSubmission方法是写两个文件比较内容的，读取原始文件的代码在Submission.java的readFiles方法，使用utf-8逐行读取原始文件，


解决方法:
1 Jplag不动， Plag先对目标目录下的doc类型文件，用tika读取文件文本以utf-8编码同名 txt文件形式写入当前目录，然后再调用jplag，结束后，将这些txt文档删除。
  实现了方案1，测试通过。jplag也能支持各种格式文件文本的相似度比较及可视化比对了。

2 修改JPlag的Submission.java的readFiles方法，使用tika读取文档。

2019.12.3
  测试plag项目，更新帮助，生成发布版，将代码及发布版更新到github。
 
下一步 基于jplag，使用jflex，将html、jsp的代码相似度检测集成进系统
学习https://github.com/bobbylight/RSyntaxTextArea/tree/master/RSyntaxTextArea/src/main/java/org/fife/ui/rsyntaxtextarea/modes
下的html，jsp flex及java代码文件，代码中声明使用jflex1.4.1生成，下载安装了jflex1.7版本。


html，jsp 中是否支持css、js、html、java的复合解析？支持
解析的token类型，token的起止位置等信息是否够用？ 
出于语法高亮目的，能解析空格、注释、关键字、操作符、字符串、数字。
org.fife.ui.rsyntaxtextarea下的TokenTypes 定义了在词法解析基础上抽取的供语法高亮使用的
token 类型 ；TokenMakerBase定义了对token操作的接口方法。


2019.12.9
由于rsyntaxtextarea的jflex解析，主要是考虑到代码高亮，词法解析的要求不能完全符合查重软件需要的替换变量名、函数名、字符串常量等的要求。所以决定还是使用antrl。
研究antrl对html代码的词法、语法解析，然后分离出html页签类、js、java代码、css内容，
针对js，再研究antrl对js的解析。最后形成一个统一的struct

html相似度检测的基本技术路径：
1 学生抄袭分析
   共性的是改变代码格式（空格、换行等）、增减注释。
   对策：过滤上述信息。
   a html页签结构，主要修改属性值、增减属性 。
      html中的文本，由于作业的要求，大家会比较一致。
      对策：过滤文本，属性、值使用统一的'A'，“V”替换。
   b javascript：重命名变量名、方法名；改变字符串内容等；改变代码位置。

   c css：修改css名称，部分值

分析工具：可以用python的difflib工具，生成相似文档的可视化差异比对的html结果。

2 确定从原始html文本中需要排除（或提取）的信息  
  常见的通过修改文本反抄袭检查，相应信息应该排除（如空格、换行、注释等）。
  抽取的信息越抽象，召回率越高，相应的精确度会下降，需要一个平衡。
  token的信息：词本身内容、位置、个数；抽象的层次：抽象到所属的类，忽略位置信息，忽略个数信息，忽略词本身。

  a 排除各种空格、换行、注释
  b html 按序抽取页签，属性名保留，全部属性值用统一的字符替换，html文本过滤掉。


2019.12.13
研究jplag的jplag.webService项目，该项目主要以web服务的形式提供查重服务（wsdl）
主要处理流程：处理服务请求，接受上传的压缩文件，生成一个AccessStructure，放在请求队列里；
JPlagCentral是主处理类，从请求处理队列，取出请求结构，构建好调用jplag的命令行参数，解压请求上传的文件，调用program.run()，将结果目录压缩，发到结果队列（服务返回的结果是查重结果的压缩文件，这点与moss不同，moss是返回一个结果url，通过url访问服务器上的结果，结果一般保存一周），保存结果信息到xml文件，删除生成的查重结果目录及解压的查重原zip文件。

上传的数据放在jplaghome\entris下，压缩文件名是 submissionid+username.zip , zip或被解压缩。
查重结果子目录放在jplaghome\results下，子目录名为:submissionid+username.

客户的请求信息是：各项查重参数+作业zip文件；获取的结果是：一个比较结果的压缩文件

jplag.wsClient项目：
提供了与web服务联系的命令行和图形界面客户端。

2019.12.21
jplag.wsClient
  修改pom.xml  将email jar包版本改为1.4.6，原1.3已无法下载
  build pom.xml :右键->run as->build- 输入package ，
     应该自动编译 生成https://jplag.ipd.kit.edu/JPlagService/service?WSDL
    提供的客户端存取源代码，
 
  默认生成的代码访问：https://jplag.ipd.kit.edu:443/JPlagService/service
 JplagSwingClient 可以运行，生成图形界面，据此可以分析用户接口信息；但由于没有账号，无法访问jplag.ipd.kit.edu上的服务。

atujplag 项目：
   是以webstart jnlp方式，在浏览器端运行的java客户端。由于年底较久（2005）pom.xml中有两个包在maven中已不存在，导致build失败。
分析了jplag原始项目github上的request
a 有提交代码与以前保存的代码进行比较的修改
b 有结果以xml格式返回的代码


2019.12.24
1 jimagehashtest项目下，测试学生提交的linux操作图片（ks1-ks5），phash256 发现：
 a  同一背景下，同一当前窗口位置，窗口（与屏幕背景占比小于1/2）命令不同导致的变化，均被检测为相似（<0.1）
 b  不同窗口背景下（不同桌面、同一桌面窗口位置不同），被检测为不相似(如ks1/ks2;ks3/y3) 相似限值0.3。
 c 两张相似图片  phash32--1024位，相似值变化，一般是高低波动的，但有个波动范围，这个范围对于
同一图片，缩放、降清晰度、明暗、加文字、点噪声等影响较小（一般<0.1）; 但对于不同内容两张图片，则波动范围较大，需要实验不同位数，确定最低和最高相似值及范围。
 d 同一背景，同一窗口位置，但窗口文本完全不同(t1/t2)，phash128，值0.3125，32位，值0.08。
 e  无背景，两个完全不同文字的窗口（t3/t4）,值0.48，但64位phash 0.18；32位，值0.388,256位、0.48
所以需要假设学生提交图片的背景、目标窗口的位置不同（要求学生截取全屏），才能有效区分出不同学生的图片是否相似。

学生对图片的攻击：
a 整体缩放、变颜色、明暗、降低分辨率、加水印字、加噪声点处理，算法可以抵抗。
b 做到背景不同，但目标窗口内容基本相同。（通过复制粘贴窗口实现）
     为了抵抗这种攻击，需要提取出目标窗口，然后再比较提取出的窗口。
    进一步的需要识别出图片中的文字信息，然后比较文字的相似度。

2019.12.25
0 前期的图片项目检索、比较及相关算法研究，根据JImageHash项目，确定技术路径。
1 实现图片查重功能 
  在分析学生常见图片攻击行为，作业图片数较少（一般小于1000张）的基础上，使用phash算法，实现提交图片间的相似度检查，结果是按相似度排好序的图片。

2 参考移植JImageHash项目的代码，已实现。

3 测试了11张图片，耗时0.7s，估计主要时间在读取图片上。
   测试500张图片？

4 将jimagehashtest项目中生成的jimagehash3.0jar包进行缩减，主要是在build path中将界面及h2
库、测试jsop相关的jar ,重新生成的jar只有4mb。

*5 需要根据学生图片特征，针对性测试，调整phash位数参数，使得差异最大化 ，提高相似度检查的精确度。
  特别是窗口中不同文字情况t3/t4，t1/t2,将phash设置为128位（有更低的相似值），默认相似度门限提高到80%。

6 整理测试图片数据：
 a pnp、jpg、gif、bmp格式图片 ballon
 b 同一张图的缩放、明暗、加文字 、高分辨率
 c 同一张图旋转、椒盐噪声 lenna
 d 有同一背景的窗口ks1-3 
 e 无背景全文字窗口t3-4

7 更新github项目，发布v3.0.0版

2019.12.26
继续实现html符合文档的相似度检测：
1 html本身的语法解析
   通过antrl已基本实现
2 语法、词法解析的基本步骤：
  前提条件： 根据g4文件，生成xxLexer.java(词法分析)、xxParser.java(语法分析）、xxListener.java(解析代码时的回调接口）、xxBaseListener.java是对接口的一个空实现。
  a 根据输入文件或文本，生成xxLexer对象（词法分析器）：
     如 ：Lexer lexer = new Java9Lexer(new ANTLRFileStream(f));  lexer就可以进行词法token分析
     输入是字符串文本的情况： 
          Lexer lexer = new Java9Lexer( (CharStream)(new ANTLRInputStream(str)));
 b 生成语法分析器：
    CommonTokenStream tokens = new CommonTokenStream(lexer);  //生成词流
    Java9Parser parser = new Java9Parser(tokens);  // 生成语法解析器
    CompilationUnitContext cuc = parser.compilationUnit();  //语法编译
    语法编译前，可以加入各种Listener
     如：parser.addParseListener(new  Java9BaseListener(this));
     parser.addErrorListener(new DiagnosticErrorListener())
  c 树遍历方式解析（自上而下，自左而右遍历），通过回调接口，处理目标语法单元：
    ParseTreeWalker ptw = new ParseTreeWalker(); //生成语法监听器对象
    for (int i = 0; i < cuc.getChildCount(); i++) {
      ParseTree pt = cuc.getChild(i);
      ptw.walk(new  Java9BaseListener(this), pt);    
    }

 d 语法监听器实现（编程的主要工作）
     在该类中，语法中的每条规则都有对应的enter方法和exit方法。
     解析信息的获取通过方法传入的ctx获取，语法块的语法类型由方法名确定，语法块的起止token由
getStart、getStop获取，在token中，可以获取文本内容，行号，行中位置。整个文本块内容文本可以通过ctx.getText()获取。
     在jplag中，在监听器中，实现对代码相应目标的过滤，提取。对于空格、注释、换行等，在解析过程中就被过滤掉了；所以主要是“提取”，即找到相应语法块接口方法，然后编写处理代码。
    jplag提供的接口是在Parser类中，调用add方法，加入提取目标的token（类型，所在文件，所在行列长度）。
    主要需要自定义类型type，分析jplag的java7解析器，在JavaTokenConstants中定义了需要提取的语法类型，值就是一个整数，最后整个代码，就是被抽取出一个整数序列，GST算法就是对这个序列进行比较。
    jplag java7解析后，将变量名、函数名、参数等都过滤了。
     
2019.12.27
复合文件如html的处理的想法：
1 实现html、js、css的词法语法解析器
2 在html解析中，提取出js、css、java等代码文本
3 实现html解析，分离出js、css、java部分
对js、css、java部分的代码调用相应语言的解析器进行解析，提取的语法点的类型值，采用加base值的方法。
如：js+100，css+200。。。
4 目前需要实现js和css解析器
5 相似度计算：
   a 多个部分解析后，全部放入struct结构，进行统一计算
   b html,css,js,java分别计算相似度，然后按比例相加。


考虑实现一个比较二进制文件（如zip，exe，class）相似度的一般方法，就是逐个字符比较了。
分析jplag chars 的实现（主要还是针对文本文件）。FileReader读字符，提取小于127的且符合提取要求的（数字、大小写字母）asc码字符，转成相应整数值，然后GST算法比较。

2022.3.5
需要完善的地方：
1 更新jplag版本
2 提供英文说明，使得程序支持英文界面
3 支持单excel格式文件，读入文本行，比较文本行之间的文本相似性
4 对包含文字的图片，使用ocr技术，提取文字，然后再进行文本的相似度比较
5 对复合文档，如html、jsp等提供相似度比较功能。
6 提供获取相似文本的index（hash结果）功能，以便于保存到数据库
7 提供获取相似文本的pos位置功能，以便于比对相似文本块

2024.9.13
1 2022年开始，moss限制一个用户账号一天只能提交100个文档，防止机器人暴力提交大量文档查重。Nov 13 ,2022.
https://theory.stanford.edu/~aiken/moss/    
原有账号开源的，访问moss现在经常失败，所以需要申请一个新账号自己用。
现在如何申请moss新账号？


参考：https://developer.aliyun.com/article/1593104  Linux Centos通过mail向QQ邮箱发邮件


虚拟机centos ，su root 123456 ,gedit  /var/mail.rc
mail.rc文件尾部添加保存：
# mail param
set from=1010402827@qq.com
set smtp=smtp.qq.com
set smtp-auth-user=1010402827@qq.com
set smtp-auth-password=aznaehnmmkkbbdib
set smtp-auth=login

测试配置，命令行执行：
echo "test content" | mail -s test 1010402827@qq.com  
成功

执行发给moss的邮件：
echo -e "registeruser\nmail 1010402827@qq.com" | mail -s mo moss@moss.stanford.edu

终于在qq邮箱收到邮件，其中账号信息：userid=493062957

2 antiplag的web应用链接在github上已失效，但在gitee上发现一个，可以修复项目首页失效的链接。
  https://gitee.com/dalefe/antiplag-web-backstage

3 antiplag集成检查llm大语言模型生成文本的功能。
 https://github.com/Hello-SimpleAI/chatgpt-comparison-detection  python代码







 







