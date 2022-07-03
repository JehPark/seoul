package model.entity

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import model.JsonColumnSupport
import org.slf4j.LoggerFactory
import java.io.IOException
import javax.persistence.Column

abstract class BaseJsonEntity<E> : JsonColumnSupport<E> {
    companion object {
        val OBJECT_MAPPER = ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(KotlinModule())
        val logger = LoggerFactory.getLogger(BaseJsonEntity::class.java)
    }

    @Column(name = "v", columnDefinition = "MEDIUMTEXT")
    private var v: String? = null
    override fun getV(): String? {
        val jsonColumns = getJsonColumn()
        if (jsonColumns != null) {
            try {
                v = OBJECT_MAPPER.writeValueAsString(jsonColumns)
            } catch (e: JsonProcessingException) {
                throw RuntimeException("error when parsing the v column: $v", e)
            }
        }
        return v
    }

    override fun setV(v: String?) {
        this.v = v
        if (v != null) {
            try {
                val e = OBJECT_MAPPER.readValue(v, getType())
                setJsonColumn(e)
            } catch (e: IOException) {
                throw RuntimeException("error when parsing the v column: $v", e)
            }
        }
    }

    fun validateVColumn() {
        if (getJsonColumn() == null && v != null) {
            setV(v)
        }

        if (getJsonColumn() == null) {
            setJsonColumn(null)
        }
    }

    fun generateVColumn() {
        getV()
    }
}