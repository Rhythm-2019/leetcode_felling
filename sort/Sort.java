package sort;

public class Sort {

    // 快速排序
    public void quickSort(char[] chars, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = partition(chars, start, end);
        System.out.println(pivot);
        quickSort(chars, start, pivot - 1);
        quickSort(chars, pivot + 1, end);
    }
    public int partition(char[] chars, int start, int end) {
        int pivot = end, counter = start;
        for (int i = start; i <= end; i++) {
            if (chars[i] < chars[pivot]) {
                char tmp = chars[i]; chars[i] = chars[counter]; chars[counter] = tmp;
                counter++;
            }
        }
        char tmp = chars[pivot]; chars[pivot] = chars[counter]; chars[counter] = tmp;
        return counter;
    }
}
