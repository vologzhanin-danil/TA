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

void merge(int a[], int l, int m, int r, int n) {
    int i = l, j = m + 1, k = 0;
    int temp[r - l + 1];

    printf("Зливаємо: ");
    printArray(a + l, m - l + 1);
    printf("і ");
    printArray(a + j, r - m);

    while (i <= m && j <= r) {
        comparisons++;
        if (a[i] <= a[j]) {
            temp[k++] = a[i++];
            printf("Порівняння: %d ≤ %d → додаємо %d\n", a[i-1], a[j], a[i-1]);
        } else {
            temp[k++] = a[j++];
            printf("Порівняння: %d ≤ %d → додаємо %d\n", a[i], a[j-1], a[j-1]);
        }
        assignments++;
    }

    while (i <= m) {
        temp[k++] = a[i++];
        printf("Додаємо залишок з лівої частини: %d\n", a[i-1]);
        assignments++;
    }

    while (j <= r) {
        temp[k++] = a[j++];
        printf("Додаємо залишок з правої частини: %d\n", a[j-1]);
        assignments++;
    }

    for (i = l, k = 0; i <= r; i++, k++) {
        a[i] = temp[k];
        assignments++;
    }

    printf("Результат злиття: ");
    printArray(a, n);
    printf("\n");
}

void mergeSort(int a[], int l, int r, int n) {
    calls++;
    printf("Виклик mergeSort(l=%d, r=%d): ", l, r);
    printArray(a, n);

    if (l >= r) return;

    int m = (l + r) / 2;

    mergeSort(a, l, m, n);
    mergeSort(a, m + 1, r, n);
    merge(a, l, m, r, n);
}

int main() {
    int a[] = {68, 97, 12, 15, 31, 40, 22, 50, 53};
    int n = sizeof(a) / sizeof(a[0]);

    printf("Початковий масив: ");
    printArray(a, n);
    printf("\n");

    mergeSort(a, 0, n - 1, n);

    printf("Відсортований масив: ");
    printArray(a, n);

    printf("Загальна кількість порівнянь: %d\n", comparisons);
    printf("Загальна кількість присвоєнь: %d\n", assignments);
    printf("Кількість рекурсивних викликів: %d\n", calls);

    return 0;
}

