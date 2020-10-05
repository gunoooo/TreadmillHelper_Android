package kr.hs.dgsw.data.etc.extension

@Suppress("UNCHECKED_CAST")
fun ArrayList<*>.refreshAll(list: List<*>): ArrayList<*> {
    this.clear()
    (this as ArrayList<Any>).addAll(list as ArrayList<Any>)
    return this
}