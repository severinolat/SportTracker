package com.example.bnav.util

fun <T1 : Any, T2 : Any, R : Any> safeLet(p1: T1?, p2: T2?, fn: (T1, T2) -> R?): R? {
    return if (p1 != null && p2 != null) fn(p1, p2) else null
}