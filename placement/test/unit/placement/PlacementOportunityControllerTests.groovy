package placement



import org.junit.*
import grails.test.mixin.*

@TestFor(PlacementOportunityController)
@Mock(PlacementOportunity)
class PlacementOportunityControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/placementOportunity/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.placementOportunityInstanceList.size() == 0
        assert model.placementOportunityInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.placementOportunityInstance != null
    }

    void testSave() {
        controller.save()

        assert model.placementOportunityInstance != null
        assert view == '/placementOportunity/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/placementOportunity/show/1'
        assert controller.flash.message != null
        assert PlacementOportunity.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/placementOportunity/list'


        populateValidParams(params)
        def placementOportunity = new PlacementOportunity(params)

        assert placementOportunity.save() != null

        params.id = placementOportunity.id

        def model = controller.show()

        assert model.placementOportunityInstance == placementOportunity
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/placementOportunity/list'


        populateValidParams(params)
        def placementOportunity = new PlacementOportunity(params)

        assert placementOportunity.save() != null

        params.id = placementOportunity.id

        def model = controller.edit()

        assert model.placementOportunityInstance == placementOportunity
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/placementOportunity/list'

        response.reset()


        populateValidParams(params)
        def placementOportunity = new PlacementOportunity(params)

        assert placementOportunity.save() != null

        // test invalid parameters in update
        params.id = placementOportunity.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/placementOportunity/edit"
        assert model.placementOportunityInstance != null

        placementOportunity.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/placementOportunity/show/$placementOportunity.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        placementOportunity.clearErrors()

        populateValidParams(params)
        params.id = placementOportunity.id
        params.version = -1
        controller.update()

        assert view == "/placementOportunity/edit"
        assert model.placementOportunityInstance != null
        assert model.placementOportunityInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/placementOportunity/list'

        response.reset()

        populateValidParams(params)
        def placementOportunity = new PlacementOportunity(params)

        assert placementOportunity.save() != null
        assert PlacementOportunity.count() == 1

        params.id = placementOportunity.id

        controller.delete()

        assert PlacementOportunity.count() == 0
        assert PlacementOportunity.get(placementOportunity.id) == null
        assert response.redirectedUrl == '/placementOportunity/list'
    }
}
