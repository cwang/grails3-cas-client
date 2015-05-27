package grail3.cas.client

import javax.servlet.http.HttpServletResponse
import edu.yale.its.tp.cas.client.filter.CASFilter

class CasController {

	def index() = {
		if (!grailsApplication.config.cas.mocking) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}

		def model
		if (params.u) {
			session?.setAttribute(CASFilter.CAS_FILTER_USER, params.u)
			model = [message: "Current cas-ified user is [${params.u}].", result: true]
		}
		else {
			model = [message: "Please supply a parameter 'u'!", result: false]
		}
		model
	}
}
