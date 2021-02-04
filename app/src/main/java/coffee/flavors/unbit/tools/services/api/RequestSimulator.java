package coffee.flavors.unbit.tools.services.api;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import java.util.Map;
import java.util.Random;

import coffee.flavors.unbit.R;

/**
 * A basic extrapolation of server request logic. This class will simulate GET and POST requests
 * that would normally be sent to a backend server implementation. We'll take care of the middle-man here
 * and return a similarly formatted JSON String response that would typically be expected.
 * <p>
 * Both `get` and `post` methods will take a RequestSimulatorCallback to listen for asynchronous
 * `onSuccess` and `onFailure` callbacks. For more information on its usage, see RequestSimulatorCallback.java.
 * <p>
 * The `post` method will require an additional parameter for requestParams. The only endpoint used
 * in this example is the `members/create/` endpoint so we'll provide the keys here as well:
 * -     "display_name" - String
 * -     "job_title" - String
 * -     "description" - String
 * Example usage would be as follows:
 * -    Map<String, String> requestParams = new HashMap<>();
 * -    requestParams.put("display_name", "Some User Name");
 * <p>
 * Created by amwomer on 1/5/18.
 */

/**
 * @disclaimer inherited from Drund.
 */
public class RequestSimulator {

    // Static endpoint references for easier visibility.
    public static final String ENDPOINT_CREATE_MEMBER = "https://api.drund.com/members/create/";
    public static final String ENDPOINT_GET_MEMBERS = "https://api.drund.com/members/";
    public static final String ENDPOINT_GET_MEMBER_HOBBIES = "https://api.drund.com/members/%d/hobbies/";
    private static final Integer ENDPOINT_GET_DELAY = 2500;
    private static final Integer ENDPOINT_POST_DELAY = 2500;

    // Create private constructor to restrict instantiation.
    // This class should be used in a static manner.
    private RequestSimulator() {
        throw new InstantiationError("Instantiation of this class is not allowed.");
    }

    // Simulate a GET request by waiting a small amount of time.
    // Use a RequestSimulatorCallback to return a String to simulate a server response
    // In the event of a successful request, we'll provide a JSON String that can be parsed to retrieve
    // the desired information.
    public static void get(final Context context, final String endpoint, final RequestSimulatorCallback callback) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (endpoint.equals(ENDPOINT_GET_MEMBERS)) {
                    callback.onSuccess(context.getResources().getString(R.string.get_members_success));
                } else if (endpoint.contains("hobbies")) {
                    if (endpoint.equals(ENDPOINT_GET_MEMBER_HOBBIES)) {
                        // String is a direct match, return an error that they must not have supplied a member ID.
                        callback.onFailure(context.getResources().getString(R.string.endpoint_not_found_no_member_id));
                    } else {
                        int memberId = findMemberHobbyId(endpoint);
                        switch (memberId % 3) {
                            case 0:
                                callback.onSuccess(context.getResources().getString(R.string.get_member_hobbies_success_0));
                                break;
                            case 1:
                                callback.onSuccess(context.getResources().getString(R.string.get_member_hobbies_success_1));
                                break;
                            case 2:
                                callback.onSuccess(context.getResources().getString(R.string.get_member_hobbies_success_2));
                                break;
                        }
                    }
                } else {
                    callback.onFailure(context.getResources().getString(R.string.endpoint_not_found));
                }
            }
        }, ENDPOINT_GET_DELAY);
    }

    /**
     * Simulate a POST request by waiting a small amount of time.
     * Use a RequestSimulatorCallback to return a String to simulate a server response
     * In the event of a successful request, we'll provide a JSON String that can be parsed to retrieve
     * the desired information.
     *
     * @param context       An Activity context to use for retrieving resources such as Strings
     * @param requestParams A HashMap of key-value pairs to provide the required params (Ex. `params.put("display_name", "Test User");)
     *                      Any missing params will throw an error that they are required.
     * @param endpoint      The URL we'd normally access from the server (Ex. "https://api.drund.com/members/create/")
     * @param callback      A RequestSimulatorCallback to handle Success and Failure responses
     */
    public static void post(final Context context, final Map<String, String> requestParams, final String endpoint, final RequestSimulatorCallback callback) {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (endpoint.equals(ENDPOINT_CREATE_MEMBER)) {
                    String errorResponse = getCreateMemberRequestParamsErrors(context, requestParams);
                    if (errorResponse == null) {
                        callback.onSuccess(getCreateMemberRequestSuccessString(requestParams));
                    } else {
                        callback.onFailure(errorResponse);
                    }
                } else {
                    callback.onFailure(context.getResources().getString(R.string.endpoint_not_found));
                }
            }
        }, ENDPOINT_POST_DELAY);
    }

    // Helper function to pull out logic for forming the error response of the `members/create/` endpoint
    // returns `null` when there are no errors.
    @Nullable
    private static String getCreateMemberRequestParamsErrors(Context context, Map<String, String> requestParams) {
        CreateMemberRequestErrors requestErrors = new CreateMemberRequestErrors();
        boolean requestHasErrors = false;

        if (!requestParams.containsKey("display_name")) {
            requestHasErrors = true;
            requestErrors.getErrors().setDisplayName(context.getResources().getString(R.string.error_payload_field_required));
        } else if (requestParams.get("display_name").trim().isEmpty()) {
            requestHasErrors = true;
            requestErrors.getErrors().setDisplayName(context.getResources().getString(R.string.error_field_empty));
        }

        if (!requestParams.containsKey("job_title")) {
            requestHasErrors = true;
            requestErrors.getErrors().setJobTitle(context.getResources().getString(R.string.error_payload_field_required));
        } else if (requestParams.get("job_title").trim().isEmpty()) {
            requestHasErrors = true;
            requestErrors.getErrors().setJobTitle(context.getResources().getString(R.string.error_field_empty));
        }

        if (!requestParams.containsKey("description")) {
            requestHasErrors = true;
            requestErrors.getErrors().setDescription(context.getResources().getString(R.string.error_payload_field_required));
        } else if (requestParams.get("description").trim().isEmpty()) {
            requestHasErrors = true;
            requestErrors.getErrors().setDescription(context.getResources().getString(R.string.error_field_empty));
        }

        if (requestHasErrors) {
            return new Gson().toJson(requestErrors);
        } else {
            return null;
        }
    }

    // Dynamically build a JSON response for the `members/create/` endpoint success.
    // Simply pass through the parameters that the user provided.
    private static String getCreateMemberRequestSuccessString(Map<String, String> requestParams) {
        int randomInt = new Random().nextInt();
        String response = "";
        response += "{\"display_name\":\"";
        response += requestParams.get("display_name");
        response += "\",\"job_title\":\"";
        response += requestParams.get("job_title");
        response += "\",\"description\":\"";
        response += requestParams.get("description");
        response += "\",\"id\":\"";
        response += Math.abs(randomInt);
        response += "\"}";
        return response;
    }

    // Terrible implementation, but find the memberId supplied by the user and return a response
    // correlating to that member.
    private static int findMemberHobbyId(String endpoint) {
        String[] parts = endpoint.split("/");
        for (String part : parts) {
            try {
                return Integer.valueOf(part);
            } catch (Exception e) {
                // no-op, keep looking for valid memberId;
            }
        }

        return 0;
    }
}
