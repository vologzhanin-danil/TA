public class SelectionSort {
    public static void main(String[] args) {
        int[] data = {};

        System.out.println("Невідсортований масив:");
        for (int n : data) {
            System.out.print(n + " ");
        }
        System.out.println("\n");

        selectionSort(data);

        System.out.println("Відсортований масив:");
        for (int n : data) {
            System.out.print(n + " ");
        }
        System.out.println();
    }

    private static void selectionSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minPos = i;
            System.out.println("Зовнішня ітерація " + (i + 1) + ": шукаємо мінімальний елемент від позиції " + i);

            for (int j = i + 1; j < nums.length; j++) {
                System.out.println("  Внутрішня ітерація: порівнюємо " + nums[j] + " з " + nums[minPos]);
                if (nums[j] < nums[minPos]) {
                    minPos = j;
                    System.out.println("    Знайдено новий мінімум: " + nums[minPos] + " на позиції " + minPos);
                }
            }

            // обмін елементів
            int tmp = nums[i];
            nums[i] = nums[minPos];
            nums[minPos] = tmp;

            System.out.print("  Масив після обміну: ");
            for (int n : nums) {
                System.out.print(n + " ");
            }
            System.out.println("\n");
        }
    }
}
