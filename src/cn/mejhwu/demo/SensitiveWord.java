package cn.mejhwu.demo;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Author: mejhwu
 * Date:   17-10-9
 * E-mail: mejhwu@163.com
 * Description:
 */

public class SensitiveWord {
    //默认敏感词替换符
    private static final String DEFAULT_REPLACEMENT = "*";
    //根节点
    private TrieNode rootNode = new TrieNode();

    private class TrieNode {
        /**
         * true 关键词的终结 ； false 继续
         */
        private boolean end = false;

        /**
         * key下一个字符，value是对应的节点
         */
        private Map<Character, TrieNode> subNodes = new HashMap<Character ,TrieNode>();

        /**
         * 向指定位置添加节点树
         */
        void addSubNode(Character key, TrieNode node) {
            subNodes.put(key, node);
        }
        /**
         * 获取下个节点
         */
        TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }

        boolean isKeywordEnd() {
            return end;
        }

        void setKeywordEnd(boolean end) {
            this.end = end;
        }
    }

    //判断是否是一个符号
    private boolean isSymbol(char c) {
        int ic = (int) c;
        // 0x2E80-0x9FFF 东亚文字范围
        return !((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')|| (c >= 'A' && c <= 'Z')) && (ic < 0x2E80 || ic > 0x9FFF);
    }


    /**
     * 过滤敏感词
     */
    public String filter(String text) {
        if (text.trim().length() == 0) {
            return text;
        }
        String replacement = DEFAULT_REPLACEMENT;
        StringBuilder result = new StringBuilder();

        TrieNode tempNode = rootNode;
        int begin = 0; // 回滚数
        int position = 0; // 当前比较的位置

        while (position < text.length()) {
            char c = text.charAt(position);
            // 空格直接跳过
            if (isSymbol(c)) {
                if (tempNode == rootNode) {
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }

            tempNode = tempNode.getSubNode(c);

            // 当前位置的匹配结束
            if (tempNode == null) {
                // 以begin开始的字符串不存在敏感词
                result.append(text.charAt(begin));
                // 跳到下一个字符开始测试
                position = begin + 1;
                begin = position;
                // 回到树初始节点
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // 发现敏感词， 从begin到position的位置用replacement替换掉
                for (int i = begin; i <= position; i++) {
                    result.append(replacement);
                }
                position = position + 1;
                begin = position;
                tempNode = rootNode;
            } else {
                ++position;
            }
        }

        result.append(text.substring(begin));

        return result.toString();
    }

    /**
     * 构造字典树
     * @param lineTxt
     */
    private void addWord(String lineTxt) {
        TrieNode tempNode = rootNode;
        // 循环每个字节
        for (int i = 0; i < lineTxt.length(); ++i) {
            Character c = lineTxt.charAt(i);
            // 过滤空格
            if (isSymbol(c)) {
                continue;
            }
            TrieNode node = tempNode.getSubNode(c);

            if (node == null) { // 没初始化
                node = new TrieNode();
                tempNode.addSubNode(c, node);
            }

            tempNode = node;

            if (i == lineTxt.length() - 1) {
                // 关键词结束， 设置结束标志
                tempNode.setKeywordEnd(true);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        SensitiveWord filter = new SensitiveWord();
        File sensitiveWordFile = new File("/home/mejhwu/idea_workspace/algorithm/src/cn/mejhwu/demo/sensitivewords.txt");
        BufferedReader reader = new BufferedReader(new FileReader(sensitiveWordFile));

        String s = null;
        while ((s = reader.readLine()) != null) {
            filter.addWord(s);
        }
        reader.close();

        reader = new BufferedReader(new FileReader(new File("/home/mejhwu/idea_workspace/algorithm/src/cn/mejhwu/demo/content.txt")));

        PrintWriter writer = new PrintWriter(new FileWriter("/home/mejhwu/idea_workspace/algorithm/src/cn/mejhwu/demo/out.txt"));

        s = null;

        while ((s = reader.readLine()) != null) {
            String r = filter.filter(s);
            writer.println(r);
        }
        reader.close();
        writer.close();
    }
}
