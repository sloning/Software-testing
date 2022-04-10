package lab.softwaretesting.util

import java.io.FileInputStream
import java.util.*

class PropertiesManager {
    companion object {
        private lateinit var properties: Properties

        init {
            var fileInputStream: FileInputStream? = null
            try {
                fileInputStream = FileInputStream("src/test/resources/config.properties")
                properties = Properties()
                properties.load(fileInputStream)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        fun getProperty(key: String): String = properties.getProperty(key)
    }
}