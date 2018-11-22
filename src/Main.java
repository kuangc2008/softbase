import java.util.Random;

public class Main {

    public static class ListNode {
        public int data;
        public ListNode next;

        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }


    public static class BinaryTreeNode {
        int value;
        BinaryTreeNode left;
        BinaryTreeNode right;
    }

    public static void swap(int array[], int left, int right) {
        if (array == null || left >= array.length || right >= array.length) {
            return;
        }

        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    public static void print(int array[]) {
        for (int a : array) {
            System.out.print(a + "   ");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");


//        旋转数组
        test5();


        //快速排序
//        test4();


        // 重建二叉树
//        test3();


        // 从头到尾打印链表
//        test2();


        //二维数组的查找
//        test1();


    }

    private static void test5() {
        int [] array = new int[] {10, 11, 12, 13, 14, 15, 16, 4, 5, 6,7, 8};
        System.out.println(getSmallInReverstArray(array));
    }

    private static int getSmallInReverstArray(int[] array) {
        if (array == null) {
            return -1;
        }

        int start = 0;
        int end = array.length - 1;
        while (start < end) {

            if (end - start == 1) {
                return (array[start] < array[end] ? array[start] : array[end]);
            }


            int middle = (start + end) / 2;
            if (array[middle] > array[start]) {
                start = middle + 1;
            } else if (array[middle] < array[start]) {
                end = middle;
            }
        }
        return array[start];
    }



    private static void test4() {
        int[] array = new int[] {10, 8, 6, 5, 4, 0, 3, 2, 1, 7, 9};
        quickSort(array, 0, array.length -1 );
        print(array);
    }

    private static void quickSort(int[] array, int start, int end) {
        if (array == null || end <= start) {
            return;
        }

        int index = partition(array, start, end);
        if (index + 1 < array.length - 1) {
            quickSort(array, index + 1, end);
        }
        if (index - 1> 0) {
            quickSort(array, start , index - 1);
        }
    }

    private static int partition(int[] array, int start, int end) {
        if (array == null) {
            return -1;
        }

        int randomIndex = start + new Random().nextInt(end - start);
        swap(array, randomIndex, end);

        int small = start - 1;
        for (int i = start; i <= end; i++) {

            if (array[i] < array[end]) {
                small++;
                if (i != small) {
                    swap(array, i, small);
                }
            }
        }

        small++;
        swap(array, small, end);
        return small;
    }


    private static void test3() {
        int[] preArray = new int[] {1, 2, 4, 7, 3, 5, 6, 8};
        int[] middleArray = new int[] {4, 7, 2, 1, 5, 3, 8, 6};

        BinaryTreeNode node = bindBinaryTree(preArray, middleArray);
        System.out.println(node);

    }

    private static BinaryTreeNode bindBinaryTree(int[] preArray, int[] middleArray) {
        if (preArray == null || middleArray == null) {
            return null;
        }
        return bindBinaryTreeInternal(preArray, 0, preArray.length - 1, middleArray, 0, preArray.length - 1);
    }

    private static BinaryTreeNode bindBinaryTreeInternal(int[] preArray, int preIndex, int preEnd,  int[] middleArray, int middleIndex, int middleEnd) {
        BinaryTreeNode node = new BinaryTreeNode();
        node.value = preArray[preIndex];
        int firstNodeIndex = findIndexArray(middleArray, middleIndex, middleEnd, node.value);

        if (firstNodeIndex >= 0 && firstNodeIndex  < middleEnd) {
            node.right = bindBinaryTreeInternal(preArray,  preEnd - (middleEnd - firstNodeIndex - 1) , preEnd,
                    middleArray, firstNodeIndex + 1,  middleEnd);
        }

        if (firstNodeIndex > preIndex) {
            node.left = bindBinaryTreeInternal(preArray, preIndex + 1, preIndex + firstNodeIndex  - middleIndex,
                    middleArray, middleIndex, firstNodeIndex - 1);
        }
        return node;
    }

    private static int findIndexArray(int[] array, int startCount, int endCount,  int num) {
        if (array == null) {
            return -1;
        }

        for (int i = startCount; i <= endCount; i++) {
            if (array[i] == num) {
                return i;
            }
        }
        return -1;
    }


    private static void test2() {
        ListNode head = new ListNode(10, new ListNode(20, new ListNode(50,
                new ListNode(8, new ListNode(88, new ListNode(1, null))))));

        printLinkedListReverst(head);
    }

    private static void printLinkedListReverst(ListNode head) {
        // 递归
        if (head == null) {
            return;
        }

        printLinkedListReverst(head.next);
        System.out.println(head.data);
    }



    public static void test1() {
        int[][] testData1 = new int[][] {
                {1, 2, 8, 9, 10},
                {2, 4, 9, 12, 18},
                {4, 7, 10, 13, 19},
                {6, 8 ,11, 15, 20}
        };
        System.out.println( findInTwoDimensionalArray(testData1, 3));
    }


    public static boolean findInTwoDimensionalArray(int[][] array, int num) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }

        int row = array.length;
        int column = array[0].length;

        int rowIndex = 0;
        int columenIndex = column -1;
        boolean found = false;
        while (rowIndex < row && columenIndex >= 0) {
            if (array[rowIndex][columenIndex] > num) {
                columenIndex--;
            } else if (array[rowIndex][columenIndex] < num) {
                rowIndex++;
            } else {
                found = true;
                break;
            }
        }

        return found;
    }



}
