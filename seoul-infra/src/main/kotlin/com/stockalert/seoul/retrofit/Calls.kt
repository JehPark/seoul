package com.stockalert.seoul.retrofit

import arrow.core.Either
import retrofit2.Call
import retrofit2.Response

inline fun <L, reified T> Call<T>.toEither(leftMapper: (res: Response<T>) -> L): Either<L, T> =
    this.execute().let {
        if (!it.isSuccessful) Either.Left(leftMapper(it))
        else if (Unit is T) Either.Right(Unit)
        else Either.Right(it.body()!!)
    }

inline fun <X : Throwable, reified T> Call<T>.orElseThrow(supplyException: (res: Response<T>) -> X): T =
    this.toEither { supplyException(it) }.fold({ throw it }, { it })

inline fun <reified T> Call<T>.getOrNull(): T? = this.toEither { }.orNull()
