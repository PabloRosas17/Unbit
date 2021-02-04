package coffee.flavors.unbit.tools

/**
 * @author, evolandlupiz
 * @date, 5/22/2020
 * @property, Unbit
 * @purpose, delegate as a contract for views that implement dark mode theme.
 */

/**
 * @desc delegate assistant for themes.
 */
interface ThemeCallbackIf {

    /**
     * [call] as a delegate.
     * @param mode as the theme mode.
     */
    fun call(mode: Boolean)
}