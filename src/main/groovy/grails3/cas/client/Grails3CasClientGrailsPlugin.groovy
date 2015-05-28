package grails3.cas.client

import grails.plugins.*
import edu.yale.its.tp.cas.client.filter.*
import org.springframework.boot.context.embedded.FilterRegistrationBean
import org.springframework.core.Ordered

class Grails3CasClientGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.0.0 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title = "Ja-sig CAS Client for Grails 3"
    def author = "Chen Wang"
    def authorEmail = "dev@chenwang.org"
    def description = '''\
The plugin handles configurations of JA-SIG CAS client integration using Yale Java client library versioned 2.1.1 with
some added features, rebuilt for Grails 3. Please note there is another Java client library which is heavily Spring based; 
Although it seems a natural fit for Grails applications, it requires more configuration works to get started.

Please make sure necessary configurations are made in your Grails application's Config.groovy file.
'''
    def profiles = ['web']

    def documentation = "https://github.com/cwang/grails3-cas-client/wiki"

    def license = "APACHE"

    def issueManagement = [ system: "github", url: "https://github.com/cwang/grails3-cas-client/issues" ]

    def scm = [ url: "https://github.com/cwang/grails3-cas-client.git" ]
    
    // plugin-specific variables
    private final static String PACKAGE_PREFIX = CASFilter.class.package.name + '.'
    private final static String[] FIELDS_OPTIONAL = ['serverName', 'serviceUrl', 'proxyCallbackUrl', 'authorizedProxy', 'renew', 'wrapRequest']
    private final static String[] FIELDS_REQUIRED = ['loginUrl', 'validateUrl']
    private final static String OUTPUT_FORMAT = "[CAS Client Plugin] %s\n"

    Closure doWithSpring() { {->
            if (config.cas.disabled) {
                printf(OUTPUT_FORMAT, 'CAS Client plugin is disabled therefore nothing needs to be done here.')
            }
            else {
                casFilter(FilterRegistrationBean) {
                    filter = bean(CASFilter)
                    initParameters = (FIELDS_REQUIRED + FIELDS_OPTIONAL).findAll { config.cas[it] instanceof String || config.cas[it] instanceof Boolean }.collectEntries { [(PACKAGE_PREFIX + it): config.cas[it]] }
                    urlPatterns = config.cas.urlPattern
                    order = Ordered.HIGHEST_PRECEDENCE
                }
                printf(OUTPUT_FORMAT, "CAS Client plugin is enabled with CAS login url [${config.cas.loginUrl}].")
            }
                              
            if (config.cas.mocking) {
                printf(OUTPUT_FORMAT, '/cas?u=USERNAME is available for mocking cas-ified user session.')
                printf(OUTPUT_FORMAT, 'Please take extra care as mocking should NOT be allowed for production environment!')

            }
        } 
    }

    void doWithDynamicMethods() {
        // Nothing to do.
    }

    void doWithApplicationContext() {
        // Nothing to do.
    }

    void onChange(Map<String, Object> event) {
        // Nothing to do.
    }

    void onConfigChange(Map<String, Object> event) {
        // Nothing to do.
    }

    void onShutdown(Map<String, Object> event) {
        // Nothing to do.
    }
}
