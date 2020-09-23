package kr.hs.dgsw.data.exception

import java.lang.RuntimeException

class TableEmptyException : RuntimeException {
    constructor(tableName: String): super("$tableName is empty")

    constructor(tableName: String, idx: Int): super("can not find $tableName for $idx")
}