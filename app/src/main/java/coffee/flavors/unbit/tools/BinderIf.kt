package coffee.flavors.unbit.tools

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, Ensures views keep their promises for the view - view-model data binding relationship.
 */

/**
 * @desc contract for classes using generated BR files.
 */
interface BinderIf<V> {

    /**
     * [V] type of the calling class generated layout, associate the binding element.
     */
    var mBinding: V

    /**
     * Method used to enforce ui bindings happen.
     */
    fun fireUiBindings()
}