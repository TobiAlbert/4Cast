package com.tobidaada.a4cast.presentation.mapper

interface Mapper<T, E> {

    fun from(e: E): T

    fun to(t: T): E

}