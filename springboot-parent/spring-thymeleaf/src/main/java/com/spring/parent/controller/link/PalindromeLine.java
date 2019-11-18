package com.spring.parent.controller.link;

public class PalindromeLine {

	public static void main(String[] args) {
		ListNode node1=new ListNode(1);
		ListNode node2=new ListNode(2);
		ListNode node3=new ListNode(3);
		ListNode node4=new ListNode(3);
		ListNode node5=new ListNode(2);
		ListNode node6=new ListNode(1);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		node5.next=node6;
		
		
		System.out.println(printNode(node3));
		System.out.println(printNode(reserve(node3)));
		
		System.out.println(isPalindrome(node1));
	}
	
	/**
     * 判断链表是否为回文链表
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        //如果链表只有一个有效节点或者没有有效节点，return true
        if (head == null || head.next == null) {
            return true;
        }
        ListNode quick = head;
        ListNode slow = head;
        //快慢指针，快指针一次走两步，慢指针一次走一步
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        //从slow开始反转后半段链表
        ListNode pre = null;
        ListNode p = slow;
        while (p != null) {
            ListNode temp = p.next;
            p.next = pre;//这个是拼接   3->null  --  2>3>null  -- 1>2>3>null   这里执行p.next=null 也就是 3->null  head也会改变。
            pre = p;
            p = temp;
        }
        //对比前半段和后半段的data值是否相同
        while (pre != null) {
            if (pre.val == head.val) {
                pre = pre.next;
                head = head.next;
            } else {
                return false;
            }
        }
        //返回true
        return true;
    }
    
    public static ListNode reserve(ListNode node) {
    	ListNode pre=null;
    	while(node!=null) {
    		ListNode temp=node.next;//2->3           //3->null              //null
    		node.next=pre;//1->null                  //2->1>null            //3->2->1->null
    		pre=node;//1->null						 //2->1->null			//3->2->1->null
    		node=temp;//2->3						 //3->null				//null
    	}
    	return pre;
    }
    
    public static String printNode(ListNode node) {
    	String str="";
    	while(node!=null) {
    		str+=node.val+"-->";
    		node=node.next;
    	}
    	return str;
    }
    
}
