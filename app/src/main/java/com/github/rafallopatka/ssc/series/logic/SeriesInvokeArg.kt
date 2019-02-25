package com.github.rafallopatka.ssc.series.logic

import java.io.Serializable
import java.util.*

sealed class SeriesInvokeArg: Serializable {
    class Create: SeriesInvokeArg(), Serializable
    data class Edit(val id: UUID): SeriesInvokeArg(), Serializable
    data class Run(val id: UUID): SeriesInvokeArg(), Serializable
    data class Delete(val id: UUID): SeriesInvokeArg(), Serializable
}