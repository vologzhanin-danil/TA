#include <stdio.h>

int comparisons = 0;
int assignments = 0;
int calls = 0;

void printArray(int a[], int n) {
    printf("[");
    for (int i = 0; i < n; i++) {
        printf("%d", a[i]);
        if (i < n - 1) printf(", ");
    }
    printf("]\n");
}

void swap(int *x, int *y) {
    int t = *x;
    *x = *y;
    *y = t;
    assignments += 3;
}

int partition(int a[], int l, int r, int n) {
    int pivot = a[(l + r) / 2];
    int i = l, j = r;

    printf("Опорний елемент: %d\n", pivot);

    while (1) {
        while (a[i] < pivot) { i++; comparisons++; }
        while (a[j] > pivot) { j--; comparisons++; }

        printf("Поточні індекси: i=%d, j=%d\n", i, j);

        if (i >= j) {
            printf("Індекси перетнулися → повертаємо %d\n", j);
            return j;
        }

        printf("Обмін елементів: a[%d]=%d ↔ a[%d]=%d\n", i, a[i], j, a[j]);
        swap(&a[i], &a[j]);
        printArray(a, n);

        i++;
        j--;
    }
}

void quicksort(int a[], int l, int r, int n) {
    calls++;
    printf("\nВиклик QuickSort: l=%d, r=%d | масив: ", l, r);
    printArray(a, n);

    if (l >= r) return;

    int p = partition(a, l, r, n);

    quicksort(a, l, p, n);
    quicksort(a, p + 1, r, n);
}

int main() {
    int a[] = {68, 97, 12, 15, 31, 40, 22, 50, 53};
    int n = 9;

    printf("Початковий масив: ");
    printArray(a, n);

    quicksort(a, 0, n - 1, n);

    printf("\nВідсортований масив: ");
    printArray(a, n);

    printf("Загальна кількість порівнянь: %d\n", comparisons);
    printf("Загальна кількість присвоєнь: %d\n", assignments);
    printf("Кількість рекурсивних викликів: %d\n", calls);

    return 0;
}
