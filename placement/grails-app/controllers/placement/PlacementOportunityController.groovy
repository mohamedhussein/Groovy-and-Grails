package placement

import org.springframework.dao.DataIntegrityViolationException

class PlacementOportunityController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [placementOportunityInstanceList: PlacementOportunity.list(params), placementOportunityInstanceTotal: PlacementOportunity.count()]
    }

    def create() {
        [placementOportunityInstance: new PlacementOportunity(params)]
    }

    def save() {
        def placementOportunityInstance = new PlacementOportunity(params)
        if (!placementOportunityInstance.save(flush: true)) {
            render(view: "create", model: [placementOportunityInstance: placementOportunityInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), placementOportunityInstance.id])
        redirect(action: "show", id: placementOportunityInstance.id)
    }

    def show() {
        def placementOportunityInstance = PlacementOportunity.get(params.id)
        if (!placementOportunityInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), params.id])
            redirect(action: "list")
            return
        }

        [placementOportunityInstance: placementOportunityInstance]
    }

    def edit() {
        def placementOportunityInstance = PlacementOportunity.get(params.id)
        if (!placementOportunityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), params.id])
            redirect(action: "list")
            return
        }

        [placementOportunityInstance: placementOportunityInstance]
    }

    def update() {
        def placementOportunityInstance = PlacementOportunity.get(params.id)
        if (!placementOportunityInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (placementOportunityInstance.version > version) {
                placementOportunityInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'placementOportunity.label', default: 'PlacementOportunity')] as Object[],
                          "Another user has updated this PlacementOportunity while you were editing")
                render(view: "edit", model: [placementOportunityInstance: placementOportunityInstance])
                return
            }
        }

        placementOportunityInstance.properties = params

        if (!placementOportunityInstance.save(flush: true)) {
            render(view: "edit", model: [placementOportunityInstance: placementOportunityInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), placementOportunityInstance.id])
        redirect(action: "show", id: placementOportunityInstance.id)
    }

    def delete() {
        def placementOportunityInstance = PlacementOportunity.get(params.id)
        if (!placementOportunityInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), params.id])
            redirect(action: "list")
            return
        }

        try {
            placementOportunityInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'placementOportunity.label', default: 'PlacementOportunity'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
