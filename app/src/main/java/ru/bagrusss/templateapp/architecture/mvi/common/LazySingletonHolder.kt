package ru.bagrusss.templateapp.architecture.mvi.common


open class LazySingletonHolder<out T, in A>(
    private val instanceCreator: (A) -> T
) {

    @Volatile
    private var savedInstance: T? = null

    @Volatile
    private lateinit var argumentSupplier: () -> A

    fun init(argumentSupplier: () -> A) {
        this.argumentSupplier = argumentSupplier
    }

    /**
     * Be sure to call this when instance has been initialized
     */
    val instance: T
        get() {
            savedInstance?.let {
                return it
            }

            return synchronized(this) {
                val i = savedInstance
                if (i != null) {
                    i
                } else {
                    val arguments = argumentSupplier()
                    val created = instanceCreator(arguments)
                    savedInstance = created
                    created
                }
            }
        }

    @Synchronized
    fun clear() {
        savedInstance = null
    }

}