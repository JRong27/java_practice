package java_0325;

public class MyQueueByLinkedList {
//链表方式建立
    // Node 是内部类，定义在某个类或方法中的类
    // static 使创建的 Node 的实例不依赖 MyQueue 这个类某个实例
    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 创建链表就要有一个头结点，队列要有头有尾是关键,所以要记录头尾
    // 给予立案表实现实现队列，头尾插入删除不固定，可互换
    private Node head = null;
    private Node tail = null;

    // 此处用 “尾部入，头部出” 的方式实现
    // 入队列
    public void offer(int val) {
        Node newNode = new Node(val);
        // 空则直接赋值
        if (head == null) {
            head = newNode;
            tail = newNode;
        }
        // 非空则移动尾部next
        else {
            tail.next = newNode;
            tail = tail.next;
        }
    }

    // 出队列
    public Integer poll() {
        // 若队列本来为空，则返回错误值
        if (head == null) {
            // 为了返回错误值null，要把 int 改为 Integer
            return null;
        }
        int ret = head.val;
        head = head.next;
        // 若删除当前元素后 head 为 null
        // 则应为空队列，让 tail 也为 null
        if (head == null) {
            tail = null;
        }
        return ret;
    }

}
