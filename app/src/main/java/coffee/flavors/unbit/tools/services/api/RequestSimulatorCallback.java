package coffee.flavors.unbit.tools.services.api;

/**
 * Abstract class to enforce implementation of required callback methods.
 * <p>
 * Used to listen for asynchronous responses from the RequestSimulator class.
 * You can create a new instance directly in your request:
 * -   RequestSimulator.get(context, endpoint, new RequestSimulatorCallback() {
 * -       @Override
 * -       public void onSuccess(String response) {
 * -           // TODO: Handle successful request response
 * -       }
 * -
 * -
 * -        @Override
 * -        public void onFailure(String response) {
 * -            // TODO: Handle unsuccessful request response
 * -        }
 * -    });
 * <p>
 * Created by amwomer on 1/5/18.
 */

/**
 * @disclaimer inherited from Drund.
 */
public abstract class RequestSimulatorCallback {

    public abstract void onSuccess(String response);

    public abstract void onFailure(String response);
}
