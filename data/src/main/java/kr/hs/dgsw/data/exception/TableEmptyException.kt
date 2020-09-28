package kr.hs.dgsw.data.exception

import androidx.room.EmptyResultSetException
import java.lang.RuntimeException

class TableEmptyException : RuntimeException {
    constructor(tableName: String): super("$tableName is empty")

    constructor(tableName: String, idx: Int): super("can not find $tableName where idx=$idx")

    constructor(emptyResultSetException: EmptyResultSetException): super(emptyResultSetException.message)
}