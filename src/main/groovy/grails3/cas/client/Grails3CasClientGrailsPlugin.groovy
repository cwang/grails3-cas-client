package grails3.cas.client

import grails.plugins.*

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

    Closure doWithSpring() { {->
        } 
    }

    void doWithDynamicMethods() {
        // TODO Implement registering dynamic methods to classes (optional)
    }

    void doWithApplicationContext() {
        // TODO Implement post initialization spring config (optional)
    }

    void onChange(Map<String, Object> event) {
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    void onConfigChange(Map<String, Object> event) {
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    void onShutdown(Map<String, Object> event) {
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
