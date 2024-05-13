//
// Created by 14331 on 2023/11/27.
//
#include <stdio.h>

#define ARR_LEN 5

int main() {
    int arr[][3] = {{0, 0, 0},
                    {1, 1, 1},
                    {2, 2, 2}};
    printf("%p\n", *arr);
    printf("%p\n", &arr);
    printf("%p\n", arr);
    printf("%p\n", arr[0]);
    printf("%p\n", &arr[0][0]);
    printf("%d",*arr[0]);
    return 0;
}
