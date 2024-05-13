cmake_minimum_required(VERSION 3.26)
project(wsw_c C)

set(CMAKE_C_STANDARD 99)

add_executable(wsw_c main.c
        w0/hello.c)

add_executable(hello w0/hello.c
        w0/arr.c)