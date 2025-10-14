public class InsertionSort {
    public static void main(String[] args) {
        int[] data = {68, 97, 12, 15, 31, 40, 22, 50, 53};

        System.out.println("Невідсортований масив:");
        for (int n : data) {
            System.out.print(n + " ");
        }
        System.out.println("\n");

        insertionSort(data);

        System.out.println("\nВідсортований масив:");
        for (int n : data) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    private static void insertionSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int temp = nums[i];
            int pos = i - 1;

            System.out.println("Зовнішня ітерація " + i + ": вставляємо елемент " + temp);

            while (pos >= 0 && nums[pos] > temp) {
                System.out.println("  Внутрішня ітерація: порівнюємо " + temp + " з " + nums[pos] + 
                                   " → пересуваємо " + nums[pos] + " вправо");
                nums[pos + 1] = nums[pos];
                pos--;
            }

            nums[pos + 1] = temp;

            System.out.print("  Масив після вставки елемента " + temp + ": ");
            for (int n : nums) {
                System.out.print(n + " ");
            }
            System.out.println("\n");
        }
    }
}
