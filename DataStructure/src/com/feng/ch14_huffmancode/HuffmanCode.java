package com.feng.ch14_huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";

        //######################################################  哈夫曼数据压缩 测试  ##########################################

        /*
         * 一、字符串 转成 字节数组
         * 字节数组 储存的是 ASCII 表对应的 数字
         * */
        byte[] contentBytes = content.getBytes();
        System.out.println("字符串转成的字节数组：" + Arrays.toString(contentBytes));// [105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
        System.out.println("字符串转成的字节数组大小：" + contentBytes.length); // 40

        /*
         * 将下面的 4 步，封装成一个方法 huffmanZip()
         *
         * */
        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果大小是：" + huffmanCodesBytes.length); // 40
        System.out.println("压缩后的结果是：" + Arrays.toString(huffmanCodesBytes)); //  [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]

        /*
         * 二、字节数组 转成 list集合
         * 1、对字节数组进行遍历，统计每一个 byte 出现的次数，封装在 Map 集合
         * 2、然后对map 集合进行遍历，对每个 key-value 生成一个 Node 结点，以 Node 结点形式 封装在 List 集合 中
         * */
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes=" + nodes); // nodes=[Node{data=32, weight=9}, Node{data=97, weight=5}, Node{data=100, weight=1}, Node{data=101, weight=4}, Node{data=117, weight=1}, Node{data=118, weight=2}, Node{data=105, weight=5}, Node{data=121, weight=1}, Node{data=106, weight=2}, Node{data=107, weight=4}, Node{data=108, weight=4}, Node{data=111, weight=2}]

        /*
         * 三、list集合 转成 哈弗曼树，
         * 1、使用 Collections 集合 对 List 集合进行从小到大排序，
         * 2、取出根节点权值最小的两颗二叉树
         * 3、组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
         * 4、再将这颗新的二叉树，以根节点的权值大小 再次排序， 不断重复  1-2-3-4 的步骤，直到数列中，所有的数据都被处理，就得到一颗赫夫曼树
         * 最后，循环完后，list集合中，只有一个数据，也就是哈弗曼树的根节点。
         *
         * 前序遍历中， data 为空的为 父结点， 不为空的为叶子结点。
         * */
//        System.out.println("哈弗曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
//        System.out.println("测试一把 ，创建的二叉树，前序遍历哈弗曼树 前序遍历：");
//        preOrder(huffmanTreeRoot);
//        System.out.println();

        /*
         * 四、哈夫曼树 转成 哈夫曼编码
         * 哈夫曼编码使用Map<Byte, String> 来储存
         * 哈夫曼编码：前面的每个元素对应的 ASCII 和 次数 构成一个 叶子结点，形成的哈弗曼树，左边为0，右边为1。元素=路径，叶子节点的路径称为 哈夫曼编码
         * */
//        Map<Byte, String> huffmanCodes = getCodes(root);// 对 getCodes(root, "", stringBuilder); 进行了重载
//        System.out.println("生成的 哈夫曼编码表 huffmanCodes：" + huffmanCodes); //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
//        System.out.println("~生成的 哈夫曼编码表 codes：" + codes); //{32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
//        System.out.println();

        /*
         * 五、对哈夫曼编码进行压缩，得到压缩后的 哈夫曼编码字节数组
         * 传入的 字符串 转成 的字节数组 和 哈夫曼编码表
         * 测试
         * */
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes)); // 17个   huffmanCodeBytes=[-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]

        //######################################################  哈夫曼数据解压  ##########################################
        System.out.println();
        System.out.println();
        System.out.println("哈夫曼数据解压:");
//        byteToBitString((byte) 1);

        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);
        System.out.println("原来的字符串大小=" + new String(sourceBytes).length()); //  i like like like java do you like a java
        System.out.println("原来的字符串=" + new String(sourceBytes)); //  i like like like java do you like a java


        //######################################################  哈夫曼编码应用实例：压缩和解压文件  ##########################################
        System.out.println();
        System.out.println();
        System.out.println("哈夫曼编码应用实例：压缩和解压文件");

//        String srcFile = "D:\\HuffmanCode.java";
//        String dstFile = "D:\\HuffmanCode.zip";
//
//        zipFile(srcFile, dstFile);
//        System.out.println("压缩文件成功~~");

        String zipFile = "D:\\HuffmanCode.zip";
        String dstFile2 = "D:\\HuffmanCode2.java";

        unZipFile(zipFile, dstFile2);
        System.out.println("解压文件成功~~~");

    }

    //######################################################  哈夫曼数据压缩  ##########################################

    /**
     * 使用一个方法，将前面的方法封装起来，便于我们的调用
     *
     * @param contentBytes 原始字符串对应的字节数组
     * @return 是经过哈夫曼编码处理后的字节数组（压缩后的数组）
     */
    private static byte[] huffmanZip(byte[] contentBytes) {
        //二、字节数组 转成 list集合
        List<Node> nodes = getNodes(contentBytes);
        //三、list集合 转成 哈弗曼树，
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //四、哈夫曼树 转成 哈夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //五、对哈夫曼编码进行压缩，得到压缩后的 哈夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);

        return huffmanCodeBytes;
    }

    /*
     * @param bytes 接收一个 字节数组
     * @return 返回的就是一个 List 集合，如形式 [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     * */
    private static List<Node> getNodes(byte[] bytes) {

        // 1、先创建一个 ArrayList
        ArrayList<Node> nodes = new ArrayList<>();

        // 遍历 bytes 字节数组,统计每一个 byte 出现的次数 -》 map[key, value]
        Map<Byte, Integer> map = new HashMap(); // Byte 为数据，Integer 为次数
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) { // Map 还没有这个字符数据，第一次
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }

        // 把每一个键值对 转成 一个 Node 对象，并加入到 nodes 集合中
        // 遍历map
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /*
     *  通过一个 list 创建对应的哈弗曼树
     * */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序 从小到大
            Collections.sort(nodes);

            // 取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            // 取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            // 创建一颗新的二叉树，他的根节点 没有data，只有权值
            Node parent = new Node(null, leftNode.getWeight() + rightNode.getWeight());

            parent.setLeft(leftNode);
            parent.setRight(rightNode);

            // 将已经处理的两颗二叉树从nodes 删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);

            // 将新的二叉树 添加到 nodes
            nodes.add(parent);
        }
        // 循环后，此集合 也就只有一个节点了，返回的为 nodes 第一个节点，此结点为根节点
        return nodes.get(0);
    }

    // 前序遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("哈弗曼树为空~~");
        }
    }

    /*
     * huffmanCodes ： 存放所有叶子节点的哈弗曼编码
     * stringBuilder ：拼接路径
     * */
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    // 重载 getCodes
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 处理root 的左子树
        getCodes(root.getLeft(), "0", stringBuilder);
        // 处理 root 的右子树
        getCodes(root.getRight(), "1", stringBuilder);
        return huffmanCodes;
    }

    /*
     * 功能：将传入的 node结点 的所有叶子结点的哈夫曼编码，并放入到  huffmanCodes 集合
     *
     * 生成 哈弗曼树 对应 的哈夫曼编码
     * 思路：
     * 1、将哈夫曼编码表 存放在 Map<Byte,String> 形式为：32=>01 97=>100 100=>11000 等等
     * 2、在生成 哈夫曼编码表 时，需要去拼接这个路径，所以定义一个StringBuilder 存储 某个叶子结点的路径
     *
     * @param node 传入结点
     * @param code 路径：左子结点是 0， 右子结点是 1
     * @param stringBuilder 是用于拼接路径
     * */
    public static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);

        // 将 code 加入到 stringBuilder2
        stringBuilder2.append(code);

        if (node != null) {
            // 判断当前 node，是叶子结点 还是非叶子结点
            if (node.getData() == null) { // 非叶子结点
                // 递归处理
                // 向左递归
                getCodes(node.getLeft(), "0", stringBuilder2);
                // 向右递归
                getCodes(node.getRight(), "1", stringBuilder2);
            } else { // 叶子结点
                huffmanCodes.put(node.getData(), stringBuilder2.toString());
            }
        }
    }

    /*
     * 编写一个方法，将字符串对应的byte[] 数组，通过生成的 哈夫曼编码表，返回一个哈夫曼编写 压缩后 的 byte[]
     *
     * @param bytes 这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的哈夫曼编码 map
     * @return 返回的哈夫曼编码处理后的 byte[]
     * 举例 String content = "i like like like java do you like a java"; => byte[] contentBytes = content.getBytes()
     * 返回的字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
     * => 对应的 byte[] huffmanCodeBytes , 即 8 位对应一个 byte，放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000 => 10101000-1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[0] = -88
     * */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 1、利用 HuffmanCodes 将 bytes 转成 哈夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历 bytes 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));  // 以 bytes 数组 的每个元素为 key ，取出 value值，value 值为其 路径，也就是哈夫曼编码map集合中的 value值
        }
        System.out.println("测试 stringBuilder=" + stringBuilder.toString()); //测试 stringBuilder= 11011011000110111111111101011000110111111111101011000110111111111101011001011001110111100101111000100111011110101001111100110110001101111111111010111001011001011001110111100

        // 将 “110110110001101111111111010110。。。” 转成 byte[]
        // 统计返回 byte[] huffmanCodeBytes 长度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        // 创建存储压缩后的 byte 数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0; // 记录第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) { // 不够 8位
                strByte = stringBuilder.substring(i);  // 从i位 到最后
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将 strByte 装成一个byte ，放入到 huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //######################################################  哈夫曼数据解压  ##########################################

    //
    //
    // 1、将huffmanCodeBytes[]
    //
    /*
     * 完成数据的解压
     * 思路
     * 1. 将huffmanCodeBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     *    重写先转成 赫夫曼编码对应的二进制的字符串 "1010100010111..."
     * 2. 赫夫曼编码对应的二进制的字符串 "1010100010111..." =》 对照 赫夫曼编码  =》 "i like like like java do you like a java"
     * */


    /*
     *
     * 功能：将一个byte 转成一个二进制的字符串
     * @param flag 标志是否需要补高位如果是true ，表示需要补高位，如果是false表示不补, 如果是最后一个字节，无需补高位
     * @param b 传入的 byte，需要将其转化为 二进制字符串，
     * @return
     *
     * 如果仅使用 String str = Integer.toBinaryString(temp); 进行返回，
     * 测试：输入 (byte)-1 ，输出 str=11111111111111111111111111111111
     *       输入 (byte)1  , 输出  java.lang.StringIndexOutOfBoundsException: String index out of range: -7 报错。
     * 说明 要对String str = Integer.toBinaryString(temp); 返回的数据进行处理，
     * 1、先进行截取后8位。
     * 2、对于正数 还要补高位。 |= 按位与 256   10000 0000 | 0000 0001 =》 10000 0001
     * */
    private static String byteToBitString(boolean flag, byte b) {
        // 使用变量 保存 b
        int temp = b;
        // 如果是正数我们还存在补高位
        if (flag) {
            temp |= 256; // |= 按位与 256   10000 0000 | 0000 0001 =》 10000 0001
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
//            System.out.println("str=" + str); // -1 str=11111111111111111111111111111111 , 1 java.lang.StringIndexOutOfBoundsException: String index out of range: -7 报错。
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /*
     * 编写 一个方法，完成对压缩数据的解码
     * */
    /**
     * @param huffmanCodes 哈夫曼编码表 map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {

        // 1、先得到 HuffmanBytes 对应的二进制的字符串，形式  1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        // 将 byte 数组转成 二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 判断是不是最后一个字节
            boolean flag = i == huffmanBytes.length - 1;
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }
        System.out.println("哈夫曼字节数组 对应的 二进制字符串=" + stringBuilder.toString()); // 1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100

        // 2、把字符串按照指定的哈夫曼编码进行解码
        // 把哈夫曼编码进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        System.out.println("map=" + map); // map={000=108, 01=32, 100=97, 101=105, 11010=121, 0011=111, 1111=107, 11001=117, 1110=101, 11000=100, 11011=118, 0010=106}

        // 创建一个集合， 存放 byte
        List<Byte> list = new ArrayList<>();
        // i 可以理解为 索引，扫描 stringBuilder  10101000101111111100100010111111110010001....
        for (int i = 0; i < stringBuilder.length(); ) { // 这里不再进行调整 i， 因为下面有 i += count ，已经调整了
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // 10101000101111111100100010111111110010001....
                // 递增的取出 key 1
                String key = stringBuilder.substring(i, i + count); // i不动，让count 移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) { // 说明没有匹配到
                    count++;
                } else {
                    // 匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count; // i 直接移动到 count
        }
        // 当 for 循环结束后， 我们list 中 就存放了所有的字符 “i like like like java do you like a java”
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    /*
    * 编写方法，将一个文件进行压缩
    * */

    /**
     *
     * @param srcFile 传入的压缩的文件全路径
     * @param dstFile 压缩后将压缩文件存放目录
     */
    public static void zipFile(String srcFile, String dstFile){

        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            // 创建文件的输入流、输出流
            fis = new FileInputStream(srcFile);
            // 创建一个和源文件大小一样的 byte 数组
            byte[] b = new byte[fis.available()];
            //读取文件
            fis.read(b);

            // 直接 对源文件 进行压缩
            byte[] huffmanBytes = huffmanZip(b);
            // 创建文件的输出流， 存放压缩文件
            fos = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(fos);
            // 以对象流的方式写入 哈夫曼编码，是为了以后 我们 恢复源文件时 使用
            oos.writeObject(huffmanBytes);
            /*
            * 这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            * 注意一定要把赫夫曼编码 写入压缩文件
            * */
            oos.writeObject(huffmanCodes);

        }catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                fos.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    //编写一个方法，完成对压缩文件的解压
    /**
     *
     * @param zipFile 准备解压的文件
     * @param dstFile 将文件解压到哪个路径
     */
    public static void unZipFile(String zipFile, String dstFile) {
        //定义 文件输入流
        InputStream is = null;
        //定义一个 对象输入流
        ObjectInputStream ois = null;
        //定义文件的输出流
        OutputStream os = null;
        try {
            //创建文件输入流
            is = new FileInputStream(zipFile);
            //创建一个和  is关联的对象输入流
            ois = new ObjectInputStream(is);

            //读取 哈夫曼字节数组  huffmanBytes
            byte[] huffmanBytes = (byte[])ois.readObject();

            //读取 赫夫曼编码表
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();

            //解码
            byte[] bytes = decode(huffmanCodes, huffmanBytes);

            //将bytes 数组写入到目标文件
            os = new FileOutputStream(dstFile);
            //写数据到 dstFile 文件
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }

}
