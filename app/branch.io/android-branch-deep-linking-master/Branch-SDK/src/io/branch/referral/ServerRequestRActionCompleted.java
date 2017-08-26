package io.branch.referral;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.branch.referral.util.CommerceEvent;

/**
 * <p>
 * The server request for Action completed event. Handles request creation and execution.
 * </p>
 */
class ServerRequestRActionCompleted extends ServerRequest {

    private final BranchViewHandler.IBranchViewEvents callback_;

    /**
     * <p>Creates an ActionCompleteRequest instance. This request take care of reporting specific user
     * actions to Branch API, with additional app-defined meta data to go along with that action.</p>
     *
     * @param context  Current {@link Application} context
     * @param action   A {@link String} value to be passed as an action that the user has carried
     *                 out. For example "registered" or "logged in".
     * @param metadata A {@link JSONObject} containing app-defined meta-data to be attached to a
     *                 user action that has just been completed.
     */
    public ServerRequestRActionCompleted(Context context, CommerceEvent commerceEvent, JSONObject metadata, BranchViewHandler.IBranchViewEvents callback) {
        super(context, Defines.RequestPath.CompletedAction.getPath());
        callback_ = callback;
        JSONObject post = new JSONObject();

        try {
            post.put(Defines.Jsonkey.IdentityID.getKey(), prefHelper_.getIdentityID());
            post.put(Defines.Jsonkey.DeviceFingerprintID.getKey(), prefHelper_.getDeviceFingerPrintID());
            post.put(Defines.Jsonkey.SessionID.getKey(), prefHelper_.getSessionID());
            if (!prefHelper_.getLinkClickID().equals(PrefHelper.NO_STRING_VALUE)) {
                post.put(Defines.Jsonkey.LinkClickID.getKey(), prefHelper_.getLinkClickID());
            }
            post.put(Defines.Jsonkey.Event.getKey(), "purchase");
            if (metadata != null) {
                post.put(Defines.Jsonkey.Metadata.getKey(), metadata);
            }
            if (commerceEvent != null) {
                post.put(Defines.Jsonkey.CommerceData.getKey(), commerceEvent.getCommerceJSONObject());
            }
            updateEnvironment(context, post);
            setPost(post);
        } catch (JSONException ex) {
            ex.printStackTrace();
            constructError_ = true;
        }
    }

    public ServerRequestRActionCompleted(String requestPath, JSONObject post, Context context) {
        super(requestPath, post, context);
        callback_ = null;
    }

    @Override
    public void onRequestSucceeded(ServerResponse resp, Branch branch) {
        // Check for any Branch view associated with this request.
        if (resp.getObject() != null && resp.getObject().has(Defines.Jsonkey.BranchViewData.getKey())) {
            if ((Branch.getInstance().currentActivityReference_ != null && Branch.getInstance().currentActivityReference_.get() != null)) {
                String actionName = "";
                try {
                    JSONObject post = getPost();
                    if (post != null && post.has(Defines.Jsonkey.Event.getKey())) {
                        actionName = post.getString(Defines.Jsonkey.Event.getKey());
                    }
                    if (Branch.getInstance().currentActivityReference_ != null) {
                        Activity currentActivity = Branch.getInstance().currentActivityReference_.get();
                        JSONObject branchViewJsonObj = resp.getObject().getJSONObject(Defines.Jsonkey.BranchViewData.getKey());
                        BranchViewHandler.getInstance().showBranchView(branchViewJsonObj, actionName, currentActivity, callback_);
                    }
                } catch (JSONException exception) {
                    if (callback_ != null) {
                        callback_.onBranchViewError(BranchViewHandler.BRANCH_VIEW_ERR_INVALID_VIEW, "Unable to show branch view. Branch view received is invalid ", actionName);
                    }
                }
            }
        }
    }

    @Override
    public void handleFailure(int statusCode, String causeMsg) {
        //No implementation on purpose
    }

    @Override
    public boolean handleErrors(Context context) {
        if (!super.doesAppHasInternetPermission(context)) {
            Log.i("BranchSDK", "Trouble executing your request. Please add 'android.permission.INTERNET' in your applications manifest file");
            return true;
        }
        return false;
    }

    @Override
    public boolean isGetRequest() {
        return false;
    }

    @Override
    public void clearCallbacks() {
        //No implementation on purpose
    }

    @Override
    public boolean shouldRetryOnFail() {
        return true;   //Action completed request need to retry on failure.
    }
}
