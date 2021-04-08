package com.cjastram.sap.kco

import com.sap.conn.jco.ext.*
import com.sap.conn.jco.rt.RuntimeEnvironment
import java.util.*


class EnvironmentConfig private constructor() : ServerDataProvider, DestinationDataProvider {
    @Throws(DataProviderException::class)
    override fun getServerProperties(s: String): Properties {
        return getPropertiesFromEnvironment(validServerKeys)
    }

    @Throws(DataProviderException::class)
    override fun getDestinationProperties(s: String): Properties {
        return getPropertiesFromEnvironment(validClientKeys)
    }

    override fun supportsEvents(): Boolean {
        return false
    }

    override fun setDestinationDataEventListener(destinationDataEventListener: DestinationDataEventListener) {
        throw RuntimeException("This method should never been called")
    }

    override fun setServerDataEventListener(serverDataEventListener: ServerDataEventListener) {
        throw RuntimeException("This method should never been called")
    }

    companion object {
        const val ENVIRONMENT = "ENVIRONMENT"

        /*

     */
        fun registerEnvironmentConfig() {
            val envConfig = EnvironmentConfig()
            RuntimeEnvironment.registerDestinationDataProvider(envConfig)
            RuntimeEnvironment.registerServerDataProvider(envConfig)
        }

        private fun getPropertiesFromEnvironment(keySet: Set<String>): Properties {
            val prop = Properties()
            val env = System.getenv()
            for (key in env.keys) {
                if (keySet.contains(key)) {
                    prop[key] = env[key]
                }
            }
            return prop
        }

        /**
         * The valid server keys for the configuration
         */
        val validServerKeys: Set<String> = HashSet(
            listOf(
                ServerDataProvider.JCO_GWHOST,
                ServerDataProvider.JCO_GWSERV,
                ServerDataProvider.JCO_MSHOST,
                ServerDataProvider.JCO_MSSERV,
                ServerDataProvider.JCO_SYSTEMID,
                ServerDataProvider.JCO_GROUP,
                ServerDataProvider.JCO_AS_UPDATE_INTERVAL,
                ServerDataProvider.JCO_PROGID,
                ServerDataProvider.JCO_TRACE,
                ServerDataProvider.JCO_SNC_MODE,
                ServerDataProvider.JCO_SNC_MYNAME,
                ServerDataProvider.JCO_SNC_QOP,
                ServerDataProvider.JCO_SNC_LIBRARY,
                ServerDataProvider.JCO_MAX_STARTUP_DELAY,
                ServerDataProvider.JCO_SAPROUTER,
                ServerDataProvider.JCO_REP_DEST,
                ServerDataProvider.JCO_REP_MAP,
                ServerDataProvider.JCO_CONNECTION_COUNT,
                ServerDataProvider.JCO_MAX_CONNECTION_COUNT,
                ServerDataProvider.JCO_WORKER_THREAD_COUNT,
                ServerDataProvider.JCO_WORKER_THREAD_MIN_COUNT,
                ServerDataProvider.JCO_APPLICATION,
                ServerDataProvider.JCO_ALLOWED_SYSTEM_IDS,
                ServerDataProvider.JCO_ALLOWED_SNC_PARTNER_NAMES
            )
        )

        /**
         * the valid client keys
         */
        val validClientKeys: Set<String> = HashSet(
            listOf(
                DestinationDataProvider.JCO_AUTH_TYPE,
                DestinationDataProvider.JCO_AUTH_TYPE_CONFIGURED_USER,
                DestinationDataProvider.JCO_AUTH_TYPE_CURRENT_USER,
                DestinationDataProvider.JCO_CLIENT,
                DestinationDataProvider.JCO_USER,
                DestinationDataProvider.JCO_ALIAS_USER,
                DestinationDataProvider.JCO_PASSWD,
                DestinationDataProvider.JCO_LANG,
                DestinationDataProvider.JCO_CODEPAGE,
                DestinationDataProvider.JCO_PCS,
                DestinationDataProvider.JCO_ASHOST,
                DestinationDataProvider.JCO_SYSNR,
                DestinationDataProvider.JCO_MSHOST,
                DestinationDataProvider.JCO_MSSERV,
                DestinationDataProvider.JCO_R3NAME,
                DestinationDataProvider.JCO_GROUP,
                DestinationDataProvider.JCO_STICKY_ASHOST,
                DestinationDataProvider.JCO_SAPROUTER,
                DestinationDataProvider.JCO_MYSAPSSO2,
                DestinationDataProvider.JCO_GETSSO2,
                DestinationDataProvider.JCO_X509CERT,
                DestinationDataProvider.JCO_EXTID_DATA,
                DestinationDataProvider.JCO_EXTID_TYPE,
                DestinationDataProvider.JCO_LCHECK,
                DestinationDataProvider.JCO_USE_BASXML,
                DestinationDataProvider.JCO_NETWORK,
                DestinationDataProvider.JCO_SERIALIZATION_FORMAT,
                DestinationDataProvider.JCO_DELTA,
                DestinationDataProvider.JCO_SNC_PARTNERNAME,
                DestinationDataProvider.JCO_SNC_QOP,
                DestinationDataProvider.JCO_SNC_MYNAME,
                DestinationDataProvider.JCO_SNC_MODE,
                DestinationDataProvider.JCO_SNC_SSO,
                DestinationDataProvider.JCO_SNC_LIBRARY
            )
        )
    }
}



